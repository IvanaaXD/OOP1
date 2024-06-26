package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.ListSelectionModel;

import fileMenager.KozmeticarFM;
import fileMenager.MenadzerFM;
import korisnici.Klijent;
import korisnici.Kozmeticar;
import korisnici.Menadzer;
import meni.appSettings;
import model.KlijentTabelaModel;
import model.KozmeticarTabelaModel;
import model.MenadzerTabelaModel;
import net.miginfocom.swing.MigLayout;
import popup.KozmeticarPopUp;
import popup.MenadzerPopUp;

public class MenadzerTabelaFrame extends JPanel{

	private static final long serialVersionUID = 8456560429229699542L;

	//private StudentManager studentMng;
	
	protected JToolBar mainToolbar = new JToolBar();
	protected JButton btnAdd = new JButton();
	protected JButton btnEdit = new JButton();
	protected JButton btnDelete = new JButton();
	protected JTextField tfSearch = new JTextField(20);
	protected JTable table;
	private appSettings app;
	private JFrame parentFrame;

	private MenadzerFM menadzerifm;

	public MenadzerTabelaFrame(JFrame parentFrame, MenadzerFM menadzerifm, appSettings app) {
		this.menadzerifm = menadzerifm;
		this.parentFrame = parentFrame;	
		this.app = app;
		
		btnAdd.setText("Add");
		btnEdit.setText("Edit");
		btnDelete.setText("Delete");
		
		setLayout(new MigLayout("wrap 3"));

		mainToolbar.add(btnAdd);
		mainToolbar.add(btnEdit);
		mainToolbar.add(btnDelete);

		// Disable toolbar floating
		mainToolbar.setFloatable(false);	
		add(mainToolbar,  "dock north");
		
		// podesavanje tabele

		ArrayList<Menadzer> menadzeri = new ArrayList<Menadzer> (menadzerifm.getMenadzeri().values());
		table = new JTable(new MenadzerTabelaModel(menadzeri));	
		
		Dimension parentSize = parentFrame.getContentPane().getSize();
		table.setPreferredScrollableViewportSize(parentSize);
		
		table.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.getTableHeader().setReorderingAllowed(false);
		
		JScrollPane sc = new JScrollPane(table);
		add(sc, "dock center");
				
		initActions();
	}

	private void initActions() {
		btnAdd.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				MenadzerPopUp add = new MenadzerPopUp(MenadzerTabelaFrame.this, menadzerifm, app, null);
				add.setVisible(true);
				refreshData();
			}
		});
		
		btnEdit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int red = table.getSelectedRow();
				if(red == -1) {
					JOptionPane.showMessageDialog(null, "Morate odabrati red u tabeli.", "Greska", JOptionPane.WARNING_MESSAGE);
				}else {
					//int id = Integer.parseInt(table.getValueAt(red, 0).toString());
					Menadzer k = menadzerifm.PronadjiMenadzeraPoId(red);
					if(k != null) {
						
						MenadzerPopUp add = new MenadzerPopUp(MenadzerTabelaFrame.this, menadzerifm, app, k);
						add.setVisible(true);
						refreshData();

					}else {
						JOptionPane.showMessageDialog(null, "Nije moguce pronaci odabranog menadzera!", "Greska", JOptionPane.ERROR_MESSAGE);
					}
				}	

			}
		});
		btnDelete.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int red = table.getSelectedRow();
				if(red == -1) {
					JOptionPane.showMessageDialog(null, "Morate odabrati red u tabeli.", "Greska", JOptionPane.WARNING_MESSAGE);
				}else {
					//int id = Integer.parseInt(table.getValueAt(red, 0).toString());
					Menadzer k = menadzerifm.PronadjiMenadzeraPoId(red);
					System.out.println(k);
					System.out.println(red);
					if(k != null) {
						int izbor = JOptionPane.showConfirmDialog(null,"Da li ste sigurni da zelite da obrisete menadzera?", 
								k.getIme() + " "+k.getPrezime() +" - Potvrda brisanja", JOptionPane.YES_NO_OPTION);
						if(izbor == JOptionPane.YES_OPTION) {
							k.setObrisan(true);
							menadzerifm.saveData();
							refreshData();
						}
					}else {
						JOptionPane.showMessageDialog(null, "Nije moguce pronaci odabranog menadzera!", "Greska", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
	}

	public void refreshData() {
		ArrayList<Menadzer> menadzeri = new ArrayList<Menadzer> (menadzerifm.getMenadzeri().values());

		MenadzerTabelaModel mtm = new MenadzerTabelaModel(menadzeri);
		
		table.setModel(mtm);	

	}
}

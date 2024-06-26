package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Map;

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

import fileMenager.KlijentFM;
import korisnici.Klijent;
import meni.appSettings;
import model.KlijentTabelaModel;
import net.miginfocom.swing.MigLayout;
import popup.KlijentPopUp;

public class KlijentTabelaFrame extends JPanel {

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

	private KlijentFM klijentifm;

	public KlijentTabelaFrame(JFrame parentFrame, KlijentFM klijentifm, appSettings app) {
		this.klijentifm = klijentifm;
		this.parentFrame = parentFrame;	
		this.app = app;
		this.setSize(parentFrame.getSize());
		
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
		ArrayList<Klijent> klijenti = new ArrayList<Klijent> (klijentifm.getKlijenti().values());
		table = new JTable(new KlijentTabelaModel(klijenti));	
		
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
				KlijentPopUp add = new KlijentPopUp(KlijentTabelaFrame.this, klijentifm, app, null);
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
					Klijent k = klijentifm.PronadjiKlijentaPoId(red);
					if(k != null) {
						
						KlijentPopUp edit = new KlijentPopUp(KlijentTabelaFrame.this, klijentifm, app, k);
						edit.setVisible(true);

					}else {
						JOptionPane.showMessageDialog(null, "Nije moguce pronaci odabranog klijenta!", "Greska", JOptionPane.ERROR_MESSAGE);
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
					Klijent k = klijentifm.PronadjiKlijentaPoId(red);
					if(k != null) {
						int izbor = JOptionPane.showConfirmDialog(null,"Da li ste sigurni da zelite da obrisete klijenta?", 
								k.getIme() + " "+k.getPrezime() +" - Potvrda brisanja", JOptionPane.YES_NO_OPTION);
						if(izbor == JOptionPane.YES_OPTION) {
							k.setObrisan(true);
							klijentifm.saveData();
							refreshData();
						}
					}else {
						JOptionPane.showMessageDialog(null, "Nije moguce pronaci odabranog klijenta!", "Greska", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
	}

	public void refreshData() {
		ArrayList<Klijent> klijenti = new ArrayList<Klijent> (klijentifm.getKlijenti().values());

		KlijentTabelaModel ktm = new KlijentTabelaModel(klijenti);
		
		table.setModel(ktm);	

	}

}

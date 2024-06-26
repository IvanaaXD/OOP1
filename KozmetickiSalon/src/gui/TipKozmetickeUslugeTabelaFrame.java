package gui;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.ListSelectionModel;

import fileMenager.KozmetickaUslugaFM;
import fileMenager.TipKozmetickeUslugeFM;
import meni.appSettings;
import model.KozmetickaUslugaTabelaModel;
import model.TipKozmetickeUslugeTabelaModel;
import net.miginfocom.swing.MigLayout;
import osobineTretmana.KozmetickaUsluga;
import osobineTretmana.TipKozmetickeUsluge;
import popup.KozmetickaUslugaPopUp;
import popup.TipKozmetickeUslugePopUp;

public class TipKozmetickeUslugeTabelaFrame extends JPanel{

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

	private TipKozmetickeUslugeFM tipUslugefm;

	public TipKozmetickeUslugeTabelaFrame(JFrame parentFrame, TipKozmetickeUslugeFM tipUslugefm, appSettings app) {
		this.tipUslugefm = tipUslugefm;
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

		ArrayList<TipKozmetickeUsluge> tipUsluge = new ArrayList<TipKozmetickeUsluge> (tipUslugefm.getTipKozmetickeUsluge().values());
		table = new JTable(new TipKozmetickeUslugeTabelaModel(tipUsluge));	
		
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
				TipKozmetickeUslugePopUp add = new TipKozmetickeUslugePopUp(TipKozmetickeUslugeTabelaFrame.this, tipUslugefm, app, null);
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
					TipKozmetickeUsluge k = tipUslugefm.PronadjiTipKozmetickeUslugePoId(red);
					if(k != null) {
						
						TipKozmetickeUslugePopUp add = new TipKozmetickeUslugePopUp(TipKozmetickeUslugeTabelaFrame.this, tipUslugefm, app, k);
						add.setVisible(true);

					}else {
						JOptionPane.showMessageDialog(null, "Nije moguce pronaci odabrani tip kozmeticke usluge!", "Greska", JOptionPane.ERROR_MESSAGE);
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
					TipKozmetickeUsluge k = tipUslugefm.PronadjiTipKozmetickeUslugePoId(red);
					System.out.println(k);
					System.out.println(red);
					if(k != null) {
						int izbor = JOptionPane.showConfirmDialog(null,"Da li ste sigurni da zelite da obrisete tip kozmeticke usluge?", 
								" - Potvrda brisanja", JOptionPane.YES_NO_OPTION);
						if(izbor == JOptionPane.YES_OPTION) {
							k.setObrisana(true);
							tipUslugefm.saveData();
							refreshData();
						}
					}else {
						JOptionPane.showMessageDialog(null, "Nije moguce pronaci odabrani tip kozmeticke usluge!", "Greska", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
	}

	public void refreshData() {
		ArrayList<TipKozmetickeUsluge> tipoviUsluge = new ArrayList<TipKozmetickeUsluge> (tipUslugefm.getTipKozmetickeUsluge().values());

		TipKozmetickeUslugeTabelaModel tkutm = new TipKozmetickeUslugeTabelaModel(tipoviUsluge);
		
		table.setModel(tkutm);	

	}

}
	

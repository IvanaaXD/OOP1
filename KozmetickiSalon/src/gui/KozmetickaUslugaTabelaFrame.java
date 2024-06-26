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

import meni.appSettings;

import model.KozmetickaUslugaTabelaModel;
import net.miginfocom.swing.MigLayout;
import osobineTretmana.KozmetickaUsluga;
import popup.KozmetickaUslugaPopUp;

public class KozmetickaUslugaTabelaFrame extends JPanel{

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

		private KozmetickaUslugaFM uslugefm;

		public KozmetickaUslugaTabelaFrame(JFrame parentFrame, KozmetickaUslugaFM uslugefm, appSettings app) {
			this.uslugefm = uslugefm;
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

			ArrayList<KozmetickaUsluga> usluge = new ArrayList<KozmetickaUsluga> (uslugefm.getKozmetickeUsluge().values());
			table = new JTable(new KozmetickaUslugaTabelaModel(usluge));	
			
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
					KozmetickaUslugaPopUp add = new KozmetickaUslugaPopUp(KozmetickaUslugaTabelaFrame.this, uslugefm, app, null);
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
						KozmetickaUsluga k = uslugefm.PronadjiKozmetickuUsluguPoId(red);
						if(k != null) {
							
							KozmetickaUslugaPopUp add = new KozmetickaUslugaPopUp(KozmetickaUslugaTabelaFrame.this, uslugefm, app, k);
							add.setVisible(true);
							refreshData();
						}else {
							JOptionPane.showMessageDialog(null, "Nije moguce pronaci odabranu kozmeticku uslugu!", "Greska", JOptionPane.ERROR_MESSAGE);
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
						KozmetickaUsluga k = uslugefm.PronadjiKozmetickuUsluguPoId(red);
						System.out.println(k);
						System.out.println(red);
						if(k != null) {
							int izbor = JOptionPane.showConfirmDialog(null,"Da li ste sigurni da zelite da obrisete kozmeticku uslugu?", 
									" - Potvrda brisanja", JOptionPane.YES_NO_OPTION);
							if(izbor == JOptionPane.YES_OPTION) {
								k.setObrisan(true);
								uslugefm.saveData();
								refreshData();
							}
						}else {
							JOptionPane.showMessageDialog(null, "Nije moguce pronaci odabranu kozmeticku uslugu!", "Greska", JOptionPane.ERROR_MESSAGE);
						}
					}
				}
			});
		}

		public void refreshData() {
			ArrayList<KozmetickaUsluga> usluge = new ArrayList<KozmetickaUsluga> (uslugefm.getKozmetickeUsluge().values());

			KozmetickaUslugaTabelaModel kutm = new KozmetickaUslugaTabelaModel(usluge);
			
			table.setModel(kutm);	

		}
	}

	

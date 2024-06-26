package gui;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.AbstractButton;
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
import fileMenager.KozmeticarFM;
import fileMenager.TipKozmetickeUslugeFM;
import fileMenager.ZakazanaKozmetickaUslugaFM;
import meni.appSettings;
import model.TipKozmetickeUslugeTabelaModel;
import model.ZakazaneKozmetickeUslugeTabelaModel;
import net.miginfocom.swing.MigLayout;
import osobineTretmana.TipKozmetickeUsluge;
import osobineTretmana.ZakazanaKozmetickaUsluga;
import popup.TipKozmetickeUslugePopUp;
import popup.ZakazanaKozmetickaUslugaChangeStatusPopUp;
import popup.ZakazanaKozmetickaUslugaMenadzerPopUp;
import popup.ZakazanaKozmetickaUslugaPopUp;

public class ZakazanaKozmetickaUslugaMenadzerTabelaFrame extends JPanel{

	private static final long serialVersionUID = 8456560429229699542L;

	//private StudentManager studentMng;
	
	protected JToolBar mainToolbar = new JToolBar();
	protected JButton btnAdd = new JButton();
	protected JButton btnEdit = new JButton();
	protected JButton btnDelete = new JButton();
	protected JTextField tfSearch = new JTextField(20);
	protected JButton btnChangeStatus = new JButton();
	protected JTable table;
	private appSettings app;
	//protected TableRowSorter<AbstractTableModel> tableSorter = new TableRowSorter<AbstractTableModel>();
	private JFrame parentFrame;

	private ZakazanaKozmetickaUslugaFM zakazaneUslugefm;
	private KozmeticarFM kozmeticarifm;

	private KlijentFM klijentifm;

	public ZakazanaKozmetickaUslugaMenadzerTabelaFrame(JFrame parentFrame, ZakazanaKozmetickaUslugaFM zakazaneUslugefm, KlijentFM klijentifm, KozmeticarFM kozmeticarifm, appSettings app) {
		this.zakazaneUslugefm = zakazaneUslugefm;
		this.parentFrame = parentFrame;
		this.kozmeticarifm = kozmeticarifm;
		this.klijentifm = klijentifm;
		this.app = app;

//		JButton btnAdd = new JButton();
//		JButton btnEdit = new JButton();
//		JButton btnDelete = new JButton();
//
		// Set the text for each button
		btnAdd.setText("Add");
		btnEdit.setText("Edit");
		btnChangeStatus.setText("Status");
		btnDelete.setText("Delete");
		
		setLayout(new MigLayout("wrap 3"));

		mainToolbar.add(btnAdd);
		mainToolbar.add(btnEdit);
		mainToolbar.add(btnChangeStatus);

		// Disable toolbar floating
		mainToolbar.setFloatable(false);	
		add(mainToolbar,  "dock north");
		
		// podesavanje tabele

		ArrayList<ZakazanaKozmetickaUsluga> zakazaneUsluge = new ArrayList<ZakazanaKozmetickaUsluga> (zakazaneUslugefm.getZakazaneKozmetickeUsluge().values());
		table = new JTable(new ZakazaneKozmetickeUslugeTabelaModel(zakazaneUsluge, klijentifm, kozmeticarifm));	
		
		Dimension parentSize = parentFrame.getContentPane().getSize();
		table.setPreferredScrollableViewportSize(parentSize);
		
		table.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		JScrollPane sc = new JScrollPane(table);
		add(sc, "dock center");

		initActions();
	}

	private void initActions() {
		btnAdd.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ZakazanaKozmetickaUslugaPopUp add = new ZakazanaKozmetickaUslugaPopUp(ZakazanaKozmetickaUslugaMenadzerTabelaFrame.this, zakazaneUslugefm, app, null);
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
					ZakazanaKozmetickaUsluga k = zakazaneUslugefm.PronadjiZakazanuKozmetickuUsluguPoId(red);
					if(k != null) {
						
						ZakazanaKozmetickaUslugaPopUp add = new ZakazanaKozmetickaUslugaPopUp(ZakazanaKozmetickaUslugaMenadzerTabelaFrame.this, zakazaneUslugefm, app, k);
						add.setVisible(true);
						refreshData();


					}else {
						JOptionPane.showMessageDialog(null, "Nije moguce pronaci odabrani zakazani tretman!", "Greska", JOptionPane.ERROR_MESSAGE);
					}
				}	

			}
		});
		
		btnChangeStatus.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int red = table.getSelectedRow();
				if(red == -1) {
					JOptionPane.showMessageDialog(null, "Morate odabrati red u tabeli.", "Greska", JOptionPane.WARNING_MESSAGE);
				}else {
					//int id = Integer.parseInt(table.getValueAt(red, 0).toString());
					ZakazanaKozmetickaUsluga k = zakazaneUslugefm.PronadjiZakazanuKozmetickuUsluguPoId(red);
					if(k != null) {
						
						ZakazanaKozmetickaUslugaChangeStatusPopUp add = new ZakazanaKozmetickaUslugaChangeStatusPopUp(ZakazanaKozmetickaUslugaMenadzerTabelaFrame.this, zakazaneUslugefm, app, k);
						add.setVisible(true);

					}else {
						JOptionPane.showMessageDialog(null, "Nije moguce pronaci odabrani zakazani tretman!", "Greska", JOptionPane.ERROR_MESSAGE);
					}
				}	

			}
		});
	}

	public void refreshData() {
		ArrayList<ZakazanaKozmetickaUsluga> zakazaneUsluge = new ArrayList<ZakazanaKozmetickaUsluga> (zakazaneUslugefm.getZakazaneKozmetickeUsluge().values());
		ZakazaneKozmetickeUslugeTabelaModel zkutm = new ZakazaneKozmetickeUslugeTabelaModel(zakazaneUsluge, klijentifm, kozmeticarifm);	
		table.setModel(zkutm);	

	}
	
}

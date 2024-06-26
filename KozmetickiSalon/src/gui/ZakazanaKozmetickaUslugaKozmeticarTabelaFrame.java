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

import fileMenager.KlijentFM;
import fileMenager.KozmeticarFM;
import fileMenager.ZakazanaKozmetickaUslugaFM;
import korisnici.Kozmeticar;
import meni.appSettings;
import model.ZakazaneKozmetickeUslugeKozmeticarTabelaModel;
import model.ZakazaneKozmetickeUslugeTabelaModel;
import net.miginfocom.swing.MigLayout;
import osobineTretmana.Status;
import osobineTretmana.ZakazanaKozmetickaUsluga;
import popup.ZakazanaKozmetickaUslugaMenadzerPopUp;
import popup.ZakazanaKozmetickaUslugaPopUp;

public class ZakazanaKozmetickaUslugaKozmeticarTabelaFrame extends JPanel{
	
	private static final long serialVersionUID = 8456560429229699542L;

	protected JToolBar mainToolbar = new JToolBar();
	protected JButton btnAdd = new JButton();
	protected JButton btnEdit = new JButton();
	protected JButton btnDelete = new JButton();
	protected JTextField tfSearch = new JTextField(20);
	protected JTable table;
	private appSettings app;
	//protected TableRowSorter<AbstractTableModel> tableSorter = new TableRowSorter<AbstractTableModel>();
	private JFrame parentFrame;

	private ZakazanaKozmetickaUslugaFM zakazaneUslugefm;
	private KozmeticarFM kozmeticarifm;

	private KlijentFM klijentifm;

	private Kozmeticar kozmeticar;
	
	private ArrayList<ZakazanaKozmetickaUsluga> zakazaneUslugeZaKozmeticara = new ArrayList<ZakazanaKozmetickaUsluga>();


	public ZakazanaKozmetickaUslugaKozmeticarTabelaFrame  (JFrame parentFrame, ZakazanaKozmetickaUslugaFM zakazaneUslugefm, KlijentFM klijentifm, KozmeticarFM kozmeticarifm, Kozmeticar kozmeticar,appSettings app) {
		this.zakazaneUslugefm = zakazaneUslugefm;
		this.parentFrame = parentFrame;
		this.kozmeticarifm = kozmeticarifm;
		this.klijentifm = klijentifm;
		this.app = app;
		this.kozmeticar = kozmeticar;

		btnAdd.setText("Add");
		btnEdit.setText("Edit");
		btnDelete.setText("Delete");
		
		setLayout(new MigLayout("wrap 3"));

		mainToolbar.add(btnEdit);

		// Disable toolbar floating
		mainToolbar.setFloatable(false);	
		add(mainToolbar,  "dock north");
		
		// podesavanje tabele

		ArrayList<ZakazanaKozmetickaUsluga> zakazaneUsluge = new ArrayList<ZakazanaKozmetickaUsluga> (zakazaneUslugefm.getZakazaneKozmetickeUsluge().values());
		
		int id = kozmeticar.getId();
		for (int i = 0; i < zakazaneUsluge.size(); i++) {
			int idd = zakazaneUsluge.get(i).getIdKozmeticar();
			if (idd == id) {
				zakazaneUslugeZaKozmeticara.add(zakazaneUsluge.get(i));
			}
		}
		
		table = new JTable(new ZakazaneKozmetickeUslugeKozmeticarTabelaModel(zakazaneUslugeZaKozmeticara, klijentifm, kozmeticarifm));	
		
		Dimension parentSize = parentFrame.getContentPane().getSize();
		table.setPreferredScrollableViewportSize(parentSize);
		
		table.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.getTableHeader().setReorderingAllowed(false);

		JScrollPane sc = new JScrollPane(table);
		add(sc, "dock center");

		initActions();
	}

	private void initActions() {
//		btnAdd.addActionListener(new ActionListener() {
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				ZakazanaKozmetickaUslugaPopUp add = new ZakazanaKozmetickaUslugaPopUp(ZakazanaKozmetickaUslugaTabelaFrame.this, zakazaneUslugefm, app, null);
//				add.setVisible(true);
//				refreshData();
//			}
//		});
		
		btnEdit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int red = table.getSelectedRow();
				if(red == -1) {
					JOptionPane.showMessageDialog(null, "Morate odabrati red u tabeli.", "Greska", JOptionPane.WARNING_MESSAGE);
				}else {
					//int id = Integer.parseInt(table.getValueAt(red, 0).toString());
					ZakazanaKozmetickaUsluga k = zakazaneUslugeZaKozmeticara.get(red);
					if(k != null) {
						
						if (k.getStanje().equals(Status.ZAKAZAN)) {
							ZakazanaKozmetickaUslugaMenadzerPopUp add = new ZakazanaKozmetickaUslugaMenadzerPopUp(ZakazanaKozmetickaUslugaKozmeticarTabelaFrame.this, zakazaneUslugefm, app, k);
							add.setVisible(true);
						} else {
							JOptionPane.showMessageDialog(null, "Nije moguce mijenjati stanje ovog tretmana tretman!", "Greska", JOptionPane.ERROR_MESSAGE);
						}
						

					}else {
						JOptionPane.showMessageDialog(null, "Nije moguce pronaci odabrani zakazani tretman!", "Greska", JOptionPane.ERROR_MESSAGE);
					}
				}	

			}
		});
	}

	public void refreshData() {
		
		ArrayList<ZakazanaKozmetickaUsluga> zakazaneUsluge = new ArrayList<ZakazanaKozmetickaUsluga> (zakazaneUslugefm.getZakazaneKozmetickeUsluge().values());
		zakazaneUslugeZaKozmeticara.clear();
		
		int id = kozmeticar.getId();
		for (int i = 0; i < zakazaneUsluge.size(); i++) {
			int idd = zakazaneUsluge.get(i).getIdKozmeticar();
			if (idd == id) {
				zakazaneUslugeZaKozmeticara.add(zakazaneUsluge.get(i));
			}
		}

		ZakazaneKozmetickeUslugeKozmeticarTabelaModel zkutm = new ZakazaneKozmetickeUslugeKozmeticarTabelaModel(zakazaneUslugeZaKozmeticara, klijentifm, kozmeticarifm);	
		
		table.setModel(zkutm);	

	}


}

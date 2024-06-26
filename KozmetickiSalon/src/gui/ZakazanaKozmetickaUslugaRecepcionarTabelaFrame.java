package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.ListSelectionModel;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import fileMenager.KlijentFM;
import fileMenager.KozmeticarFM;
import fileMenager.ZakazanaKozmetickaUslugaFM;
import meni.appSettings;
import model.ZakazaneKozmetickeUslugeTabelaModel;
import net.miginfocom.swing.MigLayout;
import osobineTretmana.KozmetickaUsluga;
import osobineTretmana.TipKozmetickeUsluge;
import osobineTretmana.ZakazanaKozmetickaUsluga;
import popup.ZakazanaKozmetickaUslugaChangeStatusPopUp;
import popup.ZakazanaKozmetickaUslugaPopUp;

public class ZakazanaKozmetickaUslugaRecepcionarTabelaFrame extends JPanel{
	

	private static final long serialVersionUID = 8456560429229699542L;

	//private StudentManager studentMng;
	
	protected JToolBar mainToolbar1 = new JToolBar();
	protected JToolBar mainToolbar2 = new JToolBar();
	protected JButton btnAdd = new JButton();
	protected JButton btnEdit = new JButton();
	protected JButton btnDelete = new JButton();
	protected JButton btnDeleteAll = new JButton();
	protected JButton btnChangeStatus = new JButton();

	protected JTextField tfSearch = new JTextField(20);
	protected JTable table;
	private appSettings app;
	//protected TableRowSorter<AbstractTableModel> tableSorter = new TableRowSorter<AbstractTableModel>();
	private JFrame parentFrame;

	private ZakazanaKozmetickaUslugaFM zakazaneUslugefm;
	private KozmeticarFM kozmeticarifm;

	private KlijentFM klijentifm;
	
	private ArrayList<KozmetickaUsluga> kUsluge = new ArrayList<KozmetickaUsluga>();
	private ArrayList<TipKozmetickeUsluge> tUsluge = new ArrayList<TipKozmetickeUsluge>();
	private ArrayList<ZakazanaKozmetickaUsluga> zakazaneUslugeNasla = new ArrayList<ZakazanaKozmetickaUsluga> ();
	private ArrayList<ZakazanaKozmetickaUsluga> zakazaneUsluge = new ArrayList<ZakazanaKozmetickaUsluga> ();

	
	public ZakazanaKozmetickaUslugaRecepcionarTabelaFrame(JFrame parentFrame, ZakazanaKozmetickaUslugaFM zakazaneUslugefm, KlijentFM klijentifm, KozmeticarFM kozmeticarifm, appSettings app) {
		this.zakazaneUslugefm = zakazaneUslugefm;
		this.parentFrame = parentFrame;
		this.kozmeticarifm = kozmeticarifm;
		this.klijentifm = klijentifm;
		this.app = app;
		
		zakazaneUsluge.addAll(app.getzkufm().getZakazaneKozmetickeUsluge().values());
		
		btnAdd.setText("Add");
		btnEdit.setText("Edit");
		btnChangeStatus.setText("Status");
		btnDelete.setText("Delete");
		btnDeleteAll.setText("Delete all");
		
		setLayout(new MigLayout("wrap 3"));

		mainToolbar1.add(btnAdd);
		mainToolbar1.add(btnEdit);
		mainToolbar1.add(btnChangeStatus);
		mainToolbar1.add(btnDelete);
		mainToolbar1.add(btnDeleteAll);

		// Disable toolbar floating
		mainToolbar1.setFloatable(false);	
		add(mainToolbar1,  "dock north");
		
		JLabel comboBoxLabel1 = new JLabel("Tretmani: ");
		JComboBox<String> comboBox1 = new JComboBox<>();
		comboBox1.addItem("");
		
		kUsluge.addAll(app.getkufm().getKozmetickeUsluge().values());
		for (KozmetickaUsluga ku: kUsluge) {
			comboBox1.addItem(ku.getNazivUsluge());
		}

		JLabel comboBoxLabel2 = new JLabel("Tipovi tretmana: ");
		JComboBox<String> comboBox2 = new JComboBox<>();
		comboBox2.addItem("");
		
		tUsluge.addAll(app.gettkufm().getTipKozmetickeUsluge().values());
		for (TipKozmetickeUsluge tku: tUsluge) {
			comboBox2.addItem(tku.getTipUsluge());
		}

		JLabel spinnerLabel1 = new JLabel("Cijena od: ");
		SpinnerModel spinnerModel1 = new SpinnerNumberModel(0, 0, 10000, 1);
		JSpinner spinner1 = new JSpinner(spinnerModel1);

		JLabel spinnerLabel2 = new JLabel("do: ");
		SpinnerModel spinnerModel2 = new SpinnerNumberModel(0, 0, 10000, 1);
		JSpinner spinner2 = new JSpinner(spinnerModel2);

		// Add the components to mainToolbar2
		mainToolbar2.add(comboBoxLabel1);
		mainToolbar2.add(comboBox1);
		mainToolbar2.add(comboBoxLabel2);
		mainToolbar2.add(comboBox2);
		mainToolbar2.add(spinnerLabel1);
		mainToolbar2.add(spinner1);
		mainToolbar2.add(spinnerLabel2);
		mainToolbar2.add(spinner2);

		// Set the layout and other properties of mainToolbar2
		mainToolbar2.setLayout(new FlowLayout(FlowLayout.LEFT));
		mainToolbar2.setFloatable(false);

		
		// podesavanje tabele

		zakazaneUslugeNasla.addAll(zakazaneUslugefm.getZakazaneKozmetickeUsluge().values());
		table = new JTable(new ZakazaneKozmetickeUslugeTabelaModel(zakazaneUslugeNasla, klijentifm, kozmeticarifm));	
		
		Dimension parentSize = parentFrame.getContentPane().getSize();
		table.setPreferredScrollableViewportSize(parentSize);
		
		table.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.getTableHeader().setReorderingAllowed(false);

	    JScrollPane scrollPane = new JScrollPane(table);

	    //add(scrollPane);
	    
	    setLayout(new BorderLayout());

	    JPanel toolbarsContainer = new JPanel();
	    toolbarsContainer.setLayout(new BoxLayout(toolbarsContainer, BoxLayout.Y_AXIS));

	    toolbarsContainer.add(mainToolbar1);
	    toolbarsContainer.add(mainToolbar2);

	    add(toolbarsContainer, BorderLayout.NORTH);
	    add(scrollPane, BorderLayout.CENTER);

	    setVisible(true);

	    initActions();
	    
		comboBox1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				String naziv = (String) comboBox1.getSelectedItem();
				
				int wuhu = 0;
				ArrayList<String> listaTipova = new ArrayList<String>();

				if (naziv.equals("")) {
					wuhu = 1;
					
				} else {
					for (TipKozmetickeUsluge tku: tUsluge) {
						if (tku.getNazivUsluge().equals(naziv)) {
							if (!listaTipova.contains(tku.getTipUsluge()))
								listaTipova.add(tku.getTipUsluge());
						}
					}
				}
				
				if (wuhu != 1) {
					zakazaneUslugeNasla.clear();
					comboBox2.removeAllItems();
					comboBox2.addItem("");
					
					for (String tip : listaTipova) {
						comboBox2.addItem(tip);

						for (ZakazanaKozmetickaUsluga zku: zakazaneUsluge) {

							if (zku.getTipUsluge().equals(tip)) {
								if (!zakazaneUslugeNasla.contains(zku)) {
									zakazaneUslugeNasla.add(zku);

								}
							}
						}
					}
					
					ZakazaneKozmetickeUslugeTabelaModel model = new ZakazaneKozmetickeUslugeTabelaModel(zakazaneUslugeNasla, klijentifm, kozmeticarifm);
					table.setModel(model);
					
				} else {
					
					comboBox2.removeAllItems();
					comboBox2.addItem("");
					for (TipKozmetickeUsluge tku: tUsluge) {
						comboBox2.addItem(tku.getTipUsluge());
					}
					
					ZakazaneKozmetickeUslugeTabelaModel model = new ZakazaneKozmetickeUslugeTabelaModel(zakazaneUsluge, klijentifm, kozmeticarifm);
					table.setModel(model);
				}
				
			}
		});
	    
		comboBox2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				String tipp = (String) comboBox2.getSelectedItem();
				String naziv = (String) comboBox1.getSelectedItem();
				
				int wuhu = 0;
				ArrayList<String> listaTipova = new ArrayList<String>();

				if (naziv.equals("")) {
					wuhu = 1;

				} else {
					for (TipKozmetickeUsluge tku: tUsluge) {
						
						if (tipp == null || tipp.equals("")) {
							if (tku.getNazivUsluge().equals(naziv)) {
								if (!listaTipova.contains(tku.getTipUsluge()))
									listaTipova.add(tku.getTipUsluge());
							}
						} else {
							if (tku.getTipUsluge().equals(tipp)) {
								if (!listaTipova.contains(tku.getTipUsluge()))
									listaTipova.add(tku.getTipUsluge());
							}
						}
					}
				}
				
				zakazaneUslugeNasla.clear();

					
				for (String tipic : listaTipova) {

					for (ZakazanaKozmetickaUsluga zku: zakazaneUsluge) {

						if (zku.getTipUsluge().equals(tipic)) {
							zakazaneUslugeNasla.add(zku);
						}
					}
				}
				
				ZakazaneKozmetickeUslugeTabelaModel model = new ZakazaneKozmetickeUslugeTabelaModel(zakazaneUslugeNasla, klijentifm, kozmeticarifm);
				table.setModel(model);
		
			}
		});
		
		spinner1.addChangeListener(new ChangeListener() {
		    @Override
		    public void stateChanged(ChangeEvent e) {
		    	double selectedValue1 = ((Number) spinner1.getValue()).doubleValue();
		    	double selectedValue2 = ((Number) spinner2.getValue()).doubleValue();

		        ArrayList<ZakazanaKozmetickaUsluga> uslugicee = new ArrayList<ZakazanaKozmetickaUsluga>();
		        for (ZakazanaKozmetickaUsluga zku: zakazaneUslugeNasla) {
		        	double cijena = zku.getCijena();
		        	if (selectedValue1 <= cijena && cijena <= selectedValue2) {
		        		uslugicee.add(zku);
		        	}	
		        }
		        
				ZakazaneKozmetickeUslugeTabelaModel model = new ZakazaneKozmetickeUslugeTabelaModel(uslugicee, klijentifm, kozmeticarifm);
				table.setModel(model);
		    }
		});
		
		spinner2.addChangeListener(new ChangeListener() {
		    @Override
		    public void stateChanged(ChangeEvent e) {
		    	double selectedValue1 = ((Number) spinner1.getValue()).doubleValue();
		    	double selectedValue2 = ((Number) spinner2.getValue()).doubleValue();

		        ArrayList<ZakazanaKozmetickaUsluga> uslugicee = new ArrayList<ZakazanaKozmetickaUsluga>();
		        for (ZakazanaKozmetickaUsluga zku: zakazaneUslugeNasla) {
		        	double cijena = zku.getCijena();
		        	if (selectedValue1 <= cijena && cijena <= selectedValue2) {
		        		uslugicee.add(zku);
		        	}	
		        }
		        
				ZakazaneKozmetickeUslugeTabelaModel model = new ZakazaneKozmetickeUslugeTabelaModel(uslugicee, klijentifm, kozmeticarifm);
				table.setModel(model);
		    }
		});

	}

	private void initActions() {
		btnAdd.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ZakazanaKozmetickaUslugaPopUp add = new ZakazanaKozmetickaUslugaPopUp(ZakazanaKozmetickaUslugaRecepcionarTabelaFrame.this, zakazaneUslugefm, app, null);
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
						
						ZakazanaKozmetickaUslugaPopUp add = new ZakazanaKozmetickaUslugaPopUp(ZakazanaKozmetickaUslugaRecepcionarTabelaFrame.this, zakazaneUslugefm, app, k);
						add.setVisible(true);

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
						
						ZakazanaKozmetickaUslugaChangeStatusPopUp add = new ZakazanaKozmetickaUslugaChangeStatusPopUp(ZakazanaKozmetickaUslugaRecepcionarTabelaFrame.this, zakazaneUslugefm, app, k);
						add.setVisible(true);

					}else {
						JOptionPane.showMessageDialog(null, "Nije moguce pronaci odabrani zakazani tretman!", "Greska", JOptionPane.ERROR_MESSAGE);
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
					ZakazanaKozmetickaUsluga k = zakazaneUslugefm.PronadjiZakazanuKozmetickuUsluguPoId(red);
					if(k != null) {
						int izbor = JOptionPane.showConfirmDialog(null,"Da li ste sigurni da zelite da obrisete zakazani tretman?", 
								" - Potvrda brisanja", JOptionPane.YES_NO_OPTION);
						if(izbor == JOptionPane.YES_OPTION) {
							app.changeZakazanaKozmetickaUslugaStatus(red, "OTKAZAO_SALON");
							//k.setStanje(Status.OTKAZAO_SALON);
							zakazaneUslugefm.saveData();
							refreshData();
						}
					}else {
						JOptionPane.showMessageDialog(null, "Nije moguce pronaci odabrani zakazani tretman!", "Greska", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
		
		btnDeleteAll.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

						int izbor = JOptionPane.showConfirmDialog(null,"Da li ste sigurni da zelite da otkazete sve zakazane tretmane?", 
								" - Potvrda brisanja", JOptionPane.YES_NO_OPTION);
						if(izbor == JOptionPane.YES_OPTION) {
							app.changeZakazanaKozmetickaUslugaStatusAll();
							//k.setStanje(Status.OTKAZAO_SALON);
							zakazaneUslugefm.saveData();
							refreshData();
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

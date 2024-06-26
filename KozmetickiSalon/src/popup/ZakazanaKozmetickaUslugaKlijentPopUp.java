package popup;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.jdatepicker.JDatePicker;
import org.jdatepicker.UtilDateModel;

import fileMenager.ZakazanaKozmetickaUslugaFM;
import gui.ZakazanaKozmetickaUslugaKlijentTabelaFrame;
import gui.ZakazanaKozmetickaUslugaRecepcionarTabelaFrame;
import korisnici.Klijent;
import korisnici.Kozmeticar;
import meni.appSettings;
import net.miginfocom.swing.MigLayout;
import osobineTretmana.KozmetickaUsluga;
import osobineTretmana.TipKozmetickeUsluge;
import osobineTretmana.ZakazanaKozmetickaUsluga;

public class ZakazanaKozmetickaUslugaKlijentPopUp extends JDialog{

	private static final long serialVersionUID = -4068540389806942594L;
	
	private ZakazanaKozmetickaUslugaFM zakazaneKozmetickeUsluge;
	private ZakazanaKozmetickaUsluga editK;
	private JPanel parent;
	private appSettings app;
    private String stariTip;

	private Klijent klijent;
	
	// Jedan isti dijalog za Add i Edit
	
	public ZakazanaKozmetickaUslugaKlijentPopUp(JPanel parent, ZakazanaKozmetickaUslugaFM zakazaneKozmetickeUsluge, appSettings app, Klijent klijent ,ZakazanaKozmetickaUsluga editUsluga) {

		if (editUsluga != null) {
			setTitle("Izmjena zakazanog tretmana");
		} else {
			setTitle("Dodavanje zakazanog tretmana");
		}
		
		this.parent = parent;
		this.zakazaneKozmetickeUsluge = zakazaneKozmetickeUsluge;
		this.app = app;
		this.editK = editUsluga;
		this.klijent = klijent;

		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setResizable(false);
		initGUI(app);

		pack();
		this.setLocationRelativeTo(null);

	}

	private void initGUI(appSettings app) {
		MigLayout ml = new MigLayout("wrap 3", "[][][]", "[]10[]10[]10[]20[]");
		setLayout(ml);
		this.setLocationRelativeTo(null);
					
//boolean obrisan, String tip usluge, String trajanje, String naziv usluge, double cijena	     
		
		JLabel lblNaziv = new JLabel("Naziv usluge");
		add(lblNaziv);
        
		String nazivi = "";
		nazivi = app.addKozmetickaUslugaUKozmeticara(); 
		
		String[] naziviArray = nazivi.split(",");
		
		JComboBox<String> dropdownNazivi = new JComboBox<>(naziviArray);
		dropdownNazivi.addItem("---");
		add(dropdownNazivi, "span 2");
		
		String naziv = (String) dropdownNazivi.getSelectedItem();
		
		JLabel lblTip = new JLabel("Tip usluge");
		add(lblTip);
		
		String tipovi = "";
		tipovi = app.addTipKozmetickeUslugeUKozmeticaraGui(naziv);
		
		String[] tipoviArray = tipovi.split(",");
		
		JComboBox<String> dropdownTipovi = new JComboBox<>(tipoviArray);
		add(dropdownTipovi, "span 2");
		
		
		JLabel lblKozmeticar = new JLabel("Kozmeticar");
		add(lblKozmeticar);
		
		KozmetickaUsluga usluga = app.getkufm().PronadjiKozmetickuUsluguPoNazivu(naziv);
		int indeksic = usluga.getId();
		//System.out.println(indeksic);
		
		int brojKozmeticara = app.getkkfm().getKozmeticar().values().size();

		String[] kozmeticarArray = new String[brojKozmeticara];
		int kkk = 0;

		ArrayList<String> kozmeticarList = new ArrayList<>(Arrays.asList(kozmeticarArray));
		for (int i = 0; i < brojKozmeticara; i++) {
		    Kozmeticar k = app.getkkfm().getKozmeticar().get((i));
    		String kIme = k.getKorisnickoIme();
 
    		if (!k.getObrisan()) {
    		    ArrayList<Integer> listaKozmeticar = k.getTretmani();
    		    //System.out.println(listaKozmeticar);

    		    for (int j = 0; j < listaKozmeticar.size(); j++) {
    		    	if (listaKozmeticar.get(j) == indeksic) {
    		    		//System.out.println(listaKozmeticar.get(j));

    		    		//System.out.println();
    		    		boolean found = kozmeticarList.contains(kIme);

    	    			kozmeticarArray[kkk] = kIme;
    	    			kkk += 1;
    		    	}
    		    }
    		}

		}
				
		JComboBox<String> dropdownKozmeticari = new JComboBox<>(kozmeticarArray);
		add(dropdownKozmeticari, "span 2");
		
		JLabel lblDatum = new JLabel("Datum");
		add(lblDatum);
		
		UtilDateModel dateModel = new UtilDateModel();
		JDatePicker datePicker = new JDatePicker(dateModel);
		
        LocalDate today = LocalDate.now();
        Date todayDate = Date.from(today.atStartOfDay(ZoneId.systemDefault()).toInstant());
        dateModel.setValue(todayDate);		
		datePicker.setShowYearButtons(true);
 
		add(datePicker);
		add(new JLabel());

		
		JLabel lblVrijeme = new JLabel("Vrijeme");
		add(lblVrijeme);
		
		ArrayList<LocalTime> vrijeme = new ArrayList<LocalTime>();
		Date datum = (Date)datePicker.getModel().getValue();
		JComboBox<LocalTime> dropdownVrijeme = new JComboBox<>();
		
		String selectedK = (String)dropdownKozmeticari.getSelectedItem();
		Kozmeticar kkkkkk = app.getkkfm().PronadjiKozmeticaraPoKorisnickomImenu(selectedK);
		int kozm = kkkkkk.getId();
		
		if (datum != null) {
			LocalDate datumLocalDate = datum.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
			vrijeme = app.setRadnoVrijemeKozmeticara(kozm,datumLocalDate);
			
			for (LocalTime time : vrijeme) {
			    dropdownVrijeme.addItem(time);
			}
		}
		
		add(dropdownVrijeme);
		
		add(new JLabel());
		
		String tip = (String) dropdownTipovi.getSelectedItem();
		JLabel lblTrajanje = new JLabel("Trajanje");
		add(lblTrajanje);
		
		TipKozmetickeUsluge ttt = app.gettkufm().PronadjiTipKozmetickeUslugePoTipu(tip);
		
		String trajanja = "";
		trajanja = app.addTipKozmetickeUslugeUTrajanjeGui(naziv);
		
		String[] trajanjaArray = trajanja.split(",");
		
		JComboBox<String> dropdownTrajanja = new JComboBox<>(trajanjaArray);
		add(dropdownTrajanja, "span 2");
		dropdownTrajanja.setSelectedItem(ttt.getTrajanje());
       
		JLabel lblCijena = new JLabel("Cijena usluge");
		add(lblCijena);

		ArrayList<Double >cijene = app.addTipKozmetickeUslugeUCijeneGui(naziv);
				
		JComboBox<Double> dropdownCijene = new JComboBox<>();
		
		for (Double d: cijene) {
			dropdownCijene.addItem(d);
		}
		add(dropdownCijene, "span 2");
		dropdownCijene.setSelectedItem(ttt.getCijena());
        
		
		dropdownNazivi.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		    	
		        dropdownTipovi.removeAllItems(); 
				dropdownKozmeticari.removeAllItems();
				dropdownTrajanja.removeAllItems();
				dropdownCijene.removeAllItems();
				
		        String selectedNaziv = (String) dropdownNazivi.getSelectedItem();
				
		    	if(dropdownNazivi.getSelectedItem() == null) {
		    		
		    	} else {
		    		
			        String tipovi = app.addTipKozmetickeUslugeUKozmeticaraGui(selectedNaziv);
			        String[] tipoviArray = tipovi.split(",");

			        for (String tip : tipoviArray) {
			            dropdownTipovi.addItem(tip); 
			        }
			        
					String trajanja = "";
					trajanja = app.addTipKozmetickeUslugeUTrajanjeGui(selectedNaziv);
					System.out.println(trajanja);
					String[] trajanjaArray = trajanja.split(",");
					
					for(String tipic: trajanjaArray) {
						dropdownTrajanja.addItem(tipic);
					}

					ArrayList<Double >cijene = app.addTipKozmetickeUslugeUCijeneGui(selectedNaziv);
										
					for (Double d: cijene) {
						dropdownCijene.addItem(d);
					}
					
				    KozmetickaUsluga usluga = app.getkufm().PronadjiKozmetickuUsluguPoNazivu(naziv);
				    int indeksic = usluga.getId();
					
					String[] kozmeticarArray = new String[brojKozmeticara];
					int kkk = 0;
	
					ArrayList<String> kozmeticarList = new ArrayList<>(Arrays.asList(kozmeticarArray));
					for (int i = 0; i < brojKozmeticara; i++) {
					    Kozmeticar k = app.getkkfm().getKozmeticar().get((i));
			    		String kIme = k.getKorisnickoIme();
			 
	
					    ArrayList<Integer> listaKozmeticar = k.getTretmani();
	
					    for (int j = 0; j < listaKozmeticar.size(); j++) {
					    	if (listaKozmeticar.get(j) == indeksic) {
	
					    		boolean found = kozmeticarList.contains(kIme);
	
					    		//if (found) {
					    			
					    		//} else {
					    			kozmeticarArray[kkk] = kIme;
					    			kkk += 1;
					    		//}
					    	}
					    }
					}
					
			        for (String kIme : kozmeticarArray) {
			            dropdownKozmeticari.addItem(kIme); // Add new items
			        }
		        
		    }}
		});
		
		dropdownKozmeticari.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		    	
		    	if (dropdownKozmeticari.getSelectedItem() == null)
		    	{
		    		
		    	}
		    	else
		    	{
		    		dropdownVrijeme.removeAllItems();
			    	
					ArrayList<LocalTime> vrijemeee = new ArrayList<LocalTime>();
					Date datummm = (Date)datePicker.getModel().getValue();
										
					String selectedK = (String)dropdownKozmeticari.getSelectedItem();
					Kozmeticar kkkkkk = app.getkkfm().PronadjiKozmeticaraPoKorisnickomImenu(selectedK);
					int kozm = kkkkkk.getId();
										
					if (datum != null) {
						LocalDate datummmLocalDate = datummm.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
						vrijemeee = app.setRadnoVrijemeKozmeticara(kozm,datummmLocalDate);

						for (LocalTime time : vrijemeee) {
						    dropdownVrijeme.addItem(time);
						}
					}}
		    	}

		    	
		});
	
		dropdownTipovi.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		    	
		    	if (dropdownTipovi.getSelectedItem() == null) {
		    		
		    	} else {
		        	
			        String tipp = (String)dropdownTipovi.getSelectedItem();
					TipKozmetickeUsluge ttt = app.gettkufm().PronadjiTipKozmetickeUslugePoTipu(tipp);

					dropdownTrajanja.setSelectedItem(ttt.getTrajanje());
					dropdownCijene.setSelectedItem(ttt.getCijena());
		    	}
		    	
		    }
		});
		
		datePicker.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		    	if (dropdownKozmeticari.getSelectedItem() == null)
		    	{
		    		
		    	}
		    	else
		    	{
		    		dropdownVrijeme.removeAllItems();
			    	
					ArrayList<LocalTime> vrijemeee = new ArrayList<LocalTime>();
					Date datummm = (Date)datePicker.getModel().getValue();
										
					String selectedK = (String)dropdownKozmeticari.getSelectedItem();
					Kozmeticar kkkkkk = app.getkkfm().PronadjiKozmeticaraPoKorisnickomImenu(selectedK);
					int kozm = kkkkkk.getId();
										
					if (datum != null) {
						LocalDate datummmLocalDate = datummm.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
						vrijemeee = app.setRadnoVrijemeKozmeticara(kozm,datummmLocalDate);
						for (LocalTime time : vrijemeee) {
						    dropdownVrijeme.addItem(time);
						}
					}}
		    	
		    }
		});
		
		dropdownTrajanja.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		    									
				String trajanje = (String)dropdownTrajanja.getSelectedItem();
				
		    	if (dropdownTrajanja.getSelectedItem() == null)
		    	{
		    		
		    	} else {
		    		
		    		ArrayList<TipKozmetickeUsluge> juhu = new ArrayList<TipKozmetickeUsluge>(app.addTipKozmetickeUslugeUTipGuii(trajanje));
		    		for (TipKozmetickeUsluge tku: juhu) {
		    			if (tku.getTrajanje().equals(trajanje))
		    				dropdownTipovi.setSelectedItem(tku.getTipUsluge());
		    		}
		    	}   	
		    }
		});
		
		dropdownCijene.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
								
				Double cijena = (Double)dropdownCijene.getSelectedItem();
				
		    	if (dropdownCijene.getSelectedItem() == null) {
		    	} else
		    	{
		    		
		    		ArrayList<TipKozmetickeUsluge> juhu = new ArrayList<TipKozmetickeUsluge>(app.addTipKozmetickeUslugeUCijenaGui(cijena));
		    		for (TipKozmetickeUsluge tku: juhu) {
		    			if (tku.getCijena() == cijena)
		    				dropdownTipovi.setSelectedItem(tku.getTipUsluge());
		    		}
			    
		    	}
		    	
		    }
		});
		
		
		add(new JLabel());

		JButton btnCancel = new JButton("Cancel");
		add(btnCancel);

		JButton btnOK = new JButton("OK");
		add(btnOK);
		
		ZakazanaKozmetickaUslugaKlijentPopUp.this.getRootPane().setDefaultButton(btnOK);


		if (editK != null) {
			// popuni polja vrednostima
//			String nazz = editK.getNazivUsluge();
//			txtNaziv.setText();

			//txtTip.setText();
			String tipic = editK.getTipUsluge();
			dropdownTipovi.setSelectedItem(tipic);
			
			String trajanje = editK.getTrajanje();
			dropdownTrajanja.setSelectedItem(trajanje);
			
			Double cijenaa = editK.getCijena();
			dropdownCijene.setSelectedItem(cijenaa);
			
			LocalDate datumic = editK.getDatum();
			Date dateic = Date.from(datumic.atStartOfDay(ZoneId.systemDefault()).toInstant()); 
	        dateModel.setValue(dateic);	

	        int idKozmeticar = editK.getIdKozmeticar();
	        
	        ArrayList<Kozmeticar> kozmeticari = new ArrayList<Kozmeticar>(app.getkkfm().getKozmeticar().values());
	        
	        String selectedKk = "";
	        for (Kozmeticar kk : kozmeticari) {
	        	if (kk.getId() == idKozmeticar) {
	    	        dropdownKozmeticari.setSelectedItem(kk.getKorisnickoIme());
	    	        selectedKk = kk.getKorisnickoIme();

	        	}
	        }
	    
			
			if (datum != null) {
				LocalDate datumLocalDate = datum.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
				vrijeme = app.setRadnoVrijemeKozmeticaraEdit(idKozmeticar,datumLocalDate, editK.getId());
				
				for (LocalTime time : vrijeme) {
				    dropdownVrijeme.addItem(time);
				}	
			}
			
	        LocalTime timeic = editK.getVrijeme();
	        dropdownVrijeme.setSelectedItem(editK.getVrijeme());
	        

		}

		btnOK.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
		        String tippp = (String) dropdownTipovi.getSelectedItem();
;
				String trajanje = (String) dropdownTrajanja.getSelectedItem();
				Double cijena = (Double)dropdownCijene.getSelectedItem();

				Date selectedDate = (Date) dateModel.getValue();
				
				LocalTime timeSelected = (LocalTime)dropdownVrijeme.getSelectedItem();
				String timeString = timeSelected.toString();
				
				String dateString = "";
				if (selectedDate != null) {
				    SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
				    dateString = dateFormat.format(selectedDate);
				}
				
				String korisnickoImeKozmeticar = (String) dropdownKozmeticari.getSelectedItem();

				// odve se odvaja GUI od funkcionalnosti Manager-a
				// ne mesati logiku app i funkcionalnosti sa GUI-om !
				if (editK != null) {
					stariTip = editK.getTipUsluge();
					int id = editK.getId();
	
							
					app.changeZakazanaKozmetickaUslugaTypeOfService(id, tippp);
					app.changeZakazanaKozmetickaUslugaDuration(id, trajanje);
					app.changeZakazanaKozmetickaUslugaPrice(id, cijena);
					app.changeZakazanaKozmetickaUslugaDate(id, dateString);
					app.changeZakazanaKozmetickaUslugaTime(id, timeString);
					
					
				} else {
					app.addZakazanaKozmetickaUsluga("ZAKAZAN", dateString, timeString, tippp, klijent.getKorisnickoIme(), korisnickoImeKozmeticar);
				}
				((ZakazanaKozmetickaUslugaKlijentTabelaFrame)parent).refreshData();

				ZakazanaKozmetickaUslugaKlijentPopUp.this.dispose();

			}
			
		});

		btnCancel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				ZakazanaKozmetickaUslugaKlijentPopUp.this.dispose();
			}
		});
	}

}

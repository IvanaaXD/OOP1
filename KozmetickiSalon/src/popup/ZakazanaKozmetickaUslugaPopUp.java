package popup;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Properties;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.jdatepicker.JDatePanel;
import org.jdatepicker.JDatePicker;
import org.jdatepicker.UtilDateModel;
import org.jdatepicker.constraints.DateSelectionConstraint;

import fileMenager.TipKozmetickeUslugeFM;
import fileMenager.ZakazanaKozmetickaUslugaFM;
import gui.TipKozmetickeUslugeTabelaFrame;
import gui.ZakazanaKozmetickaUslugaMenadzerTabelaFrame;
import gui.ZakazanaKozmetickaUslugaRecepcionarTabelaFrame;
import korisnici.Klijent;
import korisnici.Kozmeticar;
import meni.appSettings;
import net.miginfocom.swing.MigLayout;
import osobineTretmana.KozmetickaUsluga;
import osobineTretmana.TipKozmetickeUsluge;
import osobineTretmana.ZakazanaKozmetickaUsluga;

public class ZakazanaKozmetickaUslugaPopUp extends JDialog{
	
	private static final long serialVersionUID = -5247231764310200252L;
	
	private ZakazanaKozmetickaUslugaFM zakazaneKozmetickeUsluge;
	private ZakazanaKozmetickaUsluga editK;
	private JPanel parent;
	private appSettings app;
    private String stariTip;
    private String nazivi = "";
    private ArrayList<TipKozmetickeUsluge> uslugice = new ArrayList<TipKozmetickeUsluge>(); 

	
	// Jedan isti dijalog za Add i Edit
	
	public ZakazanaKozmetickaUslugaPopUp(JPanel parent, ZakazanaKozmetickaUslugaFM zakazaneKozmetickeUsluge, appSettings app,ZakazanaKozmetickaUsluga editUsluga) {

		if (editUsluga != null) {
			setTitle("Izmjena zakazanog tretmana");
		} else {
			setTitle("Dodavanje zakazanog tretmana");
		}
		
		this.parent = parent;
		this.zakazaneKozmetickeUsluge = zakazaneKozmetickeUsluge;
		this.app = app;
		this.editK = editUsluga;

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
		
		uslugice.addAll(app.gettkufm().getTipKozmetickeUsluge().values());
					
//boolean obrisan, String tip usluge, String trajanje, String naziv usluge, double cijena	     
		
		JLabel lblNaziv = new JLabel("Naziv usluge");
		add(lblNaziv);
        
		nazivi = app.addKozmetickaUslugaUKozmeticara();
		
		String[] naziviArray = nazivi.split(",");
		
		JComboBox<String> dropdownNazivi = new JComboBox<>(naziviArray);
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

		JTextField txtTrajanje = new JTextField(15);
		add(txtTrajanje, "span 2");
		txtTrajanje.setText(ttt.getTrajanje());
		txtTrajanje.setEditable(false);
       
		JLabel lblCijena = new JLabel("Cijena usluge");
		add(lblCijena);

		JTextField txtCijena = new JTextField(15);
		add(txtCijena, "span 2");
		txtCijena.setText(String.valueOf(ttt.getCijena()));
        txtCijena.setEditable(false);
        
		JLabel lblKlijent = new JLabel("Klijent");
		add(lblKlijent);

		ArrayList<Klijent> klijenti = new ArrayList<Klijent>(app.getKfm().getKlijenti().values());
		
		JComboBox<String> dropdownKlijenti = new JComboBox<>();

		
		for (Klijent k: klijenti) {
		    dropdownKlijenti.addItem(k.getKorisnickoIme());		
		}
		
		add(dropdownKlijenti);
		
		
		dropdownNazivi.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		        String selectedNaziv = (String) dropdownNazivi.getSelectedItem();
		        String tipovi = app.addTipKozmetickeUslugeUKozmeticaraGui(selectedNaziv);
		        String[] tipoviArray = tipovi.split(",");
		        
		        dropdownTipovi.removeAllItems(); 
				dropdownKozmeticari.removeAllItems();
		        
		        for (String tip : tipoviArray) {
		            dropdownTipovi.addItem(tip); 
		        }
		        
				KozmetickaUsluga usluga = app.getkufm().PronadjiKozmetickuUsluguPoNazivu(selectedNaziv);
				int indeksic = usluga.getId();
				
				
				String[] kozmeticarArray = new String[brojKozmeticara];
				int kkk = 0;

				ArrayList<String> kozmeticarList = new ArrayList<>(Arrays.asList(kozmeticarArray));
				for (int i = 0; i < brojKozmeticara; i++) {
				    Kozmeticar k = app.getkkfm().getKozmeticar().get((i));
		    		String kIme = k.getKorisnickoIme();
		 
		    		if (!k.getObrisan()) {
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

				}
				
		        for (String kIme : kozmeticarArray) {
		            dropdownKozmeticari.addItem(kIme); // Add new items
		        }
		        
		    }
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
					
					System.out.println(datummm);
					
					String selectedK = (String)dropdownKozmeticari.getSelectedItem();
					Kozmeticar kkkkkk = app.getkkfm().PronadjiKozmeticaraPoKorisnickomImenu(selectedK);
					int kozm = kkkkkk.getId();
					
					System.out.println(kozm);
					
					if (datum != null) {
						LocalDate datummmLocalDate = datummm.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
						System.out.println(datummmLocalDate);
						vrijemeee = app.setRadnoVrijemeKozmeticara(kozm,datummmLocalDate);
						System.out.println(vrijemeee);
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
			        String selectedTip = (String) dropdownTipovi.getSelectedItem();
			        TipKozmetickeUsluge ttt = app.gettkufm().PronadjiTipKozmetickeUslugePoTipu(selectedTip);
			        
			        txtTrajanje.setText(ttt.getTrajanje());
			        txtTrajanje.setEditable(false);
			        txtCijena.setText(String.valueOf(ttt.getCijena()));
			        txtCijena.setEditable(false);
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
					
					System.out.println(datummm);
					
					String selectedK = (String)dropdownKozmeticari.getSelectedItem();
					Kozmeticar kkkkkk = app.getkkfm().PronadjiKozmeticaraPoKorisnickomImenu(selectedK);
					int kozm = kkkkkk.getId();
					
					System.out.println(kozm);
					
					if (datum != null) {
						LocalDate datummmLocalDate = datummm.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
						System.out.println(datummmLocalDate);
						vrijemeee = app.setRadnoVrijemeKozmeticara(kozm,datummmLocalDate);
						System.out.println(vrijemeee);
						for (LocalTime time : vrijemeee) {
						    dropdownVrijeme.addItem(time);
						}
					}}
		    	
		    }
		});
		
		
		add(new JLabel());

		JButton btnCancel = new JButton("Cancel");
		add(btnCancel);

		JButton btnOK = new JButton("OK");
		add(btnOK);
		
		ZakazanaKozmetickaUslugaPopUp.this.getRootPane().setDefaultButton(btnOK);

		if (editK != null) {
			// popuni polja vrednostima
			
			String tipic = editK.getTipUsluge();
			
			for (TipKozmetickeUsluge ku: uslugice) {
				if (ku.getTipUsluge().equals(tipic)) {

					dropdownNazivi.setSelectedItem(ku.getNazivUsluge());
					dropdownTipovi.setSelectedItem(tipic);

				}
			}
			
			txtTrajanje.setText(editK.getTrajanje());
			
			Double cijenaa = editK.getCijena();
			txtCijena.setText(Double.toString(cijenaa));
			
			LocalDate datumic = editK.getDatum();
			Date dateic = Date.from(datumic.atStartOfDay(ZoneId.systemDefault()).toInstant()); 
	        dateModel.setValue(dateic);	

	        int idKlijent = editK.getIdKlijent();
	        dropdownKlijenti.setSelectedItem(klijenti.get(idKlijent).getKorisnickoIme());

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
				String trajanje = txtTrajanje.getText().trim();
				String cijena = txtCijena.getText().trim();
				double cijenaDouble = Double.parseDouble(cijena);

				Date selectedDate = (Date) dateModel.getValue();
				
				LocalTime timeSelected = (LocalTime)dropdownVrijeme.getSelectedItem();
				String timeString = timeSelected.toString();
				
				String dateString = "";
				if (selectedDate != null) {
				    SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
				    dateString = dateFormat.format(selectedDate);
				}
				
				String korisnickoImeKlijent = (String) dropdownKlijenti.getSelectedItem();
				String korisnickoImeKozmeticar = (String) dropdownKozmeticari.getSelectedItem();

				// odve se odvaja GUI od funkcionalnosti Manager-a
				// ne mesati logiku app i funkcionalnosti sa GUI-om !
				if (editK != null) {
					stariTip = editK.getTipUsluge();
					int id = editK.getId();
	
							
					app.changeZakazanaKozmetickaUslugaTypeOfService(id, tippp);
					app.changeZakazanaKozmetickaUslugaDuration(id, trajanje);
					app.changeZakazanaKozmetickaUslugaPrice(id, cijenaDouble);
					app.changeZakazanaKozmetickaUslugaDate(id, dateString);
					app.changeZakazanaKozmetickaUslugaTime(id, timeString);
					
					
				} else {
					app.addZakazanaKozmetickaUsluga("ZAKAZAN", dateString, timeString, tippp, korisnickoImeKlijent, korisnickoImeKozmeticar);
				}
				
				try {
				    ((ZakazanaKozmetickaUslugaRecepcionarTabelaFrame) parent).refreshData();
				} catch (Exception e1) {
					((ZakazanaKozmetickaUslugaMenadzerTabelaFrame) parent).refreshData();
				}

				ZakazanaKozmetickaUslugaPopUp.this.dispose();

			}
			
		});

		btnCancel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				ZakazanaKozmetickaUslugaPopUp.this.dispose();
			}
		});
	}

}

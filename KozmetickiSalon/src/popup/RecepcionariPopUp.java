package popup;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import fileMenager.RecepcionarFM;
import gui.RecepcionarTabelaFrame;
import korisnici.Klijent;
import korisnici.Kozmeticar;
import korisnici.Menadzer;
import korisnici.Recepcionar;
import korisnici.StrucnaSprema;
import meni.appSettings;
import net.miginfocom.swing.MigLayout;

public class RecepcionariPopUp extends JDialog{
	
	private static final long serialVersionUID = -5247231764310200252L;
	private RecepcionarFM recepcionari;
	private Recepcionar editR;
	private JPanel parent;
	private appSettings app;
    private JRadioButton femaleRadioButton;
    private JRadioButton maleRadioButton;
    private JPasswordField passwordField;
    private JCheckBox showPasswordCheckbox;
    private JCheckBox yesNoCheckBox;
    private String staroKI;
	
	// Jedan isti dijalog za Add i Edit
	
	public RecepcionariPopUp(JPanel parent, RecepcionarFM recepcionari, appSettings app,Recepcionar editRecepcionar) {

		if (editRecepcionar != null) {
			setTitle("Izmjena recepcionara");
		} else {
			setTitle("Dodavanje recepcionara");
		}
		this.parent = parent;
		this.recepcionari = recepcionari;
		this.app = app;
		this.editR = editRecepcionar;

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
		
		ArrayList<Klijent> klijenti = new ArrayList<Klijent>(app.getKfm().getKlijenti().values());
		ArrayList<Kozmeticar> kozmeticari = new ArrayList<Kozmeticar>(app.getkkfm().getKozmeticar().values());
		ArrayList<Recepcionar> recepcionari = new ArrayList<Recepcionar>(app.getrfm().getRecepcionar().values());
		ArrayList<Menadzer> menadzeri = new ArrayList<Menadzer>(app.getmfm().getMenadzeri().values());
					
//boolean obrisan, String ime, String prezime, String korisnickoIme, String lozinka, String pol, String telefon, String adresa, StrucnaSprema nivoStrucneSpreme, double nivoRadnogStaza, boolean imaBonus, double iznosBonusa, double plata, double ukupnaPlata,ArrayList<Integer> tretmani	        
		JLabel lblIme = new JLabel("Ime");
		add(lblIme);

		JTextField txtIme = new JTextField(20);
		add(txtIme, "span 2");

		JLabel lblPrezime = new JLabel("Prezime");
		add(lblPrezime);

		JTextField txtPrezime = new JTextField(20);
		add(txtPrezime, "span 2");

		JLabel lblKorisnickoIme = new JLabel("Korisnicko ime");
		add(lblKorisnickoIme);

		JTextField txtKorisnickoIme = new JTextField(20);
		add(txtKorisnickoIme, "span 2");
		
		JLabel lblLozinka = new JLabel("Lozinka");
		add(lblLozinka);

        passwordField = new JPasswordField(20);
        add(passwordField, "span 2");
        
        showPasswordCheckbox = new JCheckBox("Show Password");
        showPasswordCheckbox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JCheckBox checkBox = (JCheckBox) e.getSource();
                if (checkBox.isSelected()) {
                    passwordField.setEchoChar((char) 0);
                } else {
                    passwordField.setEchoChar('*');
                }
            }
        });
        add(showPasswordCheckbox);
		
		add(new JLabel(""));
		add(new JLabel(""));
        
		JLabel lblPol = new JLabel("Pol");
		add(lblPol);

        femaleRadioButton = new JRadioButton("zenski");
        maleRadioButton = new JRadioButton("muski");

        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(femaleRadioButton);
        buttonGroup.add(maleRadioButton);

        add(femaleRadioButton);
        add(maleRadioButton);
        
		JLabel lblTelefon = new JLabel("Telefon");
		add(lblTelefon);

		JTextField txtTelefon = new JTextField(20);
		add(txtTelefon, "span 2");
		
		JLabel lblAdresa = new JLabel("Adresa");
		add(lblAdresa);

		JTextField txtAdresa = new JTextField(20);
		add(txtAdresa, "span 2");
		
		JLabel lblStrucnaSprema = new JLabel("Nivo strucne spreme");
		add(lblStrucnaSprema);

		String [] options = {"BEZ_KVALIFIKACIJE", "OPSTA", "STRUCNA", "AKADEMSKA", "STRUKOVNA"};
		JComboBox<String> dropdown = new JComboBox<>(options);
		add(dropdown, "span 2");
	
		JLabel lblRadniStaz = new JLabel("Nivo radnog staza");
		add(lblRadniStaz);

		JTextField txtRadniStaz = new JTextField(20);
		add(txtRadniStaz, "span 2");
		
//        JLabel yesNoLabel = new JLabel("Bonus:");
//        yesNoCheckBox = new JCheckBox("Ne"); 
//
//        yesNoCheckBox.addItemListener(new ItemListener() {
//            public void itemStateChanged(ItemEvent e) {
//                if (e.getStateChange() == ItemEvent.SELECTED) {
//                    yesNoCheckBox.setText("Da");
//                } else {
//                    yesNoCheckBox.setText("Ne");
//                }
//            }
//        });
//
//        add(yesNoLabel);
//        add(yesNoCheckBox);
//        
//		add(new JLabel(""));
//        
//		JLabel lblIznosBonusa = new JLabel("Iznos bonusa");
//		add(lblIznosBonusa);
//
//		JTextField txtIznosBonusa = new JTextField(20);
//		add(txtIznosBonusa, "span 2");
//		
//		JLabel lblPlata = new JLabel("Plata");
//		add(lblPlata);
//
//		JTextField txtPlata = new JTextField(20);
//		add(txtPlata, "span 2");
//		
//		JLabel lblUkupnaPlata = new JLabel("Ukupna plata");
//		add(lblUkupnaPlata);
//
//		JTextField txtUkupnaPlata = new JTextField(20);
//		add(txtUkupnaPlata, "span 2");
        
		add(new JLabel());

		JButton btnCancel = new JButton("Cancel");
		add(btnCancel);

		JButton btnOK = new JButton("OK");
		add(btnOK);
		RecepcionariPopUp.this.getRootPane().setDefaultButton(btnOK);


		if (editR != null) {
			// popuni polja vrednostima
				       
			//boolean obrisan, String ime, String prezime, String korisnickoIme, String lozinka, String pol, String telefon, String adresa, StrucnaSprema nivoStrucneSpreme, double nivoRadnogStaza, boolean imaBonus, double iznosBonusa, double plata, double ukupnaPlata,ArrayList<Integer> tretmani	        

			txtIme.setText(editR.getIme());
			txtPrezime.setText(editR.getPrezime());
			txtKorisnickoIme.setText(editR.getKorisnickoIme());
			staroKI = editR.getKorisnickoIme();
			String password = editR.getLozinka();

			passwordField.setEchoChar('*'); 
			passwordField.setText(password);
			
			String pol = editR.getPol();
			
			if (pol.equals("zenski")) {
				femaleRadioButton.setSelected(true);
			} else if (pol.equals("muski")){
				maleRadioButton.setSelected(true);
			}
			
			txtTelefon.setText(editR.getTelefon());
			txtAdresa.setText(editR.getAdresa());
			
//			boolean imaBonus = editR.getImaBonus();
//			yesNoCheckBox.setSelected(imaBonus);
//			
//			double iznosBonusa = editR.getIznosBonusa();
//			txtIznosBonusa.setText(Double.toString(iznosBonusa));
			
			double radniStaz = editR.getNivoRadnogStaza();
			txtRadniStaz.setText(Double.toString(radniStaz));
			
			StrucnaSprema s = editR.getNivoStrucneSpreme();
			
			for (int i = 0; i < options.length; i++) {
				if (options[i].equals(s.toString())) {
					dropdown.setSelectedItem(options[i]);
				}
			}
			
		}

		btnOK.addActionListener(new ActionListener() {
			
			//"Ime", "Prezime", "Korisnicko ime", "Lozinka", "Pol", "Telefon", "Adresa", "Potroseno novca", "Kartica lojalnosti"

			@Override
			public void actionPerformed(ActionEvent e) {
				String ime = txtIme.getText().trim();
				String prezime = txtPrezime.getText().trim();
				String korisnickoIme = txtKorisnickoIme.getText().trim();
				char[] passwordChars = passwordField.getPassword();
				String lozinka = new String(passwordChars);
				String pol = "";

				if (femaleRadioButton.isSelected()) {
				    pol = "zenski";
				} else if (maleRadioButton.isSelected()) {
				    pol = "muski";
				}
				
				String telefon = txtTelefon.getText().trim();
				String adresa = txtAdresa.getText().trim();
				String radniStaz = txtRadniStaz.getText().trim();
				double radniStazDouble = Double.parseDouble(radniStaz);
				String nivoStrucneSpreme = (String) dropdown.getSelectedItem();

				boolean found = false;
				int drama = 0;
				
				Klijent klijent = null;

				for (Klijent k: klijenti) {
					if (korisnickoIme.equals(k.getKorisnickoIme()) && lozinka.equals(k.getLozinka())){
						found = true;
						drama = 1;
						klijent = k;
						break;
					}

				}
				
				Kozmeticar kozmeticar = null;
				
				if (!found) {
					for (Kozmeticar k: kozmeticari) {
						if (korisnickoIme.equals(k.getKorisnickoIme()) && lozinka.equals(k.getLozinka())) {
							found = true;
							drama = 2;
							kozmeticar = k;
							break;
						}
					}
				}
				
				Recepcionar recepcionar = null;

				if (!found) {
					for (Recepcionar k: recepcionari) {
						if (!k.equals(editR)) {
							if (korisnickoIme.equals(k.getKorisnickoIme()) && lozinka.equals(k.getLozinka())) {
								found = true;
								drama = 3;
								recepcionar = k;
								break;
							}
						}
					}
				}
				
				Menadzer menadzer = null;
				
				if (!found) {
					for (Menadzer k: menadzeri) {
						if (korisnickoIme.equals(k.getKorisnickoIme()) && lozinka.equals(k.getLozinka())) {
							found = true;
							drama = 4;
							menadzer = k;
							break;
						}
					}
				}
				
				if (editR != null) {
					
					if (korisnickoIme.equals("") || lozinka.equals("")) {
						JOptionPane.showMessageDialog(RecepcionariPopUp.this, "Niste unijeli korisnicko ime ili lozinku podatke.", "Greška", JOptionPane.INFORMATION_MESSAGE);		            
					} else if (found){
						JOptionPane.showMessageDialog(RecepcionariPopUp.this, "Korisnik sa tim korisnickim imenom vec postoji.", "Greška", JOptionPane.INFORMATION_MESSAGE);		            
					} else {
					
						app.changeRecepcionarName(staroKI, ime);
						app.changeRecepcionarSurname(staroKI, prezime);
						app.changeRecepcionarUsername(staroKI, korisnickoIme);
						app.changeRecepcionarPassword(korisnickoIme, lozinka);
						app.changeRecepcionarPhone(korisnickoIme, telefon);
						app.changeRecepcionarAdress(korisnickoIme, adresa);
						app.changeRecepcionarSex(korisnickoIme, pol);
						app.addRecepcionarNivoSS(korisnickoIme, nivoStrucneSpreme);
						app.addRecepcionarNivoRS(korisnickoIme, radniStazDouble);
						//app.changeKlijentAmount(korisnickoIme, kolicinaDouble);
						
						((RecepcionarTabelaFrame)parent).refreshData();
						RecepcionariPopUp.this.dispose();
					}
					
				} else {
					
					if (korisnickoIme.equals("") || lozinka.equals("")) {
						JOptionPane.showMessageDialog(RecepcionariPopUp.this, "Niste unijeli korisnicko ime ili lozinku podatke.", "Greška", JOptionPane.INFORMATION_MESSAGE);		            
					} else if (found){
						JOptionPane.showMessageDialog(RecepcionariPopUp.this, "Korisnik sa tim korisnickim imenom vec postoji.", "Greška", JOptionPane.INFORMATION_MESSAGE);		            
					} else {
						app.addRecepcionar(ime, prezime, korisnickoIme, lozinka, pol, telefon, adresa, nivoStrucneSpreme, radniStazDouble);
						
						((RecepcionarTabelaFrame)parent).refreshData();
						RecepcionariPopUp.this.dispose();
					}
				}
			}
		});

		btnCancel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				RecepcionariPopUp.this.dispose();
			}
		});
	}


}

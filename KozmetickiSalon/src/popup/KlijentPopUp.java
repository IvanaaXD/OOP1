package popup;

import java.awt.event.ActionEvent;

import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import fileMenager.KlijentFM;
import gui.KlijentTabelaFrame;
import korisnici.Klijent;
import korisnici.Kozmeticar;
import korisnici.Menadzer;
import korisnici.Recepcionar;
import meni.appSettings;
import net.miginfocom.swing.MigLayout;


public class KlijentPopUp extends JDialog{
	
		private static final long serialVersionUID = -5247231764310200252L;
		private KlijentFM klijenti;
		private Klijent editK;
		private JPanel parent;
		private appSettings app;
	    private JRadioButton femaleRadioButton;
	    private JRadioButton maleRadioButton;
	    private JPasswordField passwordField;
	    private JCheckBox showPasswordCheckbox;
	    private JCheckBox yesNoCheckBox;
	    private String staroKI;
		
		// Jedan isti dijalog za Add i Edit
		
		public KlijentPopUp(JPanel parent, KlijentFM klijenti, appSettings app, Klijent editKlijent) {

			if (editKlijent != null) {
				setTitle("Izmjena klijenta");
			} else {
				setTitle("Dodavanje klijenta");
			}
			this.parent = parent;
			this.klijenti = klijenti;
			this.app = app;
			this.editK = editKlijent;

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
						
			//"Ime", "Prezime", "Korisnicko ime", "Lozinka", "Pol", "Telefon", "Adresa", "Potroseno novca", "Kartica lojalnosti"
	        
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
			
//			JLabel lblKolicina = new JLabel("Potrosena kolicina");
//			add(lblKolicina);
//
//			JTextField txtKolicina = new JTextField(20);
//			add(txtKolicina, "span 2");
//			
//	        JLabel yesNoLabel = new JLabel("Kartica lojalnosti:");
//	        yesNoCheckBox = new JCheckBox("Ne"); 
//
//	        yesNoCheckBox.addItemListener(new ItemListener() {
//	            public void itemStateChanged(ItemEvent e) {
//	                if (e.getStateChange() == ItemEvent.SELECTED) {
//	                    yesNoCheckBox.setText("Da");
//	                } else {
//	                    yesNoCheckBox.setText("Ne");
//	                }
//	            }
//	        });

//	        add(yesNoLabel);
//	        add(yesNoCheckBox);

			add(new JLabel());

			JButton btnCancel = new JButton("Cancel");
			add(btnCancel);

			JButton btnOK = new JButton("OK");
			add(btnOK);
			
			KlijentPopUp.this.getRootPane().setDefaultButton(btnOK);


			if (editK != null) {
				// popuni polja vrednostima
				
				//"Ime", "Prezime", "Korisnicko ime", "Lozinka", "Pol", "Telefon", "Adresa", "Potroseno novca", "Kartica lojalnosti"
		        
				txtIme.setText(editK.getIme());
				txtPrezime.setText(editK.getPrezime());
				txtKorisnickoIme.setText(editK.getKorisnickoIme());
				staroKI = editK.getKorisnickoIme();
				String password = editK.getLozinka();

				passwordField.setEchoChar('*'); 
				passwordField.setText(password);
				
				String pol = editK.getPol();
				
				if (pol.equals("zenski")) {
					femaleRadioButton.setSelected(true);
				} else if (pol.equals("muski")){
					maleRadioButton.setSelected(true);
				}
				
				txtTelefon.setText(editK.getTelefon());
				txtAdresa.setText(editK.getAdresa());
				
//				double kolicina = editK.getKolicina();
//				txtKolicina.setText(Double.toString(kolicina));
//				txtKolicina.setEditable(false); 
//
//				boolean isChecked = editK.getKartica();
//				yesNoCheckBox.setSelected(isChecked);
//				yesNoCheckBox.setEnabled(false);
				


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

					boolean found = false;
					int drama = 0;
					
					Klijent klijent = null;

					for (Klijent k: klijenti) {
						if (!k.equals(editK)) {
							if (korisnickoIme.equals(k.getKorisnickoIme()) && lozinka.equals(k.getLozinka())){
								found = true;
								drama = 1;
								klijent = k;
								break;
							}
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
							if (korisnickoIme.equals(k.getKorisnickoIme()) && lozinka.equals(k.getLozinka())) {
								found = true;
								drama = 3;
								recepcionar = k;
								break;
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
					
					if (editK != null) {
						
						if (korisnickoIme.equals("") || lozinka.equals("")) {
							JOptionPane.showMessageDialog(KlijentPopUp.this, "Niste unijeli korisnicko ime ili lozinku podatke.", "Greška", JOptionPane.INFORMATION_MESSAGE);		            
						} else if (found){
							JOptionPane.showMessageDialog(KlijentPopUp.this, "Korisnik sa tim korisnickim imenom vec postoji.", "Greška", JOptionPane.INFORMATION_MESSAGE);		            
						} else {
						
							app.changeKlijentName(staroKI, ime);
							app.changeKlijentSurname(staroKI, prezime);
							app.changeKlijentUsername(staroKI, korisnickoIme);
							app.changeKlijentPassword(korisnickoIme, lozinka);
							app.changeKlijentPhone(korisnickoIme, telefon);
							app.changeKlijentAdress(korisnickoIme, adresa);
							app.changeKlijentSex(korisnickoIme, pol);
							
							((KlijentTabelaFrame)parent).refreshData();
							KlijentPopUp.this.dispose();
						
						}
					} else {
						
						if (korisnickoIme.equals("") || lozinka.equals("")) {
							JOptionPane.showMessageDialog(KlijentPopUp.this, "Niste unijeli korisnicko ime ili lozinku podatke.", "Greška", JOptionPane.INFORMATION_MESSAGE);		            
						} else if (found){
							JOptionPane.showMessageDialog(KlijentPopUp.this, "Korisnik sa tim korisnickim imenom vec postoji.", "Greška", JOptionPane.INFORMATION_MESSAGE);		            
						} else {
							app.addKlijent(ime, prezime, korisnickoIme, lozinka, pol, telefon, adresa, 0.00, false);
							
							((KlijentTabelaFrame)parent).refreshData();
							KlijentPopUp.this.dispose();
						}
					}
				}				
			});

			btnCancel.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					KlijentPopUp.this.dispose();
				}
			});
		}

	}


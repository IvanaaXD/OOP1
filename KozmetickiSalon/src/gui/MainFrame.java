package gui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import korisnici.Klijent;
import korisnici.Kozmeticar;
import korisnici.Menadzer;
import korisnici.Recepcionar;
import meni.appSettings;
import net.miginfocom.swing.MigLayout;
import osobineTretmana.KozmetickiSalon;
import popup.KlijentPopUpRegistracija;

public class MainFrame extends JFrame{

	private static final long serialVersionUID = 8456560429229699542L;
	
	private appSettings app;
	
	public MainFrame(appSettings app) {
		this.app = app;
		setSize(300, 200);
		setTitle("Kozmeticki salon");
		
	    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	    int screenWidth = screenSize.width;
	    int screenHeight = screenSize.height;
	    int frameWidth = getWidth();
	    int frameHeight = getHeight();
	    int x = (screenWidth - frameWidth) / 2;
	    int y = (screenHeight - frameHeight) / 2;
	    setLocation(x, y);

		setVisible(true);

	    SwingUtilities.invokeLater(() -> {
	        mainFrame();
	    });
	    
		loginDialog();

	}
	
	public void loginDialog() {
	    JDialog d = new JDialog();
	    d.setTitle("Prijava");
	    d.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
	    d.setResizable(false);
	    initLoginGUI(d);
	    d.pack();
	    d.setLocationRelativeTo(null);
	    d.setVisible(true);
	}
	
	private void mainFrame() {
	    this.setTitle("Kozmetički salon");
	    this.setSize(700, 500);
	    this.setLocationRelativeTo(null);
	    this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	    this.setResizable(true);

	    ImageIcon icon = new ImageIcon("img/icon.png");
	    this.setIconImage(icon.getImage());
	    
	    ImageIcon pictureIcon = new ImageIcon("img/beauty.png");
	    JLabel pictureLabel = new JLabel(pictureIcon);
	    
	    int desiredMainFrameWidth = this.getWidth();
	    int desiredMainFrameHeight = this.getHeight();
	    int desiredJLabelWidth = (int) (desiredMainFrameWidth * 0.3);
	    int desiredJLabelHeight = (int) (desiredMainFrameHeight * 0.4);
	    int desiredImageIconWidth = (int) (desiredJLabelWidth * 1);
	    int desiredImageIconHeight = (int) (desiredJLabelHeight * 1);

	    Image scaledImage = pictureIcon.getImage().getScaledInstance(desiredImageIconWidth, desiredImageIconHeight, Image.SCALE_SMOOTH);
	    ImageIcon scaledIcon = new ImageIcon(scaledImage);

	    pictureLabel.setPreferredSize(new Dimension(desiredJLabelWidth, desiredJLabelHeight));

	    pictureLabel.setIcon(scaledIcon);

	    Container contentPane = this.getContentPane();
	    contentPane.setLayout(new BorderLayout());
	    int contentPaneWidth = 800;
	    int contentPaneHeight = 600;
	    contentPane.setSize(contentPaneWidth, contentPaneHeight);	    
	    contentPane.add(pictureLabel, BorderLayout.NORTH);
	
		KozmetickiSalon ks = new KozmetickiSalon();
		
		ArrayList<KozmetickiSalon> salon = new ArrayList<KozmetickiSalon> (app.getksfm().getKozmetickiSalon().values());
		ks = salon.get(0);
		
		LocalTime pocetak = ks.getPocetakRadnogVremena();
		LocalTime kraj = ks.getKrajRadnogVremena();
		
		DateTimeFormatter formatterPocetak = DateTimeFormatter.ofPattern("HH:mm");
		DateTimeFormatter formatterKraj = DateTimeFormatter.ofPattern("HH:mm");
		
		String pocetakString = pocetak.format(formatterPocetak);
		String krajString = kraj.format(formatterKraj);
		
		String ime = ks.getNaziv();
		
		JLabel imeSalona = new JLabel("Salon: " + ime);
//		JPanel imePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
//		imePanel.add(imeSalona);

	    JLabel lblRadnoVrijeme = new JLabel("Radno vrijeme: " + pocetakString + " - " + krajString);
	    JPanel labelPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
	    labelPanel.add(lblRadnoVrijeme);

	    JButton buttonSignUp = new JButton("Registrujte se");
	    buttonSignUp.setPreferredSize(new Dimension(120, 30));
	    
	    JButton buttonLogIn = new JButton("Prijavite se");
	    buttonLogIn.setPreferredSize(new Dimension(120, 30));
	    
	    JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
	    buttonPanel.add(buttonLogIn);
	    buttonPanel.add(buttonSignUp);

	    JPanel imePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
	    imePanel.add(imeSalona);

	    // Create a new panel with BorderLayout to contain the label and button panels
	    JPanel centerPanel = new JPanel(new BorderLayout());
	    centerPanel.add(imePanel, BorderLayout.NORTH);
	    centerPanel.add(labelPanel, BorderLayout.CENTER);
	    centerPanel.add(buttonPanel, BorderLayout.SOUTH);

	    contentPane.add(centerPanel, BorderLayout.CENTER);
	    
	    buttonSignUp.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				KlijentPopUpRegistracija add = new KlijentPopUpRegistracija(MainFrame.this, app.getKfm(), app, null);
				add.setVisible(true);
			}
		});
	    
	    buttonLogIn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				loginDialog();
			}
		});
	    
	    initMainGUI();
	}

	
	private void initLoginGUI(JDialog dialog) {
		/*
		 * Malo detaljnije podesavanje MigLayout-a: Drugi parametar (string) sadrzi 2
		 * prazne uglaste zagrade jer imamo 2 kolone (ovde nista nismo podesili) Treci
		 * parametar ima onoliko uglastih zagrada koliko imamo redova (u nasem slucaju
		 * 4) Unutar zagrada mozemo detaljnije podesavati kolone i redove, dok vrednosti
		 * izmedju njih predstavljaju razmake u pikselima. Ovde smo postavili razmak od
		 * 20px izmedju 1. i 2. i izmedju 3. i 4. reda.
		 */
		
		ArrayList<Klijent> klijenti = new ArrayList<Klijent>(app.getKfm().getKlijenti().values());
		ArrayList<Kozmeticar> kozmeticari = new ArrayList<Kozmeticar>(app.getkkfm().getKozmeticar().values());
		ArrayList<Recepcionar> recepcionari = new ArrayList<Recepcionar>(app.getrfm().getRecepcionar().values());
		ArrayList<Menadzer> menadzeri = new ArrayList<Menadzer>(app.getmfm().getMenadzeri().values());
		
		MigLayout layout = new MigLayout("wrap 2", "[][]", "[]20[][]20[]");
		dialog.setLayout(layout);

		JTextField tfKorisnickoIme = new JTextField(20);
		JPasswordField pfLozinka = new JPasswordField(20);
		JButton btnOk = new JButton("OK");
		JButton btnCancel = new JButton("Cancel");

		dialog.getRootPane().setDefaultButton(btnOk);

		dialog.add(new JLabel("Dobrodošli! Prijavite se. "), "span 2");
		dialog.add(new JLabel("Korisničko ime: "));
		dialog.add(tfKorisnickoIme);
		dialog.add(new JLabel("Lozinka: "));
		dialog.add(pfLozinka);
		dialog.add(new JLabel());
		dialog.add(btnOk, "split 2");
		dialog.add(btnCancel);

		// Klik na Login dugme
		btnOk.addActionListener(new ActionListener() {	
			@Override
			public void actionPerformed(ActionEvent e) {
				String korisnickoIme = tfKorisnickoIme.getText().trim();
				String lozinka = new String(pfLozinka.getPassword()).trim();
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
				
				if (korisnickoIme.equals("") || lozinka.equals("")) {
					JOptionPane.showMessageDialog(dialog, "Niste unijeli sve podatke.", "Greška", JOptionPane.INFORMATION_MESSAGE);		            
				} else if (!found){
					JOptionPane.showMessageDialog(dialog, "Niste unijeli tacne podatke.", "Greška", JOptionPane.INFORMATION_MESSAGE);		            
				} else {					
		            
		            if (drama == 1) {
		            	KlijentFrame kf = new KlijentFrame(app, klijent);
		            	kf.setVisible(true);
		            } 
		            if (drama == 2) {
						KozmeticarFrame kf = new KozmeticarFrame(app, kozmeticar);
						kf.setVisible(true);
		            } 
		            if (drama == 3) {
			    		RecepcionarFrame rf = new RecepcionarFrame(app, recepcionar);
			    		rf.setVisible(true);
		            } else if (drama == 4) {
			    		MenadzerFrame mf = new MenadzerFrame(app, menadzer);
			    		mf.setVisible(true);
		            }
					dialog.setVisible(false);
					MainFrame.this.setVisible(false);

				}
			}
		});
		// Cancel dugme samo sakriva trenutni prozor
		btnCancel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dialog.setVisible(false);
				dialog.dispose();
			}
		});

	}

	
	private void initMainGUI() {
		
		JMenuBar mainMenu = new JMenuBar();

		JMenu pocetnaMenu = new JMenu("Pocetna");
		JMenu tretmaniMenu = new JMenu("Tretmani");
		JMenu kozmeticariMenu = new JMenu("Kozmeticari");
		
		mainMenu.add(pocetnaMenu);
		mainMenu.add(tretmaniMenu);
		mainMenu.add(kozmeticariMenu);
		
		this.setJMenuBar(mainMenu);

		// Klikom na stavke menija otvaraju se odgovarajuce forme za prikaz
		tretmaniMenu.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
				TipKozmetickeUslugeTabelaFrameKlijent tkutf = new TipKozmetickeUslugeTabelaFrameKlijent(MainFrame.this, app.gettkufm(), app);

				setContentPane(tkutf);
				revalidate();
				tkutf.setVisible(true);

				//pack();
				
            }
            @Override
            public void mousePressed(MouseEvent e) {}
            @Override
            public void mouseReleased(MouseEvent e) {}
            @Override
            public void mouseEntered(MouseEvent e) {}
            @Override
            public void mouseExited(MouseEvent e) {}
        });
		
		kozmeticariMenu.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
				KozmeticarTabelaFrameKlijent ktfk = new KozmeticarTabelaFrameKlijent(MainFrame.this, app.getkkfm(), app);

				setContentPane(ktfk);
				revalidate();
				ktfk.setVisible(true);

				//pack();
				
            }
            @Override
            public void mousePressed(MouseEvent e) {}
            @Override
            public void mouseReleased(MouseEvent e) {}
            @Override
            public void mouseEntered(MouseEvent e) {}
            @Override
            public void mouseExited(MouseEvent e) {}
        });
		
		pocetnaMenu.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
				MainFramePocetna mfp = new MainFramePocetna(MainFrame.this, app);

				setContentPane(mfp);
				revalidate();
				mfp.setVisible(true);

				//pack();
				
            }
            @Override
            public void mousePressed(MouseEvent e) {}
            @Override
            public void mouseReleased(MouseEvent e) {}
            @Override
            public void mouseEntered(MouseEvent e) {}
            @Override
            public void mouseExited(MouseEvent e) {}
        });
		

	}
	
public static void main(String[] args) {
		
		String fileSeparator = System.getProperty("file.separator");
		
		String klijentPath = "src" + fileSeparator + "podaci" + fileSeparator + "klijenti.csv";
		String menadzerPath = "src" + fileSeparator + "podaci" + fileSeparator + "menadzer.csv";
		String recepcionarPath = "src" + fileSeparator + "podaci" + fileSeparator + "recepcionar.csv";
		String kozmeticarPath = "src" + fileSeparator + "podaci" + fileSeparator + "kozmeticar.csv";
		String tretmanPath = "src" + fileSeparator + "podaci" + fileSeparator + "tretman.csv";
		String tipTretmanaPath = "src" + fileSeparator + "podaci" + fileSeparator + "tiptretmana.csv";
		String zakazaniTretmanPath = "src" + fileSeparator + "podaci" + fileSeparator + "zakazanitretmana.csv";
		String kozmetickisalonPath = "src" + fileSeparator + "podaci" + fileSeparator + "kozmetickisalon.csv";
		
		appSettings app = new appSettings();
		
		app.setPathKlijent(klijentPath);
		app.setPathKozmeticar(kozmeticarPath);
		app.setPathMenadzer(menadzerPath);
		app.setPathRecepcionar(recepcionarPath);
		app.setPathKozmetickaUsluga(tretmanPath);
		app.setPathTipKozmetickeUsluge(tipTretmanaPath);
		app.setPathZakazanaKozmetickaUsluga(zakazaniTretmanPath);
		app.setPathKozmetickiSalon(kozmetickisalonPath);
		
		app.getKfm().loadData();
		app.getkkfm().loadData();
		app.getmfm().loadData();
		app.getrfm().loadData();
		
		app.getkufm().loadData();
		app.gettkufm().loadData();
		app.getzkufm().loadData();
		app.getksfm().loadData();
		
		MainFrame mf = new MainFrame(app);

	}

}

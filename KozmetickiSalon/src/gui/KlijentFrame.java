package gui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import fileMenager.KlijentFM;
import korisnici.Klijent;
import korisnici.Menadzer;
import meni.appSettings;

public class KlijentFrame extends JFrame{
	
	private static final long serialVersionUID = 8456560429229699542L;
	
   	String fileSeparator = System.getProperty("file.separator");
   	String put = "src" + fileSeparator + "podaci" + fileSeparator + "klijenti.csv";
   	private KlijentFM klijentFM;
   	private appSettings app;

	private Klijent klijent;
	
	public KlijentFrame(appSettings app, Klijent klijent) {
		this.app = app;
		this.klijent = klijent;
		this.setSize(300, 200);
		setTitle("Kozmeticki salon - klijent");
		
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

		//mainFrame();
		
		initMainGUI();
	}
	
	private void mainFrame() {
	    this.setTitle("Kozmetički salon - klijent");
	    this.setSize(700, 500);
	    this.setLocationRelativeTo(null);
	    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
	    contentPane.add(pictureLabel, BorderLayout.NORTH);
	    
		JLabel imeKorisnika = new JLabel("Klijent: " + klijent.getIme() + " " + klijent.getPrezime());
		imeKorisnika.setHorizontalAlignment(JLabel.CENTER);
		imeKorisnika.setVerticalAlignment(JLabel.TOP);
		add(imeKorisnika);
	
		
		boolean imaKarticu = klijent.getKartica();
		double iznos = klijent.getKolicina();
					
		String tekst = "DA";
		if (!imaKarticu) {
			tekst = "NE";
		}

	    JLabel lblImaKarticu = new JLabel("Kartica lojalnosti: " + tekst + ", iznos na kartici lojalnosti: " + iznos);
	    JPanel labelPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
	    labelPanel.add(lblImaKarticu);

	    JPanel imePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
	    imePanel.add(imeKorisnika);

	    JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));

	    JPanel centerPanel = new JPanel(new BorderLayout());
	    centerPanel.add(imePanel, BorderLayout.NORTH);
	    centerPanel.add(labelPanel, BorderLayout.CENTER);
	    centerPanel.add(buttonPanel, BorderLayout.SOUTH);

	    contentPane.add(centerPanel, BorderLayout.CENTER);

	    initMainGUI();
	}
	
	
	
	private void initMainGUI() {
		
		JMenuBar mainMenu = new JMenuBar();

		JMenu pocetnaMenu = new JMenu("Pocetna");
		JMenu tretmaniMenu = new JMenu("Zakazani tretmani");
		JMenu kozmeticariMenu = new JMenu("Kozmeticari");
		
		mainMenu.add(pocetnaMenu);
		mainMenu.add(tretmaniMenu);
		mainMenu.add(kozmeticariMenu);
		
		this.setJMenuBar(mainMenu);

		// Klikom na stavke menija otvaraju se odgovarajuce forme za prikaz
		tretmaniMenu.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
				ZakazanaKozmetickaUslugaKlijentTabelaFrame zkutf = new ZakazanaKozmetickaUslugaKlijentTabelaFrame(KlijentFrame.this, app.getzkufm() ,app.getKfm(), app.getkkfm(), klijent ,app);

				setContentPane(zkutf);
				revalidate();
				zkutf.setVisible(true);

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
				KozmeticarTabelaFrameKlijent ktfk = new KozmeticarTabelaFrameKlijent(KlijentFrame.this, app.getkkfm(), app);

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
				KlijentPocetna kfp = new KlijentPocetna(KlijentFrame.this, klijent, app);

				setContentPane(kfp);
				revalidate();
				kfp.setVisible(true);

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
		

	}}


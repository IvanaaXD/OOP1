package gui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.SwingUtilities;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;

import korisnici.Menadzer;
import meni.appSettings;

public class MenadzerFrame extends JFrame{

	private static final long serialVersionUID = 8456560429229699542L;
	
   	private appSettings app;
	private Menadzer menadzer;
	
	public MenadzerFrame(appSettings app, Menadzer menadzer) {
		this.app = app;
		this.menadzer = menadzer;
		this.setSize(300, 200);
		setTitle("Kozmeticki salon - menadzer");
		
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
	    this.setTitle("Kozmetički salon - menadzer");
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
	    
		JLabel label = new JLabel("Menadzer: " + menadzer.getIme() + " " + menadzer.getPrezime());
		label.setHorizontalAlignment(JLabel.CENTER);
		label.setVerticalAlignment(JLabel.TOP);
		add(label);

	    initMainGUI();
	}
	
	
	
	private void initMainGUI() {
		
		JMenuBar mainMenu = new JMenuBar();

		JMenu menadzerMenu = new JMenu("Meni");
		JMenu klijentMenu = new JMenu("Klijenti");
		JMenu zaposleniMenu = new JMenu("Zaposleni");
		JMenu uslugeMenu = new JMenu ("Kozmeticka usluga");
		JMenu izvjestajiMenu = new JMenu ("Izvjestaji");
		JMenu salonMenu = new JMenu ("O salonu");
		
		JMenuItem kozmeticarItem = new JMenuItem ("Kozmeticari");
		JMenuItem recepcionarItem = new JMenuItem ("Recepcionari");
		JMenuItem menadzerItem = new JMenuItem ("Menadzeri");
		
		JMenuItem uslugaItem = new JMenuItem ("Kozmeticka usluga");
		JMenuItem tipUslugeItem = new JMenuItem ("Tipovi kozmetickih usluga");
		JMenuItem zakazanTretmanItem = new JMenuItem ("Zakazani tretmani");
		
		JMenuItem izvjestajJedanItem = new JMenuItem("Izvjestaj 1");
		JMenuItem izvjestajDvaItem = new JMenuItem("Izvjestaj 2");
		JMenuItem izvjestajTriItem = new JMenuItem("Izvjestaj 3");
		JMenuItem izvjestajCetiriItem = new JMenuItem("Izvjestaj 4");

		JMenuItem imeItem = new JMenuItem ("Naziv salona");
		JMenuItem vrijemeItem = new JMenuItem ("Radno vrijeme");
		JMenuItem karticaItem = new JMenuItem ("Kartica lojalnosti");
		JMenuItem bonusItem = new JMenuItem ("Bonus");
		JMenuItem plateItem = new JMenuItem ("Plate");
		JMenuItem prihodiIRashodiItem = new JMenuItem ("Prihodi i rashodi");

		zaposleniMenu.add(kozmeticarItem);
		zaposleniMenu.add(recepcionarItem);
		zaposleniMenu.add(menadzerItem);
		
		uslugeMenu.add(uslugaItem);
		uslugeMenu.add(tipUslugeItem);
		uslugeMenu.add(zakazanTretmanItem);
		
		izvjestajiMenu.add(izvjestajJedanItem);
		izvjestajiMenu.add(izvjestajDvaItem);
		izvjestajiMenu.add(izvjestajTriItem);
		izvjestajiMenu.add(izvjestajCetiriItem);
		
		salonMenu.add(imeItem);
		salonMenu.add(vrijemeItem);
		salonMenu.add(karticaItem);
		salonMenu.add(bonusItem);
		salonMenu.add(plateItem);
		salonMenu.add(prihodiIRashodiItem);

		mainMenu.add(menadzerMenu);
		mainMenu.add(klijentMenu);
		mainMenu.add(zaposleniMenu);
		mainMenu.add(uslugeMenu);
		mainMenu.add(izvjestajiMenu);
		mainMenu.add(salonMenu);

		this.setJMenuBar(mainMenu);


		// Klikom na stavke menija otvaraju se odgovarajuce forme za prikaz
//		menadzerMenu.addActionListener(new ActionListener() {
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				MenadzerPocetna mp = new MenadzerPocetna(MenadzerFrame.this, menadzer, app);
//
//				setContentPane(mp);
//				revalidate();
//				mp.setVisible(true);
//
//				pack();
//			}
//		});
		
		menadzerMenu.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
				MenadzerPocetna mp = new MenadzerPocetna(MenadzerFrame.this, menadzer, app);

				setContentPane(mp);
				revalidate();
				mp.setVisible(true);

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
		
//		// Klikom na stavke menija otvaraju se odgovarajuce forme za prikaz
//		klijentMenu.addMenuListener(new SampleMenuListener() {
//
//			@Override
//			
//			public void actionPerformed(ActionEvent e) {
//
//				KlijentTabelaFrame ktf = new KlijentTabelaFrame(MenadzerFrame.this, klijenti);
//				ktf.setVisible(true);
//			}
//		});
		
        klijentMenu.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
				KlijentTabelaFrame ktf = new KlijentTabelaFrame(MenadzerFrame.this, app.getKfm(), app);

				setContentPane(ktf);
				revalidate();
				ktf.setVisible(true);

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

		// Klikom na stavke menija otvaraju se odgovarajuce forme za prikaz
		kozmeticarItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				KozmeticarTabelaFrame kktf = new KozmeticarTabelaFrame(MenadzerFrame.this, app.getkkfm(), app);
				setContentPane(kktf);
				revalidate();
				kktf.setVisible(true);

				//pack();
			}
		});
		recepcionarItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				RecepcionarTabelaFrame rtf = new RecepcionarTabelaFrame(MenadzerFrame.this, app.getrfm(), app);
				setContentPane(rtf);
				revalidate();
				rtf.setVisible(true);

				//pack();			
			}
		});
		menadzerItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				MenadzerTabelaFrame mtf = new MenadzerTabelaFrame(MenadzerFrame.this, app.getmfm(), app);
				setContentPane(mtf);
				revalidate();
				mtf.setVisible(true);

				//pack();	
			}
		});
		
		uslugaItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				KozmetickaUslugaTabelaFrame kutf = new KozmetickaUslugaTabelaFrame(MenadzerFrame.this, app.getkufm(), app);
				setContentPane(kutf);
				revalidate();
				kutf.setVisible(true);

				//pack();	
			}
		});
		
		tipUslugeItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				TipKozmetickeUslugeTabelaFrame tkutf = new TipKozmetickeUslugeTabelaFrame(MenadzerFrame.this, app.gettkufm(), app);
				setContentPane(tkutf);
				revalidate();
				tkutf.setVisible(true);

				//pack();	
			}
		});
		
		zakazanTretmanItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ZakazanaKozmetickaUslugaMenadzerTabelaFrame zkutf = new ZakazanaKozmetickaUslugaMenadzerTabelaFrame(MenadzerFrame.this, app.getzkufm(), app.getKfm(), app.getkkfm(),app);
				setContentPane(zkutf);
				revalidate();
				zkutf.setVisible(true);

				//pack();	
			}
		});
		
		izvjestajJedanItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				IzvjestajJedanTabelaFrame ijtf = new IzvjestajJedanTabelaFrame(MenadzerFrame.this,app.getkkfm(),app);
				setContentPane(ijtf);
				revalidate();
				ijtf.setVisible(true);

				//pack();	
			}
		});
		
		izvjestajDvaItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				IzvjestajDvaTabelaFrame idtf = new IzvjestajDvaTabelaFrame(MenadzerFrame.this,app);
				setContentPane(idtf);
				revalidate();
				idtf.setVisible(true);

				//pack();	
			}
		});

		izvjestajTriItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				IzvjestajTriTabelaFrame ittf = new IzvjestajTriTabelaFrame(MenadzerFrame.this,app.gettkufm(),app);
				setContentPane(ittf);
				revalidate();
				ittf.setVisible(true);

				//pack();	
			}
		});
		
		izvjestajCetiriItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				IzvjestajCetiriTabelaFrame ictf = new IzvjestajCetiriTabelaFrame(MenadzerFrame.this,app);
				setContentPane(ictf);
				revalidate();
				ictf.setVisible(true);

				//pack();	
			}
		});
		
		imeItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ImeFrame iff = new ImeFrame(MenadzerFrame.this,app.getksfm(),app);
				setContentPane(iff);
				revalidate();
				iff.setVisible(true);

				//pack();	
			}
		});
		
		vrijemeItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				RadnoVrijemeFrame rvf = new RadnoVrijemeFrame(MenadzerFrame.this,app.getksfm(),app);
				setContentPane(rvf);
				revalidate();
				rvf.setVisible(true);

				//pack();	
			}
		});
		
		karticaItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				KarticaLojalnostiFrame klf = new KarticaLojalnostiFrame(MenadzerFrame.this,app);
				setContentPane(klf);
				revalidate();
				klf.setVisible(true);

				//pack();	
			}
		});
		
		bonusItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				BonusFrame bf = new BonusFrame(MenadzerFrame.this,app);
				setContentPane(bf);
				revalidate();
				bf.setVisible(true);

				//pack();	
			}
		});
		
		plateItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				PlateFrame pf = new PlateFrame(MenadzerFrame.this,app);
				setContentPane(pf);
				revalidate();
				pf.setVisible(true);

				//pack();	
			}
		});
		
		prihodiIRashodiItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				PrihodiIRashodiFrame pirf = new PrihodiIRashodiFrame(MenadzerFrame.this,app);
				setContentPane(pirf);
				revalidate();
				pirf.setVisible(true);

				//pack();	
			}
		});
		
	}
	

class SampleMenuListener implements MenuListener {

    @Override
    public void menuSelected(MenuEvent e) {
        System.out.println("menuSelected");
    }

    @Override
    public void menuDeselected(MenuEvent e) {
        System.out.println("menuDeselected");
    }

    @Override
    public void menuCanceled(MenuEvent e) {
        System.out.println("menuCanceled");
    }
}
	
}

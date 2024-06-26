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

import fileMenager.KlijentFM;
import korisnici.Kozmeticar;
import meni.appSettings;
import model.ZakazaneKozmetickeUslugeTabelaModel;

public class KozmeticarFrame extends JFrame{
	
	private static final long serialVersionUID = 8456560429229699542L;
   	private appSettings app;
    private Kozmeticar kozmeticar;
	
	public KozmeticarFrame(appSettings app, Kozmeticar kozmeticar) {
		this.kozmeticar = kozmeticar;
		this.app = app;
		setSize(300, 200);
		setTitle("Kozmeticki salon - kozmeticar");
		
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
	}
	
	private void mainFrame() {
	    this.setTitle("Kozmetički salon - kozmeticar");
	    this.setSize(700, 500);
	    this.setLocationRelativeTo(null);
	    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    this.setResizable(true);

	    ImageIcon icon = new ImageIcon("img/icon.png");
	    this.setIconImage(icon.getImage());
	    
		
	    ImageIcon pictureIcon = new ImageIcon("img/beauty.png");
	    JLabel pictureLabel = new JLabel(pictureIcon);
	    
	    int desiredMainFrameWidth = 700;
	    int desiredMainFrameHeight = 600;
	    int desiredJLabelWidth = (int) (desiredMainFrameWidth * 0.3);
	    int desiredJLabelHeight = (int) (desiredMainFrameHeight * 0.4);
	    int desiredImageIconWidth = (int) (desiredJLabelWidth * 1);
	    int desiredImageIconHeight = (int) (desiredJLabelHeight * 1);
	
	    Image scaledImage = pictureIcon.getImage().getScaledInstance(desiredImageIconWidth, desiredImageIconHeight, Image.SCALE_SMOOTH);
	    ImageIcon scaledIcon = new ImageIcon(scaledImage);
	
	    pictureLabel.setPreferredSize(new Dimension(desiredJLabelWidth, desiredJLabelHeight));
	
	    pictureLabel.setIcon(scaledIcon);
	
	    //Container contentPane = this.getContentPane();
	    setLayout(new BorderLayout());
	    add(pictureLabel, BorderLayout.NORTH);
		JLabel label = new JLabel("Kozmeticar: " + kozmeticar.getIme() + " " + kozmeticar.getPrezime());
		label.setHorizontalAlignment(JLabel.CENTER);
		label.setVerticalAlignment(JLabel.TOP);
		add(label);
	    

	    initMainGUI();
	}
	
	
	
	private void initMainGUI() {
		
		JMenuBar mainMenu = new JMenuBar();

		JMenu menadzerMenu = new JMenu("Pocetna");
		JMenu rasporedMenu = new JMenu("Raspored");
		JMenu tretmaniMenu = new JMenu("Kozmeticke usluge");


		mainMenu.add(menadzerMenu);
		mainMenu.add(rasporedMenu);
		mainMenu.add(tretmaniMenu);
		
		this.setJMenuBar(mainMenu);


		// Klikom na stavke menija otvaraju se odgovarajuce forme za prikaz
		menadzerMenu.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
			    
				KozmeticarPocetna kpf = new KozmeticarPocetna(KozmeticarFrame.this, kozmeticar, app);

				setContentPane(kpf);
				revalidate();
				kpf.setVisible(true);

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
		
		rasporedMenu.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
				ZakazanaKozmetickaUslugaKozmeticarTabelaFrame zkutf = new ZakazanaKozmetickaUslugaKozmeticarTabelaFrame(KozmeticarFrame.this, app.getzkufm(), app.getKfm(), app.getkkfm(), kozmeticar, app);

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
		
		tretmaniMenu.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
				TipKozmetickeUslugeTabelaFrameKozmeticar tkutf = new TipKozmetickeUslugeTabelaFrameKozmeticar(KozmeticarFrame.this, app.gettkufm(), app.getKfm(), app.getkkfm(), kozmeticar, app);

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
		
        JLabel label = new JLabel("Kozmeticar: " + kozmeticar.getIme() + " " + kozmeticar.getPrezime());
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setVerticalAlignment(JLabel.TOP);
        add(label);

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

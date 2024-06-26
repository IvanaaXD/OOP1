package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;

import korisnici.Klijent;
import korisnici.Recepcionar;
import meni.appSettings;

public class KlijentPocetna  extends JPanel{
	
	private static final long serialVersionUID = 8456560429229699542L;
	
   	private appSettings app;
	private Klijent klijent;
	private JFrame parentFrame;
	
	public KlijentPocetna  (JFrame parentFrame, Klijent klijent,appSettings app) {
		this.parentFrame = parentFrame;
		this.app = app;
		this.klijent = klijent;
		  
		ImageIcon pictureIcon = new ImageIcon("img/beauty.png");
		JLabel pictureLabel = new JLabel(pictureIcon);

		int desiredMainFrameWidth = parentFrame.getWidth();
		int desiredMainFrameHeight = parentFrame.getHeight();
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

	    add(centerPanel, BorderLayout.CENTER);
	}
  }

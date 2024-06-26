package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JToolBar;

import korisnici.Kozmeticar;
import korisnici.Menadzer;
import meni.appSettings;

public class MenadzerPocetna extends JPanel {
	
	private static final long serialVersionUID = 8456560429229699542L;

	private appSettings app;
	private JFrame parentFrame;
	private Menadzer menadzer;

	public MenadzerPocetna  (JFrame parentFrame, Menadzer menadzer,appSettings app) {
		this.parentFrame = parentFrame;
		this.app = app;
		this.menadzer = menadzer;
		
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

		setLayout(new BorderLayout());
		add(pictureLabel, BorderLayout.NORTH);
		
		JLabel label = new JLabel("Menadzer: " + menadzer.getIme() + " " + menadzer.getPrezime());
		label.setHorizontalAlignment(JLabel.CENTER);
		label.setVerticalAlignment(JLabel.TOP);
		add(label);
	}
}

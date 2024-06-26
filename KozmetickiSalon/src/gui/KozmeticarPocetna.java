package gui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JToolBar;

import fileMenager.KlijentFM;
import fileMenager.KozmeticarFM;
import fileMenager.ZakazanaKozmetickaUslugaFM;
import korisnici.Kozmeticar;
import meni.appSettings;

public class KozmeticarPocetna extends JPanel{

	private static final long serialVersionUID = 8456560429229699542L;

	protected JToolBar mainToolbar = new JToolBar();
	protected JButton btnAdd = new JButton();
	protected JButton btnEdit = new JButton();
	protected JButton btnDelete = new JButton();
	protected JTextField tfSearch = new JTextField(20);
	protected JTable table;
	private appSettings app;
	private JFrame parentFrame;

	private Kozmeticar kozmeticar;

	public KozmeticarPocetna  (JFrame parentFrame, Kozmeticar kozmeticar,appSettings app) {
		this.parentFrame = parentFrame;
		this.app = app;
		this.kozmeticar = kozmeticar;

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

		JLabel label = new JLabel("Kozmeticar: " + kozmeticar.getIme() + " " + kozmeticar.getPrezime());
		label.setHorizontalAlignment(JLabel.CENTER);
		label.setVerticalAlignment(JLabel.TOP);
		add(label);
	}

}

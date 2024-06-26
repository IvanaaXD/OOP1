package gui;

import java.awt.BorderLayout;
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

import korisnici.Recepcionar;
import meni.appSettings;

public class RecepcionarPocetna extends JPanel{
	
	
	private static final long serialVersionUID = 8456560429229699542L;

	protected JToolBar mainToolbar = new JToolBar();
	protected JButton btnAdd = new JButton();
	protected JButton btnEdit = new JButton();
	protected JButton btnDelete = new JButton();
	protected JTextField tfSearch = new JTextField(20);
	protected JTable table;
	private appSettings app;
	//protected TableRowSorter<AbstractTableModel> tableSorter = new TableRowSorter<AbstractTableModel>();
	private JFrame parentFrame;

	private Recepcionar recepcionar;

	public RecepcionarPocetna  (JFrame parentFrame, Recepcionar recepcionar,appSettings app) {
		this.parentFrame = parentFrame;
		this.app = app;
		this.recepcionar = recepcionar;
		  
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
		
		JLabel label = new JLabel("Recepcionar: " + recepcionar.getIme() + " " + recepcionar.getPrezime());
		label.setHorizontalAlignment(JLabel.CENTER);
		label.setVerticalAlignment(JLabel.TOP);
		add(label);
	}
}

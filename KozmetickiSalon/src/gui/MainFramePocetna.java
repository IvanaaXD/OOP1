package gui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JToolBar;

import korisnici.Menadzer;
import meni.appSettings;
import net.miginfocom.swing.MigLayout;
import osobineTretmana.KozmetickiSalon;
import popup.KlijentPopUp;
import popup.KlijentPopUpRegistracija;

public class MainFramePocetna extends JPanel{

	private static final long serialVersionUID = 1L;
	
	protected JToolBar mainToolbar = new JToolBar();
	protected JButton btnAdd = new JButton();
	protected JButton btnEdit = new JButton();
	protected JButton btnDelete = new JButton();
	protected JTextField tfSearch = new JTextField(20);
	protected JTable table;
	private appSettings app;
	//protected TableRowSorter<AbstractTableModel> tableSorter = new TableRowSorter<AbstractTableModel>();
	private JFrame parentFrame;

    private MainFrame mainFrame;

	public MainFramePocetna  (JFrame parentFrame, appSettings app) {
		this.parentFrame = parentFrame;
		this.app = app;
	    
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

//	    Container contentPane = this.getContentPane();
	   setLayout(new BorderLayout());
	    int contentPaneWidth = 800;
	    int contentPaneHeight = 600;
	    setSize(contentPaneWidth, contentPaneHeight);	    
	    add(pictureLabel, BorderLayout.NORTH);
	
		KozmetickiSalon ks = new KozmetickiSalon();
		
		ArrayList<KozmetickiSalon> salon = new ArrayList<KozmetickiSalon> (app.getksfm().getKozmetickiSalon().values());
		ks = salon.get(0);
		System.out.println(ks);
		
		LocalTime pocetak = ks.getPocetakRadnogVremena();
		LocalTime kraj = ks.getKrajRadnogVremena();
		
		DateTimeFormatter formatterPocetak = DateTimeFormatter.ofPattern("HH:mm");
		DateTimeFormatter formatterKraj = DateTimeFormatter.ofPattern("HH:mm");
		
		String pocetakString = pocetak.format(formatterPocetak);
		String krajString = kraj.format(formatterKraj);
		
		String ime = ks.getNaziv();
		
		JLabel imeSalona = new JLabel("Salon: " + ime);

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

	    add(centerPanel, BorderLayout.CENTER);
	    
	    buttonSignUp.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				KlijentPopUp add = new KlijentPopUp(MainFramePocetna.this, app.getKfm(), app, null);
				add.setVisible(true);
			}
		});
	    
	    buttonLogIn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				loginDialog();
			}
		});
	    
	    //initMainGUI();
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
	
	private void initLoginGUI(JDialog dialog) {

		MigLayout layout = new MigLayout("wrap 2", "[][]", "[]20[][]20[]");
		dialog.setLayout(layout);

		JTextField tfKorisnickoIme = new JTextField(20);
		JPasswordField pfLozinka = new JPasswordField(20);
		JButton btnOk = new JButton("OK");
		JButton btnCancel = new JButton("Cancel");

		dialog.getRootPane().setDefaultButton(btnOk);

		dialog.add(new JLabel("Prijavite se: "), "span 2");
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
				System.out.println(korisnickoIme+" "+lozinka);
				// TO DO
				// Ukoliko nesto nije uneseno, obavestimo korisnika
				
				if (korisnickoIme.equals("") || lozinka.equals("")) {
					JOptionPane.showMessageDialog(dialog, "Niste unijeli sve podatke.", "Greška", JOptionPane.INFORMATION_MESSAGE);
		            // Show the main frame	
		            
				} else {
		            // Show the main frame
		            MainFramePocetna.this.setVisible(true);
				}
				dialog.setVisible(true);
				MainFramePocetna.this.setVisible(true);
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

}

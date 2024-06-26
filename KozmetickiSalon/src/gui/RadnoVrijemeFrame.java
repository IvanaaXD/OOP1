package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import fileMenager.KozmetickiSalonFM;
import meni.appSettings;
import osobineTretmana.KozmetickiSalon;

public class RadnoVrijemeFrame extends JPanel{

	private static final long serialVersionUID = -8251975220164083047L;
	private JFrame parentFrame;
	private appSettings app;
	private KozmetickiSalonFM salonfm;
	private KozmetickiSalon ks = new KozmetickiSalon();

	
	public RadnoVrijemeFrame(JFrame parentFrame, KozmetickiSalonFM salonfm, appSettings app) {
		this.parentFrame = parentFrame;
        this.app = app;
        this.salonfm = salonfm;

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

        ArrayList<KozmetickiSalon> salon = new ArrayList<KozmetickiSalon>(app.getksfm().getKozmetickiSalon().values());
        ks = salon.get(0);

        LocalTime pocetak = ks.getPocetakRadnogVremena();
        LocalTime kraj = ks.getKrajRadnogVremena();

        DateTimeFormatter formatterPocetak = DateTimeFormatter.ofPattern("HH:mm");
        DateTimeFormatter formatterKraj = DateTimeFormatter.ofPattern("HH:mm");

        String pocetakString = pocetak.format(formatterPocetak);
        String krajString = kraj.format(formatterKraj);

        JLabel lblRadnoVrijeme = new JLabel("Radno vrijeme: " + pocetakString + " - " + krajString);
        lblRadnoVrijeme.setHorizontalAlignment(JLabel.CENTER);

        JTextField textField1 = new JTextField(10);
        textField1.setText(pocetakString);
        JTextField textField2 = new JTextField(10);
        textField2.setText(krajString);
        JButton button = new JButton("Submit");

        JPanel topPanel = new JPanel(); // Container panel for imeSalona and pictureLabel
        topPanel.setLayout(new BorderLayout());
        topPanel.add(pictureLabel, BorderLayout.CENTER);

        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(5, 5, 5, 5);

        constraints.gridx = 0;
        constraints.gridy = 0;
        centerPanel.add(textField1, constraints);

        constraints.gridx = 1;
        constraints.gridy = 0;
        centerPanel.add(textField2, constraints);

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new BorderLayout());
        inputPanel.add(lblRadnoVrijeme, BorderLayout.NORTH);
        inputPanel.add(centerPanel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(button);

        setLayout(new BorderLayout());
        add(topPanel, BorderLayout.NORTH);
        add(inputPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    
        this.parentFrame.getRootPane().setDefaultButton(button);

        button.addActionListener(new ActionListener() {
    		@Override
    		public void actionPerformed(ActionEvent e) {
    			
    			String datumPocetakNovi = textField1.getText();
    			String datumKrajNovi = textField2.getText();
    			
    			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
    			LocalTime pocetakNovi = LocalTime.parse(datumPocetakNovi, formatter);
    			LocalTime krajNovi = LocalTime.parse(datumKrajNovi, formatter);
    			
    			ks.setPocetakRadnogVremena(pocetakNovi);
    			ks.setKrajRadnogVremena(krajNovi);
    			salonfm.saveData();
    			
    			LocalTime pocetak = ks.getPocetakRadnogVremena();
    			LocalTime kraj = ks.getKrajRadnogVremena();
    			
    			DateTimeFormatter formatterPocetak = DateTimeFormatter.ofPattern("HH:mm");
    			DateTimeFormatter formatterKraj = DateTimeFormatter.ofPattern("HH:mm");
    			
    			String pocetakString = pocetak.format(formatterPocetak);
    			String krajString = kraj.format(formatterKraj);
    			lblRadnoVrijeme.setText("Radno vrijeme: " + pocetakString + " - " + krajString);
    		}
    	});
    }

}

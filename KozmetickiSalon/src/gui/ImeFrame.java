package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import fileMenager.KozmetickiSalonFM;
import korisnici.Kozmeticar;
import meni.appSettings;
import model.KozmeticarTabelaModel;
import osobineTretmana.KozmetickiSalon;
import popup.KozmeticarPopUp;

public class ImeFrame extends JPanel{

	private static final long serialVersionUID = 6202223809899369720L;
	private JFrame parentFrame;
	private appSettings app;
	private KozmetickiSalonFM salonfm;
	private KozmetickiSalon ks = new KozmetickiSalon();

	
	public ImeFrame(JFrame parentFrame, KozmetickiSalonFM salonfm, appSettings app) {
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
        String ime = ks.getNaziv();      
        JLabel imeSalona = new JLabel("Salon: " + ime);
        imeSalona.setHorizontalAlignment(JLabel.CENTER);

        JTextField textField = new JTextField(20);
        textField.setText(ime);
        JButton button = new JButton("Submit");

        JPanel topPanel = new JPanel(); // Container panel for imeSalona and pictureLabel
        topPanel.setLayout(new BorderLayout());
        topPanel.add(pictureLabel, BorderLayout.CENTER);

        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(10, 10, 10, 10);

        constraints.gridx = 0;
        constraints.gridy = 0;
        centerPanel.add(textField, constraints);

        constraints.gridx = 0;
        constraints.gridy = 1;
        centerPanel.add(button, constraints);

        setLayout(new BorderLayout());
        add(topPanel, BorderLayout.NORTH);
        add(imeSalona, BorderLayout.CENTER);
        add(centerPanel, BorderLayout.SOUTH);
        
        this.parentFrame.getRootPane().setDefaultButton(button);
        
        button.addActionListener(new ActionListener() {
    		@Override
    		public void actionPerformed(ActionEvent e) {
    			
    			String novoIme = textField.getText();
    			ks.setNaziv(novoIme);
    			salonfm.saveData();
    	        imeSalona.setText("Salon: " + novoIme);
    		}
    	});
    }
	
}

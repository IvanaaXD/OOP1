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
import meni.appSettings;
import osobineTretmana.KozmetickiSalon;

public class BonusFrame extends JPanel{

	private static final long serialVersionUID = -8810824828263408791L;
	private JFrame parentFrame;
	private appSettings app;
	private KozmetickiSalonFM salonfm;
	private KozmetickiSalon ks = new KozmetickiSalon();

	
	public BonusFrame(JFrame parentFrame, appSettings app) {
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
                

        JLabel label1 = new JLabel("Broj izvrsenih tretmana:");
        JTextField textField1 = new JTextField(10);
        JLabel label2 = new JLabel("Iznos bonusa:");
        JTextField textField2 = new JTextField(10);
        JButton button = new JButton("Submit");

        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(5, 5, 5, 5);

        constraints.gridx = 0;
        constraints.gridy = 0;
        centerPanel.add(label1, constraints);

        constraints.gridx = 1;
        constraints.gridy = 0;
        centerPanel.add(textField1, constraints);

        constraints.gridx = 0;
        constraints.gridy = 1;
        centerPanel.add(label2, constraints);

        constraints.gridx = 1;
        constraints.gridy = 1;
        centerPanel.add(textField2, constraints);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(button);

        setLayout(new BorderLayout());
        add(pictureLabel, BorderLayout.NORTH);
        add(centerPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
        
        this.parentFrame.getRootPane().setDefaultButton(button);
        
        button.addActionListener(new ActionListener() {
    		@Override
    		public void actionPerformed(ActionEvent e) {
    			
    			String bonusString = textField2.getText();
    			String brojTretmanaString = textField1.getText();
    			
    			double bonus = Double.parseDouble(bonusString);
    			int brojTretmana = Integer.parseInt(brojTretmanaString);
    			
    			app.setBonus(bonus, brojTretmana);

    		}
    	});
    }

}

package gui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.jdatepicker.JDatePicker;
import org.jdatepicker.UtilDateModel;

import meni.appSettings;

public class PrihodiIRashodiFrame extends JPanel{

	private static final long serialVersionUID = -413931131838211502L;
	private JFrame parentFrame;
	private appSettings app;

	
	public PrihodiIRashodiFrame(JFrame parentFrame,appSettings app) {
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
        
		JLabel lblDatumOd = new JLabel("Od");
		add(lblDatumOd);
		
		UtilDateModel dateModelOd = new UtilDateModel();
		JDatePicker datePickerOd = new JDatePicker(dateModelOd);
		
        LocalDate today = LocalDate.now();
        Date todayDate = Date.from(today.atStartOfDay(ZoneId.systemDefault()).toInstant());
        dateModelOd.setValue(todayDate);		
        datePickerOd.setShowYearButtons(true);
 
		add(datePickerOd);
		
		JLabel lblDatumDo = new JLabel("Od");
		add(lblDatumDo);
		
		UtilDateModel dateModelDo = new UtilDateModel();
		JDatePicker datePickerDo = new JDatePicker(dateModelDo);
		dateModelDo.setValue(todayDate);		
		datePickerDo.setShowYearButtons(true);
		
		Date selectedDateOd = (Date) dateModelOd.getValue();
		LocalDate localDateOd = selectedDateOd.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		
		Date selectedDateDo = (Date) dateModelDo.getValue();
		LocalDate localDateDo = selectedDateDo.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        
        double prihodi = app.setKozmetickiSalonBilans(localDateOd,localDateDo);
        double rashodi = app.getRashodi(localDateOd,localDateDo);

		String prihodiString = String.valueOf(prihodi);
		String rashodiString = String.valueOf(rashodi);
        
        JLabel label1 = new JLabel("Prihodi: " + 0);
        JLabel label2 = new JLabel("Rashodi: " + 0);
        
        JButton submitButton = new JButton("Submit");

        JPanel datePickersPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
        datePickersPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));

        JPanel datePicker1Panel = new JPanel(new BorderLayout());
        JPanel datePicker2Panel = new JPanel(new BorderLayout());

        JLabel lblDatumOd1 = new JLabel("Od");
        JLabel lblDatumOd2 = new JLabel("Do");

        datePicker1Panel.add(lblDatumOd1, BorderLayout.WEST);
        datePicker1Panel.add(datePickerOd, BorderLayout.CENTER);

        datePicker2Panel.add(lblDatumOd2, BorderLayout.WEST);
        datePicker2Panel.add(datePickerDo, BorderLayout.CENTER);

        datePickersPanel.add(datePicker1Panel);
        datePickersPanel.add(datePicker2Panel);

        JPanel labelsPanel = new JPanel(new GridBagLayout());
        labelsPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0; 
        gbc.anchor = GridBagConstraints.CENTER;

        labelsPanel.add(label1, gbc);
        gbc.gridy++;
        gbc.insets = new Insets(0, 0, 10, 0); 

        labelsPanel.add(label2, gbc);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(pictureLabel, BorderLayout.NORTH);
        mainPanel.add(datePickersPanel, BorderLayout.CENTER);

        JPanel submitButtonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        submitButtonPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));

        Dimension buttonSize = new Dimension(100, submitButton.getPreferredSize().height);
        submitButton.setPreferredSize(buttonSize);

        submitButtonPanel.add(submitButton);

        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.add(labelsPanel, BorderLayout.CENTER);
        bottomPanel.add(submitButtonPanel, BorderLayout.PAGE_END);

        mainPanel.add(bottomPanel, BorderLayout.SOUTH);

        setLayout(new BorderLayout());
        add(mainPanel, BorderLayout.CENTER);


        this.parentFrame.getRootPane().setDefaultButton(submitButton);
        
        submitButton.addActionListener(new ActionListener() {
    		@Override
    		public void actionPerformed(ActionEvent e) {
    			
    			Date selectedDateOd = (Date) dateModelOd.getValue();
    			LocalDate localDateOd = selectedDateOd.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    			
    			Date selectedDateDo = (Date) dateModelDo.getValue();
    			LocalDate localDateDo = selectedDateDo.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    	        
    	        double prihodi = app.setKozmetickiSalonBilans(localDateOd,localDateDo);
    	        double rashodi = app.getRashodi(localDateOd,localDateDo);

    	        System.out.println(prihodi);
    	        System.out.println(rashodi);
    			String prihodiString = String.valueOf(prihodi);
    			String rashodiString = String.valueOf(rashodi);
    	        
    	        label1.setText("Prihodi: " + prihodiString);
    	        label2.setText("Rashodi: " + rashodiString);

    		}
    	});

}
}
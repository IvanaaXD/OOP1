package popup;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import fileMenager.TipKozmetickeUslugeFM;
import gui.TipKozmetickeUslugeTabelaFrame;
import meni.appSettings;
import net.miginfocom.swing.MigLayout;
import osobineTretmana.TipKozmetickeUsluge;

public class TipKozmetickeUslugePopUp extends JDialog{

	private static final long serialVersionUID = -5247231764310200252L;
	
	private TipKozmetickeUslugeFM tipKozmetickeUsluge;
	private TipKozmetickeUsluge editK;
	private JPanel parent;
	private appSettings app;
    private String stariTip;
	
	// Jedan isti dijalog za Add i Edit
	
	public TipKozmetickeUslugePopUp(JPanel parent, TipKozmetickeUslugeFM tipKozmetickeUsluge, appSettings app,TipKozmetickeUsluge editUsluga) {

		if (editUsluga != null) {
			setTitle("Izmjena tipa kozmeticke usluge");
		} else {
			setTitle("Dodavanje tipa kozmeticke usluge");
		}
		
		this.parent = parent;
		this.tipKozmetickeUsluge = tipKozmetickeUsluge;
		this.app = app;
		this.editK = editUsluga;

		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setResizable(false);
		initGUI(app);

		pack();
		this.setLocationRelativeTo(null);

	}

	private void initGUI(appSettings app) {
		MigLayout ml = new MigLayout("wrap 3", "[][][]", "[]10[]10[]10[]20[]");
		setLayout(ml);
		this.setLocationRelativeTo(null);
					
//boolean obrisan, String tip usluge, String trajanje, String naziv usluge, double cijena	        
		
		JLabel lblTip = new JLabel("Tip usluge");
		add(lblTip);

		JTextField txtTip = new JTextField(20);
		add(txtTip, "span 2");
		
		JLabel lblTrajanje = new JLabel("Trajanje");
		add(lblTrajanje);

		JTextField txtTrajanje = new JTextField(20);
		add(txtTrajanje, "span 2");
		
		JLabel lblNaziv = new JLabel("Naziv usluge");
		add(lblNaziv);

//		JTextField txtNaziv = new JTextField(20);
//		add(txtNaziv, "span 2");
        
		String nazivi = "";
		nazivi = app.addKozmetickaUslugaUKozmeticara();

//		JComboBox<String> naziviComboBox = new JComboBox<>(nazivi.split(","));
//		add(naziviComboBox);
//
//		naziviComboBox.setSelectedItem(nazivi);
		
		String[] naziviArray = nazivi.split(",");
		
		//String [] options = {"BEZ_KVALIFIKACIJE", "OPSTA", "STRUCNA", "AKADEMSKA", "STRUKOVNA"};
		JComboBox<String> dropdown = new JComboBox<>(naziviArray);
		add(dropdown, "span 2");
       
		JLabel lblCijena = new JLabel("Cijena usluge");
		add(lblCijena);

		JTextField txtCijena = new JTextField(20);
		add(txtCijena, "span 2");

		add(new JLabel());

		JButton btnCancel = new JButton("Cancel");
		add(btnCancel);

		JButton btnOK = new JButton("OK");
		add(btnOK);
		
		TipKozmetickeUslugePopUp.this.getRootPane().setDefaultButton(btnOK);


		if (editK != null) {
			// popuni polja vrednostima
				        
			//txtNaziv.setText(editK.getNazivUsluge());
			txtTip.setText(editK.getTipUsluge());
			txtTrajanje.setText(editK.getTrajanje());
			
			Double cijenaa = editK.getCijena();
			txtCijena.setText(Double.toString(cijenaa));
			
			for (int i = 0; i < naziviArray.length; i++) {
				if (naziviArray[i].equals(editK.getNazivUsluge())) {
					dropdown.setSelectedItem(naziviArray[i]);
				}
			}

		}

		btnOK.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				//String naziv = txtNaziv.getText().trim();
				String tip = txtTip.getText().trim();
				String trajanje = txtTrajanje.getText().trim();
				String cijena = txtCijena.getText().trim();
				
				double cijenaDouble = Double.parseDouble(cijena);
				String naziv = (String) dropdown.getSelectedItem();


				// odve se odvaja GUI od funkcionalnosti Manager-a
				// ne mesati logiku app i funkcionalnosti sa GUI-om !
				if (editK != null) {
					stariTip = editK.getTipUsluge();
					app.changeTipKozmetickeUslugeTip(stariTip, tip);
					app.changeTipKozmetickeUslugeNaziv(tip,naziv);
					app.changeTipKozmetickeUslugeTrajanje(tip, trajanje);
					app.changeTipKozmetickeUslugeCijena(tip, cijenaDouble);
					
					
				} else {
					app.addTipKozmetickeUsluge(tip, trajanje, naziv, cijenaDouble);
				}
				((TipKozmetickeUslugeTabelaFrame)parent).refreshData();

				TipKozmetickeUslugePopUp.this.dispose();

			}
			
		});

		btnCancel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				TipKozmetickeUslugePopUp.this.dispose();
			}
		});
	}
	
}

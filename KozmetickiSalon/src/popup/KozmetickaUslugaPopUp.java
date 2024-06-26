package popup;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import fileMenager.KozmetickaUslugaFM;
import gui.KozmetickaUslugaTabelaFrame;

import meni.appSettings;
import net.miginfocom.swing.MigLayout;
import osobineTretmana.KozmetickaUsluga;

public class KozmetickaUslugaPopUp extends JDialog{
	
	private static final long serialVersionUID = -5247231764310200252L;
	
	private KozmetickaUslugaFM kozmetickeUsluge;
	private KozmetickaUsluga editK;
	private JPanel parent;
	private appSettings app;
    private String stariNaziv;
	
	// Jedan isti dijalog za Add i Edit
	
	public KozmetickaUslugaPopUp(JPanel parent, KozmetickaUslugaFM kozmetickeUsluge, appSettings app,KozmetickaUsluga editUsluga) {

		if (editUsluga != null) {
			setTitle("Izmjena kozmeticke usluge");
		} else {
			setTitle("Dodavanje kozmeticke usluge");
		}
		
		this.parent = parent;
		this.kozmetickeUsluge = kozmetickeUsluge;
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
					
//boolean obrisan, String naziv usluge	        
		
		JLabel lblNaziv = new JLabel("Naziv usluge");
		add(lblNaziv);

		JTextField txtNaziv = new JTextField(20);
		add(txtNaziv, "span 2");

		add(new JLabel());

		JButton btnCancel = new JButton("Cancel");
		add(btnCancel);

		JButton btnOK = new JButton("OK");
		add(btnOK);

		if (editK != null) {
			// popuni polja vrednostima
				        
			txtNaziv.setText(editK.getNazivUsluge());

		}
		
		KozmetickaUslugaPopUp.this.getRootPane().setDefaultButton(btnOK);


		btnOK.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String naziv = txtNaziv.getText().trim();
				
				// odve se odvaja GUI od funkcionalnosti Manager-a
				// ne mesati logiku app i funkcionalnosti sa GUI-om !
				if (editK != null) {
					stariNaziv = editK.getNazivUsluge();
					app.changeKozmetickaUslugaNaziv(stariNaziv, naziv);
					
				} else {
					app.addKozmetickaUsluga(naziv, "");
				}
				((KozmetickaUslugaTabelaFrame)parent).refreshData();

				KozmetickaUslugaPopUp.this.dispose();

			}
			
		});

		btnCancel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				KozmetickaUslugaPopUp.this.dispose();
			}
		});
	}

}

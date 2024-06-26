package popup;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import fileMenager.ZakazanaKozmetickaUslugaFM;
import gui.ZakazanaKozmetickaUslugaMenadzerTabelaFrame;
import gui.ZakazanaKozmetickaUslugaRecepcionarTabelaFrame;
import meni.appSettings;
import net.miginfocom.swing.MigLayout;
import osobineTretmana.Status;
import osobineTretmana.ZakazanaKozmetickaUsluga;

public class ZakazanaKozmetickaUslugaChangeStatusPopUp extends JDialog{

	private static final long serialVersionUID = 1L;
	
	
	private ZakazanaKozmetickaUslugaFM zakazaneKozmetickeUsluge;
	private ZakazanaKozmetickaUsluga editK;
	private JPanel parent;
	private appSettings app;
    private String stariTip;
	
	// Jedan isti dijalog za Add i Edit
	
	public ZakazanaKozmetickaUslugaChangeStatusPopUp(JPanel parent, ZakazanaKozmetickaUslugaFM zakazaneKozmetickeUsluge, appSettings app,ZakazanaKozmetickaUsluga editUsluga) {

		if (editUsluga != null) {
			setTitle("Izmjena zakazanog tretmana");
		} else {
			setTitle("Dodavanje zakazanog tretmana ");
		}
		
		this.parent = parent;
		this.zakazaneKozmetickeUsluge = zakazaneKozmetickeUsluge;
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
		
		JLabel lblStatus = new JLabel("Status usluge");
		add(lblStatus);
 
        Status[] statuses = Status.values();

        DefaultComboBoxModel<Status> model = new DefaultComboBoxModel<>(statuses);
        JComboBox<Status> comboBox = new JComboBox<>(model);
        
        add(comboBox);

		add(new JLabel());

		JButton btnCancel = new JButton("Cancel");
		add(btnCancel);

		JButton btnOK = new JButton("OK");
		add(btnOK);
		
		ZakazanaKozmetickaUslugaChangeStatusPopUp.this.getRootPane().setDefaultButton(btnOK);

		if (editK != null) {
			// popuni polja vrednostima
			Status status = editK.getStanje();
			comboBox.setSelectedItem(status);

		}

		btnOK.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				//String naziv = txtNaziv.getText().trim();
				Status selectedStatus = (Status) comboBox.getSelectedItem();
				String selectedStatusString = selectedStatus.toString();
				// odve se odvaja GUI od funkcionalnosti Manager-a
				// ne mesati logiku app i funkcionalnosti sa GUI-om !
				if (editK != null) {

					app.changeZakazanaKozmetickaUslugaStatus(editK.getId(), selectedStatusString);

				}
				try {
				    ((ZakazanaKozmetickaUslugaRecepcionarTabelaFrame)parent).refreshData();
				} catch (Exception e1) {
				    ((ZakazanaKozmetickaUslugaMenadzerTabelaFrame)parent).refreshData();

				}
				ZakazanaKozmetickaUslugaChangeStatusPopUp.this.dispose();

			}
			
		});

		btnCancel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				ZakazanaKozmetickaUslugaChangeStatusPopUp.this.dispose();
			}
		});
	}

}

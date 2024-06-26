package gui;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.ListSelectionModel;

import fileMenager.KozmeticarFM;
import korisnici.Kozmeticar;
import meni.appSettings;
import model.KozmeticarTabelaModel;
import net.miginfocom.swing.MigLayout;
import popup.KozmeticarPopUp;

public class KozmeticarTabelaFrame extends JPanel {
	
	private static final long serialVersionUID = 8456560429229699542L;
	
	protected JToolBar mainToolbar = new JToolBar();
	protected JButton btnAdd = new JButton();
	protected JButton btnEdit = new JButton();
	protected JButton btnDelete = new JButton();
	protected JTextField tfSearch = new JTextField(20);
	protected JTable table;
	private appSettings app;
	private JFrame parentFrame;

	private KozmeticarFM kozmeticarifm;

	public KozmeticarTabelaFrame(JFrame parentFrame, KozmeticarFM kozmeticarifm, appSettings app) {
		this.kozmeticarifm = kozmeticarifm;
		this.parentFrame = parentFrame;	
		this.app = app;
		
		btnAdd.setText("Add");
		btnEdit.setText("Edit");
		btnDelete.setText("Delete");
		
		setLayout(new MigLayout("wrap 3"));

		mainToolbar.add(btnAdd);
		mainToolbar.add(btnEdit);
		mainToolbar.add(btnDelete);

		// Disable toolbar floating
		mainToolbar.setFloatable(false);	
		add(mainToolbar,  "dock north");
		
		// podesavanje tabele

		ArrayList<Kozmeticar> kozmeticari = new ArrayList<Kozmeticar> (kozmeticarifm.getKozmeticar().values());
		table = new JTable(new KozmeticarTabelaModel(kozmeticari, app.getkufm()));	
		
		Dimension parentSize = parentFrame.getContentPane().getSize();
		table.setPreferredScrollableViewportSize(parentSize);
		
		table.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.getTableHeader().setReorderingAllowed(false);
		
		JScrollPane sc = new JScrollPane(table);
		add(sc, "dock center");

		initActions();
	}

	private void initActions() {
		btnAdd.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				app.printKozmeticari();
				KozmeticarPopUp add = new KozmeticarPopUp(KozmeticarTabelaFrame.this, kozmeticarifm, app, null);
				add.setVisible(true);
				refreshData();
			}
		});
		
		btnEdit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int red = table.getSelectedRow();
				if(red == -1) {
					JOptionPane.showMessageDialog(null, "Morate odabrati red u tabeli.", "Greska", JOptionPane.WARNING_MESSAGE);
				}else {
					//int id = Integer.parseInt(table.getValueAt(red, 0).toString());
					Kozmeticar k = kozmeticarifm.PronadjiKozmeticaraPoId(red);
					System.out.println(k);
					if(k != null) {
						
						KozmeticarPopUp add = new KozmeticarPopUp(KozmeticarTabelaFrame.this, kozmeticarifm, app, k);
						add.setVisible(true);

					}else {
						JOptionPane.showMessageDialog(null, "Nije moguce pronaci odabranog kozmeticara!", "Greska", JOptionPane.ERROR_MESSAGE);
					}
				}	

			}
		});
		btnDelete.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int red = table.getSelectedRow();
				if(red == -1) {
					JOptionPane.showMessageDialog(null, "Morate odabrati red u tabeli.", "Greska", JOptionPane.WARNING_MESSAGE);
				}else {
					//int id = Integer.parseInt(table.getValueAt(red, 0).toString());
					Kozmeticar k = kozmeticarifm.PronadjiKozmeticaraPoId(red);
					if(k != null) {
						int izbor = JOptionPane.showConfirmDialog(null,"Da li ste sigurni da zelite da obrisete kozmeticara?", 
								k.getIme() + " "+k.getPrezime() +" - Potvrda brisanja", JOptionPane.YES_NO_OPTION);
						if(izbor == JOptionPane.YES_OPTION) {
							k.setObrisan(true);
							kozmeticarifm.saveData();
							refreshData();
						}
					}else {
						JOptionPane.showMessageDialog(null, "Nije moguce pronaci odabranog kozmeticara!", "Greska", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
	}

	public void refreshData() {
		ArrayList<Kozmeticar> kozmeticari = new ArrayList<Kozmeticar> (kozmeticarifm.getKozmeticar().values());

		KozmeticarTabelaModel ktm = new KozmeticarTabelaModel(kozmeticari, app.getkufm());
		
		table.setModel(ktm);	
	}

}

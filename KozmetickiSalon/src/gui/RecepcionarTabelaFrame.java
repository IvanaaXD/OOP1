package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.util.ArrayList;

import javax.swing.ImageIcon;
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
import fileMenager.RecepcionarFM;
import korisnici.Klijent;
import korisnici.Kozmeticar;
import korisnici.Recepcionar;
import meni.appSettings;
import model.KlijentTabelaModel;
import model.KozmeticarTabelaModel;
import model.RecepcionarTabelaModel;
import net.miginfocom.swing.MigLayout;
import popup.KozmeticarPopUp;
import popup.RecepcionariPopUp;

public class RecepcionarTabelaFrame extends JPanel{

	private static final long serialVersionUID = 8456560429229699542L;

	//private StudentManager studentMng;
	
	protected JToolBar mainToolbar = new JToolBar();
	protected JButton btnAdd = new JButton();
	protected JButton btnEdit = new JButton();
	protected JButton btnDelete = new JButton();
	protected JTextField tfSearch = new JTextField(20);
	protected JTable table;
	private appSettings app;
	//protected TableRowSorter<AbstractTableModel> tableSorter = new TableRowSorter<AbstractTableModel>();
	private JFrame parentFrame;

	private RecepcionarFM recepcionarifm;

	public RecepcionarTabelaFrame(JFrame parentFrame, RecepcionarFM recepcionarifm, appSettings app) {
		this.recepcionarifm = recepcionarifm;
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

		ArrayList<Recepcionar> recepcionari = new ArrayList<Recepcionar> (recepcionarifm.getRecepcionar().values());
		table = new JTable(new RecepcionarTabelaModel(recepcionari));	
		
		Dimension parentSize = parentFrame.getContentPane().getSize();
		table.setPreferredScrollableViewportSize(parentSize);
		
		table.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.getTableHeader().setReorderingAllowed(false);
		
		JScrollPane sc = new JScrollPane(table);
		add(sc, BorderLayout.CENTER);

		initActions();
	}

	private void initActions() {
		btnAdd.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				app.printKozmeticari();
				RecepcionariPopUp add = new RecepcionariPopUp(RecepcionarTabelaFrame.this, recepcionarifm, app, null);
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
					Recepcionar k = recepcionarifm.PronadjiRecepcionaraPoId(red);
					if(k != null) {
						
						RecepcionariPopUp add = new RecepcionariPopUp(RecepcionarTabelaFrame.this, recepcionarifm, app, k);
						add.setVisible(true);

					}else {
						JOptionPane.showMessageDialog(null, "Nije moguce pronaci odabranog recepcionara!", "Greska", JOptionPane.ERROR_MESSAGE);
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
					Recepcionar k = recepcionarifm.PronadjiRecepcionaraPoId(red);
					if(k != null) {
						int izbor = JOptionPane.showConfirmDialog(null,"Da li ste sigurni da zelite da obrisete recepcionara?", 
								k.getIme() + " "+k.getPrezime() +" - Potvrda brisanja", JOptionPane.YES_NO_OPTION);
						if(izbor == JOptionPane.YES_OPTION) {
							k.setObrisan(true);
							recepcionarifm.saveData();
							refreshData();
						}
					}else {
						JOptionPane.showMessageDialog(null, "Nije moguce pronaci odabranog recepcionara!", "Greska", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
	}

	public void refreshData() {
		ArrayList<Recepcionar> recepcionari = new ArrayList<Recepcionar> (recepcionarifm.getRecepcionar().values());

		RecepcionarTabelaModel rtm = new RecepcionarTabelaModel(recepcionari);
		
		table.setModel(rtm);	

	}

}

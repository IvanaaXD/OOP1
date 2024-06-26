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
import model.KozmeticarKlijentTabelaModel;
import model.KozmeticarTabelaModel;
import net.miginfocom.swing.MigLayout;
import popup.KozmeticarPopUp;

public class KozmeticarTabelaFrameKlijent extends JPanel{

	private static final long serialVersionUID = 4505729426061108759L;

	
	protected JToolBar mainToolbar = new JToolBar();
	protected JButton btnAdd = new JButton();
	protected JButton btnEdit = new JButton();
	protected JButton btnDelete = new JButton();
	protected JTextField tfSearch = new JTextField(20);
	protected JTable table;
	private appSettings app;
	private JFrame parentFrame;
	private ArrayList<Kozmeticar> kkkkk = new ArrayList<Kozmeticar>();

	private KozmeticarFM kozmeticarifm;

	public KozmeticarTabelaFrameKlijent(JFrame parentFrame, KozmeticarFM kozmeticarifm, appSettings app) {
		this.kozmeticarifm = kozmeticarifm;
		this.parentFrame = parentFrame;	
		this.app = app;
		
		// podesavanje tabele

		ArrayList<Kozmeticar> kozmeticari = new ArrayList<Kozmeticar> (kozmeticarifm.getKozmeticar().values());
		
		for (Kozmeticar k: kozmeticari) {
			if (!k.getObrisan()) {
				kkkkk.add(k);
			}
		}
		
		table = new JTable(new KozmeticarKlijentTabelaModel(kkkkk, app.getkufm()));	
		
		Dimension parentSize = parentFrame.getContentPane().getSize();
		table.setPreferredScrollableViewportSize(parentSize);
		
		table.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.getTableHeader().setReorderingAllowed(false);

		JScrollPane sc = new JScrollPane(table);
		add(sc, "dock center");
	}

}

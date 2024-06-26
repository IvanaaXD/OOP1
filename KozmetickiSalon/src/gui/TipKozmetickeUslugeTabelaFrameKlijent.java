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

import fileMenager.TipKozmetickeUslugeFM;
import meni.appSettings;
import model.TipKozmetickeUslugeKlijentTabelaModel;
import net.miginfocom.swing.MigLayout;
import osobineTretmana.TipKozmetickeUsluge;
import popup.TipKozmetickeUslugePopUp;

public class TipKozmetickeUslugeTabelaFrameKlijent extends JPanel{

	private static final long serialVersionUID = 1019513219949092456L;
	
	protected JToolBar mainToolbar = new JToolBar();
	protected JButton btnAdd = new JButton();
	protected JButton btnEdit = new JButton();
	protected JButton btnDelete = new JButton();
	protected JTextField tfSearch = new JTextField(20);
	protected JTable table;
	private appSettings app;
	//protected TableRowSorter<AbstractTableModel> tableSorter = new TableRowSorter<AbstractTableModel>();
	private JFrame parentFrame;

	private TipKozmetickeUslugeFM tipUslugefm;

	public TipKozmetickeUslugeTabelaFrameKlijent(JFrame parentFrame, TipKozmetickeUslugeFM tipUslugefm, appSettings app) {
		this.tipUslugefm = tipUslugefm;
		this.parentFrame = parentFrame;	
		this.app = app;

		// podesavanje tabele

		ArrayList<TipKozmetickeUsluge> tipUsluge = new ArrayList<TipKozmetickeUsluge> (tipUslugefm.getTipKozmetickeUsluge().values());
		table = new JTable(new TipKozmetickeUslugeKlijentTabelaModel(tipUsluge));	
		
		Dimension parentSize = parentFrame.getContentPane().getSize();
		table.setPreferredScrollableViewportSize(parentSize);
		
		table.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.getTableHeader().setReorderingAllowed(false);
		
		JScrollPane sc = new JScrollPane(table);
		add(sc, "dock center");

	}

}

package gui;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.ListSelectionModel;

import org.jdatepicker.JDatePicker;
import org.jdatepicker.UtilDateModel;

import fileMenager.KozmeticarFM;
import korisnici.Klijent;
import meni.appSettings;
import model.IzvjestajCetiriTabelaModel;
import model.IzvjestajDvaTabelaModel;
import osobineTretmana.Status;

public class IzvjestajCetiriTabelaFrame extends JPanel{

	private static final long serialVersionUID = 1L;
	protected JToolBar mainToolbar = new JToolBar();
	protected JButton btnAdd = new JButton();
	protected JButton btnEdit = new JButton();
	protected JButton btnDelete = new JButton();
	protected JTextField tfSearch = new JTextField(20);
	protected JTable table;
	private appSettings app;

	//protected TableRowSorter<AbstractTableModel> tableSorter = new TableRowSorter<AbstractTableModel>();
	private JFrame parentFrame;

	private KozmeticarFM kozmeticarifm;

	public IzvjestajCetiriTabelaFrame(JFrame parentFrame, appSettings app) {
		this.parentFrame = parentFrame;	
		this.app = app;
		
		
		ArrayList<Klijent> nasla = new ArrayList<Klijent>(app.izvjestajCetiri());

		table = new JTable(new IzvjestajCetiriTabelaModel(nasla));	
		
		Dimension parentSize = parentFrame.getContentPane().getSize();
		table.setPreferredScrollableViewportSize(parentSize);
		
		table.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.getTableHeader().setReorderingAllowed(false);

		JScrollPane sc = new JScrollPane(table);
		add(sc, "dock center");

	}

}

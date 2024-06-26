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
import fileMenager.TipKozmetickeUslugeFM;
import korisnici.Kozmeticar;
import meni.appSettings;
import model.IzvjestajJedanTabelaModel;
import model.IzvjestajTriTabelaModel;
import osobineTretmana.TipKozmetickeUsluge;

public class IzvjestajTriTabelaFrame extends JPanel{
	
	private static final long serialVersionUID = 1L;
	protected JToolBar mainToolbar = new JToolBar();
	protected JButton btnAdd = new JButton();
	protected JButton btnEdit = new JButton();
	protected JButton btnDelete = new JButton();
	protected JTextField tfSearch = new JTextField(20);
	protected JTable table;
	private appSettings app;
	private ArrayList<TipKozmetickeUsluge> tipoviUsluge = new ArrayList<TipKozmetickeUsluge>() ;


	//protected TableRowSorter<AbstractTableModel> tableSorter = new TableRowSorter<AbstractTableModel>();
	private JFrame parentFrame;

	private KozmeticarFM kozmeticarifm;
	private TipKozmetickeUslugeFM tipovifm;

	public IzvjestajTriTabelaFrame(JFrame parentFrame,TipKozmetickeUslugeFM tipovifm, appSettings app) {
		this.parentFrame = parentFrame;	
		this.app = app;
		this.tipovifm = tipovifm;
				
		tipoviUsluge.addAll(tipovifm.getTipKozmetickeUsluge().values());

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
 
		add(datePickerDo);
		
		add(new JLabel());
		
		
		Date selectedDateOd = (Date) dateModelOd.getValue();
		LocalDate localDateOd = selectedDateOd.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		
		Date selectedDateDo = (Date) dateModelDo.getValue();
		LocalDate localDateDo = selectedDateDo.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		// podesavanje tabele
		
		if (selectedDateDo != null && selectedDateOd != null) {
			
		}
		HashMap<TipKozmetickeUsluge, ArrayList<Double>> nasla = new HashMap<TipKozmetickeUsluge, ArrayList<Double>>(app.izvjestajTri(localDateOd, localDateDo));

		table = new JTable(new IzvjestajTriTabelaModel(tipoviUsluge, nasla));	
		
		Dimension parentSize = parentFrame.getContentPane().getSize();
		table.setPreferredScrollableViewportSize(parentSize);
		
		table.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.getTableHeader().setReorderingAllowed(false);
		
		JScrollPane sc = new JScrollPane(table);
		add(sc, "dock center");
		
		datePickerOd.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		    	
				Date selectedDateOd = (Date) dateModelOd.getValue();
				LocalDate localDateOd = selectedDateOd.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
				
				Date selectedDateDo = (Date) dateModelDo.getValue();
				LocalDate localDateDo = selectedDateDo.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
				
				HashMap<TipKozmetickeUsluge, ArrayList<Double>> nasla = new HashMap<TipKozmetickeUsluge, ArrayList<Double>>(app.izvjestajTri(localDateOd, localDateDo));
				IzvjestajTriTabelaModel juhu = new IzvjestajTriTabelaModel(tipoviUsluge, nasla);	
				table.setModel(juhu);
		    }
		});
		
		datePickerDo.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		    	
				Date selectedDateOd = (Date) dateModelOd.getValue();
				LocalDate localDateOd = selectedDateOd.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
				
				Date selectedDateDo = (Date) dateModelDo.getValue();
				LocalDate localDateDo = selectedDateDo.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
				
				HashMap<TipKozmetickeUsluge, ArrayList<Double>> nasla = new HashMap<TipKozmetickeUsluge, ArrayList<Double>>(app.izvjestajTri(localDateOd, localDateDo));
				IzvjestajTriTabelaModel juhu = new IzvjestajTriTabelaModel(tipoviUsluge, nasla);	
				table.setModel(juhu);
		    }
		});

	}


}

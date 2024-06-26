package gui;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;

import org.jdatepicker.JDatePicker;
import org.jdatepicker.UtilDateModel;
import org.knowm.xchart.PieChart;
import org.knowm.xchart.PieChartBuilder;
import org.knowm.xchart.XChartPanel;
import org.knowm.xchart.style.PieStyler;

import fileMenager.KozmeticarFM;
import korisnici.Kozmeticar;
import meni.appSettings;
import model.IzvjestajDvaTabelaModel;
import model.IzvjestajJedanTabelaModel;
import osobineTretmana.Status;

public class IzvjestajDvaTabelaFrame extends JPanel{

	private static final long serialVersionUID = 1L;
	protected JToolBar mainToolbar = new JToolBar();
	protected JButton btnChart = new JButton();
	protected JTextField tfSearch = new JTextField(20);
	protected JTable table;
	private appSettings app;
	private ArrayList<Status> statusi = new ArrayList<Status>();

	//protected TableRowSorter<AbstractTableModel> tableSorter = new TableRowSorter<AbstractTableModel>();
	private JFrame parentFrame;

	private KozmeticarFM kozmeticarifm;

	public IzvjestajDvaTabelaFrame(JFrame parentFrame, appSettings app) {
		this.parentFrame = parentFrame;	
		this.app = app;
		
		for (Status status : Status.values()) {
		    statusi.add(status);
		}
		
		btnChart.setText("Grafik");
		mainToolbar.add(btnChart);
		mainToolbar.setFloatable(false);	
		add(mainToolbar,  "dock north");
		
				
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
		
		HashMap<Status, Integer> nasla = new HashMap<Status, Integer>(app.izvjestajDva(localDateOd, localDateDo));

		table = new JTable(new IzvjestajDvaTabelaModel(statusi, nasla));	
		
		Dimension parentSize = parentFrame.getContentPane().getSize();
		table.setPreferredScrollableViewportSize(parentSize);
		
		table.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.getTableHeader().setReorderingAllowed(false);
		
       // add(table);

		// podesavanje manuelnog sortera tabele, potrebno i za pretragu
		//tableSorter.setModel((AbstractTableModel) table.getModel());
		//table.setRowSorter(tableSorter);
		JScrollPane sc = new JScrollPane(table);
		add(sc, "dock center");
		
		datePickerOd.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		    	
				Date selectedDateOd = (Date) dateModelOd.getValue();
				LocalDate localDateOd = selectedDateOd.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
				
				Date selectedDateDo = (Date) dateModelDo.getValue();
				LocalDate localDateDo = selectedDateDo.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
				
				HashMap<Status, Integer> nasla = new HashMap<Status, Integer>(app.izvjestajDva(localDateOd, localDateDo));
				IzvjestajDvaTabelaModel juhu = new IzvjestajDvaTabelaModel(statusi, nasla);	
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
				
				HashMap<Status, Integer> nasla = new HashMap<Status, Integer>(app.izvjestajDva(localDateOd, localDateDo));
				IzvjestajDvaTabelaModel juhu = new IzvjestajDvaTabelaModel(statusi, nasla);	
				table.setModel(juhu);
		    }
		});
		
	
		btnChart.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		        List<String> categories = new ArrayList<>();
		        List<Integer> values = new ArrayList<>();
	
		        HashMap<Status, Integer> jjj = new HashMap<Status, Integer>(app.izvjestajDvaGrafik());
		        
				for (Map.Entry<Status, Integer> k : jjj.entrySet()) {
					categories.add(k.getKey().toString());
					values.add(k.getValue());
		        }
		        
	
		        PieChart chart = new PieChartBuilder().width(600).height(400).title("Status kozmetickih tretmana u posljednjih 30 dana").build();
		        chart.getStyler().setLegendVisible(true);
		        chart.getStyler().setLegendPosition(PieStyler.LegendPosition.OutsideS);
	
		        for (int i = 0; i < categories.size(); i++) {
		            chart.addSeries(categories.get(i), values.get(i));
		        }
	
		        displayChart(chart);
		    }
		});}
	
	
	private static void displayChart(PieChart chart) {
	    SwingUtilities.invokeLater(() -> {
	        JFrame frame = new JFrame("Chart");
	        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	        XChartPanel<PieChart> chartPanel = new XChartPanel<>(chart);
	        frame.add(chartPanel);
	        frame.pack();
	        frame.setVisible(true);
		    frame.setLocationRelativeTo(null);
	
	    });
	}

}

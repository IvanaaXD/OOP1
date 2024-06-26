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
import org.knowm.xchart.XYChart;
import org.knowm.xchart.XYChartBuilder;
import org.knowm.xchart.style.PieStyler;
import org.knowm.xchart.style.Styler;

import fileMenager.KozmeticarFM;
import korisnici.Kozmeticar;
import meni.appSettings;
import model.IzvjestajJedanTabelaModel;
import osobineTretmana.KozmetickaUsluga;

public class IzvjestajJedanTabelaFrame extends JPanel{

	private static final long serialVersionUID = 4200859934522658920L;

	protected JToolBar mainToolbar = new JToolBar();
	protected JButton btnChart1 = new JButton();
	protected JButton btnChart2 = new JButton();
	protected JTextField tfSearch = new JTextField(20);
	protected JTable table;
	private appSettings app;
	private ArrayList<Kozmeticar> kozmeticari = new  ArrayList<Kozmeticar>();

	//protected TableRowSorter<AbstractTableModel> tableSorter = new TableRowSorter<AbstractTableModel>();
	private JFrame parentFrame;

	private KozmeticarFM kozmeticarifm;

	public IzvjestajJedanTabelaFrame(JFrame parentFrame, KozmeticarFM kozmeticarifm, appSettings app) {
		this.kozmeticarifm = kozmeticarifm;
		this.parentFrame = parentFrame;	
		this.app = app;
		
		kozmeticari.addAll(kozmeticarifm.getKozmeticar().values());
		
		btnChart1.setText("Grafik 1");
		btnChart2.setText("Grafik 2");
		mainToolbar.add(btnChart1);
		mainToolbar.add(btnChart2);
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

		HashMap<Kozmeticar, ArrayList<Double>> nasla = new HashMap<Kozmeticar, ArrayList<Double>>(app.izvjestajJedan(localDateOd, localDateDo));

		table = new JTable(new IzvjestajJedanTabelaModel(kozmeticari, nasla));	
		
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
				
				HashMap<Kozmeticar, ArrayList<Double>> nasla = app.izvjestajJedan(localDateOd, localDateDo);
				IzvjestajJedanTabelaModel juhu = new IzvjestajJedanTabelaModel(kozmeticari, nasla);	
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
				
				HashMap<Kozmeticar, ArrayList<Double>> nasla = app.izvjestajJedan(localDateOd, localDateDo);
				IzvjestajJedanTabelaModel juhu = new IzvjestajJedanTabelaModel(kozmeticari, nasla);	
				table.setModel(juhu);
		    }
		});
		
		btnChart1.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		        List<String> categories = new ArrayList<>();
		        List<Integer> values = new ArrayList<>();

		        HashMap<Kozmeticar, Integer> jjj = new HashMap<Kozmeticar, Integer>(app.izvjestajJedanGrafik1());
		        
				for (Map.Entry<Kozmeticar, Integer> k : jjj.entrySet()) {
					categories.add(k.getKey().getKorisnickoIme());
					values.add(k.getValue());
		        }
		        

		        PieChart chart = new PieChartBuilder().width(600).height(400).title("Opterecenje kozmeticara u posljednjih 30 dana").build();
		        chart.getStyler().setLegendVisible(true);
		        chart.getStyler().setLegendPosition(PieStyler.LegendPosition.OutsideS);

		        for (int i = 0; i < categories.size(); i++) {
		            chart.addSeries(categories.get(i), values.get(i));
		        }

		        displayChart(chart);
		    }
		});
	
		btnChart2.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		        SwingUtilities.invokeLater(new Runnable() {
		            @Override
		            public void run() {
		                XYChart chart = new XYChartBuilder()
		                        .width(800)
		                        .height(600)
		                        .title("Prihodi po kozmetickom tretmanu")
		                        .xAxisTitle("Mjeseci")
		                        .yAxisTitle("Prihod")
		                        .theme(Styler.ChartTheme.Matlab)
		                        .build();
		                chart.getStyler().setDatePattern("MMMMM YYYY.");
		                
		                HashMap<KozmetickaUsluga,HashMap<Date, Double>> mapa = new HashMap<KozmetickaUsluga,HashMap<Date, Double>>(app.izvjestajJedanGrafik2());

		                ArrayList<Date> datumi = new ArrayList<Date>();
		        		LocalDate datumKraj = LocalDate.now();
		        		LocalDate datumPocetak = datumKraj.minusYears(1);
		                
		    	        LocalDate currentMonth = datumPocetak;
		    	        while (!currentMonth.isAfter(datumKraj)) {
		    	        	Date currentDate = Date.from(currentMonth.withDayOfMonth(1).atStartOfDay(ZoneId.systemDefault()).toInstant());
		    	        	datumi.add(currentDate);

		    	            currentMonth = currentMonth.plusMonths(1);
		    	        }
		                
		        		for (Map.Entry<KozmetickaUsluga,HashMap<Date, Double>> k : mapa.entrySet()) {
		        			HashMap<Date, Double> mapica = new HashMap<Date, Double>(k.getValue());
		        			
		        			ArrayList<Double> vrijednosti = new ArrayList<Double>();
			        		for (Map.Entry<Date, Double> m : mapica.entrySet()) {

			        			vrijednosti.add(m.getValue());
		        			
			        		}
			        		
		        			chart.addSeries(k.getKey().getNazivUsluge(), datumi,vrijednosti);

		        		}
		        		

		                // Create the chart panel
		                XChartPanel<XYChart> chartPanel = new XChartPanel<>(chart);
		                
		                

		                // Create the frame and set the chart panel as its content pane
		                JFrame frame = new JFrame("Godisnji prihodi");
		                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		                frame.getContentPane().add(chartPanel);
		                frame.setLocationRelativeTo(null);

		                // Pack and display the frame
		                frame.pack();
		                frame.setVisible(true);
		            }
		        });
		    }
		});

}

	
    
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

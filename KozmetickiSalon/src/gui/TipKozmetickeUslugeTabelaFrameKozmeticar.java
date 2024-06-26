package gui;

import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.ListSelectionModel;

import fileMenager.KlijentFM;
import fileMenager.KozmeticarFM;
import fileMenager.TipKozmetickeUslugeFM;
import fileMenager.ZakazanaKozmetickaUslugaFM;
import korisnici.Kozmeticar;
import meni.appSettings;
import model.TipKozmetickeUslugeKozmeticarTabelaModel;
import model.TipKozmetickeUslugeTabelaModel;
import model.ZakazaneKozmetickeUslugeKozmeticarTabelaModel;
import net.miginfocom.swing.MigLayout;
import osobineTretmana.KozmetickaUsluga;
import osobineTretmana.KozmetickiSalon;
import osobineTretmana.TipKozmetickeUsluge;
import osobineTretmana.ZakazanaKozmetickaUsluga;

public class TipKozmetickeUslugeTabelaFrameKozmeticar extends JPanel{

	private static final long serialVersionUID = -2210389637194209441L;
	
	protected JToolBar mainToolbar = new JToolBar();
	protected JButton btnAdd = new JButton();
	protected JButton btnEdit = new JButton();
	protected JButton btnDelete = new JButton();
	protected JTextField tfSearch = new JTextField(20);
	protected JTable table;
	private appSettings app;
	private JFrame parentFrame;

	private KozmeticarFM kozmeticarifm;

	private KlijentFM klijentifm;

	private Kozmeticar kozmeticar;
	private	ArrayList<TipKozmetickeUsluge> nasla = new ArrayList<TipKozmetickeUsluge> ();

	private TipKozmetickeUslugeFM tipUslugefm;


	public TipKozmetickeUslugeTabelaFrameKozmeticar(JFrame parentFrame, TipKozmetickeUslugeFM tipUslugefm, KlijentFM klijentifm, KozmeticarFM kozmeticarifm, Kozmeticar kozmeticar,appSettings app) {
		this.tipUslugefm = tipUslugefm;
		this.parentFrame = parentFrame;
		this.kozmeticarifm = kozmeticarifm;
		this.klijentifm = klijentifm;
		this.app = app;
		this.kozmeticar = kozmeticar;
		
		// podesavanje tabele

		ArrayList<TipKozmetickeUsluge> tipoviUsluga = new ArrayList<TipKozmetickeUsluge> (tipUslugefm.getTipKozmetickeUsluge().values());
		ArrayList<Integer> listica = new ArrayList<Integer>(kozmeticar.getTretmani());
		
		ArrayList<KozmetickaUsluga> usluge = new ArrayList<KozmetickaUsluga>(app.getkufm().getKozmetickeUsluge().values()); 

		for (Integer indeks: listica) {
			for (KozmetickaUsluga k : usluge) {
				if (k.getId() == indeks) {
					ArrayList<Integer> tt = new ArrayList<Integer>(k.getListuTipova());
					
					for (Integer t: tt) {
						if (!tipoviUsluga.get(t).getObrisana()) {
							nasla.add(tipoviUsluga.get(t));

						}
					}
				}
			}	
		}

		table = new JTable(new TipKozmetickeUslugeKozmeticarTabelaModel(nasla));	
		
		Dimension parentSize = parentFrame.getContentPane().getSize();
		table.setPreferredScrollableViewportSize(parentSize);
		
		table.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.getTableHeader().setReorderingAllowed(false);

		JScrollPane sc = new JScrollPane(table);
		add(sc, "dock center");

}
}
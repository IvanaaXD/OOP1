package model;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import fileMenager.KlijentFM;
import fileMenager.KozmeticarFM;
import korisnici.Klijent;
import korisnici.Kozmeticar;
import meni.appSettings;
import osobineTretmana.ZakazanaKozmetickaUsluga;

public class ZakazaneKozmetickeUslugeKozmeticarTabelaModel extends AbstractTableModel{

	
	private appSettings app = new appSettings();
	
	private static final long serialVersionUID = 1L;
	
	//(boolean obrisan, String naziv

	private ArrayList<ZakazanaKozmetickaUsluga> zakazaneKozmetickeUsluge;
	private String[] columnNames = {"Stanje",  "Datum", "Vrijeme", "Tip usluge","Trajanje",  "Klijent", "Cijena"};

	private KozmeticarFM kozmeticarifm;

	private KlijentFM klijentifm;

	public ZakazaneKozmetickeUslugeKozmeticarTabelaModel(ArrayList<ZakazanaKozmetickaUsluga> zakazaneKozmetickeUsluge, KlijentFM klijentifm, KozmeticarFM kozmeticarifm) {
		this.zakazaneKozmetickeUsluge = zakazaneKozmetickeUsluge;
		this.klijentifm = klijentifm;
		this.kozmeticarifm = kozmeticarifm;
	}
	
	public ArrayList<ZakazanaKozmetickaUsluga> getModel() {
		return this.zakazaneKozmetickeUsluge;
	}

	@Override
	public int getRowCount() {
		return zakazaneKozmetickeUsluge.size();
	}

	@Override
	public int getColumnCount() {
		return columnNames.length;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		
		if (zakazaneKozmetickeUsluge.size() == 0) {
            return null;
        }
		
		ZakazanaKozmetickaUsluga k = zakazaneKozmetickeUsluge.get(rowIndex);		
		//(boolean obrisana, Status stanje, LocalDate datum, LocalTime vrijeme, String tipUsluge, String trajanje, double cijena, int idKlijent, int idKozmeticar
		ArrayList<Klijent> klijenti = new ArrayList<Klijent> (klijentifm.getKlijenti().values());
		//ArrayList<Kozmeticar> kozmeticari = new ArrayList<Kozmeticar> (kozmeticarifm.getKozmeticar().values());

		switch (columnIndex) {
		case 0:
			return k.getStanje();
		case 1:
			return k.getDatum();
		case 2:
			return k.getVrijeme();
		case 3:
			return k.getTipUsluge();
		case 4:
			return k.getTrajanje();
		case 5:
			Klijent kk = klijenti.get(k.getIdKlijent());
			return kk.getKorisnickoIme();
		case 6:			
			return k.getCijena();
		default:
			return null;
		}

	}

	@Override
	public String getColumnName(int column) {
		return this.columnNames[column];
	}

	@Override
	public Class<?> getColumnClass(int columnIndex) {
		if(this.getValueAt(0, columnIndex) == null) {
			return Object.class;
		}
		return this.getValueAt(0, columnIndex).getClass();
	}
	
}

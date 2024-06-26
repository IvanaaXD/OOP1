package model;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import fileMenager.KlijentFM;
import fileMenager.KozmeticarFM;
import korisnici.Klijent;
import korisnici.Kozmeticar;
import meni.appSettings;
import osobineTretmana.ZakazanaKozmetickaUsluga;

public class ZakazaneKozmetickeUslugeTabelaModel extends AbstractTableModel{
	
	private appSettings app = new appSettings();
	
	private static final long serialVersionUID = 1L;
	
	//(boolean obrisan, String naziv

	private ArrayList<ZakazanaKozmetickaUsluga> zakazaneKozmetickeUsluge;
	private String[] columnNames = {"Obrisana", "Stanje",  "Datum", "Vrijeme", "Tip usluge","Trajanje", "Cijena", "Klijent", "Kozmeticar"};

	private KozmeticarFM kozmeticarifm;

	private KlijentFM klijentifm;

	public ZakazaneKozmetickeUslugeTabelaModel(ArrayList<ZakazanaKozmetickaUsluga> zakazaneKozmetickeUsluge, KlijentFM klijentifm, KozmeticarFM kozmeticarifm) {
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
		ArrayList<Kozmeticar> kozmeticari = new ArrayList<Kozmeticar> (kozmeticarifm.getKozmeticar().values());

		switch (columnIndex) {
		case 0:
			return k.getObrisana();
		case 1:
			return k.getStanje();
		case 2:
			return k.getDatum();
		case 3:
			return k.getVrijeme();
		case 4:
			return k.getTipUsluge();
		case 5:
			return k.getTrajanje();
		case 6:
			return k.getCijena();
		case 7:			
			Klijent kk = klijenti.get(k.getIdKlijent());
			return kk.getKorisnickoIme();
		case 8:
			Kozmeticar kkk = kozmeticari.get(k.getIdKozmeticar());
			return kkk.getKorisnickoIme();
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

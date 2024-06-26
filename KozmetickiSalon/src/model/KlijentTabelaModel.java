package model;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;
import korisnici.Klijent;

public class KlijentTabelaModel extends AbstractTableModel {

	private static final long serialVersionUID = 1L;
	
	private ArrayList<Klijent> klijenti;
	private String[] columnNames = {"Obrisan","Ime", "Prezime", "Korisnicko ime", "Lozinka", "Pol", "Telefon", "Adresa", "Potroseno novca", "Kartica lojalnosti"};

	public KlijentTabelaModel(ArrayList<Klijent> klijenti) {
		this.klijenti = klijenti;
	}

	@Override
	public int getRowCount() {
		return klijenti.size();
	}

	@Override
	public int getColumnCount() {
		return columnNames.length;
	}
	
	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		
		if (klijenti.size() == 0) {
            return null;
        }
		
		Klijent k = klijenti.get(rowIndex);		
		
		//String ime, String prezime, String korisnickoIme, String lozinka, String pol, String telefon, String adresa, double potrosenaKolicina, boolean karticaLojalnosti
		
		switch (columnIndex) {
		case 0:
			return k.getObrisan();
		case 1:
			return k.getIme();
		case 2:
			return k.getPrezime();
		case 3:
			return k.getKorisnickoIme();
		case 4:
			return k.getLozinka();
		case 5:
			return k.getPol();
		case 6:
			return k.getTelefon();
		case 7:
			return k.getAdresa();
		case 8:
			return k.getKolicina();
		case 9:
			return k.getKartica();
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

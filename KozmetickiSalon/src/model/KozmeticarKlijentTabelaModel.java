package model;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import fileMenager.KozmetickaUslugaFM;
import korisnici.Klijent;
import korisnici.Kozmeticar;
import osobineTretmana.KozmetickaUsluga;
import osobineTretmana.ZakazanaKozmetickaUsluga;

public class KozmeticarKlijentTabelaModel extends AbstractTableModel{
	
	private static final long serialVersionUID = 6067854670223857445L;
	private ArrayList<Kozmeticar> kozmeticari;
	private String[] columnNames = {"Ime", "Prezime", "Korisnicko ime", "Telefon","Tipovi tretmana"};
	private KozmetickaUslugaFM kozmetickeUsluge;

	public KozmeticarKlijentTabelaModel(ArrayList<Kozmeticar> kozmeticari, KozmetickaUslugaFM kozmetickeUsluge) {
		this.kozmeticari = kozmeticari;
		this.kozmetickeUsluge = kozmetickeUsluge;
	}
	
	public ArrayList<Kozmeticar> getModel() {
		return this.kozmeticari;
	}

	@Override
	public int getRowCount() {
		return kozmeticari.size();
	}

	@Override
	public int getColumnCount() {
		return columnNames.length;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		
		if (kozmeticari.size() == 0) {
            return null;
        }
		
		Kozmeticar k = kozmeticari.get(rowIndex);		
		//(boolean obrisan, String ime, String prezime, String korisnickoIme, String telefon, ArrayList<Integer> tretmani
		
		ArrayList<KozmetickaUsluga> usluge = new ArrayList<KozmetickaUsluga> (kozmetickeUsluge.getKozmetickeUsluge().values());
		
		switch (columnIndex) {
		case 0:
			return k.getKorisnickoIme(); 
		case 1:
			return k.getIme();
		case 2:
			return k.getPrezime();
		case 3:
			return k.getTelefon();
		case 4:
			
			ArrayList<Integer> tretmani = k.getTretmani();			
			ArrayList<String> uslugice = new ArrayList<String>();

			for (KozmetickaUsluga ku : usluge) {
				for (int t = 0; t < tretmani.size(); t++) {
					if (tretmani.get(t) == ku.getId()) {
						uslugice.add(ku.getNazivUsluge());
					}
				}
			}
			
			return uslugice;

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

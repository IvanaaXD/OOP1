package model;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import fileMenager.KozmetickaUslugaFM;
import korisnici.Kozmeticar;
import osobineTretmana.KozmetickaUsluga;

public class KozmeticarTabelaModel extends AbstractTableModel{

	private static final long serialVersionUID = 1L;
	
	//(boolean obrisan, String ime, String prezime, String korisnickoIme, String lozinka, String pol, String telefon, String adresa, ArrayList<Integer> tretmani, StrucnaSprema nivoStrucneSpreme, double nivoRadnogStaza, boolean imaBonus, double iznosBonusa, double plata, double ukupnaPlata

	private ArrayList<Kozmeticar> kozmeticari;
	private String[] columnNames = {"Obrisan","Ime", "Prezime", "Korisnicko ime", "Lozinka", "Pol", "Telefon", "Adresa", "Tipovi tretmana", "NSS", "NRA", "Bonus", "Iznos bonusa", "Plata", "Ukupna plata"};

	private KozmetickaUslugaFM kozmetickeUsluge;

	public KozmeticarTabelaModel(ArrayList<Kozmeticar> kozmeticari, KozmetickaUslugaFM kozmetickeUsluge) {
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
		//(boolean obrisan, String ime, String prezime, String korisnickoIme, String lozinka, String pol, String telefon, String adresa, ArrayList<Integer> tretmani, StrucnaSprema nivoStrucneSpreme, double nivoRadnogStaza, boolean imaBonus, double iznosBonusa, double plata, double ukupnaPlata
		
		ArrayList<KozmetickaUsluga> usluge = new ArrayList<KozmetickaUsluga> (kozmetickeUsluge.getKozmetickeUsluge().values());
		
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
		case 9:
			return k.getNivoStrucneSpreme();
		case 10:
			return k.getNivoRadnogStaza();
		case 11:
			return k.getImaBonus();
		case 12:
			return k.getIznosBonusa();
		case 13:
			return k.getPlata();
		case 14:
			return k.getUkupnaPlata();
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

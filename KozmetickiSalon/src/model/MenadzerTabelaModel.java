package model;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import korisnici.Menadzer;

public class MenadzerTabelaModel extends AbstractTableModel {
	

	private static final long serialVersionUID = 1L;
	
	//(boolean obrisan, String ime, String prezime, String korisnickoIme, String lozinka, String pol, String telefon, String adresa,StrucnaSprema nivoStrucneSpreme, double nivoRadnogStaza, boolean imaBonus, double iznosBonusa, double plata, double ukupnaPlata

	private ArrayList<Menadzer> menadzeri;
	private String[] columnNames = {"Obrisan","Ime", "Prezime", "Korisnicko ime", "Lozinka", "Pol", "Telefon", "Adresa", "NSS", "NRA", "Bonus", "Iznos bonusa", "Plata", "Ukupna plata"};

	public MenadzerTabelaModel(ArrayList<Menadzer> menadzeri) {
		this.menadzeri = menadzeri;
	}
	
	public ArrayList<Menadzer> getModel() {
		return this.menadzeri;
	}

	@Override
	public int getRowCount() {
		return menadzeri.size();
	}

	@Override
	public int getColumnCount() {
		return columnNames.length;
	}
	
	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		
		if (menadzeri.size() == 0) {
            return null;
        }
		
		Menadzer m = menadzeri.get(rowIndex);		
		//(boolean obrisan, String ime, String prezime, String korisnickoIme, String lozinka, String pol, String telefon, String adresa, ArrayList<Integer> tretmani, StrucnaSprema nivoStrucneSpreme, double nivoRadnogStaza, boolean imaBonus, double iznosBonusa, double plata, double ukupnaPlata
		
		switch (columnIndex) {
		case 0:
			return m.getObrisan();
		case 1:
			return m.getIme();
		case 2:
			return m.getPrezime();
		case 3:
			return m.getKorisnickoIme();
		case 4:
			return m.getLozinka();
		case 5:
			return m.getPol();
		case 6:
			return m.getTelefon();
		case 7:
			return m.getAdresa();
		case 8:
			return m.getNivoStrucneSpreme();
		case 9:
			return m.getNivoRadnogStaza();
		case 10:
			return m.getImaBonus();
		case 11:
			return m.getIznosBonusa();
		case 12:
			return m.getPlata();
		case 13:
			return m.getUkupnaPlata();
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

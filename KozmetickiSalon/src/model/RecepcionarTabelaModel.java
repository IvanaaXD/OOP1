package model;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import korisnici.Recepcionar;

public class RecepcionarTabelaModel extends AbstractTableModel{
	

	private static final long serialVersionUID = 1L;
	
	//(boolean obrisan, String ime, String prezime, String korisnickoIme, String lozinka, String pol, String telefon, String adresa,StrucnaSprema nivoStrucneSpreme, double nivoRadnogStaza, boolean imaBonus, double iznosBonusa, double plata, double ukupnaPlata

	private ArrayList<Recepcionar> recepcionari;
	private String[] columnNames = {"Obrisan","Ime", "Prezime", "Korisnicko ime", "Lozinka", "Pol", "Telefon", "Adresa", "NSS", "NRA", "Bonus", "Iznos bonusa", "Plata", "Ukupna plata"};

	public RecepcionarTabelaModel(ArrayList<Recepcionar> recepcionari) {
		this.recepcionari = recepcionari;
	}
	
	public ArrayList<Recepcionar> getModel() {
		return this.recepcionari;
	}

	@Override
	public int getRowCount() {
		return recepcionari.size();
	}

	@Override
	public int getColumnCount() {
		return columnNames.length;
	}
	
	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		
		if (recepcionari.size() == 0) {
            return null;
        }
		
		Recepcionar r = recepcionari.get(rowIndex);		
		//(boolean obrisan, String ime, String prezime, String korisnickoIme, String lozinka, String pol, String telefon, String adresa, ArrayList<Integer> tretmani, StrucnaSprema nivoStrucneSpreme, double nivoRadnogStaza, boolean imaBonus, double iznosBonusa, double plata, double ukupnaPlata
		
		switch (columnIndex) {
		case 0:
			return r.getObrisan();
		case 1:
			return r.getIme();
		case 2:
			return r.getPrezime();
		case 3:
			return r.getKorisnickoIme();
		case 4:
			return r.getLozinka();
		case 5:
			return r.getPol();
		case 6:
			return r.getTelefon();
		case 7:
			return r.getAdresa();
		case 8:
			return r.getNivoStrucneSpreme();
		case 9:
			return r.getNivoRadnogStaza();
		case 10:
			return r.getImaBonus();
		case 11:
			return r.getIznosBonusa();
		case 12:
			return r.getPlata();
		case 13:
			return r.getUkupnaPlata();
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

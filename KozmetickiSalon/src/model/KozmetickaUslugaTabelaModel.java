package model;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import osobineTretmana.KozmetickaUsluga;

public class KozmetickaUslugaTabelaModel extends AbstractTableModel{
	
	private static final long serialVersionUID = 1L;
	
	//(boolean obrisan, String naziv

	private ArrayList<KozmetickaUsluga> kozmetickeUsluge;
	private String[] columnNames = {"Obrisan","Naziv"};

	public KozmetickaUslugaTabelaModel(ArrayList<KozmetickaUsluga> kozmetickeUsluge) {
		this.kozmetickeUsluge = kozmetickeUsluge;
	}
	
	public ArrayList<KozmetickaUsluga> getModel() {
		return this.kozmetickeUsluge;
	}

	@Override
	public int getRowCount() {
		return kozmetickeUsluge.size();
	}

	@Override
	public int getColumnCount() {
		return columnNames.length;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		
		if (kozmetickeUsluge.size() == 0) {
            return null;
        }
		
		KozmetickaUsluga k = kozmetickeUsluge.get(rowIndex);		
		//(boolean obrisan, String ime, String prezime, String korisnickoIme, String lozinka, String pol, String telefon, String adresa, ArrayList<Integer> tretmani, StrucnaSprema nivoStrucneSpreme, double nivoRadnogStaza, boolean imaBonus, double iznosBonusa, double plata, double ukupnaPlata
		
		switch (columnIndex) {
		case 0:
			return k.getObrisan();
		case 1:
			return k.getNazivUsluge();
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

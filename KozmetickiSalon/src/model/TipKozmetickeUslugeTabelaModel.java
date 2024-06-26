package model;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import osobineTretmana.TipKozmetickeUsluge;

public class TipKozmetickeUslugeTabelaModel extends AbstractTableModel{

	private static final long serialVersionUID = 1L;
	
	//(boolean obrisan, String naziv

	private ArrayList<TipKozmetickeUsluge> tipoviKozmetickeUsluge;
	private String[] columnNames = {"Obrisana","Tip", "Trajanje", "Naziv", "Cijena"};

	public TipKozmetickeUslugeTabelaModel(ArrayList<TipKozmetickeUsluge> tipoviKozmetickeUsluge) {
		this.tipoviKozmetickeUsluge = tipoviKozmetickeUsluge;
	}
	
	public ArrayList<TipKozmetickeUsluge> getModel() {
		return this.tipoviKozmetickeUsluge;
	}

	@Override
	public int getRowCount() {
		return tipoviKozmetickeUsluge.size();
	}

	@Override
	public int getColumnCount() {
		return columnNames.length;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		
		if (tipoviKozmetickeUsluge.size() == 0) {
            return null;
        }
		
		TipKozmetickeUsluge k = tipoviKozmetickeUsluge.get(rowIndex);		
		//(boolean obrisan, String ime, String prezime, String korisnickoIme, String lozinka, String pol, String telefon, String adresa, ArrayList<Integer> tretmani, StrucnaSprema nivoStrucneSpreme, double nivoRadnogStaza, boolean imaBonus, double iznosBonusa, double plata, double ukupnaPlata
		
		switch (columnIndex) {
		case 0:
			return k.getObrisana();
		case 1:
			return k.getTipUsluge();
		case 2:
			return k.getTrajanje();
		case 3:
			return k.getNazivUsluge();
		case 4:
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

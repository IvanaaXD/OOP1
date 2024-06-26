package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.swing.table.AbstractTableModel;

import korisnici.Kozmeticar;
import osobineTretmana.Status;

public class IzvjestajDvaTabelaModel extends AbstractTableModel{

	private static final long serialVersionUID = 1L;
	private ArrayList<Status> statusi;
	private String[] columnNames = {"Status","Broj tretmana"};
	private HashMap<Status, Integer> nasla;

	public IzvjestajDvaTabelaModel(ArrayList<Status> statusi, HashMap<Status, Integer>  nasla) {
		this.statusi = statusi;
		this.nasla = nasla;
	}
	
	public ArrayList<Status> getModel() {
		return this.statusi;
	}

	@Override
	public int getRowCount() {
		return statusi.size();
	}

	@Override
	public int getColumnCount() {
		return columnNames.length;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		
		if (statusi.size() == 0) {
            return null;
        }
		
		Status s = statusi.get(rowIndex);	
		//(boolean obrisan, String ime, String prezime, String korisnickoIme, String telefon, ArrayList<Integer> tretmani
		
		switch (columnIndex) {
		case 0:
			return s; 
		case 1:
			return nasla.get(s);
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

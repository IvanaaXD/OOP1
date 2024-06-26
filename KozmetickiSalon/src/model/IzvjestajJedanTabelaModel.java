package model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.swing.table.AbstractTableModel;

import fileMenager.KozmetickaUslugaFM;
import korisnici.Kozmeticar;
import osobineTretmana.KozmetickaUsluga;

public class IzvjestajJedanTabelaModel extends AbstractTableModel{

	private static final long serialVersionUID = 1L;
	private ArrayList<Kozmeticar> kozmeticari;
	private String[] columnNames = {"Korisnicko ime", "Ime", "Prezime", "Broj izvrsenih tretmana","Prihod"};
	private HashMap<Kozmeticar, ArrayList<Double>> nasla;

	public IzvjestajJedanTabelaModel(ArrayList<Kozmeticar> kozmeticari, HashMap<Kozmeticar,ArrayList<Double>> nasla) {
		this.kozmeticari = kozmeticari;
		this.nasla = nasla;
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
		ArrayList<Double> listica = new ArrayList<Double>();
		
		if (!nasla.isEmpty()) {

			for (Map.Entry<Kozmeticar, ArrayList<Double>> kkk : this.nasla.entrySet()) {
		        if (kkk.getKey().getId() == k.getId()) {
		        	listica = kkk.getValue();
		        }
	        }
		}
		
		switch (columnIndex) {
		case 0:
			return k.getKorisnickoIme(); 
		case 1:
			return k.getIme();
		case 2:
			return k.getPrezime();
		case 3:
			double brojTretmana = 0;
			int brojTretmanaInt = 0;
			
		    if (!listica.isEmpty()) {
		        brojTretmana = listica.get(0);
		        brojTretmanaInt = (int) brojTretmana;
		    }
			
			return brojTretmanaInt;
		case 4:
			double cijena = listica.get(1);
			return cijena;
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

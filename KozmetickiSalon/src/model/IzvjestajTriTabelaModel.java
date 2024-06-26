package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.swing.table.AbstractTableModel;

import osobineTretmana.TipKozmetickeUsluge;

public class IzvjestajTriTabelaModel extends AbstractTableModel{

	private static final long serialVersionUID = 1L;
	private ArrayList<TipKozmetickeUsluge> tipoviKozmetickeUsluge;
	private String[] columnNames = {"Tip", "Trajanje", "Naziv", "Cijena", "Broj zakazanih tretmana", "Prihodi"};
	private HashMap<TipKozmetickeUsluge, ArrayList<Double>> nasla;

	public IzvjestajTriTabelaModel(ArrayList<TipKozmetickeUsluge> tipoviKozmetickeUsluge, HashMap<TipKozmetickeUsluge,ArrayList<Double>> nasla) {
		this.nasla = nasla;
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
		
		if (nasla.size() == 0) {
            return null;
        }
		
		TipKozmetickeUsluge k = tipoviKozmetickeUsluge.get(rowIndex);		
		//(boolean obrisan, String ime, String prezime, String korisnickoIme, String lozinka, String pol, String telefon, String adresa, ArrayList<Integer> tretmani, StrucnaSprema nivoStrucneSpreme, double nivoRadnogStaza, boolean imaBonus, double iznosBonusa, double plata, double ukupnaPlata
		
		ArrayList<Double> listica = new ArrayList<Double>();
		
		if (!nasla.isEmpty()) {

			for (Map.Entry<TipKozmetickeUsluge, ArrayList<Double>> kkk : this.nasla.entrySet()) {
		        if (kkk.getKey().getId() == k.getId()) {
		        	listica = kkk.getValue();
		        }
	        }
		}
				
		switch (columnIndex) {
		case 0:
			return k.getTipUsluge();
		case 1:
			return k.getTrajanje();
		case 2:
			return k.getNazivUsluge();
		case 3:
			return k.getCijena();
		case 4:
			double brojTretmana = 0;
			int brojTretmanaInt = 0;
			
		    if (!listica.isEmpty()) {
		        brojTretmana = listica.get(0);
		        brojTretmanaInt = (int) brojTretmana;
		    }
			
			return brojTretmanaInt;
		case 5:
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

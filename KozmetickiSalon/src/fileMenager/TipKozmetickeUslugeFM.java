package fileMenager;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import osobineTretmana.TipKozmetickeUsluge;

public class TipKozmetickeUslugeFM {

	private String tipkozmetickauslugafile;
	private HashMap<Integer, TipKozmetickeUsluge> tipkozmetickeUsluge = new HashMap<Integer, TipKozmetickeUsluge>();
	//public HashMap<Integer, TipKozmetickeUsluge> tipovizabrisanje = new HashMap<Integer, TipKozmetickeUsluge>();
	private int indeks;
	
	public TipKozmetickeUslugeFM() {
		indeks = 0;
	}
	
	public void setPath(String path) {
		this.tipkozmetickauslugafile = path;
	}
	
	public HashMap<Integer, TipKozmetickeUsluge> getTipKozmetickeUsluge() {
		return this.tipkozmetickeUsluge;
	}
	
    public TipKozmetickeUsluge PronadjiTipKozmetickeUslugePoId(int id){
    	
    	TipKozmetickeUsluge traziId = null;
        
    	for (Map.Entry<Integer, TipKozmetickeUsluge> entry : tipkozmetickeUsluge.entrySet()) {
            //Integer key = entry.getKey();
            TipKozmetickeUsluge tku = entry.getValue();
            
            if (tku.getId() == id)
            {
                traziId = tku;
                break;
            }
   	 }
        return traziId;
    }
    
    public TipKozmetickeUsluge PronadjiTipKozmetickeUslugePoTipu(String tipUsluge){
    	
    	TipKozmetickeUsluge traziTip = null;
    	
    	for (Map.Entry<Integer, TipKozmetickeUsluge> entry : tipkozmetickeUsluge.entrySet()) {
            //Integer key = entry.getKey();
            TipKozmetickeUsluge tku = entry.getValue();
            
            if (entry.getValue().getTipUsluge().equals(tipUsluge))
            {
            	traziTip = tku;
                break;
            }
   	 }
        return traziTip;
    }
    
//    public HashMap<Integer, TipKozmetickeUsluge> PronadjiTipKozmetickeUslugePoNazivu(String nazivUsluge){
//        
//        for (Map.Entry<Integer, TipKozmetickeUsluge> entry : tipkozmetickeUsluge.entrySet()) {
//            //Integer key = entry.getKey();
//            TipKozmetickeUsluge tku = entry.getValue();
//            
//            if (tku.getNazivUsluge().equals(nazivUsluge))
//            {
//            	tipovizabrisanje.put(id, tku);
//            	id += 1;
//            }
//   	 }
//        return tipovizabrisanje;
//    }
    
	/*Ucitava iz fajla*/
	public boolean loadData() {
		try {
			BufferedReader br = new BufferedReader(new FileReader(this.tipkozmetickauslugafile));
			String linija = null;
			while ((linija = br.readLine()) != null) {
				String[] dio = linija.split(";");
				int id = Integer.parseInt(dio[0]);
				
				if (id >= this.indeks) {
					this.indeks = id + 1;
				}
				
				boolean obrisana = false;
				if(dio[1].equals("true")) {
					obrisana = true;
				} 
								
				double cijena = Double.parseDouble(dio[5]);
				
		        TipKozmetickeUsluge tku = new TipKozmetickeUsluge(obrisana, dio[2], dio[3], dio[4], cijena);
		        tku.setId(id);
				tipkozmetickeUsluge.put(id, tku);
			}
			br.close();
		} catch (IOException e) {
			return false;
		}
		return true;
	}

	/*Upisuje u fajl*/
	public boolean saveData() {
		PrintWriter pw = null;
		try {
			pw = new PrintWriter(new FileWriter(this.tipkozmetickauslugafile, false));
			for (Map.Entry<Integer, TipKozmetickeUsluge> tku : tipkozmetickeUsluge.entrySet()) {
	 
				pw.println(tku.getKey() + ";" + tku.getValue().toFileString());
			}
			pw.close();
		} catch (IOException e) {
			return false;
		}
		return true;
	}

	public void add(boolean obrisana, String tipUsluge, String trajanje, String nazivUsluge, double cijena) {
		TipKozmetickeUsluge tku = new TipKozmetickeUsluge(false, tipUsluge, trajanje, nazivUsluge, cijena);
		this.tipkozmetickeUsluge.put(this.indeks, tku);	
		this.indeks += 1;
		this.saveData();
	}

	public void remove(int id) {
		this.tipkozmetickeUsluge.remove(id);
		this.saveData();
	}
	
	public void printAll() {
		
		for (Map.Entry<Integer, TipKozmetickeUsluge> tku : this.tipkozmetickeUsluge.entrySet()) {
            System.out.println((tku.getKey() + ";" + tku.getValue().toString())); 
        }
	}
	
	public void printAllNotObrisan() {
		
		for (Map.Entry<Integer, TipKozmetickeUsluge> tku : this.tipkozmetickeUsluge.entrySet()) {
			if (tku.getValue().getObrisana() == false) {
	            System.out.println((tku.getKey() + ";" + tku.getValue().toString())); 
			}
        }
	}
	
}

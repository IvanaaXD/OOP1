package fileMenager;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import osobineTretmana.KozmetickiSalon;

public class KozmetickiSalonFM {
	
	private String kozmetickisalonfile;
	private HashMap<Integer, KozmetickiSalon> kozmetickiSalon = new HashMap<Integer, KozmetickiSalon>();
	public int indeks;
	
	public KozmetickiSalonFM() {
		indeks = 0;
	}
	
	public HashMap<Integer, KozmetickiSalon> getKozmetickiSalon() {
		return this.kozmetickiSalon;
	}
	
	public void setPath(String path) {
		this.kozmetickisalonfile = path;
	}
	
    public KozmetickiSalon PronadjiKozmetickiSalonPoId(int id){
    	
    	KozmetickiSalon traziId = null;
    	for (Map.Entry<Integer, KozmetickiSalon> entry : kozmetickiSalon.entrySet()) {
	        Integer key = entry.getKey();
    		KozmetickiSalon ks = entry.getValue();
    		
    		System.out.println(ks);
	        
	    	if (key == id)
	        {
	            traziId = ks;
	            break;
	        }
		 }
	    return traziId;
	}
    
    public KozmetickiSalon PronadjiKozmetickiSalonPoNazivu(String naziv){
    	
    	KozmetickiSalon traziNaziv = null;
       	for (Map.Entry<Integer, KozmetickiSalon> entry : kozmetickiSalon.entrySet()) {
	        //Integer key = entry.getKey();
			KozmetickiSalon ks = entry.getValue();
			
			if (ks.getNaziv().equals(naziv))
            {
            	traziNaziv = ks;
                break;
            }
		 }
	    return traziNaziv;
	}
    
	/*Ucitava iz fajla*/
	public boolean loadData() {
		try {
			BufferedReader br = new BufferedReader(new FileReader(this.kozmetickisalonfile));
			String linija = null;
			while ((linija = br.readLine()) != null) {
				
				String[] dio = linija.split(";");
				
				int id = Integer.parseInt(dio[0]);
				LocalTime pocetak = LocalTime.parse(dio[2]);
				LocalTime kraj = LocalTime.parse(dio[3]);
				
				KozmetickiSalon ks = new KozmetickiSalon(dio[1], pocetak, kraj);
				ks.setId(id);
				kozmetickiSalon.put(id, ks);
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
			pw = new PrintWriter(new FileWriter(this.kozmetickisalonfile, false));
			for (Entry<Integer, KozmetickiSalon> ks : kozmetickiSalon.entrySet()) {
	 
				pw.println(ks.getKey() + ";" + ks.getValue().toFileString());
			}
			pw.close();
		} catch (IOException e) {
			return false;
		}
		return true;
	}


	public void add(String naziv, LocalTime pocetak, LocalTime kraj) {
		this.kozmetickiSalon.put(this.indeks, new KozmetickiSalon(naziv, pocetak, kraj));	
		this.indeks += 1;
		this.saveData();
	}

	public void remove(int id) {
		this.kozmetickiSalon.remove(id);
		this.saveData();
	}
	
	public void printAll() {
		
		for (Map.Entry<Integer, KozmetickiSalon> ks : this.kozmetickiSalon.entrySet()) {
            System.out.println((ks.getKey() + ";" + ks.getValue().toString())); 
        }
	}

}

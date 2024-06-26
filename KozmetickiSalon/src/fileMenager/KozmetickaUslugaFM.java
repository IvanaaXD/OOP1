package fileMenager;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import osobineTretmana.KozmetickaUsluga;

public class KozmetickaUslugaFM {

	private String kozmetickauslugafile;
	private HashMap<Integer, KozmetickaUsluga> kozmetickeUsluge = new HashMap<Integer, KozmetickaUsluga>();
	public int indeks;
	
	public KozmetickaUslugaFM() {
		indeks = 0;
	}
	
	public void setPath(String path) {
		this.kozmetickauslugafile = path;
	}
	
	public HashMap<Integer, KozmetickaUsluga> getKozmetickeUsluge() {
		return this.kozmetickeUsluge;
	}
	
    public KozmetickaUsluga PronadjiKozmetickuUsluguPoId(int id){
    	
    	KozmetickaUsluga traziId = null;
    	for (Map.Entry<Integer, KozmetickaUsluga> entry : kozmetickeUsluge.entrySet()) {
	        //Integer key = entry.getKey();
    		KozmetickaUsluga ku = entry.getValue();
	        
	    	if (entry.getKey() == id)
	        {
	            traziId = ku;
	            break;
	        }
		 }
	    return traziId;
	}
    
    public KozmetickaUsluga PronadjiKozmetickuUsluguPoNazivu(String nazivUsluge){
    	
    	KozmetickaUsluga traziNaziv = null;
    	
    	 for (Map.Entry<Integer, KozmetickaUsluga> entry : kozmetickeUsluge.entrySet()) {
             //Integer key = entry.getKey();
             KozmetickaUsluga ku = entry.getValue();
             String naziv = ku.getNazivUsluge();
             
             if (naziv.equals(nazivUsluge))
             {
             	traziNaziv = ku;
                 break;
             }
    	 }
        return traziNaziv;
    }
    
	/*Ucitava iz fajla*/
	public boolean loadData() {
		try {
			BufferedReader br = new BufferedReader(new FileReader(this.kozmetickauslugafile));
			String linija = null;
			while ((linija = br.readLine()) != null) {
				String[] dio = linija.split(";");
				int id = Integer.parseInt(dio[0]);
				
				if (id >= this.indeks) {
					this.indeks = id + 1;
				}
				
				boolean obrisana = false;
				if(dio[1].equals("true") || dio[1].equals("TRUE")) {
					obrisana = true;
				} 
				
		        ArrayList<Integer> lista = new ArrayList<Integer>();
				
				if (!dio[3].equals("null") && !dio[3].equals("[]")) {
					String[] strlista = dio[3].split(","); 
								        
		        	for (int i = 0; i < strlista.length; i++) {
		        		String[] djelic = strlista[i].split("");
		        		int wuhu = Integer.parseInt(djelic[1]);
		        		lista.add(wuhu);
		        	}
				}
				
				KozmetickaUsluga ku = new KozmetickaUsluga(obrisana, dio[2], lista);
				ku.setId(id);
				kozmetickeUsluge.put(id, ku);
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
			pw = new PrintWriter(new FileWriter(this.kozmetickauslugafile, false));
			for (Entry<Integer, KozmetickaUsluga> ku : kozmetickeUsluge.entrySet()) {
	 
				pw.println(ku.getKey() + ";" + ku.getValue().toFileString());
			}
			pw.close();
		} catch (IOException e) {
			return false;
		}
		return true;
	}


	public void add(boolean obrisan, String nazivUsluge, ArrayList<Integer> listaTipova) {
		this.kozmetickeUsluge.put(this.indeks, new KozmetickaUsluga(false, nazivUsluge,listaTipova));	
		this.indeks += 1;
		this.saveData();
	}

	public void remove(int id) {
		this.kozmetickeUsluge.remove(id);
		this.saveData();
	}
	
	public void printAll() {
		
		for (Map.Entry<Integer, KozmetickaUsluga> k : this.kozmetickeUsluge.entrySet()) {
            System.out.println((k.getKey() + ";" + k.getValue().toString())); 
        }	
	}
	
	public void printAllNotObrisan() {
		
		for (Map.Entry<Integer, KozmetickaUsluga> k : this.kozmetickeUsluge.entrySet()) {
			if (k.getValue().getObrisan() == false) {
	            System.out.println((k.getKey() + ";" + k.getValue().toString())); 
			}
        }
	}
	
}

package fileMenager;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import korisnici.Klijent;

public class KlijentFM {
	
	private String klijentfile;
	private HashMap<Integer, Klijent> klijenti = new HashMap<Integer, Klijent>();
	private int indeks;	
	
	public KlijentFM() {
		indeks = 0;
	}
	
	public void setPath(String path) {
		this.klijentfile = path;
	}
	
	public HashMap<Integer, Klijent> getKlijenti() {
		return this.klijenti;
	}
	
	public void setKLijenti(HashMap<Integer, Klijent> klijenti) {
		this.klijenti = klijenti;
	}
	
    public Klijent PronadjiKlijentaPoId(int id){
    	
    	Klijent traziId = null;
    	for (Map.Entry<Integer, Klijent> entry : klijenti.entrySet()) {
	        //Integer key = entry.getKey();
    		Klijent k = entry.getValue();
	        
	    	if (k.getId() == id)
	        {
	            traziId = k;
	            break;
	        }
		 }
	    return traziId;
	}
    
    public Klijent PronadjiKlijentaPoKorisnickomImenu(String korisnickoIme){
    	
    	Klijent traziKorisnickoIme = null;
       	for (Map.Entry<Integer, Klijent> entry : klijenti.entrySet()) {
	        //Integer key = entry.getKey();
			Klijent k = entry.getValue();
	        
			if ((k.getKorisnickoIme()).equals(korisnickoIme))
            {
            	traziKorisnickoIme = k;
                break;
            }
        }
        return traziKorisnickoIme;
	}
	
	/*Ucitava iz fajla*/
	public boolean loadData() {
		try {
			BufferedReader br = new BufferedReader(new FileReader(this.klijentfile));
			String linija = null;
			//this.indeks = 0;
			while ((linija = br.readLine()) != null) {
				String[] dio = linija.split(";");
				int id = Integer.parseInt(dio[0]);
				
				if (id >= this.indeks) {
					this.indeks = id + 1;
				}
				boolean obrisan = false;
				if(dio[1].equals("TRUE") || dio[1].equals("true")) {
					obrisan = true;
				} 
				
				double broj = Double.parseDouble(dio[9]);
				
				boolean tekst = false;
				if(dio[10].equals("TRUE") || dio[10].equals("true")) {
					tekst = true;
				} 
				
				Klijent k = new Klijent(obrisan, dio[2], dio[3], dio[4], dio[5], dio[6], dio[7], dio[8], broj, tekst);
				k.setId(id);
				klijenti.put(id, k);
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
            pw = new PrintWriter(new FileWriter(this.klijentfile, false));
            for (Map.Entry<Integer, Klijent> k : this.klijenti.entrySet()) {
                pw.println(k.getKey() + ";" + k.getValue().toFileString()); 
            }
            
            pw.close();
        } catch (IOException e) {
            return false;
        }
        
        return true;
	}
  
	public void add(boolean obrisan, String ime, String prezime, String korisnickoIme, String lozinka, String pol, String telefon, String adresa, double kolicina, boolean kartica) {
		loadData();
		Klijent k = new Klijent(false, ime, prezime, korisnickoIme, lozinka, pol, telefon, adresa, kolicina, kartica);
		this.klijenti.put(this.indeks, k);

		//+k.setId(indeks);
		this.indeks += 1;

		this.saveData();
	}

	public void remove(int id) {
		this.klijenti.remove(id);
		this.saveData();
	}
	
	public void printAll() {
		
		for (Map.Entry<Integer, Klijent> k : this.klijenti.entrySet()) {
            System.out.println((k.getKey() + ";" + k.getValue().toString())); 
        }
		
	}
	
	public void printAllNotObrisan() {
		
		for (Map.Entry<Integer, Klijent> k : this.klijenti.entrySet()) {
			if (k.getValue().getObrisan() == false) {
	            System.out.println((k.getKey() + ";" + k.getValue().toString())); 
			}
        }
	}
	
	public void getListKlijent() {
		List<Klijent> list = new ArrayList<>();
		for (Map.Entry<Integer, Klijent> k : this.klijenti.entrySet()) {
	        list.add(k.getValue()); 
        }

	}
	
	public void setLCValue(double iznos) {

		for (Map.Entry<Integer, Klijent> k : this.klijenti.entrySet()) {
            if (k.getValue().getKolicina() >= iznos) {
            	k.getValue().setKartica(true);
            } else {
            	k.getValue().setKartica(false);
            }
                        	
        } saveData();
	}
	

}

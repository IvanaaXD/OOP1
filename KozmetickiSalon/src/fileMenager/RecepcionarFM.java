package fileMenager;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import korisnici.Recepcionar;
import korisnici.StrucnaSprema;

public class RecepcionarFM {
	
	private String recepcionarfile;
	private HashMap<Integer, Recepcionar> recepcionari = new HashMap<Integer, Recepcionar>();
	private int indeks = 0;
	
	public void setPath(String path) {
		this.recepcionarfile = path;
	}
	
	public RecepcionarFM() {
		indeks = 0;
	}
	
	public void setRecpcionar(HashMap<Integer, Recepcionar> recepcionari) {
		this.recepcionari = recepcionari;
	}
	
	public HashMap<Integer, Recepcionar> getRecepcionar() {
		return this.recepcionari;
	}
	
    public Recepcionar PronadjiRecepcionaraPoId(int id){
    	
    	Recepcionar traziId = null;
        
        for (Map.Entry<Integer, Recepcionar> entry : recepcionari.entrySet()) {
            //Integer key = entry.getKey();
        	Recepcionar r = entry.getValue();
            
            if (r.getId() == id)
            {
                traziId = r;
                break;
            }
   	 }
        return traziId;
    }
    
    public Recepcionar PronadjiRecepcionaraPoKorisnickomImenu(String korisnickoIme){
    	
    	Recepcionar traziKorisnickoIme = null;

        for (Map.Entry<Integer, Recepcionar> entry : recepcionari.entrySet()) {
            //Integer key = entry.getKey();
        	Recepcionar r = entry.getValue();
            
        	if (r.getKorisnickoIme().equals(korisnickoIme))
            {
            	traziKorisnickoIme = r;
                break;
            }
   	 }
        return traziKorisnickoIme;
    }
	
	/*Ucitava iz fajla*/
	public boolean loadData() {
		try {
			BufferedReader br = new BufferedReader(new FileReader(this.recepcionarfile));
			String linija = null;
			while ((linija = br.readLine()) != null) {
				String[] dio = linija.split(";");
				int id = Integer.parseInt(dio[0]);
				
				if (id >= this.indeks) {
					this.indeks = id + 1;
				}
				
				boolean obrisan = false;
				if(dio[1].equals("true")) {
					obrisan = true;
				} 
				
				StrucnaSprema s;
				if (!dio[9].equals("null")) {
					s = StrucnaSprema.valueOf(dio[9]);
				} else {
					s = null;
				}
				
				double radniStaz = Double.parseDouble(dio[10]);

				boolean tekst = false;
				if(dio[11].equals("true")) {
					tekst = true;
				} 
				
				double bonus = Double.parseDouble(dio[12]);
				double plata = Double.parseDouble(dio[13]);
				double ukupno = Double.parseDouble(dio[14]);
				
				Recepcionar r = new Recepcionar(obrisan, dio[2], dio[3], dio[4], dio[5], dio[6], dio[7], dio[8], s, radniStaz, tekst, bonus, plata, ukupno);
				r.setId(id);
				recepcionari.put(id, r);
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
			pw = new PrintWriter(new FileWriter(this.recepcionarfile, false));
			for (Entry<Integer, Recepcionar> k : this.recepcionari.entrySet()) {
	 
				pw.println(k.getKey() + ";" + k.getValue().toFileString());
			}
			pw.close();
		} catch (IOException e) {
			return false;
		}
		return true;
	}


	public void add(boolean obrisan, String ime, String prezime, String korisnickoIme, String lozinka, String pol, String telefon, String adresa,  StrucnaSprema nivoStrucneSpreme, double nivoRadnogStaza) {
		loadData();
		Recepcionar r = new Recepcionar(false, ime, prezime, korisnickoIme, lozinka, pol, telefon, adresa, nivoStrucneSpreme, nivoRadnogStaza);
		this.recepcionari.put(this.indeks, r);	
		r.setId(indeks);
		this.indeks += 1;
		this.saveData();
	}

	public void remove(int id) {
		this.recepcionari.remove(id);
		this.saveData();
	}

	public void printAll() {

		for (Map.Entry<Integer, Recepcionar> r : this.recepcionari.entrySet()) {
            System.out.println((r.getKey() + ";" + r.getValue().toString())); 
        }
	}
	
	public void printAllNotObrisan() {
		
		for (Map.Entry<Integer, Recepcionar> r : this.recepcionari.entrySet()) {
			if (r.getValue().getObrisan() == false) {
	            System.out.println((r.getKey() + ";" + r.getValue().toString())); 
			}
        }
	}
	
	public double saberiplate() {
		double plate = 0;
		
		for (Map.Entry<Integer, Recepcionar> r : this.recepcionari.entrySet()) {
			plate += r.getValue().getUkupnaPlata();
        }
		
		return plate;
	}

}

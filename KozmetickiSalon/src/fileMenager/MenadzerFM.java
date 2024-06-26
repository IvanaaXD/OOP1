package fileMenager;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import korisnici.Menadzer;
import korisnici.StrucnaSprema;

public class MenadzerFM {
	
	private String menadzerfile;
	private HashMap<Integer, Menadzer> menadzeri = new	HashMap<Integer, Menadzer>();
	private int indeks;
	
	public MenadzerFM() {
		indeks = 0;
	}
	
	public void setPath(String path) {
		this.menadzerfile = path;
	}
	
	public void setMenadzeri(HashMap<Integer, Menadzer> menadzeri) {
		this.menadzeri = menadzeri;
	}
	
	public HashMap<Integer, Menadzer> getMenadzeri() {
		return this.menadzeri;
	}
	
    public Menadzer PronadjiMenadzeraPoId(int id){
    	
    	Menadzer traziId = null;
	    
	    for (Map.Entry<Integer, Menadzer> entry : menadzeri.entrySet()) {
	        //Integer key = entry.getKey();
	    	Menadzer m = entry.getValue();
	        
	    	if (m.getId() == id)
	        {
	            traziId = m;
	            break;
	        }
		 }
	    return traziId;
	}
    
    public Menadzer PronadjiMenadzeraPoKorisnickomImenu(String korisnickoIme){
    	
    	Menadzer traziKorisnickoIme = null;
    
    	for (Map.Entry<Integer, Menadzer> entry : menadzeri.entrySet()) {
	        //Integer key = entry.getKey();
	    	Menadzer m = entry.getValue();
	        
	    	if (m.getKorisnickoIme().equals(korisnickoIme))
            {
            	traziKorisnickoIme = m;
                break;
            }
		 }
        return traziKorisnickoIme;
	}
	
	/*Ucitava iz fajla*/
	public boolean loadData() {
		try {
			BufferedReader br = new BufferedReader(new FileReader(this.menadzerfile));
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
				
				StrucnaSprema s;
				if (!dio[9].equals("null")) {
					s = StrucnaSprema.valueOf(dio[9]);
				} else {
					s = null;
				}
				
				double radniStaz = Double.parseDouble(dio[10]);

				boolean tekst = false;
				if(dio[11].equals("TRUE") || dio[11].equals("true")) {
					tekst = true;
				} 
				
				double bonus = Double.parseDouble(dio[12]);
				double plata = Double.parseDouble(dio[13]);
				double ukupno = Double.parseDouble(dio[14]);
				
				Menadzer m = new Menadzer(obrisan, dio[2], dio[3], dio[4], dio[5], dio[6], dio[7], dio[8], s, radniStaz, tekst, bonus, plata, ukupno);
				m.setId(id);
				//++this.indeks;
				menadzeri.put(id, m);
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
			pw = new PrintWriter(new FileWriter(this.menadzerfile, false));
			for (Map.Entry<Integer, Menadzer> k : this.menadzeri.entrySet()) {
				pw.println(k.getKey() + ";" + k.getValue().toFileString());
			}
			pw.close();
		} catch (IOException e) {
			return false;
		}
		return true;
	}


	public void add(boolean obrisan,String ime, String prezime, String korisnickoIme, String lozinka, String pol, String telefon, String adresa,  StrucnaSprema nivoStrucneSpreme, double nivoRadnogStaza) {
		Menadzer m = new Menadzer(false, ime, prezime, korisnickoIme, lozinka, pol, telefon, adresa, nivoStrucneSpreme, nivoRadnogStaza);

		this.menadzeri.put(this.indeks, m);	
		this.indeks += 1;
		this.saveData();
	}

	public void remove(int id) {
		this.menadzeri.remove(id);
		this.saveData();
	}
	
	public void printAll() {
		
		for (Map.Entry<Integer, Menadzer> m : this.menadzeri.entrySet()) {
            System.out.println((m.getKey() + ";" + m.getValue().toString())); 
        }
	}
	
	public void printAllNotObrisan() {
		
		for (Map.Entry<Integer, Menadzer> m : this.menadzeri.entrySet()) {
			if (m.getValue().getObrisan() == false) {
	            System.out.println((m.getKey() + ";" + m.getValue().toString())); 
			}
        }
	}
	
	public double saberiplate() {
		double plate = 0;
		
		for (Map.Entry<Integer, Menadzer> m : this.menadzeri.entrySet()) {
			plate += m.getValue().getUkupnaPlata();
        }
		
		return plate;
	}


}

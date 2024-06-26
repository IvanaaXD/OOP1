package fileMenager;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import korisnici.Kozmeticar;
import korisnici.StrucnaSprema;

public class KozmeticarFM {
	
	private String kozmeticarfile;
	private HashMap<Integer, Kozmeticar> kozmeticari = new HashMap<Integer, Kozmeticar>();
	private int indeks = 0;
			
	public KozmeticarFM() {
		indeks = 0;
	}
	
	public void setPath(String path) {
		this.kozmeticarfile = path;
	}
	
	public void setKozmeticari(HashMap<Integer, Kozmeticar> kozmeticari) {
		this.kozmeticari = kozmeticari;
	}
	
	public HashMap<Integer, Kozmeticar> getKozmeticar() {
		return this.kozmeticari;
	}
	
    public Kozmeticar PronadjiKozmeticaraPoId(int id){
    	
    	Kozmeticar traziId = null;
    	for (Map.Entry<Integer, Kozmeticar> entry : kozmeticari.entrySet()) {
	        //Integer key = entry.getKey();
    		Kozmeticar k = entry.getValue();
	        
	    	if (k.getId() == id)
	        {
	            traziId = k;
	            break;
	        }
		 }
	    return traziId;
	}
    
    public Kozmeticar PronadjiKozmeticaraPoKorisnickomImenu(String korisnickoIme){
    	
    	Kozmeticar traziKorisnickoIme = null;
    
	    for (Map.Entry<Integer, Kozmeticar> entry : kozmeticari.entrySet()) {
	        //Integer key = entry.getKey();
			Kozmeticar k = entry.getValue();
	        
			if (k.getKorisnickoIme().equals(korisnickoIme))
            {
            	traziKorisnickoIme = k;
                break;
            }
        }
        return traziKorisnickoIme;
	}
    
    public ArrayList<Integer> PronadjiKozmeticaraPoTipuUsluge(int tipUsluge){
    	
    	ArrayList<Integer> moguciKozmeticari = new ArrayList<Integer>();
    
	    for (Map.Entry<Integer, Kozmeticar> entry : kozmeticari.entrySet()) {
	        //Integer key = entry.getKey();
			Kozmeticar k = entry.getValue();
	        
			for (int i : k.getTretmani()) {
				if (i == tipUsluge) {
					moguciKozmeticari.add(k.getId());
				}
			}
        }
        return moguciKozmeticari;
	}
	
	/*Ucitava iz fajla*/
	public boolean loadData() {
		try {
			BufferedReader br = new BufferedReader(new FileReader(this.kozmeticarfile));
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
								
		        ArrayList<Integer> lista = new ArrayList<Integer>();
				String[] strlista;
				//String[] lista = new String[strlista.length];
								
				if (!dio[9].equals("null") && (!dio[9].equals("[]"))) {
								        
					 strlista = dio[9].split(",");
		        	for (int i = 0; i < strlista.length; i++) {
		        		String[] djelic = strlista[i].split("");
		        		int wuhu = Integer.parseInt(djelic[1]);
		        		lista.add(wuhu);        	
		        	}
				} 
				StrucnaSprema s;
				if (!dio[10].equals("null")) {
					s = StrucnaSprema.valueOf(dio[10]);
				} else {
					s = null;
				}
				double radniStaz = Double.parseDouble(dio[11]);

				boolean tekst = false;
				if(dio[12].equals("TRUE") || dio[12].equals("true")) {
					tekst = true;
				} 
				
				double bonus = Double.parseDouble(dio[13]);
				double plata = Double.parseDouble(dio[14]);
				double ukupno = Double.parseDouble(dio[15]);
				
				Kozmeticar k = new Kozmeticar(obrisan, dio[2], dio[3], dio[4], dio[5], dio[6], dio[7], dio[8], lista, s, radniStaz, tekst, bonus, plata, ukupno);
				k.setId(id);
				
				//++this.indeks;
				kozmeticari.put(id, k);
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
			pw = new PrintWriter(new FileWriter(this.kozmeticarfile, false));
			for (Map.Entry<Integer, Kozmeticar> k : kozmeticari.entrySet()) {
	 
				pw.println(k.getKey() + ";" + k.getValue().toFileString());
			}
			pw.close();
		} catch (IOException e) {
			return false;
		}
		return true;
	}
	
	//boolean obrisan, String ime, String prezime, String korisnickoIme, String lozinka, String pol, String telefon, String adresa, StrucnaSprema nivoStrucneSpreme, double nivoRadnogStaza, boolean imaBonus, double iznosBonusa, double plata, double ukupnaPlata

	public void add(boolean obrisan, String ime, String prezime, String korisnickoIme, String lozinka, String pol, String telefon, String adresa, StrucnaSprema nivoStrucneSpreme, double nivoRadnogStaza, ArrayList<Integer> kozmetickeUsluge) {
		Kozmeticar k = new Kozmeticar(false, ime, prezime, korisnickoIme, lozinka, pol, telefon, adresa, kozmetickeUsluge, nivoStrucneSpreme, nivoRadnogStaza);

		this.kozmeticari.put(this.indeks, k);
		this.indeks += 1;

		k.setId(indeks);
		this.saveData();
	}

	public void remove(int id) {
		this.kozmeticari.remove(id);
		this.saveData();
	}

	public void printAll() {

		for (Map.Entry<Integer, Kozmeticar> k : this.kozmeticari.entrySet()) {
            System.out.println((k.getKey() + ";" + k.getValue().toString())); 
        }
	}
	
	public void printAllNotObrisan() {
		
		for (Map.Entry<Integer, Kozmeticar> k : this.kozmeticari.entrySet()) {
			if (k.getValue().getObrisan() == false) {
	            System.out.println((k.getKey() + ";" + k.getValue().toString())); 
			}
        }
	}
	
	public void getListKlijent() {
		List<Kozmeticar> list = new ArrayList<>();
		for (Map.Entry<Integer, Kozmeticar> k : this.kozmeticari.entrySet()) {
	        list.add(k.getValue()); 
        }

	}
	
	public double saberiplate() {
		double plate = 0;
		
		for (Map.Entry<Integer, Kozmeticar> k : this.kozmeticari.entrySet()) {
			plate += k.getValue().getUkupnaPlata();
        }
		
		return plate;
	}

}

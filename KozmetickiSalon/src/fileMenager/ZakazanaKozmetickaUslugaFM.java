package fileMenager;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import osobineTretmana.Status;
import osobineTretmana.ZakazanaKozmetickaUsluga;

public class ZakazanaKozmetickaUslugaFM {

	private String zakazanakozmetickauslugafile;
	private HashMap<Integer, ZakazanaKozmetickaUsluga> zakazaneKozmetickeUsluge = new HashMap<Integer, ZakazanaKozmetickaUsluga>();
	public int indeks;
	
	public ZakazanaKozmetickaUslugaFM() {
		indeks = 0;
	}
	
	public void setPath(String path) {
		this.zakazanakozmetickauslugafile = path;
	}
	
	public HashMap<Integer, ZakazanaKozmetickaUsluga> getZakazaneKozmetickeUsluge() {
		return this.zakazaneKozmetickeUsluge;
	}
	
    public ZakazanaKozmetickaUsluga PronadjiZakazanuKozmetickuUsluguPoId(int id){
    	
    	ZakazanaKozmetickaUsluga traziId = null;
        
    	for (Map.Entry<Integer, ZakazanaKozmetickaUsluga> entry : zakazaneKozmetickeUsluge.entrySet()) {
            Integer key = entry.getKey();
            
    		ZakazanaKozmetickaUsluga zku = entry.getValue();
            
            if (key == id)
            {
                traziId = zku;
                break;
            }
   	 }
        return traziId;
    }
    
	/*Ucitava iz fajla*/
	public boolean loadData() {
		try {
			BufferedReader br = new BufferedReader(new FileReader(this.zakazanakozmetickauslugafile));
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
				
				Status s = Status.valueOf(dio[2]);
				LocalDate datum = LocalDate.parse(dio[3], DateTimeFormatter.ofPattern("yyyy-MM-dd"));
				LocalTime vrijeme = LocalTime.parse(dio[4]);
				double cijena = Double.parseDouble(dio[7]);
				int idKlijent = Integer.parseInt(dio[8]); 
				int idKozmeticar = Integer.parseInt(dio[9]); 

				ZakazanaKozmetickaUsluga zku = new ZakazanaKozmetickaUsluga(obrisana, s, datum, vrijeme, dio[5], dio[6], cijena, idKlijent, idKozmeticar);
				zku.setId(id);
				zakazaneKozmetickeUsluge.put(id, zku);
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
			pw = new PrintWriter(new FileWriter(this.zakazanakozmetickauslugafile, false));
			for (Entry<Integer, ZakazanaKozmetickaUsluga> zku : zakazaneKozmetickeUsluge.entrySet()) {
	 
				pw.println(zku.getKey() + ";" + zku.getValue().toFileString());
			}
			pw.close();
		} catch (IOException e) {
			return false;
		}
		return true;
	}


	public void add(boolean obrisana, Status stanje, LocalDate datum, LocalTime vrijeme, String tipUsluge, String trajanje, double cijena, int idKlijent, int idKozmeticar) {
		this.zakazaneKozmetickeUsluge.put(indeks, new ZakazanaKozmetickaUsluga(false, stanje, datum, vrijeme, tipUsluge, trajanje, cijena, idKlijent, idKozmeticar));	
		indeks += 1;
		this.saveData();
	}

	public void remove(int id) {
		this.zakazaneKozmetickeUsluge.remove(id);
		this.saveData();
	}
	
	public void printAll() {
		
		for (Map.Entry<Integer, ZakazanaKozmetickaUsluga> zku : this.zakazaneKozmetickeUsluge.entrySet()) {
            System.out.println((zku.getKey() + ";" + zku.getValue().toString())); 
        }
	}
	
	public void printAllNotObrisan() {
		
		for (Map.Entry<Integer, ZakazanaKozmetickaUsluga> zku : this.zakazaneKozmetickeUsluge.entrySet()) {
			if (zku.getValue().getObrisana() == false) {
	            System.out.println((zku.getKey() + ";" + zku.getValue().toString())); 
			}
        }
	}
	
	public void printAllKK() {
		
		KlijentFM k = new KlijentFM();
		KozmeticarFM kk = new KozmeticarFM();
		
		for (Map.Entry<Integer, ZakazanaKozmetickaUsluga> zku : this.zakazaneKozmetickeUsluge.entrySet()) {
            int idKlijent = zku.getValue().getIdKlijent();
            int idKozmeticar = zku.getValue().getIdKozmeticar();
            
            k.PronadjiKlijentaPoId(idKlijent);
            kk.PronadjiKozmeticaraPoId(idKozmeticar);
            
            System.out.println(k);
            System.out.println(kk);
          
        }
	}
	
	public boolean check(boolean obrisan, Status s, LocalDate datum, LocalTime vrijeme, String tipUsluge, String trajanje, double cijenaUsluge, int idKlijent, int idKozmeticar) {
		
		int k = 0;
		for (Map.Entry<Integer, ZakazanaKozmetickaUsluga> zku : this.zakazaneKozmetickeUsluge.entrySet()) {

            if (zku.getValue().getIdKozmeticar() == idKozmeticar) {
            	if (zku.getValue().getDatum().equals(datum)) {
            		if (zku.getValue().getVrijeme().equals(vrijeme)) {
                    	k = 1;

            		}
            	}	
            }
        }
		
		if (k == 0) {
			return false;
		} else {
			return true;
		}
	}
		
	public void findKozmeticar(int idKozmeticar) {
		
		for (Map.Entry<Integer, ZakazanaKozmetickaUsluga> zku : this.zakazaneKozmetickeUsluge.entrySet()) {

            if (zku.getValue().getIdKozmeticar() == idKozmeticar) {
                System.out.println((zku.getKey() + ";" + zku.getValue().toString())); 
	
            }
        }
	}
	
	public void findKlijent(int idKlijent) {
		
		for (Map.Entry<Integer, ZakazanaKozmetickaUsluga> zku : this.zakazaneKozmetickeUsluge.entrySet()) {

            if (zku.getValue().getIdKlijent() == idKlijent) {
                System.out.println((zku.getKey() + ";" + zku.getValue().toString())); 
	
            }
        }
	}
	
	public double setBilans(LocalDate localDateOd, LocalDate localDateDo) {
		
		double bilans = 0;
		
		for (Map.Entry<Integer, ZakazanaKozmetickaUsluga> zku : this.zakazaneKozmetickeUsluge.entrySet()) {

			if (zku.getValue().getDatum().isAfter(localDateOd) && zku.getValue().getDatum().isBefore(localDateDo)) {
	            if (zku.getValue().getStanje().toString().equals("IZVRŠEN")) {
	                bilans += zku.getValue().getCijena(); 
	            } else if (zku.getValue().getStanje().toString().equals("OTKAZAO_KLIJENT")) {
	            	bilans += 0.1 * zku.getValue().getCijena(); 
	            } else if (zku.getValue().getStanje().toString().equals("NIJE_SE_POJAVIO")) {
	            	bilans += zku.getValue().getCijena();
	            }
			}

        }
		return bilans;
		
	}
	
	public HashMap<Integer, Integer> setBonus() {
		
		HashMap<Integer, Integer> moguciBonusi = new HashMap<Integer, Integer>();
		
		for (Map.Entry<Integer, ZakazanaKozmetickaUsluga> zku : this.zakazaneKozmetickeUsluge.entrySet()) {

			if (zku.getValue().getStanje().equals(Status.IZVRŠEN)) {
	            if (moguciBonusi.containsKey(zku.getValue().getIdKozmeticar())) {
	            	Integer key = zku.getValue().getIdKozmeticar();
	            	Integer value = moguciBonusi.get(key);
	            	moguciBonusi.put(key, value + 1);  
	            } else {
	            	Integer key = zku.getValue().getIdKozmeticar();
	            	moguciBonusi.put(key, 1);  
	            }
			}

        }
		return moguciBonusi;
		
	}
	
}

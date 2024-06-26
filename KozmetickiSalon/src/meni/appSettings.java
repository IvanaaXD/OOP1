package meni;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import fileMenager.KlijentFM;
import fileMenager.KozmeticarFM;
import fileMenager.KozmetickaUslugaFM;
import fileMenager.KozmetickiSalonFM;
import fileMenager.MenadzerFM;
import fileMenager.RecepcionarFM;
import fileMenager.TipKozmetickeUslugeFM;
import fileMenager.ZakazanaKozmetickaUslugaFM;
import korisnici.Klijent;
import korisnici.Kozmeticar;
import korisnici.Menadzer;
import korisnici.Recepcionar;
import korisnici.StrucnaSprema;
import osobineTretmana.KozmetickaUsluga;
import osobineTretmana.KozmetickiSalon;
import osobineTretmana.Status;
import osobineTretmana.TipKozmetickeUsluge;
import osobineTretmana.ZakazanaKozmetickaUsluga;

public class appSettings {
	
	public HashMap<Integer, TipKozmetickeUsluge> tipovizabrisanje = new HashMap<Integer, TipKozmetickeUsluge>();
		
	KlijentFM Kfm = new KlijentFM();
	KozmeticarFM kfm = new KozmeticarFM();
	MenadzerFM mfm = new MenadzerFM();
	RecepcionarFM rfm = new RecepcionarFM();
	
	KozmetickaUslugaFM kufm = new KozmetickaUslugaFM();
	TipKozmetickeUslugeFM tkufm = new TipKozmetickeUslugeFM();
	ZakazanaKozmetickaUslugaFM zkufm = new ZakazanaKozmetickaUslugaFM();
	KozmetickiSalonFM ksfm = new KozmetickiSalonFM();
	
	Klijent k = new Klijent();
	Menadzer m = new Menadzer();
	Recepcionar r = new Recepcionar();
	Kozmeticar kk = new Kozmeticar();
	
	KozmetickaUsluga ku = new KozmetickaUsluga();
	TipKozmetickeUsluge tku = new TipKozmetickeUsluge();
	ZakazanaKozmetickaUsluga zku = new ZakazanaKozmetickaUsluga();
	KozmetickiSalon ks = new KozmetickiSalon();
	
	public KozmetickiSalonFM getksfm() {
		return this.ksfm;
	}
	
	public KlijentFM getKfm() {
		return this.Kfm;
	}
	
	public KozmeticarFM getkkfm() {
		return this.kfm;
	}
	
	public MenadzerFM getmfm() {
		return this.mfm;
	}
	
	public RecepcionarFM getrfm() {
		return this.rfm;
	}
	
	public KozmetickaUslugaFM getkufm() {
		return this.kufm;
	}
	
	public TipKozmetickeUslugeFM gettkufm() {
		return this.tkufm;
	}
	
	public ZakazanaKozmetickaUslugaFM getzkufm() {
		return this.zkufm;
	}
	
	public void setPathKozmetickiSalon(String path) {
		this.ksfm.setPath(path);
	}
	
	public void setPathKlijent(String path) {
		this.Kfm.setPath(path);
	}

	public void setPathKozmeticar(String path) {
		this.kfm.setPath(path);
	}
	
	public void setPathRecepcionar(String path) {
		this.rfm.setPath(path);
	}
	
	public void setPathMenadzer(String path) {
		this.mfm.setPath(path);
	}
	
	public void setPathKozmetickaUsluga(String path) {
		this.kufm.setPath(path);
	}
	
	public void setPathTipKozmetickeUsluge(String path) {
		this.tkufm.setPath(path);
	}
	
	public void setPathZakazanaKozmetickaUsluga(String path) {
		this.zkufm.setPath(path);
	}

	
	/*-----------------------------------------Podesavanja kozmetickog salona-----------------------------------------*/
	
	public void addPlataSvima(double plata) {
		mfm.loadData();
		rfm.loadData();
		kfm.loadData();
			
		ArrayList<Menadzer> menadzeri = new ArrayList<Menadzer>(mfm.getMenadzeri().values());
		ArrayList<Recepcionar> recepcionari = new ArrayList<Recepcionar>(rfm.getRecepcionar().values());
		ArrayList<Kozmeticar> kozmeticari = new ArrayList<Kozmeticar>(kfm.getKozmeticar().values());
		
		for (Menadzer m: menadzeri) {
			m.setPlata(plata);
			m.setUkupnaPlata();
		}
		
		for (Recepcionar r: recepcionari) {
			r.setPlata(plata);
			r.setUkupnaPlata();
		}
		
		for (Kozmeticar k: kozmeticari) {
			k.setPlata(plata);
			k.setUkupnaPlata();
		}
		
		mfm.saveData();
		rfm.saveData();
		kfm.saveData();
		
	}
	
	public void addKozmetickiSalon(String naziv, String pocetak, String kraj) {
		ksfm.loadData();
		LocalTime pocetakRadnogVremena = LocalTime.parse(pocetak);
		LocalTime krajRadnogVremena = LocalTime.parse(kraj);
		ksfm.add(naziv, pocetakRadnogVremena, krajRadnogVremena);
		ksfm.saveData();
	}
	
	public void changeKozmetickiSalonNaziv(String naziv, String noviNaziv) {
		ksfm.loadData(); 
		ks = ksfm.PronadjiKozmetickiSalonPoNazivu(naziv);
		ks.setNaziv(noviNaziv);
		ksfm.saveData();
	}
	
	public void changeKozmetickiSalonPocetak(String naziv, String pocetak) {
		ksfm.loadData();
		ks = ksfm.PronadjiKozmetickiSalonPoNazivu(naziv);
		LocalTime pocetakRadnogVremena = LocalTime.parse(pocetak);
		ks.setPocetakRadnogVremena(pocetakRadnogVremena);
		ksfm.saveData();
	}
	
	public void changeKozmetickiSalonKraj(String naziv, String kraj) {
		ksfm.loadData();
		ks = ksfm.PronadjiKozmetickiSalonPoNazivu(naziv);
		LocalTime krajRadnogVremena = LocalTime.parse(kraj);
		ks.setKrajRadnogVremena(krajRadnogVremena);
		ksfm.saveData();
	}
	
	public void printKozmetickiSalon() {
		ksfm.loadData();
		ksfm.printAll();
	}
	
	public double setKozmetickiSalonBilans(LocalDate localDateOd, LocalDate localDateDo) {
		double bilans = zkufm.setBilans(localDateOd, localDateDo);
		return bilans;
		
	}
	
	public void setBonus(double iznosBonusa, int brojtretmana) {
		zkufm.loadData();
		kfm.loadData();
		
		HashMap<Integer, Integer> moguciBonusi = zkufm.setBonus();
		
		for (Map.Entry<Integer, Integer> mBonusi : moguciBonusi.entrySet()) {
		    Integer key = mBonusi.getKey();
		    Integer value = mBonusi.getValue();
		    
		    if (value >= brojtretmana) {
		    	kk = kfm.PronadjiKozmeticaraPoId(key);
		    	kk.setImaBonus();
		    	kk.setIznosBonusa(iznosBonusa);
		    }
		}
		
		kfm.saveData();
	}
	
	public double getRashodi(LocalDate localDateOd, LocalDate localDateDo) {
				
		long monthsPassed = ChronoUnit.MONTHS.between(localDateOd.withDayOfMonth(1), localDateDo.withDayOfMonth(1));
		
		if (monthsPassed <= 0) {
			monthsPassed = 1;
		}
		
		kfm.loadData();
		double plateKozmeticari = kfm.saberiplate();
		
		rfm.loadData();
		double plateRecepcionari = rfm.saberiplate();
		
		mfm.loadData();
		double plateMenadzeri = mfm.saberiplate();
		
		double rashod = (plateKozmeticari + plateRecepcionari + plateMenadzeri)*monthsPassed;
		
		return rashod;
	}

	public ArrayList<LocalTime> setRadnoVrijemeKozmeticara(int idKozmeticar, LocalDate date) {
				
		kfm.loadData();
		kk = kfm.PronadjiKozmeticaraPoId(idKozmeticar);
		
		ksfm.loadData();
		ks = ksfm.PronadjiKozmetickiSalonPoId(0);
		
		zkufm.loadData();
		
		LocalTime datumOtvaranja = ks.getPocetakRadnogVremena();
		LocalTime datumZatvaranja = ks.getKrajRadnogVremena();
		
        Duration duration = Duration.between(datumOtvaranja, datumZatvaranja);
        double hours = duration.toHours();    
        
		int duzina = zkufm.getZakazaneKozmetickeUsluge().size();
		
		ArrayList<ZakazanaKozmetickaUsluga> zkulista = new ArrayList<ZakazanaKozmetickaUsluga>(zkufm.getZakazaneKozmetickeUsluge().values());
		ZakazanaKozmetickaUsluga zkuu = new ZakazanaKozmetickaUsluga();	
		
		ArrayList<LocalTime> radnoVrijeme = new ArrayList<LocalTime>();
		
		HashMap<LocalTime, String> zauzetost = new HashMap<LocalTime, String>();
		
		for (int i = 0; i < duzina; i++) {
			zkuu = zkulista.get(i);

			if (zkuu.getIdKozmeticar() == idKozmeticar && zkuu.getDatum().equals(date) ) {
				
				String trajanje = zkuu.getTrajanje();
				
				String trajanja[] = trajanje.split(" ");
		        int number = Integer.parseInt(trajanja[0]);
				
		        int sati = (int) Math.ceil((double) number / 60);
		        
		        LocalTime pocetak = zkuu.getVrijeme();
				LocalTime increasedTime = pocetak;

		        for (int w = 0; w < sati; w++) {
					increasedTime = pocetak.plusHours(w);

					zauzetost.put(increasedTime, zkuu.getTrajanje());

		        }
			}
		}
		
		LocalTime increasedTime = datumOtvaranja;
		
		for (int i = 0; i < hours; i++) {
			increasedTime = datumOtvaranja.plusHours(i);
			if (zauzetost.containsKey(increasedTime)) {
				continue;
			} else {
				radnoVrijeme.add(increasedTime);
			}
		}
		
		return radnoVrijeme;
		
	}
	
	public ArrayList<LocalTime> setRadnoVrijemeKozmeticaraEdit(int idKozmeticar, LocalDate date, int idZakazaneUsluge) {
				
		kfm.loadData();
		kk = kfm.PronadjiKozmeticaraPoId(idKozmeticar);
		
		ksfm.loadData();
		ks = ksfm.PronadjiKozmetickiSalonPoId(0);
		
		zkufm.loadData();
		
		LocalTime datumOtvaranja = ks.getPocetakRadnogVremena();
		LocalTime datumZatvaranja = ks.getKrajRadnogVremena();
		
        Duration duration = Duration.between(datumOtvaranja, datumZatvaranja);
        double hours = duration.toHours();    
        
		int duzina = zkufm.getZakazaneKozmetickeUsluge().size();
		
		ArrayList<ZakazanaKozmetickaUsluga> zkulista = new ArrayList<ZakazanaKozmetickaUsluga>(zkufm.getZakazaneKozmetickeUsluge().values());
		ZakazanaKozmetickaUsluga zkuu = new ZakazanaKozmetickaUsluga();	
		
		ArrayList<LocalTime> radnoVrijeme = new ArrayList<LocalTime>();
		
		HashMap<LocalTime, String> zauzetost = new HashMap<LocalTime, String>();
		
		
		for (int i = 0; i < duzina; i++) {
			zkuu = zkulista.get(i);

			if (zkuu.getIdKozmeticar() == idKozmeticar && zkuu.getDatum().equals(date) ) {
				
				if (zkuu.getId() == idZakazaneUsluge) {
					continue;
				} else {
				
				String trajanje = zkuu.getTrajanje();
				
				String trajanja[] = trajanje.split(" ");
		        int number = Integer.parseInt(trajanja[0]);
				
		        int sati = (int) Math.ceil((double) number / 60);
		        
		        LocalTime pocetak = zkuu.getVrijeme();
				LocalTime increasedTime = pocetak;

		        for (int w = 0; w < sati; w++) {
					increasedTime = pocetak.plusHours(w);

					zauzetost.put(increasedTime, zkuu.getTrajanje());

		        }}
			}
		}
		
		LocalTime increasedTime = datumOtvaranja;
		
		for (int i = 0; i < hours; i++) {
			increasedTime = datumOtvaranja.plusHours(i);
			if (zauzetost.containsKey(increasedTime)) {
				continue;
			} else {
				radnoVrijeme.add(increasedTime);
			}
		}
		
		return radnoVrijeme;
		
	}
	
	/*-----------------------------------------Podesavanja klijenta-----------------------------------------*/
	
	public void addKlijent(String ime, String prezime, String korisnickoIme, String lozinka, String pol, String telefon, String adresa, double kolicina, boolean kartica) {
		Kfm.loadData();
		Kfm.add(false, ime, prezime, korisnickoIme, lozinka, pol, telefon, adresa, kolicina, kartica);
		Kfm.saveData();
	}

	public void addKlijentLoyaltyCard(String korisnickoIme) {
		Kfm.loadData();
		k = Kfm.PronadjiKlijentaPoKorisnickomImenu(korisnickoIme);
		
		boolean obrisan = k.getObrisan();
		
		if (obrisan == false) {
			k.setKartica(true);
			Kfm.saveData();			
		}
	}
	
	public void changeKlijentName(String korisnickoIme, String ime) {
		Kfm.loadData();
		k = Kfm.PronadjiKlijentaPoKorisnickomImenu(korisnickoIme);
		
		//boolean obrisan = k.getObrisan();
		
		//if (obrisan == false) {
			k.setIme(ime);
			Kfm.saveData();		
		//}
	}

	public void changeKlijentSurname(String korisnickoIme, String prezime) {
		Kfm.loadData();
		k = Kfm.PronadjiKlijentaPoKorisnickomImenu(korisnickoIme);
		
		boolean obrisan = k.getObrisan();
		
		if (obrisan == false) {
			k.setPrezime(prezime);
			Kfm.saveData();		
		}	
	}	
	
	public void changeKlijentUsername(String korisnickoIme, String novoKorisnickoIme) {
		Kfm.loadData();
		k = Kfm.PronadjiKlijentaPoKorisnickomImenu(korisnickoIme);
		
		boolean obrisan = k.getObrisan();
		
		if (obrisan == false) {
			k.setKorisnickoIme(novoKorisnickoIme);
			Kfm.saveData();	
		}			
	}	
	
	public void changeKlijentPassword(String korisnickoIme, String lozinka) {
		Kfm.loadData();
		k = Kfm.PronadjiKlijentaPoKorisnickomImenu(korisnickoIme);
		
		boolean obrisan = k.getObrisan();
		
		if (obrisan == false) {
			k.setLozinka(lozinka);
			Kfm.saveData();
		}	
	}	
	
	public void changeKlijentSex(String korisnickoIme, String pol) {
		Kfm.loadData();
		k = Kfm.PronadjiKlijentaPoKorisnickomImenu(korisnickoIme);
		
		boolean obrisan = k.getObrisan();
		
		if (obrisan == false) {
			k.setPol(pol);
			Kfm.saveData();
		}			
	}	
	
	public void changeKlijentPhone(String korisnickoIme, String telefon) {
		Kfm.loadData();
		k = Kfm.PronadjiKlijentaPoKorisnickomImenu(korisnickoIme);
		
		boolean obrisan = k.getObrisan();
		
		if (obrisan == false) {
			k.setTelefon(telefon);
			Kfm.saveData();
		}				
	}	
	
	public void changeKlijentAdress(String korisnickoIme, String adresa) {
		Kfm.loadData();
		k = Kfm.PronadjiKlijentaPoKorisnickomImenu(korisnickoIme);
		
		boolean obrisan = k.getObrisan();
		
		if (obrisan == false) {
			k.setAdresa(adresa);
			Kfm.saveData();
		}			
	}
	
	public void changeKlijentAmount(String korisnickoIme, double potrosenaKolicina) {
		Kfm.loadData();
		k = Kfm.PronadjiKlijentaPoKorisnickomImenu(korisnickoIme);
		
		boolean obrisan = k.getObrisan();
		
		if (obrisan == false) {
			double trenutnaKolicina = k.getKolicina();
			k.setKolicina(potrosenaKolicina + trenutnaKolicina);
			Kfm.saveData();
		}
	}
	
	public void deleteKlijent(String korisnickoIme, boolean obrisan) {
		Kfm.loadData();
		k = Kfm.PronadjiKlijentaPoKorisnickomImenu(korisnickoIme);		
		k.setObrisan(obrisan);
		Kfm.saveData();
	}
	
	public void printKlijenti() {	
		Kfm.loadData();
		Kfm.printAll();
	}
	
	public void printKlijentiNotObrisan() {	
		Kfm.loadData();
		Kfm.printAllNotObrisan();
	}
	
	/*-----------------------------------------Podesavanja menadzera-----------------------------------------*/
	
	public void addMenadzer(String ime, String prezime, String korisnickoIme, String lozinka, String pol, String telefon, String adresa,  String nivoStrucneSpreme, double nivoRadnogStaza) {
		mfm.loadData();
		
		StrucnaSprema s = StrucnaSprema.valueOf(nivoStrucneSpreme);
		mfm.add(false, ime, prezime, korisnickoIme, lozinka, pol, telefon, adresa, s, nivoRadnogStaza);

		mfm.saveData();
	}
	
	public void addMenadzerNivoSS(String korisnickoIme, String nivoStrucneSpreme) { //dodavanje ili promjena nivoa strucne spreme
		mfm.loadData();
		m = mfm.PronadjiMenadzeraPoKorisnickomImenu(korisnickoIme);
		
		boolean obrisan = m.getObrisan();
		
		if (obrisan == false) {
			StrucnaSprema s = StrucnaSprema.valueOf(nivoStrucneSpreme);
			m.setNivoStrucneSpreme(s);
			mfm.saveData();	
		}
	}
	
	public void addMenadzerNivoRS(String korisnickoIme, double nivoRadnogStaza) { //dodavanje ili promjena nivoa radnog staza
		mfm.loadData();
		m = mfm.PronadjiMenadzeraPoKorisnickomImenu(korisnickoIme);
		
		boolean obrisan = m.getObrisan();
		
		if (obrisan == false) {
			m.setNivoRadnogStaza(nivoRadnogStaza);
			mfm.saveData();
		}
	}
	
	public void changeMenadzerName(String korisnickoIme, String ime) {
		mfm.loadData();
		m = mfm.PronadjiMenadzeraPoKorisnickomImenu(korisnickoIme);
		
		boolean obrisan = m.getObrisan();
		
		if (obrisan == false) {
			m.setIme(ime);
			mfm.saveData();
		}
	}

	public void changeMenadzerSurname(String korisnickoIme, String prezime) {
		mfm.loadData();
		m = mfm.PronadjiMenadzeraPoKorisnickomImenu(korisnickoIme);
		
		boolean obrisan = m.getObrisan();
		
		if (obrisan == false) {
			m.setPrezime(prezime);
			mfm.saveData();	
		}
	}	
	
	public void changeMenadzerUsername(String korisnickoIme, String novoKorisnickoIme) {
		mfm.loadData();
		m = mfm.PronadjiMenadzeraPoKorisnickomImenu(korisnickoIme);
		
		boolean obrisan = m.getObrisan();
		
		if (obrisan == false) {
			m.setKorisnickoIme(novoKorisnickoIme);
			mfm.saveData();		
		}		
	}	
	
	public void changeMenadzerPassword(String korisnickoIme, String lozinka) {
		mfm.loadData();
		m = mfm.PronadjiMenadzeraPoKorisnickomImenu(korisnickoIme);
		
		boolean obrisan = m.getObrisan();
		
		if (obrisan == false) {
			m.setLozinka(lozinka);
			mfm.saveData();	
		}	
	}	
	
	public void changeMenadzerSex(String korisnickoIme, String pol) {
		mfm.loadData();
		m = mfm.PronadjiMenadzeraPoKorisnickomImenu(korisnickoIme);
		
		boolean obrisan = m.getObrisan();
		
		if (obrisan == false) {
			m.setPol(pol);
			mfm.saveData();
		}			
	}	
	
	public void changeMenadzerPhone(String korisnickoIme, String telefon) {
		mfm.loadData();
		m = mfm.PronadjiMenadzeraPoKorisnickomImenu(korisnickoIme);
		
		boolean obrisan = m.getObrisan();
		
		if (obrisan == false) {
			m.setTelefon(telefon);
			mfm.saveData();	
		}			
	}	
	
	public void changeMenadzerAdress(String korisnickoIme, String adresa) {
		mfm.loadData();
		m = mfm.PronadjiMenadzeraPoKorisnickomImenu(korisnickoIme);
		
		boolean obrisan = m.getObrisan();
		
		if (obrisan == false) {
			m.setAdresa(adresa);
			mfm.saveData();	
		}
		
		System.out.println(mfm.getMenadzeri().size());
	}
	
	public void deleteMenadzer(String korisnickoIme, boolean obrisan) {
		mfm.loadData();
		m = mfm.PronadjiMenadzeraPoKorisnickomImenu(korisnickoIme);
		r.setObrisan(obrisan);;
		mfm.saveData();
	}
	
	public void printMenadzeri() {
		mfm.loadData();
		mfm.printAll();
	}
	
	public void printMenadzeriNotObrisan() {	
		mfm.loadData();
		mfm.printAllNotObrisan();
	}
	
	public void setLoyaltyCardValue(double iznos) {
		Kfm.setLCValue(iznos);
		Kfm.saveData();
	}
	
	/*-----------------------------------------Podesavanja recepcionara-----------------------------------------*/
	
	public void addRecepcionar(String ime, String prezime, String korisnickoIme, String lozinka, String pol, String telefon, String adresa, String nivoStrucneSpreme, double nivoRadnogStaza) {
		rfm.loadData();
		StrucnaSprema s = StrucnaSprema.valueOf(nivoStrucneSpreme);
		rfm.add(false, ime, prezime, korisnickoIme, lozinka, pol, telefon, adresa, s, nivoRadnogStaza);
		rfm.saveData();
	}
	
	public void addRecepcionarNivoSS(String korisnickoIme, String nivoStrucneSpreme) { //dodavanje ili promjena nivoa strucne spreme
		rfm.loadData();
		r = rfm.PronadjiRecepcionaraPoKorisnickomImenu(korisnickoIme);
		
		boolean obrisan = r.getObrisan();
		
		if (obrisan == false) {
			StrucnaSprema s = StrucnaSprema.valueOf(nivoStrucneSpreme);
			r.setNivoStrucneSpreme(s);
			rfm.saveData();
		}
	}
	
	public void addRecepcionarNivoRS(String korisnickoIme, double nivoRadnogStaza) { //dodavanje ili promjena nivoa radnog staza
		rfm.loadData();
		r = rfm.PronadjiRecepcionaraPoKorisnickomImenu(korisnickoIme);
		
		boolean obrisan = r.getObrisan();
		
		if (obrisan == false) {
			r.setNivoRadnogStaza(nivoRadnogStaza);
			rfm.saveData();
		}
	}
	
	public void changeRecepcionarName(String korisnickoIme, String ime) {
		rfm.loadData();
		r = rfm.PronadjiRecepcionaraPoKorisnickomImenu(korisnickoIme);
		
		boolean obrisan = r.getObrisan();
		
		if (obrisan == false) {
			r.setIme(ime);
			rfm.saveData();
		}
	}

	public void changeRecepcionarSurname(String korisnickoIme, String prezime) {
		rfm.loadData();
		r = rfm.PronadjiRecepcionaraPoKorisnickomImenu(korisnickoIme);
		
		boolean obrisan = r.getObrisan();
		
		if (obrisan == false) {
			r.setPrezime(prezime);
			rfm.saveData();	
		}
	}	
	
	public void changeRecepcionarUsername(String korisnickoIme, String novoKorisnickoIme) {
		rfm.loadData();
		r = rfm.PronadjiRecepcionaraPoKorisnickomImenu(korisnickoIme);
		
		boolean obrisan = r.getObrisan();
		
		if (obrisan == false) {
			r.setKorisnickoIme(novoKorisnickoIme);
			rfm.saveData();	
		}		
	}	
	
	public void changeRecepcionarPassword(String korisnickoIme, String lozinka) {
		rfm.loadData();
		r = rfm.PronadjiRecepcionaraPoKorisnickomImenu(korisnickoIme);
		
		boolean obrisan = r.getObrisan();
		
		if (obrisan == false) {
			r.setLozinka(lozinka);
			rfm.saveData();		
		}
	}	
	
	public void changeRecepcionarSex(String korisnickoIme, String pol) {
		rfm.loadData();
		r = rfm.PronadjiRecepcionaraPoKorisnickomImenu(korisnickoIme);
		
		boolean obrisan = r.getObrisan();
		
		if (obrisan == false) {
			r.setPol(pol);
			rfm.saveData();			
		}		
	}	
	
	public void changeRecepcionarPhone(String korisnickoIme, String telefon) {
		rfm.loadData();
		r = rfm.PronadjiRecepcionaraPoKorisnickomImenu(korisnickoIme);
		
		boolean obrisan = r.getObrisan();
		
		if (obrisan == false) {
			r.setTelefon(telefon);
			rfm.saveData();			
		}				
	}	
	
	public void changeRecepcionarAdress(String korisnickoIme, String adresa) {
		rfm.loadData();
		r = rfm.PronadjiRecepcionaraPoKorisnickomImenu(korisnickoIme);
		
		boolean obrisan = r.getObrisan();
		
		if (obrisan == false) {
			r.setAdresa(adresa);
			rfm.saveData();			
		}			
	}
	
	public void deleteRecepcionar(String korisnickoIme, boolean obrisan) {
		rfm.loadData();
		r = rfm.PronadjiRecepcionaraPoKorisnickomImenu(korisnickoIme);
		r.setObrisan(obrisan);
		rfm.saveData();
	}
	
	public void printRecepcionari() {
		rfm.loadData();
		rfm.printAll();
	}
	
	public void printRecepcionariNotObrisan() {	
		rfm.loadData();
		rfm.printAllNotObrisan();
	}
	
	/*-----------------------------------------Podesavanja kozmeticara-----------------------------------------*/
	
	public void addKozmeticar(String ime, String prezime, String korisnickoIme, String lozinka, String pol, String telefon, String adresa, String nivoStrucneSpreme, double nivoRadnogStaza, String kozmetickeUsluge) {
		kfm.loadData();
		kufm.loadData();
		StrucnaSprema s = StrucnaSprema.valueOf(nivoStrucneSpreme);
		ArrayList<Integer> idUsluga = new ArrayList<>();

		if (!kozmetickeUsluge.equals("")) {
			String[] uslugeArray = kozmetickeUsluge.split(",");
			List<String> uslugeList = new ArrayList<>();
			uslugeList.addAll(Arrays.asList(uslugeArray));
			
			
			for (String usluga : uslugeList) {
			    ku = kufm.PronadjiKozmetickuUsluguPoNazivu(usluga);
			    int indeks = ku.getId();
			    idUsluga.add(indeks);
			    
			}
		}

		
		kfm.add(false, ime, prezime, korisnickoIme, lozinka, pol, telefon, adresa, s, nivoRadnogStaza, idUsluga);
		kfm.saveData();
	}
	
	public void addKozmeticarNivoSS(String korisnickoIme, String nivoStrucneSpreme) { //dodavanje ili promjena nivoa strucne spreme
		kfm.loadData();
		kk = kfm.PronadjiKozmeticaraPoKorisnickomImenu(korisnickoIme);
		
		boolean obrisan = kk.getObrisan();
		
		if (obrisan == false) {
			StrucnaSprema s = StrucnaSprema.valueOf(nivoStrucneSpreme);
			kk.setNivoStrucneSpreme(s);
			kfm.saveData();
		}
	}
	
	public void addKozmeticarNivoRS(String korisnickoIme, double nivoRadnogStaza) { //dodavanje ili promjena nivoa radnog staza
		kfm.loadData();
		kk = kfm.PronadjiKozmeticaraPoKorisnickomImenu(korisnickoIme);
		
		boolean obrisan = kk.getObrisan();
		
		if (obrisan == false) {
			kk.setNivoRadnogStaza(nivoRadnogStaza);
			kfm.saveData();
		}
	}

	public void addKozmeticarListaUsluga(String korisnickoIme, String listaUsluga) {
		kfm.loadData();
		kufm.loadData();
		
		kk = kfm.PronadjiKozmeticaraPoKorisnickomImenu(korisnickoIme);
		
		//boolean obrisan = kk.getObrisan();
		ArrayList<Integer> lista = new ArrayList<Integer>();
		
		//if (obrisan == false) {
			if (!listaUsluga.equals("")) {
				
				String[] list = listaUsluga.split(",");
				
				for (int i = 0; i < list.length; i++) {
					
					ku = kufm.PronadjiKozmetickuUsluguPoNazivu(list[i]);
					int id = ku.getId();
					
		    		lista.add(id);
				}
			//}
			kk.setTretmani(lista);
			kfm.saveData();
		}
		
	}
	
	public void changeKozmeticarName(String korisnickoIme, String ime) {
		kfm.loadData();
		kk = kfm.PronadjiKozmeticaraPoKorisnickomImenu(korisnickoIme);
		
		boolean obrisan = kk.getObrisan();
		
		if (obrisan == false) {
			kk.setIme(ime);
			kfm.saveData();
		}
	}

	public void changeKozmeticarSurname(String korisnickoIme, String prezime) {
		kfm.loadData();
		kk = kfm.PronadjiKozmeticaraPoKorisnickomImenu(korisnickoIme);
		
		boolean obrisan = kk.getObrisan();
		
		if (obrisan == false) {
			kk.setPrezime(prezime);
			kfm.saveData();	
		}
	}	
	
	public void changeKozmeticarUsername(String korisnickoIme, String novoKorisnickoIme) {
		kfm.loadData();
		kk = kfm.PronadjiKozmeticaraPoKorisnickomImenu(korisnickoIme);
		
		boolean obrisan = kk.getObrisan();
		
		if (obrisan == false) {
			kk.setKorisnickoIme(novoKorisnickoIme);
			kfm.saveData();		
		}		
	}	
	
	public void changeKozmeticarPassword(String korisnickoIme, String lozinka) {
		kfm.loadData();
		kk = kfm.PronadjiKozmeticaraPoKorisnickomImenu(korisnickoIme);
		
		boolean obrisan = kk.getObrisan();
		
		if (obrisan == false) {
			kk.setLozinka(lozinka);
			kfm.saveData();			
		}
	}	
	
	public void changeKozmeticarSex(String korisnickoIme, String pol) {
		kfm.loadData();
		kk = kfm.PronadjiKozmeticaraPoKorisnickomImenu(korisnickoIme);
		
		boolean obrisan = kk.getObrisan();
		
		if (obrisan == false) {
			kk.setPol(pol);
			kfm.saveData();		
		}			
	}	
	
	public void changeKozmeticarPhone(String korisnickoIme, String telefon) {
		kfm.loadData();
		kk = kfm.PronadjiKozmeticaraPoKorisnickomImenu(korisnickoIme);
		
		boolean obrisan = kk.getObrisan();
		
		if (obrisan == false) {
			kk.setTelefon(telefon);
			kfm.saveData();			
		}		
	}	
	
	public void changeKozmeticarAdress(String korisnickoIme, String adresa) {
		kfm.loadData();
		kk = kfm.PronadjiKozmeticaraPoKorisnickomImenu(korisnickoIme);
		
		boolean obrisan = kk.getObrisan();
		
		if (obrisan == false) {
			kk.setAdresa(adresa);
			kfm.saveData();			
		}		
	}
	
	public void deleteKozmeticar(String korisnickoIme, boolean obrisan) {
		kfm.loadData();
		kk = kfm.PronadjiKozmeticaraPoKorisnickomImenu(korisnickoIme);
		kk.setObrisan(obrisan);
		kfm.saveData();
	}

	public void printKozmeticari() {
		kfm.loadData();
		kfm.printAll();
	}
	
	public void printKozmeticariNotObrisan() {	
		kfm.loadData();
		kfm.printAllNotObrisan();
	}
	
	/*-----------------------------------------Podesavanja kozmeticke usluge-----------------------------------------*/

	public void addKozmetickaUsluga(String nazivUsluge, String string) {
		kufm.loadData();
		
		ArrayList<Integer> lista = new ArrayList<Integer>();
		
		if (!string.equals("")) {
			
			String[] list = string.split(",");
			
			for (int i = 0; i < list.length; i++) {
	    		int l = Integer.parseInt(list[i]);
	    		lista.add(l);
			}
		}
		
		kufm.add(false, nazivUsluge, lista);
		kufm.saveData();
	}

	public void changeKozmetickaUslugaNaziv(String nazivUsluge, String noviNaziv) {
		kufm.loadData();
		ku = kufm.PronadjiKozmetickuUsluguPoNazivu(nazivUsluge);
		
		boolean obrisan = ku.getObrisan();
		
		if (obrisan == false) {
			ku.setNazivUsluge(noviNaziv);
			kufm.saveData();	
		}
	}	
	
	public void changeKozmetickaUslugaListaTipova(String nazivUsluge,String listaTipova) {
		kufm.loadData();
		ku = kufm.PronadjiKozmetickuUsluguPoNazivu(nazivUsluge);
		
		ArrayList<Integer> lista = new ArrayList<Integer>();
		
		String[] list = listaTipova.split(",");
		
		for (int i = 0; i < list.length; i++) {
    		int l = Integer.parseInt(list[i]);
    		lista.add(l);
		}
		
		ku.setListuTipova(lista);
		kufm.saveData();			
	}	
	
	public void appendKozmetickaUslugaListaTipova(String nazivUsluge, int id) {
		ku = kufm.PronadjiKozmetickuUsluguPoNazivu(nazivUsluge);
		
		ArrayList<Integer> lista = new ArrayList<Integer>();

		lista = ku.getListuTipova();

    	lista.add(id);
    	
		ku.setListuTipova(lista);
	}

	public void removeKozmetickaUslugaListaTipova(String nazivUsluge, int id) {
		ku = kufm.PronadjiKozmetickuUsluguPoNazivu(nazivUsluge);
		
		ArrayList<Integer> lista = new ArrayList<Integer>();

		lista = ku.getListuTipova();

    	//lista.remove(id);
		lista.remove(Integer.valueOf(id));
    	
		ku.setListuTipova(lista);
	}
	
	public void deleteKozmetickaUsluga(String nazivUsluge, boolean obrisan) {
		kufm.loadData();
		ku = kufm.PronadjiKozmetickuUsluguPoNazivu(nazivUsluge);
		ku.setObrisan(obrisan);
		kufm.saveData();
	}
	
	public void printKozmetickeUsluge() {	
		kufm.loadData();
		kufm.printAll();
	}
	
	public void printKozmetickaUslugaNotObrisan() {	
		kufm.loadData();
		kufm.printAllNotObrisan();
	}

	/*-----------------------------------------Podesavanja tipa kozmeticke usluge-----------------------------------------*/

	public void addTipKozmetickeUsluge(String tipUsluge, String trajanje, String nazivUsluge, double cijena) {
		tkufm.loadData();
		tkufm.add(false, tipUsluge, trajanje, nazivUsluge, cijena);
		tkufm.saveData();
	}
	
	public String addTipKozmetickeUslugeUKozmeticara(String korisnickoIme) {
		kfm.loadData();
		kk = kfm.PronadjiKozmeticaraPoKorisnickomImenu(korisnickoIme);
		ArrayList<Integer> tipovi = kk.getTretmani();
		
		String tip = "";
		tkufm.loadData();
		
		for (int indeks : tipovi) {
			tku = tkufm.PronadjiTipKozmetickeUslugePoId(indeks);
			String naziv = tku.getTipUsluge();
			tip += naziv + ",";
		}
		return tip;
	}
	
	public String addKozmetickaUslugaUKozmeticara() {
		kufm.loadData();
		ArrayList<KozmetickaUsluga> kozmetickeUsluge = new ArrayList<>(kufm.getKozmetickeUsluge().values());
		
		String tip = "";
		
		for (int i = 0; i < kozmetickeUsluge.size(); i++) {
			ku = kufm.PronadjiKozmetickuUsluguPoId(i);
			if (!ku.getObrisan()) {
				String naziv = ku.getNazivUsluge();
				tip += naziv + ",";
			}
		}
		return tip;
	}
	
	public String addTipKozmetickeUslugeUKozmeticaraGui(String naziv) {
		tkufm.loadData();
		ArrayList<TipKozmetickeUsluge> tipKozmetickeUsluge = new ArrayList<>(tkufm.getTipKozmetickeUsluge().values());
		
		String tipovi = "";
		
		for (int i = 0; i < tipKozmetickeUsluge.size(); i++) {
			tku = tkufm.PronadjiTipKozmetickeUslugePoId(i);
			String tip = tku.getNazivUsluge();

			if (naziv.equals(tip)){
				if (!tku.getObrisana()) {
					tipovi += tku.getTipUsluge() + ",";
				}
			}
		}
		return tipovi;
	}
	
	public String addTipKozmetickeUslugeUTrajanjeGui(String naziv) {
		tkufm.loadData();
		ArrayList<TipKozmetickeUsluge> tipKozmetickeUsluge = new ArrayList<>(tkufm.getTipKozmetickeUsluge().values());
		ArrayList<String> juhu = new ArrayList<String>();
		String tipovi = "";
		
		for (int i = 0; i < tipKozmetickeUsluge.size(); i++) {
			tku = tkufm.PronadjiTipKozmetickeUslugePoId(i);

			if (tku.getNazivUsluge().equals(naziv)) {
				if (!tku.getObrisana()) {
					if (!juhu.contains(tku.getTrajanje())) {
						juhu.add(tku.getTrajanje());
						tipovi += tku.getTrajanje() + ",";
					}
				}	
			}
		}
		return tipovi;
	}
	
	public ArrayList<Double> addTipKozmetickeUslugeUCijeneGui(String naziv) {
		tkufm.loadData();
		ArrayList<TipKozmetickeUsluge> tipKozmetickeUsluge = new ArrayList<>(tkufm.getTipKozmetickeUsluge().values());
		ArrayList<Double> listica = new ArrayList<Double>();
		
		for (int i = 0; i < tipKozmetickeUsluge.size(); i++) {
			tku = tkufm.PronadjiTipKozmetickeUslugePoId(i);

			if(tku.getNazivUsluge().equals(naziv)) {
				if (!tku.getObrisana()) {
					if (!listica.contains(tku.getCijena())) {
						listica.add(tku.getCijena());
					}
			}
			}

		}
		return listica;
	}
	
	public ArrayList<String> addTipKozmetickeUslugeUTipGui(String trajanje) {
		tkufm.loadData();
		ArrayList<TipKozmetickeUsluge> tipKozmetickeUsluge = new ArrayList<>(tkufm.getTipKozmetickeUsluge().values());
		ArrayList<String> juhu = new ArrayList<String>();
		
		for (int i = 0; i < tipKozmetickeUsluge.size(); i++) {
			tku = tkufm.PronadjiTipKozmetickeUslugePoId(i);
			String tt = tku.getTrajanje();

			if (trajanje.equals(tt)){
				if (!tku.getObrisana()) {
					if (!juhu.contains(tku.getTipUsluge())) {
						juhu.add(tku.getTipUsluge());
					}
				}
			}
		}
		return juhu;
	}
	
	public ArrayList<TipKozmetickeUsluge> addTipKozmetickeUslugeUTipGuii(String trajanje) {
		tkufm.loadData();
		ArrayList<TipKozmetickeUsluge> tipKozmetickeUsluge = new ArrayList<>(tkufm.getTipKozmetickeUsluge().values());
		ArrayList<TipKozmetickeUsluge> juhu = new ArrayList<TipKozmetickeUsluge>();
		
		for (int i = 0; i < tipKozmetickeUsluge.size(); i++) {
			tku = tkufm.PronadjiTipKozmetickeUslugePoId(i);
			String tt = tku.getTrajanje();

			if (trajanje.equals(tt)){
				if (!tku.getObrisana()) {
					if (!juhu.contains(tku)) {
						juhu.add(tku);
					}
				}
			}
		}
		return juhu;
	}
	
	public ArrayList<TipKozmetickeUsluge> addTipKozmetickeUslugeUCijenaGui(Double cijena) {
		tkufm.loadData();
		ArrayList<TipKozmetickeUsluge> tipKozmetickeUsluge = new ArrayList<>(tkufm.getTipKozmetickeUsluge().values());
		ArrayList<TipKozmetickeUsluge> juhu = new ArrayList<TipKozmetickeUsluge>();
		
		for (int i = 0; i < tipKozmetickeUsluge.size(); i++) {
			tku = tkufm.PronadjiTipKozmetickeUslugePoId(i);

			if (cijena == tku.getCijena()){
				if (!tku.getObrisana()) {
					if (!juhu.contains(tku)) {
						juhu.add(tku);
					}
				}
			}
		}
		return juhu;
	}
	
	public ArrayList<String> addKozmetickaUslugaUKozmeticaraZaKozmeticara(String kozmeticar) {
		kufm.loadData();
		kfm.loadData();
		
		kk = kfm.PronadjiKozmeticaraPoKorisnickomImenu(kozmeticar);
		ArrayList<Integer> kozmetickeUslugeId = kk.getTretmani();
		
		ArrayList<String> kozmetickeUsluge = new ArrayList<>();
				
		for (int i = 0; i < kozmetickeUslugeId.size(); i++) {
			ku = kufm.PronadjiKozmetickuUsluguPoId(i);
			String naziv = ku.getNazivUsluge();
			kozmetickeUsluge.add(naziv);
		}
		return kozmetickeUsluge;
	}
	
	
	public void addTipKozmetickeUslugeUKozmetickuUslugu(String tipUsluge, String nazivUsluge) {
		tkufm.loadData();
		kufm.loadData();
		
		tku = tkufm.PronadjiTipKozmetickeUslugePoTipu(tipUsluge);
		int id = tku.getId();
		appendKozmetickaUslugaListaTipova(nazivUsluge, id);
		kufm.saveData();
		
	}
	
	public void deleteTipKozmetickeUslugeIzKozmetickeUsluge(String tipUsluge, String nazivUsluge) {
		tkufm.loadData();
		kufm.loadData();
		
		tku = tkufm.PronadjiTipKozmetickeUslugePoTipu(tipUsluge);
		int id = tku.getId();
		removeKozmetickaUslugaListaTipova(nazivUsluge, id);
		kufm.saveData();
	}
	
	public void changeTipKozmetickeUslugeTip(String tipUsluge, String noviTip) {
		tkufm.loadData();
		tku = tkufm.PronadjiTipKozmetickeUslugePoTipu(tipUsluge);
		
		boolean obrisan = tku.getObrisana();
		
		if (obrisan == false) {
			tku.setTipUsluge(noviTip);
			tkufm.saveData();
		}
	}
	
	public void changeTipKozmetickeUslugeTrajanje(String tipUsluge, String trajanje) {
		tkufm.loadData();
		tku = tkufm.PronadjiTipKozmetickeUslugePoTipu(tipUsluge);
		tku.setTrajanje(trajanje);
		tkufm.saveData();
	}
	
	public void changeTipKozmetickeUslugeNaziv(String tipUsluge, String nazivUsluge) {
		tkufm.loadData();
		tku = tkufm.PronadjiTipKozmetickeUslugePoTipu(tipUsluge);
		
		boolean obrisan = tku.getObrisana();
		
		if (obrisan == false) {
			tku.setNazivUsluge(nazivUsluge);
			tkufm.saveData();
		}
	}
	
	public void changeTipKozmetickeUslugeCijena(String tipUsluge, double cijena) {
		tkufm.loadData();
		tku = tkufm.PronadjiTipKozmetickeUslugePoTipu(tipUsluge);
		
		boolean obrisan = tku.getObrisana();
		
		if (obrisan == false) {
			tku.setCijena(cijena);
			tkufm.saveData();
		}
	}
	
	public void deleteTipKozmetickeUsluge1(String tipUsluge, boolean obrisan) {
		tkufm.loadData();
		tku = tkufm.PronadjiTipKozmetickeUslugePoTipu(tipUsluge);
		tku.setObrisana(obrisan);
		tkufm.saveData();
	}
	
//	public void deleteTipKozmetickeUsluge2(String nazivUsluge) {
//		tkufm.loadData();
//		tipovizabrisanje = tkufm.PronadjiTipKozmetickeUslugePoNazivu(nazivUsluge);
//		
//		for (int i = 0; i < tipovizabrisanje.size(); i++) {
//			tku = tipovizabrisanje.get(i);
//			int indeks = tku.getId();
//			tkufm.remove(indeks);
//			tkufm.saveData();
//		}
//		
//	}
	
	public void printTipoviKozmetickeUsluge() {	
		tkufm.loadData();
		tkufm.printAll();
	}
	
	public void printTipoviKozmetickeUslugeNotObrisan() {	
		tkufm.loadData();
		tkufm.printAllNotObrisan();
	}

	/*-----------------------------------------Podesavanja zakazane kozmeticke usluge-----------------------------------------*/

	public void addZakazanaKozmetickaUsluga(String stanje, String strdatum, String strvrijeme, String tipUsluge, String korisnickoImeKlijent, String korisnickoImeKozmeticar) {
		zkufm.loadData();
		
		Status s = Status.valueOf(stanje);
		LocalDate datum = LocalDate.parse(strdatum, DateTimeFormatter.ofPattern("dd.MM.yyyy"));
		LocalTime vrijeme = LocalTime.parse(strvrijeme);
		
		tkufm.loadData();
		tku = tkufm.PronadjiTipKozmetickeUslugePoTipu(tipUsluge);
		double cijenaUsluge = tku.getCijena();
		String trajanje = tku.getTrajanje();

		Kfm.loadData();
		k = Kfm.PronadjiKlijentaPoKorisnickomImenu(korisnickoImeKlijent);
		int idKlijent = k.getId();
		boolean imaKarticu = k.getKartica();
		
		double cijena = cijenaUsluge;
		if (imaKarticu == true) {
			cijena = 0.9 * cijenaUsluge;
		}
		
		kfm.loadData();
		
		if (korisnickoImeKozmeticar.equals("")) {
			String nazivUsluge = tku.getNazivUsluge();
			
			kufm.loadData();
			ku = kufm.PronadjiKozmetickuUsluguPoNazivu(nazivUsluge);
			ArrayList<Integer> moguciKozmeticari = kfm.PronadjiKozmeticaraPoTipuUsluge(ku.getId());
			
			for (int i : moguciKozmeticari) {
				 boolean slobodno = zkufm.check(false, s, datum, vrijeme, tipUsluge, trajanje, cijenaUsluge, idKlijent, i);
				 if (slobodno) {
					 continue;
				 } else {
					 zkufm.add(slobodno, s, datum, vrijeme, tipUsluge, trajanje, cijena, idKlijent, i);
					 changeKlijentAmount(korisnickoImeKlijent, cijena);
					 zkufm.saveData();
					 break;
				 } 
			}
			
		} else {
			kk = kfm.PronadjiKozmeticaraPoKorisnickomImenu(korisnickoImeKozmeticar);
			int idKozmeticar = kk.getId();
			
			kfm.loadData();
			kk = kfm.PronadjiKozmeticaraPoId(idKozmeticar - 1);
			//int id = kk.getId();
			
			 boolean slobodno = zkufm.check(false, s, datum, vrijeme, tipUsluge, trajanje, cijenaUsluge, idKlijent, idKozmeticar);
			 if (slobodno) {
			 } else {
				 zkufm.add(slobodno, s, datum, vrijeme, tipUsluge, trajanje, cijena, idKlijent, idKozmeticar);
				 changeKlijentAmount(korisnickoImeKlijent, cijena);
				 zkufm.saveData();
			 } 
		}	
		zkufm.saveData();
 
	}
	
	public void addZakazanaKozmetickaUsluga1(String stanje, String strdatum, String strvrijeme, String tipUsluge, String korisnickoImeKlijent, int idKozmeticar) {
		zkufm.loadData();
		
		Status s = Status.valueOf(stanje);
		LocalDate datum = LocalDate.parse(strdatum, DateTimeFormatter.ofPattern("dd.MM.yyyy"));
		LocalTime vrijeme = LocalTime.parse(strvrijeme);
		
		tkufm.loadData();
		tku = tkufm.PronadjiTipKozmetickeUslugePoTipu(tipUsluge);
		double cijenaUsluge = tku.getCijena();
		String trajanje = tku.getTrajanje();
		
		Kfm.loadData();
		k = Kfm.PronadjiKlijentaPoKorisnickomImenu(korisnickoImeKlijent);
		int idKlijent = k.getId();
		boolean imaKarticu = k.getKartica();
		
		double cijena = cijenaUsluge;
		if (imaKarticu == true) {
			cijena = 0.9 * cijenaUsluge;
		}
		
		kfm.loadData();
		kk = kfm.PronadjiKozmeticaraPoId(idKozmeticar - 1);
		//int id = kk.getId();
		
		 boolean slobodno = zkufm.check(false, s, datum, vrijeme, tipUsluge, trajanje, cijena, idKlijent, idKozmeticar - 1);
		 if (slobodno) {
			 System.out.println("Tretman je zauzet");
		 } else {
			 zkufm.add(slobodno, s, datum, vrijeme, tipUsluge, trajanje, cijena, idKlijent, idKozmeticar - 1);
			 changeKlijentAmount(korisnickoImeKlijent, cijena);
			 zkufm.saveData();
			 System.out.println("Tretman uspjesno zakazan");
		 }  
	}
	
	public void changeZakazanaKozmetickaUslugaStatus(int id, String stanje) {
		zkufm.loadData();
		Status s = Status.valueOf(stanje);
		zku = zkufm.PronadjiZakazanuKozmetickuUsluguPoId(id);
		
		Kfm.loadData();
		int indeksKlijenta = zku.getIdKlijent();
		k = Kfm.PronadjiKlijentaPoId(indeksKlijenta);
		double potrosenaKolicina = k.getKolicina();
		double cijena = zku.getCijena();
		double trenutniIznos = potrosenaKolicina - cijena;

		//if (obrisana == false) {
		if (stanje.equals("OTKAZAO_KLIJENT")) {
			double kolicina = 0.1 * cijena;
			k.setKolicina(kolicina + trenutniIznos);
			zku.setStanje(s);
		} else if (stanje.equals("OTKAZAO_SALON")) {
			k.setKolicina(trenutniIznos);
			zku.setStanje(s);
		} else {
			zku.setStanje(s);
		}
		zkufm.saveData();
		Kfm.saveData();
		//}
	}
	
	public void changeZakazanaKozmetickaUslugaStatusAll() {
		zkufm.loadData();
		Kfm.loadData();
		
		Status s = Status.valueOf("OTKAZAO_SALON");
		ArrayList<ZakazanaKozmetickaUsluga> zkulista = new ArrayList<ZakazanaKozmetickaUsluga>(zkufm.getZakazaneKozmetickeUsluge().values());
		
		for (ZakazanaKozmetickaUsluga zz: zkulista) {
			if (zz.getStanje().equals(Status.ZAKAZAN)) {
				
				int indeksKlijenta = zz.getIdKlijent();
				k = Kfm.PronadjiKlijentaPoId(indeksKlijenta);
				double potrosenaKolicina = k.getKolicina();
				double cijena = zz.getCijena();
				double trenutniIznos = potrosenaKolicina - cijena;
				//boolean obrisana = zku.getObrisana();

				
				k.setKolicina(trenutniIznos);
				zz.setStanje(s);
				
			}
		}

		zkufm.saveData();
		Kfm.saveData();
		//}
	}

	public void changeZakazanaKozmetickaUslugaDate(int id, String strdatum) {
		zkufm.loadData();
		LocalDate datum = LocalDate.parse(strdatum, DateTimeFormatter.ofPattern("dd.MM.yyyy."));
		zku = zkufm.PronadjiZakazanuKozmetickuUsluguPoId(id);
		
		boolean obrisana = zku.getObrisana();
		
		if (obrisana == false) {
			zku.setDatum(datum);
			zkufm.saveData();
		}	
	}	
	
	public void changeZakazanaKozmetickaUslugaTime(int id, String strvrijeme) {
		zkufm.loadData();
		LocalTime vrijeme = LocalTime.parse(strvrijeme);
		zku = zkufm.PronadjiZakazanuKozmetickuUsluguPoId(id);
		
		boolean obrisana = zku.getObrisana();
		
		if (obrisana == false) {
			zku.setVrijeme(vrijeme);
			zkufm.saveData();
		}
		
		zkufm.saveData();
	}	
	
	public void changeZakazanaKozmetickaUslugaTypeOfServiceAll(int id, String tipUsluge) {
		zkufm.loadData();
		zku = zkufm.PronadjiZakazanuKozmetickuUsluguPoId(id);
		zku.setTipUsluge(tipUsluge);
		
		tkufm.loadData();
		tku = tkufm.PronadjiTipKozmetickeUslugePoTipu(tipUsluge);
		double cijena = tku.getCijena();
		String trajanje = tku.getTrajanje();
		
		zku.setCijena(cijena);
		zku.setTrajanje(trajanje);
		
		zkufm.saveData();	
	}		
	
	public void changeZakazanaKozmetickaUslugaTypeOfService(int id, String tipUsluge) {
		zkufm.loadData();
		zku = zkufm.PronadjiZakazanuKozmetickuUsluguPoId(id);
		
		//boolean obrisana = zku.getObrisana();
		
		//if (obrisana == false) {
			zku.setTipUsluge(tipUsluge);
			zkufm.saveData();
		//}	
	}	
	
	public void changeZakazanaKozmetickaUslugaPrice(int id, double cijena) {
		zkufm.loadData();
		zku = zkufm.PronadjiZakazanuKozmetickuUsluguPoId(id);
		
		boolean obrisana = zku.getObrisana();
		
		if (obrisana == false) {
			zku.setCijena(cijena);
			zkufm.saveData();
		}			
	}	
	
	public void changeZakazanaKozmetickaUslugaDuration(int id, String trajanje) {
		zkufm.loadData();
		zku = zkufm.PronadjiZakazanuKozmetickuUsluguPoId(id);
		
		boolean obrisana = zku.getObrisana();
		
		if (obrisana == false) {
			zku.setTrajanje(trajanje);
			zkufm.saveData();
		}	
	}
	
	public void changeZakazanaKozmetickaUslugaClient(int id, int idKlijenta) {
		zkufm.loadData();
		zku = zkufm.PronadjiZakazanuKozmetickuUsluguPoId(id);
		
		boolean obrisana = zku.getObrisana();
		
		if (obrisana == false) {
			zku.setIdKlijent(idKlijenta);
			zkufm.saveData();
		}			
	}	
	
	public void changeKZakazanaKozmetickaUslugaBeautician(int id, int idKozmeticara) {
		zkufm.loadData();
		zku = zkufm.PronadjiZakazanuKozmetickuUsluguPoId(id);
		
		boolean obrisana = zku.getObrisana();
		
		if (obrisana == false) {
			zku.setIdKozmeticar(idKozmeticara);
			zkufm.saveData();
		}			
	}
	
	public void deleteZakazanaKozmetickaUsluga(int id, boolean obrisana) {
		zkufm.loadData();
		zku = zkufm.PronadjiZakazanuKozmetickuUsluguPoId(id);
		zku.setObrisana(obrisana);
		zkufm.saveData();
	}
	
	public void printZakazaneKozmetickeUsluge() {	
		zkufm.loadData();
		zkufm.printAll();
	}
	
	public void printZakazaneKozmetickeUslugeNotObrisan() {	
		zkufm.loadData();
		zkufm.printAllNotObrisan();
	}
	
	public void printZakazanaKozmetickaUslugaZaKozmeticara(int idKozmeticara) {
		zkufm.loadData();
		
		zkufm.findKozmeticar(idKozmeticara);
	}
	
	public void printZakazanaKozmetickaUslugaZaKlijenta(String korisnickoIme) {
		zkufm.loadData();
		Kfm.loadData();
		
		k = Kfm.PronadjiKlijentaPoKorisnickomImenu(korisnickoIme);
		int idKlijenta = k.getId();
		System.out.println(k);
		
		zkufm.findKlijent(idKlijenta);
	}
	
	
	/*-----------------------------------------Izvjestaji-----------------------------------------*/

	public HashMap<Kozmeticar,ArrayList<Double>> izvjestajJedan(LocalDate datumPocetak, LocalDate datumKraj) {
		zkufm.loadData();
		kfm.loadData();
		
		ArrayList<ZakazanaKozmetickaUsluga> usluge = new ArrayList<ZakazanaKozmetickaUsluga>(zkufm.getZakazaneKozmetickeUsluge().values());
		ArrayList<Kozmeticar> kozmeticari = new ArrayList<Kozmeticar>(kfm.getKozmeticar().values());

		ArrayList<ZakazanaKozmetickaUsluga> uslugeUOpsegu = new ArrayList<ZakazanaKozmetickaUsluga>();

		for (ZakazanaKozmetickaUsluga zku: usluge) {
			if (zku.getStanje().equals(Status.IZVRŠEN)) {
				if (zku.getDatum().isAfter(datumPocetak) && zku.getDatum().isBefore(datumKraj)) {
					uslugeUOpsegu.add(zku);
				}
			}
		}
		
		HashMap<Kozmeticar,ArrayList<Double>> nasla = new HashMap<Kozmeticar,ArrayList<Double>>();
		
		for (Kozmeticar k : kozmeticari) {
			double brojUsluga = 0;
			double novac = 0;
			ArrayList<Double> vrijednosti = new ArrayList<Double>();

			for (ZakazanaKozmetickaUsluga zku: uslugeUOpsegu) {
				if (zku.getIdKozmeticar() == k.getId()) {
					novac += zku.getCijena();
					brojUsluga += 1;
				}				
			}
			
			vrijednosti.add(brojUsluga);
			vrijednosti.add(novac);
			nasla.put(k, vrijednosti);
		}
		return nasla;	
	}
	
	public HashMap<Status,Integer> izvjestajDva(LocalDate datumPocetak, LocalDate datumKraj) {
		zkufm.loadData();
		kfm.loadData();
		
		ArrayList<ZakazanaKozmetickaUsluga> usluge = new ArrayList<ZakazanaKozmetickaUsluga>(zkufm.getZakazaneKozmetickeUsluge().values());
		ArrayList<ZakazanaKozmetickaUsluga> uslugeUOpsegu = new ArrayList<ZakazanaKozmetickaUsluga>();

		for (ZakazanaKozmetickaUsluga zku: usluge) {
			if (zku.getDatum().isAfter(datumPocetak) && zku.getDatum().isBefore(datumKraj)) {
				uslugeUOpsegu.add(zku);
			}
		}
		
		ArrayList<Status> statusList = new ArrayList<>();

		for (Status status : Status.values()) {
		    statusList.add(status);
		}
		
		HashMap<Status,Integer> nasla = new HashMap<Status,Integer>();
		
		for (Status k : statusList) {
			int brojUsluga = 0;

			for (ZakazanaKozmetickaUsluga zku: uslugeUOpsegu) {
				if (zku.getStanje().equals(k)) {
					brojUsluga += 1;
				}				
			}
			nasla.put(k, brojUsluga);
		}
		return nasla;	
	}
	
	public HashMap<TipKozmetickeUsluge,ArrayList<Double>> izvjestajTri(LocalDate datumPocetak, LocalDate datumKraj) {
		zkufm.loadData();
		kfm.loadData();
		tkufm.loadData();
		
		ArrayList<ZakazanaKozmetickaUsluga> zakazaneUsluge = new ArrayList<ZakazanaKozmetickaUsluga>(zkufm.getZakazaneKozmetickeUsluge().values());
		ArrayList<TipKozmetickeUsluge> tipUsluge = new ArrayList<TipKozmetickeUsluge>(tkufm.getTipKozmetickeUsluge().values());

		ArrayList<ZakazanaKozmetickaUsluga> uslugeUOpsegu = new ArrayList<ZakazanaKozmetickaUsluga>();

		for (ZakazanaKozmetickaUsluga zku: zakazaneUsluge) {
			if (zku.getDatum().isAfter(datumPocetak) && zku.getDatum().isBefore(datumKraj)) {
				uslugeUOpsegu.add(zku);
			}
		}
		
		HashMap<TipKozmetickeUsluge,ArrayList<Double>> nasla = new HashMap<TipKozmetickeUsluge,ArrayList<Double>>();
		
		for (TipKozmetickeUsluge k: tipUsluge) {
			double brojUsluga = 0;
			double novac = 0;
			ArrayList<Double> vrijednosti = new ArrayList<Double>();

			for (ZakazanaKozmetickaUsluga zku: uslugeUOpsegu) {
				if (zku.getStanje().equals(Status.ZAKAZAN) && zku.getTipUsluge().equals(k.getTipUsluge())) {
					brojUsluga += 1;
				} else if (!zku.getStanje().equals(Status.ZAKAZAN)  && zku.getTipUsluge().equals(k.getTipUsluge())){
					novac += zku.getCijena();
				}
			}
			
			vrijednosti.add(brojUsluga);
			vrijednosti.add(novac);
			nasla.put(k, vrijednosti);
			
		}
		return nasla;	
	}
	
	public ArrayList<Klijent> izvjestajCetiri() {
		Kfm.loadData();

		ArrayList<Klijent> klijenti = new ArrayList<Klijent>(Kfm.getKlijenti().values());
		ArrayList<Klijent> nasla = new ArrayList<Klijent>();
		
		for (Klijent k: klijenti) {
			if (k.getKartica() == true) {
				nasla.add(k);
			}
		}
		return nasla;	
	}
	
	public HashMap<Kozmeticar,Integer> izvjestajJedanGrafik1() {
		zkufm.loadData();
		kfm.loadData();
		
		LocalDate datumKraj = LocalDate.now();
		LocalDate datumPocetak = datumKraj.minusDays(30);
		
		ArrayList<ZakazanaKozmetickaUsluga> usluge = new ArrayList<ZakazanaKozmetickaUsluga>(zkufm.getZakazaneKozmetickeUsluge().values());
		ArrayList<Kozmeticar> kozmeticari = new ArrayList<Kozmeticar>(kfm.getKozmeticar().values());

		ArrayList<ZakazanaKozmetickaUsluga> uslugeUOpsegu = new ArrayList<ZakazanaKozmetickaUsluga>();

		for (ZakazanaKozmetickaUsluga zku: usluge) {
			if (zku.getStanje().equals(Status.IZVRŠEN)) {
				if (zku.getDatum().isAfter(datumPocetak) && zku.getDatum().isBefore(datumKraj)) {
					uslugeUOpsegu.add(zku);
				}
			}
		}
		
		HashMap<Kozmeticar,Integer> nasla = new HashMap<Kozmeticar,Integer>();
		
		for (Kozmeticar k : kozmeticari) {
			int brojUsluga = 0;
			
			if (!k.getObrisan()) {
				for (ZakazanaKozmetickaUsluga zku: uslugeUOpsegu) {
					if (zku.getIdKozmeticar() == k.getId()) {
						brojUsluga += 1;
					}				
				}
				
				nasla.put(k, brojUsluga);
			}

		}
		return nasla;	
	}
	
	public HashMap<Kozmeticar,Integer> izvjestajJedanTest() {
		zkufm.loadData();
		kfm.loadData();
		
		ArrayList<ZakazanaKozmetickaUsluga> usluge = new ArrayList<ZakazanaKozmetickaUsluga>(zkufm.getZakazaneKozmetickeUsluge().values());
		ArrayList<Kozmeticar> kozmeticari = new ArrayList<Kozmeticar>(kfm.getKozmeticar().values());

		ArrayList<ZakazanaKozmetickaUsluga> uslugeUOpsegu = new ArrayList<ZakazanaKozmetickaUsluga>();

		for (ZakazanaKozmetickaUsluga zku: usluge) {
			if (zku.getStanje().equals(Status.IZVRŠEN)) {
					uslugeUOpsegu.add(zku);
			}
		}
		
		HashMap<Kozmeticar,Integer> nasla = new HashMap<Kozmeticar,Integer>();
		
		for (Kozmeticar k : kozmeticari) {
			int brojUsluga = 0;

			for (ZakazanaKozmetickaUsluga zku: uslugeUOpsegu) {
				if (zku.getIdKozmeticar() == k.getId()) {
					brojUsluga += 1;
				}				
			}
			
			nasla.put(k, brojUsluga);
		}
		return nasla;	
	}
	
	public HashMap<KozmetickaUsluga,HashMap<Date, Double>> izvjestajJedanGrafik2() {
		
		LocalDate datumKraj = LocalDate.now();
		LocalDate datumPocetak = datumKraj.minusYears(1);
		
		ArrayList<ZakazanaKozmetickaUsluga> zakazaneUsluge = new ArrayList<ZakazanaKozmetickaUsluga>(zkufm.getZakazaneKozmetickeUsluge().values());
		ArrayList<KozmetickaUsluga> usluge = new ArrayList<KozmetickaUsluga>(kufm.getKozmetickeUsluge().values());
		ArrayList<TipKozmetickeUsluge> tipUsluge = new ArrayList<TipKozmetickeUsluge>(tkufm.getTipKozmetickeUsluge().values());
		
		HashMap<KozmetickaUsluga,HashMap<Date, Double>> nasla = new HashMap<KozmetickaUsluga,HashMap<Date, Double>>();

		for (KozmetickaUsluga ku: usluge) {
			HashMap<Date, Double> mapica = new HashMap<Date, Double>();

			if (!ku.getObrisan()) {
				
		        LocalDate currentMonth = datumPocetak;
		        while (!currentMonth.isAfter(datumKraj)) {
		        	Date currentDate = Date.from(currentMonth.withDayOfMonth(1).atStartOfDay(ZoneId.systemDefault()).toInstant());
		        	mapica.put(currentDate, 0.0);

		            currentMonth = currentMonth.plusMonths(1);
		        }
				
				for (TipKozmetickeUsluge tku: tipUsluge) {
					
					if (ku.getNazivUsluge().equals(tku.getNazivUsluge())) {
						
						for (ZakazanaKozmetickaUsluga zku: zakazaneUsluge) {
							
							if (zku.getDatum().isAfter(datumPocetak) && zku.getDatum().isBefore(datumKraj)) {
								
								double bilans = 0;
					            if (zku.getStanje().equals(Status.IZVRŠEN)) {
					                bilans = zku.getCijena(); 
					            } else if (zku.getStanje().equals(Status.OTKAZAO_KLIJENT)) {
					            	bilans = 0.1 * zku.getCijena(); 
					            } else if (zku.getStanje().equals(Status.NIJE_SE_POJAVIO)) {
					            	bilans = zku.getCijena();
					            }
								
								LocalDate datum = zku.getDatum();
					        	Date current = Date.from(datum.withDayOfMonth(1).atStartOfDay(ZoneId.systemDefault()).toInstant());
					        	
					        	mapica.put(current, mapica.get(current) + bilans);
					            
							}
								
						}
					
					}
					
				} 			nasla.put(ku, mapica);

			}
				
		}

		return nasla;	
	}
	
	public HashMap<Status,Integer> izvjestajDvaGrafik() {
		zkufm.loadData();
		kfm.loadData();
		
		LocalDate datumKraj = LocalDate.now();
		LocalDate datumPocetak = datumKraj.minusDays(30);
		
		ArrayList<ZakazanaKozmetickaUsluga> usluge = new ArrayList<ZakazanaKozmetickaUsluga>(zkufm.getZakazaneKozmetickeUsluge().values());
		ArrayList<ZakazanaKozmetickaUsluga> uslugeUOpsegu = new ArrayList<ZakazanaKozmetickaUsluga>();

		for (ZakazanaKozmetickaUsluga zku: usluge) {
			if (zku.getDatum().isAfter(datumPocetak) && zku.getDatum().isBefore(datumKraj)) {
				uslugeUOpsegu.add(zku);
			}
		}
		
		ArrayList<Status> statusList = new ArrayList<>();

		for (Status status : Status.values()) {
		    statusList.add(status);
		}
		
		HashMap<Status,Integer> nasla = new HashMap<Status,Integer>();
		
		for (Status k : statusList) {
			int brojUsluga = 0;

			for (ZakazanaKozmetickaUsluga zku: uslugeUOpsegu) {
				if (zku.getStanje().equals(k)) {
					brojUsluga += 1;
				}				
			}
			nasla.put(k, brojUsluga);
		}
		
		return nasla;	
	}
	
}

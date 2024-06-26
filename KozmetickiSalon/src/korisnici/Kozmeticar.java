package korisnici;

import java.util.ArrayList;

public class Kozmeticar extends Zaposleni{
	
	private ArrayList<Integer> tretmani;
	//private String[] tretmaniIspis;
	
	public Kozmeticar() {
		super();
	}
	
	public Kozmeticar(boolean obrisan, String ime, String prezime, String korisnickoIme, String lozinka, String pol, String telefon, String adresa) {
		super(false, ime, prezime, korisnickoIme, lozinka, pol, telefon, adresa);
	}
	
	public Kozmeticar(boolean obrisan, String ime, String prezime, String korisnickoIme, String lozinka, String pol, String telefon, String adresa, ArrayList<Integer> tretmani, StrucnaSprema nivoStrucneSpreme, double nivoRadnogStaza) {
		super(obrisan, ime, prezime, korisnickoIme, lozinka, pol, telefon, adresa,nivoStrucneSpreme, nivoRadnogStaza);
		this.setTretmani(tretmani);
	}
	
	public Kozmeticar(boolean obrisan, String ime, String prezime, String korisnickoIme, String lozinka, String pol, String telefon, String adresa, ArrayList<Integer> tretmani, StrucnaSprema nivoStrucneSpreme, double nivoRadnogStaza, boolean imaBonus, double iznosBonusa, double plata, double ukupnaPlata) {
		super(obrisan, ime, prezime, korisnickoIme, lozinka, pol, telefon, adresa,nivoStrucneSpreme, nivoRadnogStaza, imaBonus, iznosBonusa, plata, ukupnaPlata);
		this.setTretmani(tretmani);
	}

	public ArrayList<Integer> getTretmani() {
		return tretmani;
	}

	public void setTretmani(ArrayList<Integer> tretmani) {
		this.tretmani = tretmani;
	}
	
	@Override
	public String toString() {
		return  "obrisan: " + this.obrisan + ", ime: " + this.ime + ", prezime: " + this.prezime + ", korisnicko ime: " + this.korisnickoIme + ", pol: " + this.pol + ", telefon: " + this.telefon + ", adresa: " + this.adresa+ "; nazivi tretmana: " + this.tretmani + ", strucna sprema: " + this.nivoStrucneSpreme + ", radni staz: " + this.nivoRadnogStaza + ", ima bonus: " + this.imaBonus + ", iznos bonusa: " + this.iznosBonusa + ", plata: " + this.plata + ", ukupna plata: " + this.ukupnaPlata;
		
	}
	
	public String toFileString() {
		return this.obrisan + ";" + this.ime + ";" + this.prezime + ";" + this.korisnickoIme + ";" + this.lozinka+  ";" + this.pol + ";" + this.telefon + ";" + this.adresa+ ";" + this.tretmani + ";" + this.nivoStrucneSpreme + ";" + this.nivoRadnogStaza + ";" + this.imaBonus + ";" + this.iznosBonusa + ";" + this.plata + ";" + this.ukupnaPlata;
	}

	
}

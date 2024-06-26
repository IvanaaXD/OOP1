package korisnici;

public class Menadzer extends Zaposleni{
	
	public Menadzer() {
		super();
	}
	
	public Menadzer(boolean obrisan, String ime, String prezime, String korisnickoIme, String lozinka, String pol, String telefon, String adresa) {
		super(false, ime, prezime, korisnickoIme, lozinka, pol, telefon, adresa);
	}

	public Menadzer(boolean obrisan, String ime, String prezime, String korisnickoIme, String lozinka, String pol, String telefon, String adresa, StrucnaSprema nivoStrucneSpreme, double nivoRadnogStaza) {
		super(obrisan, ime, prezime, korisnickoIme, lozinka, pol, telefon, adresa, nivoStrucneSpreme, nivoRadnogStaza);
	}	
	
	public Menadzer(boolean obrisan, String ime, String prezime, String korisnickoIme, String lozinka, String pol, String telefon, String adresa, StrucnaSprema nivoStrucneSpreme, double nivoRadnogStaza, boolean imaBonus, double iznosBonusa, double plata, double ukupnaPlata) {
		super(obrisan, ime, prezime, korisnickoIme, lozinka, pol, telefon, adresa, nivoStrucneSpreme, nivoRadnogStaza, imaBonus, iznosBonusa, plata, ukupnaPlata);
	}
	
}

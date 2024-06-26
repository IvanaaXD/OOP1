package korisnici;

public class Recepcionar extends Zaposleni{
	
	public Recepcionar() {
		super();
	}
	
	public Recepcionar(boolean obrisan, String ime, String prezime, String korisnickoIme, String lozinka, String pol, String telefon, String adresa) {
		super(false, ime, prezime, korisnickoIme, lozinka, pol, telefon, adresa);
	}
	
	public Recepcionar(boolean obrisan, String ime, String prezime, String korisnickoIme, String lozinka, String pol, String telefon, String adresa, StrucnaSprema nivoStrucneSpreme, double nivoRadnogStaza) {
		super(obrisan, ime, prezime, korisnickoIme, lozinka, pol, telefon, adresa, nivoStrucneSpreme, nivoRadnogStaza);
	}
	
	public Recepcionar(boolean obrisan, String ime, String prezime, String korisnickoIme, String lozinka, String pol, String telefon, String adresa, StrucnaSprema nivoStrucneSpreme, double nivoRadnogStaza, boolean imaBonus, double iznosBonusa, double plata, double ukupnaPlata) {
		super(obrisan, ime, prezime, korisnickoIme, lozinka, pol, telefon, adresa, nivoStrucneSpreme, nivoRadnogStaza, imaBonus, iznosBonusa, plata, ukupnaPlata);
	}

}

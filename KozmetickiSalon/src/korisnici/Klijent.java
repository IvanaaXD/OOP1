package korisnici;

public class Klijent extends Korisnik{
	
	private double potrosenaKolicina;
	private boolean karticaLojalnosti;
	
	public Klijent() {
		super();
	}
	  
	public Klijent(boolean obrisan, String ime, String prezime, String korisnickoIme, String lozinka, String pol, String telefon, String adresa) {
		super(false, ime, prezime, korisnickoIme, lozinka, pol, telefon, adresa);
	}
	
	public Klijent(boolean obrisan, String ime, String prezime, String korisnickoIme, String lozinka, String pol, String telefon, String adresa, double potrosenaKolicina, boolean karticaLojalnosti) {
		super(obrisan,ime, prezime, korisnickoIme, lozinka, pol, telefon, adresa);
		this.potrosenaKolicina = potrosenaKolicina;
		this.karticaLojalnosti = karticaLojalnosti;
	}
	
	public void setKolicina(double potrosenaKolicina) {
		this.potrosenaKolicina = potrosenaKolicina;
	}
	
	public double getKolicina() {
		return potrosenaKolicina;
	}
	
	public void setKartica(boolean karticaLojalnosti) {
		this.karticaLojalnosti = karticaLojalnosti;
	}
	
	public boolean getKartica() {
		return karticaLojalnosti;
	}
	
	public void imaKarticu() {
		
		if (this.karticaLojalnosti == true) {
			System.out.println("Ima karticu lojalnosti");
		}
	}
	
	public String toFileString() {
		return this.obrisan + ";" + this.ime + ";" + this.prezime + ";" + this.korisnickoIme + ";" + this.lozinka + ";" + this.pol + ";" + this.telefon + ";" + this.adresa + ";" + this.potrosenaKolicina + ";" + this.karticaLojalnosti;
		
	}
	
	@Override
	public String toString() {
		return "obrisan: " + this.obrisan + ", ime: " + this.ime + ", prezime: " + this.prezime + ", korisnicko ime: " + this.korisnickoIme + ", pol: " + this.pol + ", telefon: " + this.telefon + ", adresa:" + this.adresa + ", potrosena kolicina: " + this.potrosenaKolicina + ", kartica lojalnosti: " + this.karticaLojalnosti;
	}

}

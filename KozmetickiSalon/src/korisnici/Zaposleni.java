package korisnici;

abstract class Zaposleni extends Korisnik{
	
	protected boolean imaBonus = false;
	protected StrucnaSprema nivoStrucneSpreme;
	protected double nivoRadnogStaza;
	protected double iznosBonusa;
	protected double plata;
	protected double ukupnaPlata;
	
	public Zaposleni() {
		super();
	}
	
	public Zaposleni(boolean obrisan, String ime, String prezime, String korisnickoIme, String lozinka, String pol, String telefon, String adresa) {
		super(false, ime, prezime, korisnickoIme, lozinka, pol, telefon, adresa);
	}
	
	public Zaposleni(boolean obrisan, String ime, String prezime, String korisnickoIme, String lozinka, String pol, String telefon, String adresa, StrucnaSprema nivoStrucneSpreme, double nivoRadnogStaza) {
		super(obrisan, ime, prezime, korisnickoIme, lozinka, pol, telefon, adresa);
		this.nivoStrucneSpreme = nivoStrucneSpreme;
		this.nivoRadnogStaza = nivoRadnogStaza;
	}
	
	public Zaposleni(boolean obrisan, String ime, String prezime, String korisnickoIme, String lozinka, String pol, String telefon, String adresa, StrucnaSprema nivoStrucneSpreme, double nivoRadnogStaza, boolean imaBonus, double iznosBonusa, double plata, double ukupnaPlata) {
		super(obrisan, ime, prezime, korisnickoIme, lozinka, pol, telefon, adresa);
		this.nivoStrucneSpreme = nivoStrucneSpreme;
		this.nivoRadnogStaza = nivoRadnogStaza;
		this.imaBonus = imaBonus;
		this.iznosBonusa = iznosBonusa;
		this.plata = plata;
		this.ukupnaPlata = ukupnaPlata;
	}
	
	public void setNivoStrucneSpreme(StrucnaSprema nivoStrucneSpreme) {
		this.nivoStrucneSpreme = nivoStrucneSpreme;
	}
	
	public StrucnaSprema getNivoStrucneSpreme() {
		return nivoStrucneSpreme;
	}
	
	public void setNivoRadnogStaza(double nivoRadnogStaza) {
		this.nivoRadnogStaza = nivoRadnogStaza;
	}
	
	public double getNivoRadnogStaza() {
		return nivoRadnogStaza;
	}
	
	public void setImaBonus() {
		this.imaBonus = true;
	}
	
	public boolean getImaBonus() {
		return imaBonus;
	}
	
	public void setIznosBonusa(double iznosBonusa) {
		this.iznosBonusa = iznosBonusa;
	}
	
	public double getIznosBonusa() {
		return iznosBonusa;
	}
	
	public void setPlata(double plata) {
		double nns = 0;
		if (this.nivoStrucneSpreme.toString().equals("BEZ_KVALIFIKACIJE")) {
			nns = 1;
		} else if (this.nivoStrucneSpreme.toString().equals("OPSTA")) {
			nns = 1.5;
		} else if (this.nivoStrucneSpreme.toString().equals("STRUCNA")) {
			nns = 2;
		} else if (this.nivoStrucneSpreme.toString().equals("AKADEMSA")) {
			nns = 2.5;
		} else {
			nns = 3;
		}
		//Plata = OsnovaPlate + (Koeficijent * StručnaSprema * RadniStaž)
		this.plata = plata + this.nivoRadnogStaza * nns * 1000;
	}
	
	public double getPlata() {
		return plata;
	}
	
	public void setUkupnaPlata() {
		this.ukupnaPlata = this.plata + this.iznosBonusa;
	}
	
	public double getUkupnaPlata() {
		return ukupnaPlata;
	}
	
	@Override
	public String toString() {
		return "obrisan: " + this.obrisan + ", ime: " + this.ime + ", prezime: " + this.prezime + ", korisnicko ime: " + this.korisnickoIme + ", pol: " + this.pol + ", telefon: " + this.telefon + ", adresa: " + this.adresa + ", strucna sprema: " + this.nivoStrucneSpreme + ", radni staz: " + this.nivoRadnogStaza + ", ima bonus: " + this.imaBonus + ", iznos bonusa: " + this.iznosBonusa + ", plata: " + this.plata + ", ukupna plata: " + this.ukupnaPlata;
		
	}
	
	public String toFileString() {
		return this.obrisan + ";" + this.ime + ";" + this.prezime + ";" + this.korisnickoIme + ";" + this.lozinka+  ";" + this.pol + ";" + this.telefon + ";" + this.adresa + ";" + this.nivoStrucneSpreme + ";" + this.nivoRadnogStaza + ";" + this.imaBonus + ";" + this.iznosBonusa + ";" + this.plata + ";" + this.ukupnaPlata;
	}
	
}

package korisnici;

abstract class Korisnik {

	protected int id;
	protected String ime;
	protected String prezime;
	protected String korisnickoIme;
	protected String lozinka;
	protected String pol;
	protected String telefon;
	protected String adresa;
	protected boolean obrisan;

	public Korisnik() {
	}
	
	public Korisnik(boolean  obrisan, String ime, String prezime, String korisnickoIme, String lozinka, String pol, String telefon, String adresa) {
		this.obrisan = obrisan;
		this.ime = ime;
		this.prezime = prezime;
		this.korisnickoIme = korisnickoIme;
		this.lozinka = lozinka;
		this.pol = pol;
		this.telefon = telefon;
		this.adresa = adresa;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public boolean getObrisan() {
		return obrisan;
	}

	public void setObrisan(boolean obrisan) {
		this.obrisan = obrisan;
	}

	public String getIme() {
		return ime;
	}

	public void setIme(String ime) {
		this.ime = ime;
	}
	
	public String getPrezime() {
		return prezime;
	}

	public void setPrezime(String prezime) {
		this.prezime = prezime;
	}
	
	public String getKorisnickoIme() {
		return this.korisnickoIme;
	}

	public void setKorisnickoIme(String korisnickoIme) {
		this.korisnickoIme = korisnickoIme;
	}
	
	public String getLozinka() {
		return lozinka;
	}

	public void setLozinka(String lozinka) {
		this.lozinka = lozinka;
	}
	
	public String getPol() {
		return pol;
	}

	public void setPol(String pol) {
		this.pol = pol;
	}
	
	public String getTelefon() {
		return telefon;
	}

	public void setTelefon(String telefon) {
		this.telefon = telefon;
	}
	
	public String getAdresa() {
		return adresa;
	}

	public void setAdresa(String adresa) {
		this.adresa = adresa;
	}
	
}

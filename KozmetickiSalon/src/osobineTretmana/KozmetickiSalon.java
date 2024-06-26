package osobineTretmana;

import java.time.LocalTime;

public class KozmetickiSalon {

	private String naziv;
	private LocalTime pocetakRadnogVremena;
	private LocalTime krajRadnogVremena;
	private int id;
	
	public KozmetickiSalon() {
	}
	
	public KozmetickiSalon(String naziv, LocalTime pocetakRadnogVremena, LocalTime krajRadnogVremena) {
		this.naziv = naziv;
		this.pocetakRadnogVremena = pocetakRadnogVremena;
		this.krajRadnogVremena = krajRadnogVremena;
	}

	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public LocalTime getPocetakRadnogVremena() {
		return pocetakRadnogVremena;
	}

	public void setPocetakRadnogVremena(LocalTime pocetakRadnogVremena) {
		this.pocetakRadnogVremena = pocetakRadnogVremena;
	}

	public LocalTime getKrajRadnogVremena() {
		return krajRadnogVremena;
	}

	public void setKrajRadnogVremena(LocalTime krajRadnogVremena) {
		this.krajRadnogVremena = krajRadnogVremena;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	@Override
	public String toString() {
		return  "naziv: " + this.naziv + ", pocetak radnog vremena: " + this.pocetakRadnogVremena + ", kraj radnog vremena: " + this.krajRadnogVremena;
		
	}
	
	public String toFileString() {
		return this.naziv + ";" + this.pocetakRadnogVremena + ";" + this.krajRadnogVremena;
	}

}

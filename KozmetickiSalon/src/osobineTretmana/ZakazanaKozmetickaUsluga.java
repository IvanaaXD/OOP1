package osobineTretmana;

import java.time.LocalDate;
import java.time.LocalTime;

public class ZakazanaKozmetickaUsluga {
	
	private int id;
	private Status stanje;
	private LocalDate datum; 
	private LocalTime vrijeme;
	private String tipUsluge;
	private String trajanje;
	private double cijena;
	private int idKlijent;
	private int idKozmeticar;
	private boolean obrisana;
	
	public ZakazanaKozmetickaUsluga() {
	}
	
	public ZakazanaKozmetickaUsluga(boolean obrisana, Status stanje, LocalDate datum, LocalTime vrijeme, String tipUsluge, String trajanje, double cijena, int idKlijent, int idKozmeticar) {
		this.obrisana = obrisana;
		this.stanje = stanje;
		this.datum = datum;
		this.vrijeme = vrijeme;
		this.tipUsluge = tipUsluge;
		this.trajanje = trajanje;
		this.cijena = cijena;
		this.idKlijent = idKlijent;
		this.idKozmeticar = idKozmeticar;
	}

	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public boolean getObrisana() {
		return obrisana;
	}
	
	public void setObrisana(boolean obrisana) {
		this.obrisana = obrisana;
	}
	
	public Status getStanje() {
		return stanje;
	}
	
	public void setStanje(Status stanje) {
		this.stanje = stanje;
	}
	
	public LocalDate getDatum() {
		return datum;
	}
	
	public void setDatum(LocalDate datum) {
		this.datum = datum;
	}
	
	public LocalTime getVrijeme() {
		return vrijeme;
	}
	
	public void setVrijeme(LocalTime vrijeme) {
		this.vrijeme = vrijeme;
	}
	
	public String getTipUsluge() {
		return tipUsluge;
	}
	
	public void setTipUsluge(String tipUsluge) {
		this.tipUsluge = tipUsluge;
	}
	
	public String getTrajanje() {
		return trajanje;
	}

	public void setTrajanje(String trajanje) {
		this.trajanje = trajanje;
	}
	
	public double getCijena() {
		return cijena;
	}

	public void setCijena(double cijena) {
		this.cijena = cijena;
	}
	
	public int getIdKlijent() {
		return idKlijent;
	}
	
	public void setIdKlijent(int idKlijent) {
		this.idKlijent = idKlijent;
	}
	
	public int getIdKozmeticar() {
		return idKozmeticar;
	}
	
	public void setIdKozmeticar(int idKozmeticar) {
		this.idKozmeticar = idKozmeticar;
	}
		
	@Override
	public String toString() {
		return "obrisana: " + this.obrisana + ", stanje: " + this.stanje + "; datum: " + this.datum + "; vrijeme: " + this.vrijeme + "; tip usluge: " + this.tipUsluge + "; trajanje: " + this.trajanje + "; cijena: " + this.cijena + "; klijent: " + this.idKlijent + "; kozmeticar: " + this.idKozmeticar;
	}
	
	public String toFileString() {
		return this.obrisana + ";" + this.stanje + ";" + this.datum + ";" + this.vrijeme + ";" + this.tipUsluge + ";" + this.trajanje + ";" + this.cijena + ";"+ this.idKlijent + ";" + this.idKozmeticar;
	}

}

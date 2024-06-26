package osobineTretmana;

public class TipKozmetickeUsluge {

	private int id;
	private String tipUsluge;
	private String trajanje;
	private String nazivUsluge;
	private double cijena;
	private boolean obrisana;
	
	public TipKozmetickeUsluge() {
	}
	
	public TipKozmetickeUsluge(boolean obrisana, String tipUsluge, String trajanje, String nazivUsluge, double cijena) {
		this.tipUsluge = tipUsluge;
		this.trajanje = trajanje;
		this.nazivUsluge = nazivUsluge;
		this.cijena = cijena;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public int getId() {
		return id;
	}
	
	public void setObrisana(boolean obrisana) {
		this.obrisana = obrisana;
	}
	
	public boolean getObrisana() {
		return obrisana;
	}

	public void setTipUsluge(String tipUsluge) {
		this.tipUsluge = tipUsluge;
	}
	
	public String getTipUsluge() {
		return tipUsluge;
	}
	
	public String getTrajanje() {
		return trajanje;
	}

	public void setTrajanje(String trajanje) {
		this.trajanje = trajanje;
	}

	public String getNazivUsluge() {
		return nazivUsluge;
	}

	public void setNazivUsluge(String nazivUsluge) {
		this.nazivUsluge = nazivUsluge;
	}
	
	public double getCijena() {
		return cijena;
	}

	public void setCijena(double cijena) {
		this.cijena = cijena;
	}
	
	@Override
	public String toString() {
		return "obrisana: " + this.obrisana + ", tip usluge: " + this.tipUsluge + ", trajanje: " + this.trajanje + ", naziv usluge: " + this.nazivUsluge + ", cijena: " + this.cijena;
	}
	
	public String toFileString() {
		return this.obrisana + ";" + this.tipUsluge + ";" + this.trajanje + ";" + this.nazivUsluge + ";" + this.cijena;
	}

}

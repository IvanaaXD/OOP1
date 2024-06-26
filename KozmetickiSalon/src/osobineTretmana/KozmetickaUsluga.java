package osobineTretmana;

import java.util.ArrayList;

public class KozmetickaUsluga {

	private int id;
	private String nazivUsluge;
	private ArrayList<Integer> listaTipova;
	private boolean obrisana;
	//private String[] listaTipovaIspis;
	
	public KozmetickaUsluga(){
	}
	
	public KozmetickaUsluga(boolean obrisana, String nazivUsluge, ArrayList<Integer> listaTipova){
		this.obrisana = obrisana;
		this.nazivUsluge = nazivUsluge;
		this.listaTipova = listaTipova;
	}
	
	public void setObrisan(boolean obrisan) {
		this.obrisana = obrisan;
	}
	
	public void setNazivUsluge(String nazivUsluge) {
		this.nazivUsluge = nazivUsluge;
	}
	
	public void setListuTipova(ArrayList<Integer> listaTipova) {
		this.listaTipova = listaTipova;
	}
	
	public boolean getObrisan() {
		return obrisana;
	}
	
	public String getNazivUsluge() {
		return nazivUsluge;
	}
	
	public ArrayList<Integer> getListuTipova() {
		return listaTipova;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	@Override
	public String toString() {
		return "obrisana: " + this.obrisana + ", naziv usluge: " + this.nazivUsluge + "; lista tipova: " + this.listaTipova;
	}
	
	public String toFileString() {
		return this.obrisana + ";" + this.nazivUsluge + ";" + this.listaTipova;
	}

}

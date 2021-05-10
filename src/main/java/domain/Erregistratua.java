package domain;

import java.util.ArrayList;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

@Entity
public class Erregistratua extends Erabiltzailea {
	private double dirua;
	@OneToMany(fetch=FetchType.EAGER)
	private ArrayList<Erregistratua> erreplikatuak;
	private int bonoKop;

	public Erregistratua(String iz, String nan, String adina, String pasahitza) {
		super(iz, nan, adina, pasahitza, 0);
		dirua = 20.0;
		erreplikatuak = new ArrayList<Erregistratua>();
		bonoKop = 2;
	}
	
	public double getDirua() {
		return this.dirua;
	}
	
	public void setDirua(double d) {
		this.dirua = d;
	}
	
	public ArrayList<Erregistratua> erreplikatuListaLortu(){
		return this.erreplikatuak;
	}
	
	public void erreplikatuaGehitu(Erregistratua e) {
		erreplikatuak.add(e);
	}
	
	@Override
	public String toString() {
		return "Erabiltzaile izena: " + this.getIz() + ", NAN: " + this.getNan();
	}
	
	public int bonoKopLortu() {
		return bonoKop;
	}
	
	public void bonoBatKendu() {
		this.bonoKop--;
	}
	
	public void bonoakEguneratu(int z) {
		this.bonoKop = z;
	}
}

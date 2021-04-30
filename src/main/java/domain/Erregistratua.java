package domain;

import javax.persistence.Entity;

@Entity
public class Erregistratua extends Erabiltzailea {
	private double dirua;

	public Erregistratua(String iz, String nan, String adina, String pasahitza) {
		super(iz, nan, adina, pasahitza, 0);
		dirua = 20.0;
	}
	
	public double getDirua() {
		return this.dirua;
	}
	
	public void setDirua(double d) {
		this.dirua = d;
	}
}

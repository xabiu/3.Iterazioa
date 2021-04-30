package domain;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public abstract class Erabiltzailea {

	// private int nanZb;
	// private char nanL;
	@Id
	private String iz;
	
	private String nan;
	private String adina;
	private String pasahitza;
	private int erabMota;

	public Erabiltzailea(String iz, String nan, String adina, String pasahitza, int erabMota) {
		this.iz = iz;
		this.nan = nan;
		this.adina = adina;
		this.pasahitza = pasahitza;
		this.erabMota = erabMota;
	}

	public String getIz() {
		return iz;
	}

	public void setIz(String iz) {
		this.iz = iz;
	}

	public String getNan() {
		return nan;
	}

	public void setNan(String nan) {
		this.nan = nan;
	}

	public String getAdina() {
		return adina;
	}

	public void setAdina(String adina) {
		this.adina = adina;
	}

	public String getPasahitza() {
		return pasahitza;
	}

	public void setPasahitza(String pasahitza) {
		this.pasahitza = pasahitza;
	}

	public int getErabMota() {
		return erabMota;
	}

}

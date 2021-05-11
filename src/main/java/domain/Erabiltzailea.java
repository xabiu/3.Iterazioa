package domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlSeeAlso;

@SuppressWarnings("serial")
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
@XmlSeeAlso ({Erregistratua.class, Admin.class})

public abstract class Erabiltzailea implements Serializable{

	// private int nanZb;
	// private char nanL;
	@Id
	@XmlID
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

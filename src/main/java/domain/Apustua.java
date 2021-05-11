package domain;

import java.io.Serializable;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlIDREF;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@SuppressWarnings("serial")
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
public class Apustua implements Serializable {
	@Id
	@XmlID
	@XmlJavaTypeAdapter(IntegerAdapter.class)
	private String erabNAN;
	private int apostuZb;
	private double dirua;
	@OneToOne
	private String emaitza;

	public Apustua(String emaitza, double dirua, String erabNAN, int z) {
		this.emaitza = emaitza;
		this.dirua = dirua;
		this.erabNAN = erabNAN;
		this.apostuZb = z;
	}

	public int getZenbakia() {
		return this.apostuZb;
	}

	public String getEmaitza() {
		return emaitza;
	}

	public String getNAN() {
		return erabNAN;
	}

	public double getDirua() {
		return dirua;
	}
}

package domain;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Apustua {
	@Id
	private int apostuZb;
	private String emaitza;
	private double dirua;
	private String erabNAN;
	
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

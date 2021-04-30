
package domain;

import java.util.List;
import java.util.Vector;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Pronostikoa {
	private double kuota;
	@Id
	private int pronostikoZb;
	@OneToMany(fetch=FetchType.EAGER, cascade=CascadeType.ALL)
	private Vector<Apustua> apustuak=new Vector<Apustua>();
	
	public Pronostikoa() {
		super();
	}
	
	public Pronostikoa(double kuota, int pronostikoZb) {
		this.kuota = kuota;
		this.pronostikoZb = pronostikoZb;
		apustuak = null;
	}
	
	public void apustuaGehitu(Apustua a) {
		apustuak.add(a);
	}
	
	public int getPronostikoZb() {
		return this.pronostikoZb;
	}
	
	public List<Apustua> getApustuak(){
		return apustuak;
	}
	
	public double getKuota() {
		return kuota;
	}
}

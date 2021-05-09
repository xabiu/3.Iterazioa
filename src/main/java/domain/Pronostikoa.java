
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
	
	@OneToMany(fetch=FetchType.EAGER, cascade=CascadeType.ALL)
	private Vector<ApostuAnitza> apustuAnitzak=new Vector<ApostuAnitza>();
	private String Emaitza;
	
	public Pronostikoa() {
		super();
	}
	
	public Pronostikoa(double kuota, int pronostikoZb) {
		this.kuota = kuota;
		this.pronostikoZb = pronostikoZb;
		apustuak = new Vector<Apustua>();
		apustuAnitzak = new Vector<ApostuAnitza>();
	}
	
	public Pronostikoa(double kuota, int pronostikoZb, String emaitza) {
		this.kuota = kuota;
		this.pronostikoZb = pronostikoZb;
		apustuak = new Vector<Apustua>();
		this.Emaitza = emaitza;
		apustuAnitzak = new Vector<ApostuAnitza>();
	}
	
	public void apustuaGehitu(Apustua a) {
		apustuak.add(a);
	}
	
	public void apustuAnitzaGehitu(ApostuAnitza ap) {
		apustuAnitzak.add(ap);
	}
	
	public void setEmaitza(String e) {
		this.Emaitza = e;
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
	
	public String getEmaitza() {
		return this.Emaitza;
	}
	
	@Override
	public String toString() {
		return "Emaitza: " + this.Emaitza + ", eta " + "kuota: " + this.kuota; 
	}
}

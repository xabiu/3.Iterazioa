package domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlIDREF;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@SuppressWarnings("serial")
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
public class ApostuAnitza implements Serializable {
	@Id
	@XmlJavaTypeAdapter(IntegerAdapter.class)
	@XmlID
	private int apostuZB;
	private boolean egoera; // true bada apostua oraindik begira dezakegu, bestela ez.
	private double dirua;
	private String erabNAN;
	private double kuotaMetatua;
	@OneToMany(fetch = FetchType.EAGER)
	private ArrayList<Pronostikoa> pronoLista = new ArrayList<Pronostikoa>();

	public ApostuAnitza(int Zb, double dirua, String NAN, double kuotaM, ArrayList<Pronostikoa> l) {
		this.apostuZB = Zb;
		this.egoera = true;
		this.dirua = dirua;
		this.erabNAN = NAN;
		this.kuotaMetatua = kuotaM;
		this.pronoLista = l;
	}

	public void diruaGehitu(double d) {
		this.dirua += d;
	}

	public boolean badagoListan(Pronostikoa p) {
		for (Pronostikoa p1 : pronoLista) {
			if (p1.getPronostikoZb() == p.getPronostikoZb()) {
				return true;
			}
		}
		return false;
	}

	public List<Pronostikoa> apostuenListaLortu() {
		return this.pronoLista;
	}

	public boolean erabilgarritasunaLortu() {
		return egoera;
	}

	public String getNAN() {
		return this.erabNAN;
	}

	public double getDirua() {
		return this.dirua;
	}

	public double getKuota() {
		return this.kuotaMetatua;
	}

	public void kuotaMetatu(double kuota) {
		this.kuotaMetatua *= kuota;
	}

	public void listanGehitu(Pronostikoa p) {
		pronoLista.add(p);
	}

	public void listatikKenu(Pronostikoa p) {
		for (int i = 0; i < this.pronoLista.size(); i++) {
			if (p.getPronostikoZb() == this.pronoLista.get(i).getPronostikoZb()) {
				this.pronoLista.remove(i);
			}
		}
	}

	public void erabilgarritasunaKendu() {
		this.egoera = false;
	}

	public void setZenbakia(int z) {
		this.apostuZB = z;
	}

	public int getZenbakia() {
		return this.apostuZB;
	}

}

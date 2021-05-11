package domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@SuppressWarnings("serial")
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
public class Admin extends Erabiltzailea implements Serializable {

	private int adminZb;

	public Admin(String iz, String nan, String adina, String pasahitza, int adminZb) {
		super(iz, nan, adina, pasahitza, 1);
		this.adminZb = adminZb;
	}

	public int getAdminZb() {
		return adminZb;
	}

	public void setAdminZb(int adminZb) {
		this.adminZb = adminZb;
	}

}

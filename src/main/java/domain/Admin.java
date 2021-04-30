package domain;

import javax.persistence.Entity;

@Entity
public class Admin extends Erabiltzailea {
	
	private int adminZb;

	public Admin(String iz, String nan, String
		adina, String pasahitza, int adminZb) {
		super(iz, nan,adina,pasahitza,1);
		this.adminZb=adminZb;
	}

	public int getAdminZb() {
		return adminZb;
	}

	public void setAdminZb(int adminZb) {
		this.adminZb = adminZb;
	}
	
	
	
}

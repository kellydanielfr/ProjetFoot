package model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("ADMIN")
public class Admin extends Compte{

	public Admin(String nom, String prenom, Integer num_compte, String login, String password) {
		super(nom, prenom, num_compte, login, password);
	}
	
	public Admin() {}
	

	public Admin(String nom, String prenom, String login, String password) {
		super(nom, prenom, login, password);
	}

	@Override
	public String toString() {
		return "Admin [num_compte = " + num_compte + " nom=" + nom + ", prenom=" + prenom + ", num_compte=" + num_compte
				+ ", login=" + login + ", password=" + password + "]";
	}



}

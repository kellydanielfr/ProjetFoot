package model;

public class Admin extends Compte{
	private String droit="ADMIN";

	public Admin(String nom, String prenom, Integer num_compte, String login, String password) {
		super(nom, prenom, num_compte, login, password);
	}
	
	

	public Admin(String nom, String prenom, String login, String password) {
		super(nom, prenom, login, password);
	}



	public String getDroit() {
		return droit;
	}

	public void setDroit(String droit) {
		this.droit = droit;
	}



	@Override
	public String toString() {
		return "Admin [droit=" + droit + ", nom=" + nom + ", prenom=" + prenom + ", num_compte=" + num_compte
				+ ", login=" + login + ", password=" + password + "]";
	}



}

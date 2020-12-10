package model;

import javax.persistence.*;

@Entity
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="TYPE_COMPTE")
public abstract class Compte {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected Integer num_compte;
	protected String nom;
	protected String prenom;
	protected String login;
	protected String password;

	
	public Compte() {
	}

	public Compte(String nom, String prenom, Integer num_compte, String login, String password) {
		this.nom = nom;
		this.prenom = prenom;
		this.num_compte = num_compte;
		this.login = login;
		this.password = password;
	}
	
	public Compte(String nom, String prenom, String login, String password) {
		this.nom = nom;
		this.prenom = prenom;
		this.login = login;
		this.password = password;
	}

	public Integer getNum_compte() {
		return num_compte;
	}

	public void setNum_compte(Integer num_compte) {
		this.num_compte = num_compte;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}


	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	@Override
	public String toString() {
		return "Compte [num_compte=" + num_compte + ", login=" + login + ", password=" + password + "]";
	}
}

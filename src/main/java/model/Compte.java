package model;

import javax.persistence.*;

import Config.Context;

@Entity
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="droit")
public abstract class Compte {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected Integer num_compte;
	protected String nom;
	protected String prenom;
	@Column(unique = true)
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

	public static void showAllCompte() {
		System.out.println("\nListe des Comptes : \n");
		for(Compte c : Context.getInstance().getDaoCompte().selectAll()) 
		{
			System.out.println(c);
		}
	}
	
	public static void creatAdherent(Integer num_voie, String voie, String code_postal, String ville, String nom,
			String prenom, String login, String password, double solde) {
		Adresse adresse = new Adresse(num_voie, voie, code_postal,ville);
		Context.getInstance().getDaoCompte().ajouter(new Adherent(nom, prenom, login, password, solde, adresse));
	}
}

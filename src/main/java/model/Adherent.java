package model;

import javax.persistence.*;

import Config.Context;

@Entity
@DiscriminatorValue("ADHERENT")
public class Adherent extends Compte implements UserFunctions{
	
	private double solde;
	
	@Embedded
	private Adresse adresse;
	
	public Adherent() {}
	
	public Adherent(String nom, String prenom, Integer num_compte, String login, String password, double solde,
			 Adresse adresse) {
		super(nom, prenom, num_compte, login, password);
		this.solde = solde;
		this.adresse = adresse;
	}


	public Adherent(String nom, String prenom, String login, String password, double solde, Adresse adresse) {
		super(nom, prenom, login, password);
		this.solde = solde;
		this.adresse = adresse;
	}


	public Adresse getAdresse() {
		return adresse;
	}


	public void setAdresse(Adresse adresse) {
		this.adresse = adresse;
	}


	public double getSolde() {
		return solde;
	}


	public void setSolde(double solde) {
		this.solde = solde;
	}
	
	public void creditSolde(double credit) throws creditNotValid {
		//API Banque
		if(credit > 0) {
			this.setSolde(solde+credit);
		}else {
			throw new creditNotValid("Vous ne pouvez crediter un montant negatif");
		}
	}
	
	
	public static void achatTickts() {
		
	}
	
	public static void priseParis() {
		
	}
	
	public static void paiementSolde() {
		
	}

	
	public void consulterCatalog() {
		// TODO Auto-generated method stub
		
	}

	
	public void realiserAcahatProduit() {
		// TODO Auto-generated method stub
		
	}

	
	public void consulterEvent() {
		// TODO Auto-generated method stub
		
	}

	
	public void consulterClassement() {
		// TODO Auto-generated method stub
		
	}



	public String toString() {
		return "Adherent [num_compte = " + num_compte + " solde=" + solde + ",  adresse=" + adresse + ", nom=" + nom + ", prenom="
				+ prenom + ", num_compte=" + num_compte + ", login=" + login + ", password=" + password + "]";
	}


	
	
}

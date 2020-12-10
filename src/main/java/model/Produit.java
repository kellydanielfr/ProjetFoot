package model;

import javax.persistence.*;

@Entity
@Inheritance(strategy=InheritanceType.JOINED)
public abstract class Produit {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected Integer num_produit;
	protected double prix;
	protected Integer quantite;
	
	
	public Produit() {	}
	
	public Produit(double prix, Integer quantite) {
		this.prix = prix;
		this.quantite = quantite;
	}

	

	public Produit(Integer num_produit, double prix, Integer quantite) {
		this.num_produit = num_produit;
		this.prix = prix;
		this.quantite = quantite;
	}

	public double getPrix() {
		return prix;
	}

	public void setPrix(double prix) {
		this.prix = prix;
	}

	public Integer getQuantite() {
		return quantite;
	}

	public void setQuantite(Integer quantite) {
		this.quantite = quantite;
	}

	public Integer getNum_produit() {
		return num_produit;
	}

	public void setNum_produit(Integer num_produit) {
		this.num_produit = num_produit;
	}

	@Override
	public String toString() {
		return "Produit [ prix=" + prix + ", quantite=" + quantite
				+ "]";
	}
	
	

}

package model;

public abstract class Produit {
	
	protected double prix;
	protected Integer quantite;
	
	public Produit(double prix, Integer quantite) {
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

	@Override
	public String toString() {
		return "Produit [ prix=" + prix + ", quantite=" + quantite
				+ "]";
	}
	
	

}

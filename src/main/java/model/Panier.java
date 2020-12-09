package model;

import java.util.List;
import java.util.Map;

public class Panier {
	
	private Map<Produit, Integer> panier;
	private double total=0;
	
	
	public Panier() {}

	public void ajouterPanier(Article article, Integer qte) {
		panier.put(article,qte);
	}
	
	public static void totalPanier() {
		
	}
	
	public static void supprimerProduit() {
		
	}
	
	public static void miseAJourQteProduit() {
		
	}
	
	public static void showPanier() {
		
	}

	public Map<Produit,Integer> getPanier() {
		return panier;
	}

	public void setPanier(Map<Produit,Integer> panier) {
		this.panier = panier;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}
	
	
}

package model;

import java.util.List;
import java.util.Map;

import Config.Context;

public class Panier {
	
	private Map<Produit, Integer> panier;
	private double total=0;
	
	
	public Panier() {}

	public void ajouterPanier(Article article, Integer qte) {
		panier.put(article,qte);
	}
	
	public static void totalPanier() {
		
	}
	
	public void supprimerProduit(Integer num_article) {
		Article article = Context.getInstance().getDaoArticle().selectById(num_article);
		panier.remove(article);
	}
	
	public void miseAJourQteProduit(Integer num_article, int qte) {
		Article article = Context.getInstance().getDaoArticle().selectById(num_article);
		panier.put(article,qte);
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

package model;

import java.util.*;

import Config.Context;

public class Panier {
	
	private Map<Integer, Integer> panier = new HashMap<Integer, Integer>();
	
	public Panier() {}

	public void ajouterPanier(Produit produit, Integer qte) throws qteInsiffisante {
		if (qte<=produit.getQuantite()) {
			panier.put(produit.getNum_produit(),qte);
		}else {
			throw new qteInsiffisante("Quantite insiffisante");
		}
	}
	
	public double totalPanier() {
		Iterator<Integer> it=panier.keySet().iterator();
		double total = 0;
		while(it.hasNext()){
			Integer key=it.next();
			Produit produit = Context.getInstance().getDaoProduit().selectById(key);
			Integer qte = panier.get(key);
			total += produit.getPrix()*qte;
		}
		return total;
	}
	
	public void supprimerProduit(Integer num_article) {
		panier.remove(num_article);
	}
	
	public void miseAJourQteProduit(Integer num_article, int qte) {
		panier.put(num_article,qte);
	}

	public Map<Integer,Integer> getPanier() {
		return panier;
	}

	public void setPanier(Map<Integer,Integer> panier) {
		this.panier = panier;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((panier == null) ? 0 : panier.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Panier other = (Panier) obj;
		if (panier == null) {
			if (other.panier != null)
				return false;
		} else if (!panier.equals(other.panier))
			return false;
		return true;
	}

	public void payerCarte() {
		//TODO: APICarte
		majQte();
		viderPanier();
	}
	
	public void majQte() {
		Iterator<Integer> it=panier.keySet().iterator();
		double total = 0;
		while(it.hasNext()){
			Integer key=it.next();
			Produit produit = Context.getInstance().getDaoProduit().selectById(key);
			Integer qte = panier.get(key);
			produit.setQuantite(produit.getQuantite()-qte);
			Context.getInstance().getDaoProduit().modifier(produit);
		}
	}
	public void viderPanier() {
		panier =new HashMap<Integer, Integer>();
	}

	public void payerSolde(Compte connected) {
		if(((Adherent) connected).getSolde()>=totalPanier()) {
			((Adherent) connected).setSolde(((Adherent) connected).getSolde()-totalPanier());
			Context.getInstance().getDaoCompte().modifier(connected);
			majQte();
			viderPanier();
		}else {
			System.out.println("Solde insufisant ! Redirection vers payement par carte");
			payerCarte();
		}
	}

	public static void showPanier(Panier panier) {
		System.out.println(panier.getPanier());
		System.out.println("Total: "+ panier.totalPanier());	
	}
}

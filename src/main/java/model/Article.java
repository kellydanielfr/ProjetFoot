package model;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;

import Config.Context;

@Entity
public class Article extends Produit{
	
	@PrimaryKeyJoinColumn(name="code_article",referencedColumnName="id")
	private String nom;
	private String taille;
	protected String description;
	
	
	public Article() {
	}

	public Article(String nom, double prix, Integer quantite, String taille, String description) {
		super(prix, quantite);
		this.nom=nom;
		this.taille = taille;
		this.description = description;
	}
	
	public Article(Integer num_produit, String nom, double prix, Integer quantite,  String taille, String description) {
		super(num_produit, prix, quantite);
		this.nom = nom;
		this.taille = taille;
		this.description = description;
	}

	public String getNom() {
		return nom;
	}


	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getTaille() {
		return taille;
	}

	public void setTaille(String taille) {
		this.taille = taille;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public static void showAllArticle() {
		System.out.println("\nListe des Articles : \n");
		for(Article a : Context.getInstance().getDaoArticle().selectAll()) 
		{
			System.out.println(a);
		}
	}
	
	public static void creatArticle(String nom, double prix, Integer quantite, String taille, String description) {
		Article a = new Article(nom, prix, quantite, taille, description);
		Context.getInstance().getDaoArticle().ajouter(a);
	}
	
	@Override
	public String toString() {
		return "Article [num_produit = " + num_produit + " nom=" + nom + ", taille=" + taille + ", description="
				+ description + ", prix=" + prix + ", quantite=" + quantite + "]";
	}





	
}

package model;

public class Article extends Produit{
	private Integer code_article;
	private String nom;
	private String taille;
	protected String description;
	
	public Article(Integer code_article, String nom, double prix, Integer quantite, String taille, String description) {
		super(prix, quantite);
		this.nom=nom;
		this.code_article=code_article;
		this.taille = taille;
		this.description = description;
	}

	public Article(String nom, double prix, Integer quantite, String taille, String description) {
		super(prix, quantite);
		this.nom=nom;
		this.taille = taille;
		this.description = description;
	}
	
	public String getNom() {
		return nom;
	}


	public void setNom(String nom) {
		this.nom = nom;
	}


	public Integer getCode_article() {
		return code_article;
	}

	public void setCode_article(Integer code_article) {
		this.code_article = code_article;
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

	@Override
	public String toString() {
		return "Article [code_article=" + code_article + ", nom=" + nom + ", taille=" + taille + ", description="
				+ description + ", prix=" + prix + ", quantite=" + quantite + "]";
	}



	
}

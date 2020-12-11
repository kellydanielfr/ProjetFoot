package model;

import java.time.LocalDate;

import javax.persistence.*;

import Config.Context;

@Entity
public class Evenement {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id_evenement;
	@Column(columnDefinition = "DATE")
	private LocalDate date;
	private String titre;
	private String description;

	public Evenement(Integer id_evenement,LocalDate date, String titre, String description) {
		this.id_evenement=id_evenement;
		this.date = date;
		this.titre = titre;
		this.description = description;
	}
	
	
	public Evenement() {
		super();
	}


	public Evenement(LocalDate date, String titre, String description) {
		this.date = date;
		this.titre = titre;
		this.description = description;
	}
	
	public Integer getId_evenement() {
		return id_evenement;
	}


	public void setId_evenement(Integer id_evenement) {
		this.id_evenement = id_evenement;
	}


	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}
	public String getTitre() {
		return titre;
	}
	
	public void setTitre(String titre) {
		this.titre = titre;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	public static void showAllEvenement() {
		System.out.println("\nListe des Evenement : \n");
		for(Evenement e : Context.getInstance().getDaoEvenement().selectAll()) 
		{
			System.out.println(e);
		}	
	}
	
	public static void creatEvenement(LocalDate date, String titre, String description) {
		Evenement ev = new Evenement(date, titre, description);
		Context.getInstance().getDaoEvenement().ajouter(ev);
	}
	
	@Override
	public String toString() {
		return "Evenement n°" + id_evenement + ", Date de l'eveneent le " + date + ", Titre de l'evenement : " + titre + 
				", Description de l'evenement : " + description;
	}
}

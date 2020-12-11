package model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;

import Config.Context;

@Entity
public class Ticket extends Produit{
	
	@PrimaryKeyJoinColumn(name="code_ticket",referencedColumnName="id")
	@Column(columnDefinition = "DATE")
	private LocalDate date;
	private String lieu;
	
	public Ticket(double prix, Integer quantite, LocalDate date, String lieu) {
		super(prix, quantite);
		this.date = date;
		this.lieu = lieu;
	}

	public Ticket(Integer num_produit, double prix, Integer quantite, LocalDate date, String lieu) {
		super(num_produit, prix, quantite);
		this.date = date;
		this.lieu = lieu;
	}

	public Ticket() {}


	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public String getLieu() {
		return lieu;
	}

	public void setLieu(String lieu) {
		this.lieu = lieu;
	}

	public static void showAllTicket() {
		System.out.println("\nListe des Tickets : \n");
		for(Ticket t : Context.getInstance().getDaoTicket().selectAll()) 
		{
			System.out.println(t);
		}	
	}
	
	@Override
	public String toString() {
		return "Ticket [num_produit = " + num_produit + " date=" + date + ", lieu=" + lieu + ", prix=" + prix
				+ ", quantite=" + quantite + "]";
	}


}

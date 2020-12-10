package model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity
public class Ticket extends Produit{
	
	@PrimaryKeyJoinColumn(name="code_ticket",referencedColumnName="id")
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

	@Override
	public String toString() {
		return "Ticket [date=" + date + ", lieu=" + lieu + ", prix=" + prix
				+ ", quantite=" + quantite + "]";
	}


}

package model;

import java.time.LocalDate;

public class Ticket extends Produit{
	private Integer code_ticket;
	private LocalDate date;
	private String lieu;
	
	public Ticket(Integer code_ticket, double prix, Integer quantite, LocalDate date, String lieu) {
		super(prix, quantite);
		this.code_ticket=code_ticket;
		this.date = date;
		this.lieu = lieu;
	}
	
	public Ticket(double prix, Integer quantite, LocalDate date, String lieu) {
		super(prix, quantite);
		this.date = date;
		this.lieu = lieu;
	}

	public Integer getCode_ticket() {
		return code_ticket;
	}

	public void setCode_ticket(Integer code_ticket) {
		this.code_ticket = code_ticket;
	}

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
		return "Ticket [code_ticket=" + code_ticket + ", date=" + date + ", lieu=" + lieu + ", prix=" + prix
				+ ", quantite=" + quantite + "]";
	}


}

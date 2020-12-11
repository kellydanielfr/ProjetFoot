package model;

import java.time.LocalDate;

import javax.persistence.*;

import com.sun.istack.Nullable;
@Entity
public class SiteParis {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id_match;
	//private int classement;
	private double cotesNull;
	private double cotesTeam1;
	private double cotesTeam2;
	private String team1;
	private String team2;
	@Column(columnDefinition = "DATE")
	private LocalDate date;
	@Nullable
	private String results=null;
	
	
	
	
	public SiteParis() {
		
	}
	




	public double getCotesNull() {
		return cotesNull;
	}




	public void setCotesNull(double cotesNull) {
		this.cotesNull = cotesNull;
	}




	public double getCotesTeam1() {
		return cotesTeam1;
	}




	public void setCotesTeam1(double cotesTeam1) {
		this.cotesTeam1 = cotesTeam1;
	}




	public double getCotesTeam2() {
		return cotesTeam2;
	}




	public void setCotesTeam2(double cotesTeam2) {
		this.cotesTeam2 = cotesTeam2;
	}




	public String getTeam1() {
		return team1;
	}




	public void setTeam1(String team1) {
		this.team1 = team1;
	}




	public String getTeam2() {
		return team2;
	}




	public void setTeam2(String team2) {
		this.team2 = team2;
	}




	public LocalDate getDate() {
		return date;
	}




	public void setDate(LocalDate date) {
		this.date = date;
	}




	public String getResults() {
		return results;
	}




	public void setResults(String results) {
		this.results = results;
	}
	
	
	
}

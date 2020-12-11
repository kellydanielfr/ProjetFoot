package model;

import javax.persistence.*;

@Entity
public class Pari {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id_pari;
	@ManyToOne
	private SiteParis match;
	private double mise;
	private String choix;
	
	public Pari() {
	}

	public Pari(SiteParis match, double mise, String choix) {
		this.match = match;
		this.mise = mise;
		this.choix = choix;
	}

	public Integer getId_pari() {
		return id_pari;
	}

	public void setId_pari(Integer id_pari) {
		this.id_pari = id_pari;
	}

	public double getMise() {
		return mise;
	}

	public void setMise(double mise) {
		this.mise = mise;
	}

	public String getChoix() {
		return choix;
	}

	public void setChoix(String choix) {
		this.choix = choix;
	}

	@Override
	public String toString() {
		return "Pari [id_pari=" + id_pari + ", mise=" + mise + ", choix=" + choix + "]";
	}
	
	
	

}

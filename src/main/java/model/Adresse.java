package model;

import javax.persistence.*;

@Embeddable
public class Adresse {
	
	private Integer num_voie;
	private String voie;
	private String code_postal;
	private String ville;

	public Adresse() {}

	public Adresse(Integer num_voie, String voie, String code_postal, String ville) {
		this.num_voie = num_voie;
		this.voie = voie;
		this.code_postal = code_postal;
		this.ville = ville;
	}
	
	public Integer getNum_voie() {
		return num_voie;
	}
	public void setNum_voie(Integer num_voie) {
		this.num_voie = num_voie;
	}
	public String getVoie() {
		return voie;
	}
	public void setVoie(String voie) {
		this.voie = voie;
	}
	public String getCode_postal() {
		return code_postal;
	}
	public void setCode_postal(String code_postal) {
		this.code_postal = code_postal;
	}
	public String getVille() {
		return ville;
	}
	public void setVille(String ville) {
		this.ville = ville;
	}
	
	@Override
	public String toString() {
		return "Adresse [num_voie=" + num_voie + ", voie=" + voie + ", code_postal=" + code_postal + ", ville=" + ville
				+ "]";
	}
	
	
}

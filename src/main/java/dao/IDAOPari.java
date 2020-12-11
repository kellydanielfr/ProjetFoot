package dao;

import java.util.List;

import model.Pari;
import model.SiteParis;

public interface IDAOPari extends IDAO<Pari,Integer>{
	public List<Pari> selectPari(Integer id_match, String resultat);
	
}

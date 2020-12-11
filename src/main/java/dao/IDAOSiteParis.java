package dao;

import java.util.List;

import model.Pari;
import model.SiteParis;

public interface IDAOSiteParis extends IDAO<SiteParis,Integer>{
	public List<SiteParis> selectAllEndded();

	
}

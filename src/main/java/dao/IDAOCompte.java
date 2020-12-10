package dao;

import java.util.List;

import com.sun.istack.Nullable;

import model.Adherent;
import model.Admin;
import model.Compte;

public interface IDAOCompte extends IDAO<Compte,Integer> {
	
	public List<Admin> SelectAdmin();
	public List<Adherent> SelectAdherent();
	
	public Compte SelectByLoginMdp(String login, String mdp);
	@Nullable
	public Compte SelectByLogin(String login);

}

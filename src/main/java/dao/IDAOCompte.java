package dao;

import java.util.List;

import model.Adherent;
import model.Admin;
import model.Compte;

public interface IDAOCompte extends IDAO<Compte,Integer> {
	
	public List<Admin> SelectAdmin();
	public List<Adherent> SelectAdherent();
	
	public Compte SelectByLoginMdp(String login, String mdp);
	public Compte SelectByLogin(String login);
}

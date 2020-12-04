package Config;

import java.util.*;

import dao.*;
import dao.jdbc.*;
import model.*;

public class Context {
	private static Context _instance;
	
	private IDAOArticle daoArticle = new DAOArticleJDBC();
	private IDAOCompte daoCompte = new DAOCompteJDBC();
	private IDAOTicket daoTicket = new DAOTicketJDBC();
	private IDAOEvenement daoEvenement = new DAOEvenementJDBC();
	
	private Context() {}
	

	
	public static Context getInstance()
    {
        if(_instance==null) 
        {
            _instance=new Context();
        }
        return _instance;
    }



	public IDAOArticle getDaoArticle() {
		return daoArticle;
	}



	public void setDaoArticle(IDAOArticle daoArticle) {
		this.daoArticle = daoArticle;
	}



	public IDAOCompte getDaoCompte() {
		return daoCompte;
	}



	public void setDaoCompte(IDAOCompte daoCompte) {
		this.daoCompte = daoCompte;
	}



	public static Context get_instance() {
		return _instance;
	}



	public static void set_instance(Context _instance) {
		Context._instance = _instance;
	}



	public IDAOTicket getDaoTicket() {
		return daoTicket;
	}



	public void setDaoTicket(IDAOTicket daoTicket) {
		this.daoTicket = daoTicket;
	}



	public IDAOEvenement getDaoEvenement() {
		return daoEvenement;
	}



	public void setDaoEvenement(IDAOEvenement daoEvenement) {
		this.daoEvenement = daoEvenement;
	}	
}

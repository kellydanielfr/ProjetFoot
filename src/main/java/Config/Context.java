package Config;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import dao.IDAOArticle;
import dao.IDAOCompte;
import dao.IDAOEvenement;
import dao.IDAOTicket;
import dao.jpa.DAOArticleJPA;
import dao.jpa.DAOCompteJPA;
import dao.jpa.DAOEvenementJPA;
import dao.jpa.DAOTicketJPA;


public class Context {
	private static Context _instance;

	private EntityManagerFactory emf = Persistence.createEntityManagerFactory("sitefoot");

	private IDAOArticle daoArticle= new DAOArticleJPA();
	private IDAOCompte daoCompte= new DAOCompteJPA();
	private IDAOEvenement daoEvenement= new DAOEvenementJPA();
	private IDAOTicket daoTicket= new DAOTicketJPA();





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

	public EntityManagerFactory getEmf() {
		return emf;
	}

	public void closeEmf() 
	{
		emf.close();
	}
}

package Config;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import dao.IDAOArticle;
import dao.IDAOCompte;
import dao.IDAOEvenement;
import dao.IDAOPari;
import dao.IDAOProduit;
import dao.IDAOSiteParis;
import dao.IDAOTicket;
import dao.jpa.DAOArticleJPA;
import dao.jpa.DAOCompteJPA;
import dao.jpa.DAOEvenementJPA;
import dao.jpa.DAOPariJPA;
import dao.jpa.DAOProduitJPA;
import dao.jpa.DAOSiteParisJPA;
import dao.jpa.DAOTicketJPA;


public class Context {
	private static Context _instance;

	private EntityManagerFactory emf = Persistence.createEntityManagerFactory("sitefoot");

	private IDAOArticle daoArticle= new DAOArticleJPA();
	private IDAOCompte daoCompte= new DAOCompteJPA();
	private IDAOEvenement daoEvenement= new DAOEvenementJPA();
	private IDAOTicket daoTicket= new DAOTicketJPA();
	private IDAOProduit daoProduit= new DAOProduitJPA();
	private IDAOSiteParis daoSiteParis= new DAOSiteParisJPA();
	private IDAOPari daoPari= new DAOPariJPA();





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



	public IDAOProduit getDaoProduit() {
		return daoProduit;
	}



	public void setDaoProduit(IDAOProduit daoProduit) {
		this.daoProduit = daoProduit;
	}



	public IDAOSiteParis getDaoSiteParis() {
		return daoSiteParis;
	}



	public void setDaoSiteParis(IDAOSiteParis daoSiteParis) {
		this.daoSiteParis = daoSiteParis;
	}



	public IDAOPari getDaoPari() {
		return daoPari;
	}



	public void setDaoPari(IDAOPari daoPari) {
		this.daoPari = daoPari;
	}

}

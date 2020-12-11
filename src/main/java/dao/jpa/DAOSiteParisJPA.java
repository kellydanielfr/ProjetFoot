package dao.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import Config.Context;
import dao.IDAOSiteParis;
import model.SiteParis;


public class DAOSiteParisJPA implements IDAOSiteParis{

	@Override
	public void ajouter(SiteParis t) {
		EntityManager em=Context.getInstance().getEmf().createEntityManager();
		em.getTransaction().begin();
		em.persist(t);
		em.getTransaction().commit();
		em.close();
	}

	@Override
	public void modifier(SiteParis t) {
		EntityManager em=Context.getInstance().getEmf().createEntityManager();
		em.getTransaction().begin();
		t=em.merge(t);
		em.getTransaction().commit();
		em.close();
		
	}

	@Override
	public void supprimer(Integer id) {
		EntityManager em=Context.getInstance().getEmf().createEntityManager();
		em.getTransaction().begin();
		id=em.merge(id);

		em.remove(id);

		em.getTransaction().commit();
		em.close();		
	}

	@Override
	public SiteParis selectById(Integer id) {
		EntityManager em=Context.getInstance().getEmf().createEntityManager();
		SiteParis a = em.find(SiteParis.class, id);
		em.close();
		return a;		
	}

	@Override
	public List<SiteParis> selectAll() {
		EntityManager em=Context.getInstance().getEmf().createEntityManager();

		Query maRequete = em.createQuery("from site_paris",SiteParis.class);

		return maRequete.getResultList();
	}

}

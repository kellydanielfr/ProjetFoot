package dao.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import Config.Context;
import dao.IDAOEvenement;
import model.Evenement;
import model.Evenement;

public class DAOEvenementJPA implements IDAOEvenement {

	@Override
	public void ajouter(Evenement t) {
		EntityManager em=Context.getInstance().getEmf().createEntityManager();
		em.getTransaction().begin();
		em.persist(t);
		em.getTransaction().commit();
		em.close();
	}

	@Override
	public void modifier(Evenement t) {
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
	public Evenement selectById(Integer id) {
		EntityManager em=Context.getInstance().getEmf().createEntityManager();
		Evenement a = em.find(Evenement.class, id);
		em.close();
		return a;		
	}

	@Override
	public List<Evenement> selectAll() {
		EntityManager em=Context.getInstance().getEmf().createEntityManager();

		Query maRequete = em.createQuery("from Evenement",Evenement.class);
		return maRequete.getResultList();
	}

}

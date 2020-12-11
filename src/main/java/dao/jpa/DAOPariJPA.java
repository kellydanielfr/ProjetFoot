package dao.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import Config.Context;
import dao.IDAOPari;
import model.Pari;
import model.Pari;


public class DAOPariJPA implements IDAOPari{

	@Override
	public void ajouter(Pari t) {
		EntityManager em=Context.getInstance().getEmf().createEntityManager();
		em.getTransaction().begin();
		em.persist(t);
		em.getTransaction().commit();
		em.close();
	}

	@Override
	public void modifier(Pari t) {
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
	public Pari selectById(Integer id) {
		EntityManager em=Context.getInstance().getEmf().createEntityManager();
		Pari a = em.find(Pari.class, id);
		em.close();
		return a;		
	}

	@Override
	public List<Pari> selectAll() {
		EntityManager em=Context.getInstance().getEmf().createEntityManager();

		Query maRequete = em.createQuery("from Pari",Pari.class);

		return maRequete.getResultList();
	}
	
	@Override
	public List<Pari> selectPari(Integer id_match, String resultat) {
		EntityManager em=Context.getInstance().getEmf().createEntityManager();

		Query maRequete = em.createQuery("from Pari where match_id_match=:match_id and choix=:resultat",Pari.class);
		maRequete.setParameter("match_id",id_match);
		maRequete.setParameter("resultat",resultat);

		return maRequete.getResultList();
	}
}

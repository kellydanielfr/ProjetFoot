package dao.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import Config.Context;
import dao.IDAOProduit;
import model.Produit;

public class DAOProduitJPA implements IDAOProduit{

	@Override
	public void ajouter(Produit t) {
		EntityManager em=Context.getInstance().getEmf().createEntityManager();
		em.getTransaction().begin();
		em.persist(t);
		em.getTransaction().commit();
		em.close();
	}

	@Override
	public void modifier(Produit t) {
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
	public Produit selectById(Integer id) {
		EntityManager em=Context.getInstance().getEmf().createEntityManager();
		Produit a = em.find(Produit.class, id);
		em.close();
		return a;		
	}

	@Override
	public List<Produit> selectAll() {
		EntityManager em=Context.getInstance().getEmf().createEntityManager();

		Query maRequete = em.createQuery("from produit",Produit.class);

		return maRequete.getResultList();
	}

}

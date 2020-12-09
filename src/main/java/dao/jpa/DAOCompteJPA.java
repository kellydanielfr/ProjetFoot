package dao.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import Config.Context;
import dao.IDAOCompte;
import model.Adherent;
import model.Admin;
import model.Compte;
import model.Compte;

public class DAOCompteJPA implements IDAOCompte {

	@Override
	public void ajouter(Compte t) {
		EntityManager em=Context.getInstance().getEmf().createEntityManager();
		em.getTransaction().begin();
		em.persist(t);
		em.getTransaction().commit();
		em.close();		
	}

	@Override
	public void modifier(Compte t) {
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
	public Compte selectById(Integer id) {
		EntityManager em=Context.getInstance().getEmf().createEntityManager();
		Compte a = em.find(Compte.class, id);
		em.close();
		return a;	
	}

	@Override
	public List<Compte> selectAll() {
		EntityManager em=Context.getInstance().getEmf().createEntityManager();

		Query maRequete = em.createQuery("from Compte",Compte.class);

		return maRequete.getResultList();
	}

	@Override
	public List<Admin> SelectAdmin() {
		EntityManager em=Context.getInstance().getEmf().createEntityManager();

		Query maRequete = em.createQuery("from Compte WHERE droit='ADMIN'",Compte.class);

		return maRequete.getResultList();
	}

	@Override
	public List<Adherent> SelectAdherent() {
		EntityManager em=Context.getInstance().getEmf().createEntityManager();

		Query maRequete = em.createQuery("from Compte WHERE droit='ADHERENT'",Compte.class);

		return maRequete.getResultList();
	}

	@Override
	public Compte SelectByLoginMdp(String login, String mdp) {
		EntityManager em=Context.getInstance().getEmf().createEntityManager();
		
		
		Query maRequete = em.createQuery("from Compte c  where c.login=:loginReq and c.password=:password ",Admin.class);
		maRequete.setParameter("loginReq",login);
		maRequete.setParameter("password",mdp);
		Compte c = (Compte) maRequete.getSingleResult();
		return c;
		
	}

	@Override
	public Compte SelectByLogin(String login) {
		EntityManager em=Context.getInstance().getEmf().createEntityManager();
		
		
		Query maRequete = em.createQuery("from Compte c  where c.login=:loginReq",Admin.class);
		maRequete.setParameter("loginReq",login);
		Compte c = (Compte) maRequete.getSingleResult();
		return c;
	}

	
}

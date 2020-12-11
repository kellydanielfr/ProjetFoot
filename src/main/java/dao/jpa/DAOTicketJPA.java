package dao.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import Config.Context;
import dao.IDAOTicket;
import model.Ticket;


public class DAOTicketJPA implements IDAOTicket{

	@Override
	public void ajouter(Ticket t) {
		EntityManager em=Context.getInstance().getEmf().createEntityManager();
		em.getTransaction().begin();
		em.persist(t);
		em.getTransaction().commit();
		em.close();
	}

	@Override
	public void modifier(Ticket t) {
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
	public Ticket selectById(Integer id) {
		EntityManager em=Context.getInstance().getEmf().createEntityManager();
		Ticket a = em.find(Ticket.class, id);
		em.close();
		return a;		
	}

	@Override
	public List<Ticket> selectAll() {
		EntityManager em=Context.getInstance().getEmf().createEntityManager();

		Query maRequete = em.createQuery("from Ticket",Ticket.class);
		return maRequete.getResultList();
	}

}

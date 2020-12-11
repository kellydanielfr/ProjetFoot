package dao.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import Config.Context;
import dao.IDAOArticle;
import model.Article;
import model.Produit;

public class DAOArticleJPA implements IDAOArticle{

	@Override
	public void ajouter(Article t) {
		EntityManager em=Context.getInstance().getEmf().createEntityManager();
		em.getTransaction().begin();
		em.persist(t);
		em.getTransaction().commit();
		em.close();
	}

	@Override
	public void modifier(Article t) {
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
	public Article selectById(Integer id) {
		EntityManager em=Context.getInstance().getEmf().createEntityManager();
		Article a = em.find(Article.class, id);
		em.close();
		return a;		
	}

	@Override
	public List<Article> selectAll() {
		EntityManager em=Context.getInstance().getEmf().createEntityManager();

		Query maRequete = em.createQuery("from Article",Article.class);
		return maRequete.getResultList();
	}

}

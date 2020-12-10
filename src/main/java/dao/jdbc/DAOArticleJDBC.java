package dao.jdbc;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import dao.IDAOArticle;
import model.Article;

public class DAOArticleJDBC implements IDAOArticle{

	public void ajouter(Article a) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn=DriverManager.getConnection(lien+database, login, password);
			
			PreparedStatement ps=conn.prepareStatement("Insert into article (nom,taille,prix,quantite,description) values (?,?,?,?,?)");
			ps.setString(1, a.getNom());
			ps.setString(2, a.getTaille());
			ps.setDouble(3, a.getPrix());
			ps.setInt(4, a.getQuantite());
			ps.setString(5, a.getDescription());
			
			ps.executeUpdate();
			ps.close();
			conn.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void modifier(Article a) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn=DriverManager.getConnection(lien+database, login, password);
			
			PreparedStatement ps=conn.prepareStatement("UPDATE article set nom=?, taille=?, prix=?, quantite=?, description=? where code_article=?");
			ps.setString(1, a.getNom());
			ps.setString(2, a.getTaille());
			ps.setDouble(3, a.getPrix());
			ps.setInt(4, a.getQuantite());
			ps.setString(5, a.getDescription());
			ps.setInt(6, a.getNum_produit());
			
			ps.executeUpdate();
			ps.close();
			conn.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void supprimer(Integer id) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn=DriverManager.getConnection(lien+database, login, password);
			
			PreparedStatement ps=conn.prepareStatement("Delete from article WHERE code_article=?");
			ps.setInt(1, id);
			ps.executeUpdate();
			
			ps.close();
			conn.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Article selectById(Integer id) {
		Article article = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn=DriverManager.getConnection(lien+database, login, password);
			
			PreparedStatement ps=conn.prepareStatement("select * from article where code_article=?");
			ps.setInt(1, id);
			ResultSet rs=ps.executeQuery();
			
			while(rs.next()) 
	        {
				article = new Article(rs.getInt("num_produit"), rs.getString("nom"), rs.getDouble("prix"), rs.getInt("quantite"), rs.getString("taille"), rs.getString("description"));
	        }
			
					
			rs.close();
			ps.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}		
		return article;
	}

	public List<Article> selectAll() {
		List<Article> articles = new ArrayList<Article>();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn=DriverManager.getConnection(lien+database, login, password);
			
			PreparedStatement ps=conn.prepareStatement("select * from article");
			ResultSet rs=ps.executeQuery();
			
			Article a= null;
			while(rs.next()) 
	        {
				a = new Article(rs.getInt("code_article"), rs.getString("nom"), rs.getDouble("prix"), rs.getInt("quantite"), rs.getString("taille"), rs.getString("description"));
	            articles.add(a);
	        }
			
					
			rs.close();
			ps.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}		
		return articles;
	}
}

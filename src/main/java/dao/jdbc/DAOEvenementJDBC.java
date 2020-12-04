package dao.jdbc;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import dao.IDAOEvenement;
import model.Evenement;

public class DAOEvenementJDBC implements IDAOEvenement{

	
	public void ajouter(Evenement e) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn=DriverManager.getConnection(lien+database, login, password);
			
			PreparedStatement ps=conn.prepareStatement("Insert into evenement (date,titre,description) values (?,?,?)");
			ps.setString(1, e.getDate().toString());
			ps.setString(2, e.getTitre());
			ps.setString(3, e.getDescription());
			
			ps.executeUpdate();
			ps.close();
			conn.close();
			
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	
	public void modifier(Evenement e) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn=DriverManager.getConnection(lien+database, login, password);
			
			PreparedStatement ps=conn.prepareStatement("UPDATE evenement set date=?,titre=?,description=? where id_evenement=?");
			ps.setString(1, e.getDate().toString());
			ps.setString(2, e.getTitre());
			ps.setString(3, e.getDescription());
			ps.setInt(4, e.getId_evenement());
			
			ps.executeUpdate();
			ps.close();
			conn.close();
			
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	
	public void supprimer(Integer id) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn=DriverManager.getConnection(lien+database, login, password);
			
			PreparedStatement ps=conn.prepareStatement("Delete from evenement WHERE id_evenement=?");
			ps.setInt(1, id);
			ps.executeUpdate();
			
			ps.close();
			conn.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	public Evenement selectById(Integer id) {
		Evenement evenement = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn=DriverManager.getConnection(lien+database, login, password);
			
			PreparedStatement ps=conn.prepareStatement("select * from evenement where id_evenement=?");
			ps.setInt(1, id);
			ResultSet rs=ps.executeQuery();
			
			while(rs.next()) 
	        {
				LocalDate date = LocalDate.parse(rs.getString("date"));
				evenement = new Evenement(rs.getInt("id_evenement"),date, rs.getString("titre"), rs.getString("description"));
	        }
			
					
			rs.close();
			ps.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}		
		return evenement;
	}

	
	public List<Evenement> selectAll() {
		List<Evenement> evenements = new ArrayList<Evenement>();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn=DriverManager.getConnection(lien+database, login, password);
			
			PreparedStatement ps=conn.prepareStatement("select * from evenement");
			ResultSet rs=ps.executeQuery();
			
			Evenement e= null;
			while(rs.next()) 
	        {
				LocalDate date = LocalDate.parse(rs.getString("date"));
				e = new Evenement(rs.getInt("id_evenement"),date, rs.getString("titre"), rs.getString("description"));
	            evenements.add(e);
	        }
			
					
			rs.close();
			ps.close();
			conn.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}		
		return evenements;
	}
}

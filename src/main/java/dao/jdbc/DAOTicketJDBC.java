package dao.jdbc;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import dao.IDAOTicket;
import model.Ticket;

public class DAOTicketJDBC implements IDAOTicket{

	
	public void ajouter(Ticket t) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn=DriverManager.getConnection(lien+database, login, password);
			
			PreparedStatement ps=conn.prepareStatement("Insert into ticket (date,lieu,quantite,prix) values (?,?,?,?)");
			ps.setString(1, t.getDate().toString());
			ps.setString(2, t.getLieu());
			ps.setInt(3, t.getQuantite());
			ps.setDouble(4, t.getPrix());
			
			ps.executeUpdate();
			ps.close();
			conn.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	public void modifier(Ticket t) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn=DriverManager.getConnection(lien+database, login, password);
			
			PreparedStatement ps=conn.prepareStatement("UPDATE ticket set date=?,lieu=?,quantite=?,prix=? where code_ticket=?");
			ps.setString(1, t.getDate().toString());
			ps.setString(2, t.getLieu());
			ps.setInt(3, t.getQuantite());
			ps.setDouble(4, t.getPrix());
			ps.setInt(5, t.getCode_ticket());
			
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
			
			PreparedStatement ps=conn.prepareStatement("Delete from ticket WHERE code_ticket=?");
			ps.setInt(1, id);
			ps.executeUpdate();
			
			ps.close();
			conn.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	public Ticket selectById(Integer id) {
		Ticket ticket = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn=DriverManager.getConnection(lien+database, login, password);
			
			PreparedStatement ps=conn.prepareStatement("select * from ticket where code_ticket=?");
			ps.setInt(1, id);
			ResultSet rs=ps.executeQuery();
			
			while(rs.next()) 
	        {
				LocalDate date = LocalDate.parse(rs.getString("date"));
				ticket = new Ticket(rs.getInt("code_ticket"), rs.getDouble("prix"), rs.getInt("quantite"), date, rs.getString("lieu"));
	        }
			
					
			rs.close();
			ps.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}		
		return ticket;
	}

	
	public List<Ticket> selectAll() {
		List<Ticket> tickets = new ArrayList<Ticket>();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn=DriverManager.getConnection(lien+database, login, password);
			
			PreparedStatement ps=conn.prepareStatement("select * from ticket");
			ResultSet rs=ps.executeQuery();
			
			Ticket a= null;
			while(rs.next()) 
	        {
				LocalDate date = LocalDate.parse(rs.getString("date"));
				a = new Ticket(rs.getInt("code_ticket"), rs.getDouble("prix"), rs.getInt("quantite"), date, rs.getString("lieu"));
	            tickets.add(a);
	        }
			
					
			rs.close();
			ps.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}		
		return tickets;
	}
}

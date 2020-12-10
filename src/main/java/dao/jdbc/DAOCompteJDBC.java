package dao.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import java.sql.Statement;

import Config.Context;
import dao.IDAOCompte;
import model.*;

public class DAOCompteJDBC implements IDAOCompte {

	
	public void ajouter(Compte c) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn=DriverManager.getConnection(lien+database, login, password);
			PreparedStatement ps=null;
			if (c instanceof Admin) {
				ps=conn.prepareStatement("Insert into compte (login,nom,prenom,password) values (?,?,?,?)");
				ps.setString(1, c.getLogin());
				ps.setString(2, c.getNom());
				ps.setString(3, c.getPrenom());
				ps.setString(4, c.getPassword());
				
			} else {
				int lastInsertId=0;
				ps=conn.prepareStatement("Insert into compte (login,nom,prenom,password,solde) values (?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
				ps.setString(1, c.getLogin());
				ps.setString(2, c.getNom());
				ps.setString(3, c.getPrenom());
				ps.setString(4, c.getPassword());
				ps.setDouble(5, ((Adherent) c).getSolde());
				ps.executeUpdate();
				
				ResultSet rs = ps.getGeneratedKeys();
				while (rs.next()) {
					lastInsertId=rs.getInt(1);
					
				}
				
				PreparedStatement ps2=conn.prepareStatement("Insert into adresse (num_voie,voie,code_postal,ville,num_compte) values (?,?,?,?,?)");
				ps2.setInt(1, ((Adherent) c).getAdresse().getNum_voie());
				ps2.setString(2, ((Adherent) c).getAdresse().getVoie());
				ps2.setString(3, ((Adherent) c).getAdresse().getCode_postal());
				ps2.setString(4, ((Adherent) c).getAdresse().getVille());
				ps2.setInt(5, lastInsertId);
				ps2.executeUpdate();
				ps2.close();
			}
			
			
			
			ps.close();
			conn.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	public void modifier(Compte c) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn=DriverManager.getConnection(lien+database, login, password);
			
			PreparedStatement ps=null;
			PreparedStatement ps2=null;
			if (c instanceof Admin) {
				ps=conn.prepareStatement("UPDATE compte set login=?,nom=?, prenom=?,password=? where num_compte=?");
				ps.setInt(5, c.getNum_compte());
			} else {
				ps=conn.prepareStatement("UPDATE compte set login=?,nom=?, prenom=?,password=?,solde=? where num_compte=?");
				ps.setDouble(5, ((Adherent) c).getSolde());
				ps.setInt(6, c.getNum_compte());
				ps2=conn.prepareStatement("UPDATE adresse set num_voie=?,voie=?,code_postal=?, ville=? where num_compte=?");
				ps2.setInt(1, ((Adherent) c).getAdresse().getNum_voie());
				ps2.setString(2, ((Adherent) c).getAdresse().getVoie());
				ps2.setString(3, ((Adherent) c).getAdresse().getCode_postal());
				ps2.setString(4, ((Adherent) c).getAdresse().getVille());
				ps2.setInt(5,c.getNum_compte());
			}
			ps.setString(1, c.getLogin());
			ps.setString(2, c.getNom());
			ps.setString(3, c.getPrenom());
			ps.setString(4, c.getPassword());
			
			
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
			
			PreparedStatement ps=conn.prepareStatement("Delete from compte WHERE num_compte=?");
			ps.setInt(1, id);
			ps.executeUpdate();
			
			ps.close();
			conn.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	public Compte selectById(Integer id) {
		Compte compte = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn=DriverManager.getConnection(lien+database, login, password);
			
			PreparedStatement ps=conn.prepareStatement("select * from compte where num_compte=?");
			ps.setInt(1, id);
			ResultSet rs=ps.executeQuery();
			
			while(rs.next()) 
	        {
				if (rs.getObject("droit").equals("ADMIN")) {
					compte = new Admin(rs.getString("nom"), rs.getString("prenom"), rs.getInt("num_compte"), rs.getString("login"), rs.getString("password"));
				}else {
					Adresse adresse = new Adresse(rs.getInt("num_voie"), rs.getString("voie"), rs.getString("code_postal"),rs.getString("ville"));
					compte=new Adherent(rs.getString("nom"), rs.getString("prenom"), rs.getInt("compte.num_compte"), rs.getString("login"), rs.getString("password"), rs.getInt("solde"),adresse);
				}
	            
	        }	
			rs.close();
			ps.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}		
		return compte;
	}

	
	public List<Compte> selectAll() {
		List<Compte> comptes = new ArrayList<Compte>();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn=DriverManager.getConnection(lien+database, login, password);
			
			PreparedStatement ps=conn.prepareStatement("select * from compte left join adresse on compte.num_compte=adresse.num_compte");
			ResultSet rs=ps.executeQuery();
			
			Compte c= null;
			while(rs.next()) 
	        {
				if (rs.getObject("droit").equals("ADMIN")) {
					c = new Admin(rs.getString("nom"), rs.getString("prenom"), rs.getInt("num_compte"), rs.getString("login"), rs.getString("password"));
				}else {
					Adresse adresse = new Adresse(rs.getInt("num_voie"), rs.getString("voie"), rs.getString("code_postal"),rs.getString("ville"));
					c=new Adherent(rs.getString("nom"), rs.getString("prenom"), rs.getInt("compte.num_compte"), rs.getString("login"), rs.getString("password"), rs.getInt("solde"),adresse);
				}
	            
	            comptes.add(c);
	        }
			
					
			rs.close();
			ps.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}		
		return comptes;
	}

	
	public Compte SelectByLogin(String loginCompte) {
		Compte compte = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn=DriverManager.getConnection(lien+database, login, password);
			
			PreparedStatement ps=conn.prepareStatement("select * from compte left join adresse on compte.num_compte=adresse.num_compte where login=?");
			ps.setString(1, loginCompte);
			ResultSet rs=ps.executeQuery();
			
			while(rs.next()) 
	        {
				if (rs.getObject("droit").equals("ADMIN")) {
					compte = new Admin(rs.getString("nom"), rs.getString("prenom"), rs.getInt("compte.num_compte"), rs.getString("login"), rs.getString("password"));
				}else {
					Adresse adresse = new Adresse(rs.getInt("num_voie"), rs.getString("voie"), rs.getString("code_postal"),rs.getString("ville"));
					compte=new Adherent(rs.getString("nom"), rs.getString("prenom"), rs.getInt("compte.num_compte"), rs.getString("login"), rs.getString("password"), rs.getInt("solde"),adresse);
				}
	            
	        }
			
					
			rs.close();
			ps.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}		
		return compte;
	}
	
	
	public Compte SelectByLoginMdp(String loginCompte, String mdp) {
		Compte compte = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn=DriverManager.getConnection(lien+database, login, password);
			
			PreparedStatement ps=conn.prepareStatement("select * from compte left join adresse on compte.num_compte=adresse.num_compte where login=? and password=?");
			ps.setString(1, loginCompte);
			ps.setString(2, mdp);
			ResultSet rs=ps.executeQuery();
			
			while(rs.next()) 
	        {
				if (rs.getObject("droit").equals("ADMIN")) {
					compte = new Admin(rs.getString("nom"), rs.getString("prenom"), rs.getInt("compte.num_compte"), rs.getString("login"), rs.getString("password"));
				}else {
					Adresse adresse = new Adresse(rs.getInt("num_voie"), rs.getString("voie"), rs.getString("code_postal"),rs.getString("ville"));
					compte=new Adherent(rs.getString("nom"), rs.getString("prenom"), rs.getInt("compte.num_compte"), rs.getString("login"), rs.getString("password"), rs.getInt("solde"),adresse);
				}
	            
	        }
			
					
			rs.close();
			ps.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}		
		return compte;
	}
	
	
	public List<Adherent> SelectAdherent() {
		List<Adherent> adherents = new ArrayList<Adherent>();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn=DriverManager.getConnection(lien+database, login, password);
			
			PreparedStatement ps=conn.prepareStatement("select * from compte,adresse where compte.num_compte=adresse.num_compte and droit=\"ADHERENT\"");
			ResultSet rs=ps.executeQuery();
			
			
			while(rs.next()) 
	        {
				Adresse adresse = new Adresse(rs.getInt("num_voie"), rs.getString("voie"), rs.getString("code_postal"),rs.getString("ville"));
				Adherent adherent =new Adherent(rs.getString("nom"), rs.getString("prenom"), rs.getInt("compte.num_compte"), rs.getString("login"), rs.getString("password"), rs.getInt("solde"),adresse);
				adherents.add(adherent);
	        }	
			rs.close();
			ps.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}		
		return adherents;
	}

	
	public List<Admin> SelectAdmin() {
		List<Admin> admins = new ArrayList<Admin>();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn=DriverManager.getConnection(lien+database, login, password);
			
			PreparedStatement ps=conn.prepareStatement("select * from compte where droit=\"ADMIN\"");
			ResultSet rs=ps.executeQuery();
			
			
			while(rs.next()) 
	        {
				Admin admin = new Admin(rs.getString("nom"), rs.getString("prenom"), rs.getInt("compte.num_compte"), rs.getString("login"), rs.getString("password"));
				admins.add(admin);
	        }	
			rs.close();
			ps.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}		
		return admins;
	}
}

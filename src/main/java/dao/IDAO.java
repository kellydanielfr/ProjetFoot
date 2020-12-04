package dao;

import java.util.List;

public interface IDAO<T,K> {
	String lien= "jdbc:mysql://localhost:3306/";
	String login = "root";
	String password = "";
	String database = "sitefoot";
	
	public void ajouter(T t);
	public void modifier(T t);
	public void supprimer(K id);
	public T selectById(K id);
	public List<T> selectAll();

}

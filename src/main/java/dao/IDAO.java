package dao;

import java.util.List;

public interface IDAO<T,K> {

	
	public void ajouter(T t);
	public void modifier(T t);
	public void supprimer(K id);
	public T selectById(K id);
	public List<T> selectAll();

}

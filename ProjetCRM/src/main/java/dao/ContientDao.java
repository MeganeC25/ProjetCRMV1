package dao;

import java.util.List;

import model.Contient;

public interface ContientDao {

	void creer(Contient contient) throws DaoException;

	Contient trouver(long id) throws DaoException;

	List<Contient> lister() throws DaoException;

	void supprimer(long id) throws DaoException;

	void miseAJour(Contient contient) throws DaoException;
	
	public List<Contient> listerByProduit(long idProduit) throws DaoException;
	
	public List<Contient> listerByPanier(long idPanier) throws DaoException;
}

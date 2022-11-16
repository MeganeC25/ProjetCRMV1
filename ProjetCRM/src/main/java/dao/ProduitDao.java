package dao;

import java.util.List;

import model.Produit;

public interface ProduitDao {

	void creer(Produit produit) throws DaoException;

	Produit trouver(long id) throws DaoException;

	List<Produit> lister() throws DaoException;

	void supprimer(long id) throws DaoException;

	void miseAJour(Produit produit) throws DaoException;

}

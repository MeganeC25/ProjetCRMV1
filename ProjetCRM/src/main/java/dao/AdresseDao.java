package dao;

import java.util.List;

import model.Adresse;

public interface AdresseDao {

	void creer(Adresse adresse) throws DaoException;

	Adresse trouver(long id) throws DaoException;

	List<Adresse> lister() throws DaoException;

	void supprimer(long id) throws DaoException;

	void miseAJour(Adresse adresse) throws DaoException;
}

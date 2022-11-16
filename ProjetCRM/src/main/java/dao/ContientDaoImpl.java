package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


import model.Contient;

public class ContientDaoImpl implements ContientDao {

	private static final String SQL_INSERT = "INSERT INTO CONTIENT(ID_PRODUIT, ID_PANIER) VALUES(?,?)";
	private static final String SQL_SELECT = "SELECT ID, ID_PRODUIT, ID_PANIER FROM CONTIENT";
	private static final String SQL_SELECT_BY_ID = SQL_SELECT + " WHERE ID = ?";
	private static final String SQL_DELETE_BY_ID = "DELETE FROM CONTIENT WHERE ID = ? ";

	private static final String SQL_UPDATE = "UPDATE CONTIENT SET ID_PRODUIT=?, ID_PANIER=? WHERE ID=?";
	
	private static final String SQL_SELECT_BY_PRODUIT = SQL_SELECT + " WHERE ID_PRODUIT=?";
	private static final String SQL_SELECT_BY_PANIER = SQL_SELECT + " WHERE ID_PANIER=?";

	private DaoFactory factory;

	public ContientDaoImpl(DaoFactory factory) {
		this.factory = factory;
	}

	@Override
	public void creer(Contient contient) throws DaoException {
		Connection con = null;
		try {
			con = factory.getConnection();

			PreparedStatement ps = con.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS);
			ps.setLong(1, contient.getProduit().getId());
			ps.setLong(2, contient.getPanier().getId());

			int statut = ps.executeUpdate();

			if (statut == 0) {
				throw new DaoException("Echec creation Contient (aucun ajout)");
			}

			ResultSet rsKeys = ps.getGeneratedKeys();
			if (rsKeys.next()) {
				contient.setId(rsKeys.getLong(1));
			} else {
				throw new DaoException("Echec creation Contient (ID non retourné)");
			}

			rsKeys.close();
			ps.close();

		} catch (SQLException ex) {
			throw new DaoException("Echec creation Contient", ex);
		} finally {
			factory.releaseConnection(con);
		}

	}

	private static Contient map(ResultSet resultSet) throws SQLException {
		Contient contient = new Contient();
		contient.setId(resultSet.getLong("id"));
		try {
			contient.setProduit(DaoFactory.getInstance().getProduitDao().trouver(resultSet.getLong("id_produit")));
			contient.setPanier(DaoFactory.getInstance().getPanierDao().trouver(resultSet.getLong("id_panier")));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return contient;
	}

	@Override
	public Contient trouver(long id) throws DaoException {
		Contient contient = null;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			con = factory.getConnection();
			ps = con.prepareStatement(SQL_SELECT_BY_ID);
			ps.setLong(1, id);
			rs = ps.executeQuery();
			if (rs.next())
				contient = map(rs);

			rs.close();
			ps.close();
		} catch (SQLException ex) {
			throw new DaoException("Erreur de recherche BDD Contient", ex);
		} finally {
			factory.releaseConnection(con);
		}
		return contient;
	}

	@Override
	public List<Contient> lister() throws DaoException {
		List<Contient> listeContient = new ArrayList<Contient>();
		Connection con = null;
		try {
			con = factory.getConnection();
			PreparedStatement ps = con.prepareStatement(SQL_SELECT);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				listeContient.add(map(rs));
			}
			rs.close();
			ps.close();
		} catch (SQLException ex) {
			throw new DaoException("Erreur de lecture BDD Contient", ex);
		} finally {
			factory.releaseConnection(con);
		}
		return listeContient;
	}

	@Override
	public void supprimer(long id) throws DaoException {
		Connection con = null;
		try {
			con = factory.getConnection();
			PreparedStatement ps = con.prepareStatement(SQL_DELETE_BY_ID);
			ps.setLong(1, id);
			int statut = ps.executeUpdate();
			if (statut == 0) {
				throw new DaoException("Erreur de suppression Contient(" + id + ")");
			}
			ps.close();
		} catch (SQLException ex) {
			throw new DaoException("Erreur de suppression BDD Contient", ex);
		} finally {
			factory.releaseConnection(con);
		}

	}

	@Override
	public void miseAJour(Contient contient) throws DaoException {
		Connection con = null;
		try {
			con = factory.getConnection();

			PreparedStatement ps = con.prepareStatement(SQL_UPDATE);
			ps.setLong(1, contient.getProduit().getId());
			ps.setLong(2, contient.getPanier().getId());
			ps.setLong(3, contient.getId());

			int statut = ps.executeUpdate();

			if (statut == 0) {
				throw new DaoException("Echec maj Contient (aucune maj)");
			}

			ps.close();

		} catch (SQLException ex) {
			throw new DaoException("Echec maj Contient", ex);
		} finally {
			factory.releaseConnection(con);
		}

	}

	public List<Contient> listerByProduit(long idProduit) throws DaoException {
		List<Contient> listeContient = new ArrayList<Contient>();
		Connection con = null;
		try {
			con = factory.getConnection();
			PreparedStatement ps = con.prepareStatement(SQL_SELECT_BY_PRODUIT);
			ps.setLong(1, idProduit);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				listeContient.add(map(rs));
			}
			rs.close();
			ps.close();
		} catch (SQLException ex) {
			throw new DaoException("Erreur de lecture BDD Contient", ex);
		} finally {
			factory.releaseConnection(con);
		}
		return listeContient;
	}

	public List<Contient> listerByPanier(long idPanier) throws DaoException {
		List<Contient> listeContient = new ArrayList<Contient>();
		Connection con = null;
		try {
			con = factory.getConnection();
			PreparedStatement ps = con.prepareStatement(SQL_SELECT_BY_PANIER);
			ps.setLong(1, idPanier);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				listeContient.add(map(rs));
			}
			rs.close();
			ps.close();
		} catch (SQLException ex) {
			throw new DaoException("Erreur de lecture BDD Contient", ex);
		} finally {
			factory.releaseConnection(con);
		}
		return listeContient;
	}
	
}

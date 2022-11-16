package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Panier;

public class PanierDaoImpl implements PanierDao {

	private static final String SQL_INSERT = "INSERT INTO PANIER(ID_CLIENT) VALUES(?)";
	private static final String SQL_SELECT = "SELECT ID, ID_CLIENT FROM PANIER";
	private static final String SQL_SELECT_BY_ID = SQL_SELECT + " WHERE ID = ?";
	private static final String SQL_DELETE_BY_ID = "DELETE FROM PANIER WHERE ID = ? ";

	private static final String SQL_UPDATE = "UPDATE PANIER SET ID_CLIENT=? WHERE ID=?";

	private DaoFactory factory;

	public PanierDaoImpl(DaoFactory factory) {
		this.factory = factory;
	}

	@Override
	public void creer(Panier panier) throws DaoException {
		Connection con = null;
		try {
			con = factory.getConnection();

			PreparedStatement ps = con.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS);
			ps.setLong(1, panier.getClient().getId());

			int statut = ps.executeUpdate();

			if (statut == 0) {
				throw new DaoException("Echec creation Panier (aucun ajout)");
			}

			ResultSet rsKeys = ps.getGeneratedKeys();
			if (rsKeys.next()) {
				panier.setId(rsKeys.getLong(1));
			} else {
				throw new DaoException("Echec creation Panier (ID non retourné)");
			}

			rsKeys.close();
			ps.close();

		} catch (SQLException ex) {
			throw new DaoException("Echec creation Panier", ex);
		} finally {
			factory.releaseConnection(con);
		}

	}

	private static Panier map(ResultSet resultSet) throws SQLException {
		Panier panier = new Panier();
		panier.setId(resultSet.getLong("id"));
		try {
			panier.setClient(DaoFactory.getInstance().getClientDao().trouver(resultSet.getLong("id_client")));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return panier;
	}

	@Override
	public Panier trouver(long id) throws DaoException {
		Panier panier = null;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			con = factory.getConnection();
			ps = con.prepareStatement(SQL_SELECT_BY_ID);
			ps.setLong(1, id);
			rs = ps.executeQuery();
			if (rs.next())
				panier = map(rs);

			rs.close();
			ps.close();
		} catch (SQLException ex) {
			throw new DaoException("Erreur de recherche BDD Panier", ex);
		} finally {
			factory.releaseConnection(con);
		}
		return panier;
	}

	@Override
	public List<Panier> lister() throws DaoException {
		List<Panier> listePaniers = new ArrayList<Panier>();
		Connection con = null;
		try {
			con = factory.getConnection();
			PreparedStatement ps = con.prepareStatement(SQL_SELECT);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				listePaniers.add(map(rs));
			}
			rs.close();
			ps.close();
		} catch (SQLException ex) {
			throw new DaoException("Erreur de lecture BDD Panier", ex);
		} finally {
			factory.releaseConnection(con);
		}
		return listePaniers;

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
				throw new DaoException("Erreur de suppression Panier(" + id + ")");
			}
			ps.close();
		} catch (SQLException ex) {
			throw new DaoException("Erreur de suppression BDD Panier", ex);
		} finally {
			factory.releaseConnection(con);
		}

	}

	@Override
	public void miseAJour(Panier panier) throws DaoException {
		Connection con = null;
		try {
			con = factory.getConnection();

			PreparedStatement ps = con.prepareStatement(SQL_UPDATE);
			ps.setLong(1, panier.getClient().getId());
			ps.setLong(2, panier.getId());

			int statut = ps.executeUpdate();

			if (statut == 0) {
				throw new DaoException("Echec maj Panier (aucune maj)");
			}

			ps.close();

		} catch (SQLException ex) {
			throw new DaoException("Echec maj Panier", ex);
		} finally {
			factory.releaseConnection(con);
		}

	}

}

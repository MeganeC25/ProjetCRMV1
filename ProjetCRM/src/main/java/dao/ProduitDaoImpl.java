package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Produit;

public class ProduitDaoImpl implements ProduitDao {

	private static final String SQL_INSERT = "INSERT INTO PRODUIT(NOM, DESCRIPTION, PRIX) VALUES(?,?,?)";
	private static final String SQL_SELECT = "SELECT ID, NOM, DESCRIPTION, PRIX FROM PRODUIT";
	private static final String SQL_SELECT_BY_ID = SQL_SELECT + " WHERE ID = ?";
	private static final String SQL_DELETE_BY_ID = "DELETE FROM PRODUIT WHERE ID = ? ";

	private static final String SQL_UPDATE = "UPDATE PRODUIT SET NOM=?, DESCRIPTION=?, PRIX=? WHERE ID=?";

	private DaoFactory factory;

	public ProduitDaoImpl(DaoFactory factory) {
		this.factory = factory;
	}

	@Override
	public void creer(Produit produit) throws DaoException {
		Connection con = null;
		try {
			con = factory.getConnection();

			PreparedStatement ps = con.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, produit.getNom());
			ps.setString(2, produit.getDescription());
			ps.setFloat(3, produit.getPrix());

			int statut = ps.executeUpdate();

			if (statut == 0) {
				throw new DaoException("Echec creation Produit (aucun ajout)");
			}

			ResultSet rsKeys = ps.getGeneratedKeys();
			if (rsKeys.next()) {
				produit.setId(rsKeys.getLong(1));
			} else {
				throw new DaoException("Echec creation Produit (ID non retourné)");
			}

			rsKeys.close();
			ps.close();

		} catch (SQLException ex) {
			throw new DaoException("Echec creation Produit", ex);
		} finally {
			factory.releaseConnection(con);
		}

	}

	private static Produit map(ResultSet resultSet) throws SQLException {
		Produit produit = new Produit();
		produit.setId(resultSet.getLong("id"));
		produit.setNom(resultSet.getString("nom"));
		produit.setDescription(resultSet.getString("description"));
		produit.setPrix(resultSet.getFloat("prix"));
		return produit;
	}

	@Override
	public Produit trouver(long id) throws DaoException {
		Produit produit = null;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			con = factory.getConnection();
			ps = con.prepareStatement(SQL_SELECT_BY_ID);
			ps.setLong(1, id);
			rs = ps.executeQuery();
			if (rs.next())
				produit = map(rs);

			rs.close();
			ps.close();
		} catch (SQLException ex) {
			throw new DaoException("Erreur de recherche BDD Produit", ex);
		} finally {
			factory.releaseConnection(con);
		}
		return produit;
	}

	@Override
	public List<Produit> lister() throws DaoException {
		List<Produit> listeProduits = new ArrayList<Produit>();
		Connection con = null;
		try {
			con = factory.getConnection();
			PreparedStatement ps = con.prepareStatement(SQL_SELECT);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				listeProduits.add(map(rs));
			}
			rs.close();
			ps.close();
		} catch (SQLException ex) {
			throw new DaoException("Erreur de lecture BDD Produit", ex);
		} finally {
			factory.releaseConnection(con);
		}
		return listeProduits;
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
				throw new DaoException("Erreur de suppression Produit(" + id + ")");
			}
			ps.close();
		} catch (SQLException ex) {
			throw new DaoException("Erreur de suppression BDD Produit", ex);
		} finally {
			factory.releaseConnection(con);
		}


	}

	@Override
	public void miseAJour(Produit produit) throws DaoException {
		Connection con = null;
		try {
			con = factory.getConnection();

			PreparedStatement ps = con.prepareStatement(SQL_UPDATE);
			ps.setString(1, produit.getNom());
			ps.setString(2, produit.getDescription());
			ps.setFloat(3, produit.getPrix());
			ps.setLong(4, produit.getId());

			int statut = ps.executeUpdate();

			if (statut == 0) {
				throw new DaoException("Echec maj Produit (aucune maj)");
			}

			ps.close();

		} catch (SQLException ex) {
			throw new DaoException("Echec maj Produit", ex);
		} finally {
			factory.releaseConnection(con);
		}

	}

}

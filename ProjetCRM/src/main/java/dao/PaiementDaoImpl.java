package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Paiement;

public class PaiementDaoImpl implements PaiementDao {

	private static final String SQL_INSERT = "INSERT INTO PAIEMENT(NO_CARTE, CODE_CONFIDENTIEL, BANQUE, ID_CLIENT) VALUES(?,?,?,?)";
	private static final String SQL_SELECT = "SELECT ID, NO_CARTE, CODE_CONFIDENTIEL, BANQUE, ID_CLIENT FROM PAIEMENT";
	private static final String SQL_SELECT_BY_ID = SQL_SELECT + " WHERE id = ?";
	private static final String SQL_DELETE_BY_ID = "DELETE FROM PAIEMENT WHERE id = ? ";

	private static final String SQL_UPDATE = "UPDATE PAIEMENT SET NO_CARTE=?, CODE_CONFIDENTIEL=?, BANQUE=?, ID_CLIENT=? WHERE ID=?";

	private DaoFactory factory;

	public PaiementDaoImpl(DaoFactory factory) {
		this.factory = factory;
	}

	@Override
	public void creer(Paiement paiement) throws DaoException {
		Connection con = null;
		try {
			con = factory.getConnection();

			PreparedStatement ps = con.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS);
			ps.setInt(1, paiement.getNumCarte());
			ps.setInt(2, paiement.getCodeConf());
			ps.setString(3, paiement.getBanque());
			ps.setLong(4, paiement.getClient().getId());

			int statut = ps.executeUpdate();

			if (statut == 0) {
				throw new DaoException("Echec creation Paiement (aucun ajout)");
			}

			ResultSet rsKeys = ps.getGeneratedKeys();
			if (rsKeys.next()) {
				paiement.setId(rsKeys.getLong(1));
			} else {
				throw new DaoException("Echec creation Paiement (ID non retourné)");
			}

			rsKeys.close();
			ps.close();

		} catch (SQLException ex) {
			throw new DaoException("Echec creation Paiement", ex);
		} finally {
			factory.releaseConnection(con);
		}

	}

	private static Paiement map(ResultSet resultSet) throws SQLException {
		Paiement paiement = new Paiement();
		paiement.setId(resultSet.getLong("id"));
		paiement.setNumCarte(resultSet.getInt("no_carte"));
		paiement.setCodeConf(resultSet.getInt("code_confidentiel"));
		paiement.setBanque(resultSet.getString("banque"));
		try {
			paiement.setClient(DaoFactory.getInstance().getClientDao().trouver(resultSet.getInt("id_client")));
		} catch (Exception e) {
			System.out.println("Erreur sur le numero de client " + resultSet.getInt("id_client"));
			e.printStackTrace();
		}

		return paiement;
	}

	@Override
	public Paiement trouver(long id) throws DaoException {
		Paiement paiement = null;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			con = factory.getConnection();
			ps = con.prepareStatement(SQL_SELECT_BY_ID);
			ps.setLong(1, id);
			rs = ps.executeQuery();
			if (rs.next())
				paiement = map(rs);

			rs.close();
			ps.close();
		} catch (SQLException ex) {
			throw new DaoException("Erreur de recherche BDD Paiement", ex);
		} finally {
			factory.releaseConnection(con);
		}
		return paiement;
	}

	@Override
	public List<Paiement> lister() throws DaoException {
		List<Paiement> listePaiements = new ArrayList<Paiement>();
		Connection con = null;
		try {
			con = factory.getConnection();
			PreparedStatement ps = con.prepareStatement(SQL_SELECT);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				listePaiements.add(map(rs));
			}
			rs.close();
			ps.close();
		} catch (SQLException ex) {
			throw new DaoException("Erreur de lecture BDD Paiement", ex);
		} finally {
			factory.releaseConnection(con);
		}
		return listePaiements;
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
				throw new DaoException("Erreur de suppression Paiement(" + id + ")");
			}
			ps.close();
		} catch (SQLException ex) {
			throw new DaoException("Erreur de suppression BDD Paiement", ex);
		} finally {
			factory.releaseConnection(con);
		}

	}

	@Override
	public void miseAJour(Paiement paiement) throws DaoException {
		Connection con = null;
		try {
			con = factory.getConnection();

			PreparedStatement ps = con.prepareStatement(SQL_UPDATE);
			ps.setInt(1, paiement.getNumCarte());
			ps.setInt(2, paiement.getCodeConf());
			ps.setString(3, paiement.getBanque());
			ps.setLong(4, paiement.getClient().getId());
			ps.setLong(5, paiement.getId());

			int statut = ps.executeUpdate();

			if (statut == 0) {
				throw new DaoException("Echec maj Paiement (aucune maj)");
			}

			ps.close();

		} catch (SQLException ex) {
			throw new DaoException("Echec maj Paiement", ex);
		} finally {
			factory.releaseConnection(con);
		}

	}

}

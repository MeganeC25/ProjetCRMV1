package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Adresse;

public class AdresseDaoImpl implements AdresseDao {

	private static final String SQL_INSERT = "INSERT INTO ADRESSE(RUE, VILLE, PAYS, CODE_POSTAL) VALUES(?,?,?,?)";
	private static final String SQL_SELECT = "SELECT ID, RUE, VILLE, PAYS, CODE_POSTAL FROM ADRESSE";
	private static final String SQL_SELECT_BY_ID = SQL_SELECT + " WHERE id = ?";
	private static final String SQL_DELETE_BY_ID = "DELETE FROM ADRESSE WHERE id = ? ";

	private static final String SQL_UPDATE = "UPDATE ADRESSE SET RUE=?, VILLE=?, PAYS=?, CODE_POSTAL=? WHERE ID=?";

	private DaoFactory factory;

	public AdresseDaoImpl(DaoFactory factory) {
		this.factory = factory;
	}

	@Override
	public void creer(Adresse adresse) throws DaoException {
		Connection con = null;
		try {
			con = factory.getConnection();

			PreparedStatement ps = con.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, adresse.getRue());
			ps.setString(2, adresse.getVille());
			ps.setString(3, adresse.getPays());
			ps.setString(4, adresse.getCodePostal());

			int statut = ps.executeUpdate();

			if (statut == 0) {
				throw new DaoException("Echec creation Adresse (aucun ajout)");
			}

			ResultSet rsKeys = ps.getGeneratedKeys();
			if (rsKeys.next()) {
				adresse.setId(rsKeys.getLong(1));
			} else {
				throw new DaoException("Echec creation Adresse (ID non retourné)");
			}

			rsKeys.close();
			ps.close();

		} catch (SQLException ex) {
			throw new DaoException("Echec creation Adresse", ex);
		} finally {
			factory.releaseConnection(con);
		}

	}

	private static Adresse map(ResultSet resultSet) throws SQLException {
		Adresse adresse = new Adresse();
		adresse.setId(resultSet.getLong("id"));
		adresse.setRue(resultSet.getString("rue"));
		adresse.setVille(resultSet.getString("ville"));
		adresse.setPays(resultSet.getString("pays"));
		adresse.setCodePostal(resultSet.getString("id"));
		return adresse;
	}

	@Override
	public Adresse trouver(long id) throws DaoException {
		Adresse adresse = null;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			con = factory.getConnection();
			ps = con.prepareStatement(SQL_SELECT_BY_ID);
			ps.setLong(1, id);
			rs = ps.executeQuery();
			if (rs.next())
				adresse = map(rs);

			rs.close();
			ps.close();
		} catch (SQLException ex) {
			throw new DaoException("Erreur de recherche BDD Adresse", ex);
		} finally {
			factory.releaseConnection(con);
		}
		return adresse;
	}

	@Override
	public List<Adresse> lister() throws DaoException {
		List<Adresse> listeAdresses = new ArrayList<Adresse>();
		Connection con = null;
		try {
			con = factory.getConnection();
			PreparedStatement ps = con.prepareStatement(SQL_SELECT);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				listeAdresses.add(map(rs));
			}
			rs.close();
			ps.close();
		} catch (SQLException ex) {
			throw new DaoException("Erreur de lecture BDD Adresse", ex);
		} finally {
			factory.releaseConnection(con);
		}
		return listeAdresses;
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
				throw new DaoException("Erreur de suppression Adresse(" + id + ")");
			}
			ps.close();
		} catch (SQLException ex) {
			throw new DaoException("Erreur de suppression BDD Adresse", ex);
		} finally {
			factory.releaseConnection(con);
		}

	}

	@Override
	public void miseAJour(Adresse adresse) throws DaoException {
		Connection con = null;
		try {
			con = factory.getConnection();

			PreparedStatement ps = con.prepareStatement(SQL_UPDATE);
			ps.setString(1, adresse.getRue());
			ps.setString(2, adresse.getVille());
			ps.setString(3, adresse.getPays());
			ps.setString(4, adresse.getCodePostal());
			ps.setLong(5, adresse.getId());

			int statut = ps.executeUpdate();

			if (statut == 0) {
				throw new DaoException("Echec maj Adresse (aucune maj)");
			}

			ps.close();

		} catch (SQLException ex) {
			throw new DaoException("Echec maj Adresse", ex);
		} finally {
			factory.releaseConnection(con);
		}

	}

}

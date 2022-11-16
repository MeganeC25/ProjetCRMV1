package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Client;

public class ClientDaoImpl implements ClientDao {

	private static final String SQL_INSERT = "INSERT INTO CLIENT(ID_ADRESSE, NOM_SOCIETE, MAIL, NOM, PRENOM, TELEPHONE, ETAT, GENRE) VALUES(?,?,?,?,?,?,?,?)";
	private static final String SQL_SELECT = "SELECT ID, ID_ADRESSE, NOM_SOCIETE, MAIL, NOM, PRENOM, TELEPHONE, ETAT, GENRE FROM CLIENT";
	private static final String SQL_SELECT_BY_ID = SQL_SELECT + " WHERE id = ?";
	private static final String SQL_DELETE_BY_ID = "DELETE FROM CLIENT WHERE id = ? ";

	private static final String SQL_UPDATE = "UPDATE CLIENT SET ID_ADRESSE=?, NOM_SOCIETE=?, MAIL=?, NOM=?, PRENOM=?, TELEPHONE=?, ETAT=?, GENRE=? WHERE ID=?";

	private DaoFactory factory;

	public ClientDaoImpl(DaoFactory factory) {
		this.factory = factory;
	}

	@Override
	public void creer(Client client) throws DaoException {
		Connection con = null;
		try {
			con = factory.getConnection();

			PreparedStatement ps = con.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS);
			ps.setLong(1, client.getAdresse().getId());
			ps.setString(2, client.getNomSociete());
			ps.setString(3, client.getMail());
			ps.setString(4, client.getNom());
			ps.setString(5, client.getPrenom());
			ps.setString(6, client.getTelephone());
			ps.setInt(7, client.getEtat());
			ps.setInt(8, client.getGenre());

			int statut = ps.executeUpdate();

			if (statut == 0) {
				throw new DaoException("Echec creation Client (aucun ajout)");
			}

			ResultSet rsKeys = ps.getGeneratedKeys();
			if (rsKeys.next()) {
				client.setId(rsKeys.getLong(1));
			} else {
				throw new DaoException("Echec creation Client (ID non retourné)");
			}

			rsKeys.close();
			ps.close();

		} catch (SQLException ex) {
			throw new DaoException("Echec creation Client", ex);
		} finally {
			factory.releaseConnection(con);
		}

	}

	private static Client map(ResultSet resultSet) throws SQLException {
		Client client = new Client();
		client.setId(resultSet.getLong("id"));
		try {
			client.setAdresse(DaoFactory.getInstance().getAdresseDao().trouver(resultSet.getLong("id_adresse")));
		} catch (Exception e) {
			System.out.println("Erreur adresse : " + resultSet.getLong("id_adresse"));
			e.printStackTrace();
		}
		client.setNomSociete(resultSet.getString("nom_societe"));
		client.setMail(resultSet.getString("mail"));
		client.setNom(resultSet.getString("nom"));
		client.setPrenom(resultSet.getString("prenom"));
		client.setTelephone(resultSet.getString("telephone"));
		client.setEtat(resultSet.getInt("etat"));
		client.setGenre(resultSet.getInt("genre"));

		return client;
	}

	@Override
	public Client trouver(long id) throws DaoException {
		Client client = null;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			con = factory.getConnection();
			ps = con.prepareStatement(SQL_SELECT_BY_ID);
			ps.setLong(1, id);
			rs = ps.executeQuery();
			if (rs.next())
				client = map(rs);

			rs.close();
			ps.close();
		} catch (SQLException ex) {
			throw new DaoException("Erreur de recherche BDD Client", ex);
		} finally {
			factory.releaseConnection(con);
		}
		return client;
	}

	@Override
	public List<Client> lister() throws DaoException {
		List<Client> listeClient = new ArrayList<Client>();
		Connection con = null;
		try {
			con = factory.getConnection();
			PreparedStatement ps = con.prepareStatement(SQL_SELECT);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				listeClient.add(map(rs));
			}
			rs.close();
			ps.close();
		} catch (SQLException ex) {
			throw new DaoException("Erreur de lecture BDD Client", ex);
		} finally {
			factory.releaseConnection(con);
		}
		return listeClient;
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
				throw new DaoException("Erreur de suppression Client(" + id + ")");
			}
			ps.close();
		} catch (SQLException ex) {
			throw new DaoException("Erreur de suppression BDD Client", ex);
		} finally {
			factory.releaseConnection(con);
		}

	}

	@Override
	public void miseAJour(Client client) throws DaoException {
		Connection con = null;
		try {
			con = factory.getConnection();

			PreparedStatement ps = con.prepareStatement(SQL_UPDATE);
			ps.setLong(1, client.getAdresse().getId());
			ps.setString(2, client.getNomSociete());
			ps.setString(3, client.getMail());
			ps.setString(4, client.getNom());
			ps.setString(5, client.getPrenom());
			ps.setString(6, client.getTelephone());
			ps.setInt(7, client.getEtat());
			ps.setInt(8, client.getGenre());
			ps.setLong(9, client.getId());

			int statut = ps.executeUpdate();

			if (statut == 0) {
				throw new DaoException("Echec maj Client (aucune maj)");
			}

			ps.close();

		} catch (SQLException ex) {
			throw new DaoException("Echec maj Client", ex);
		} finally {
			factory.releaseConnection(con);
		}

	}

}

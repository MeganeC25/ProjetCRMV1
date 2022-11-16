package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Donné par le formateur
 *
 */
public class DaoFactory {

	private String url;
	private String username;
	private String passwd;
	private Connection con = null;

	private static DaoFactory instanceSingleton = null;

	private DaoFactory(String url, String username, String passwd) {
		this.url = url;
		this.username = username;
		this.passwd = passwd;
	}

	public static DaoFactory getInstance() {
		if (DaoFactory.instanceSingleton == null) {
			try {
				Class.forName(DaoParam.DRIVER_LOCAL);
				DaoFactory.instanceSingleton = new DaoFactory(DaoParam.URL, DaoParam.UTILISATEUR, DaoParam.MOTDEPASSE);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
		return DaoFactory.instanceSingleton;
	}

	public AdresseDao getAdresseDao() {
		return new AdresseDaoImpl(this);
	}
	
	public ClientDao getClientDao() {
		return new ClientDaoImpl(this);
	}
	
	public PaiementDao getPaiementDao() {
		return new PaiementDaoImpl(this);
	}
	
	public ProduitDao getProduitDao() {
		return new ProduitDaoImpl(this);
	}

	public PanierDao getPanierDao() {
		return new PanierDaoImpl(this);
	}
	
	public ContientDao getContientDao() {
		return new ContientDaoImpl(this);
	}
	
	Connection getConnection() throws SQLException {
		if (this.con == null) {
			this.con = DriverManager.getConnection(url, username, passwd);
		}
		return this.con;
	}

	void releaseConnection(Connection connectionRendue) {
		if (this.con == null) {
			return;
		}
		try {
			if (!this.con.isValid(10)) {
				this.con.close();
				this.con = null;
			}
		} catch (SQLException e) {
			con = null;
		}
	}

}

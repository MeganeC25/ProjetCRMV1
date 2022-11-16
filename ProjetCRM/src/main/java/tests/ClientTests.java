package tests;

import java.util.List;

import dao.AdresseDao;
import dao.ClientDao;
import dao.DaoFactory;
import model.Adresse;
import model.Client;

public class ClientTests {

	public static void main(String[] args) {
		try {
			AdresseDao adresseDao = DaoFactory.getInstance().getAdresseDao();
            ClientDao clientDao = DaoFactory.getInstance().getClientDao();
            
            // afficherListClients(clientDao);
            // Adresse adresse = adresseDao.trouver(2);
            // Client client = new Client("Toto", "Titi", "toto@gigi.com", "Picsou Inc", "0902020202", adresse);
            // clientDao.creer(client);
            // afficherListClients(clientDao);
            
            // Client c2 = clientDao.trouver(14);
            //c2.setNom("Roger");
            // clientDao.miseAJour(c2);
            
            // clientDao.supprimer(14);
            
            afficherListClients(clientDao);


		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void afficherListClients(ClientDao clientDao) {
		try {
			List<Client> listClients = clientDao.lister();
			System.out.println("---------------------------");
			for(Client a : listClients) {
				System.out.println(a);
			}
			System.out.println("---------------------------");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

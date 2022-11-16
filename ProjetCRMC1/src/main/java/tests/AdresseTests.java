package tests;

import java.util.ArrayList;
import java.util.List;

import dao.AdresseDao;
import dao.DaoFactory;
import model.Adresse;

public class AdresseTests {

	public static void main(String[] args) {

		try {
			AdresseDao adresseDao = DaoFactory.getInstance().getAdresseDao();
			Adresse adresse = new Adresse("10 rue des Blaireaux", "Rennes", "France", "35000");
			Adresse a2;
			
			afficherListAdresses(adresseDao);
			
			// adresseDao.creer(adresse);
			
			// adresse = adresseDao.trouver(5);
			// System.out.println(adresse);
			
			// a2 = adresseDao.trouver(12);
			// System.out.println(a2);
			// a2.setVille("BEDEE");
			// adresseDao.miseAJour(a2);
			//System.out.println(a2);
			
			adresseDao.supprimer(12);
			
			afficherListAdresses(adresseDao);
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	public static void afficherListAdresses(AdresseDao adresseDao) {
		try {
			List<Adresse> listAdresses = adresseDao.lister();
			System.out.println("---------------------------");
			for(Adresse a : listAdresses) {
				System.out.println(a);
			}
			System.out.println("---------------------------");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}

package tests;

import java.util.List;

import dao.ClientDao;
import dao.DaoFactory;
import dao.PaiementDao;
import model.Paiement;

public class PaiementTests {

	public static void main(String[] args) {
        
		try {
			PaiementDao paiementDao = DaoFactory.getInstance().getPaiementDao();
			ClientDao clientDao = DaoFactory.getInstance().getClientDao();
			
			afficherListPaiement(paiementDao);
			
			// Paiement p1 = new Paiement(555, 666, "BNP", clientDao.trouver(6));
			// paiementDao.creer(p1);
			
			// Paiement p2 = paiementDao.trouver(1);
			//System.out.println(p2);
			// p2.setBanque("Voleur Inc");
			//paiementDao.miseAJour(p2);
			
			// paiementDao.supprimer(2);
			
			 afficherListPaiement(paiementDao);
			
		} catch(Exception e) {
			e.printStackTrace();
		}

	}

	
	public static void afficherListPaiement(PaiementDao paiementDao) {
		try {
			List<Paiement> listPaiements = paiementDao.lister();
			System.out.println("---------------------------");
			for(Paiement a : listPaiements) {
				System.out.println(a);
			}
			System.out.println("---------------------------");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

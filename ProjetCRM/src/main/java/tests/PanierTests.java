package tests;

import java.util.List;

import dao.DaoFactory;
import dao.PanierDao;
import dao.ProduitDao;
import model.Panier;
import model.Produit;

public class PanierTests {

	public static void main(String[] args) {
		try {
			PanierDao panierDao = DaoFactory.getInstance().getPanierDao();
			
			afficherListPaniers(panierDao);
			
			Panier p1 = new Panier(DaoFactory.getInstance().getClientDao().trouver(6));
			// panierDao.creer(p1);
			
			// p1 = panierDao.trouver(6);
			//p1.setClient(DaoFactory.getInstance().getClientDao().trouver(2));
			// panierDao.miseAJour(p1);
			
			//panierDao.supprimer(6);
			
			afficherListPaniers(panierDao);
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	public static void afficherListPaniers(PanierDao panierDao) {
		try {
			List<Panier> listPaniers = panierDao.lister();
			System.out.println("---------------------------");
			for(Panier a : listPaniers) {
				System.out.println(a);
			}
			System.out.println("---------------------------");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}

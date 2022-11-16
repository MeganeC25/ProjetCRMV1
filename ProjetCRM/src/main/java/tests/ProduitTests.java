package tests;

import java.util.List;

import dao.DaoFactory;
import dao.ProduitDao;
import model.Produit;

public class ProduitTests {

	public static void main(String[] args) {
		try {
			ProduitDao produitDao = DaoFactory.getInstance().getProduitDao();
			
			afficherListProduits(produitDao);
			
			Produit p1 = new Produit("Pizza","La top des pizzas",20.3f);
			// produitDao.creer(p1);
			
			// p1 = produitDao.trouver(11);
			// p1.setDescription("Une belle paire de chaussettes");
			// produitDao.miseAJour(p1);
			
			//produitDao.supprimer(13);
			
			afficherListProduits(produitDao);
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	public static void afficherListProduits(ProduitDao produitDao) {
		try {
			List<Produit> listProduits = produitDao.lister();
			System.out.println("---------------------------");
			for(Produit a : listProduits) {
				System.out.println(a);
			}
			System.out.println("---------------------------");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


}

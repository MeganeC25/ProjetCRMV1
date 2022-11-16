package tests;

import java.util.List;

import dao.ContientDao;
import dao.DaoFactory;
import dao.ProduitDao;
import model.Contient;
import model.Panier;
import model.Produit;


public class ContientTests {

	public static void main(String[] args) {
		try {
			ContientDao contientDao = DaoFactory.getInstance().getContientDao();
			afficherListContient(contientDao);
			
			Produit p1 = DaoFactory.getInstance().getProduitDao().trouver(5); 
			Panier pa1 = DaoFactory.getInstance().getPanierDao().trouver(4);
			Contient c1 = new Contient(p1, pa1);
			// contientDao.creer(c1);
			// contientDao.supprimer(12);
			
			//c1 = contientDao.trouver(13);
			//c1.setProduit(p1);
			//c1.setPanier(pa1);
			//contientDao.miseAJour(c1);
			
			
			afficherListContient(contientDao);
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	public static void afficherListContient(ContientDao contientDao) {
		try {
			List<Contient> listContient = contientDao.lister();
			System.out.println("---------------------------");
			for(Contient a : listContient) {
				System.out.println(a);
			}
			System.out.println("---------------------------");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}

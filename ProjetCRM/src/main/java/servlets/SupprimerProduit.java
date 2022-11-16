package servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DaoFactory;
import dao.ProduitDao;
import model.Contient;
import model.Produit;

/**
 * Servlet implementation class SupprimerProduit
 */
@WebServlet("/supprimerProduit")
public class SupprimerProduit extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SupprimerProduit() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String message = "";
		
		try {
			// On recupere l'id du livre
			long id = Long.parseLong(request.getParameter("id"));
			ProduitDao produitDao = DaoFactory.getInstance().getProduitDao();

			Produit produit = produitDao.trouver(id);
			// Le produit n'existe pas ou plus
			if (produit == null) {
				request.getSession().setAttribute("messageProduit", "Produit inexistant !");
				response.sendRedirect(request.getContextPath() + "/listProduits");
				return;
			}

			List<Contient> listContient = DaoFactory.getInstance().getContientDao().listerByProduit(id);
			if (listContient.size() > 0) {
				message = produit.getNom() + " : le produit ne peut pas être supprimé car il est référencé dans des paniers ! (" + listContient.size() + " paniers)<br/>";
			} else {
				produitDao.supprimer(id);
				message = produit.getNom() + " : produit supprimé !";
			}

		} catch (Exception e) {
			e.printStackTrace();
			message = " Echec de la suppression du produit !<br/>";
		}

		request.getSession().setAttribute("messageProduit", message);
		
		response.sendRedirect( request.getContextPath() + "/listProduits" );
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

	}

}

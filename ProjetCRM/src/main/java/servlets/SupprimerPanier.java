package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DaoException;
import dao.DaoFactory;
import model.Panier;

/**
 * Servlet implementation class SupprimerPanier
 */
@WebServlet("/supprimerPanier")
public class SupprimerPanier extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SupprimerPanier() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
		long idpanier = 0;
        Panier panier = null;
        
		try {
			idpanier = Long.parseLong(request.getParameter("id"));
		} catch (Exception e) {
			e.printStackTrace();
			request.getSession().setAttribute("messagePanier", "Panier inconnu ou identifiant incorrect !");
			response.sendRedirect(request.getContextPath() + "/listPaniers");
			return;
		}
		
		try {
			panier = DaoFactory.getInstance().getPanierDao().trouver(idpanier);
		} catch (DaoException d) {
			d.printStackTrace();
			request.getSession().setAttribute("messagePanier", "Panier inconnu !");
			response.sendRedirect(request.getContextPath() + "/listPaniers");
			return;
		}
		
		try {
			DaoFactory.getInstance().getPanierDao().supprimer(idpanier);
			request.getSession().setAttribute("messagePanier", "Panier supprimé ! [" + panier.getId() + "]");
		} catch (DaoException d) {
			d.printStackTrace();
			request.getSession().setAttribute("messagePanier", "Echec de la suppression du panier [" + panier.getId() + "]. Vérifier qu'il ne contient pas des produits avant de le supprimer.");
		}
		
		response.sendRedirect(request.getContextPath() + "/listPaniers");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

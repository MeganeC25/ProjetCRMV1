package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DaoException;
import dao.DaoFactory;
import model.Contient;
import model.Panier;
import model.Produit;

/**
 * Servlet implementation class AjouterContient
 */
@WebServlet("/ajouterContient")
public class AjouterContient extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AjouterContient() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String idpanier = request.getParameter("idpanier");
		long idpanierfinal = 0;

		try {
			idpanierfinal = Long.parseLong(idpanier);
		} catch (Exception e) {
			e.printStackTrace();
			response.sendRedirect(request.getContextPath() + "/listPaniers");
			return;
		}

		try {
			Panier panier = DaoFactory.getInstance().getPanierDao().trouver(idpanierfinal);
		} catch (DaoException d) {
			d.printStackTrace();
			response.sendRedirect(request.getContextPath() + "/listPaniers");
			return;
		}

		try {
			request.setAttribute("listProduits", DaoFactory.getInstance().getProduitDao().lister());
		} catch (DaoException d) {
			d.printStackTrace();
			response.sendRedirect(request.getContextPath() + "/listPaniers");
			return;
		}

		request.setAttribute("idpanier", idpanier);

		this.getServletContext().getRequestDispatcher("/WEB-INF/ajouterContient.jsp").forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String idpanier = request.getParameter("idpanier");
		String idproduit = request.getParameter("idproduit");
		long idpanierfinal = 0;
		long idproduitfinal = 0;
		Panier panier = null;
		Produit produit = null;

		try {
			idpanierfinal = Long.parseLong(idpanier);
		} catch (Exception e) {
			e.printStackTrace();
			response.sendRedirect(request.getContextPath() + "/listPaniers");
			return;
		}

		try {
			idproduitfinal = Long.parseLong(idproduit);
		} catch (Exception e) {
			e.printStackTrace();
			response.sendRedirect(request.getContextPath() + "/listPaniers");
			return;
		}

		try {
			panier = DaoFactory.getInstance().getPanierDao().trouver(idpanierfinal);
		} catch (DaoException d) {
			d.printStackTrace();
			response.sendRedirect(request.getContextPath() + "/listPaniers");
			return;
		}

		try {
			produit = DaoFactory.getInstance().getProduitDao().trouver(idproduitfinal);
		} catch (DaoException d) {
			d.printStackTrace();
			response.sendRedirect(request.getContextPath() + "/listPaniers");
			return;
		}

		try {
			Contient contient = new Contient(produit, panier);
			DaoFactory.getInstance().getContientDao().creer(contient);
			request.getSession().setAttribute("messageContient", "Produit ajouté au panier !");
		} catch (DaoException d) {
			d.printStackTrace();
			request.getSession().setAttribute("messageContient", "Echec de l'ajout du produit ajouté au panier !");
		}

		try {
			request.setAttribute("listContients", DaoFactory.getInstance().getContientDao().listerByPanier(idpanierfinal));
		} catch (DaoException d) {
			d.printStackTrace();
        	request.getSession().setAttribute("messagePanier", "Probleme de lecture du contenu du panier : " + idpanier);
			response.sendRedirect(request.getContextPath() + "/listPaniers");
			return;
		}
		
		request.setAttribute("idpanier", idpanier);
		this.getServletContext().getRequestDispatcher("/WEB-INF/listContients.jsp").forward(request, response);
	}

}

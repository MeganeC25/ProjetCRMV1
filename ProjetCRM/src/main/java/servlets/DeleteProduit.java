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
 * Servlet implementation class DeleteProduit
 */
@WebServlet("/deleteProduit")
public class DeleteProduit extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DeleteProduit() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String messageErreur = null;
		String message = null;
		String supprimer = "N";

		try {
			// On recupere l'id du produit
			long id = Long.parseLong(request.getParameter("idproduit"));

			// On recupere le produit
			Produit produit = DaoFactory.getInstance().getProduitDao().trouver(id);

			// Le produit n'existe pas
			if (produit == null) {
				supprimer = "Y";
				response.sendRedirect(request.getContextPath() + "/listProduits");
				return;
			}

			request.setAttribute("idproduit", id);
			request.setAttribute("produit", produit);

		} catch (Exception e) {
			e.printStackTrace();
			messageErreur = "Erreur lors de la préparation (GET) de la suppression du livre !<br/>";
			supprimer = "Y";
		}

		request.setAttribute("supprimer", supprimer);
		request.setAttribute("message", message);
		request.setAttribute("messageErreur", messageErreur);

		this.getServletContext().getRequestDispatcher("/WEB-INF/DeleteProduit.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String messageErreur = null;
		String message = null;
		String supprimer = "N";

		try {
			// On recupere l'id du livre
			long id = Long.parseLong(request.getParameter("idproduit"));
			ProduitDao produitDao = DaoFactory.getInstance().getProduitDao();

			Produit produit = produitDao.trouver(id);
			// Le produit n'existe pas
			if (produit == null) {
				supprimer = "Y";
				response.sendRedirect(request.getContextPath() + "/listProduits");
				return;
			}

			List<Contient> listContient = DaoFactory.getInstance().getContientDao().listerByProduit(id);
			if (listContient.size() > 0) {
				supprimer = "Y";
				messageErreur = " Le produit ne peut pas être supprimé car il est référencé dans des paniers ! (" + listContient.size() + " paniers)<br/>";
			} else {
				produitDao.supprimer(id);
				message = "Produit supprimé !";
				supprimer = "Y";
			}

			request.setAttribute("idproduit", id);
			request.setAttribute("produit", produit);

		} catch (Exception e) {
			e.printStackTrace();
			messageErreur = " Erreur lors de la suppression du produit !<br/>";
		}

		request.setAttribute("message", message);
		request.setAttribute("supprimer", supprimer);
		request.setAttribute("messageErreur", messageErreur);

		this.getServletContext().getRequestDispatcher("/WEB-INF/DeleteProduit.jsp").forward(request, response);
	}

}

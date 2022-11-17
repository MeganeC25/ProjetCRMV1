package servlets;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DaoException;
import dao.DaoFactory;
import model.Produit;

/**
 * Servlet implementation class ModifierProduit
 */
@WebServlet("/modifierProduit")
public class ModifierProduit extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ModifierProduit() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String idproduit = request.getParameter("id");
		try {
			Produit produit = DaoFactory.getInstance().getProduitDao().trouver(Long.parseLong(idproduit));
			if(produit == null) {
				response.sendRedirect(request.getContextPath() + "/listProduits");
				return;
			}
			request.setAttribute("produit", produit);
		} catch (DaoException d) {
			d.printStackTrace();
			response.sendRedirect(request.getContextPath() + "/listProduits");
			return;
		}

		this.getServletContext().getRequestDispatcher("/WEB-INF/modifierProduit.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Map<String, String> erreurs = new HashMap<String, String>();

		String idproduit = request.getParameter("idproduit");
		String nom = request.getParameter("nom");
		String description = request.getParameter("description");
		String prix = request.getParameter("prix");
		float prixBdd = 0;
		Produit produit = null;
		long id = 0;

		try {
			id = Long.parseLong(idproduit);
		} catch (Exception e) {
			response.sendRedirect(request.getContextPath() + "/listProduits");
			return;
		}

		try {
			prixBdd = Float.parseFloat(prix);
		} catch (Exception e) {
			erreurs.put("prix", "Le format du prix n'est pas valide");
		}

		if ((nom == null) || (nom.trim().length() <= 0)) {
			erreurs.put("nom", "Le nom est obligatoire");
		} else {
			if (nom.trim().length() > 255) {
				erreurs.put("nom", "Le nom doit contenir moins de 256 caractères");
			}
		}

		if (erreurs.isEmpty()) {
			try {
				produit = DaoFactory.getInstance().getProduitDao().trouver(id);
			} catch (DaoException d) {
				d.printStackTrace();
				response.sendRedirect(request.getContextPath() + "/listProduits");
				return;
			}
			
			produit.setNom(nom);
			produit.setDescription(description);
			produit.setPrix(prixBdd);
			
			try {
				DaoFactory.getInstance().getProduitDao().miseAJour(produit);
				response.sendRedirect(request.getContextPath() + "/listProduits");
				return;
			} catch(DaoException d) {
				d.printStackTrace();
				erreurs.put("produit", "Echec de la mise à jour du produit");
			}
		}

		this.getServletContext().getRequestDispatcher("/WEB-INF/modifierProduit.jsp").forward(request, response);
	}

}

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
 * Servlet implementation class AjouterProduit
 */
@WebServlet("/ajouterProduit")
public class AjouterProduit extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AjouterProduit() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.getServletContext().getRequestDispatcher("/WEB-INF/ajouterProduit.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Map<String, String> erreurs = new HashMap<String, String>();

		String nom = request.getParameter("nom");
		String description = request.getParameter("description");
		String prix = request.getParameter("prix");
		float numPrix = 0;

		if ((nom == null) || (nom.trim().length() <= 0)) {
			erreurs.put("nom", "Le nom est obligatoire");
		} else {
			if (nom.trim().length() > 255) {
				erreurs.put("nom", "Le nom doit contenir moins de 256 caractères");
			}
		}

		try {
			numPrix = Float.parseFloat(prix);
		} catch (Exception e) {
			e.printStackTrace();
			erreurs.put("prix", "Le format du prix n'est pas valide");
		}

		if (erreurs.isEmpty()) {
			try {

				Produit produit = new Produit(nom, description, numPrix);

				DaoFactory.getInstance().getProduitDao().creer(produit);
				response.sendRedirect(request.getContextPath() + "/listProduits");
				return;

			} catch (DaoException d) {
				d.printStackTrace();
				erreurs.put("produit", "Erreur lors de l'enregistrement !");
			}
		}

		this.getServletContext().getRequestDispatcher("/WEB-INF/ajouterProduit.jsp").forward(request, response);

	}

}

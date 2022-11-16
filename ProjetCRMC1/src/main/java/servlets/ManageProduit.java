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
import dao.ProduitDao;
import model.Produit;

/**
 * Servlet implementation class ManageProduit
 */
@WebServlet("/manageProduit")
public class ManageProduit extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ManageProduit() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String idproduit = request.getParameter("idproduit");
		String libelleAction = "";

		if ((idproduit == null) || (idproduit.trim().length() <= 0)) {
			libelleAction = "Ajout";
		} else {
			libelleAction = "Modification";
			ProduitDao produitDao = DaoFactory.getInstance().getProduitDao();
			int id = 0;
			try {
				id = Integer.parseInt(idproduit);
			} catch (Exception e) {
				System.out.println("Erreur sur l'identifiant du produit : " + idproduit + " (parsing)");
				response.sendRedirect(request.getContextPath() + "/listProduits");
				return;
			}

			if (id > 0) {
				try {
					Produit produit = produitDao.trouver(id);
					if (produit == null) {
						System.out.println("Erreur sur l'identifiant du produit : " + idproduit + " (inconnu)");
						response.sendRedirect(request.getContextPath() + "/listProduits");
						return;
					}
					request.setAttribute("produit", produit);
				} catch (Exception e) {
					System.out.println("Erreur sur l'identifiant du produit : " + idproduit);
					e.printStackTrace();
					response.sendRedirect(request.getContextPath() + "/listProduits");
					return;
				}
			} else {
				response.sendRedirect(request.getContextPath() + "/listProduits");
				return;
			}
		}

		request.setAttribute("idproduit", idproduit);
		request.setAttribute("libelleAction", libelleAction);

		this.getServletContext().getRequestDispatcher("/WEB-INF/ManageProduit.jsp").forward(request, response);

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
		String description = request.getParameter("descrip");
		String prix = request.getParameter("prix");

		// Gestion des messages d'erreurs
		String messageErreur = "";
		String message = "";
		String libelleAction = "Modification";
		boolean ctrl = true;

		ProduitDao produitDao = DaoFactory.getInstance().getProduitDao();
		Produit produit = null;
		float numPrix = 0;

		// Controle des valeurs des champs obligatoires
		if ((nom == null) || (nom.trim().length() <= 0)) {
			ctrl = false;
			messageErreur = messageErreur + "Le nom est obligatoire<br/>";
			erreurs.put("nom", "Le nom est obligatoire");
		} else {
			if (nom.trim().length() > 255) {
				ctrl = false;
				messageErreur = messageErreur + "Le nom doit contenir moins de 256 caractères<br/>";
				erreurs.put("nom", "Le nom doit contenir moins de 256 caractères");
			}
		}

		try {
			numPrix = Float.parseFloat(prix);
		} catch (Exception e) {
			e.printStackTrace();
			ctrl = false;
			messageErreur = messageErreur + "Le format du prix n'est pas valide<br/>";
			erreurs.put("prix", "Le format du prix n'est pas valide");
		}

		// Les controles sont Ok
		if (ctrl) {

			if ((idproduit == null) || (idproduit.trim().length() <= 0)) {

				// Ajout d'un produit
				libelleAction = "Ajout";

				try {

					produit = new Produit(nom, description, numPrix);

					produitDao.creer(produit);

					message = "Produit enregitré !";
					request.setAttribute("idproduit", produit.getId().toString());
					request.setAttribute("produit", produit);
					libelleAction = "Modification";
				} catch (DaoException d) {
					ctrl = false;
					messageErreur = messageErreur + "Erreur lors de l'enregistrement !";
					d.printStackTrace();
				}

			} else {

				// Modification d'un produit
				try {
					produit = produitDao.trouver(Long.parseLong(idproduit));
					produit.setNom(nom);
					produit.setDescription(description);
					produit.setPrix(numPrix);

					produitDao.miseAJour(produit);

					request.setAttribute("idproduit", produit.getId().toString());
					request.setAttribute("produit", produit);
					message = "Produit modifié !";
				} catch (DaoException d) {
					ctrl = false;
					messageErreur = messageErreur + "Erreur lors de la mise à jour !";
					d.printStackTrace();
				}
			}
		}

		// Cas si controle Ko en ajout ou modif
		if (!ctrl) {
			produit = new Produit();
			try {
				produit = produitDao.trouver(Long.parseLong(idproduit));
			} catch (Exception e) {
				produit.setNom(nom);
				produit.setDescription(description);
				produit.setPrix(numPrix);
			}
			request.setAttribute("produit", produit);
			request.setAttribute("idproduit", idproduit);
		}

		request.setAttribute("erreurs", erreurs);
		request.setAttribute("message", message);
		request.setAttribute("messageErreur", messageErreur);

		request.setAttribute("libelleAction", libelleAction);

		this.getServletContext().getRequestDispatcher("/WEB-INF/ManageProduit.jsp").forward(request, response);

	}

}

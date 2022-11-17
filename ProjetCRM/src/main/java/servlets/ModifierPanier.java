package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DaoException;
import dao.DaoFactory;
import model.Client;
import model.Panier;

/**
 * Servlet implementation class ModifierPanier
 */
@WebServlet("/modifierPanier")
public class ModifierPanier extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ModifierPanier() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String idpanier = request.getParameter("id");
		try {
			Panier panier = DaoFactory.getInstance().getPanierDao().trouver(Long.parseLong(idpanier));
			if(panier == null) {
				request.getSession().setAttribute("messagePanier", "Panier inconnu !");
				response.sendRedirect(request.getContextPath() + "/listPaniers");
				return;
			}
			request.setAttribute("panier", panier);
		} catch (DaoException d) {
			d.printStackTrace();
			request.getSession().setAttribute("messagePanier", "Panier inconnu ou mauvais identifiant !");
			response.sendRedirect(request.getContextPath() + "/listPaniers");
			return;
		}
		
		try {
			request.setAttribute("listClients", DaoFactory.getInstance().getClientDao().lister());
		} catch(DaoException d) {
			d.printStackTrace();
			request.getSession().setAttribute("messagePanier", "Echec de l'accès aux clients !");
			response.sendRedirect(request.getContextPath() + "/listPaniers");
			return;
		}
		
		this.getServletContext().getRequestDispatcher("/WEB-INF/modifierPanier.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Map<String, String> erreurs = new HashMap<String, String>();

		String idpanier = request.getParameter("idpanier");
		String idclient = request.getParameter("idclient");
        Client client = null;
        Panier panier = null;
		long idclientfinal = 0;
		long idpanierfinal = 0;
		
		try {
			idclientfinal = Long.parseLong(idclient);
		} catch (Exception e) {
			request.getSession().setAttribute("messagePanier", "Le numéro du client est incorrect : " + idclient);
			response.sendRedirect(request.getContextPath() + "/listPaniers");
			return;
		}

		try {
			idpanierfinal = Long.parseLong(idpanier);
		} catch (Exception e) {
			request.getSession().setAttribute("messagePanier", "Le numéro du panier est incorrect : " + idclient);
			response.sendRedirect(request.getContextPath() + "/listPaniers");
			return;
		}

		
		try {
			client = DaoFactory.getInstance().getClientDao().trouver(idclientfinal);
			if (client == null) {
				request.getSession().setAttribute("messagePanier", "Client inconnu : " + idclient);
				response.sendRedirect(request.getContextPath() + "/listPaniers");
				return;
			}
		} catch (DaoException d) {
			d.printStackTrace();
			request.getSession().setAttribute("messagePanier", "Echec lors de l'accès au client : " + idclient);
			response.sendRedirect(request.getContextPath() + "/listPaniers");
			return;
		}
		
		try {
			panier = DaoFactory.getInstance().getPanierDao().trouver(idpanierfinal);
		} catch (DaoException d) {
			d.printStackTrace();
			request.getSession().setAttribute("messagePanier", "Panier inconnu !");
			response.sendRedirect(request.getContextPath() + "/listPaniers");
			return;
		}
		
		try {
			panier.setClient(client);
			DaoFactory.getInstance().getPanierDao().miseAJour(panier);
			request.getSession().setAttribute("messagePanier", "Panier modifié : " + panier.getId());
		} catch (DaoException d) {
			d.printStackTrace();
			request.getSession().setAttribute("messagePanier", "Echec de la mise à jour du panier : " + panier.getId());
		}
		
		response.sendRedirect(request.getContextPath() + "/listPaniers");
	}

}

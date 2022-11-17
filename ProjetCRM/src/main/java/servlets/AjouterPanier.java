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
import model.Client;
import model.Panier;

/**
 * Servlet implementation class AjouterPanier
 */
@WebServlet("/ajouterPanier")
public class AjouterPanier extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AjouterPanier() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			request.setAttribute("listClients", DaoFactory.getInstance().getClientDao().lister());
		} catch(DaoException d) {
			d.printStackTrace();
			response.sendRedirect(request.getContextPath() + "/listPaniers");
			return;
		}
		
        this.getServletContext().getRequestDispatcher("/WEB-INF/ajouterPanier.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Map<String, String> erreurs = new HashMap<String, String>();

		String idclient = request.getParameter("idclient");
        Client client = null;
		long idclientfinal = 0;
		
		try {
			idclientfinal = Long.parseLong(idclient);
		} catch (Exception e) {
			request.getSession().setAttribute("messagePanier", "Le numéro du client est incorrect : " + idclient);
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
			Panier panier = new Panier(client);
			DaoFactory.getInstance().getPanierDao().creer(panier);
			request.getSession().setAttribute("messagePanier", "Panier créé !");
		} catch (DaoException d) {
			d.printStackTrace();
			request.getSession().setAttribute("messagePanier", "Echec lors de la création du panier");
		}
		
		response.sendRedirect(request.getContextPath() + "/listPaniers");
	}

}

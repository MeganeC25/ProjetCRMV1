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

/**
 * Servlet implementation class SupprimerContient
 */
@WebServlet("/supprimerContient")
public class SupprimerContient extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SupprimerContient() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String idcontient = request.getParameter("idcontient");
		String idpanier = request.getParameter("idpanier");
		long idcontientfinal = 0;
		long idpanierfinal = 0;
		Contient contient = null;
		
		try {
			idcontientfinal = Long.parseLong(idcontient);
		} catch (Exception e) {
			request.getSession().setAttribute("messagePanier", "Probleme sur l'identifiant de Contient : " + idcontient);
			response.sendRedirect(request.getContextPath() + "/listPaniers");
			return;
		}
		
		try {
			idpanierfinal = Long.parseLong(idpanier);
		} catch (Exception e) {
			request.getSession().setAttribute("messagePanier", "Probleme sur l'identifiant de panier : " + idpanier);
			response.sendRedirect(request.getContextPath() + "/listPaniers");
			return;
		}
		
		try {
			contient = DaoFactory.getInstance().getContientDao().trouver(idcontientfinal);
		} catch (Exception e) {
			request.getSession().setAttribute("messagePanier", "Probleme sur l'acces au contenu " + idcontient + " du panier");
			response.sendRedirect(request.getContextPath() + "/listPaniers");
			return;
		}
		
		if (contient.getPanier().getId() != idpanierfinal) {
			request.getSession().setAttribute("messagePanier", "Le contenu " + idcontient + " ne correspond pas au panier " + idpanierfinal);
			response.sendRedirect(request.getContextPath() + "/listPaniers");
			return;
		}
		
		try {
			DaoFactory.getInstance().getContientDao().supprimer(idcontientfinal);
			request.getSession().setAttribute("messageContient", "Contenu " + idcontient + " supprimé !");
		} catch (DaoException d) {
			d.printStackTrace();
			request.getSession().setAttribute("messageContient", "Echec de la suppression du contenu !");
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

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DaoException;
import dao.DaoFactory;
import dao.PaiementDao;

/**
 * Servlet implementation class SupprimerPaiement
 */
@WebServlet("/SupprimerPaiement")
public class SupprimerPaiement extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private PaiementDao paiementDao;
	
	
    public SupprimerPaiement() {
        super();
        paiementDao = DaoFactory.getInstance().getPaiementDao();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			long id = Long.parseLong(request.getParameter("id"));
			paiementDao.supprimer(id);
			
			request.getSession().setAttribute("confirmMessage", "Le moyen de paiement a bien été supprimé.");			
		} catch (DaoException e) {
			e.printStackTrace();
		}
		
		
		response.sendRedirect(request.getContextPath() + "/ListPaiements");
	}
}

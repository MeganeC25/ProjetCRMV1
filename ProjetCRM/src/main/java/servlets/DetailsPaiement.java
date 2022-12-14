package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ClientDao;
import dao.DaoException;
import dao.DaoFactory;
import dao.PaiementDao;

/**
 * Servlet implementation class DetailsPaiement
 */
@WebServlet("/DetailsPaiement")
public class DetailsPaiement extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private PaiementDao paiementDao;
	
    public DetailsPaiement() {
        super();
        paiementDao = DaoFactory.getInstance().getPaiementDao();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			long id = Long.parseLong(request.getParameter("id"));
			request.setAttribute("paiement",  paiementDao.trouver(id));
		} catch (DaoException e) {
			e.printStackTrace();
		}
		this.getServletContext().getRequestDispatcher("WEB-INF/detailsPaiement.jsp").forward(request, response);
	}

}

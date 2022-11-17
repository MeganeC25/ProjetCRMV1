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
 * Servlet implementation class ListPaiements
 */
@WebServlet("/ListPaiements")
public class ListPaiements extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private PaiementDao paiementDao;
	
	public ListPaiements() {
		super();
		paiementDao = DaoFactory.getInstance().getPaiementDao();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		try {
			request.setAttribute("paiements", paiementDao.lister());
		} catch (DaoException e) {
			e.printStackTrace();
		}
		
		this.getServletContext().getRequestDispatcher("WEB-INF/listPaiements.jsp").forward(request, response);
		
		request.getSession().removeAttribute("confirmMessage");
	}
}
	
	

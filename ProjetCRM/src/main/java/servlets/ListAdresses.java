package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.AdresseDao;
import dao.DaoException;
import dao.DaoFactory;

@WebServlet("/ListAdresses")
public class ListAdresses extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private AdresseDao adresseDao;

	public ListAdresses() {
		super();
		adresseDao = DaoFactory.getInstance().getAdresseDao();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			request.setAttribute("adresses", adresseDao.lister());
		} catch (DaoException e) {
			e.printStackTrace();
		}

		this.getServletContext().getRequestDispatcher("/WEB-INF/listAdresses.jsp").forward(request, response);
	}
}

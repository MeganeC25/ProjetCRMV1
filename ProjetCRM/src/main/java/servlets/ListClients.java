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

@WebServlet("/ListClients")
public class ListClients extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private ClientDao clientDao;

	public ListClients() {
		super();
		clientDao = DaoFactory.getInstance().getClientDao();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
		request.setAttribute("client", clientDao.lister());
		} catch (DaoException e) {
			e.printStackTrace();
		}
	
	this.getServletContext().getRequestDispatcher("/WEB-INF/listClient.jsp").forward(request,response);
	}


}

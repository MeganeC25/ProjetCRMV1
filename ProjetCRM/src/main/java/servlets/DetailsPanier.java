package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DaoException;
import dao.DaoFactory;

/**
 * Servlet implementation class DetailsPanier
 */
@WebServlet("/detailsPanier")
public class DetailsPanier extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DetailsPanier() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			long id = Long.parseLong(request.getParameter("id"));
			request.setAttribute("panier", DaoFactory.getInstance().getPanierDao().trouver(id));
		} catch (DaoException e) {
			e.printStackTrace();
			request.getSession().setAttribute("messagePanier", "Panier inconnu ou identifiant incorrect !");
			response.sendRedirect(request.getContextPath() + "/listPaniers");
			return;
		}
		this.getServletContext().getRequestDispatcher("/WEB-INF/detailsPanier.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

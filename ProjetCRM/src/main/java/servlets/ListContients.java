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
 * Servlet implementation class ListContient
 */
@WebServlet("/listContients")
public class ListContients extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ListContients() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String idpanier = request.getParameter("idpanier");
		long idpanierfinale = 0;
        
		try {
			idpanierfinale = Long.parseLong(idpanier);
		} catch(Exception e) {
        	e.printStackTrace();
        	request.getSession().setAttribute("messagePanier", "Identifiant du panier incorrect : " + idpanier);
			response.sendRedirect(request.getContextPath() + "/listPaniers");
			return;
		}
		
		try {
			request.setAttribute("listContients", DaoFactory.getInstance().getContientDao().listerByPanier(idpanierfinale));
		} catch (DaoException d) {
			d.printStackTrace();
        	request.getSession().setAttribute("messagePanier", "Probleme de lecture du contenu du panier : " + idpanier);
			response.sendRedirect(request.getContextPath() + "/listPaniers");
			return;
		}
		
		request.setAttribute("idpanier", idpanier);
		
		this.getServletContext().getRequestDispatcher("/WEB-INF/listContients.jsp").forward(request, response);
		request.getSession().removeAttribute("messageContient");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

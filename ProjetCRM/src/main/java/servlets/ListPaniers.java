package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DaoFactory;

/**
 * Servlet implementation class ListPaniers
 */
@WebServlet("/listPaniers")
public class ListPaniers extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ListPaniers() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
                
        try {
        	request.setAttribute("listPaniers", DaoFactory.getInstance().getPanierDao().lister());
        } catch(Exception e) {
        	e.printStackTrace();
        	request.getSession().removeAttribute("messagePanier");
			response.sendRedirect(request.getContextPath());
			return;
        }
		
		this.getServletContext().getRequestDispatcher("/WEB-INF/listPaniers.jsp").forward(request, response);
		
		request.getSession().removeAttribute("messagePanier");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}

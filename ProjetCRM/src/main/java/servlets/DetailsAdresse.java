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


@WebServlet("/DetailsAdresse")
public class DetailsAdresse extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private AdresseDao adresseDao;

    public DetailsAdresse() {
        super();
        adresseDao = DaoFactory.getInstance().getAdresseDao();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		try {
			long id = Long.parseLong(request.getParameter("id"));
		long id = Long.parseLong(request.getParameter("id"));
		
		try {
			request.setAttribute("adresse", adresseDao.trouver(id));
		} catch (DaoException e) {
			e.printStackTrace();
		} 
		this.getServletContext().getRequestDispatcher("/WEB-INF/detailsAdresse.jsp").forward(request, response);
	}
	

}

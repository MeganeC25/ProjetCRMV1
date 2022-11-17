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


@WebServlet("/SupprimerAdresse")
public class SupprimerAdresse extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
private AdresseDao adresseDao;

    public SupprimerAdresse() {
        super();
        adresseDao = DaoFactory.getInstance().getAdresseDao();   
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		long id = Long.parseLong(request.getParameter("id"));
		
		try {
			adresseDao.supprimer(id);
		} catch (DaoException e) {
			e.printStackTrace();
		}
		response.sendRedirect( request.getContextPath() + "/ListAdresses" );
	}
}
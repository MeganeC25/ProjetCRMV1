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
import model.Adresse;

@WebServlet("/AjouterAdresse")
public class AjouterAdresse extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private AdresseDao adresseDao;

	public AjouterAdresse() {
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
		this.getServletContext().getRequestDispatcher("/WEB-INF/ajouterAdresse.jsp").forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String rue = request.getParameter("rue");
		String ville = request.getParameter("ville");
		String pays = request.getParameter("pays");
		String codePostal = request.getParameter("codePostal");
		
		
		try {
			String idString = request.getParameter("id");
			long id = Long.parseLong(idString);
			Adresse adresse = new Adresse (rue, ville, pays, codePostal);
			adresseDao.creer(adresse);
		} catch (DaoException e) {
			e.printStackTrace();
		}
		
		this.getServletContext().getRequestDispatcher("/WEB-INF/ajouterAdresse.jsp").forward(request, response);
	}

}

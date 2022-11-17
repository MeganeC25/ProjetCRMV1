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

@WebServlet("/ModifierAdresse")
public class ModifierAdresse extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private AdresseDao adresseDao;

	public ModifierAdresse() {
		super();
		adresseDao = DaoFactory.getInstance().getAdresseDao();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			request.setAttribute("adresse", adresseDao.lister());
		} catch (DaoException e) {

			e.printStackTrace();
		}
		this.getServletContext().getRequestDispatcher("/WEB-INF/modifierAdresse.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		long idAdresse = Long.parseLong(request.getParameter("adresse"));
		
		String rue = request.getParameter("rue");
		String ville = request.getParameter("ville");
		String pays = request.getParameter("pays");
		String codePostal = request.getParameter("codePostal");

		try {
			Adresse adresse = adresseDao.trouver(idAdresse);
			adresse.setRue(rue);
			adresse.setVille(ville);
			adresse.setPays(pays);
			adresse.setCodePostal(codePostal);
			
			adresseDao.miseAJour(adresse);

		} catch (Exception e) {
			e.printStackTrace();
		}

		this.getServletContext().getRequestDispatcher("/WEB-INF/modifierAdresse.jsp").forward(request, response);
	}

}

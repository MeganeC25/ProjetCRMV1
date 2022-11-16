package servlets;

import java.io.IOException;

import javax.el.ListELResolver;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.AdresseDao;
import dao.ClientDao;
import dao.DaoException;
import dao.DaoFactory;
import model.Client;

@WebServlet("/AjouterClient")
public class AjouterClient extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private ClientDao clientDao;
	private AdresseDao adresseDao;

	public AjouterClient() {
		super();
		clientDao = DaoFactory.getInstance().getClientDao();
		adresseDao = DaoFactory.getInstance().getAdresseDao();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			request.setAttribute("adresses", adresseDao.lister());
		} catch (DaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.getServletContext().getRequestDispatcher("/WEB-INF/ajouterClient.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String nom = request.getParameter("nom");
		String prenom = request.getParameter("prenom");
		String mail = request.getParameter("mail");
		String nomSociete = request.getParameter("nomSociete");
		String telephone = request.getParameter("telephone");
		int etat = Integer.parseInt(request.getParameter("etat"));
		int genre = Integer.parseInt(request.getParameter("genre"));

		try {
			long id = Long.parseLong(request.getParameter("adresse"));
			Client client = new Client(nom, prenom, mail, nomSociete, telephone, adresseDao.trouver(id));
			clientDao.creer(client);
		} catch (Exception e) {
			e.printStackTrace();
		}

		this.getServletContext().getRequestDispatcher("/WEB-INF/ajouterClient.jsp").forward(request, response);
	}

}

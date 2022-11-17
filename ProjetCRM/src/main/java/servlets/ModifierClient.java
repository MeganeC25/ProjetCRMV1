package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.AdresseDao;
import dao.ClientDao;
import dao.DaoException;
import dao.DaoFactory;
import model.Adresse;
import model.Client;

@WebServlet("/ModifierClient")
public class ModifierClient extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private ClientDao clientDao;
	private AdresseDao adresseDao;

	public ModifierClient() {
		super();
		clientDao = DaoFactory.getInstance().getClientDao();
		adresseDao = DaoFactory.getInstance().getAdresseDao();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		long id = Long.parseLong(request.getParameter("id"));
		try {
			request.setAttribute("client", clientDao.trouver(id));

			request.setAttribute("adresse", adresseDao.lister());
		} catch (DaoException e) {

			e.printStackTrace();
		}
		this.getServletContext().getRequestDispatcher("/WEB-INF/modifierClient.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		long idClient = Long.parseLong(request.getParameter("id"));
		long idAdresse = Long.parseLong(request.getParameter("adresse"));
		
		String nom = request.getParameter("nom");
		String prenom = request.getParameter("prenom");
		String mail = request.getParameter("mail");
		String nomSociete = request.getParameter("nomSociete");
		String telephone = request.getParameter("telephone");
		int etat = Integer.parseInt(request.getParameter("etat"));
		int genre = Integer.parseInt(request.getParameter("genre"));

		

		try {
			Client client = clientDao.trouver(idClient);
			Adresse adresse = adresseDao.trouver(idAdresse);
			client.setNom(nom);
			client.setPrenom(prenom);
			client.setMail(mail);
			client.setNomSociete(nomSociete);
			client.setTelephone(telephone);
			client.setEtat(etat);
			client.setGenre(genre);
			client.setAdresse(adresse);

			clientDao.miseAJour(client);

		} catch (Exception e) {
			e.printStackTrace();
		}

		this.getServletContext().getRequestDispatcher("/WEB-INF/modifierClient.jsp").forward(request, response);
	}

}

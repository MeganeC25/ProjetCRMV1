package servlets;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ClientDao;
import dao.DaoException;
import dao.DaoFactory;
import dao.PaiementDao;
import model.Client;
import model.Paiement;

/**
 * Servlet implementation class AjouterPaiement
 */
@WebServlet("/AjouterPaiement")
public class AjouterPaiement extends HttpServlet {
	private static final long serialVersionUID = 1L;
     
	private PaiementDao paiementDao;
	private ClientDao	clientDao;
  
    public AjouterPaiement() {
        super();
        paiementDao = DaoFactory.getInstance().getPaiementDao();
        clientDao = DaoFactory.getInstance().getClientDao();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			request.setAttribute("clients", clientDao.lister());
		} catch (DaoException e) {
			e.printStackTrace();
		}
		this.getServletContext().getRequestDispatcher("/WEB-INF/ajouterPaiement.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Map<String,String> erreurs = new HashMap<String,String>();
		
		String banque = request.getParameter("banque");
		
		int numCarte = 0;
		try {
			numCarte = Integer.parseInt(request.getParameter("numCarte"));
		} catch(NumberFormatException e) {
			erreurs.put("numCarte", "Veuillez entrer des caractères numériques.");
		}
		
		int codeConf = 0;
		try {
			codeConf = Integer.parseInt(request.getParameter("codeConf"));
		} catch(NumberFormatException e) {
			erreurs.put("codeConf", "Veuillez entrer des caractères numériques.");
		}
		
		if(banque != null) {
			if(banque.length() < 1 || banque.length() > 255) {
				erreurs.put("banque", "Le nom de banque doit compris entre 1 et 255 caractères.");
			}
		} else {
			erreurs.put("banque", "Veuillez entrer un nom de banque.");
		}
		
		Client client = null;
		try {
			Long idClient = Long.parseLong(request.getParameter("client"));
			client = clientDao.trouver(idClient);
		} catch(DaoException | NumberFormatException e) {
			erreurs.put("clientPaiement", "Erreur: le client n'existe pas.");
		}
				
		Paiement paiement = new Paiement();
		paiement.setNumCarte(numCarte);
		paiement.setCodeConf(codeConf);
		paiement.setBanque(banque);
		paiement.setClient(client);
		
		if(erreurs.isEmpty()) {
			try {
				paiementDao.creer(paiement);
				
				request.getSession().setAttribute("confirmMessage",  "Le moyen de paiement a bien été ajouté.");
			} catch(DaoException e) {
				e.printStackTrace();
			}
			
			response.sendRedirect(request.getContextPath() + "/ListPaiements");
		} else {
			try {
				request.setAttribute("clients", clientDao.lister());
			} catch(DaoException e) {
				e.printStackTrace();
			}
			request.setAttribute("client", client);
			request.setAttribute("erreurs", erreurs);
			
			this.getServletContext().getRequestDispatcher("/WEB-INF/ajouterPaiement.jsp").forward(request, response);
		}
	}
}

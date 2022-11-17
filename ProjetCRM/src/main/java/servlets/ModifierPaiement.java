package servlets;

import java.io.IOException;
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
 * Servlet implementation class ModifierPaiement
 */
@WebServlet("/ModifierPaiement")
public class ModifierPaiement extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	private PaiementDao paiementDao;
	private ClientDao	clientDao;
  
    public ModifierPaiement() {
        super();
        paiementDao = DaoFactory.getInstance().getPaiementDao();
        clientDao = DaoFactory.getInstance().getClientDao();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		long id = Long.parseLong(request.getParameter("id"));
		try {
			request.setAttribute("paiements", paiementDao.trouver(id));
			request.setAttribute("clients", clientDao.lister());
		} catch (DaoException e) {
			e.printStackTrace();
		}
		
		this.getServletContext().getRequestDispatcher("/WEB-INF/modifierPaiement.jsp").forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		long id = Long.parseLong(request.getParameter("id"));
		int numCarte = Integer.parseInt(request.getParameter("numCarte"));
		int codeConf = Integer.parseInt(request.getParameter("codeConf"));
		String banque = request.getParameter("banque");
		long idClient = Long.parseLong(request.getParameter("client"));
		
		Paiement paiement= new Paiement();
		
		try {
			paiement = paiementDao.trouver(id);
			paiement.setNumCarte(numCarte);
			paiement.setCodeConf(codeConf);
			paiement.setBanque(banque);
			
			Client client = clientDao.trouver(idClient);
			paiement.setClient(client);
			
			paiementDao.miseAJour(paiement);
		} catch (DaoException e) {
			e.printStackTrace();
		}
		
		response.sendRedirect(request.getContextPath() + "/ListPaiements");		
	}
}

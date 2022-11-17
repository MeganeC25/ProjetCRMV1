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
		
		
		int numCarte = Integer.parseInt(request.getParameter("numCarte"));
		int codeConf = Integer.parseInt(request.getParameter("codeConf"));
		String banque = request.getParameter("banque");
		
		Paiement paiement = new Paiement();
		paiement.setNumCarte(numCarte);
		paiement.setCodeConf(codeConf);
		paiement.setBanque(banque);
		
		try {
			long idClient = Long.parseLong(request.getParameter("client"));
			paiement.setClient(clientDao.trouver(idClient));
			
			paiementDao.creer(paiement);
		} catch (DaoException e) {
			e.printStackTrace();
		}
		
		response.sendRedirect(request.getContextPath() + "/ListPaiements");
	}
}

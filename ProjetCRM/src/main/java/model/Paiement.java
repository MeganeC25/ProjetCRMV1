package model;

public class Paiement {

	private Long id;
	private String numCarte;
	private String codeConf;
	private String banque;;
	private Client client;

	
	
	/*Constructeurs*/
	public Paiement() {
	}
	
	public Paiement(Long id, String numCarte, String codeConf, String banque, Client client) {
		this.id = id;
		this.numCarte = numCarte;
		this.codeConf = codeConf;
		this.banque = banque;
		this.client = client;
	}
	
	public Paiement(String numCarte, String codeConf, String banque, Client client) {
		this.numCarte = numCarte;
		this.codeConf = codeConf;
		this.banque = banque;
		this.client = client;
	}
	
	
	
	/* Getters Setters */
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNumCarte() {
		return numCarte;
	}

	public void setNumCarte(String numCarte) {
		this.numCarte = numCarte;
	}

	public String getCodeConf() {
		return codeConf;
	}

	public void setCodeConf(String codeConf) {
		this.codeConf = codeConf;
	}

	public String getBanque() {
		return banque;
	}

	public void setBanque(String banque) {
		this.banque = banque;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}
}

package model;

public class Panier {

	private Long id;
	private Client client;
	
	
	/* Constructeurs*/
	public Panier() {
	}
	
	public Panier(Long id, Client client) {
		this.id = id;
		this.client = client;
	}
	
	public Panier(Client client) {
		this.client = client;
	}
	
	
	
	
	/*Getters Setters*/
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Client getClient() {
		return client;
	}
	public void setClient(Client client) {
		this.client = client;
	}

}

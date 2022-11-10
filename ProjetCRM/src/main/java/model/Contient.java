package model;

public class Contient {
	
	private Long id;
	private Produit produit;
	private Panier panier;
	
	
	
	/* Constructeurs*/
	public Contient() {
	}
	
	public Contient(Long id, Produit produit, Panier panier) {
		this.id = id;
		this.produit = produit;
		this.panier = panier;
	}
	


	public Contient(Produit produit, Panier panier) {
		this.produit = produit;
		this.panier = panier;
	}
	
	
	
	/*Getters Setters*/
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Produit getProduit() {
		return produit;
	}
	public void setProduit(Produit produit) {
		this.produit = produit;
	}
	public Panier getPanier() {
		return panier;
	}
	public void setPanier(Panier panier) {
		this.panier = panier;
	}
	
	
	@Override
	public String toString() {
		return "id: " + id + "; produit: " + produit.toString() + "; panier: " + panier.toString();
	}
	
	
}

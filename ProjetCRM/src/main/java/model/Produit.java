package model;

public class Produit {
	
	private Long id;
	private String nom;
	private String description;
	private float prix;
	
	
	/* Constructeurs*/
	public Produit() {
	}

	public Produit(Long id, String nom, String description, float prix) {
		this.id = id;
		this.nom = nom;
		this.description = description;
		this.prix = prix;
	}
	
	public Produit(String nom, String description, float prix) {
		this.nom = nom;
		this.description = description;
		this.prix = prix;
	}
		
	
	
	/*Getters Setters*/
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public float getPrix() {
		return prix;
	}
	public void setPrix(float prix) {
		this.prix = prix;
	}
	@Override
	public String toString() {
		return "id :" + id + "; nom: " + nom + "; description: " + description + "; prix: " + prix;
	}
}

package parserxml;

import android.location.Location;

public class ElementXML {
	
	private String nom;
	private String description;
	private String image;
	private Coordonnees coordonnees;
	private Location location;
	
	public ElementXML(String nom, String description, String image,
			Coordonnees coordonnees, Location location) {
		super();
		this.nom = nom;
		this.description = description;
		this.image = image;
		this.coordonnees = coordonnees;
		this.location = location;
	}

	public ElementXML() {
		super();
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

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public Coordonnees getCoordonnees() {
		return coordonnees;
	}

	public void setCoordonnees(Coordonnees coordonnees) {
		this.coordonnees = coordonnees;
	}
	
	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	@Override
	public String toString() {
		return "ElementXML [nom=" + nom + ", description=" + description
				+ ", image=" + image + ", coordonnees=" + coordonnees
				+ ", location=" + location + "]";
	}

}

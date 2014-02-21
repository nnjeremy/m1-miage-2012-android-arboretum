package parserxml;

import android.location.Location;

public class ElementArbreXML extends ElementXML {
	
	private String famille;

	public ElementArbreXML(String nom, String description, String image,
			Coordonnees coordonnees, Location location, String famille) {
		super(nom, description, image, coordonnees, location);
		this.famille = famille;
	}

	public ElementArbreXML() {
		super();
	}

	public String getFamille() {
		return famille;
	}

	public void setFamille(String famille) {
		this.famille = famille;
	}

	@Override
	public String toString() {
		return "ElementArbreXML [famille=" + famille + super.toString() + ", getClass()="
				+ getClass() + ", hashCode()=" + hashCode() + "]";
	}
	
}

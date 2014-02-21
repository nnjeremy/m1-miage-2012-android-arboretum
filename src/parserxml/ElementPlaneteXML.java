package parserxml;

import android.location.Location;

public class ElementPlaneteXML extends ElementXML {
	
	private String demiGrandAxe;
	private String rayonEquatorial;
	private String masse;
	private String masseVolumique;
	private String GraviteDeSurface;
	
	public ElementPlaneteXML(String nom, String description, String image,
			Coordonnees coordonnees, Location location, String demiGrandAxe,
			String rayonEquatorial, String masse, String masseVolumique,
			String graviteDeSurface) {
		super(nom, description, image, coordonnees, location);
		this.demiGrandAxe = demiGrandAxe;
		this.rayonEquatorial = rayonEquatorial;
		this.masse = masse;
		this.masseVolumique = masseVolumique;
		GraviteDeSurface = graviteDeSurface;
	}

	public ElementPlaneteXML() {
		super();
	}

	public String getDemiGrandAxe() {
		return demiGrandAxe;
	}

	public void setDemiGrandAxe(String demiGrandAxe) {
		this.demiGrandAxe = demiGrandAxe;
	}

	public String getRayonEquatorial() {
		return rayonEquatorial;
	}

	public void setRayonEquatorial(String rayonEquatorial) {
		this.rayonEquatorial = rayonEquatorial;
	}

	public String getMasse() {
		return masse;
	}

	public void setMasse(String masse) {
		this.masse = masse;
	}

	public String getMasseVolumique() {
		return masseVolumique;
	}

	public void setMasseVolumique(String masseVolumique) {
		this.masseVolumique = masseVolumique;
	}

	public String getGraviteDeSurface() {
		return GraviteDeSurface;
	}

	public void setGraviteDeSurface(String graviteDeSurface) {
		GraviteDeSurface = graviteDeSurface;
	}

	@Override
	public String toString() {
		return "ElementPlaneteXML [demiGrandAxe=" + demiGrandAxe
				+ ", rayonEquatorial=" + rayonEquatorial + ", masse=" + masse
				+ ", masseVolumique=" + masseVolumique + ", GraviteDeSurface="
				+ GraviteDeSurface + super.toString() + ", getClass()="
				+ getClass() + ", hashCode()=" + hashCode() + "]";
	}

}

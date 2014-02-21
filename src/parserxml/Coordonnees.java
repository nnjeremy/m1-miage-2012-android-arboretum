package parserxml;

public class Coordonnees {

	private String longitude;
	private String latitude;
	private String heading;
	private String bearing;
	private String orientation;
	
	public Coordonnees(String longitude, String latitude, String heading,
			String bearing, String orientation) {
		super();
		this.longitude = longitude;
		this.latitude = latitude;
		this.heading = heading;
		this.bearing = bearing;
		this.orientation = orientation;
	}
	public Coordonnees() {
		super();
	}
	
	public String getLongitude() {
		return longitude;
	}
	
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	
	public String getLatitude() {
		return latitude;
	}
	
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	
	public String getHeading() {
		return heading;
	}
	
	public void setHeading(String heading) {
		this.heading = heading;
	}
	
	public String getBearing() {
		return bearing;
	}
	
	public void setBearing(String bearing) {
		this.bearing = bearing;
	}
	
	public String getOrientation() {
		return orientation;
	}
	
	public void setOrientation(String orientation) {
		this.orientation = orientation;
	}
	
	@Override
	public String toString() {
		return "Coordonnees [longitude=" + longitude + ", latitude=" + latitude
				+ ", heading=" + heading + ", bearing=" + bearing
				+ ", orientation=" + orientation + "]";
	}
	
}

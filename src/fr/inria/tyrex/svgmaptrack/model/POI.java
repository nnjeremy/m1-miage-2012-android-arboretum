package fr.inria.tyrex.svgmaptrack.model;

import android.os.Parcel;
import android.os.Parcelable;

public class POI implements Parcelable {

	protected double lat;
	protected double lon;
	protected int id;

	public POI(double lat, double lon, int id) {
		this.lat = lat;
		this.lon = lon;
		this.id = id;
	}
	

	public POI(Parcel source) {
		this.lat = source.readDouble();
		this.lon = source.readDouble();;
		this.id = source.readInt();
	}

	public double getLat() {
		return lat;
	}
	public void setLat(double lat) {
		this.lat = lat;
	}
	public double getLon() {
		return lon;
	}
	public void setLon(double lon) {
		this.lon = lon;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	public int describeContents() {
		return 0;
	}
	
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeDouble(lat);
		dest.writeDouble(lon);
		dest.writeInt(id);
	}


	public static final Parcelable.Creator<POI> CREATOR = new Parcelable.Creator<POI>() {

		public POI createFromParcel(Parcel source) {
			return new POI(source);
		}

		public POI[] newArray(int size) {
			return new POI[size];
		}

	};

	@Override
	public boolean equals(Object o) {
		if(!(o instanceof POI)) {
			return false;
		}
		return ((POI) o).id == this.id;
	}

}

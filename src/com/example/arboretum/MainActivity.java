package com.example.arboretum;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Locale;

import parserxml.ContainerData;
import parserxml.ElementArbreXML;
import parserxml.ElementPlaneteXML;
import parserxml.ElementXML;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.speech.tts.TextToSpeech.OnInitListener;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import fr.inria.tyrex.svgmaptrack.model.POI;

public class MainActivity extends Activity implements LocationListener,
		OnInitListener {

	public final static String EXTRA_LOC = "com.example.arboretum.newlocation";

	private LocationManager locationManager;
	private ArrayList<ElementArbreXML> elementsArbreXML;
	private ArrayList<ElementPlaneteXML> elementsplanete;
	private TextToSpeech tts;
	private BroadcastReceiver myReceiver;

	private ProgressDialog prodial;

	// private LocationManager locationManager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		tts = new TextToSpeech(this, this);

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		//On crée l'intent filter sur le click d'un poi
		IntentFilter intentFilter = new IntentFilter("fr.inria.tyrex.svgmaptrack.broadcast.clickcoordinates");
		//on crée le broadcast receiver pour afficher le poi lors d'un click
        myReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
            	double lat = intent.getDoubleExtra("lat", 0);
        		double lon = intent.getDoubleExtra("lon", 0);
        		Location newloc = new Location("BLank");
        		newloc.setLatitude(lat);
        		newloc.setLongitude(lon);
        		ElementXML poi = getNearestPOI(newloc);
        		afficherPOI(poi);
                
            }
        };
        //On enregistre le listener
        this.registerReceiver(myReceiver, intentFilter);
		try {
			elementsArbreXML = ContainerData.getElementsArbreXML(this.getAssets().open("Arboretum.xml"));
			elementsplanete = ContainerData.getElementsPlaneteXML(this.getAssets().open("Arboretum.xml"));

		} catch (IOException e) {
			Toast.makeText(this, "Ressource XML introuvable",Toast.LENGTH_SHORT).show();
		}
		prodial = new ProgressDialog(this);
		prodial.setMessage("En attente de localisation GPS");
		prodial.setCancelable(false);
		getLocalisation();

	}

	public void openMap(View v) {
		affichercarte();

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	public void getLocalisation() {
		// On récupère le location Manager
		locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
		// On vérifie si le GPS est activé
		if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
			// On demande une mise à jour des coordonnées
			locationManager.requestLocationUpdates(
					LocationManager.GPS_PROVIDER, 5000, (float) 1.0, this);
			// On affiche le progress dialog
			// prodial.show();
		} else {
			Toast.makeText(this, "Impossible de recuperer les coordonnees",
					Toast.LENGTH_SHORT).show();
		}
	}

	public void affichercarte() {

		// Geo uri see http://en.wikipedia.org/wiki/Geo_URI
		String uri = "geo:45.193885,5.778331?z=25";

		Intent intent = new Intent();
		intent.setComponent(new ComponentName("fr.inria.tyrex.svgmaptrack",
				"fr.inria.tyrex.svgmaptrack.SvgMapActivity"));

		// Set GeoUri as data
		intent.setData(Uri.parse(uri));
		int i = 0;
		POI[] poiList = new POI[26];
		for (ElementXML element : elementsArbreXML) {
			POI poi = new POI(element.getLocation().getLatitude(), element
					.getLocation().getLongitude(), i);
			poiList[i] = poi;
			i++;
		}
		for (ElementPlaneteXML element : elementsplanete) {
			POI poi = new POI(element.getLocation().getLatitude(), element
					.getLocation().getLongitude(), i);
			poiList[i] = poi;
			i++;
		}

		intent.putExtra("poi", poiList);

		startActivity(intent);
	}

	public void onLocationChanged(Location location) {
		// Nouvelle position, on enleve le progressdialog
		// prodial.dismiss();
		// On affiche qu'une nouvelle position a été trouvée
		// Toast.makeText(getApplicationContext(),
		// "new location find",Toast.LENGTH_SHORT).show();
		EditText textlat = (EditText) findViewById(R.id.TextLattitude);
		textlat.setText(String.valueOf(location.getLatitude()));
		EditText textlong = (EditText) findViewById(R.id.TextLongitude);
		textlong.setText(String.valueOf(location.getLongitude()));

		// a chaque changement de position on cherche le POI le plus proche
		ElementXML poi = getNearestPOI(location);
		// si le POI est a moins de 2 mètres de notre position on affiche les
		// infos sinon on indique la direction de celui-ci
		if (location.distanceTo(poi.getLocation()) < (float) 5.0)
			afficherPOI(poi);
		else
			IndiquerDirectionPoi(poi);

	}

	// renvoi le POI le plus proche de la position actuelle
	// Min posActuelle.distanceTo(POI)
	private ElementXML getNearestPOI(Location posActuelle) {

		ElementXML nearest = elementsArbreXML.get(0);
		for (ElementXML element : elementsArbreXML) {
			if (posActuelle.distanceTo(element.getLocation()) < posActuelle
					.distanceTo(nearest.getLocation())) {
				nearest = element;
			}
		}
		for (ElementXML element : elementsplanete) {
			if (posActuelle.distanceTo(element.getLocation()) < posActuelle
					.distanceTo(nearest.getLocation())) {
				nearest = element;
			}
		}
		return nearest;
	}

	// Montrer la direction vers le poi passŽ en parametre
	private void IndiquerDirectionPoi(ElementXML poi) {
		// TODO Auto-generated method stub

	}

	// Afficher les infos du poi passŽ en parametre
	private void afficherPOI(ElementXML poi) {
		// On ouvre un intent vers la deuxieme activity
		Intent i = new Intent(this, Details.class);
		String type;
		// ElementArbreXML elem;
		// On passe les informations necessaires vers l'activity permettant
		// l'affichage
		if (poi instanceof ElementArbreXML) {
			type = "arbre";
			ElementArbreXML elem = (ElementArbreXML) poi;
			String famille = elem.getFamille();
			i.putExtra("poi_famille", famille);
		} else {
			type = "planete";
			ElementPlaneteXML elem = (ElementPlaneteXML) poi;
			String DemiGrandAxe = elem.getDemiGrandAxe();
			i.putExtra("poi_axe", DemiGrandAxe);
			String RayonEquatorial = elem.getRayonEquatorial();
			i.putExtra("poi_rayon", RayonEquatorial);
			String Masse = elem.getMasse();
			i.putExtra("poi_masse", Masse);
			String MasseVolumique = elem.getMasseVolumique();
			i.putExtra("poi_masseVol", MasseVolumique);
			String GraviteDeSurface = elem.getGraviteDeSurface();
			i.putExtra("poi_gravite", GraviteDeSurface);
		}
		i.putExtra("poi_nom", poi.getNom());
		tts.speak(poi.getNom(), TextToSpeech.QUEUE_FLUSH, null);
		i.putExtra("poi_description", poi.getDescription());
		i.putExtra("poi_image", poi.getImage());
		i.putExtra("poi_type", type);
		// On demarre l'activity permettant l'affichage
		this.startActivity(i);
	}

	public void onStatusChanged(String provider, int status, Bundle extras) {
	}

	public void onProviderEnabled(String provider) {
	}

	public void onProviderDisabled(String provider) {
	}

	@Override
	public void onInit(int arg0) {

		int result = tts.setLanguage(Locale.CANADA_FRENCH);

		if (result == TextToSpeech.LANG_MISSING_DATA
				|| result == TextToSpeech.LANG_NOT_SUPPORTED) {
			Log.e("TTS", "This Language is not supported");
		}
		tts.speak("Bienvenue dans l'arboretum, pour afficher la carte, veuillez toucher l'image ", TextToSpeech.QUEUE_FLUSH, null);
	}

}

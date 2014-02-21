package com.example.arboretum;

import java.io.InputStream;

import android.os.Bundle;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class Details extends Activity {

	private String poi_nom;
	private String poi_description;
	private String poi_type;
	private Bitmap poi_image;
	private TextView nom;
	private EditText description;
	private ImageView image;
	private EditText family;
	private Button buttonRetour;
	private String poi_famille;
	private String poi_axe;
	private String poi_rayon;
	private String poi_masse;
	private String poi_masseVol;
	private String poi_gravite;
	private EditText axe;
	private EditText rayon;
	private EditText masseVol;
	private EditText gravite;
	private EditText masse;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_affichage);
		this.initPOI();
		this.setView();
	}

	/**
	 * Retour vers la 1�re activitŽ
	 * 
	 * @param v
	 */
	public void retourActivity(View v) {
		finish();
	}

	/**
	 * Initialisation des param�tres du POI envoyŽs par la premi�re activitŽ
	 * Initialisation des composants graphiques de la vue
	 */
	private void initPOI() {
		Bundle extras = this.getIntent().getExtras();
		if (extras == null)
			return;
		// On rŽcup�re les donnŽes de la premi�re activitŽ
		this.poi_nom = extras.getString("poi_nom");
		this.poi_description = extras.getString("poi_description");
		// Un traitement un peu particulier pour l'image
		String nomImage = extras.getString("poi_image");
		this.poi_image = this.getImageBitmap(nomImage);
		this.nom = (TextView) findViewById(R.id.nomPOI);
		this.description = (EditText) findViewById(R.id.descriptionPOI);
		this.image = (ImageView) findViewById(R.id.imagePOI);
		this.buttonRetour = (Button) findViewById(R.id.buttonRetour);
		this.poi_type = extras.getString("poi_type");
		if (this.poi_type.equals("arbre")) {
			this.poi_famille = extras.getString("poi_famille");

		}
		if (this.poi_type.equals("planete")) {
			this.poi_axe = extras.getString("poi_axe");
			this.poi_rayon = extras.getString("poi_rayon");
			this.poi_masse = extras.getString("poi_masse");
			this.poi_masseVol = extras.getString("poi_masseVol");
			this.poi_gravite = extras.getString("poi_gravite");

		}
		// On initialise les composants graphiques
		this.family = (EditText) findViewById(R.id.famillePOI);

		this.axe = (EditText) findViewById(R.id.axePOI);
		this.rayon = (EditText) findViewById(R.id.rayonPOI);
		this.masse = (EditText) findViewById(R.id.massePOI);
		this.masseVol = (EditText) findViewById(R.id.masseVolPOI);
		this.gravite = (EditText) findViewById(R.id.gravitePOI);
	}

	/**
	 * Mise ˆ jour de la vue de la deuxi�me activitŽ
	 */
	private void setView() {
		// Mise ˆ jour du nom du POI
		this.nom.setText(this.poi_nom);
		// Mise ˆ jour de la description
		this.description.setText(this.description.getText()
				+ this.poi_description);
		// Mise ˆ jour de l'image
		this.image.setImageBitmap(this.poi_image);
		if (this.poi_type.equals("arbre")) {
			this.family.setText(this.poi_famille);
			this.axe.setVisibility(View.GONE);
			this.rayon.setVisibility(View.GONE);
			this.masse.setVisibility(View.GONE);
			this.masseVol.setVisibility(View.GONE);
			this.gravite.setVisibility(View.GONE);
			findViewById(R.id.labelAxe).setVisibility(View.GONE);
			findViewById(R.id.labelRayon).setVisibility(View.GONE);
			findViewById(R.id.labelMasse).setVisibility(View.GONE);
			findViewById(R.id.labelMasseVol).setVisibility(View.GONE);
			findViewById(R.id.labelGravite).setVisibility(View.GONE);
		}
		if (this.poi_type.equals("planete")) {
			this.family.setVisibility(View.GONE);
			this.axe.setText(this.poi_axe);
			this.rayon.setText(this.poi_rayon);
			this.masse.setText(this.poi_masse);
			this.masseVol.setText(this.poi_masseVol);
			this.gravite.setText(this.poi_gravite);
			findViewById(R.id.labelFamille).setVisibility(View.GONE);
		}
	}

	/**
	 * RŽcupŽration de l'image au format Bitmap
	 */
	private Bitmap getImageBitmap(String nomImage) {
		Bitmap image = null;
		try {
			// On rŽcup�re le flux en entrŽe
			InputStream path = this.getAssets().open("Photos/" + nomImage);
			// On rŽcup�re l'image
			image = BitmapFactory.decodeStream(path);
			// On ferme nos flux
			path.close();
			return image;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_affichage, menu);
		return true;
	}

}

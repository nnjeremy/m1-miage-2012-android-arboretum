package parserxml;

import java.util.ArrayList;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import android.location.Location;
import android.util.Log;

public class ParserXMLHandler extends DefaultHandler {
	
	private final String ARBRE = "arbre";
	private final String NOM = "nom";
	private final String DESCRIPTION = "description";
	private final String FAMILLE = "famille";
	private final String IMAGE = "image";
	
	private final String PLANETE = "planete";
	private final String DGA = "demiGrandAxe";
	private final String RE = "rayonEquatorial";
	private final String MASSE = "masse";
	private final String MV = "masseVolumique";
	private final String GDS = "GraviteDeSurface";
	
	private final String LONGITUDE = "longitude";
	private final String LATITUDE = "latitude";
	private final String DLONGITUDE = "decimalLongitude";
	private final String DLATITUDE = "decimalLatitude";
	private final String HEADING = "heading";
	private final String BEARING = "bearing";
	private final String ORIENTATION = "orientation";
	
	private ArrayList<ElementArbreXML> elementsArbreXML;
	private ArrayList<ElementPlaneteXML> elementsPlaneteXML;
	
	private boolean inItem;
	
	private ElementXML currentElementXML;
	
	private StringBuffer buffer;
	
	@Override
	public void processingInstruction(String target, String data) throws SAXException {		
		super.processingInstruction(target, data);
	}

	public ParserXMLHandler() {
		super();		
	}
	
	@Override
	public void startDocument() throws SAXException {
		super.startDocument();
		elementsArbreXML = new ArrayList<ElementArbreXML>();
		elementsPlaneteXML = new ArrayList<ElementPlaneteXML>();		
	}

	@Override
	public void startElement(String uri, String localName, String name,	Attributes attributes) throws SAXException {
		buffer = new StringBuffer();		
		
		if (localName.equalsIgnoreCase(ARBRE)){			
			this.currentElementXML = new ElementArbreXML();
			this.currentElementXML.setCoordonnees(new Coordonnees());
			this.currentElementXML.setLocation(new Location("BLANK"));
			inItem = true;
		}
		if (localName.equalsIgnoreCase(PLANETE)){			
			this.currentElementXML = new ElementPlaneteXML();
			this.currentElementXML.setCoordonnees(new Coordonnees());
			this.currentElementXML.setLocation(new Location("BLANK"));
			inItem = true;
		}
		
		if (localName.equalsIgnoreCase(NOM)){
			// Rien a faire (pour le moment	
		}
	}
	
	@Override
	public void endElement(String uri, String localName, String name) throws SAXException {		
		
		if (localName.equalsIgnoreCase(NOM)){
			if(inItem){				
				this.currentElementXML.setNom(buffer.toString());
				this.currentElementXML.getLocation().setProvider(buffer.toString());
				buffer = null;
			}
		}
		if (localName.equalsIgnoreCase(DESCRIPTION)){
			if(inItem){				
				this.currentElementXML.setDescription(buffer.toString());				
				buffer = null;
			}
		}
		if (localName.equalsIgnoreCase(FAMILLE)){
			if(inItem){				
				((ElementArbreXML)this.currentElementXML).setFamille(buffer.toString());				
				buffer = null;
			}
		}
		if (localName.equalsIgnoreCase(DGA)){
			if(inItem){				
				((ElementPlaneteXML)this.currentElementXML).setDemiGrandAxe(buffer.toString());				
				buffer = null;
			}
		}
		if (localName.equalsIgnoreCase(RE)){
			if(inItem){				
				((ElementPlaneteXML)this.currentElementXML).setRayonEquatorial(buffer.toString());				
				buffer = null;
			}
		}
		if (localName.equalsIgnoreCase(MASSE)){
			if(inItem){				
				((ElementPlaneteXML)this.currentElementXML).setMasse(buffer.toString());				
				buffer = null;
			}
		}
		if (localName.equalsIgnoreCase(MV)){
			if(inItem){				
				((ElementPlaneteXML)this.currentElementXML).setMasseVolumique(buffer.toString());				
				buffer = null;
			}
		}
		if (localName.equalsIgnoreCase(GDS)){
			if(inItem){				
				((ElementPlaneteXML)this.currentElementXML).setGraviteDeSurface(buffer.toString());				
				buffer = null;
			}
		}
		if (localName.equalsIgnoreCase(IMAGE)){
			if(inItem){				
				this.currentElementXML.setImage(buffer.toString());				
				buffer = null;
			}
		}
		if (localName.equalsIgnoreCase(LONGITUDE)){
			if(inItem){				
				this.currentElementXML.getCoordonnees().setLongitude(buffer.toString());				
				buffer = null;
			}
		}
		if (localName.equalsIgnoreCase(LATITUDE)){
			if(inItem){				
				this.currentElementXML.getCoordonnees().setLatitude(buffer.toString());				
				buffer = null;
			}
		}
		if (localName.equalsIgnoreCase(DLONGITUDE)){
			if(inItem){				
				this.currentElementXML.getLocation().setLongitude(Double.valueOf(buffer.toString()));				
				buffer = null;
			}
		}
		if (localName.equalsIgnoreCase(DLATITUDE)){
			if(inItem){				
				this.currentElementXML.getLocation().setLatitude(Double.valueOf(buffer.toString()));				
				buffer = null;
			}
		}
		if (localName.equalsIgnoreCase(HEADING)){
			if(inItem){				
				this.currentElementXML.getCoordonnees().setHeading(buffer.toString());				
				buffer = null;
			}
		}
		if (localName.equalsIgnoreCase(BEARING)){
			if(inItem){				
				this.currentElementXML.getCoordonnees().setBearing(buffer.toString());	
				this.currentElementXML.getLocation().setBearing(Float.valueOf(buffer.toString()));
				buffer = null;
			}
		}
		if (localName.equalsIgnoreCase(ORIENTATION)){
			if(inItem){				
				this.currentElementXML.getCoordonnees().setOrientation(buffer.toString());				
				buffer = null;
			}
		}
		if (localName.equalsIgnoreCase(ARBRE)){		
			elementsArbreXML.add((ElementArbreXML)this.currentElementXML);
			inItem = false;
		}
		if (localName.equalsIgnoreCase(PLANETE)){		
			elementsPlaneteXML.add((ElementPlaneteXML)this.currentElementXML);
			inItem = false;
		}
	}

	public void characters(char[] ch,int start, int length)	throws SAXException{		
		String lecture = new String(ch,start,length);
		if(buffer != null) buffer.append(lecture);      		
	}
		
	public ArrayList<ElementArbreXML> getDataArbre(){
		Log.d("DebugMSG","taille elemensArbreXML : " + elementsArbreXML.size());
		return this.elementsArbreXML;
	}
	
	public ArrayList<ElementPlaneteXML> getDataPlanete(){
		Log.d("DebugMSG","taille elemensPlaneteXML : " + elementsPlaneteXML.size());
		return this.elementsPlaneteXML;
	}

}

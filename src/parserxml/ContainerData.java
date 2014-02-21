package parserxml;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import android.content.Context;
import android.util.Log;

public class ContainerData {
	
	static public Context context;
	
	public ContainerData() {

	}
	
	public static ArrayList<ElementArbreXML> getElementsArbreXML(InputStream file){
		SAXParserFactory fabrique = SAXParserFactory.newInstance();
		SAXParser parseur = null;
		ArrayList<ElementArbreXML> ElementsArbreXML = null;
		try {
			parseur = fabrique.newSAXParser();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		}
		
		URL url = null;
		try {
			url = new URL("http://164.138.28.113:8080/POO/Arboretum.xml");
		} catch (MalformedURLException e1) {
			e1.printStackTrace();
		}
		
		DefaultHandler handler = new ParserXMLHandler();
		try {
			parseur.parse(new BufferedInputStream(file), handler);
			
			ElementsArbreXML = ((ParserXMLHandler) handler).getDataArbre();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return ElementsArbreXML;
	}
	
	public static ArrayList<ElementPlaneteXML> getElementsPlaneteXML(InputStream file){
		SAXParserFactory fabrique = SAXParserFactory.newInstance();
		SAXParser parseur = null;
		ArrayList<ElementPlaneteXML> ElementsPlaneteXML = null;
		try {
			parseur = fabrique.newSAXParser();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		}
		
		URL url = null;
		try {
			url = new URL("http://164.138.28.113:8080/POO/Arboretum.xml");
		} catch (MalformedURLException e1) {
			e1.printStackTrace();
		}
		
		DefaultHandler handler = new ParserXMLHandler();
		try {
			parseur.parse(new BufferedInputStream(file), handler);
			
			ElementsPlaneteXML = ((ParserXMLHandler) handler).getDataPlanete();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return ElementsPlaneteXML;
	}

}

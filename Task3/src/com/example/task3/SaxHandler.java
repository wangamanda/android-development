package com.example.task3;

import java.util.ArrayList;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class SaxHandler extends DefaultHandler{
	private String CD = "CD";
	private String TITLE = "TITLE";
	private String ARTIST = "ARTIST";
	private String COUNTRY = "COUNTRY";
	private String COMPANY = "COMPANY";
	private String PRICE = "PRICE";
	private String YEAR = "YEAR";
	
	private ArrayList<XMLStructure> arr = new ArrayList<XMLStructure>();
	XMLStructure xmlstructure;
	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException{
		super.startElement(uri, localName, qName, attributes);
		if(localName.equals(CD)){
			xmlstructure = new XMLStructure();
		}
	}
	
	String str = null;
	@Override
	public void characters(char[] ch, int start, int length) throws SAXException{
		super.characters(ch, start, length);
		str = new String(ch, start, length);
	}
	
	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException{
		super.endElement(uri, localName, qName);
		if(localName.equals(TITLE)){
			xmlstructure.setTitle(str);
		}else if(localName.equals(ARTIST)){
			xmlstructure.setArtiest(str);
		}
		arr.add(xmlstructure);
	}
	
	public ArrayList<XMLStructure> getData(){
		return arr;
	}
}
package com.example.thirdtask;

import java.util.ArrayList;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class SaxHandler extends DefaultHandler{

	private String TITLE="TITLE";
	private String ARTIST="ARTIST";
	private String COUNTRY="COUNTRY";
	private String COMPANY="COMPANY";
	private String PRICE="PRICE";
	private String YEAR="YEAR";
	
	private String CD = "CD";
	
	private ArrayList<XMLStructure> mList = new ArrayList<XMLStructure>();
	
	public ArrayList<XMLStructure> getParsedData() {
		return mList;
	}
	
	XMLStructure lXmlStructure;
	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {
		super.startElement(uri, localName, qName, attributes);
		
		if(localName.equals("CATALOG")){
			//
		}
		
		if(localName.equals(CD)){
			lXmlStructure = new XMLStructure();
		}
		
	}
	
	String lTemp = null;
	@Override
	public void characters(char[] ch, int start, int length)
			throws SAXException {
		
		lTemp = new String(ch,start,length);

	}
	
	@Override
	public void endElement(String uri, String localName, String qName)
			throws SAXException {
		super.endElement(uri, localName, qName);
		if(localName.equals(TITLE)){
			lXmlStructure.setTITLE(lTemp);
		}else if(localName.equals(ARTIST)){
			lXmlStructure.setARTIST(lTemp);
		}
		
		
		if(localName.equals(CD)){
			mList.add(lXmlStructure);
		}
		
		if(localName.equals("CATALOG")){
			int lSize = mList.size();
		}
	}
}

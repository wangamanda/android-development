package com.example.thirdtask;

import java.io.InputStream;
import java.util.ArrayList;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;

public class CustomSaxParser {

	public static ArrayList<XMLStructure> parseXML(InputStream pInputStream) {
		try{
			SAXParserFactory lFactory = SAXParserFactory.newInstance();
			SAXParser lParser = lFactory.newSAXParser();
			XMLReader  lXMLReader  = lParser.getXMLReader();
			
			InputSource lInputSource = new InputSource(pInputStream);
			
			SaxHandler lHandler = new SaxHandler();
			lXMLReader.setContentHandler(lHandler);
			
			lXMLReader.parse(lInputSource);
			
			ArrayList<XMLStructure> xmlList = lHandler.getParsedData();
			return xmlList;
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return null;
		
	}
}

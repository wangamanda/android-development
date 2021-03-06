package com.example.task3;

import java.io.IOException;
import java.io.InputStream;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

public class SaxParser{
	public static void parseXML(InputStream lInputStream) throws IOException{
		SAXParserFactory lSAXParserFactory = SAXParserFactory.newInstance();
		try {
			SAXParser lSAXParser = lSAXParserFactory.newSAXParser();
			XMLReader lXMLReader = lSAXParser.getXMLReader();
			
			InputSource lInputSource = new InputSource(lInputStream);
			SaxHandler lSAXHandler = new SaxHandler();
			lXMLReader.setContentHandler(lSAXHandler);
			lXMLReader.parse(lInputSource);
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
package com.example.task3;

public class XMLStructure{
	private String TITLE;
	private String ARTIST;
	private String COUNTRY;
	private String COMPANY;
	private String PRICE;
	private String YEAR;
	
	public String getTitle(){
		return TITLE;
	}
	
	public String getArtist(){
		return ARTIST;
	}
	
	public String getCountry(){
		return COUNTRY;
	}
	
	public String getCompany(){
		return COMPANY;
	}
	
	public String getPrice(){
		return PRICE;
	}
	
	public String getYear(){
		return YEAR;
	}
	
	public void setTitle(String t){
		TITLE = t;
	}
	
	public void setArtiest(String a){
		ARTIST = a;
	}
	
	public void setCountry(String c){
		COUNTRY = c;
	}
	
	public void setCompany(String c){
		COMPANY = c;
	}
	
	public void setPrice(String p){
		PRICE = p;
	}
	
	public void setYear(String y){
		YEAR = y;
	}
}
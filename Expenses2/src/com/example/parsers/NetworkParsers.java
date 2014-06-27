package com.example.parsers;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class NetworkParsers{

	public static JSONObject ParseLoginResponse(String lResponse){
		JSONArray arr = null;
		JSONObject obj = null;
		
		try {
			arr = new JSONArray(lResponse.toString());
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			obj = (JSONObject) arr.get(0);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return obj;
	}
}

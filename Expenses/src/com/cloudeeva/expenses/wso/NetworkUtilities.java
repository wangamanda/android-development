package com.cloudeeva.expenses.wso;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONObject;

import android.content.Context;

import com.cloudeeva.expenses.constants.AppConstants;

public class NetworkUtilities {

	public static void verifyTheLoginCredentials(Context pContext, String pUsername, String pPassword) {
		HttpClient lBrowser = new DefaultHttpClient();
		
		try{
//			HttpGet lGetRequest = new HttpGet();
//			lGetRequest.setURI(new URI(AppConstants.getLoginURL(pUsername, pUsername)));
//			
//			HttpResponse  lResponse = lBrowser.execute(lGetRequest);
//			InputStream lInputStream = lResponse.getEntity().getContent();
			
			URL url = new URL(AppConstants.getLoginURL(pUsername, pPassword));
			URLConnection urlConnection = url.openConnection();
			InputStream lInputStream = urlConnection.getInputStream();
			
			BufferedReader reader = new BufferedReader(new InputStreamReader(lInputStream, "iso-8859-1"), 8);
	            StringBuilder sb = new StringBuilder();
	            String line = null;
	            while ((line = reader.readLine()) != null) {
	                sb.append(line + "\n");
	            }
	         String response = sb.toString();
			
//			InputStreamReader lISReader = new InputStreamReader(lInputStream);
//			BufferedReader lBufferedReader = new BufferedReader(lISReader,8);
//			String lLine = null;
//			StringBuilder lBuilder = new StringBuilder();
//			while ((lLine = lBufferedReader.readLine()) != null) {
//				lBuilder.append(lLine+"\n");
//			}
//			
//			String lData = lBuilder.toString();
			
			//parsing the json
			//JSONObject lJsonObject = new JSONObject(lData);
			JSONArray lJsonArray = new JSONArray(response);
			JSONObject lValues = (JSONObject) lJsonArray.get(0);
			int lUserId = lValues.getInt("UserID");
			String lUserName = lValues.getString("Username");
			
		}catch(Exception e){
			//an error occured
			e.printStackTrace();
		}
		
		
	}
}

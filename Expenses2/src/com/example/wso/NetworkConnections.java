package com.example.wso;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import org.apache.http.client.ClientProtocolException;
import org.json.JSONObject;

import com.example.parsers.NetworkParsers;

public class NetworkConnections {
	public static JSONObject LoginNetworkRequest(String username, String password) {
		String lURL = "http://146.145.105.252/ServiceHRMS/HrmsService.svc/web/LoginUser/"+username+"/"+password;
		StringBuilder res = new StringBuilder();
		try {
			URL url = new URL(lURL);
			URLConnection urlConnection = url.openConnection();
			InputStream lInputStream = urlConnection.getInputStream();
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					lInputStream, "iso-8859-1"), 8);
			String line = null;
			while ((line = reader.readLine()) != null) {
				res.append(line + "\n");
			}
			String lResponse = res.toString();
			return NetworkParsers.ParseLoginResponse(lResponse);
			
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		return null;
	}
}

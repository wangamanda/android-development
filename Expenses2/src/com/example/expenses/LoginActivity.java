package com.example.expenses;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class LoginActivity extends Activity{

	private EditText tUsername;
	private EditText tPassword;
	private String sUsername;
	private String sPassword;
	private Button myButton;
	
	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		tUsername = (EditText)findViewById(R.id.edit_name);
		tPassword = (EditText)findViewById(R.id.edit_password);
		
		sUsername = tUsername.getText().toString();
		sPassword = tPassword.getText().toString();

		myButton = (Button)findViewById(R.id.my_button);
		myButton.setOnClickListener(new ButtonListener());
		
	}
	
	class ButtonListener implements OnClickListener{

		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			
			if(TextUtils.isEmpty(sUsername)){
				tUsername.setError("Please enter your username.");
			}
			
			if(TextUtils.isEmpty(sPassword)){
				tPassword.setError("Please enter your password.");
			}
			
			new WebTask().execute(new String[]{sUsername, sPassword});
		}
		
	}
	
	class WebTask extends AsyncTask<String, Void, Void>{

		private int UserID = 0;
		private String UserName = null;
		@Override
		protected Void doInBackground(String... arg0) {
			// TODO Auto-generated method stub
			JSONObject JSONObj= parseJson(arg0[0], arg0[1]);
			try {
				UserID = JSONObj.getInt("UserID");
				UserName = JSONObj.getString("UserName");
				
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return null;
		}
	}
	
	public JSONObject parseJson(String sUsername, String sPassword){
		StringBuilder sb = new StringBuilder();
		sb.append("http://146.145.105.252/ServiceHRMS/HrmsService.svc/web/LoginUser/");
		sb.append(sUsername);
		sb.append("/");
		sb.append(sPassword);
		String URL = sb.toString();
		StringBuilder res = new StringBuilder();
		
		try {
			HttpClient lHttpClient = new DefaultHttpClient();
			HttpGet lHttpGet = new HttpGet();
			lHttpGet.setURI(new URI(URL));
			HttpResponse lHttpResponse = lHttpClient.execute(lHttpGet);
			HttpEntity lHttpEntity = lHttpResponse.getEntity();
			InputStream lInputStream = lHttpEntity.getContent();
			InputStreamReader lInputStreamReader = new InputStreamReader(lInputStream);
			BufferedReader lBufferedReader = new BufferedReader(lInputStreamReader);
			String tmp = null;
			
			while((tmp = lBufferedReader.readLine()) != null){
				res.append(tmp);
			}
			JSONObject obj = new JSONObject(res.toString());
			return obj;
					
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}

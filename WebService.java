package com.example.task3;

import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class WebService extends Activity implements OnClickListener{
	private Button mbutton;
	private String URL = "http://www.xmlfiles.com/examples/cd_catalog.xml";
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mbutton = (Button) this.findViewById(R.id.button1);
		mbutton.setOnClickListener(this);
	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		switch(arg0.getId()){
			case R.id.button1:
				WebAsyncTask lWebAsyncTask = new WebAsyncTask();
				lWebAsyncTask.execute();
				break;
		}
	}
	
	class WebAsyncTask extends AsyncTask<Void, Void, String>{

		@Override
		protected String doInBackground(Void... arg0) {
			// TODO Auto-generated method stub
			return makeWebConnection();
		}

		@Override
		protected void onPostExecute(String result){
			//ListView
		}
		
		private String makeWebConnection() {
			// TODO Auto-generated method stub
			
			try {
				HttpClient lHttpClient = new DefaultHttpClient();
				HttpGet request = new HttpGet();
				request.setURI(new URI(URL));
				HttpResponse lHttpResponse = lHttpClient.execute(request);
				
				InputStream lInputStream = lHttpResponse.getEntity().getContent();
				SaxParser.parseXML(lInputStream);
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return null;
		}

	}
	
}
package com.example.task3;

import java.io.InputStream;
import java.net.URI;
import java.util.ArrayList;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import android.R;
import android.app.Activity;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

public class WebService extends Activity implements OnClickListener {
	private Button mbutton;
	private String URL = "http://www.xmlfiles.com/examples/cd_catalog.xml";
	private ArrayAdapter<String> adapter;
	private ArrayList<String> lArrayList = new ArrayList<String>();
	private ListView lListView;
	private ArrayList<XMLStructure> arr = new ArrayList<XMLStructure>();

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mbutton = (Button) this.findViewById(R.id.button1);
		mbutton.setOnClickListener(this);
		adapter = new ArrayAdapter<String>(this, R.id.list, lArrayList);

	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		switch (arg0.getId()) {
		case R.id.button1:
			WebAsyncTask lWebAsyncTask = new WebAsyncTask();
			lWebAsyncTask.execute();
			break;
		}
	}

	class WebAsyncTask extends AsyncTask<Void, Void, Void> {

		@Override
		protected Void doInBackground(Void... arg0) {
			// TODO Auto-generated method stub
			makeWebConnection();
			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			// ListView
			
			lListView = (ListView) findViewById(R.id.list);
			lListView.setAdapter(adapter);			
			// lListView.notifyAll();
		}

		private void makeWebConnection() {
			// TODO Auto-generated method stub

			try {
				HttpClient lHttpClient = new DefaultHttpClient();
				HttpGet request = new HttpGet();
				request.setURI(new URI(URL));
				HttpResponse lHttpResponse = lHttpClient.execute(request);
				// int lResponseCode =
				// lHttpResponse.getStatusLine().getStatusCode();
				// System.out.println(lResponseCode+"");

				InputStream lInputStream = lHttpResponse.getEntity()
						.getContent();
				ArrayList<XMLStructure> arr = CustomSaxParser
						.parseXML(lInputStream);
				for(XMLStructure x : arr){
					 String s = x.getArtist()+"."+x.getTitle();
					 lArrayList.add(s);
					 Toast.makeText(getApplicationContext(), s, 0).show();
				 }

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

}
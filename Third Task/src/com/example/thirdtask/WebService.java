package com.example.thirdtask;

import java.io.InputStream;
import java.net.URI;
import java.util.ArrayList;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import android.app.ListActivity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;

public class WebService extends ListActivity implements OnClickListener {

	private String URL = "http://www.xmlfiles.com/examples/cd_catalog.xml";
	private Button mButton;
	private ArrayList<XMLStructure> xmlArray;
	private ArrayList<String> artists, titles;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		xmlArray = new ArrayList<XMLStructure>();
		artists = new ArrayList<String>();
		titles = new ArrayList<String>();
		setContentView(R.layout.activity_main);
		initUI();
	}

	private void initUI() {
		mButton = (Button) this.findViewById(R.id.simple_button);
		mButton.setOnClickListener(this);
	}

	@Override
	public void onClick(View arg0) {
		switch (arg0.getId()) {
		case R.id.simple_button:
			new WebRequestAsyncTask().execute();
			break;
		default:
			break;
		}
	}

	ProgressDialog lProgressDialog;

	class WebRequestAsyncTask extends AsyncTask<Void, Void, Void> {
		@Override
		protected void onPreExecute() {
			lProgressDialog = new ProgressDialog(WebService.this);
			lProgressDialog.show();
		}

		@Override
		protected Void doInBackground(Void... params) {
			makeWebConnection();
			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			lProgressDialog.dismiss();
			String[] values = new String[titles.size()];
			for (int i = 0; i < values.length; i++) {
				values[i] = artists.get(i) + ". " + titles.get(i);

			}
			ArrayAdapter<String> adapter = new ArrayAdapter<String>(
					getApplicationContext(),
					android.R.layout.simple_list_item_1, values);

			setListAdapter(adapter);

		}
	}

	private void makeWebConnection() {

		try {
			HttpClient lBrowser = new DefaultHttpClient();

			HttpGet lGetRequest = new HttpGet();
			lGetRequest.setURI(new URI(URL));

			HttpResponse lResponse = lBrowser.execute(lGetRequest);
			// int lResponseCode = lResponse.getStatusLine().getStatusCode();
			InputStream lInputStream = lResponse.getEntity().getContent();

			xmlArray = CustomSaxParser.parseXML(lInputStream);

			// InputStreamReader lISReader = new
			// InputStreamReader(lInputStream);
			// BufferedReader lBufferedReader = new BufferedReader(lISReader,
			// 8);
			// String lLine = null;
			// StringBuilder lBuilder = new StringBuilder();
			// while ((lLine = lBufferedReader.readLine()) != null) {
			// lBuilder.append(lLine);
			// }

			// String lData = lBuilder.toString();

			String result = "";
			for (int i = 0; i < xmlArray.size(); i++) {
				titles.add(xmlArray.get(i).getTITLE());
				artists.add(xmlArray.get(i).getARTIST());
				result += xmlArray.get(i).getARTIST() + "\n";
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}

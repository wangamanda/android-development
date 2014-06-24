package com.example.expenses;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

import org.apache.http.client.ClientProtocolException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends Activity {

	private EditText tUsername;
	private EditText tPassword;
	private String sUsername;
	private String sPassword;
	private Button myButton;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		tUsername = (EditText) findViewById(R.id.edit_name);
		tPassword = (EditText) findViewById(R.id.edit_password);

		myButton = (Button) findViewById(R.id.my_button);
		myButton.setOnClickListener(new ButtonListener());
	}

	class ButtonListener implements OnClickListener {

		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub

			sUsername = tUsername.getText().toString();
			sPassword = tPassword.getText().toString();
			if (TextUtils.isEmpty(sUsername)) {
				tUsername.setError("Please enter your username.");
			}

			if (TextUtils.isEmpty(sPassword)) {
				tPassword.setError("Please enter your password.");
			}

			new WebTask().execute(new String[] { sUsername, sPassword });
		}

	}

	class WebTask extends AsyncTask<String, Void, JSONObject> {

		private String UserID = null;
		private String UserStatus = null;

		@Override
		protected JSONObject doInBackground(String... arg0) {
			// TODO Auto-generated method stub
			JSONObject JSONObj = parseJson(arg0[0], arg0[1]);

			return JSONObj;
		}

		@Override
		protected void onPostExecute(JSONObject JSONobj) {
			if (JSONobj == null || JSONobj.length() == 0) {
				Toast.makeText(
						getApplicationContext(),
						"The username and password does not match. Try again please.",
						0).show();
				return;
			} else {
				try {
					UserID = JSONobj.getString("UserID");
					UserStatus = JSONobj.getString("Ustatus");
					new WebJSONTask()
							.execute(new String[] { UserID, UserStatus });
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

	class WebJSONTask extends AsyncTask<String, Void, ArrayList<String>> {
		@Override
		protected ArrayList<String> doInBackground(String... arg0) {
			ArrayList<String> arr = getJson(arg0[0], arg0[1]);
			return arr;

		}

		@Override
		protected void onPostExecute(ArrayList<String> arr) {
			ArrayAdapter<String> adapter = new ArrayAdapter<String>(
					getApplicationContext(),
					android.R.layout.simple_expandable_list_item_1, arr);
			Intent i = new Intent(getApplicationContext(),
					ExpenseActivity.class);
			i.putStringArrayListExtra("arrayList", arr);
			startActivity(i);
			// ListView lListView = (ListView)findViewById(R.id.my_listview);
			// lListView.setAdapter(adapter);
		}
	}

	public ArrayList<String> getJson(String uID, String uStatus) {
		String surl = "http://146.145.105.252/ServiceHRMS/HrmsService.svc/web/GetExpenses?EmpID="
				+ uID + "&Ustatus=" + uStatus;
		StringBuilder res = new StringBuilder();
		ArrayList<String> result = new ArrayList<String>();
		URL url;
		try {
			url = new URL(surl);
			URLConnection urlConnection = url.openConnection();
			InputStream lInputStream = urlConnection.getInputStream();
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					lInputStream, "iso-8859-1"), 8);
			String line = null;
			while ((line = reader.readLine()) != null) {
				res.append(line + "\n");
			}
			JSONObject object = new JSONObject(res.toString());
			JSONArray array = object.getJSONArray("JGetExpensesResult");

			for (int i = 0; i < array.length(); i++) {
				result.add(array.getJSONObject(i).getString("ExpenseNumber"));
			}

		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;

	}

	public JSONObject parseJson(String sUsername, String sPassword) {
		JSONObject obj = null;
		StringBuilder sb = new StringBuilder();
		sb.append("http://146.145.105.252/ServiceHRMS/HrmsService.svc/web/LoginUser/");
		sb.append(sUsername);
		sb.append("/");
		sb.append(sPassword);
		String lURL = sb.toString();
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
			JSONArray arr = new JSONArray(res.toString());
			obj = (JSONObject) arr.get(0);

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
		return obj;
	}
}

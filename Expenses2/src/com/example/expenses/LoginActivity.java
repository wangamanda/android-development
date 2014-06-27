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
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import com.example.wso.NetworkConnections;

/**
 * This is the activity for login screen. Take username and password as inputs.
 * @author xinwa_000
 *
 */
public class LoginActivity extends Activity {

	private EditText tUsername;
	private EditText tPassword;

	private Button myButton;
	private String Empname;
	private String EmailID;
	private String cell;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		init();
	}

	private void init(){
		tUsername = (EditText) findViewById(R.id.edit_name);
		tPassword = (EditText) findViewById(R.id.edit_password);

		myButton = (Button) findViewById(R.id.my_button);
		myButton.setOnClickListener(mOnClickListener);
	}
	
	OnClickListener mOnClickListener = new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			String sUsername = tUsername.getText().toString().trim();//trim white space
			String sPassword = tPassword.getText().toString().trim();
			
			if (TextUtils.isEmpty(sUsername)) {
				tUsername.setError("Please enter your username.");
				return;
			}

			if (TextUtils.isEmpty(sPassword)) {
				tPassword.setError("Please enter your password.");
				return;
			}

			new WebTask().execute(new String[] { sUsername, sPassword });

		}
	};
	
	ProgressDialog lProgressDialog;
	class WebTask extends AsyncTask<String, Void, JSONObject> {

		private String UserID = null;
		private String UserStatus = null;

		@Override
		protected void onPreExecute() {
			lProgressDialog = new ProgressDialog(LoginActivity.this);
			lProgressDialog.setMessage("Please wait.");
			lProgressDialog.show();
		}
		
		@Override
		protected JSONObject doInBackground(String... arg0) {
			return NetworkConnections.LoginNetworkRequest(arg0[0], arg0[1]);
		}

		@Override
		protected void onPostExecute(JSONObject JSONobj) {
			if (JSONobj == null || JSONobj.length() == 0) {
//				 Toast.makeText(
//				 getApplicationContext(),
//				 "The username and password does not match. Try again please.",
//				 0).show();
				MakeDialog();
				return;
			} else {
				try {
					UserID = JSONobj.getString("UserID");
					UserStatus = JSONobj.getString("Ustatus");
					Empname = JSONobj.getString("Empname");
					EmailID = JSONobj.getString("EmailID");
					cell = JSONobj.getString("cell");
					new WebJSONTask()
							.execute(new String[] { UserID, UserStatus });
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

	class WebJSONTask extends AsyncTask<String, Void, ArrayList<ItemStructure>> {
		@Override
		protected ArrayList<ItemStructure> doInBackground(String... arg0) {
			ArrayList<ItemStructure> arr = getJson(arg0[0], arg0[1]);
			return arr;

		}

		@Override
		protected void onPostExecute(ArrayList<ItemStructure> array) {
			ArrayList<String> arr1 = new ArrayList<String>();
			ArrayList<String> arr2 = new ArrayList<String>();
			for(ItemStructure itm : array){
				if(itm.getStatus() == 0){
					arr1.add(itm.getExpenseNumber());//saved
				}else{
					arr2.add(itm.getExpenseNumber());//submitted
				}
			}
			
			lProgressDialog.dismiss();
//			ArrayAdapter<String> adapter = new ArrayAdapter<String>(
//					getApplicationContext(),
//					android.R.layout.simple_expandable_list_item_1, arr);
			Intent i = new Intent(getApplicationContext(),
					ExpenseActivity.class);
			i.putStringArrayListExtra("saved", arr1);
			i.putStringArrayListExtra("submitted", arr2);
//			SharedPreferences sharedPreferences = PreferenceManager
//					                .getDefaultSharedPreferences(LoginActivity.this);
			SharedPreferences sharedPreferences = getSharedPreferences("user_info", 0);  
			Editor editor = sharedPreferences.edit();
			editor.putString("Empname", Empname);
			editor.putString("EmailID", EmailID);
			editor.putString("cell", cell);
			editor.commit();
			startActivity(i);
			// ListView lListView = (ListView)findViewById(R.id.my_listview);
			// lListView.setAdapter(adapter);
		}
	}

	public void MakeDialog() {
		AlertDialog.Builder builder = new Builder(LoginActivity.this);
		builder.setTitle("ErrorMessage:");
		builder.setMessage("The Username and Password does not match. Please try again.");
		builder.setPositiveButton("OK", new DialogInterface.OnClickListener(){

			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				dialog.dismiss();
			}
			
		});
		builder.create().show();
		
	}

	public ArrayList<ItemStructure> getJson(String uID, String uStatus) {
		String surl = "http://146.145.105.252/ServiceHRMS/HrmsService.svc/web/GetExpenses?EmpID="
				+ uID + "&Ustatus=" + uStatus;
		StringBuilder res = new StringBuilder();
		ArrayList<ItemStructure> result = new ArrayList<ItemStructure>();
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
				ItemStructure itm = new ItemStructure();
				itm.setExpenseNumber(array.getJSONObject(i).getString("ExpenseNumber"));
				itm.setStatus(array.getJSONObject(i).getInt("Status"));
				result.add(itm);
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

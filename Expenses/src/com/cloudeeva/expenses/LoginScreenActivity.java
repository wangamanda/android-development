package com.cloudeeva.expenses;

import com.cloudeeva.expenses.wso.NetworkUtilities;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.Toast;

public class LoginScreenActivity extends Activity{

	private EditText mUserName;
	private EditText mPassword;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login_screen);
		initUIObjects();
	}
	
	private void initUIObjects() {
		mUserName = (EditText)findViewById(R.id.edittext_username);
		mPassword = (EditText)findViewById(R.id.edittext_pwd);
		
		//setting the click listner for login button
		findViewById(R.id.button_login).setOnClickListener(mLoginListener);
	}
	
	OnClickListener mLoginListener = new OnClickListener() {
		
		@Override
		public void onClick(View arg0) {
			performTheLogin();
		}
	};
	
	private void performTheLogin() {
		String lUsername = mUserName.getText().toString().trim();
		String lPwd = mPassword.getText().toString().trim();
		
		//check if the username is empty
		if(TextUtils.isEmpty(lUsername)){
			//show some notification to the user that username is required
			mUserName.setError(getResources().getString(R.string.error_username));
			return;
		}
		
		if(TextUtils.isEmpty(lPwd)){
			//show some notification to the user that password is required
			mUserName.setError(getResources().getString(R.string.error_password));
			return;
		}
		
		new LoginTask().execute(new String[] {lUsername,lPwd});
		
	}
	
	//STring type of paramenters
	ProgressDialog lProgressDialog;
	class LoginTask extends AsyncTask<String, Void, Void> {

		@Override
		protected void onPreExecute() {
			lProgressDialog = new ProgressDialog(LoginScreenActivity.this);
			lProgressDialog.setMessage("Please wait");
			lProgressDialog.setCancelable(false);
		}
		
		@Override
		protected Void doInBackground(String... lValues) {

			//all the fields are available perform the login
			NetworkUtilities.verifyTheLoginCredentials(LoginScreenActivity.this,lValues[0], lValues[1]);
			return null;
		}
		
		
		@Override
		protected void onPostExecute(Void result) {
			lProgressDialog.cancel();
		}
	}
}

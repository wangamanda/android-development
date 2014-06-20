package com.example.expenses;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.EditText;

public class LoginActivity extends Activity{

	private EditText tUsername;
	private EditText tPassword;
	
	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		tUsername = (EditText)findViewById(R.id.edit_name);
		tPassword = (EditText)findViewById(R.id.edit_password);
		
		String sUsername = tUsername.getText().toString();
		String sPassword = tPassword.getText().toString();
		
		if(TextUtils.isEmpty(sUsername)){
			tUsername.setError("Please enter your username.");
		}
		
		if(Te)
	}
}

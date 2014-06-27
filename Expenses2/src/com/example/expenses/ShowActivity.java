package com.example.expenses;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

public class ShowActivity extends Activity{
	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_show);
//		Toast.makeText(getApplicationContext(), "here", 0).show();

//		SharedPreferences sharedPreferences = ShowActivity.this.getSharedPreferences("ljq", Context.MODE_WORLD_READABLE);
		SharedPreferences sharedPreferences = getSharedPreferences("user_info", 0);  
		String Name = sharedPreferences.getString("Empname", "");
		String Email = sharedPreferences.getString("EmailID", "");
		String Phone = sharedPreferences.getString("cell", "");
		TextView lTextView = (TextView)findViewById(R.id.show_textview);
		lTextView.setText("Name \t"+Name + "\n"+"Email \t"+Email+"\n"+"Phone \t"+Phone);
		Button lButton = (Button)findViewById(R.id.button);
		lButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
			}
		});
		
	}
}

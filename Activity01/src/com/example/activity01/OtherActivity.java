package com.example.activity01;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class OtherActivity extends Activity{

	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_other);
		Intent lIntent = getIntent();
		String value = lIntent.getStringExtra("intent");
		TextView lTextView = (TextView)findViewById(R.id.text_view);
		lTextView.setText(value);
	}
}

package com.example.multiply;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class ResultActivity extends Activity{
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_result);
		Intent lIntent = getIntent();
		String s1 = lIntent.getStringExtra("1");
		String s2 = lIntent.getStringExtra("2");
		
		int num1 = Integer.parseInt(s1);
		int num2 = Integer.parseInt(s2);
		
		TextView lTextView = (TextView)findViewById(R.id.result_view);
		String res = num1*num2+"";
		lTextView.setText(res);
	}
}

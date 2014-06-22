package com.example.multiply;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends ActionBarActivity {
	private String edit1 = null;
	private String edit2 = null;
	private EditText firstEditText;
	private EditText secondEditText;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		firstEditText = (EditText)findViewById(R.id.multi1);
		secondEditText = (EditText)findViewById(R.id.multi2);
		TextView lTextView = (TextView)findViewById(R.id.text_view);
		Button myButton = (Button)findViewById(R.id.my_button);
		
		myButton.setOnClickListener(new buttonClickListener());
	}
	
	class buttonClickListener implements OnClickListener{

		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			Intent lIntent = new Intent();
			lIntent.setClass(MainActivity.this, ResultActivity.class);
			edit1 = firstEditText.getText().toString();
			edit2 = secondEditText.getText().toString();
			lIntent.putExtra("1", edit1);
			lIntent.putExtra("2", edit2);
			MainActivity.this.startActivity(lIntent);
		}		
	}

}

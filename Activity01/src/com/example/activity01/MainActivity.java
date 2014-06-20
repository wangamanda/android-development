package com.example.activity01;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		TextView myTextView = (TextView) findViewById(R.id.text_view);
		Button myButton = (Button) findViewById(R.id.my_button);
		myTextView.setText("This is textview.");
		myButton.setText("Click here");
		myButton.setOnClickListener(new myButtonListener());
	}
	
	class myButtonListener implements OnClickListener{

		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			Intent lIntent = new Intent();
			lIntent.setClass(MainActivity.this, OtherActivity.class);
			lIntent.putExtra("intent", "FromIntent");
			MainActivity.this.startActivity(lIntent);
		}
	}

}

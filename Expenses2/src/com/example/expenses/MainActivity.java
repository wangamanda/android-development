package com.example.expenses;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.TextView;

public class MainActivity extends Activity {

	private TextView lTextView;
	private Intent lIntent;
	private final static int SPLASH_TIME = 3000;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);		
		setContentView(R.layout.activity_main);
		initUI();
		myHandler mHandler = new myHandler();
		mHandler.sendEmptyMessageDelayed(0, SPLASH_TIME);
	}
	
	public void login(){
		lIntent = new Intent();
		lIntent.setClass(MainActivity.this, LoginActivity.class);
		MainActivity.this.startActivity(lIntent);
		finish();
	}
	
	protected void initUI(){
		lTextView = (TextView)findViewById(R.id.text_view);
	}
	
	class myHandler extends Handler{
		@Override
		public void handleMessage(Message msg){
			login();
		}
	}
}

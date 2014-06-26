package com.example.expenses;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

/**
 * This is the activity responsible for displaying flash screen 
 * @author xinwa_000
 *
 */
public class MainActivity extends Activity {
	
	/**Duration for the splash screen*/
	private final int SPLASH_TIME = 3000;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);		
		setContentView(R.layout.activity_main);
		init();
	}
		
	private void init(){
		myHandler mHandler = new myHandler();
		mHandler.sendEmptyMessageDelayed(0, SPLASH_TIME);
	}
	
	private void login(){
		Intent lIntent = new Intent();
		lIntent.setClass(MainActivity.this, LoginActivity.class);
		startActivity(lIntent);
		finish();
	}
	
	class myHandler extends Handler{
		@Override
		public void handleMessage(Message msg){
			login();
		}
	}
}

package com.cloudeeva.expenses;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.StrictMode;

public class FlashScreenActivity extends Activity {
	
	/**THe amount of time the flash screen should displayed*/
	private static final int FLASH_TIME = 3000;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		
		//it sends a msg after defined amount of time
		mHandler.sendEmptyMessageDelayed(0, FLASH_TIME);

	}
	
	private Handler mHandler = new Handler() {
		public void handleMessage(Message msg) {
			startLoginScreen();
		};
	};

	/**
	 * starts login Screen
	 */
	private void startLoginScreen() {
		Intent LIntent = new Intent();
		LIntent.setClass(this, LoginScreenActivity.class);
		startActivity(LIntent);
	}

}

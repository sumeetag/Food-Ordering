package com.codragon.restuarant;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class Splash extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.splash);
		
		Thread timer = new Thread(){
			@Override
			public void run() {
				// TODO Auto-generated method stub
				super.run();
				try {
					sleep(4000);
					//sleep(5000);
				} catch (InterruptedException e) {
					// TODO: handle exception
					e.printStackTrace();
				}finally{
					Intent intent=new Intent();
					intent.setClass(Splash.this, Login.class);
					startActivity(intent);
				}
			}
		};
		timer.start();
	}
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		finish();
	}
	
}

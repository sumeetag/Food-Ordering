package com.codragon.restuarant;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class Main extends Activity {
	
	// label to display gcm messages
	TextView lblMessage;
	Controller aController;
	
	
	@Override
	public void onCreate(Bundle savedInstanceState) { 
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		/******************* Intialize Database *************/
		DBAdapter.init(this);
		
		// Get Global Controller Class object 
		// (see application tag in AndroidManifest.xml)
		aController = (Controller) getApplicationContext();
		
		
		// Check if Internet present
		if (!aController.isConnectingToInternet()) {
			
			// Internet Connection is not present
			aController.showAlertDialog(Main.this,
					"Internet Connection Error",
					"Please connect to Internet connection", false);
			// stop executing code by return
			return;
		}
	
		//Check device contains self information in sqlite database or not. 
		int vDevice = DBAdapter.validateDevice();	
		System.out.println("device"+ vDevice);
		if(vDevice > 0)
		{	
			
			// Launch Main Activity
			Intent i = new Intent(getApplicationContext(), Login.class);
			startActivity(i);
			finish();
		}
		else
		{
			Intent i = new Intent();
			i.setClass(Main.this, Register.class);
			startActivity(i);
			}	
	}		
	
	
	// Class with extends AsyncTask class
	
	@Override
	protected void onDestroy() {
		
		super.onDestroy();
	}

}

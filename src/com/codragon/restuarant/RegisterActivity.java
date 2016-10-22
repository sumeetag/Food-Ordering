package com.codragon.restuarant;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class RegisterActivity extends Activity {

	// UI elements
	EditText txtName;
	EditText txtEmail;

	// Register button
	Button btnRegister;
	private String name;
	private String email;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		/******************* Intialize Database *************/
		DBAdapter.init(this);

		// setContentView(R.layout.activity_register);

		// Get Global Controller Class object (see application tag in
		// AndroidManifest.xml)
		final Controller aController = (Controller) getApplicationContext();

		// Check if Internet Connection present
		if (!aController.isConnectingToInternet()) {

			// Internet Connection is not present
			aController.showAlertDialog(RegisterActivity.this,
					"Internet Connection Error",
					"Please connect to working Internet connection", false);

			// stop executing code by return
			return;
		}

		// Check if GCM configuration is set
		if (Config.YOUR_SERVER_URL == null || Config.GOOGLE_SENDER_ID == null
				|| Config.YOUR_SERVER_URL.length() == 0
				|| Config.GOOGLE_SENDER_ID.length() == 0) {

			// GCM sernder id / server url is missing
			aController.showAlertDialog(RegisterActivity.this,
					"Configuration Error!",
					"Please set your Server URL and GCM Sender ID", false);

			// stop executing code by return
			return;
		}

		// Click event on Register button
		// Get data from EditText
		Intent in = getIntent();

		name = in.getStringExtra("name");
		email = in.getStringExtra("email");

		// Check if user filled the form
		if (name.trim().length() > 0 && email.trim().length() > 0) {

			// Launch Main Activity
			Intent i = new Intent(getApplicationContext(), GCMMainActivity.class);

			// Registering user on our server
			// Sending registraiton details to MainActivity
			i.putExtra("name", name);
			i.putExtra("email", email);
			startActivity(i);
			finish();

		} else {

			// user doen't filled that data
			aController.showAlertDialog(RegisterActivity.this,
					"Registration Error!", "Please enter your details", false);
		}

	}

}

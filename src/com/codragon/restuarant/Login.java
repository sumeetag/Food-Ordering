package com.codragon.restuarant;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Login extends Activity implements OnClickListener {

	private EditText pw, rid;
	private LoginDatabase db;
	public static String mail;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login);

		rid = (EditText) findViewById(R.id.etREGID);
		pw = (EditText) findViewById(R.id.etpass);
		Button login = (Button) findViewById(R.id.bLogin);
		TextView forpass = (TextView) findViewById(R.id.tvpassforget);

		forpass.setOnClickListener(this);
		//register.setOnClickListener(this);
		login.setOnClickListener(this);

	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.bLogin:
			boolean diditwork = true;
			try {
				String pass = pw.getText().toString();
				String reg = rid.getText().toString();

				db = new LoginDatabase(this);
				db.open();
				if (pass.length() != 0 && reg.length() != 0) {
					if (!db.getPass(pass).equalsIgnoreCase(null) && !db.getReg(reg).equalsIgnoreCase(null)) {
						db.close();
						diditwork = false;
						// Launch Main Activity
						Intent i = new Intent(getApplicationContext(), GridViewExample.class);
						startActivity(i);
						finish();
					}
				}
				db.close();
			} catch (Exception e) {
				// TODO: handle exception
				diditwork = false;
				String error = e.toString();

				Toast.makeText(getApplicationContext(), " not registered",
						Toast.LENGTH_SHORT).show();

			} finally {
				if (diditwork) {

					Toast.makeText(getApplicationContext(),
							"Pls enter the details", Toast.LENGTH_SHORT)
							.show();

				}
			}

			break;
			
		case R.id.tvpassforget:
			Intent i = new Intent(getApplicationContext(), Password.class);
			startActivity(i);
			
		}
	}
}
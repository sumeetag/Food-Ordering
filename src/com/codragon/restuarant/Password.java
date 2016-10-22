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

public class Password extends Activity implements OnClickListener {
	
	private EditText email;
	private EditText name;
	private LoginDatabase db;
	private String e;
	private String n;
	private TextView pass;
	private EditText reg;
	private EditText phone;
	private String pw;
	private String ph;
	private String rid;
	String deviceIMEI = "";
	Controller aController;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.password);

		name = (EditText) findViewById(R.id.etpname);
		email = (EditText) findViewById(R.id.etpemail);
		//pass = (EditText)findViewById(R.id.etass);
		reg = (EditText)findViewById(R.id.etpreg_no);
		phone = (EditText)findViewById(R.id.etpmobile);
		
		Button show = (Button) findViewById(R.id.bpass);
		pass = (TextView)findViewById(R.id.tvshowpass);
		pass.setText("");
		show.setOnClickListener(this);

	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		finish();
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.bpass:
			boolean diditwork = true;
			try {
				e = email.getText().toString();
				n = name.getText().toString();
				ph = phone.getText().toString();
				rid = reg.getText().toString();
				db = new LoginDatabase(this);
				db.open();
				// db.createEntry(n, e);
				if (e.length() != 0 && n.length() != 0 && rid.length() != 0 && ph.length() != 0) {
					if (!db.getReg(rid).equalsIgnoreCase(null) && !db.getName(n).equalsIgnoreCase(null) && !db.getEmail(e).equalsIgnoreCase(null) && !db.getPhone(ph).equalsIgnoreCase(null)) {
						pass.setText(db.getPassShow(rid));
						db.close();
						diditwork = false;
						
					}else{
						diditwork = false;
						Toast.makeText(getApplicationContext(), "Account not exists",
								Toast.LENGTH_SHORT).show();
					}

				}
				db.close();
			} catch (Exception ex) {
				// TODO: handle exception
				diditwork = false;
				db = new LoginDatabase(this);
				db.open();
				// db.createEntry(n, e);
				System.out.println(e+" "+n+" "+pw+" "+rid+" "+ph);
				if (e.length() != 0 && n.length() != 0 && rid.length() != 0 && ph.length() != 0) {
						db.createEntry(n, e, ph, pw, rid);
						db.close();
						//Login.mail = e;
						

				}
				db.close();
				
			} finally {
				if (diditwork) {

					Toast.makeText(getApplicationContext(),
							"Pls enter the details", Toast.LENGTH_SHORT).show();

				}
			}

			break;
		}
	}

}

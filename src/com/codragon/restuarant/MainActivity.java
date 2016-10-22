/*package com.codragon.restuarant;

import java.io.IOException;
import android.app.Activity;
import android.content.Intent;
import android.database.SQLException;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity implements OnItemSelectedListener,
		OnClickListener {

	Spinner spinner;
	Button order, rating;
	String pos;
	DatabaseOpenHelper entry;
	private TextView wel;
	private LoginDatabase db;
	private Button pre;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		order = (Button) findViewById(R.id.place_order);
		rating = (Button) findViewById(R.id.give_rating);
		wel = (TextView)findViewById(R.id.tvwelcome);
		pre = (Button)findViewById(R.id.pre_order);
		pre.setOnClickListener(this);
		order.setOnClickListener(this);
		rating.setOnClickListener(this);

		//setnof();
		
		db = new LoginDatabase(this);
		db.open();
		wel.setText("Welcome " + db.getName(Login.mail));
		Toast.makeText(getApplicationContext(), db.getCount(Login.mail), Toast.LENGTH_SHORT).show();
		db.close();
		String[] paths = { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10" };
		// Spinner element
		spinner = (Spinner) findViewById(R.id.spinner1);

		// Spinner click listener
		spinner.setOnItemSelectedListener(this);

		// Spinner Drop down elements
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_dropdown_item, paths);
		spinner.setAdapter(adapter);

	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		//setnof();
	}

	private void setnof() {
		
		
		entry = new DatabaseOpenHelper(MainActivity.this);

		try {

			entry.create();

		} catch (IOException ioe) {

			throw new Error("Unable to create database");

		}

		try {

			entry.open();

		} catch (SQLException sqle) {

			throw sqle;

		}
		entry.open();
		for (int i = 1; i <= 222; i++) {
			entry.updateNofUser(i, "0");
		}
		entry.close();
	}
	

	@Override
	public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
			long arg3) {
		// TODO Auto-generated method stub
		int position = spinner.getSelectedItemPosition();
		switch (position) {
		case 0:
			pos = "1";
			break;
		case 1:
			pos = "2";
			break;
		case 2:
			pos = "3";
			break;
		case 3:
			pos = "4";
			break;
		case 4:
			pos = "5";
			break;
		case 5:
			pos = "6";
			break;
		case 6:
			pos = "7";
			break;
		case 7:
			pos = "8";
			break;
		case 8:
			pos = "9";
			break;
		case 9:
			pos = "10";
			break;

		default:
			break;
		}

	}

	@Override
	public void onNothingSelected(AdapterView<?> arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub

		switch (v.getId()) {
		case R.id.place_order:
			Bundle basket = new Bundle();
			basket.putString("key", pos);
			Intent i = new Intent(this, FoodList.class);
			i.putExtras(basket);
			startActivity(i);
			break;

		case R.id.give_rating:
			Bundle bask = new Bundle();
			bask.putString("key", pos);
			Intent in = new Intent(this, Rating.class);
			in.putExtras(bask);
			startActivity(in);
			break;
			
		case R.id.pre_order:
			Intent ind = new Intent(this, Preorders.class);
			startActivity(ind);
			break;
		}

	}

	
	@Override
	protected void onPause() {
	    if(entry != null ){
	        entry.close();
	    }
	}
	

}
*/
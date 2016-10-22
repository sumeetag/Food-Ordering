/*package com.codragon.restuarant;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;

public class Rating extends ActionBarActivity {

	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		// setContentView(R.layout.giverating);
		setContentView(R.layout.rating);
		FragmentManager fm = getSupportFragmentManager();
		FragmentTransaction ft = fm.beginTransaction();

		RatingFragment list = new RatingFragment();
		ft.add(R.id.content, list).commit();
		
		
		
	}

	

}
*/
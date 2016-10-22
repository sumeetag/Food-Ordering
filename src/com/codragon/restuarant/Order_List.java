package com.codragon.restuarant;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;

public class Order_List extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.order);
		FragmentManager fm = getSupportFragmentManager();
		FragmentTransaction ft = fm.beginTransaction();

		Order_ListFragment list = new Order_ListFragment();
		ft.add(R.id.content, list).commit();
		/*
		 * 
		 * DatabaseOpenHelper entry = new DatabaseOpenHelper(this);
		 * entry.open(); // int i = entry.getFood1("0").length;
		 * 
		 * System.out.println(table_no); listItems = (entry.getFood1(table_no));
		 * entry.close(); setListAdapter(new ArrayAdapter<String>(this,
		 * android.R.layout.simple_list_item_1, listItems));
		 */

	}

	/*
	 * @Override public void onClick(View v) { // TODO Auto-generated method
	 * stub switch (v.getId()) { case R.id.confirm: Intent i = new Intent(this,
	 * MainActivity.class); startActivity(i); break;
	 * 
	 * case R.id.add: //Intent in = new Intent(this, FoodList.class);
	 * //startActivity(in); finish(); break; } }
	 */

}

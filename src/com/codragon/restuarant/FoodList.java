package com.codragon.restuarant;

import java.io.IOException;
import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.SQLException;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBar.Tab;
import android.support.v7.app.ActionBarActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class FoodList extends ActionBarActivity implements
		ActionBar.TabListener {

	// String[] foods;
	ArrayList<String> foods, tabs;
	public int i = 0;
	ListView lv;
	Model[] modelItems = null;
	String table_no, food, order, rating, price, cat, nof;
	Button see_order;
	private String[] splited;
	int tabpos;
	public String position;
	private ViewPager viewPager;
	private TabsPagerAdapter mAdapter;
	private ActionBar actionBar;
	public static int nom;
	boolean flag;
	int pos, no;
	CheckBox cb;
	TextView nf;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.newlayout);

		tabs = new ArrayList<String>();
		MenuDatabase md = new MenuDatabase(this);
		boolean diditwork = true;
		try {
			System.out.println("foodlist sdfdfdf");

			md.open();
			System.out.println("foodlist ada " + md.getUserDataCount());
			for (int i = 1; i <= md.getUserDataCount(); i++) {
				System.out.println("foodlist before");
				tabs.add(md.getName(i));
				System.out.println("foodlist " + tabs.get(i - 1));
			}
			nom = md.getUserDataCount();
			System.out.println("foodlist " + nom);
			md.close();
			diditwork = false;
		} catch (Exception e) {
			// TODO: handle exception
			diditwork = false;
			String error = e.toString();
			System.out.println("foodlist catch");
			Toast.makeText(getApplicationContext(), "Email not registered",
					Toast.LENGTH_SHORT).show();

		} finally {
			if (diditwork) {
				System.out.println("foodlist final");
				Toast.makeText(getApplicationContext(), "Pls enter the email",
						Toast.LENGTH_SHORT).show();

			}
			viewPager = (ViewPager) findViewById(R.id.pager);
			actionBar = getSupportActionBar();
			mAdapter = new TabsPagerAdapter(getSupportFragmentManager());
			viewPager.setAdapter(mAdapter);
			actionBar.setHomeButtonEnabled(false);
			actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

			// Adding Tabs
			for (String tab_name : tabs) {
				LayoutInflater inflater = LayoutInflater.from(this);
				View customView = inflater.inflate(R.layout.tab_title, null);

				TextView titleTV = (TextView) customView
						.findViewById(R.id.action_custom_title);
				titleTV.setText(tab_name);
				// Here you can also add any other styling you want.
				actionBar.addTab(actionBar.newTab().setText(tab_name)
						.setCustomView(customView).setTabListener(this));
			}

			/**
			 * on swiping the viewpager make respective tab selected
			 * */
			viewPager
					.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

						@Override
						public void onPageSelected(int position) {
							// on changing the page
							// make respected tab selected
							actionBar.setSelectedNavigationItem(position);
						}

						@Override
						public void onPageScrolled(int arg0, float arg1,
								int arg2) {
						}

						@Override
						public void onPageScrollStateChanged(int arg0) {
						}
					});
		}

	}

	public void name(String position, Activity context) {
		// this.position=position;
		MainDatabaseOpenHelper entry = new MainDatabaseOpenHelper(context);

		try {

			entry.open();

		} catch (SQLException sqle) {

			throw sqle;

		}

		entry.open();
		int id = entry.getId(position);

		String order = entry.getOrder(id);

		int i = Integer.parseInt(order);

		if (i == 0) {
			i++;
			// cb.setChecked(true);
			order = Integer.toString(i);
			System.out.println(order);
			System.out.println(position);

			LayoutInflater inflator = (LayoutInflater) context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			View toastRoot = inflator.inflate(R.layout.toast, null);

			Toast toast = new Toast(context);
			TextView tt = (TextView) toastRoot.findViewById(R.id.toast);
			tt.setText("You have selected " + order + " " + position);
			toast.setView(toastRoot);
			toast.setGravity(Gravity.CENTER_HORIZONTAL, 0, 0);
			toast.setDuration(Toast.LENGTH_SHORT);
			toast.show();
			/*
			 * Toast.makeText(context, "You have selected "+ nof + " " +
			 * position, Toast.LENGTH_SHORT).show();
			 */
			entry.updateOrder(id, order);
		}
		entry.close();
	}

	public void notname(String position, Activity context) {

		MainDatabaseOpenHelper entry = new MainDatabaseOpenHelper(context);

		try {

			entry.open();

		} catch (SQLException sqle) {

			throw sqle;

		}

		entry.open();
		int id = entry.getId(position);

		entry.updateOrder(id, "0");
		entry.close();
	}

	public void nof(String position, Activity context, CheckBox cb) {

		MainDatabaseOpenHelper entry = new MainDatabaseOpenHelper(context);

		/*try {

			entry.open();

		} catch (IOException ioe) {

			throw new Error("Unable to create database");

		}*/

		try {

			entry.open();

		} catch (SQLException sqle) {

			throw sqle;

		}finally{
			entry.open();
		}
		int id = entry.getId(position);

		String order = entry.getOrder(id);

		int i = Integer.parseInt(order);
		i++;
		// cb.setChecked(true);
		order = Integer.toString(i);
		System.out.println(order);
		System.out.println(position);

		LayoutInflater inflator = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View toastRoot = inflator.inflate(R.layout.toast, null);

		Toast toast = new Toast(context);
		TextView tt = (TextView) toastRoot.findViewById(R.id.toast);
		tt.setText("You have selected " + order + " " + position);
		toast.setView(toastRoot);
		toast.setGravity(Gravity.CENTER_HORIZONTAL, 0, 0);
		toast.setDuration(Toast.LENGTH_SHORT);
		toast.show();
		/*
		 * Toast.makeText(context, "You have selected "+ nof + " " + position,
		 * Toast.LENGTH_SHORT).show();
		 */
		entry.updateOrder(id, order);
		entry.close();

	}

	public void nofnew(String position, Activity context) {

		MainDatabaseOpenHelper entry = new MainDatabaseOpenHelper(context);

		try {

			entry.open();

		} catch (SQLException sqle) {

			throw sqle;

		}

		entry.open();
		int id = entry.getId(position);

		String order = entry.getOrder(id);

		int i = Integer.parseInt(order);
		i--;
		if (i >= 0) {
			order = Integer.toString(i);

			LayoutInflater inflator = (LayoutInflater) context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			View toastRoot = inflator.inflate(R.layout.toast, null);

			Toast toast = new Toast(context);
			TextView tt = (TextView) toastRoot.findViewById(R.id.toast);
			tt.setText("You have selected " + nof + " " + position);
			toast.setView(toastRoot);
			toast.setGravity(Gravity.CENTER_HORIZONTAL, 0, 0);
			toast.setDuration(Toast.LENGTH_SHORT);
			toast.show();

			/*
			 * Toast.makeText(context, "You have selected "+ nof + " " +
			 * position, Toast.LENGTH_SHORT).show();
			 */
			entry.updateOrder(id, order);
		} else {
			Toast.makeText(context, "Pls select a valid number",
					Toast.LENGTH_LONG).show();
		}
		entry.close();

	}

	@Override
	public void onTabSelected(Tab tab, FragmentTransaction ft) {
		// TODO Auto-generated method stub
		viewPager.setCurrentItem(tab.getPosition());
		tabpos = tab.getPosition();
	}

	@Override
	public void onTabUnselected(Tab tab, FragmentTransaction ft) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onTabReselected(Tab tab, FragmentTransaction ft) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		switch (item.getItemId()) {
		case R.id.See_Order:
			Intent in = new Intent();
			in.setClass(this, Order_List.class);

			startActivity(in);
			return true;

		case R.id.Search_Food:
			Intent i = new Intent();
			i.setClass(this, Search.class);
			startActivity(i);

		default:
			return false;
		}
	}

	/*
	 * public void displayAlert() { final DialogFragment dialog = new
	 * DialogFragment(); dialog.setContentView(R.layout.dialog);
	 * dialog.setTitle("No of Orders..."); i = 0;
	 * 
	 * nf = (TextView)dialog.findViewById(R.id.nofood); Button add =
	 * (Button)dialog.findViewById(R.id.foodadd); Button sub =
	 * (Button)dialog.findViewById(R.id.foodsub);
	 * 
	 * add.setOnClickListener(this); sub.setOnClickListener(this);
	 * 
	 * dialog.show(); /* new
	 * AlertDialog.Builder(getContext()).setMessage(Integer.toString(i))
	 * .setTitle("No of Orders.") .setCancelable(true)
	 * .setNeutralButton(android.R.string.ok, new
	 * DialogInterface.OnClickListener() { public void onClick(DialogInterface
	 * dialog, int whichButton){
	 * 
	 * i ++;
	 * 
	 * } }) .show();
	 * 
	 * }
	 * 
	 * @Override public void onClick(View v) { // TODO Auto-generated method
	 * stub switch (v.getId()) { case R.id.foodadd: i++;
	 * nf.setText(Integer.toString(i)); break;
	 * 
	 * case R.id.foodsub: i--; nf.setText(Integer.toString(i)); break; } }
	 */

}

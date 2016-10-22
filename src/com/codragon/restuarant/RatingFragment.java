/*package com.codragon.restuarant;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.database.SQLException;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class RatingFragment extends ListFragment {

	RatingModel[] modelItems = null;
	ArrayAdapter<Integer> ids;
	ArrayList<String> food = new ArrayList<String>();
	ListView lv;
	String table_no;
	String[] splited;
	public static ArrayList<Integer> preorder;
	int pos, no, k = 0;
	public static String table;
	public static ArrayList<String> fd;
	public static ArrayList<String> rt;
	String table1, food_item, order, rating, price, cat, nof;
	private LoginDatabase db;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View v = inflater.inflate(R.layout.rating_listview, container, false);
		Bundle gotBasket = getActivity().getIntent().getExtras();
		table_no = gotBasket.getString("key");
		table = table_no;
		fd = new ArrayList<String>();
		rt = new ArrayList<String>();
		preorder = new ArrayList<Integer>();

		
		 * ArrayAdapter<RatingModel> adapter = new RatingCustomAdapter(this,
		 * getModel()); setListAdapter(adapter);
		 
		return v;
	}

	@Override
	public void onActivityCreated(Bundle b) {
		setHasOptionsMenu(true);
		ArrayAdapter<RatingModel> adapter = new RatingCustomAdapter(
				getActivity(), getModel());
		setListAdapter(adapter);
		super.onActivityCreated(b);
	}

	private List<RatingModel> getModel() {
		List<RatingModel> list = new ArrayList<RatingModel>();
		DatabaseOpenHelper entry = new DatabaseOpenHelper(getActivity());

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
		food = entry.getFood1(table_no);
		System.out.println(food.size());
		// list.add(get("Give Rating..."));
		for (int i = 0; i < food.size(); i++) {
			System.out.println(food.get(i));
			String foo = food.get(i);
			String[] pro;
			String s = "";
			pro = foo.split("_");
			for (int in = 0; in < pro.length; in++) {
				s = s + pro[in] + " ";
			}
			list.add(get(s));
		}
		entry.close();
		// Initially select one of the items
		return list;
	}

	private RatingModel get(String food) {
		return new RatingModel(food);
	}

	public void updateorders(String position, float userrating) {

		if (RatingFragment.fd.size() != 0) {
			for (k = 0; k < RatingFragment.fd.size(); k++) {
				if (!RatingFragment.fd.contains(position)) {
					RatingFragment.fd.add(position);
					RatingFragment.rt.add(Float.toString(userrating));
				} else {
					int loc = RatingFragment.fd.indexOf(position);
					RatingFragment.rt.add(loc, Float.toString(userrating));
				}
			}
		} else {
			RatingFragment.fd.add(position);
			RatingFragment.rt.add(Float.toString(userrating));
		}

		
		DatabaseOpenHelper entry = new DatabaseOpenHelper(getActivity());

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
		System.out.println(position);

		entry.open();
		int id = entry.getId(position);
		System.out.println(id);
		cat = entry.getCat(id);
		order = entry.getOrders(id);
		rating = entry.getRating(id);
		price = entry.getPrice(id);
		table1 = entry.getTable(id);
		nof = entry.getNof(id);
		// System.out.println(cat); //System.out.println(order);
		// System.out.println(rating); //System.out.println(price);
		System.out.println(table1);
		pos = -1;
		splited = table1.split("\\s+");
		for (int i = 0; i < splited.length; i++) {
			// System.out.println(splited[i]); no =
			// Integer.parseInt(splited[i]);
			if (no == Integer.parseInt(RatingFragment.table)) {
				pos = i;
				break;
			}
		}
		table1 = "";
		for (int i = 0; i < splited.length; i++) {
			if (i != pos) {
				table1 += splited[i] + " ";
			}
		}

		int or = Integer.parseInt(order);
		or += 1;
		float r = Float.parseFloat(rating);
		r = (r + userrating) / or;
		rating = String.format("%.2f", r);
		order = Integer.toString(or);
		entry.updateNewUser(id, cat, position, price, table1, order, rating,
				nof);
		entry.close();
		

	}

	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		super.onCreateOptionsMenu(menu, inflater);
		// TODO Add your menu entries here
		inflater.inflate(R.menu.rate, menu);

	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		switch (item.getItemId()) {
		case R.id.confirmrate:
			
			DatabaseOpenHelper entry = new DatabaseOpenHelper(getActivity());

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
			//System.out.println(position);

			entry.open();
			for(k=0; k<RatingFragment.fd.size(); k++)
			{
			int id = entry.getId(RatingFragment.fd.get(k));
			System.out.println(id);
			cat = entry.getCat(id);
			order = entry.getOrders(id);
			rating = entry.getRating(id);
			price = entry.getPrice(id);
			table1 = entry.getTable(id);
			nof = entry.getNof(id);
			RatingFragment.preorder.add(id);
			// System.out.println(cat); //System.out.println(order);
			// System.out.println(rating); //System.out.println(price);
			System.out.println(table1);
			pos = -1;
			splited = table1.split("\\s+");
			for (int i = 0; i < splited.length; i++) {
				// System.out.println(splited[i]); 
				no = Integer.parseInt(splited[i]);
				if (no == Integer.parseInt(RatingFragment.table)) {
					pos = i;
					break;
				}
			}
			table1 = "";
			for (int i = 0; i < splited.length; i++) {
				if (i != pos) {
					table1 += splited[i] + " ";
				}
			}

			int or = Integer.parseInt(order);
			or += 1;
			float r = Float.parseFloat(rating);
			r = (r + Float.parseFloat(RatingFragment.rt.get(k))) / or;
			rating = String.format("%.2f", r);
			order = Integer.toString(or);
			entry.updateNewUser(id, cat, RatingFragment.fd.get(k), price, table1, order, rating,
					nof);
			}
			entry.close();
			

			Toast.makeText(getActivity(), "Thank You For Rating",
					Toast.LENGTH_SHORT).show();
			db = new LoginDatabase(getActivity());
			db.open();
			String eatcount = db.getCount(Login.mail);
			int c = Integer.parseInt(eatcount);
			c = c + 1;
			String s ="";
			for(k=0; k<RatingFragment.preorder.size(); k++)
				s = RatingFragment.preorder.get(k) + " " + s;
			
			eatcount = Integer.toString(c);
			db.updateCount(eatcount, Login.mail);
			db.updateFood(s, Login.mail);
			System.out.println("adasdadasdasd "+ s);
			db.close();
			Intent i = new Intent(getActivity(), Login.class);
			startActivity(i);
			return true;
		}
		return false;

	}
	
	@Override
	public void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		getActivity().finish();
	}

}
*/
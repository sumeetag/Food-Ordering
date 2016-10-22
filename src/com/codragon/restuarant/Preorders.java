/*package com.codragon.restuarant;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.app.ListActivity;
import android.database.SQLException;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

public class Preorders extends ListActivity {

	// List view
	private ListView lv;
	int no;
	// Listview Adapter
	ArrayAdapter<Model> adapter;

	// Search EditText
	EditText inputSearch;
	ArrayList<String> products;
	ArrayList<String> product;
	String s = "";
	// ArrayList for Listview
	ArrayList<HashMap<String, String>> productList;

	Model[] modelItems = null;
	String table_no, food, order, rating, price, nof, serves;
	Button see_order;

	private LoginDatabase db;

	private String[] splited;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.preorder);

		

		// lv = (ListView) findViewById(R.id.listView1);
		adapter = new CustomAdapter(this, getModel());
		setListAdapter(adapter);

		
	}

	private List<Model> getModel() {
		List<Model> list = new ArrayList<Model>();
		DatabaseOpenHelper entry = new DatabaseOpenHelper(this);

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
		db = new LoginDatabase(this);
		db.open();
		String pre = db.getPre(Login.mail);
		db.close();
		splited = pre.split("\\s+");
		for (int i = 0; i < splited.length; i++) {
			// System.out.println(splited[i]);
			no = Integer.parseInt(splited[i]);
			food = entry.getFood(no);
			order = entry.getOrders(no);
			rating = entry.getRating(no);
			price = entry.getPrice(no);
			nof = entry.getNof(no);
			serves = entry.getServes(no);
			String[] pro;
			pro = food.split("_");
			for (int in = 0; in < pro.length; in++) {
				s = s + pro[in] + " ";
			}

			list.add(get(s,price));
			s = "";
		}
		entry.close();
		// Initially select one of the items
		// list.get(0).setSelected(true);
		return list;
	}

	private Model get(String food, String price) {
		return new Model(food, price);
	}

}*/
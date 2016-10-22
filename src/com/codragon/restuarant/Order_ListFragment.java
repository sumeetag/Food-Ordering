package com.codragon.restuarant;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.database.SQLException;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

public class Order_ListFragment extends ListFragment {
	ArrayList<String> listItems = new ArrayList<String>();
	Button confirm, add;
	ListView lv;
	String table_no, t;
	String nof, price;
	String[] splited;
	int net, q;
	ArrayList<String> food = new ArrayList<String>();
	int table, pos, no;
	TextView tv;

	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// ArrayAdapter<String> adapter = new ArrayAdapter<String>(
		// inflater.getContext(), android.R.layout.simple_list_item_1,
		// numbers_text);
		View v = inflater.inflate(R.layout.order_listview, container, false);
		

		return v;
	}

	@Override
	public void onActivityCreated(Bundle b) {
		setHasOptionsMenu(true);
		ArrayAdapter<OrderModel> adapter = new OrderCustomAdapter(
				getActivity(), getModel());
		setListAdapter(adapter);
		super.onActivityCreated(b);
	}

	private List<OrderModel> getModel() {
		List<OrderModel> list = new ArrayList<OrderModel>();
		MainDatabaseOpenHelper entry = new MainDatabaseOpenHelper(getActivity());

		

		try {

			entry.open();

		} catch (SQLException sqle) {

			throw sqle;

		}

		entry.open();
		food = entry.getFood1("0");
		net = 0;
		q = 0;
		System.out.println("size "+food.size());
		for (int i = 0; i < food.size(); i++) {
			System.out.println(food.get(i));
			int id = entry.getId(food.get(i));
			nof = entry.getOrder(id);
			price = entry.getPrice1(id);
			int total = Integer.parseInt(nof) * Integer.parseInt(price);
			net += total;
			q += Integer.parseInt(nof);
			
			//if (Integer.parseInt(nof) >= 1) {
				list.add(get(food.get(i), Integer.toString(total), nof));
		//	} else {
				/*
				t = entry.getTable(id);
				// System.out.println(t);
				splited = t.split("\\s+");
				for (int j = 0; j < splited.length; j++) {
					// System.out.println(splited[j]);
					no = Integer.parseInt(splited[j]);
					if (no == Integer.parseInt(FoodList.table)) {
						pos = j;
						break;
					}
				}
				t = "";
				for (int j = 0; j < splited.length; j++) {
					if (j != pos) {
						t += splited[j] + " ";
					}
				}
				// System.out.println(t);

				entry.updateTable(id, t);
		//	}
		 * 
		 */
		}
		entry.close();
		list.add(get("Grand Total: ", Integer.toString(net),
				Integer.toString(q)));
		// Initially select one of the items
		return list;
	}

	private OrderModel get(String food, String nof, String q) {
		return new OrderModel(food, nof, q);
	}

	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		super.onCreateOptionsMenu(menu, inflater);
		// TODO Add your menu entries here
		inflater.inflate(R.menu.menu, menu);
		
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		switch (item.getItemId()) {
		case R.id.confirm:
			
			
			MainDatabaseOpenHelper entry = new MainDatabaseOpenHelper(getActivity());

			

			try {

				entry.open();

			} catch (SQLException sqle) {

				throw sqle;

			}
			entry.open();
			
			//food = entry.getFood1(FoodList.table);
			
			for (int i = 0; i < food.size(); i++) {
				System.out.println(food.get(i));
				int id = entry.getId(food.get(i));
				entry.updateOrder(id, "0");
			}
			
			/*
			for (int i = 1; i <= 222; i++) {
				entry.updateNofUser(i, "0");
			}
			*/
			entry.close();
			Intent i = new Intent(getActivity(), Login.class);
			startActivity(i);
			return true;

		case R.id.add_order:
			getActivity().finish();
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

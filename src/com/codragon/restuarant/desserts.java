package com.codragon.restuarant;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import android.database.SQLException;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;

public class desserts extends ListFragment implements OnItemClickListener{
	ListView lv;
	Model[] modelItems = null;
	String table_no, food, order, rating, price, nof, serves;
	Button see_order;
	String s;

	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View rootView = inflater.inflate(R.layout.food_list, container, false);
		CustomAdapter adapter = new CustomAdapter(getActivity(),
		        getModel());
		    setListAdapter(adapter);
		    
		    //see_order = (Button)FindListener(R.id.button1);
			return rootView;
		
	}
	
	private List<Model> getModel() {
	    List<Model> list = new ArrayList<Model>();
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
		for (int i = 211; i <= 222; i++) {
			food = entry.getFood(i);
			order = entry.getOrders(i);
			rating = entry.getRating(i);
			price = entry.getPrice(i);
			nof = entry.getNof(i);
			serves = entry.getServes(i);
			String[] pro;
			if(i == 211){
				food.replace("null", "");
			}
			pro  = food.split("_");
			for(int in=0;in<pro.length;in++){
	        	s = s+ pro[in] + " ";
	        	}
			list.add(get(s, order, rating, price, nof, serves));
			s="";
		}
		entry.close();
	    // Initially select one of the items
	   // list.get(0).setSelected(true);
	    return list;
	  }

	  private Model get(String food, String orders, String rating, String price, String nof, String serves) {
	    return new Model(food,price);
	  }
	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		// TODO Auto-generated method stub
		
	}
	
}
package com.codragon.restuarant;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.database.SQLException;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.Toast;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.ExpandableListView.OnGroupClickListener;
import android.widget.ExpandableListView.OnGroupCollapseListener;
import android.widget.ExpandableListView.OnGroupExpandListener;

public class sides extends ListFragment {
	MyExpandableListAdapter listAdapter;
    ExpandableListView expListView;
    List<String> listDataHeader;
    HashMap<String, List<String>> listDataChild;
    String table_no, food, order, rating, price, serves;
    ArrayList<String> check;
    int k;
    int lastExpandedGroupPosition;
	private int width;


    @Override
     public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
     View v = inflater.inflate(R.layout.expandable, container, false);
     expListView = (ExpandableListView) v.findViewById(android.R.id.list);
     
     DisplayMetrics metrics = new DisplayMetrics();
		getActivity().getWindowManager().getDefaultDisplay().getMetrics(metrics);
		width = metrics.widthPixels;
		expListView.setIndicatorBounds(width - (150), width
				- (10));

        prepareListData();       

     // preparing list data
    // 

      listAdapter = new MyExpandableListAdapter(getActivity(), listDataHeader, listDataChild);

     // setting list adapter
     expListView.setAdapter(listAdapter);

     // Listview Group expanded listener
     expListView.setOnGroupExpandListener(new OnGroupExpandListener() {

         @Override
         public void onGroupExpand(int groupPosition) {
        	 
        	
			if (groupPosition != lastExpandedGroupPosition) {
					expListView.collapseGroup(lastExpandedGroupPosition);
				}

				// super.onGroupExpand(groupPosition);
				lastExpandedGroupPosition = groupPosition;
        	 /*
        	 if(listDataHeader.get(groupPosition).equalsIgnoreCase("veg")){
        		 k = 3;
        	 }else{
        		 k = 2;
        	 }
        	 
        	 for(int i=0; i<k; i++){
        		 String s = listDataChild.get(listDataHeader.get(groupPosition)).get(i);
        		 String[] splited = s.split("\\s+");
        		 check.add(splited[0]);
        		 
        	 }
        	 
        	 
        	 
             Toast.makeText(getActivity(),
                     listDataHeader.get(groupPosition) + check,
                     Toast.LENGTH_SHORT).show();
                     */
         }
     });
     
     expListView.setOnGroupClickListener(new OnGroupClickListener() {

         @Override
         public boolean onGroupClick(ExpandableListView parent, View v,
                 int groupPosition, long id) {
             // Toast.makeText(getApplicationContext(),
             // "Group Clicked " + listDataHeader.get(groupPosition),
             // Toast.LENGTH_SHORT).show();
             return false;
         }
     });

     // Listview Group collasped listener
     expListView.setOnGroupCollapseListener(new OnGroupCollapseListener() {

         @Override
         public void onGroupCollapse(int groupPosition) {
             Toast.makeText(getActivity().getApplicationContext(),
                     listDataHeader.get(groupPosition) + " Collapsed",
                     Toast.LENGTH_SHORT).show();

         }
     });

     // Listview on child click listener
     expListView.setOnChildClickListener(new OnChildClickListener() {

         @Override
         public boolean onChildClick(ExpandableListView parent, View v,
                 int groupPosition, int childPosition, long id) {
             // TODO Auto-generated method stub
        	 String s = listDataChild.get(listDataHeader.get(groupPosition)).get(
                     childPosition);
        	 String[] splited = s.split("\\s+");
             Toast.makeText(
                     getActivity().getApplicationContext(),
                     listDataHeader.get(groupPosition) + " : "+ splited[0], Toast.LENGTH_SHORT).show();


             return false;
         }
     });



     return v;
     }


    private void prepareListData() {
        // TODO Auto-generated method stub
        listDataHeader = new ArrayList<String>();
        listDataChild = new HashMap<String, List<String>>();

        // Adding child data
        listDataHeader.add("Raitha's");
        listDataHeader.add("Salad's");
        listDataHeader.add("Padad");
        
        
        //listDataHeader.add("Set Alarm Tone");
        
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
		List<String> raitha = new ArrayList<String>();
		for (int i = 197; i <= 201; i++) {
			food = entry.getFood(i);
			price = entry.getPrice(i);
			order = entry.getOrders(i);
			rating = entry.getRating(i);
			serves = entry.getServes(i);
			raitha.add(food + " " + price + " " + order + " " + rating+ " " + serves);
		}
		
		List<String> salad = new ArrayList<String>();
		for (int i = 202; i <= 206; i++) {
			food = entry.getFood(i);
			price = entry.getPrice(i);
			order = entry.getOrders(i);
			rating = entry.getRating(i);
			serves = entry.getServes(i);
			salad.add(food + " " + price + " " + order + " " + rating+ " " + serves);
		}
		
		List<String> padad = new ArrayList<String>();
		for (int i = 207; i <= 210; i++) {
			food = entry.getFood(i);
			price = entry.getPrice(i);
			order = entry.getOrders(i);
			rating = entry.getRating(i);
			serves = entry.getServes(i);
			padad.add(food + " " + price + " " + order + " " + rating+ " " + serves);
		}
		
		entry.close();
        // Adding child data

        listDataChild.put(listDataHeader.get(0), raitha); // Header, Child data
        listDataChild.put(listDataHeader.get(1), salad);
        listDataChild.put(listDataHeader.get(2), padad);
        
        //listDataChild.put(listDataHeader.get(2), comingSoon);
    }
    
}

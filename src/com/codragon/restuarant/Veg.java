package com.codragon.restuarant;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.ExpandableListView.OnGroupClickListener;
import android.widget.ExpandableListView.OnGroupCollapseListener;
import android.widget.ExpandableListView.OnGroupExpandListener;
import android.widget.Toast;

public class Veg extends ListFragment {

	MyExpandableListAdapter listAdapter;
	ExpandableListView expListView;
	List<String> listDataHeader;
	HashMap<String, List<String>> listDataChild;
	String table_no, food, order, rating, price, serves;
	ArrayList<String> check;
	ArrayList<Integer> smid = new ArrayList<Integer>();
	ArrayList<String> foodarray = new ArrayList<String>();
	ArrayList<String> pricearray = new ArrayList<String>();
	int lastExpandedGroupPosition;
	int k;
	int menu_pos;
	private float pixels;
	private int width = 15;
	private ArrayList<String> newfoodarray;

	public Veg(int index) {
		// TODO Auto-generated constructor stub
		menu_pos = index+1;
		System.out.println("veg "+ menu_pos);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
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

		listAdapter = new MyExpandableListAdapter(getActivity(),
				listDataHeader, listDataChild);

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
				/*
				 * Toast.makeText(getActivity().getApplicationContext(),
				 * listDataHeader.get(groupPosition) + " Collapsed",
				 * Toast.LENGTH_SHORT).show();
				 */
			}
		});

		// Listview on child click listener
		expListView.setOnChildClickListener(new OnChildClickListener() {

			@Override
			public boolean onChildClick(ExpandableListView parent, View v,
					int groupPosition, int childPosition, long id) {
				// TODO Auto-generated method stub
				String s = listDataChild.get(listDataHeader.get(groupPosition))
						.get(childPosition);
				String[] splited = s.split("\\s+");
				Toast.makeText(getActivity().getApplicationContext(),
						listDataHeader.get(groupPosition) + " : " + splited[0],
						Toast.LENGTH_SHORT).show();

				return false;
			}
		});

		return v;
	}

	private int GetDipsFromPixel(int i) {
		// TODO Auto-generated method stub

		// Get the screen's density scale
		final float scale = getResources().getDisplayMetrics().density;
		// Convert the dps to pixels, based on density scale
		return (int) (pixels * scale + 0.5f);

	}

	private void prepareListData() {
		// TODO Auto-generated method stub
		listDataHeader = new ArrayList<String>();
		listDataChild = new HashMap<String, List<String>>();
		MenuDatabase md = new MenuDatabase(getActivity());
		md.open();
		int mid = md.getMid(menu_pos);
		md.close();
		MainDatabaseOpenHelper mdh = new MainDatabaseOpenHelper(getActivity());
		mdh.open();
		smid = mdh.getsmid(mid);
		System.out.println("veg smid "+ smid);
		SubMenuDatabase smd = new SubMenuDatabase(getActivity());
		smd.open();
		for(int i = 0; i<smid.size(); i++){
		// Adding child data
			foodarray = new ArrayList<String>();
			pricearray = new ArrayList<String>();
			String s = smd.getName(smid.get(i));
			System.out.println("veg smid food "+ s);
		listDataHeader.add(s);
		foodarray = mdh.getFood(smid.get(i));
		pricearray = mdh.getPrice(smid.get(i));
		//System.out.println("veg food array "+ foodarray);
		//System.out.println("veg price array "+ pricearray);
		List<String> name = new ArrayList<String>(); 
		newfoodarray = new ArrayList<String>(); 
		for (int j = 0; j < foodarray.size(); j++) {
			
			 newfoodarray.add(foodarray.get(j).replace(" ", "_"));
			// System.out.println("veg food array  space "+ newfoodarray);
			name.add(newfoodarray.get(j) +" " +pricearray.get(j) );
		}
		listDataChild.put(listDataHeader.get(i), name);
		
		}
		mdh.close();
		smd.close();
		
		// listDataChild.put(listDataHeader.get(2), comingSoon);
	}

}

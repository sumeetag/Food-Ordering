package com.codragon.restuarant;


import java.util.HashMap;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.database.SQLException;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MyExpandableListAdapter extends BaseExpandableListAdapter {

	private Activity _context;
	private List<String> _listDataHeader; // header titles
	// child data in format of header title, child title
	private HashMap<String, List<String>> _listDataChild;
	TextView food, price;
	CheckBox cb;
	ImageView iv;
	String s;
	String[] splited;
	protected Button add, sub;
	String fontPath = "fonts/Face Your Fears.ttf";

	public MyExpandableListAdapter(Activity context, List<String> listDataHeader,
			HashMap<String, List<String>> listChildData) {
		this._context = context;
		this._listDataHeader = listDataHeader;
		this._listDataChild = listChildData;
	}
	
	static class ViewHolder {
	    protected TextView food, price;
	    protected CheckBox checkbox;
	    protected ImageView veg;
	    protected Button add, sub;
	    
	    
	  }

	@Override
	public Object getChild(int groupPosition, int childPosititon) {
		return this._listDataChild.get(this._listDataHeader.get(groupPosition))
				.get(childPosititon);
	}

	@Override
	public long getChildId(int groupPosition, int childPosition) {
		return childPosition;
	}

	@Override
	public View getChildView(final int groupPosition, final int childPosition,
			boolean isLastChild, View convertView, ViewGroup parent) {

		final String childText = getChild(groupPosition, childPosition).toString();
		String name1 = _listDataChild.get(_listDataHeader.get(groupPosition)).get(
                childPosition);
		System.out.println("qqqqqqq"+ childText);
		
//		if (convertView == null) {
			LayoutInflater infalInflater = (LayoutInflater) this._context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = infalInflater.inflate(R.layout.list_item, null);
			
			
			//Typeface tf = Typeface.createFromAsset(_context.getAssets() , fontPath);
			food = (TextView) convertView
					.findViewById(R.id.textView1);
			//food.setTypeface(tf);
			
			
			price = (TextView) convertView
					.findViewById(R.id.price);
			
			
			
			iv = (ImageView) convertView
					.findViewById(R.id.imageView1);
			
			add = (Button) convertView.findViewById(R.id.addfood);
		    sub = (Button) convertView.findViewById(R.id.subfood);
		    
			
			cb = (CheckBox) convertView
					.findViewById(R.id.checkBox1);
			//cb.setButtonDrawable(R.drawable.checkbox_unchecked);
			
//		}else{
			
			cb.setOnCheckedChangeListener(null);
			
//		}

		
	    add.setOnClickListener( new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stuboas
				//Toast.makeText(context, "You have selected", Toast.LENGTH_SHORT).show();
				
				FoodList no_of_orders = new FoodList();
				String split = _listDataChild.get(_listDataHeader.get(groupPosition)).get(
                        childPosition);
				System.out.println("asdasad"+ split);
        		String[] splited1 = childText.split("\\s+");
        		System.out.println("asdasad"+ splited1[0].replace("_", " "));
				no_of_orders.nof(splited1[0].replace("_", " "), _context, cb);
				
			}
		});
	      sub.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				FoodList no_of_orders = new FoodList();
				String split = _listDataChild.get(_listDataHeader.get(groupPosition)).get(
                        childPosition);
        		String[] splited1 = childText.split("\\s+");
				no_of_orders.nofnew(splited1[0].replace("_", " "), _context);
			}
		});
		
		
		String name =_listDataChild.get(_listDataHeader.get(groupPosition)).get(
                childPosition);
		System.out.println("name"+ name);
		String[] splited1 = childText.split("\\s+");
		System.out.println(splited1[0]);
		MainDatabaseOpenHelper entry = new MainDatabaseOpenHelper(_context);

		

		try {

			entry.open();

		} catch (SQLException sqle) {

			throw sqle;

		}

		entry.open();
		
			String cat = entry.getCat(splited1[0].replace("_", " "));
			System.out.println(cat);
			if(cat.equalsIgnoreCase("0")){
				iv.setImageResource(R.drawable.veg);
			}else{
				iv.setImageResource(R.drawable.nonveg);
			}
		
		entry.close();
		
		
		splited = name1.split("\\s+");
		
		cb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			 
            @Override
            public void onCheckedChanged(CompoundButton buttonView,
                boolean isChecked) {
            	if(isChecked){
            		//cb.setButtonDrawable(R.drawable.checkbox_checked_icon);
            		//cb.setButtonDrawable(R.drawable.checked);
            		Toast.makeText(_context, "Pls select the Quantity", Toast.LENGTH_SHORT).show();
            		FoodList fl = new FoodList();
            		String split = _listDataChild.get(_listDataHeader.get(groupPosition)).get(
                            childPosition);
            		String[] splited1 = childText.split("\\s+");
            		fl.name(splited1[0].replace("_", " "),_context);
            	}else if(!isChecked){
            		//cb.setButtonDrawable(R.drawable.checkbox_unchecked);
            		//cb.setButtonDrawable(R.drawable.unchecked);
            		String split = _listDataChild.get(_listDataHeader.get(groupPosition)).get(
                            childPosition);
            		String[] splited1 = childText.split("\\s+");
            		FoodList f2 = new FoodList();
            		//System.out.println(splited1[0]);
            		f2.notname(splited1[0].replace("_", " "),_context);
            		//System.out.println(list.get(position).getFood());
            	}
  	              //cb.getTag().setSelected(buttonView.isChecked());
            }
		});
		
		//cb.setTag(_listDataChild.get(_listDataHeader.get(groupPosition)).get(
        //        childPosition));
		
		//(convertView.getTag()).cb.setTag(_listDataChild.get(_listDataHeader.get(groupPosition)).get(
         //       childPosition));
		
		String[] pro;
		
		pro  = splited[0].split("_");
		s = "";
		//System.out.println("dfds"+pro);
		//System.out.println(s);
		for(int in=0;in<pro.length;in++){
        	s = s+ pro[in] + " ";
        	//System.out.println(s);
        	}
        
		food.setText(s);
		s= "";
		price.setText(splited[1]);
	
		
		return convertView;
	}

	@Override
	public int getChildrenCount(int groupPosition) {
		return this._listDataChild.get(this._listDataHeader.get(groupPosition))
				.size();
	}

	@Override
	public Object getGroup(int groupPosition) {
		return this._listDataHeader.get(groupPosition);
	}

	@Override
	public int getGroupCount() {
		return this._listDataHeader.size();
	}

	@Override
	public long getGroupId(int groupPosition) {
		return groupPosition;
	}

	@Override
	public View getGroupView(int groupPosition, boolean isExpanded,
			View convertView, ViewGroup parent) {
		String headerTitle = (String) getGroup(groupPosition);
		if (convertView == null) {
			LayoutInflater infalInflater = (LayoutInflater) this._context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = infalInflater.inflate(R.layout.list_group, null);
		}

		TextView lblListHeader = (TextView) convertView
				.findViewById(R.id.lblListHeader);
		lblListHeader.setTypeface(null, Typeface.BOLD);
		lblListHeader.setText(headerTitle);

		return convertView;
	}

	@Override
	public boolean hasStableIds() {
		return false;
	}

	@Override
	public boolean isChildSelectable(int groupPosition, int childPosition) {
		return true;
	}

}

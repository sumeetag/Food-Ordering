package com.codragon.restuarant;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.app.ListActivity;
import android.database.SQLException;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

public class Search extends ListActivity {
	
	 // List view
    private ListView lv;
     
    // Listview Adapter
    ArrayAdapter<Model> adapter;
     
    // Search EditText
    EditText inputSearch;
    ArrayList<String>products;
    ArrayList<String>product;
     String s="";
    // ArrayList for Listview
    ArrayList<HashMap<String, String>> productList;
    
	Model[] modelItems = null;
	String table_no, food, order, rating, price, nof, serves;
	Button see_order;
    
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.search_list);

		//see_order = (Button) findViewById(R.id.see_order);
		//see_order.setOnClickListener(this);
		inputSearch = (EditText) findViewById(R.id.inputSearch);
		//Bundle gotBasket = getIntent().getExtras();
		//table_no = gotBasket.getString("key");

		//lv = (ListView) findViewById(R.id.listView1);
		adapter = new CustomAdapter(this,
		        getModel());
		    setListAdapter(adapter);
		    
		    inputSearch.addTextChangedListener(new TextWatcher() {
	             
	            @Override
	            public void onTextChanged(CharSequence cs, int arg1, int arg2, int arg3) {
	                // When user changed the Text
	            	System.out.println(cs);
	            	 ( adapter.getFilter()).filter(cs.toString()); 
	            }
	             
	            @Override
	            public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,
	                    int arg3) {
	                // TODO Auto-generated method stub
	                 
	            }
	             
	            @Override
	            public void afterTextChanged(Editable arg0) {
	                // TODO Auto-generated method stub                          
	            }
	        });
	}
	
	private List<Model> getModel() {
	    List<Model> list = new ArrayList<Model>();
	    MainDatabaseOpenHelper entry = new MainDatabaseOpenHelper(this);
		
	   

		try {

			entry.open();

		} catch (SQLException sqle) {

			throw sqle;

		}
		
		entry.open();
		for (int i = 1; i <= entry.getUserDataCount(); i++) {
			food = entry.getName1(i);
			price = entry.getPrice1(i);
			String[] pro;
			pro  = food.split("_");
			for(int in=0;in<pro.length;in++){
	        	s = s+ pro[in] + " ";
	        	}
	        
			list.add(get(s,price));
			s = "";
		}
		entry.close();
	    // Initially select one of the items
	   // list.get(0).setSelected(true);
	    return list;
	  }

	  private Model get(String food,String price) {
	    return new Model(food,price);
	  }
	
	
}
	
	
	
	
	
	
	
	
	
	
	
	
	
	/*
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_list);
        
        product = new ArrayList<String>();
        products = new ArrayList<String>();
        // Listview Data
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
		
		for(int i=1; i<=222; i++){
			product.add(entry.getFood(i));
		}
		
		entry.close();
        String pro[];
        for(int j = 0; j<product.size(); j++){
        pro  = product.get(j).split("_");
        for(int i=0;i<pro.length;i++){
        	s = s+ pro[i] + " ";
        	}
        products.add(s);
        s = "";
        }
        
         
        lv = (ListView) findViewById(android.R.id.list);
        inputSearch = (EditText) findViewById(R.id.inputSearch);
         
        // Adding items to listview
        adapter = new ArrayAdapter<String>(this, R.layout.search, R.id.product_name, products);
        lv.setAdapter(adapter);
         
        /**
         * Enabling Search Filter
         * *
        inputSearch.addTextChangedListener(new TextWatcher() {
             
            @Override
            public void onTextChanged(CharSequence cs, int arg1, int arg2, int arg3) {
                // When user changed the Text
                Search.this.adapter.getFilter().filter(cs);   
            	
            }
             
            @Override
            public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,
                    int arg3) {
                // TODO Auto-generated method stub
                 
            }
             
            @Override
            public void afterTextChanged(Editable arg0) {
                // TODO Auto-generated method stub                          
            }
        });
    }    

}
*/


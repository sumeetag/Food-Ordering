package com.codragon.restuarant;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

public class AllProductsActivity extends Activity {

	// Progress Dialog
	private ProgressDialog pDialog;

	// Creating JSON Parser object
	JSONParser jParser = new JSONParser();

	ArrayList<String> food;
	ArrayList<String> menu;
	ArrayList<String> submenu;
	ArrayList<Integer> fsmid;
	ArrayList<Integer> smid;
	ArrayList<Integer> fmid;
	ArrayList<Integer> mid;
	ArrayList<Integer> food_id;
	ArrayList<String> price;
	ArrayList<Integer> type;
	ArrayList<String> ingre;
	
	

	// url to get all products list
	private static String url_all_products = "http://mathrandom.esy.es/sumeet_php_connect/getOlive.php";
	private static String url_main_menu = "http://mathrandom.esy.es/sumeet_php_connect/getmenuname.php";
	private static String url_sub_main_menu = "http://mathrandom.esy.es/sumeet_php_connect/submenuname.php";

	// JSON Node names
	private static final String TAG_SUCCESS = "success";
	private static final String TAG_PRODUCTS = "pari";
	private static final String TAG_PID = "item_id";
	private static final String TAG_NAME = "food";
	private static final String TAG_PRICE = "price";
	private static final String TAG_MID = "mid";
	private static final String TAG_SMID = "smid";
	private static final String TAG_TYPE = "type";
	private static final String TAG_INGRE = "ingredient";
	private static final String TAG_MENUNAME = "name";
	private static final String TAG_SUBMENUNAME = "name";

	// products JSONArray
	JSONArray products = null;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// setContentView(R.layout.disp);
		
		food = new ArrayList<String>();
		menu = new ArrayList<String>();
		submenu = new ArrayList<String>();
		fsmid = new ArrayList<Integer>();
		smid = new ArrayList<Integer>();
		fmid = new ArrayList<Integer>();
		mid = new ArrayList<Integer>();
		food_id = new ArrayList<Integer>();
		price = new ArrayList<String>();
		type = new ArrayList<Integer>();
		ingre = new ArrayList<String>();

		if (!isConnectingToInternet()) {
			
			// Internet Connection is not present
			showAlertDialog(AllProductsActivity.this,
					"Internet Connection Error",
					"Please connect to Internet connection", false);
			// stop executing code by return
			return;
		}
		
		// Loading products in Background Thread
		new LoadAllProducts().execute();

	}

	
	public boolean isConnectingToInternet(){
    	
        ConnectivityManager connectivity = 
        	                 (ConnectivityManager) getSystemService(
        	                  Context.CONNECTIVITY_SERVICE);
          if (connectivity != null)
          {
              NetworkInfo[] info = connectivity.getAllNetworkInfo();
              if (info != null)
                  for (int i = 0; i < info.length; i++)
                      if (info[i].getState() == NetworkInfo.State.CONNECTED)
                      {
                          return true;
                      }
 
          }
          return false;
    }
	
	
	@SuppressWarnings("deprecation")
	public void showAlertDialog(Context context, String title, String message,
			Boolean status) {
		AlertDialog alertDialog = new AlertDialog.Builder(context).create();

		// Set Dialog Title
		alertDialog.setTitle(title);

		// Set Dialog Message
		alertDialog.setMessage(message);

		if(status != null)
			// Set alert dialog icon
			alertDialog.setIcon((status) ? R.drawable.success : R.drawable.fail);

		// Set OK Button
		alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				
			}
		});

		// Show Alert Message
		alertDialog.show();
	}
	// Response from Edit Product Activity
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		// if result code 100
		if (resultCode == 100) {
			Intent intent = getIntent();
			finish();
			startActivity(intent);
		}

	}

	/**
	 * Background Async Task to Load all product by making HTTP Request
	 * */
	class LoadAllProducts extends AsyncTask<String, String, String> {

		/**
		 * Before starting background thread Show Progress Dialog
		 * */
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			pDialog = new ProgressDialog(AllProductsActivity.this);
			pDialog.setMessage("Loading products. Please wait...");
			pDialog.setIndeterminate(false);
			pDialog.setCancelable(false);
			pDialog.show();
		}

		/**
		 * getting All products from url
		 * */
		protected String doInBackground(String... args) {
			// Building Parameters

			olive();
			

			return null;
		}

		/**
		 * After completing background task Dismiss the progress dialog
		 * **/
		protected void onPostExecute(String file_url) {
			// dismiss the dialog after getting all products
			pDialog.dismiss();
			// updating UI from Background Thread
			runOnUiThread(new Runnable() {
				public void run() {
					MainDatabaseOpenHelper mdo = new MainDatabaseOpenHelper(getApplication());
					mdo.open();
					for(int i = 0; i<food.size();i++){
					
					mdo.createEntry(fmid.get(i), fsmid.get(i), type.get(i), food.get(i), ingre.get(i),
							price.get(i));
					}
					mdo.close();
					
					SubMenuDatabase sm = new SubMenuDatabase(getApplicationContext());
					sm.open();
					for(int i = 0; i<smid.size();i++){
					sm.createEntry(submenu.get(i), smid.get(i));
					
					}
					sm.close();
					
					MenuDatabase md = new MenuDatabase(getApplicationContext());
					md.open();
					for(int i = 0; i<mid.size();i++){
					md.createEntry(menu.get(i), mid.get(i));
					
					}
					md.close();
					
					Intent i = new Intent(getApplicationContext(), FoodList.class);
					finish();
					startActivity(i);
					
					System.out.println("aeqqe"+submenu.size());

				}
			});

		}

		public void olive() {
			// TODO Auto-generated method stub
			List<NameValuePair> params = new ArrayList<NameValuePair>();
			// getting JSON string from URL
			JSONObject json = jParser.makeHttpRequest(url_all_products, "GET",
					params);

			// Check your log cat for JSON reponse
			//Log.d("All Products: ", json.toString());
			food = new ArrayList<String>();
			
			fsmid = new ArrayList<Integer>();
			
			fmid = new ArrayList<Integer>();
			
			food_id = new ArrayList<Integer>();
			price = new ArrayList<String>();
			type = new ArrayList<Integer>();
			ingre = new ArrayList<String>();

			try {
				// Checking for SUCCESS TAG
				int success = json.getInt(TAG_SUCCESS);

				System.out.println(success);
				if (success == 1) {
					// products found
					// Getting Array of Products
					products = json.getJSONArray(TAG_PRODUCTS);
					//System.out.println(products);
						
					System.out.println("SADASDSDASDS"+products.length());
					
					// looping through All Products
					for (int i = 0; i < products.length(); i++) {
						JSONObject c = products.getJSONObject(i);

						// Storing each json item in variable
						food_id.add(c.getInt(TAG_PID));
						food.add(c.getString(TAG_NAME));
						price.add(Integer.toString(c.getInt(TAG_PRICE)));
						fmid.add(c.getInt(TAG_MID));
						fsmid.add(c.getInt(TAG_SMID));
						type.add(c.getInt(TAG_TYPE));
						ingre.add(c.getString(TAG_INGRE));
						
						//System.out.println("food_id "+food_id);
						
					}
					
					//
				} else {

					Toast.makeText(getApplicationContext(), "No data found",
							Toast.LENGTH_SHORT).show();
				}
			} catch (JSONException e) {
				e.printStackTrace();
			}

			menuname();

		}

		public void submenuname() {
			// TODO Auto-generated method stub
			List<NameValuePair> params = new ArrayList<NameValuePair>();
			// getting JSON string from URL
			JSONObject json = jParser.makeHttpRequest(url_sub_main_menu, "GET",
					params);
			
			
			submenu = new ArrayList<String>();
			
			smid = new ArrayList<Integer>();
			

			// Check your log cat for JSON reponse
			Log.d("All Products: ", json.toString());

			try {
				// Checking for SUCCESS TAG
				int success = json.getInt(TAG_SUCCESS);

				System.out.println(success);
				if (success == 1) {
					// products found
					// Getting Array of Products
					products = json.getJSONArray(TAG_PRODUCTS);
					System.out.println(products);

					// looping through All Products
					for (int i = 0; i < products.length(); i++) {
						JSONObject c = products.getJSONObject(i);

						// Storing each json item in variable
						smid.add(c.getInt(TAG_SMID));
						submenu.add(c.getString(TAG_SUBMENUNAME));
						
						//System.out.println("smid "+smid);
					
					}
					
					//System.out.println("Sdsdsd"+food_id.size());
				} else {

					Toast.makeText(getApplicationContext(), "No data found",
							Toast.LENGTH_SHORT).show();
				}
			} catch (JSONException e) {
				e.printStackTrace();
			}

		}

		public void menuname() {
			// TODO Auto-generated method stub
			List<NameValuePair> params = new ArrayList<NameValuePair>();
			// getting JSON string from URL
			JSONObject json = jParser.makeHttpRequest(url_main_menu, "GET",
					params);
			
			menu = new ArrayList<String>();
			
			mid = new ArrayList<Integer>();
			

			// Check your log cat for JSON reponse
			Log.d("All Products: ", json.toString());

			try {
				// Checking for SUCCESS TAG
				int success = json.getInt(TAG_SUCCESS);

				System.out.println(success);
				if (success == 1) {
					// products found
					// Getting Array of Products
					products = json.getJSONArray(TAG_PRODUCTS);
					System.out.println(products);

					// looping through All Products
					for (int i = 0; i < products.length(); i++) {
						JSONObject c = products.getJSONObject(i);

						// Storing each json item in variable
						mid.add(c.getInt(TAG_MID));
						menu.add(c.getString(TAG_MENUNAME));
						
						
						
					}
					
					//System.out.println("mid "+mid.size());

				} else {

					Toast.makeText(getApplicationContext(), "No data found",
							Toast.LENGTH_SHORT).show();
				}
			} catch (JSONException e) {
				e.printStackTrace();
			}
			submenuname();
		}

	}

}
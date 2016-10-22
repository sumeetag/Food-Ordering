package com.codragon.restuarant;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;

public class GridViewExample extends Activity {

	GridView gridView;
    Controller aController = null;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.grid_view_android_example); 

		//Get gridview object from xml file
		gridView = (GridView) findViewById(R.id.gridView1);

		// Get Global variable instance
	    aController = (Controller) getApplicationContext();
		
		// WebServer Request URL to get All registered devices
        String serverURL = Config.YOUR_SERVER_URL+"userdata.php";
        
        // Use AsyncTask execute Method To Prevent ANR Problem
        LongOperation serverRequest = new LongOperation(); 
        
        serverRequest.execute(serverURL,"rrr","","");
		
        
       /*  // GET IMEI NUMBER      
		 TelephonyManager tManager = (TelephonyManager) getBaseContext()
		    .getSystemService(Context.TELEPHONY_SERVICE);
		 final String deviceIMEI = tManager.getDeviceId(); */
		 
		gridView.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View v,
					int position, long id) {
				
				String deviceIMEI = "";
				if(Config.SECOND_SIMULATOR){
					
					//Make it true in CONFIG if you want to open second simutor
					// for testing actually we are using IMEI number to save a unique device
					
					deviceIMEI = "000000000000001";
				}	
				else
				{
				  // GET IMEI NUMBER      
				 TelephonyManager tManager = (TelephonyManager) getBaseContext()
				    .getSystemService(Context.TELEPHONY_SERVICE);
				  deviceIMEI = tManager.getDeviceId(); 
				}
				
				String uIMEI = aController.getUserData(position).getIMEI();
				String uName = aController.getUserData(position).getName();
				
				
				
				// Launch Main Activity
				Intent i = new Intent(getApplicationContext(), SendPushNotification.class);
				
				// Registering user on our server					
				// Sending registraiton details to MainActivity
				i.putExtra("name", uName);
				i.putExtra("imei", uIMEI);  // Send to
				i.putExtra("sendfrom", deviceIMEI);
				startActivity(i);
				//finish();
				
				/*
				Toast.makeText(
						getApplicationContext(),
						((TextView) v.findViewById(R.id.grid_item_label))
								.getText(), Toast.LENGTH_SHORT).show();
                */
			}
		});

	}
	
	
	// Class with extends AsyncTask class
	public class LongOperation  extends AsyncTask<String, Void, String> {
	         
	    	// Required initialization
	    	
	        //private final HttpClient Client = new DefaultHttpClient();
	       // private Controller aController = null;
	        private String Error = null;
	        private ProgressDialog Dialog = new ProgressDialog(GridViewExample.this); 
	        String data =""; 
	        int sizeData = 0;  
	        
	        
	        protected void onPreExecute() {
	            // NOTE: You can call UI Element here.
	             
	            //Start Progress Dialog (Message)
	           
	            Dialog.setMessage("Getting registered users ..");
	            Dialog.show();
	            
	        }
	 
	        // Call after onPreExecute method
	        protected String doInBackground(String... params) {
	        	
	        	/************ Make Post Call To Web Server ***********/
	        	BufferedReader reader=null;
	        	String Content = "";
		             // Send data 
		            try{
		            	
		            	// Defined URL  where to send data
			               URL url = new URL(params[0]);
		            	
			            // Set Request parameter
			            if(!params[1].equals(""))
		               	   data +="&" + URLEncoder.encode("data", "UTF-8") + "="+params[1].toString();
			            if(!params[2].equals(""))
			               	   data +="&" + URLEncoder.encode("data2", "UTF-8") + "="+params[2].toString();	
			            if(!params[3].equals(""))
			               	   data +="&" + URLEncoder.encode("data3", "UTF-8") + "="+params[3].toString();
		              Log.i("GCM",data);
			            
			          // Send POST data request
		   
		              URLConnection conn = url.openConnection(); 
		              conn.setDoOutput(true); 
		              OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream()); 
		              wr.write( data ); 
		              wr.flush(); 
		          
		              // Get the server response 
		               
		              reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		              StringBuilder sb = new StringBuilder();
		              String line = null;
		            
			            // Read Server Response
			            while((line = reader.readLine()) != null)
			                {
			                       // Append server response in string
			                       sb.append(line + "\n");
			                }
		                
		                // Append Server Response To Content String 
		               Content = sb.toString();
		            }
		            catch(Exception ex)
		            {
		            	Error = ex.getMessage();
		            }
		            finally
		            {
		                try
		                {
		     
		                    reader.close();
		                }
		   
		                catch(Exception ex) {}
		            }
	        	
	            /*****************************************************/
	            return Content;
	        }
	         
	        protected void onPostExecute(String Content) {
	            // NOTE: You can call UI Element here.
	             
	            // Close progress dialog
	            Dialog.dismiss();
	            
	            if (Error != null) {
	                 
	                 
	            } else {
	              
	            	// Show Response Json On Screen (activity)
	            	
	             /****************** Start Parse Response JSON Data *************/
	            	aController.clearUserData();
	            	
	            	JSONObject jsonResponse;
	                      
	                try {
	                      
	                     /****** Creates a new JSONObject with name/value mappings from the JSON string. ********/
	                     jsonResponse = new JSONObject(Content);
	                      
	                     /***** Returns the value mapped by name if it exists and is a JSONArray. ***/
	                     /*******  Returns null otherwise.  *******/
	                     JSONArray jsonMainNode = jsonResponse.optJSONArray("Android");
	                      
	                     /*********** Process each JSON Node ************/
	  
	                     int lengthJsonArr = jsonMainNode.length();  
	  
	                     for(int i=0; i < lengthJsonArr; i++) 
	                     {
	                         /****** Get Object for each JSON node.***********/
	                         JSONObject jsonChildNode = jsonMainNode.getJSONObject(i);
	                          
	                         /******* Fetch node values **********/
	                         String IMEI       = jsonChildNode.optString("imei").toString();
	                         String Name       = jsonChildNode.optString("name").toString();
	                          
	                         Log.i("GCM","---"+Name);
	                         
	                         UserData userdata = new UserData();
	                         userdata.setIMEI(IMEI);
	                         userdata.setName(Name);
	                        
	                       //Add user data to controller class UserDataArr arraylist
	                         aController.setUserData(userdata);
	                         
	                    }
	                     
	                 /****************** End Parse Response JSON Data *************/
	                     
	                  //Add user data to controller class UserDataArr arraylist
	                  gridView.setAdapter(new CustomGridAdapter(getBaseContext(), aController));
	                    
	                      
	                 } catch (JSONException e) {
	          
	                     e.printStackTrace();
	                 }
	  
	                 
	             }
	        }
	         
	    }


}
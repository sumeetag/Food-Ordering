package com.codragon.restuarant;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class SendPushNotification extends Activity {
	
	// UI elements
	EditText txtMessage; 
	TextView sendTo;
	
	// Register button
	Button btnSend;
    
	Controller aController = null;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		/******************* Intialize Database *************/
		DBAdapter.init(this);
		
		setContentView(R.layout.send_push_notification);
		
		//Get Global Controller Class object (see application tag in AndroidManifest.xml)
		aController = (Controller) getApplicationContext();
		
		// Check if Internet Connection present
		if (!aController.isConnectingToInternet()) {
			
			// Internet Connection is not present
			aController.showAlertDialog(SendPushNotification.this,
					"Internet Connection Error",
					"Please connect to working Internet connection", false);
			
			// stop executing code by return
			return;
		}

		// Getting name, email from intent
		Intent i = getIntent();
		
		final String name = i.getStringExtra("name");
		final String imei = i.getStringExtra("imei");
		final String sendfrom = i.getStringExtra("sendfrom");
		
		
		txtMessage = (EditText) findViewById(R.id.txtMessage);
		sendTo     = (TextView) findViewById(R.id.sendTo);
		btnSend    = (Button) findViewById(R.id.btnSend);
		
		sendTo.setText("Send To : "+name);
		
		// Click event on Register button
		btnSend.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {  
				// Get data from EditText 
				String message = txtMessage.getText().toString(); 
				 
				// WebServer Request URL
		        String serverURL = Config.YOUR_SERVER_URL+"sendpush.php";
		        
		        // Use AsyncTask execute Method To Prevent ANR Problem
		        new LongOperation().execute(serverURL,imei,message,sendfrom); 
		        
		        txtMessage.setText("");
			}
		});
	}
	
	
	public class LongOperation  extends AsyncTask<String, Void, String> {
        
    	// Required initialization
    	
        //private final HttpClient Client = new DefaultHttpClient();
       // private Controller aController = null;
        private String Error = null;
        private ProgressDialog Dialog = new ProgressDialog(SendPushNotification.this); 
        String data  = ""; 
        int sizeData = 0;  
        
        
        protected void onPreExecute() {
            // NOTE: You can call UI Element here.
             
            //Start Progress Dialog (Message)
           
            Dialog.setMessage("Please wait..");
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
	               	   data +="&" + URLEncoder.encode("data1", "UTF-8") + "="+params[1].toString();
		            if(!params[2].equals(""))
		               data +="&" + URLEncoder.encode("data2", "UTF-8") + "="+params[2].toString();	
		            if(!params[3].equals(""))
			           data +="&" + URLEncoder.encode("data3", "UTF-8") + "="+params[3].toString();	
	              
		            
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
         
        protected void onPostExecute(String Result) {
            // NOTE: You can call UI Element here.
             
            // Close progress dialog
            Dialog.cancel();
            
            if (Error != null) {
            	Toast.makeText(getBaseContext(), "Error: "+Error, Toast.LENGTH_LONG).show();  
                 
            } else {
              
            	// Show Response Json On Screen (activity)
            	 Toast.makeText(getBaseContext(), "Message sent."+Result, Toast.LENGTH_LONG).show();  
                 
             }
        }
         
    }

}

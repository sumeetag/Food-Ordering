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
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;



public class Register extends Activity implements OnClickListener {

	private EditText email;
	private EditText name;
	private LoginDatabase db;
	private String e;
	private String n;
	private EditText pass;
	private EditText reg;
	private EditText phone;
	private String pw;
	private String ph;
	private String rid;
	String deviceIMEI = "";
	Controller aController;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.register);
		aController = (Controller) getApplicationContext();
		name = (EditText) findViewById(R.id.etname);
		email = (EditText) findViewById(R.id.etreg_email);
		pass = (EditText)findViewById(R.id.etPass);
		reg = (EditText)findViewById(R.id.etreg_no);
		phone = (EditText)findViewById(R.id.etmobile);
		
		Button register = (Button) findViewById(R.id.bRegister);

		register.setOnClickListener(this);

	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		finish();
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.bRegister:
			boolean diditwork = true;
			try {
				e = email.getText().toString();
				n = name.getText().toString();
				pw = pass.getText().toString();
				ph = phone.getText().toString();
				rid = reg.getText().toString();
				db = new LoginDatabase(this);
				db.open();
				// db.createEntry(n, e);
				if (e.length() != 0 && n.length() != 0 && pw.length() != 0 && rid.length() != 0 && ph.length() != 0) {

					System.out.println("adadsd");
					if (db.getReg(rid).equalsIgnoreCase(null)) {
						System.out.println("qeqeeqw");
						db.createEntry(n, e, ph, pw, rid);
						db.close();
						 validte();
						//Login.mail = e;
						Intent i = new Intent();
						i.putExtra("name", n);
     					i.putExtra("email", e);
						i.setClass(Register.this, RegisterActivity.class);
						startActivity(i);
					}else{
						diditwork = false;
						Toast.makeText(getApplicationContext(), "Account already exists",
								Toast.LENGTH_SHORT).show();
					}

				}
				db.close();
			} catch (Exception ex) {
				// TODO: handle exception
				diditwork = false;
				db = new LoginDatabase(this);
				db.open();
				// db.createEntry(n, e);
				System.out.println(e+" "+n+" "+pw+" "+rid+" "+ph);
				if (e.length() != 0 && n.length() != 0 && pw.length() != 0 && rid.length() != 0 && ph.length() != 0) {
						System.out.println("qeqeeqw");
						db.createEntry(n, e, ph, pw, rid);
						db.close();
						//Login.mail = e;
						validte();
						Intent i = new Intent();
						i.putExtra("name", n);
     					i.putExtra("email", e);
						i.setClass(Register.this, RegisterActivity.class);
						startActivity(i);

				}
				db.close();
				
			} finally {
				if (diditwork) {

					Toast.makeText(getApplicationContext(),
							"Pls enter the details", Toast.LENGTH_SHORT).show();

				}
			}

			break;
		}
	}

	private void validte() {
		// TODO Auto-generated method stub
		 TelephonyManager tManager = (TelephonyManager) getBaseContext()
				    .getSystemService(Context.TELEPHONY_SERVICE);
				  deviceIMEI = tManager.getDeviceId(); 
				  
				  String serverURL = Config.YOUR_SERVER_URL+"validate_device.php";
			        
			        // Use AsyncTask execute Method To Prevent ANR Problem
			        LongOperation serverRequest = new LongOperation(); 
			        
			        serverRequest.execute(serverURL,deviceIMEI,"","");
		
	}
	
	public class LongOperation  extends AsyncTask<String, Void, String> {
        
        // Required initialization
    	
       //private final HttpClient Client = new DefaultHttpClient();
       // private Controller aController = null;
        private String Error = null;
        private ProgressDialog Dialog = new ProgressDialog(Register.this); 
        String data =""; 
        int sizeData = 0;  
        
        
        protected void onPreExecute() {
            // NOTE: You can call UI Element here.
             
            //Start Progress Dialog (Message)
           
            //Dialog.setMessage("Validating Device..");
            //Dialog.show();
            
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
           //Dialog.dismiss();
            
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
                         String Status       = jsonChildNode.optString("status").toString();
                         
                         Log.i("GCM","---"+Status);
                         System.out.println("adadaaaaaaaaa"+ Status);
                         
                         // IF server response status is update
                         if(Status.equals("update")){
                            
                        	String RegID      = jsonChildNode.optString("regid").toString();
                            String Name       = jsonChildNode.optString("name").toString();
                            String Email      = jsonChildNode.optString("email").toString();
                            String IMEI       = jsonChildNode.optString("imei").toString();
                            
                           // add device self data in sqlite database
                            DBAdapter.addDeviceData(Name, Email,RegID, IMEI);
                            
                            // Launch GridViewExample Activity
                			Intent i1 = new Intent(getApplicationContext(), GridViewExample.class);
                			startActivity(i1);
                			finish();
                           
                            Log.i("GCM","---"+Name);
                         }
                         else if(Status.equals("install")){  
                        	
                        	 // Launch RegisterActivity Activity
                        	 Intent in = new Intent(getApplicationContext(), RegisterActivity.class);
         					
         					// Registering user on our server					
         					// Sending registraiton details to MainActivity
         					in.putExtra("name", n);
         					in.putExtra("email", e);
         					startActivity(in);
	                		finish();
                        	 
                         }
                         
                        
                    }
                     
                 /****************** End Parse Response JSON Data *************/     
                   
                      
                 } catch (JSONException e) {
          
                     e.printStackTrace();
                 }
  
                 
             }
        }
         
    }

}



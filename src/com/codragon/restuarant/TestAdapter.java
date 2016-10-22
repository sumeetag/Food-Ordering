/*package com.codragon.restuarant;

import java.io.IOException;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class TestAdapter {
	
	 protected static final String TAG = "DataAdapter"; 
	 
	    private final Context mContext; 
	    private SQLiteDatabase mDb; 
	    private DatabaseOpenHelper mDbHelper; 
	 
	    public TestAdapter(Context context)  
	    { 
	        this.mContext = context; 
	        mDbHelper = new DatabaseOpenHelper(mContext); 
	    } 
	 
	    public TestAdapter createDatabase() throws SQLException  
	    { 
	        try  
	        { 
	            mDbHelper.create(); 
	        }  
	        catch (IOException mIOException)  
	        { 
	            Log.e(TAG, mIOException.toString() + "  UnableToCreateDatabase"); 
	            throw new Error("UnableToCreateDatabase"); 
	        } 
	        return this; 
	    } 
	 
	    public TestAdapter open() throws SQLException  
	    { 
	        try  
	        { 
	            mDbHelper.open(); 
	            mDbHelper.close(); 
	            mDb = mDbHelper.getReadableDatabase(); 
	        }  
	        catch (SQLException mSQLException)  
	        { 
	            Log.e(TAG, "open >>"+ mSQLException.toString()); 
	            throw mSQLException; 
	        } 
	        return this; 
	    } 
	 
	    public void close()  
	    { 
	        mDbHelper.close(); 
	    } 
	 
	     public String getFood(int i) 
	     { 
	        return mDbHelper.getFood(i);
			
	     }
	     public String getOrders(int i) 
	     { 
	        return mDbHelper.getOrders(i);
			
	     }
	     public String getRating(int i) 
	     { 
	        return mDbHelper.getRating(i);
			
	     }
	     
	     public String getPrice(int i) 
	     { 
	        return mDbHelper.getPrice(i);
			
	     }
	     
}
*/
package com.codragon.restuarant;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MenuDatabase {		
		public static final String KEY_ROWID = "_id";
		public static final String KEY_NAME = "name";
		public static final String KEY_MID = "mid";
		
		private static final String DATABASE_NAME="menu";
		private static final String DATABASE_TABLE = "menu";
		private static final int DATABASE_VERSION = 1;
		
		private DbHelper ourHelper;
		private final Context ourContext;
		private SQLiteDatabase ourDatabase; 
		
		
		private static class DbHelper extends SQLiteOpenHelper{ 

			public DbHelper(Context context) {
				super(context, DATABASE_NAME, null, DATABASE_VERSION);
				// TODO Auto-generated constructor stub
			}

			@Override
			public void onCreate(SQLiteDatabase db) {
				// TODO Auto-generated method stub
				db.execSQL("CREATE TABLE " + DATABASE_TABLE + " (" +
						KEY_ROWID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + 
						KEY_MID + " INTEGER, " + 
						KEY_NAME + " TEXT NOT NULL);" 
						);
				
			}

			@Override
			public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
				// TODO Auto-generated method stub
				db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE);
				onCreate(db);
			}
			
		}
		
		public MenuDatabase(Context c){
			ourContext = c;
		}
		
		public MenuDatabase open() throws SQLException{
			ourHelper = new DbHelper(ourContext);
			ourDatabase = ourHelper.getWritableDatabase();
			return this;
		}
		
		public void close(){
			ourHelper.close();
		}

		public long createEntry(String name, int mid) {
			// TODO Auto-generated method stub
			ContentValues cv = new ContentValues();
			System.out.println("sSsSsassassS");
			cv.put(KEY_NAME, name);
			cv.put(KEY_MID, mid);
			return ourDatabase.insert(DATABASE_TABLE, null, cv);
		}

		

		

		public String getName(int id)throws SQLException {
			// TODO Auto-generated method stub
			String[] columns = new String[]{KEY_ROWID, KEY_MID, KEY_NAME};
			Cursor c = ourDatabase.query(DATABASE_TABLE, columns, KEY_ROWID + "=" + id, null, null, null, null);
			if(c!=null){
				c.moveToFirst();
				String name = c.getString(2);
				//System.out.println("return name"+ name);
				
				return name;
			}
			return null;
		}
		
		public int getMid(long id)throws SQLException {
			// TODO Auto-generated method stub
			String[] columns = new String[]{KEY_ROWID, KEY_MID, KEY_NAME};
			Cursor c = ourDatabase.query(DATABASE_TABLE, columns, KEY_ROWID + "=" + id, null, null, null, null);
			if(c!=null){
				c.moveToFirst();
				int mid = c.getInt(1);
				return mid;
			}
			return 0;
		}
		
		
		public int getUserDataCount() {
	        String countQuery = "SELECT * FROM " + DATABASE_TABLE;
	        Cursor cursor = ourDatabase.rawQuery(countQuery, null);
	        
	        int count = cursor.getCount();
	        System.out.println("AD"+count);
	        cursor.close();
	        
	        // return count
	        return count;
	    }
		

		

		public void deleteEntry(long lRow1)throws SQLException {
			// TODO Auto-generated method stub
			ourDatabase.delete(DATABASE_TABLE, KEY_ROWID + "=" + lRow1, null);
		}
	}


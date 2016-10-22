package com.codragon.restuarant;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MainDatabaseOpenHelper{

	private static final String DATABASE_NAME = "food";
	private static final int DATABASE_VERSION = 1;

	private static final String TABLE_NAME = "food";
	private static final String COLUMN_ID = "_id";
	private static final String COLUMN_MID = "menu";
	private static final String COLUMN_SMID = "submenu";
	private static final String COLUMN_CATEGORY = "category";
	private static final String COLUMN_FOOD = "food_item";
	private static final String COLUMN_RATE = "rate";
	private static final String COLUMN_INGRE = "ingre";
	private static final String COLUMN_ORDERS = "orders";
	
	
		
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
			db.execSQL("CREATE TABLE " + TABLE_NAME + " (" +
					COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
					COLUMN_MID + " INTEGER, " +
					COLUMN_SMID + " INTEGER, " +
					COLUMN_CATEGORY + " INTEGER, " +
					COLUMN_FOOD + " TEXT NOT NULL, " +
					COLUMN_RATE + " TEXT," +
					COLUMN_INGRE + " TEXT, " +
					COLUMN_ORDERS + " TEXT);" 
					);
			
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			// TODO Auto-generated method stub
			db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
			onCreate(db);
		}
		
	}
	
	public MainDatabaseOpenHelper(Context c){
		ourContext = c;
	}
	
	public MainDatabaseOpenHelper open() throws SQLException{
		ourHelper = new DbHelper(ourContext);
		ourDatabase = ourHelper.getWritableDatabase();
		return this;
	}
	
	public void close(){
		ourHelper.close();
	}
	
	public long createEntry(int mid, int smid, int cat, String name, String ingre, String price) {
		// TODO Auto-generated method stub
		ContentValues cv = new ContentValues();
		//cv.put(COLUMN_ID, id);
		cv.put(COLUMN_MID, mid);
		cv.put(COLUMN_SMID, smid);
		cv.put(COLUMN_CATEGORY, cat);
		cv.put(COLUMN_FOOD, name);
		cv.put(COLUMN_INGRE, ingre);
		cv.put(COLUMN_RATE, price);
		cv.put(COLUMN_ORDERS, "0");
		return ourDatabase.insert(TABLE_NAME, null, cv);
	}
	
	
	public ArrayList<Integer> getsmid(int mid) throws SQLException {
		// TODO Auto-generated method stub
		ArrayList<Integer> foo = new ArrayList<Integer>();
		// selectionArgs = new String[] {"%" + table_no + "%"};
		/*String[] columns = new String[] { COLUMN_ID, COLUMN_MID, COLUMN_SMID, COLUMN_CATEGORY, COLUMN_FOOD, COLUMN_INGRE, 
				COLUMN_FOOD, COLUMN_RATE, COLUMN_ORDERS};
		// String query = " SELECT food_item FROM users WHERE table_no=?";
		// Cursor c = database.query(TABLE_NAME, columns, COLUMN_TABLE +"= '" +
		// table_no +"'", null, null, null, null);
		Cursor c = ourDatabase.query(TABLE_NAME, columns,
				COLUMN_MID + "=" + mid, null, null, null, null);
		// Cursor c = database.rawQuery(query, selectionArgs);
		*/
		
		 String countQuery = "SELECT DISTINCT submenu FROM " + TABLE_NAME + " WHERE menu = "+ mid;
	        Cursor c = ourDatabase.rawQuery(countQuery, null);
		for (c.moveToFirst(); !c.isAfterLast(); c.moveToNext()) {
			// c.move(1);
			int food = c.getInt(0);
			foo.add(food);
		}
		c.close();
		return foo;
	}
	
	public ArrayList<String> getFood(int smid) throws SQLException {
		// TODO Auto-generated method stub
		ArrayList<String> foo = new ArrayList<String>();
		// selectionArgs = new String[] {"%" + table_no + "%"};
		String[] columns = new String[] { COLUMN_ID, COLUMN_MID, COLUMN_SMID, COLUMN_CATEGORY, COLUMN_FOOD, COLUMN_INGRE, 
				COLUMN_FOOD, COLUMN_RATE, COLUMN_ORDERS};
		// String query = " SELECT food_item FROM users WHERE table_no=?";
		// Cursor c = database.query(TABLE_NAME, columns, COLUMN_TABLE +"= '" +
		// table_no +"'", null, null, null, null);
		Cursor c = ourDatabase.query(TABLE_NAME, columns,
				COLUMN_SMID + "=" + smid, null, null, null, null);
		// Cursor c = database.rawQuery(query, selectionArgs);
		
		for (c.moveToFirst(); !c.isAfterLast(); c.moveToNext()) {
			// c.move(1);
			String food = c.getString(4);
			foo.add(food);
		}
		c.close();
		return foo;
	}
	
	public ArrayList<String> getPrice(int smid) throws SQLException {
		// TODO Auto-generated method stub
		ArrayList<String> foo = new ArrayList<String>();
		// selectionArgs = new String[] {"%" + table_no + "%"};
		String[] columns = new String[] { COLUMN_ID, COLUMN_MID, COLUMN_SMID, COLUMN_CATEGORY, COLUMN_FOOD, COLUMN_INGRE, 
				COLUMN_FOOD, COLUMN_RATE, COLUMN_ORDERS};
		// String query = " SELECT food_item FROM users WHERE table_no=?";
		// Cursor c = database.query(TABLE_NAME, columns, COLUMN_TABLE +"= '" +
		// table_no +"'", null, null, null, null);
		Cursor c = ourDatabase.query(TABLE_NAME, columns,
				COLUMN_SMID + "=" + smid, null, null, null, null);
		// Cursor c = database.rawQuery(query, selectionArgs);
		
		for (c.moveToFirst(); !c.isAfterLast(); c.moveToNext()) {
			// c.move(1);
			String food = c.getString(7);
			foo.add(food);
		}
		c.close();
		return foo;
	}
	
	public String getName1(int i) throws SQLException {
		// TODO Auto-generated method stub
		String[] columns = new String[] { COLUMN_ID, COLUMN_MID, COLUMN_SMID, COLUMN_CATEGORY, COLUMN_FOOD, COLUMN_INGRE, 
				COLUMN_FOOD, COLUMN_RATE, COLUMN_ORDERS};
		Cursor c = ourDatabase.query(TABLE_NAME, columns, COLUMN_ID + "=" + i,
				null, null, null, null);
		if (c != null) {
			c.moveToFirst();
			String cat = c.getString(4);
			c.close();
			return cat;
		}
		return null;
	}
	
	public String getPrice1(int i) throws SQLException {
		// TODO Auto-generated method stub
		String[] columns = new String[] { COLUMN_ID, COLUMN_MID, COLUMN_SMID, COLUMN_CATEGORY, COLUMN_FOOD, COLUMN_INGRE, 
				COLUMN_FOOD, COLUMN_RATE, COLUMN_ORDERS};
		Cursor c = ourDatabase.query(TABLE_NAME, columns, COLUMN_ID + "=" + i,
				null, null, null, null);
		if (c != null) {
			c.moveToFirst();
			String cat = c.getString(7);
			c.close();
			return cat;
		}
		return null;
	}
	
	
	public String getCat(String name) throws SQLException {
		// TODO Auto-generated method stub
		String[] columns = new String[] { COLUMN_ID, COLUMN_MID, COLUMN_SMID, COLUMN_CATEGORY, COLUMN_FOOD, COLUMN_INGRE, 
				COLUMN_FOOD, COLUMN_RATE, COLUMN_ORDERS};
		Cursor c = ourDatabase.query(TABLE_NAME, columns, COLUMN_FOOD + "= '"
				+ name + "'",
				null, null, null, null);
		if (c != null) {
			c.moveToFirst();
			String cat = c.getString(3);
			c.close();
			return cat;
		}
		return null;
	}
	
	public int getUserDataCount() {
        String countQuery = "SELECT * FROM " + TABLE_NAME;
        Cursor cursor = ourDatabase.rawQuery(countQuery, null);
        
        int count = cursor.getCount();
        System.out.println("AD"+count);
        cursor.close();
        
        // return count
        return count;
    }
	
	public int getId(String name) throws SQLException {
		// TODO Auto-generated method stub
		String[] columns = new String[] { COLUMN_ID, COLUMN_MID, COLUMN_SMID, COLUMN_CATEGORY, COLUMN_FOOD, COLUMN_INGRE, 
				COLUMN_FOOD, COLUMN_RATE, COLUMN_ORDERS};
		Cursor c = ourDatabase.query(TABLE_NAME, columns, COLUMN_FOOD + "= '"
				+ name + "'",
				null, null, null, null);
		if (c != null) {
			c.moveToFirst();
			int cat = c.getInt(0);
			c.close();
			return cat;
		}
		return 0;
	}
	
	public String getOrder(int i) throws SQLException {
		// TODO Auto-generated method stub
		String[] columns = new String[] { COLUMN_ID, COLUMN_MID, COLUMN_SMID, COLUMN_CATEGORY, COLUMN_FOOD, COLUMN_INGRE, 
				COLUMN_FOOD, COLUMN_RATE, COLUMN_ORDERS};
		Cursor c = ourDatabase.query(TABLE_NAME, columns, COLUMN_ID + "=" + i,
				null, null, null, null);
		if (c != null) {
			c.moveToFirst();
			String cat = c.getString(8);
			c.close();
			return cat;
		}
		return null;
	}
	
	
	public void updateOrder(long l, String  order) {
		ContentValues args = new ContentValues();
		args.put(COLUMN_ORDERS, order);
		ourDatabase.update(TABLE_NAME, args, COLUMN_ID + "=" + l, null);
	}
	
	public ArrayList<String> getFood1(String l) throws SQLException {
		// TODO Auto-generated method stub
		ArrayList<String> foo = new ArrayList<String>();
		// selectionArgs = new String[] {"%" + table_no + "%"};
		String[] columns = new String[] { COLUMN_ID, COLUMN_MID, COLUMN_SMID, COLUMN_CATEGORY, COLUMN_FOOD, COLUMN_INGRE, 
				COLUMN_FOOD, COLUMN_RATE, COLUMN_ORDERS};
		// String query = " SELECT food_item FROM users WHERE table_no=?";
		// Cursor c = database.query(TABLE_NAME, columns, COLUMN_TABLE +"= '" +
		// table_no +"'", null, null, null, null);
		Cursor c = ourDatabase.query(TABLE_NAME, columns,
				COLUMN_ORDERS + " NOT LIKE ?",
				new String[] { "%" + l + "%" }, null, null, null, null);
		// Cursor c = database.rawQuery(query, selectionArgs);
		System.out.println(c);
		for (c.moveToFirst(); !c.isAfterLast(); c.moveToNext()) {
			// c.move(1);
			String food = c.getString(4);
			foo.add(food);
		}
		c.close();
		return foo;
	}
	
}

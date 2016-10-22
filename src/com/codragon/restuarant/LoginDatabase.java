package com.codragon.restuarant;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;



public class LoginDatabase{
	
	public static final String KEY_ROWID = "_id";
	public static final String KEY_NAME = "persons_name";
	public static final String KEY_EMAIL = "email";
	public static final String KEY_PHONE = "phone";
	public static final String KEY_PASS = "pass";
	public static final String KEY_REG = "reg";
	
	private static final String DATABASE_NAME="CUSTOMERS";
	private static final String DATABASE_TABLE = "details";
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
					KEY_NAME + " TEXT NOT NULL, " +
					KEY_EMAIL + " TEXT, " +
					KEY_PHONE + " TEXT," +
					KEY_PASS + " TEXT, " +
					KEY_REG + " TEXT);" 
					);
			
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			// TODO Auto-generated method stub
			db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE);
			onCreate(db);
		}
		
	}
	
	public LoginDatabase(Context c){
		ourContext = c;
	}
	
	public LoginDatabase open() throws SQLException{
		ourHelper = new DbHelper(ourContext);
		ourDatabase = ourHelper.getWritableDatabase();
		return this;
	}
	
	public void close(){
		ourHelper.close();
	}

	public long createEntry(String name, String mail, String phone, String pass, String reg) {
		// TODO Auto-generated method stub
		ContentValues cv = new ContentValues();
		cv.put(KEY_NAME, name);
		cv.put(KEY_EMAIL, mail);
		cv.put(KEY_PHONE, phone);
		cv.put(KEY_PASS, pass);
		cv.put(KEY_REG, reg);
		return ourDatabase.insert(DATABASE_TABLE, null, cv);
	}

	
	
	public String getPass(String pw)throws SQLException {
		// TODO Auto-generated method stub
		String[] columns = new String[]{KEY_ROWID, KEY_NAME, KEY_EMAIL, KEY_PHONE, KEY_PASS, KEY_REG};
		Cursor c = ourDatabase.query(DATABASE_TABLE, columns, KEY_PASS + "= '" + pw + "'", null, null, null, null);
		if(c!=null){
			c.moveToFirst();
			String pass = c.getString(4);
			return pass;
		}
		return null;
	}
	
	public String getPassShow(String rid)throws SQLException {
		// TODO Auto-generated method stub
		String[] columns = new String[]{KEY_ROWID, KEY_NAME, KEY_EMAIL, KEY_PHONE, KEY_PASS, KEY_REG};
		Cursor c = ourDatabase.query(DATABASE_TABLE, columns, KEY_REG + "= '" + rid + "'", null, null, null, null);
		if(c!=null){
			c.moveToFirst();
			String pass = c.getString(4);
			return pass;
		}
		return null;
	}
	
	public String getReg(String reg)throws SQLException {
		// TODO Auto-generated method stub
		String[] columns = new String[]{KEY_ROWID, KEY_NAME, KEY_EMAIL, KEY_PHONE, KEY_PASS, KEY_REG};
		Cursor c = ourDatabase.query(DATABASE_TABLE, columns, KEY_REG + "= '" + reg + "'", null, null, null, null);
		if(c!=null){
			c.moveToFirst();
			String regid = c.getString(5);
			return regid;
		}
		return null;
	}
	
	public String getName(String nam)throws SQLException {
		// TODO Auto-generated method stub
		String[] columns = new String[]{KEY_ROWID, KEY_NAME, KEY_EMAIL, KEY_PHONE, KEY_PASS, KEY_REG};
		Cursor c = ourDatabase.query(DATABASE_TABLE, columns, KEY_NAME + "= '" + nam + "'", null, null, null, null);
		if(c!=null){
			c.moveToFirst();
			String name = c.getString(1);
			return name;
		}
		return null;
	}
	
	public String getEmail(String e)throws SQLException {
		// TODO Auto-generated method stub
		String[] columns = new String[]{KEY_ROWID, KEY_NAME, KEY_EMAIL, KEY_PHONE, KEY_PASS, KEY_REG};
		Cursor c = ourDatabase.query(DATABASE_TABLE, columns, KEY_EMAIL + "= '" + e + "'", null, null, null, null);
		if(c!=null){
			c.moveToFirst();
			String email = c.getString(2);
			return email;
		}
		return null;
	}
	
	public String getPhone(String p)throws SQLException {
		// TODO Auto-generated method stub
		String[] columns = new String[]{KEY_ROWID, KEY_NAME, KEY_EMAIL, KEY_PHONE, KEY_PASS, KEY_REG};
		Cursor c = ourDatabase.query(DATABASE_TABLE, columns, KEY_PHONE + "= '" + p + "'", null, null, null, null);
		if(c!=null){
			c.moveToFirst();
			String ph = c.getString(3);
			return ph;
		}
		return null;
	}
	
	

	
	/*
	public void updateFood(String pre, String mail)throws SQLException {
		// TODO Auto-generated method stub
		ContentValues cvUpdate = new ContentValues();
		cvUpdate.put(KEY_FOOD, pre);
		//cvUpdate.put(KEY_EMAIL, mail);
		ourDatabase.update(DATABASE_TABLE, cvUpdate, KEY_EMAIL + "= '" + mail + "'", null);
	}
	*/

	public void deleteEntry(long lRow1)throws SQLException {
		// TODO Auto-generated method stub
		ourDatabase.delete(DATABASE_TABLE, KEY_ROWID + "=" + lRow1, null);
	}
}

/*
public class LoginDatabase {

	// All Static variables
	// Database Version
	private static final int DATABASE_VERSION = 1;

	// Database Name
	private static final String DATABASE_NAME = "customers";

	// Contacts table name
	private static final String TABLE_DETAIL = "detail";

	// Contacts Table Columns names
	private static final String KEY_ID = "id";
	private static final String KEY_NAME = "name";
	private static final String KEY_EMAIL = "email";
	//private static final String KEY_PRE_ORDER = "lol";
	//private static final String KEY_TABLE = "tab";
	//private static final String KEY_EAT_COUNT = "eat";

	private DbHelper ourHelper;
	private final Context ourContext;
	private SQLiteDatabase ourDatabase;

	private static class DbHelper extends SQLiteOpenHelper {

		public DbHelper(Context context) {
			super(context, DATABASE_NAME, null, DATABASE_VERSION);
		}

		// Creating Tables
		@Override
		public void onCreate(SQLiteDatabase db) {
			String CREATE_DETAIL_TABLE = "CREATE TABLE " + TABLE_DETAIL + " ("
					+ KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + KEY_NAME + " TEXT NOT NULL, "
					+ KEY_EMAIL + " TEXT NOT NULL,"  + ");";
			db.execSQL(CREATE_DETAIL_TABLE);
		}

		// Upgrading database
		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			// Drop older table if existed
			db.execSQL("DROP TABLE IF EXISTS " + TABLE_DETAIL);

			// Create tables again
			onCreate(db);
		}
	}

	

	public LoginDatabase(Context c) {
		ourContext = c;
	}

	public LoginDatabase open() throws SQLException {
		ourHelper = new DbHelper(ourContext);
		ourDatabase = ourHelper.getWritableDatabase();
		return this;
	}

	public void close() {
		ourHelper.close();
	}

	// Adding new contact
	public long addDetails(String name, String email) {

		ContentValues values = new ContentValues();
		values.put(KEY_NAME, name); // Contact Name
		values.put(KEY_EMAIL, email);

		// Inserting Row
		ourDatabase.insert(TABLE_DETAIL, null, values);
		//ourDatabase.close(); // Closing database connection
		return ourDatabase.insert(TABLE_DETAIL, null, values);
	}

	public String getName(long l) throws SQLException {
		// TODO Auto-generated method stub
		String[] columns = new String[] { KEY_ID, KEY_NAME };
		Cursor c = ourDatabase.query(TABLE_DETAIL, columns, KEY_ID + "=" + l,
				null, null, null, null);
		if (c != null) {
			c.moveToFirst();
			String name = c.getString(1);
			return name;
		}
		return null;
	}

	public boolean getemail(String mail) throws SQLException {
		// TODO Auto-generated method stub
		String[] columns = new String[] { KEY_ID, KEY_NAME, KEY_EMAIL };
		Cursor c = ourDatabase.query(TABLE_DETAIL, columns, KEY_EMAIL + "= '"
				+ mail + "'", null, null, null, null);
		if (c != null) {
			c.moveToFirst();
			System.out.println(c);
			//String email = c.getString(0);
			return true;
		}
		return false;
	}
	// Getting single contact

}
*/
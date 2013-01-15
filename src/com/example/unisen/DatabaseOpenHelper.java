package com.example.unisen;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseOpenHelper extends SQLiteOpenHelper {
	
	public static final String DATABASE_NAME = "unisen.db";
	private static final int DATABASE_VERSION = 1;
	
	
	public DatabaseOpenHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		//date is UNIXTIME
		db.execSQL("CREATE TABLE ranks (" + 
				"_id INTEGER PRIMARY KEY," +
				"score INTEGER," +
				"average REAL," +
				"date INTEGER);"
				);
		
		Log.d("", "CREATE DB");
		
		// insert dummy data
		String insertMe = "INSERT INTO ranks" +
		"(score, average, date) VALUES";
		db.execSQL(insertMe + "(11111, 40.5, 1357896755);");
		db.execSQL(insertMe + "(1234567, 20.5, 1357983155);");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

	}

}

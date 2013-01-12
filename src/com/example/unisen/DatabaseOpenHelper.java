package com.example.unisen;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseOpenHelper extends SQLiteOpenHelper {
	
	private static final String DATABASE_NAME = "unisen.db";
	private static final int DATABASE_VERSION = 1;
	
	
	public DatabaseOpenHelper(Context context, String name,
			CursorFactory factory, int version) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		//date はUNIXTIMEで保存
		db.execSQL("CREATE TABLE ranks (" + 
				"_id INTEGER PRIMARY KEY," +
				"average REAL," +
				"date INTEGER);"
				);
		// insert dummy data
		String insertMe = "INSERT INTO ranks" +
		"(average, date) VALUES";
		db.execSQL(insertMe + "(40.5, 1357896755);");
		db.execSQL(insertMe + "(20.5, 1357983155);");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

	}

}

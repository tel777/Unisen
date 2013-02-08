package com.example.unisen;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseOpenHelper extends SQLiteOpenHelper {
	
	public static final String DATABASE_NAME  = "unisen.db";
	public static final String COLUMN_ID      = "_id";
	public static final String COLUMN_SCORE   = "score";
	public static final String COLUMN_AVERAGE = "average";
	public static final String COLUMN_DATE    = "date";
	public static final String SCORE_TABLE    = "ranks_score";
	public static final String TIME_TABLE     = "ranks_time";
	
	private static final int DATABASE_VERSION = 1;
	
	
	
	public DatabaseOpenHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		//date is UNIXTIME
		// unused
		db.execSQL("CREATE TABLE " + SCORE_TABLE + " (" + 
				COLUMN_ID +" INTEGER PRIMARY KEY," +
				COLUMN_SCORE + " INTEGER," +
				COLUMN_AVERAGE +" REAL," +
				COLUMN_DATE + " INTEGER);"
				);
		// used
		db.execSQL("CREATE TABLE " + TIME_TABLE + " (" + 
				COLUMN_ID +" INTEGER PRIMARY KEY," +
				COLUMN_AVERAGE +" REAL," +
				COLUMN_DATE + " INTEGER);"
				);
		
		Log.d("", "CREATE DB");
		
		// insert dummy data to ranks_score
		String insertMe = "INSERT INTO " + SCORE_TABLE +
		"("+ COLUMN_SCORE +", " + COLUMN_AVERAGE + ", " + COLUMN_DATE + ") VALUES";
		db.execSQL(insertMe + "(11111, 40.5, 1357896755);");
		db.execSQL(insertMe + "(1234567, 20.5, 1357983155);");
		
		// insert dummy data to ranks_time
		insertMe = "INSERT INTO " + TIME_TABLE +
		"(" + COLUMN_AVERAGE + ", " + COLUMN_DATE + ") VALUES";
		db.execSQL(insertMe + "(40.5, 1357896755);");
		db.execSQL(insertMe + "(20.5, 1357983155);");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

	}

}

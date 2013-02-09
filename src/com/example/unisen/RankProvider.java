package com.example.unisen;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.util.Log;

public class RankProvider extends ContentProvider {
	private DatabaseOpenHelper mOpenHelper;

	@Override
	public int delete(Uri uri, String selection, String[] selectionArgs) {
		return 0;
	}

	@Override
	public String getType(Uri uri) {
		return null;
	}

	//
	// --- dbへinsertする方法 ---
	// ContentResolver contentResolver = getContentResolver();
	//
	// ContentValues contentValues = new ContentValues();
	// contentValues.put("score", スコア); <- 今回使わない
	// contentValues.put("average", 平均点);
	// contentValues.put("date", System.currentTimeMills());
	// contentResolver.insert(Ranks.CONTENT_URI, contentValues);
	//
	// 
	@Override
	public Uri insert(Uri uri, ContentValues values) {
		SQLiteDatabase db = mOpenHelper.getWritableDatabase();
		String selectTable = uri.getPathSegments().get(0);
		Log.d("Unisen_DB", selectTable);
		int rowId = (int) db.insert(selectTable, null, values);
		
		if (rowId != -1) {
			return Uri.withAppendedPath(uri, String.valueOf(rowId));
		} else {
			Log.d("Unisen_DB", "miss");
			return null;
		}
	}

	@Override
	public boolean onCreate() {
		mOpenHelper = new DatabaseOpenHelper(getContext());
		return true;
	}

	@Override
	public Cursor query(Uri uri, String[] projectionIn, String selection,
			String[] selectionArgs, String sortOrder) {
		SQLiteDatabase db = mOpenHelper.getReadableDatabase();
		
		SQLiteQueryBuilder qb = new SQLiteQueryBuilder();
		String selectTable = uri.getPathSegments().get(0);
		qb.setTables(selectTable);
		
		Cursor ret = qb.query(db, projectionIn, selection, selectionArgs, null, null, sortOrder);
		
		return ret;
	}

	@Override
	public int update(Uri uri, ContentValues values, String selection,
			String[] selectionArgs) {
		return 0;
	}
}

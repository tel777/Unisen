package com.example.unisen;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;

public class RankProvider extends ContentProvider {
	private DatabaseOpenHelper mOpenHelper;

	@Override
	public int delete(Uri uri, String selection, String[] selectionArgs) {
		return 0;
	}

	@Override
	public String getType(Uri uri) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Uri insert(Uri uri, ContentValues values) {
		SQLiteDatabase db = mOpenHelper.getWritableDatabase();
		db.insert(DatabaseOpenHelper.DATABASE_NAME, null, values);
		return null;
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
		qb.setTables(DatabaseOpenHelper.DATABASE_NAME);
		
		Cursor ret = qb.query(db, projectionIn, selection, selectionArgs, null, null, sortOrder);
		
		return ret;
	}

	@Override
	public int update(Uri uri, ContentValues values, String selection,
			String[] selectionArgs) {
		return 0;
	}

}

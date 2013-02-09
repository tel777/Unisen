package com.Ichif1205.unisen;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;

public class Ranks {
//	expand table
//	private static final String[] RANK_QUERY_COLUMNS = {"_id", "score", "average", "date"};
	
	//
	private static final String[] RANK_QUERY_COLUMNS = {"_id", "average", "date"};
	public static final Uri CONTENT_URI_20 = Uri.parse("content://com.Ichif1205.unisen/"+DatabaseOpenHelper.TABLE_20);
	public static final Uri CONTENT_URI_40 = Uri.parse("content://com.Ichif1205.unisen/"+DatabaseOpenHelper.TABLE_40);
			
	public static Cursor getRanks20Cursor(ContentResolver contentResolver) {
		return contentResolver.query(CONTENT_URI_20, RANK_QUERY_COLUMNS, null, null, "average ASC" + " LIMIT 10");
	}
	public static Cursor getRanks40Cursor(ContentResolver contentResolver) {
		return contentResolver.query(CONTENT_URI_40, RANK_QUERY_COLUMNS, null, null, "average ASC" + " LIMIT 10");
	}
	
}

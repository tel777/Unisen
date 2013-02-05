package com.example.unisen;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;

public class Ranks {
//	expand table
//	private static final String[] RANK_QUERY_COLUMNS = {"_id", "score", "average", "date"};
	
	//
	private static final String[] RANK_QUERY_COLUMNS = {"_id", "average", "date"};
	public static final Uri CONTENT_URI = Uri.parse("content://com.example.unisen");
			
	public static Cursor getRanksCursor(ContentResolver contentResolver) {
		return contentResolver.query(CONTENT_URI, RANK_QUERY_COLUMNS, null, null, "average DESC");
	}
	
}

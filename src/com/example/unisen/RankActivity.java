package com.example.unisen;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.widget.SimpleCursorAdapter;
import android.view.Menu;
import android.widget.ListAdapter;
import android.widget.ListView;

public class RankActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_rank);
		
		DatabaseOpenHelper helper = new DatabaseOpenHelper(this);
		SQLiteDatabase db = helper.getReadableDatabase();
		
		Cursor c = db.rawQuery("select rowid as _id, average, date from ranks", null);
		startManagingCursor(c);
		
		ListAdapter adapter = new SimpleCursorAdapter(this, R.layout.rank_item, c, new String[] {"average", "date"}, new int[] {R.id.average_text, R.id.date_text});
		
		ListView lv = (ListView) findViewById(R.id.rank_list);
		lv.setAdapter(adapter);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_rank, menu);
		return true;
	}

}

package com.example.unisen;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ResultActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_result);

		// time表示
		Intent intent = getIntent();
		double ResultTime = intent.getLongExtra("ResultTime", 10);
		int count = intent.getIntExtra("Count", 20);
		
		// set TextView
		TextView result_time = (TextView) findViewById(R.id.result_time);
		ResultTime = ResultTime / 1000;
		result_time.setText(String.valueOf(ResultTime));

		// 日付表示
		TextView date = (TextView) findViewById(R.id.count);
		date.setText(String.valueOf(count+"回数"));

		// Button
		Button homeButton = (Button) findViewById(R.id.homebutton_id);
		homeButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent(ResultActivity.this,
						HomeActivity.class);
				startActivity(intent);
				finish();
			}
		});
	
		// DBに登録
		
		Uri uri;
		if(count == 20) {
			uri = Ranks.CONTENT_URI_20;
		} else {
			uri = Ranks.CONTENT_URI_40;
		}
		
		 ContentResolver contentResolver = getContentResolver();
		 ContentValues contentValues = new ContentValues();
		 contentValues.put("average", ResultTime);
		 contentValues.put("date", System.currentTimeMillis());
		 contentResolver.insert(uri, contentValues);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_result, menu);
		return true;
	}

}

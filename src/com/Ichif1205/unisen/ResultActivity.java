package com.Ichif1205.unisen;

import com.Ichif1205.unisen.R;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ResultActivity extends Activity {
	private int count;
	private Typeface mFace;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_result);
		setTitle("Result");
		mFace = Utils.getFonts(getApplicationContext());

		// time表示
		Intent intent = getIntent();
		double ResultTime = intent.getLongExtra("ResultTime", 10);
		count = intent.getIntExtra("Count", 20);

		// set TextView
		TextView result_time = (TextView) findViewById(R.id.result_time);
		ResultTime = ResultTime / 1000;
		result_time.setText(String.valueOf(ResultTime));
		result_time.setTypeface(mFace);

		// 日付表示
		TextView count_view = (TextView) findViewById(R.id.count);
		count_view.setText(String.valueOf("Count " + count));
		count_view.setTypeface(mFace);

		// Button
		Button homeButton = (Button) findViewById(R.id.homebutton_id);
		homeButton.setTypeface(mFace);
		homeButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent(ResultActivity.this,
						HomeActivity.class);
				startActivity(intent);
				finish();
			}
		});

		// Button
		Button retryButton = (Button) findViewById(R.id.retrybutton);
		retryButton.setTypeface(mFace);
		retryButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent(ResultActivity.this,
						CountActivity.class);
				intent.putExtra("Count", count);
				startActivity(intent);
				finish();
			}
		});

		// Button
		Button rankingButton = (Button) findViewById(R.id.rankingbutton);
		rankingButton.setTypeface(mFace);
		rankingButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent(ResultActivity.this,
						RankActivity.class);
				startActivity(intent);
				finish();
			}
		});

		// DB周りの処理
		Uri uri;
		Cursor cursor;
		TextView highScoreView = (TextView) findViewById(R.id.highScore);
		highScoreView.setTypeface(mFace);

		if (count == 20) {
			cursor = Ranks.getRanks20Cursor(getContentResolver());
			uri = Ranks.CONTENT_URI_20;
		} else {
			cursor = Ranks.getRanks40Cursor(getContentResolver());
			uri = Ranks.CONTENT_URI_40;
		}
		// High Scoreか確認
		if (cursor.getCount() > 0) {
			cursor.moveToFirst();
			int averageIndex = cursor
					.getColumnIndex(DatabaseOpenHelper.COLUMN_AVERAGE);
			double highScore = cursor.getDouble(averageIndex);

			if (highScore > ResultTime) {
				highScoreView.setVisibility(View.VISIBLE);
			}
		} else {
			highScoreView.setVisibility(View.VISIBLE);
		}

		// DBに登録
		ContentResolver contentResolver = getContentResolver();
		ContentValues contentValues = new ContentValues();
		contentValues.put("average", ResultTime);
		contentValues.put("date", System.currentTimeMillis());
		contentResolver.insert(uri, contentValues);

	}
}

package com.Ichif1205.unisen;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.Ichif1205.unisen.R;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.widget.SimpleCursorAdapter;
import android.view.View;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TextView;

public class RankActivity extends Activity {
	private final String TAG = RankActivity.class.getSimpleName();
	private Cursor mCursor;
	private RadioGroup mTabGroup;
	private Typeface mFace;

	class RankCursorAdapter extends SimpleCursorAdapter {
		public RankCursorAdapter(Context context, int layout, Cursor c,
				String[] from, int[] to, int flg) {
			super(context, layout, c, from, to, flg);
			setTitle("Ranking");
			setViewBinder(new RankListViewBinder());
		}

		class RankListViewBinder implements SimpleCursorAdapter.ViewBinder {
			@Override
			public boolean setViewValue(View view, Cursor cursor,
					int columnIndex) {
				// 順位をセット
				if (columnIndex == cursor
						.getColumnIndex(DatabaseOpenHelper.COLUMN_ID)) {
					int rank = cursor.getPosition() + 1;
					((TextView) view).setText(String.valueOf(rank));
					((TextView) view).setTypeface(mFace);
					return true;
				}
				
				// 時間をセット
				if (columnIndex == cursor.getColumnIndex(DatabaseOpenHelper.COLUMN_AVERAGE)) {
					double score = cursor.getDouble(columnIndex);
					((TextView) view).setText(String.valueOf(score));
					((TextView) view).setTypeface(mFace);
					return true;
				}

				// 日付をセット
				if (columnIndex == cursor
						.getColumnIndex(DatabaseOpenHelper.COLUMN_DATE)) {
					long unixtime = cursor.getLong(columnIndex);
					Date date = new Date(unixtime);
					SimpleDateFormat format = new SimpleDateFormat(
							"yy/MM/dd HH:mm");
					((TextView) view).setText(format.format(date));
					((TextView) view).setTypeface(mFace);
					return true;
				}

				return false;
			}
		}
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_rank);
		setFonts();
		setEventListener();

		mCursor = Ranks.getRanks20Cursor(getContentResolver());
		
		showListView();
	}
	
	private void setFonts() {
		mFace = Utils.getFonts(getApplicationContext());
		
		// Tab
		((RadioButton) findViewById(R.id.twenty_ranks)).setTypeface(mFace);
		((RadioButton) findViewById(R.id.forty_ranks)).setTypeface(mFace);
		
		// Header
		((TextView) findViewById(R.id.header_rank)).setTypeface(mFace);
		((TextView) findViewById(R.id.header_time)).setTypeface(mFace);
		((TextView) findViewById(R.id.header_date)).setTypeface(mFace);
	}

	/**
	 * イベントリスナーをセット
	 */
	private void setEventListener() {
		mTabGroup = (RadioGroup) findViewById(R.id.tab_group);
		mTabGroup.setOnCheckedChangeListener(onTabChangeListener);
	}

	private OnCheckedChangeListener onTabChangeListener = new OnCheckedChangeListener() {

		@Override
		public void onCheckedChanged(RadioGroup group, int checkedId) {
			if (mTabGroup.getCheckedRadioButtonId() == R.id.twenty_ranks) {
				mCursor = Ranks.getRanks20Cursor(getContentResolver());
			} else {
				mCursor = Ranks.getRanks40Cursor(getContentResolver());
			}
			showListView();
		}
	};

	private void showListView() {

		// new String[] {...} と　new int[] {...}
		// の中身がそれぞれ対応している
		// 例えば、DBのscoreカラムはR.id.score_textに表示される
		ListAdapter adapter = new RankCursorAdapter(this,
				R.layout.time_rank_item, mCursor, new String[] {
						DatabaseOpenHelper.COLUMN_ID,
						DatabaseOpenHelper.COLUMN_AVERAGE,
						DatabaseOpenHelper.COLUMN_DATE }, new int[] {
						R.id.rank, R.id.average_text, R.id.date_text }, 0);

		ListView lv = (ListView) findViewById(R.id.rank_list);
		lv.setHeaderDividersEnabled(true);

		lv.setAdapter(adapter);
	}
}

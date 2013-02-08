package com.example.unisen;

import java.text.SimpleDateFormat;
import java.util.Date;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.widget.SimpleCursorAdapter;
import android.view.Menu;
import android.view.View;
import android.widget.CompoundButton;
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

	class RankCursorAdapter extends SimpleCursorAdapter {
		public RankCursorAdapter(Context context, int layout, Cursor c,
				String[] from, int[] to, int flg) {
			super(context, layout, c, from, to, flg);
			setViewBinder(new RankListViewBinder());
		}

		class RankListViewBinder implements SimpleCursorAdapter.ViewBinder {
			@Override
			public boolean setViewValue(View view, Cursor cursor,
					int columnIndex) {
				// 順位を設定
				if (columnIndex == cursor
						.getColumnIndex(DatabaseOpenHelper.COLUMN_ID)) {
					int rank = cursor.getPosition() + 1;
					((TextView) view).setText(String.valueOf(rank));
					return true;
				}

				// 日付を設定
				if (columnIndex == cursor
						.getColumnIndex(DatabaseOpenHelper.COLUMN_DATE)) {
					long unixtime = cursor.getLong(columnIndex);
					Date date = new Date(unixtime);
					SimpleDateFormat format = new SimpleDateFormat(
							"yy/MM/dd HH:mm");
					((TextView) view).setText(format.format(date));
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

		setEventListener();

		mCursor = Ranks.getRanks20Cursor(getContentResolver());
		
		showListView();
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

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_rank, menu);
		return true;
	}

}

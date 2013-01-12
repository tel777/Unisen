package com.example.unisen;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.os.CountDownTimer;
import android.widget.TextView;
import android.content.Intent;

public class CountActivity extends Activity {

	CountDownTimer timer;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_count);
		countDown();
	}

	private void countDown() {
		timer = new CountDownTimer( 4999, 1000 ){
			TextView count_timer = (TextView)findViewById(R.id.count_id);
			public void onTick(long millisUntilFinished){
				if ( (millisUntilFinished/1000)-1 == 0 ) {
					count_timer.setText("START!");
				} else {
					count_timer.setText(String.valueOf((millisUntilFinished/1000)-1));	
				}
			}
			public void onFinish(){
				
				Intent intent = new Intent(CountActivity.this, SubActivity.class);
				startActivity(intent);
				finish();
			}
		}.start();
	}
	
	//戻るボタンを押したときにカウントダウンストップする
	@Override
	public void onDestroy() {
		super.onDestroy();
		timer.cancel();
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_count, menu);
		return true;
	}

}

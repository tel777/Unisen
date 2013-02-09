package com.example.unisen;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.widget.TextView;

public class MainToResultActivity extends Activity {

	CountDownTimer timer;
	private long diff;
	private int count;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//FullScreen
		setContentView(R.layout.activity_main_to_result);
		Intent intent = getIntent();
		diff = intent.getLongExtra("ResultTime", 10);
		count = intent.getIntExtra("Count", 20);
		countDown();
	}

	private void countDown() {
		timer = new CountDownTimer( 2999, 1000 ){
			TextView count_timer = (TextView)findViewById(R.id.finish_id);
			public void onTick(long millisUntilFinished){
				Typeface face = Utils.getFonts(getApplicationContext()); 
				count_timer.setTypeface(face);
				count_timer.setText("Finish!");
			}
			public void onFinish(){
				//回数指定(20or40)
				Intent intent = new Intent(MainToResultActivity.this, ResultActivity.class);
				intent.putExtra("ResultTime", diff);
				intent.putExtra("Count", count);
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
	
}

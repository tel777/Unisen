package com.example.unisen;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.Window;
import android.view.WindowManager;
import android.os.CountDownTimer;
import android.widget.TextView;
import android.content.Intent;
import android.graphics.Typeface;

public class CountActivity extends Activity {

	CountDownTimer timer;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//FullScreen
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);  
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_count);
		countDown();
	}

	private void countDown() {
		timer = new CountDownTimer( 4999, 1000 ){
			TextView count_timer = (TextView)findViewById(R.id.count_id);
			public void onTick(long millisUntilFinished){
				Typeface face = Typeface.createFromAsset(getAssets(), "fonts/Ostrich_Black.ttf");			
				count_timer.setTypeface(face);
				if ( (millisUntilFinished/1000)-1 == 0 ) {
					count_timer.setText("START!");
				} else {
					count_timer.setText(String.valueOf((millisUntilFinished/1000)-1));	
				}
			}
			public void onFinish(){
				//回数指定(20or40)
			    Intent intentCount = getIntent();
			    int count = intentCount.getIntExtra("Count", 20);   //何回おすか(+1する)
			    
				Intent intent = new Intent(CountActivity.this, MainActivity.class);
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

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_count, menu);
		return true;
	}

}

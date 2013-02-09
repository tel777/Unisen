package com.Ichif1205.unisen;

import com.Ichif1205.unisen.R;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class TipsActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tips);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_tips, menu);
		return true;
	}

}

package com.example.unisen;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class HomeActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ImageView image = (ImageView)findViewById(R.id.title_icon_id);
        image.setImageResource(R.drawable.title_icon);
        
        Typeface face = Utils.getFonts(getApplicationContext());
        
        Button button_20_start = (Button)findViewById(R.id.start_20_id);
        button_20_start.setTypeface(face);
        Button button_40_start = (Button)findViewById(R.id.start_40_id);
        button_40_start.setTypeface(face);
        Button button_rank = (Button)findViewById(R.id.rank_id);
        button_rank.setTypeface(face);
        
        button_20_start.setOnClickListener(new View.OnClickListener() {
        	public void onClick(View view) {
        		Intent intent = new Intent(HomeActivity.this, CountActivity.class);
        		intent.putExtra("Count", 20);
        		startActivity(intent);
        	}
        });   
        
        // 40 Count Activity
        button_40_start.setOnClickListener(new View.OnClickListener() {
        	public void onClick(View view) {
        		Intent intent = new Intent(HomeActivity.this, CountActivity.class);
        		intent.putExtra("Count", 40);
        		startActivity(intent);
        	}
        });   
        
        // Rank Activity
        button_rank.setOnClickListener(new View.OnClickListener() {
        	public void onClick(View view) {
        		Intent intent = new Intent(HomeActivity.this, RankActivity.class);
        		startActivity(intent);
        	}
        });
    }
}

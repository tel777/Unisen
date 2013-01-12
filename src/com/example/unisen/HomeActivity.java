package com.example.unisen;

import android.app.Activity;
import android.content.Intent;
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
        
        ImageView image = (ImageView)findViewById(R.id.unisen_photo_id);
        image.setImageResource(R.drawable.unisen_photo);
        
        Button button_start = (Button)findViewById(R.id.start_id);
        Button button_rank = (Button)findViewById(R.id.rank_id);
        // for debug
        Button button_result = (Button)findViewById(R.id.result_id);
        Button button_sub = (Button)findViewById(R.id.sub_id);
        
        
        button_start.setOnClickListener(new View.OnClickListener() {
        	public void onClick(View view) {
        		Intent intent = new Intent(HomeActivity.this, CountActivity.class);
        		startActivity(intent);
        		finish();
        	}
        });    
        
        button_rank.setOnClickListener(new View.OnClickListener() {
        	public void onClick(View view) {
        		Intent intent = new Intent(HomeActivity.this, RankActivity.class);
        		startActivity(intent);
        		finish();
        	}
        });
        
        //for debug
        button_result.setOnClickListener(new View.OnClickListener() {
        	public void onClick(View view) {
        		Intent intent = new Intent(HomeActivity.this, ResultActivity.class);
        		startActivity(intent);
        		finish();
        	}
        });
        button_sub.setOnClickListener(new View.OnClickListener() {
        	public void onClick(View view) {
        		Intent intent = new Intent(HomeActivity.this, SubActivity.class);
        		startActivity(intent);
        		finish();
        	}
        });
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
}

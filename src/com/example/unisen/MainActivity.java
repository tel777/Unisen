package com.example.unisen;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        Button button_start = (Button)findViewById(R.id.start_id);
        Button button_rank = (Button)findViewById(R.id.rank_id);
        
        button_start.setOnClickListener(new View.OnClickListener() {
        	public void onClick(View view) {
        		Intent intent = new Intent(MainActivity.this, CountActivity.class);
        		startActivity(intent);
        		finish();
        	}
        });    
        
        button_rank.setOnClickListener(new View.OnClickListener() {
        	public void onClick(View view) {
        		Intent intent = new Intent(MainActivity.this, RankActivity.class);
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

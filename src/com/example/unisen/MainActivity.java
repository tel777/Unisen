package com.example.unisen;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Display;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.Window;
import android.view.WindowManager;

public class MainActivity extends Activity {
	private float Wran;
    private float Hran; 
    private int radius = 40;    //半径
    private int count = 10;    //何回おすか

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		//FullScreen
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);  
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		//画面サイズ確認
		WindowManager wm = (WindowManager)getSystemService(WINDOW_SERVICE);
		Display dp = wm.getDefaultDisplay();
		int width = dp.getWidth();
		int height = dp.getHeight();
		//random生成
        Wran = (float) Math.floor(Math.random() * ((width-radius - radius + 1)) + radius) ;
        Hran = (float) Math.floor(Math.random() * ((height-120 - radius + 1)) + radius) ;
		        
        //Toast.makeText(this, "s"+Wran, Toast.LENGTH_LONG).show();
        // main.xmlのGUIにはGraphicsViewがないため、
        // Viewクラスを継承したGraphicsViewを自分で作成
        // 画面に登録する
        setContentView(new GraphicsView(this, Wran, Hran ) );
	}
	/**
     * タッチイベント
     */
    public boolean onTouchEvent(MotionEvent event) {

        // タッチされた座標の取得
        int x1 = (int)event.getX();
        int y1 = (int)event.getY();
      
        // タッチされた座標がアイコン内かどうか
        if (Wran-radius < x1 && x1 < Wran + radius) {
            if (Hran-radius < y1 && y1 < Hran + radius ) {
          
            	count = count - 1;
            	if (count == 0){
            		Intent intent = new Intent(MainActivity.this, ResultActivity.class);
    				startActivity(intent);
            	}
            	//画面サイズ確認
            	WindowManager wm = (WindowManager)getSystemService(WINDOW_SERVICE);
            	Display dp = wm.getDefaultDisplay();
            	int width = dp.getWidth();
            	int height = dp.getHeight();
                //random生成
                Wran = (float) Math.floor(Math.random() * ((width-radius - radius + 1)) + radius) ;
                Hran = (float) Math.floor(Math.random() * ((height-120 - radius + 1)) + radius) ;
            }
        }

        // 再描画
        setContentView(new GraphicsView(this, Wran, Hran ) );

        return true;
    }

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

}

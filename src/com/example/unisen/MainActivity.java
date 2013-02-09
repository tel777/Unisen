package com.example.unisen;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.MotionEvent;
import android.view.Window;
import android.view.WindowManager;

public class MainActivity extends Activity {
	private float Wran;
    private float Hran; 
    private float radius;    //半径
    private int count;
    private int dummycount;		//回数を数える
    long start = System.currentTimeMillis();	//時間計測開始

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		//FullScreen
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);  
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		//回数指定(20or40)
	    Intent intent = getIntent();
	    count = intent.getIntExtra("Count", 20);   //何回おすか(+1する)
	    dummycount = count;
		//画面サイズ確認
		WindowManager wm = (WindowManager)getSystemService(WINDOW_SERVICE);
		Display dp = wm.getDefaultDisplay();
		int width = dp.getWidth();
		int height = dp.getHeight();	    
        //解像度対応
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        radius = (float)metrics.scaledDensity;
        radius = radius * 30;
		//random生成
        Wran = (float) Math.floor(Math.random() * ((width-radius - radius + 1)) + radius) ;
        Hran = (float) Math.floor(Math.random() * ((height-120 - radius + 1)) + radius) ;
		    
        //Toast.makeText(this, "s"+Wran, Toast.LENGTH_LONG).show();
        // main.xmlのGUIにはGraphicsViewがないため、
        // Viewクラスを継承したGraphicsViewを自分で作成
        // 画面に登録する
        setContentView(new GraphicsView(this, Wran, Hran, radius ) );
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
          
            	dummycount = dummycount - 1;
            	if (dummycount == 0){
            	    long stop = System.currentTimeMillis();
            	    long diff = stop - start -1000;
            	    Intent intent = new Intent(MainActivity.this, MainToResultActivity.class);
            	    intent.putExtra("ResultTime", diff);
            	    intent.putExtra("Count", count);
            	    startActivity(intent);
            	    finish();
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
        setContentView(new GraphicsView(this, Wran, Hran, radius ) );

        return true;
    }
    
}

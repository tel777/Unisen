package com.example.unisen;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.View;

public class GraphicsView extends View {
	private static float Wran;
	private static float Hran;
	private static float radius;
	
	public GraphicsView(Context context, float Wran, float Hran, float radius) {
		super(context);
		this.Wran = Wran;
		this.Hran = Hran;
		this.radius = radius;
	}
	  // onDrawをオーバーライドして描画処理を作成する。
    @Override
    protected void onDraw(Canvas canvas) {
        //clear
        //canvas.drawColor(Color.WHITE);
        
        // Paintを定義(描画をする際のペン的なイメージでOK)
        Paint paint = new Paint();

        // 色セット
        paint.setARGB(255,87,217,158);
        //paint.setColor(Color.RED);
        // Viewの描画エリアはcanvasという名前になっているため、
        // ここにdwawCircleで点を描く
        // (円中心座標X、円中心座標Y、半径幅px)となります。
        // 数字fというのは、その数字をfloatで扱うという意味です。
        // 上記でも描画はできますが、なめらかな円ではないです。
        // そこで下記のアンチエイリアス(円滑化)をONにします。
        paint.setAntiAlias(true);
        
        canvas.drawCircle(Wran, Hran, radius, paint);
    }

}

package com.Ichif1205.unisen;

import android.content.Context;
import android.graphics.Typeface;

public class Utils {
	
	
	/**
	 * フォントを取得
	 * @param context
	 * @return
	 */
	public static Typeface getFonts(Context context) {
        return Typeface.createFromAsset(context.getAssets(), "fonts/Ostrich_Black.ttf");
	}

}

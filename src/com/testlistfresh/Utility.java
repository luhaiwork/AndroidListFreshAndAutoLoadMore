package com.testlistfresh;

import android.content.Context;
import android.widget.Toast;

public class Utility {
	/**
	 * 防止toast重复出现
	 */
	private static Toast mToast = null;
	public static void showToast(Context context, String text){
		showToast(context,text,Toast.LENGTH_SHORT);
	}
	public static void showToast(Context context, String text, int duration) {
		if (mToast == null) {
			mToast = Toast.makeText(context, text, duration);
		} else {
			mToast.setText(text);
			mToast.setDuration(duration);
		}
		mToast.show();
	}
}

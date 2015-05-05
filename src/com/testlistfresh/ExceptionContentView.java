package com.testlistfresh;

import android.app.Activity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;

import com.kstapp.wanshida.R;

/**
 * 异常提示View
 * 
 * 
 */
public class ExceptionContentView {
	/** 无数据 */
	public static final int NO_NET = 0;
	/** 无网络 */
	public static final int NO_DATA = 1;
	/** 我的订单无数据 **/
	public static final int NO_DATA_ORDER = 10;

	/** 异常提示标题、内容TextView */
	private static TextView excTvHead, excTvBody;
	/** 异常提示ImageVIew */
	private static ImageView excIv;

	/*
	 * private static Activity context; private static LayoutInflater inflater;
	 * private static LinearLayout exceptionLL; private Drawable excDrawable;
	 * private TextView excTv;
	 */
	public static LinearLayout exceptView(Activity context, String msgHead,
			String msgBody, int excCode) {
		LayoutInflater inflater = context.getLayoutInflater();
		LinearLayout exceptionLL = (LinearLayout) inflater.inflate(
				R.layout.exception_layout, null);
		LayoutParams layoutParams = new RelativeLayout.LayoutParams(
				RelativeLayout.LayoutParams.MATCH_PARENT,
				RelativeLayout.LayoutParams.MATCH_PARENT);
		layoutParams.addRule(RelativeLayout.CENTER_IN_PARENT);
		exceptionLL.setLayoutParams(layoutParams);
		exceptionLL.setGravity(Gravity.CENTER_HORIZONTAL);
		excTvHead = (TextView) exceptionLL.findViewById(R.id.msg_tv_bg);
		excTvBody = (TextView) exceptionLL.findViewById(R.id.msg_tv_small);
		excIv = (ImageView) exceptionLL.findViewById(R.id.excption_iv);

		// Drawable excDrawable = null;
		switch (excCode) {
		case NO_NET:
			excIv.setImageResource(R.drawable.no_net);
			if (msgHead == null && msgBody == null) {
				excTvHead.setText(context.getString(R.string.no_net_head));
				excTvBody.setText(context.getString(R.string.no_net_body));
			}
			break;
		case NO_DATA:
			excIv.setImageResource(R.drawable.no_data);
			if (msgHead == null && msgBody == null) {
				excTvHead.setText(context.getString(R.string.no_data_head));
				excTvBody.setText(context.getString(R.string.no_data_body));
			}
			break;
		case NO_DATA_ORDER:
			excIv.setImageResource(R.drawable.no_data);
			if (msgHead == null && msgBody == null) {
				excTvHead.setText(context.getString(R.string.no_data_head));
				excTvBody.setText(context.getString(R.string.no_data_order));
			}
			break;
		default:
			break;
		}
		if (msgHead != null)
			excTvHead.setText(msgHead);
		if (msgBody != null)
			excTvBody.setText(msgBody);
		return exceptionLL;
	}

	public static LinearLayout exceptView(Activity context, int excCode) {
		return exceptView(context, null, null, excCode);
	}
}

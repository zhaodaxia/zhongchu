package com.cai.vegetables.widget;

import com.cai.vegetables.R;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

/**
 * 自定义toast能输入较多文字的
 * 
 * @author yang
 */
public class ToastCommomBig {
	private static ToastCommomBig toastCommom;

	private Toast toast;

	private ToastCommomBig() {
	}

	public static ToastCommomBig createToastConfig() {
		if (toastCommom == null) {
			toastCommom = new ToastCommomBig();
		}
		return toastCommom;
	}

	/**
	 * 显示Toast
	 * 
	 * @param context
	 * @param tvString
	 */

	public void ToastShow(Context context, String tvString) {
		View layout = LayoutInflater.from(context).inflate(R.layout.toast_dialogbig, null);
		TextView text = (TextView) layout.findViewById(R.id.toast_des);
		text.setText(tvString);
		if (toast == null) {
			toast = new Toast(context);
			toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
			toast.setDuration(Toast.LENGTH_SHORT);
			toast.setView(layout);
			toast.show();
		} else {
			toast.setView(layout);
			toast.show();
		}

	}

}

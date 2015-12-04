package com.cai.vegetables.widget;

import com.cai.vegetables.R;
import com.cai.vegetables.city.CityPicker;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

/**
 * 自定义选择省市区
 * 
 * @author yang
 * 
 */
public class DialogChooseAdress  {
	private Context context;
	private Dialog dialog;
	private Display display;
	private SexClickListener listener;

	public DialogChooseAdress(Context context) {
		this.context = context;
		WindowManager windowManager = (WindowManager) context
				.getSystemService(Context.WINDOW_SERVICE);
		display = windowManager.getDefaultDisplay();
	}

	@SuppressLint("InflateParams")
	@SuppressWarnings("deprecation")
	public DialogChooseAdress builder() {
		// 获取Dialog布局
		View view = LayoutInflater.from(context).inflate(
				R.layout.city_select, null);

		// 设置Dialog最小宽度为屏幕宽度
		view.setMinimumWidth(display.getWidth());

		// 获取自定义Dialog布局中的控件
		TextView save=(TextView) view.findViewById(R.id.adress_Save);
		final CityPicker citypicker=(CityPicker) view.findViewById(R.id.citypicker);
		save.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				listener.getAdress(citypicker.getCity_string());
				dialog.dismiss();
			}
		});
		// 定义Dialog布局和参数
		dialog = new Dialog(context, R.style.ActionSheetDialogStyle);
		dialog.setContentView(view);
		Window dialogWindow = dialog.getWindow();
		dialogWindow.setGravity(Gravity.LEFT | Gravity.BOTTOM);
		WindowManager.LayoutParams lp = dialogWindow.getAttributes();
		lp.x = 0;
		lp.y = 0;
		dialogWindow.setAttributes(lp);
		initdata();
		return this;
	}


	private void initdata() {
		// 添加change事件
  
	}

	public DialogChooseAdress setCancelable(boolean cancel) {
		dialog.setCancelable(cancel);
		return this;
	}

	public DialogChooseAdress setCanceledOnTouchOutside(boolean cancel) {
		dialog.setCanceledOnTouchOutside(cancel);
		return this;
	}

	public void show() {
		dialog.show();
	}

	public interface SexClickListener {
		void getAdress(String adress);
	}

	public void setOnSheetItemClickListener(SexClickListener listener){
		this.listener=listener;
	}

}

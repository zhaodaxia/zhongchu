package com.cai.vegetables.widget;

import com.cai.vegetables.R;
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
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * 自定义dialog动画类
 * 
 * @author sto_LiHui
 * 
 */
public class SheetDialogChooseSex {
	private Context context;
	private Dialog dialog;
	private RelativeLayout man;
	private RelativeLayout woman;
	private Display display;
	private SexClickListener listener;

	public SheetDialogChooseSex(Context context) {
		this.context = context;
		WindowManager windowManager = (WindowManager) context
				.getSystemService(Context.WINDOW_SERVICE);
		display = windowManager.getDefaultDisplay();
	}

	@SuppressLint("InflateParams")
	@SuppressWarnings("deprecation")
	public SheetDialogChooseSex builder() {
		// 获取Dialog布局
		View view = LayoutInflater.from(context).inflate(
				R.layout.choosesex_actionsheet, null);

		// 设置Dialog最小宽度为屏幕宽度
		view.setMinimumWidth(display.getWidth());

		// 获取自定义Dialog布局中的控件
		man = (RelativeLayout) view.findViewById(R.id.sex_man);
		woman = (RelativeLayout) view.findViewById(R.id.sex_woman);
		
		man.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				listener.onClick(0);
				dialog.dismiss();
			}
		});
		
		woman.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				listener.onClick(1);
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

		return this;
	}


	public SheetDialogChooseSex setCancelable(boolean cancel) {
		dialog.setCancelable(cancel);
		return this;
	}

	public SheetDialogChooseSex setCanceledOnTouchOutside(boolean cancel) {
		dialog.setCanceledOnTouchOutside(cancel);
		return this;
	}

	public void show() {
		dialog.show();
	}

	public interface SexClickListener {
		void onClick(int which);
	}

	public void setOnSheetItemClickListener(SexClickListener listener){
		this.listener=listener;
	}
}

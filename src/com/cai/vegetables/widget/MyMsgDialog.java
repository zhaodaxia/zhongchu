package com.cai.vegetables.widget;

import com.cai.vegetables.R;

import android.app.Dialog;
import android.content.Context;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

/**
 * 弹出框
 * 
 * 带标题和内容  无图片
 * 
 * @author yang
 * 
 */
public class MyMsgDialog {

	private Context context;
	private Dialog dialog;
	private Display display;
	private TextView cancel_title;
	private TextView cancel_content;
	private Button cancel_obtn;
	private Button cancel_cbtn;

	public MyMsgDialog(Context context) {
		this.context = context;
		WindowManager windowManager = (WindowManager) context
				.getSystemService(Context.WINDOW_SERVICE);
		display = windowManager.getDefaultDisplay();
	}

	public MyMsgDialog builder() {
		// 获取Dialog布局
		View view = LayoutInflater.from(context).inflate(R.layout.cancel_order,
				null);
		cancel_title = (TextView) view.findViewById(R.id.cancel_title);
		cancel_content = (TextView) view.findViewById(R.id.cancel_content);
		// 定义Dialog布局和参数
		dialog = new Dialog(context, R.style.AlertDialogStyle);
		cancel_obtn = (Button) view.findViewById(R.id.cancel_obtn);
		cancel_cbtn = (Button) view.findViewById(R.id.cancel_cbtn);
		dialog.setContentView(view);
		// 调整dialog背景大小
		view.setMinimumWidth(500);
		view.setMinimumHeight(220);
		return this;
	}

	public void setContenthide(){
		cancel_content.setVisibility(View.GONE);
	}

	public void setTitle(String msg) {
		if ("".equals(msg)) {
			cancel_title.setText("");
		} else {
			cancel_title.setText(msg);
		}
	}
	public void setContent(String msg) {
		if ("".equals(msg)) {
			cancel_content.setText("");
		} else {
			cancel_content.setText(msg);
		}
	}

	public void setPositiveButton(String text,
			final OnClickListener listener) {
		cancel_obtn.setVisibility(View.VISIBLE);
		if ("".equals(text)) {
			cancel_obtn.setText("确定");
		} else {
			cancel_obtn.setText(text);
		}
		cancel_obtn.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					listener.onClick(v);
					dialog.dismiss();
				}
			});
	}

	public void setNegativeButton(String text,
			final OnClickListener listener) {
		cancel_cbtn.setVisibility(View.VISIBLE);
		if ("".equals(text)) {
			cancel_cbtn.setText("取消");
		} else {
			cancel_cbtn.setText(text);
		}
		cancel_cbtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if (listener != null) {
					listener.onClick(v);
				} else {
					dialog.dismiss();
				}
			}
		});
	}

	public void show() {
		dialog.show();
	}
}

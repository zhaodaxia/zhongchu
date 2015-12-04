package com.cai.vegetables.widget;

import com.cai.vegetables.R;

import android.app.Dialog;
import android.content.Context;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * 弹出框
 * 
 * @author wangbin
 * 
 */
public class MyShopDialog {

	private Context context;
	private Dialog dialog;
	private Display display;
	private RelativeLayout rlDialog;
	private TextView tvMes;
	private TextView tvShop;
	private ImageView ivDialog;
	private LinearLayout llShop;
	private TextView tvSure;
	private TextView tvCancel;

	public MyShopDialog(Context context) {
		this.context = context;
		WindowManager windowManager = (WindowManager) context
				.getSystemService(Context.WINDOW_SERVICE);
		display = windowManager.getDefaultDisplay();
	}

	public MyShopDialog builder() {
		// 获取Dialog布局
		View view = LayoutInflater.from(context).inflate(R.layout.shop_dialog,
				null);
		rlDialog = (RelativeLayout) view.findViewById(R.id.rlDialog);
		tvMes = (TextView) view.findViewById(R.id.tvMes);
		tvShop = (TextView) view.findViewById(R.id.tvShop);
		ivDialog = (ImageView) view.findViewById(R.id.ivDialog);
		llShop = (LinearLayout) view.findViewById(R.id.llShop);
		// 定义Dialog布局和参数
		dialog = new Dialog(context, R.style.AlertDialogStyle);
		tvSure = (TextView) view.findViewById(R.id.tvSure);
		tvCancel = (TextView) view.findViewById(R.id.tvCancel);
		dialog.setContentView(view);
		// 调整dialog背景大小
		rlDialog.setLayoutParams(new FrameLayout.LayoutParams((int) (display
				.getWidth() * 0.7),
				android.view.ViewGroup.LayoutParams.WRAP_CONTENT));
		return this;
	}

	public MyShopDialog setIv(int resource) {
		ivDialog.setBackgroundResource(resource);
		return this;
	}

	public MyShopDialog setMsg(String msg) {
		if ("".equals(msg)) {
			tvMes.setText("内容");
		} else {
			tvMes.setText(msg);
		}
		return this;
	}

	public MyShopDialog setPositiveButton(String text,
			final OnClickListener listener, int type) {
		if ("".equals(text)) {
			tvShop.setText("确定");
		} else {
			tvShop.setText(text);
		}
		if (type == 0) {
			tvShop.setVisibility(View.VISIBLE);
			tvShop.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					listener.onClick(v);
					dialog.dismiss();
				}
			});
		} else if (type == 1) {
			llShop.setVisibility(View.VISIBLE);
			tvSure.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					listener.onClick(v);
					dialog.dismiss();
				}
			});
		}
		return this;
	}

	public MyShopDialog setNegativeButton(String text,
			final OnClickListener listener) {
		if ("".equals(text)) {
			tvCancel.setText("取消");
		} else {
			tvCancel.setText(text);
		}
		tvCancel.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if (listener != null) {
					listener.onClick(v);
				} else {
					dialog.dismiss();
				}
			}
		});
		return this;
	}

	public void show() {
		dialog.show();
	}
}

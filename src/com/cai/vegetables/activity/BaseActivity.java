package com.cai.vegetables.activity;

import com.cai.vegetables.R;
import com.lidroid.xutils.ViewUtils;
import com.tandong.sa.activity.SmartFragmentActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * 基础Activity
 * 
 * @author wangbin
 * 
 */
@SuppressLint("NewApi")
public abstract class BaseActivity extends SmartFragmentActivity {
	private TextView tv_title;
	private TextView tvRight;
	private TextView tvRight3;
	private RelativeLayout rlRight;
	private ImageView ivRight;
	private RelativeLayout rlRight2;
	private RelativeLayout rlRight3;
	private ImageView ivRight2;
	public Intent intent;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// 手机窗口设置无标题
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setLayout();
		// 注入控件
		ViewUtils.inject(this);
		init(savedInstanceState);
	}

	/**
	 * 设置布局
	 */
	public abstract void setLayout();

	public abstract void init(Bundle savedInstanceState);

	/**
	 * 设置顶部标题
	 * 
	 * @param title
	 */
	public void setTopTitle(String title) {
		tv_title = (TextView) findViewById(R.id.tv_title);
		if (title != null) {
			tv_title.setText(title);
		}
	}

	/**
	 * 顶部右边按键
	 * 
	 * @param rightText
	 */
	public void setRightBtn(String rightText, OnClickListener onClick) {
		tvRight = (TextView) findViewById(R.id.tvRight);
		tvRight.setVisibility(View.VISIBLE);
		if (!TextUtils.isEmpty(rightText)) {
			tvRight.setText(rightText);
		}
		if (onClick != null) {
			tvRight.setOnClickListener(onClick);
		}
	}

	/**
	 * 顶部右边按键2
	 * 
	 * @param rightText
	 */
	public void setRightBtn2(String rightText, OnClickListener clickListener) {
		tvRight = (TextView) findViewById(R.id.tvRight);
		tvRight.setVisibility(View.VISIBLE);
		if (!TextUtils.isEmpty(rightText)) {
			tvRight.setText(rightText);
		}
		if (clickListener != null) {
			tvRight.setOnClickListener(clickListener);
		}
	}

	/**
	 * 顶部右边按键3 距离右边45dp 可设置背景和文字
	 * 文字默认背景为紫色
	 */
	public void setRightBtn3(int resource, String rightText, OnClickListener clickListener) {
		rlRight3 = (RelativeLayout) findViewById(R.id.rl_right3);
		tvRight3 = (TextView) findViewById(R.id.tvRight3);
		rlRight3.setVisibility(View.VISIBLE);
		rlRight3.setBackgroundResource(resource);
		if (!TextUtils.isEmpty(rightText)) {
			tvRight3.setText(rightText);
		}
		if (clickListener != null) {
			rlRight3.setOnClickListener(clickListener);
		}
	}

	public void setRightTop(int resource, OnClickListener clickListener) {
		rlRight = (RelativeLayout) findViewById(R.id.rl_right);
		ivRight = (ImageView) findViewById(R.id.ivRight);
		ivRight.setBackgroundResource(resource);
		rlRight.setVisibility(View.VISIBLE);
		if (clickListener != null) {
			rlRight.setOnClickListener(clickListener);
		}
	}

	/**
	 * 宽高为包裹布局的右上角图标显示
	 * 
	 * @param resource
	 * @param clickListener
	 */
	public void setRightTop2(int resource, OnClickListener clickListener) {
		rlRight2 = (RelativeLayout) findViewById(R.id.rl_right2);
		ivRight2 = (ImageView) findViewById(R.id.ivRight2);
		ivRight2.setBackgroundResource(resource);
		rlRight2.setVisibility(View.VISIBLE);
		if (clickListener != null) {
			rlRight2.setOnClickListener(clickListener);
		}
	}

	/**
	 * 返回
	 * 
	 * @param view
	 */
	public void goback(View view) {
		finish();
	}
}
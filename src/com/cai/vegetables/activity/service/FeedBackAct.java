package com.cai.vegetables.activity.service;

import com.cai.vegetables.R;
import com.cai.vegetables.activity.BaseActivity;
import com.cai.vegetables.widget.ToastCommom;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * 反馈投诉
 * 
 * @author yang
 *
 */
public class FeedBackAct extends BaseActivity {
	@ViewInject(R.id.feedback_btn)
	private Button feedback_btn;

	@Override
	public void setLayout() {
		setContentView(R.layout.feedback);
	}

	@Override
	public void init(Bundle savedInstanceState) {
		setTopTitle("反馈投诉");
	}

	@OnClick(R.id.feedback_btn)
	public void todo(View v) {
		switch (v.getId()) {
		case R.id.feedback_btn:
			ToastCommom.createToastConfig().ToastShow(FeedBackAct.this, "内容已提交，请等待审核..\n将在1-3个工作日内显示结果");
			break;

		default:
			break;
		}
	}
}

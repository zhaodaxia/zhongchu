package com.cai.vegetables.activity.service;

import com.cai.vegetables.R;
import com.cai.vegetables.activity.BaseActivity;
import com.lidroid.xutils.view.annotation.ViewInject;

import android.os.Bundle;
import android.webkit.WebView;

/**
 * 退货细则
 * 
 * @author yang
 *
 */
public class GoodsBackAct extends BaseActivity {
	@ViewInject(R.id.aboutus_wv)
	private WebView aboutus_wv;

	@Override
	public void setLayout() {
		setContentView(R.layout.aboutus);
	}

	@Override
	public void init(Bundle savedInstanceState) {
		setTopTitle("退货细则");
	}

}

package com.cai.vegetables.activity.myself;

import com.cai.vegetables.R;
import com.cai.vegetables.activity.BaseActivity;
import com.lidroid.xutils.view.annotation.ViewInject;

import android.os.Bundle;
import android.webkit.WebView;

/**
 * 如何使用积分
 * 
 * @author yang
 *
 */
public class HowToUseAct extends BaseActivity {
	@ViewInject(R.id.aboutus_wv)
	private WebView aboutus_wv;

	@Override
	public void setLayout() {
		setContentView(R.layout.aboutus);
	}

	@Override
	public void init(Bundle savedInstanceState) {
		setTopTitle("如何使用积分");
	}

}

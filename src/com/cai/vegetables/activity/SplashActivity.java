package com.cai.vegetables.activity;

import android.os.Bundle;
import android.os.Handler;

import com.cai.vegetables.R;
import com.cai.vegetables.activity.user.LoginActivity;

/**
 * 闪屏页面
 * 
 * @author wangbin
 * 
 */
public class SplashActivity extends BaseActivity {


	@Override
	public void setLayout() {
		setContentView(R.layout.activity_splash);
	}

	@Override
	public void init(Bundle savedInstanceState) {

		new Handler().postDelayed(new Runnable() {

			@Override
			public void run() {
				gotoActivity(MainActivity.class, true);
			}
		}, 0);
	
	}
}

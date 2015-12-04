package com.cai.vegetables.activity.service;

import com.cai.vegetables.R;
import com.cai.vegetables.activity.BaseActivity;

import android.os.Bundle;

/**
 * 联系客服
 * 
 * @author yang
 *
 */
public class LinkServiceAct extends BaseActivity {

	@Override
	public void setLayout() {
		setContentView(R.layout.linkservice);
	}

	@Override
	public void init(Bundle savedInstanceState) {
		setTopTitle("联系客服");
	}

}

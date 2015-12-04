package com.cai.vegetables.activity.shop;

import android.os.Bundle;

import com.cai.vegetables.R;
import com.cai.vegetables.activity.BaseActivity;

/**
 * 运费说明
 * @author wangbin
 *
 */
public class ShippingActivity extends BaseActivity{

	@Override
	public void setLayout() {
		setContentView(R.layout.activity_shipping);
	}

	@Override
	public void init(Bundle savedInstanceState) {
		setTopTitle("运费说明");
	}

}

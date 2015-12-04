package com.cai.vegetables.activity.shop;

import android.os.Bundle;

import com.cai.vegetables.R;
import com.cai.vegetables.activity.BaseActivity;

/**
 * 支付成功
 * @author wangbin
 *
 */
public class PaySucessActivity extends BaseActivity{

	@Override
	public void setLayout() {
		setContentView(R.layout.activity_pay_sucess);
	}

	@Override
	public void init(Bundle savedInstanceState) {
        setTopTitle("订单支付成功");		
	}

}

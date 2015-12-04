package com.cai.vegetables.activity.shop;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.cai.vegetables.R;
import com.cai.vegetables.activity.BaseActivity;
import com.lidroid.xutils.view.annotation.event.OnClick;

/**
 * 确认订单
 * @author wangbin
 *
 */
public class SureOrderActivity extends BaseActivity{

	@Override
	public void setLayout() {
		setContentView(R.layout.activity_sure_order);
	}

	@Override
	public void init(Bundle savedInstanceState) {
		setTopTitle("确认订单");
	}
	
	@OnClick({R.id.rlAddress,R.id.tvSureOrder})
	public void toClick(View v){
		Intent intent=null;
		switch(v.getId()){
		//没有选择地址
		case R.id.rlAddress:
			gotoActivity(SelectAddressActivity.class, false);
			break;
		case R.id.tvSureOrder:
			intent=new Intent(SureOrderActivity.this,OrderPayActivity.class);
			startActivity(intent);
			break;
		}
	}

}

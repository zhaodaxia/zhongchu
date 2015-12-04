package com.cai.vegetables.activity.cookbook;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.cai.vegetables.R;
import com.cai.vegetables.activity.BaseActivity;
import com.cai.vegetables.activity.shop.OrderPayActivity;
import com.cai.vegetables.activity.shop.SelectAddressActivity;
import com.cai.vegetables.adapter.FoodOrderAdapter;
import com.leaf.library.widget.MyListView;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;

/**
 * 确认菜谱订单
 * @author wangbin
 *
 */
public class FoodOrderActivity extends BaseActivity{
	
	@ViewInject(R.id.lvFood)
	private MyListView lvFood;

	@Override
	public void setLayout() {
		setContentView(R.layout.activity_food_order);
	}

	@Override
	public void init(Bundle savedInstanceState) {
		setTopTitle("确认订单");
		lvFood.setAdapter(new FoodOrderAdapter(this));
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
			intent=new Intent(FoodOrderActivity.this,OrderPayActivity.class);
			startActivity(intent);
			break;
		}
	}
}

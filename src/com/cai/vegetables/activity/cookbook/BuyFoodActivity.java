package com.cai.vegetables.activity.cookbook;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ListView;

import com.cai.vegetables.R;
import com.cai.vegetables.activity.BaseActivity;
import com.cai.vegetables.adapter.BuyFoodAdapter;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;

/**
 * 购买主要食材
 * @author wangbin
 *
 */
public class BuyFoodActivity extends BaseActivity{
    @ViewInject(R.id.lvBuy)
    private ListView lvBuy;//适配器
	
	@Override
	public void setLayout() {
		setContentView(R.layout.activity_buy_food);
	}

	@Override
	public void init(Bundle savedInstanceState) {
		setTopTitle("购买主要食材");
		setRightBtn2("取消", new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				finish();
			}
		});
		lvBuy.setAdapter(new BuyFoodAdapter(this));
	}
	
	@OnClick(R.id.tvSett)
	public void toClick(View v){
		gotoActivity(FoodOrderActivity.class, false);
	}

}

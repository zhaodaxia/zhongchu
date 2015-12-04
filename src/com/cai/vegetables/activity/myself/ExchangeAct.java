package com.cai.vegetables.activity.myself;

import com.cai.vegetables.R;
import com.cai.vegetables.activity.BaseActivity;
import com.cai.vegetables.activity.shop.OrderPayActivity;
import com.cai.vegetables.activity.shop.SelectAddressActivity;
import com.cai.vegetables.activity.shop.SureOrderActivity;
import com.cai.vegetables.widget.MyMsgDialog;
import com.cai.vegetables.widget.MyShopDialog;
import com.lidroid.xutils.view.annotation.event.OnClick;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

/**
 * 积分兑换详情
 * 
 * @author yang
 *
 */
public class ExchangeAct extends BaseActivity {

	@Override
	public void setLayout() {
		setContentView(R.layout.exchangeact);
	}

	@Override
	public void init(Bundle savedInstanceState) {
		setTopTitle("兑换确认");
	}
	
	@OnClick({R.id.rlAddress,R.id.tvSureOrder,R.id.exchange_btn})
	public void toClick(View v){
		switch(v.getId()){
		//没有选择地址
		case R.id.rlAddress:
			gotoActivity(SelectAddressActivity.class, false);
			break;
		case R.id.exchange_btn:
			MyMsgDialog dialog = new MyMsgDialog(this).builder();
			dialog.setTitle("您确认要兑换此商品吗？");
			dialog.setContent("");
			dialog.setPositiveButton("确定", new OnClickListener() {
				@Override
				public void onClick(View v) {
					
				}
			});
			dialog.setNegativeButton("取消", null);
			dialog.show();
			break;
		}
	}

}

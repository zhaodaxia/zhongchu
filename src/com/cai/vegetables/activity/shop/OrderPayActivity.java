package com.cai.vegetables.activity.shop;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import com.cai.vegetables.R;
import com.cai.vegetables.activity.BaseActivity;
import com.cai.vegetables.widget.ToastCommom;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;

/**
 * 订单支付
 * @author wangbin
 *
 */
public class OrderPayActivity extends BaseActivity{
    @ViewInject(R.id.cbZfb)
    private CheckBox cbZfb;
    @ViewInject(R.id.cbWx)
    private CheckBox cbWx;
    @ViewInject(R.id.cbXj)
    private CheckBox cbXj;
    
    @ViewInject(R.id.tvSure)
    private TextView tvSure;
    
    //0支付宝，1微信，2现金
    private int POSITION;
	
	@Override
	public void setLayout() {
		setContentView(R.layout.activity_order_pay);
	}

	@Override
	public void init(Bundle savedInstanceState) {
		setTopTitle("订单支付");
	}
    
	@OnClick({R.id.tvShipp,R.id.rlZfb,R.id.rlWx,R.id.rlXj,R.id.tvSure})
	public void toClick(View v){
		switch(v.getId()){
		case R.id.tvShipp:
			gotoActivity(ShippingActivity.class, false);
			break;
		case R.id.rlZfb:
			POSITION=0;
			cbZfb.setChecked(true);
			cbWx.setChecked(false);
			cbXj.setChecked(false);
			break;
		case R.id.rlWx:
			POSITION=1;
			cbZfb.setChecked(false);
			cbWx.setChecked(true);
			cbXj.setChecked(false);
			break;
		case R.id.rlXj:
			POSITION=2;
			cbZfb.setChecked(false);
			cbWx.setChecked(false);
			cbXj.setChecked(true);
			break;
		//确认支付
		case R.id.tvSure:
			if(POSITION==0){
				gotoActivity(PaySucessActivity.class, false);
			}else{
				ToastCommom.createToastConfig().ToastShow(getApplicationContext(), "支付失败");
				tvSure.setText("重新支付");
			}
			break;
		}
	}
}

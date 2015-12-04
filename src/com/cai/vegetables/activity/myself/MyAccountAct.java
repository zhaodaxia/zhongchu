package com.cai.vegetables.activity.myself;

import com.cai.vegetables.R;
import com.cai.vegetables.activity.BaseActivity;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;

import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

/**
 * 我的账户
 * 
 * @author yang
 *
 */
public class MyAccountAct extends BaseActivity {
	@ViewInject(R.id.myaccount_rl)
	private RelativeLayout myaccount_rl;// 跳往积分商城
	@ViewInject(R.id.myaccount_howtouse)
	private RelativeLayout myaccount_howtouse;// 如何使用
	@ViewInject(R.id.myaccount_djq)
	private RelativeLayout myaccount_djq;// 跳往我的代金券界面

	@Override
	public void setLayout() {
		setContentView(R.layout.myaccount);
	}

	@Override
	public void init(Bundle savedInstanceState) {
		setTopTitle("我的账户");
	}

	@OnClick({ R.id.myaccount_rl, R.id.myaccount_howtouse, R.id.myaccount_djq })
	public void todo(View v) {
		switch (v.getId()) {
		case R.id.myaccount_rl:
			gotoActivity(IntegralActivity.class, false);
			break;
		case R.id.myaccount_howtouse:
			gotoActivity(HowToUseAct.class, false);
			break;
		case R.id.myaccount_djq:
			gotoActivity(MyDjqAct.class, false);
			break;
		default:
			break;
		}
	}

}

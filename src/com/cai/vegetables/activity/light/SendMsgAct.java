package com.cai.vegetables.activity.light;

import com.cai.vegetables.R;
import com.cai.vegetables.activity.BaseActivity;

import android.os.Bundle;

/**
 * 给司机留言界面
 * 
 * @author yang
 *
 */
public class SendMsgAct extends BaseActivity {

	@Override
	public void setLayout() {
		setContentView(R.layout.sendmsg);
	}

	@Override
	public void init(Bundle savedInstanceState) {
		setTopTitle("给司机留言");
	}

}

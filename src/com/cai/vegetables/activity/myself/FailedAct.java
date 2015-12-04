package com.cai.vegetables.activity.myself;

import com.cai.vegetables.R;
import com.cai.vegetables.activity.BaseActivity;

import android.os.Bundle;

/**
 * 申请失败
 * 
 * @author yang
 *
 */
public class FailedAct extends BaseActivity {

	@Override
	public void setLayout() {
		setContentView(R.layout.failedact);
	}

	@Override
	public void init(Bundle savedInstanceState) {
		// 标题名字由哪个界面跳转过来设置成哪个，这里显示成申请成为商户
		setTopTitle("申请成为商户");
	}

}

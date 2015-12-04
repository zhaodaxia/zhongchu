package com.cai.vegetables.activity.user;


import android.os.Bundle;

import com.cai.vegetables.R;
import com.cai.vegetables.activity.BaseActivity;

/**
 * 忘记密码
 * @author wangbin
 *
 */
public class FindPwdActivity extends BaseActivity{
    
	
	@Override
	public void setLayout() {
		setContentView(R.layout.activity_find_pwd);
	}

	@Override
	public void init(Bundle savedInstanceState) {
		setTopTitle("找回密码");
	}
}

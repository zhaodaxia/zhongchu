package com.cai.vegetables.activity.myself;

import com.cai.vegetables.R;
import com.cai.vegetables.activity.BaseActivity;

import android.os.Bundle;

/**
 * 申请成为配送员
 * 
 * @author yang
 *
 */
public class CourierAct extends BaseActivity {

	@Override
	public void setLayout() {
		setContentView(R.layout.courieract);
	}

	@Override
	public void init(Bundle savedInstanceState) {
		setTopTitle("申请成为配送员");
	}

}

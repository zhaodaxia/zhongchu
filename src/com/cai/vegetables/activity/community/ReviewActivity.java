package com.cai.vegetables.activity.community;

import android.os.Bundle;
import android.view.View;

import com.cai.vegetables.R;
import com.cai.vegetables.activity.BaseActivity;
import com.cai.vegetables.widget.ToastCommom;
import com.lidroid.xutils.view.annotation.event.OnClick;

/**
 * 写点评
 * @author wangbin
 *
 */
public class ReviewActivity extends BaseActivity{

	@Override
	public void setLayout() {
		setContentView(R.layout.activity_review);
	}

	@Override
	public void init(Bundle savedInstanceState) {
		setTopTitle("写点评");
	}
	
	@OnClick({R.id.ivPhoto,R.id.tvSucess})
	public void toClick(View v){
		switch(v.getId()){
		//照相
		case R.id.ivPhoto:
			
			break;
		case R.id.tvSucess:
			ToastCommom.createToastConfig().ToastShow(getApplicationContext(), "点评成功！");
			break;
		}
	}

}

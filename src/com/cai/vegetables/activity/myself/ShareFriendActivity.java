package com.cai.vegetables.activity.myself;

import com.cai.vegetables.R;
import com.cai.vegetables.activity.BaseActivity;
import com.cai.vegetables.widget.ToastCommom;
import com.lidroid.xutils.view.annotation.event.OnClick;

import android.os.Bundle;
import android.view.View;

/** 
* 邀请好友		
* @author dongsy  
* @version 创建时间：2015年10月23日 上午11:25:26 
*/
public class ShareFriendActivity extends BaseActivity {

	@Override
	public void setLayout() {
		// TODO Auto-generated method stub
		setContentView(R.layout.activity_sharefriend);
	}

	@Override
	public void init(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		setTopTitle("邀请好友");
	}
	
	@OnClick(R.id.me_sharebutton)
	public void OnClick(View v){
		switch (v.getId()) {
		case R.id.me_sharebutton:
			ToastCommom.createToastConfig().ToastShow(this, "已成功发出邀请！");
			break;
		}
	}

}

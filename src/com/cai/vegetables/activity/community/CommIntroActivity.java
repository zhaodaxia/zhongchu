package com.cai.vegetables.activity.community;

import android.os.Bundle;
import android.view.View;

import com.cai.vegetables.R;
import com.cai.vegetables.activity.BaseActivity;
import com.cai.vegetables.widget.ShareDialog;
import com.lidroid.xutils.view.annotation.event.OnClick;

/**
 * 菜场介绍
 * @author wangbin
 *
 */
public class CommIntroActivity extends BaseActivity{

	@Override
	public void setLayout() {
		setContentView(R.layout.activity_intro);
	}

	@Override
	public void init(Bundle savedInstanceState) {
		
	}
	
	@OnClick(R.id.one_share)
	public void toClick(View v){
		ShareDialog dialog = new ShareDialog(this, R.style.ActionSheetDialogStyle);
		dialog.show();
	}

}

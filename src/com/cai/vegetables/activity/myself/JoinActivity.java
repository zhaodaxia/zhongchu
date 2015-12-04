package com.cai.vegetables.activity.myself;

import com.cai.vegetables.R;
import com.cai.vegetables.activity.BaseActivity;
import com.cai.vegetables.widget.ShareDialog;
import com.cai.vegetables.widget.ShareDialog.ShareDialogOnclickListener;
import com.lidroid.xutils.view.annotation.event.OnClick;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

/**
 * 申请加盟
 * 
 * @author dongsy
 * @version 创建时间：2015年10月23日 下午8:50:14
 */
public class JoinActivity extends BaseActivity {

	@Override
	public void setLayout() {
		setContentView(R.layout.activity_join);
	}

	@Override
	public void init(Bundle savedInstanceState) {
		setTopTitle("申请加盟");
		setRightTop2(R.drawable.zccp_03, new OnClickListener() {
			@Override
			public void onClick(View v) {
				ShareDialog dialog = new ShareDialog(JoinActivity.this, R.style.ActionSheetDialogStyle);
				dialog.show();
				dialog.setShareOnclickListener(new ShareDialogOnclickListener() {

					@Override
					public void momentsOnclick() {
					}

					@Override
					public void WechatOnclick() {
					}

					@Override
					public void TencentOnclick() {
					}

					@Override
					public void SinaOnclick() {
					}

					@Override
					public void QZoneOnclick() {
					}

					@Override
					public void QQOnclick() {
					}
				});
			}
		});
	}

	@OnClick({ R.id.join_company, R.id.join_shanghu, R.id.join_courier })
	public void OnClick(View v) {
		switch (v.getId()) {
		case R.id.join_company:
			gotoActivity(CompanyAct.class, false);
			break;

		case R.id.join_shanghu:
			gotoActivity(ShangHuAct.class, false);
			break;
		case R.id.join_courier:
			gotoActivity(CourierAct.class, false);
			break;
		}
	}
}

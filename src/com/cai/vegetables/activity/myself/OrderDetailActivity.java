package com.cai.vegetables.activity.myself;

import com.cai.vegetables.R;
import com.cai.vegetables.activity.BaseActivity;
import com.cai.vegetables.widget.ShareDialog;
import com.cai.vegetables.widget.ShareDialog.ShareDialogOnclickListener;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

/** 
* 订单详情
* @author dongsy  
* @version 创建时间：2015年11月2日 下午12:25:41 
*/
public class OrderDetailActivity extends BaseActivity {
	
	private ShareDialog dialog;

	@Override
	public void setLayout() {
		// TODO Auto-generated method stub
		setContentView(R.layout.orderdetail_layout);
	}

	@Override
	public void init(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		setTopTitle("订单详情");
		setRightTop2(R.drawable.zccp_03, new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				share();
			}
		});
	}
	
	private void share() {
		dialog=new ShareDialog(this, R.style.ActionSheetDialogStyle);
		dialog.show();
		dialog.setShareOnclickListener(new ShareDialogOnclickListener() {

			@Override
			public void momentsOnclick() {
				// TODO Auto-generated method stub
				GoShare();
			}
			
			@Override
			public void WechatOnclick() {
				// TODO Auto-generated method stub
				GoShare();
			}
			
			@Override
			public void TencentOnclick() {
				// TODO Auto-generated method stub
				GoShare();
			}
			
			@Override
			public void SinaOnclick() {
				// TODO Auto-generated method stub
				GoShare();
			}
			
			@Override
			public void QZoneOnclick() {
				// TODO Auto-generated method stub
				GoShare();
			}
			
			@Override
			public void QQOnclick() {
				// TODO Auto-generated method stub
				GoShare();
			}
		});
	}
	
	private void GoShare(){
		Intent share=new Intent(this,ShareFriendActivity.class);
		startActivity(share);
		dialog.dismiss();
	}
	

}

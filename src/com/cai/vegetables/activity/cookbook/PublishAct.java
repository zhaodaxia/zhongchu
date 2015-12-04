package com.cai.vegetables.activity.cookbook;

import com.cai.vegetables.R;
import com.cai.vegetables.activity.BaseActivity;
import com.cai.vegetables.widget.ShareDialog;
import com.cai.vegetables.widget.ShareDialog.ShareDialogOnclickListener;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

/**
 * 发布菜谱回调界面
 * 
 * @author yang
 *
 */
public class PublishAct extends BaseActivity {
	@ViewInject(R.id.publish_success_share)
	private Button share;
	

	@Override
	public void setLayout() {
		setContentView(R.layout.publish);
	}

	@Override
	public void init(Bundle savedInstanceState) {
		setTopTitle("发布成功");
		setRightBtn2("继续发布", new OnClickListener() {
			@Override
			public void onClick(View v) {

			}
		});
	}
	
	@OnClick(R.id.publish_success_share)
	public void todo(View v){
		switch (v.getId()) {
		case R.id.publish_success_share:
			ShareDialog dialog = new ShareDialog(this, R.style.ActionSheetDialogStyle);
			dialog.show();
			dialog.setShareOnclickListener(new ShareDialogOnclickListener() {
				@Override
				public void momentsOnclick() {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void WechatOnclick() {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void TencentOnclick() {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void SinaOnclick() {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void QZoneOnclick() {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void QQOnclick() {
					// TODO Auto-generated method stub
					
				}
			});
			break;

		default:
			break;
		}
	}

}

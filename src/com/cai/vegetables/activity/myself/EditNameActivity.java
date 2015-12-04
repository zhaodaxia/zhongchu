package com.cai.vegetables.activity.myself;

import com.cai.vegetables.R;
import com.cai.vegetables.activity.BaseActivity;
import com.cai.vegetables.utils.SharedPreferencesUtils;
import com.cai.vegetables.widget.ClearEditText;
import com.cai.vegetables.widget.ToastCommom;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.tandong.sa.eventbus.EventBus;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;

/** 
* 修改昵称
* @author dongsy  
* @version 创建时间：2015年10月24日 下午1:27:58 
*/
public class EditNameActivity extends BaseActivity {

	@ViewInject(R.id.ed_username)
	private ClearEditText ed_username;
	@Override
	public void setLayout() {
		// TODO Auto-generated method stub
		setContentView(R.layout.activity_editname);
	}

	@Override
	public void init(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		setTopTitle("昵称");
		ed_username.setText(SharedPreferencesUtils.getString(this, "USERNAME", "拎着背包去流浪"));
		setRightBtn("保存", new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String newname=ed_username.getText().toString().trim();
				if(TextUtils.isEmpty(newname)){
					ToastCommom.createToastConfig().ToastShow(EditNameActivity.this, "不接受空白昵称");;
				}
				else{
					SharedPreferencesUtils.saveString(EditNameActivity.this, "USERNAME", newname);
					finish();
				}
			}
		});
	}

}

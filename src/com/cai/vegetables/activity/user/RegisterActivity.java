package com.cai.vegetables.activity.user;


import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.cai.vegetables.R;
import com.cai.vegetables.activity.BaseActivity;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
/**
 * 注册
 * @author wangbin
 *
 */
public class RegisterActivity extends BaseActivity{
    
	@ViewInject(R.id.tv_title)
	protected TextView tv_title;//标题
	
	@ViewInject(R.id.etName)
    protected EditText etName;//用户名
	@ViewInject(R.id.tvGetCode)
	protected TextView tvGetCode;//获取验证码
	@ViewInject(R.id.etCode)
    protected EditText etCode;//验证码输入框
	@ViewInject(R.id.etPwd)
    protected EditText etPwd;//密码
	@ViewInject(R.id.tvCb)
    protected CheckBox tvCb;//是否同意
	@ViewInject(R.id.tvAgree)
    protected TextView tvAgree;//协议
    
	@Override
	public void setLayout() {
		setContentView(R.layout.activity_register);
	}

	@Override
	public void init(Bundle savedInstanceState) {
		tv_title.setText("快速注册");
	}
    
	@OnClick({R.id.tvRegister,R.id.tvToLogin})
    public void toClick(View v){
    	switch(v.getId()){
    	//提交
    	case R.id.tvRegister:
    		finish();
    		break;
    	//已有账号，到登陆
    	case R.id.tvToLogin:
    		finish();
    		break;
    	}
    }

	
}

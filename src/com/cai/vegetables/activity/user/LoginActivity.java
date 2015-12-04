package com.cai.vegetables.activity.user;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.cai.vegetables.ConstantValue;
import com.cai.vegetables.R;
import com.cai.vegetables.activity.BaseActivity;
import com.cai.vegetables.activity.MainActivity;
import com.cai.vegetables.utils.MyToastUtils;
import com.cai.vegetables.utils.SharedPreferencesUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;

/**
 * 登陆
 * 
 * @author wangbin
 * 
 */
public class LoginActivity extends BaseActivity {
	@ViewInject(R.id.tv_title)
	protected TextView tv_title;
	@ViewInject(R.id.etName)
	protected EditText etName;
	@ViewInject(R.id.etPwd)
	protected EditText etPwd;

	private String name, pwd;

	@Override
	public void setLayout() {
		setContentView(R.layout.activity_login);
	}

	@Override
	public void init(Bundle savedInstanceState) {
		tv_title.setText("登陆");
	}

	/**
	 * 点击事件
	 * @param v
	 */
	@OnClick({ R.id.tvLogin, R.id.tvRegister, R.id.tvFindPwd })
	public void toClick(View v) {
		switch (v.getId()) {
		// 登陆
		case R.id.tvLogin:
			if (isValidate()) {
				gotoActivity(MainActivity.class, true);
				SharedPreferencesUtils.saveBoolean(this, ConstantValue.TOKEN, true);
			}
			break;
		// 注册
		case R.id.tvRegister:
			gotoActivity(RegisterActivity.class, false);
			break;
		// 忘记密码
		case R.id.tvFindPwd:
			gotoActivity(FindPwdActivity.class, false);
			break;
		}
	}

	/**
	 * 验证是否为空
	 * 
	 * @return
	 */
	private boolean isValidate() {
		name = etName.getText().toString().trim();
		pwd = etPwd.getText().toString().trim();
		if (TextUtils.isEmpty(name)) {
			MyToastUtils.showShortToast(getApplicationContext(), getResources()
					.getString(R.string.input_name));
			etName.requestFocus();
			return false;
		}

		if (TextUtils.isEmpty(pwd)) {
			MyToastUtils.showShortToast(getApplicationContext(), getResources()
					.getString(R.string.input_pwd));
			etPwd.requestFocus();
			return false;
		}
		return true;
	}

}

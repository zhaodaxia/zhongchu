package com.cai.vegetables.activity.shop;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.cai.vegetables.R;
import com.cai.vegetables.activity.BaseActivity;
import com.cai.vegetables.entity.Adress;
import com.cai.vegetables.widget.DialogChooseAdress;
import com.cai.vegetables.widget.DialogChooseAdress.SexClickListener;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;

/**
 * 新增地址
 * @author wangbin
 *
 */
public class AddAdressActivity extends BaseActivity{
    @ViewInject(R.id.ivOn)
    private ImageView ivOn;
    @ViewInject(R.id.ivOff)
    private ImageView ivOff;
    @ViewInject(R.id.tv_adress)
    private TextView tv_adress;
    @ViewInject(R.id.add_username)
    private EditText add_username;
    @ViewInject(R.id.add_usernumber)
    private EditText add_usernumber;
    @ViewInject(R.id.add_useraddress)
    private EditText add_useraddress;
    
    private boolean isOff=true;
    private String username;
    private String usernumber;
    private String useradress;
    private String userzone;
    private boolean isdefault=false;
	@Override
	public void setLayout() {
		setContentView(R.layout.activity_add_adress);
	}

	@Override
	public void init(Bundle savedInstanceState) {
		setTopTitle("新增地址");
	}
	
	@OnClick({R.id.rlTogg,R.id.tvSave,R.id.choose_zone})
	public void toClick(View v){
		switch(v.getId()){
		case R.id.rlTogg:
			if(isOff){
				ivOff.setVisibility(View.VISIBLE);
				ivOn.setVisibility(View.GONE);
				isOff=false;
				isdefault=true;
			}else{
				ivOff.setVisibility(View.GONE);
				ivOn.setVisibility(View.VISIBLE);
				isOff=true;
				isdefault=false;
			}
			break;
		case R.id.tvSave:
			checkinfo();
			break;
		case R.id.choose_zone:
			DialogChooseAdress choose=new DialogChooseAdress(this).builder();
			choose.setOnSheetItemClickListener(new SexClickListener() {
				
				@Override
				public void getAdress(String adress) {
					// TODO Auto-generated method stub
					userzone=adress;
					tv_adress.setText(adress);
				}
			});
			choose.show();
			break;
		}
	}

	private void checkinfo() {
		// TODO Auto-generated method stub
		username=add_username.getText().toString().trim();
		usernumber=add_usernumber.getText().toString().trim();
		useradress=add_useraddress.getText().toString().trim();
		if(TextUtils.isEmpty(username)){
			Toast.makeText(this, "请填写名称", 0).show();
		}else if(TextUtils.isEmpty(usernumber)){
			Toast.makeText(this, "请填写收货人手机号", 0).show();
		}else if(TextUtils.isEmpty(useradress)){
			Toast.makeText(this, "请填写收货人地址", 0).show();
		}else if(TextUtils.isEmpty(userzone)){
			Toast.makeText(this, "请选择地区", 0).show();
		}else{
			Adress adinfo=new Adress(username, userzone+useradress, usernumber, isdefault);
		}
	}

}

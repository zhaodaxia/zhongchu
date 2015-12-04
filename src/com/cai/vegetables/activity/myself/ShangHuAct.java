package com.cai.vegetables.activity.myself;

import com.cai.vegetables.R;
import com.cai.vegetables.activity.BaseActivity;
import com.cai.vegetables.widget.DialogChooseAdress;
import com.cai.vegetables.widget.DialogChooseAdress.SexClickListener;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
/**
 * 申请成为商户
 * 
 * @author yang
 *
 */
public class ShangHuAct extends BaseActivity {
	@ViewInject(R.id.shanghu_btn)
	private Button shanghu_btn;
	@ViewInject(R.id.shanghu_address)
	private RelativeLayout shanghu_address;//选择地址
	@ViewInject(R.id.shanghu_address_tv)
	private TextView shanghu_address_tv;//选择地址文本
	@Override
	public void setLayout() {
		setContentView(R.layout.shanghu);
	}

	@Override
	public void init(Bundle savedInstanceState) {
		setTopTitle("申请成为商户");
	}
	
	@OnClick({R.id.shanghu_btn,R.id.shanghu_address})
	public void todo(View v) {
		switch (v.getId()) {
		case R.id.shanghu_btn:
//			ToastCommomBig.createToastConfig().ToastShow(ShangHuAct.this, "您的申请已经提交，请等待审核..\n      将在1-3个工作日显示结果");
			gotoActivity(FailedAct.class, false);
			break;
		case R.id.shanghu_address:
			DialogChooseAdress choose=new DialogChooseAdress(this).builder();
			choose.setOnSheetItemClickListener(new SexClickListener() {
				
				@Override
				public void getAdress(String adress) {
					shanghu_address_tv.setText(adress);
				}
			});
			choose.show();
			break;

		default:
			break;
		}
	}


}

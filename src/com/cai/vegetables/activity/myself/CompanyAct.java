package com.cai.vegetables.activity.myself;

import com.cai.vegetables.R;
import com.cai.vegetables.activity.BaseActivity;
import com.cai.vegetables.widget.DialogChooseAdress;
import com.cai.vegetables.widget.ToastCommomBig;
import com.cai.vegetables.widget.DialogChooseAdress.SexClickListener;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * 申请成为企业
 * 
 * @author yang
 *
 */
public class CompanyAct extends BaseActivity {
	@ViewInject(R.id.company_btn)
	private Button company_btn;
	@ViewInject(R.id.company_address)
	private RelativeLayout company_address;//选择地址
	@ViewInject(R.id.company_address_tv)
	private TextView company_address_tv;//选择地址文本

	@Override
	public void setLayout() {
		setContentView(R.layout.companyact);
	}

	@Override
	public void init(Bundle savedInstanceState) {
		setTopTitle("申请成为企业采购员");
	}

	@OnClick({R.id.company_btn,R.id.company_address})
	public void todo(View v) {
		switch (v.getId()) {
		case R.id.company_btn:
			ToastCommomBig.createToastConfig().ToastShow(CompanyAct.this, "您的申请已经提交，请等待审核..\n      将在1-3个工作日显示结果");
			break;
		case R.id.company_address:
			DialogChooseAdress choose=new DialogChooseAdress(this).builder();
			choose.setOnSheetItemClickListener(new SexClickListener() {
				
				@Override
				public void getAdress(String adress) {
					company_address_tv.setText(adress);
				}
			});
			choose.show();
			break;

		default:
			break;
		}
	}

}

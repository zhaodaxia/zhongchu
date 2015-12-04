package com.cai.vegetables.activity.light;

import com.cai.vegetables.R;
import com.cai.vegetables.activity.BaseActivity;
import com.cai.vegetables.widget.DialogChooseAdress;
import com.cai.vegetables.widget.DialogChooseAdress.SexClickListener;
import com.cai.vegetables.widget.ToastCommom;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * 添加常用路线
 * 
 * @author yang
 *
 */
public class AddRoateAct extends BaseActivity {
	@ViewInject(R.id.addroate_btn)
	private Button addroate_btn;
	@ViewInject(R.id.selectroate1)
	private TextView start;
	@ViewInject(R.id.selectroate2)
	private TextView end;
	private DialogChooseAdress chooseAdress;

	@Override
	public void setLayout() {
		setContentView(R.layout.addroate);
	}

	@Override
	public void init(Bundle savedInstanceState) {
		setTopTitle("添加常用路线");
	}

	@OnClick({ R.id.addroate_btn, R.id.selectroate1, R.id.selectroate2 })
	public void todo(View v) {
		switch (v.getId()) {
		case R.id.addroate_btn:
			ToastCommom.createToastConfig().ToastShow(AddRoateAct.this, "添加成功！");
			break;
		case R.id.selectroate1:
			chooseAdress = new DialogChooseAdress(this).builder();
			chooseAdress.show();
			chooseAdress.setOnSheetItemClickListener(new SexClickListener() {
				@Override
				public void getAdress(String adress) {
					start.setText(adress);
				}
			});
			break;
		case R.id.selectroate2:
			chooseAdress = new DialogChooseAdress(this).builder();
			chooseAdress.show();
			chooseAdress.setOnSheetItemClickListener(new SexClickListener() {
				@Override
				public void getAdress(String adress) {
					end.setText(adress);
				}
			});
			break;

		default:
			break;
		}
	}

}

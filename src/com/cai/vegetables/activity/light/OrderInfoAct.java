package com.cai.vegetables.activity.light;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.cai.vegetables.R;
import com.cai.vegetables.activity.BaseActivity;
import com.cai.vegetables.widget.TimeDialog;
import com.cai.vegetables.widget.TimeDialog.OnTimeSelectListener;
import com.cai.vegetables.widget.TimeDialog.Type;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * 完善订单信息
 * 
 * @author yang
 *
 */
public class OrderInfoAct extends BaseActivity {
	@ViewInject(R.id.orderinfo_btn)
	private Button orderinfo_btn;// 立即下单
	@ViewInject(R.id.orderinfo_rl1)
	private RelativeLayout orderinfo_rl1;// 用车时间
	@ViewInject(R.id.orderinfo_rl2)
	private RelativeLayout orderinfo_rl2;// 选择常用路线
	@ViewInject(R.id.orderinfo_rl3)
	private RelativeLayout orderinfo_rl3;// 始发地
	@ViewInject(R.id.orderinfo_rl4)
	private RelativeLayout orderinfo_rl4;// 发货人
	@ViewInject(R.id.orderinfo_rl5)
	private RelativeLayout orderinfo_rl5;// 目的地
	@ViewInject(R.id.orderinfo_rl6)
	private RelativeLayout orderinfo_rl6;// 收货人
	@ViewInject(R.id.orderinfo_rl7)
	private RelativeLayout orderinfo_rl7;// 给司机留言
	@ViewInject(R.id.orderinfo_ll)
	private LinearLayout orderinfo_ll;// 时间计费方式
	@ViewInject(R.id.orderinfo_tv1)
	private TextView orderinfo_tv1;// 时间显示文本
	private Bundle bundle;

	@Override
	public void setLayout() {
		setContentView(R.layout.orderinfo);
	}

	@Override
	public void init(Bundle savedInstanceState) {
		setTopTitle("完善订单信息");
		setRightBtn2("历史司机", new OnClickListener() {
			@Override
			public void onClick(View v) {
				gotoActivity(MyCarerAct.class, false);
			}
		});
	}

	@OnClick({ R.id.orderinfo_btn, R.id.orderinfo_rl1, R.id.orderinfo_rl2, R.id.orderinfo_rl3, R.id.orderinfo_rl4,
			R.id.orderinfo_rl5, R.id.orderinfo_rl6, R.id.orderinfo_rl7, R.id.orderinfo_ll })
	public void todo(View v) {
		switch (v.getId()) {
		case R.id.orderinfo_btn:
			gotoActivity(PdAct.class, true);
			break;
		case R.id.orderinfo_rl1:
			TimeDialog dialog = new TimeDialog(OrderInfoAct.this, Type.MONTH_DAY_HOUR_MIN);
			dialog.setRange(2000, 2050);
			dialog.builder();
			dialog.show();
			dialog.setOnTimeSelectListener(new OnTimeSelectListener() {
				@SuppressLint("SimpleDateFormat")
				@Override
				public void onTimeSelect(Date date) {
					 SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
					 String t1 = format.format(date);
					 orderinfo_tv1.setText(t1);
				}
			});
			break;
		case R.id.orderinfo_rl2:
			gotoActivity(SelectMyRoateAct.class, false);
			break;
		case R.id.orderinfo_rl3:
			bundle = new Bundle();
			bundle.putInt("flag", 1);
			gotoActivity(SelectAdressAct.class, false,bundle);
			break;
		case R.id.orderinfo_rl4:
			bundle = new Bundle();
			bundle.putInt("flag", 1);
			gotoActivity(SelectLinkManAct.class, false,bundle);
			break;
		case R.id.orderinfo_rl5:
			bundle = new Bundle();
			bundle.putInt("flag", 2);
			gotoActivity(SelectAdressAct.class, false,bundle);
			break;
		case R.id.orderinfo_rl6:
			bundle = new Bundle();
			bundle.putInt("flag", 2);
			gotoActivity(SelectLinkManAct.class, false,bundle);
			break;
		case R.id.orderinfo_rl7:
			gotoActivity(SendMsgAct.class, false);
			break;
		case R.id.orderinfo_ll:
			gotoActivity(WaitPriceAct.class, false);
			break;

		default:
			break;
		}
	}

}

package com.cai.vegetables.activity.light;

import com.cai.vegetables.R;
import com.cai.vegetables.activity.BaseActivity;
import com.cai.vegetables.activity.home.CityActivity;
import com.cai.vegetables.utils.VegetableUtils;
import com.cai.vegetables.view.LoadingView;
import com.cai.vegetables.view.pulltorefresh.PullToRefreshListView;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.RelativeLayout;

/**
 * 选择发货和收货地址
 * 
 * @author yang
 *
 */
public class SelectAdressAct extends BaseActivity {
	private static final int COUNTRY = 0;
	@ViewInject(R.id.selectaddress_plv)
	private PullToRefreshListView plv;
	@ViewInject(R.id.loadView)
	private LoadingView loadView;
	@ViewInject(R.id.selectaddress_et)
	private EditText selectaddress_et;// 手动输入地址
	@ViewInject(R.id.selectadressact_rl)
	private RelativeLayout selectadressact_rl;// 选择城市列表

	private MyAdapter adapter;

	private int flag;

	@Override
	public void setLayout() {
		setContentView(R.layout.selectaddressact);

	}

	@SuppressLint("NewApi")
	@Override
	public void init(Bundle savedInstanceState) {
		initInfo();

		loadView.noContent();
		loadView.setNoContentTxt("未查到该地址，请尝试其他地址");
		adapter = new MyAdapter();
		plv.getRefreshableView().setAdapter(adapter);
		plv.setPullRefreshEnabled(true);
		plv.setScrollLoadEnabled(true);
		plv.setPullLoadEnabled(false);
		plv.setHasMoreData(true);
		plv.getRefreshableView().setDivider(null);
		plv.getRefreshableView().setVerticalScrollBarEnabled(false);
		plv.getRefreshableView().setSelector(new ColorDrawable(Color.TRANSPARENT));
		plv.setLastUpdatedLabel(VegetableUtils.getCurrentTime());
	}

	private void initInfo() {
		Intent intent = getIntent();
		if (intent != null) {
			Bundle bundle = intent.getExtras();
			if (bundle != null) {
				flag = bundle.getInt("flag");
			}
		}
		switch (flag) {
		case 1:
			setTopTitle("选择发货地址");
			selectaddress_et.setHint("输入发货地址");
			break;
		case 2:
			setTopTitle("选择收货地址");
			selectaddress_et.setHint("输入收货地址");
			break;

		default:
			break;
		}

	}

	private class MyAdapter extends BaseAdapter {

		@Override
		public int getCount() {

			return 4;

		}

		@Override
		public View getView(final int position, View convertView, ViewGroup parent) {
			convertView = View.inflate(SelectAdressAct.this, R.layout.selectaddressact_item, null);

			return convertView;
		}

		@Override
		public Object getItem(int position) {
			return null;
		}

		@Override
		public long getItemId(int position) {
			return 0;
		}

		class Holder {

		}

	}

	@OnClick(R.id.selectadressact_rl)
	public void todo(View v){
		switch (v.getId()) {
		case R.id.selectadressact_rl:
			Intent intent = new Intent(SelectAdressAct.this, CityActivity.class);
			startActivityForResult(intent, COUNTRY);
			break;

		default:
			break;
		}
	}
}

package com.cai.vegetables.fragment;

import com.cai.vegetables.R;
import com.cai.vegetables.activity.light.OrderInfoAct;
import com.cai.vegetables.activity.light.PdAct;
import com.cai.vegetables.utils.VegetableUtils;
import com.cai.vegetables.view.pulltorefresh.PullToRefreshBase;
import com.cai.vegetables.view.pulltorefresh.PullToRefreshBase.OnRefreshListener;
import com.cai.vegetables.widget.ShareDialog;
import com.cai.vegetables.view.pulltorefresh.PullToRefreshListView;
import com.lidroid.xutils.view.annotation.ViewInject;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;

/**
 * 闪电配
 * 
 * @author wangbin
 *
 */
public class LightFragment extends BaseFragment implements OnClickListener {
	@ViewInject(R.id.lightfg_ptr)
	private PullToRefreshListView lightfg_ptr;
	@ViewInject(R.id.one_share)
	private ImageView one_share;

	private MyAdapter adapter;

	@Override
	public View initView(LayoutInflater inflater) {
		View view = inflater.inflate(R.layout.fragment_light, null);
		return view;
	}

	@SuppressLint("NewApi")
	@Override
	public void initData(Bundle savedInstanceState) {
		adapter = new MyAdapter();
		lightfg_ptr.getRefreshableView().setAdapter(adapter);
		lightfg_ptr.setPullLoadEnabled(true);
		lightfg_ptr.setPullRefreshEnabled(true);
		lightfg_ptr.getRefreshableView().setDivider(null);
		lightfg_ptr.getRefreshableView().setVerticalScrollBarEnabled(false);
		lightfg_ptr.getRefreshableView().setSelector(new ColorDrawable(Color.TRANSPARENT));
		lightfg_ptr.setOnRefreshListener(new OnRefreshListener<ListView>() {

			@Override
			public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
				lightfg_ptr.onPullDownRefreshComplete();
				lightfg_ptr.setLastUpdatedLabel(VegetableUtils.getCurrentTime());
			}

			@Override
			public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {

			}
		});
		one_share.setOnClickListener(this);
		lightfg_ptr.getRefreshableView().setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
				Intent intent = new Intent(context, OrderInfoAct.class);
				startActivity(intent);
			}
		});
	}

	private class MyAdapter extends BaseAdapter {

		@Override
		public int getCount() {
			return 3;
		}

		@Override
		public Object getItem(int position) {
			return null;
		}

		@Override
		public long getItemId(int position) {
			return 0;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			convertView = View.inflate(context, R.layout.light_item, null);

			return convertView;
		}

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.one_share:
			ShareDialog dialog = new ShareDialog(context, R.style.ActionSheetDialogStyle);
			dialog.show();
			break;

		default:
			break;
		}
	}

}

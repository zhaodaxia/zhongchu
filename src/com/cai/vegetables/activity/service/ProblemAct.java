package com.cai.vegetables.activity.service;

import com.cai.vegetables.R;
import com.cai.vegetables.activity.BaseActivity;
import com.cai.vegetables.utils.VegetableUtils;
import com.cai.vegetables.view.pulltorefresh.PullToRefreshBase;
import com.cai.vegetables.view.pulltorefresh.PullToRefreshBase.OnRefreshListener;
import com.cai.vegetables.view.pulltorefresh.PullToRefreshListView;
import com.lidroid.xutils.view.annotation.ViewInject;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ListView;
/**
 * 常见问题
 * @author yang
 *
 */
public class ProblemAct extends BaseActivity {
	@ViewInject(R.id.problem_ptr)
	private PullToRefreshListView problem_ptr;
	
	private MyAdapter adapter;

	@Override
	public void setLayout() {
		setContentView(R.layout.problemact);
	}

	@Override
	public void init(Bundle savedInstanceState) {
		setTopTitle("常见问题");
		adapter = new MyAdapter();
		problem_ptr.getRefreshableView().setAdapter(adapter);
		problem_ptr.setPullLoadEnabled(true);
		problem_ptr.setPullRefreshEnabled(true);
		problem_ptr.getRefreshableView().setDivider(null);
		problem_ptr.getRefreshableView().setVerticalScrollBarEnabled(false);
		problem_ptr.getRefreshableView().setSelector(new ColorDrawable(Color.TRANSPARENT));
		problem_ptr.setOnRefreshListener(new OnRefreshListener<ListView>() {

			@Override
			public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
				problem_ptr.onPullDownRefreshComplete();
				problem_ptr.setLastUpdatedLabel(VegetableUtils.getCurrentTime());
			}

			@Override
			public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {

			}
		});
		problem_ptr.getRefreshableView().setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
				gotoActivity(ProblemDetailAct.class, false);
			}
		});
	}

	
	private class MyAdapter extends BaseAdapter{

		@Override
		public int getCount() {
			return 5;
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
			convertView = View.inflate(ProblemAct.this, R.layout.problem_item, null);
			return convertView;
		}
		
	}
}

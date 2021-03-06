package com.cai.vegetables.activity.light;

import com.cai.vegetables.R;
import com.cai.vegetables.activity.BaseActivity;
import com.cai.vegetables.utils.VegetableUtils;
import com.cai.vegetables.view.LoadingView;
import com.cai.vegetables.view.pulltorefresh.PullToRefreshListView;
import com.lidroid.xutils.view.annotation.ViewInject;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
/**
 * 等候时间计费方式
 * 
 * @author yang
 *
 */
public class WaitPriceAct extends BaseActivity {
	@ViewInject(R.id.waitprice_ptr)
	private PullToRefreshListView plv;
	@ViewInject(R.id.loadView)
	private LoadingView loadView;
	
	private MyAdapter adapter;

	@Override
	public void setLayout() {
		setContentView(R.layout.waitprice);
	}

	@Override
	public void init(Bundle savedInstanceState) {
		setTopTitle("等候时间计费方式");
		
		loadView.loadComplete();
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
	
	private class MyAdapter extends BaseAdapter {

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
			convertView = View.inflate(WaitPriceAct.this, R.layout.waitprice_item, null);
			
			return convertView;
		}

	}


}

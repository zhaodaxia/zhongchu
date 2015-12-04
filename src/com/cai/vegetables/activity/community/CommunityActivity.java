package com.cai.vegetables.activity.community;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

import com.cai.vegetables.R;
import com.cai.vegetables.activity.BaseActivity;
import com.cai.vegetables.adapter.CommunityAdapter;
import com.cai.vegetables.utils.VegetableUtils;
import com.cai.vegetables.view.LoadingView;
import com.cai.vegetables.view.pulltorefresh.PullToRefreshListView;
import com.lidroid.xutils.view.annotation.ViewInject;

/**
 * 社区菜场
 * @author wangbin
 *
 */
public class CommunityActivity extends BaseActivity{
	 @ViewInject(R.id.loadView)
	    private LoadingView loadView;
		@ViewInject(R.id.plv)
		private PullToRefreshListView plv;
	@Override
	public void setLayout() {
		setContentView(R.layout.activity_community);
	}

	@Override
	public void init(Bundle savedInstanceState) {
		setTopTitle("社区菜场");
		loadView.loadComplete();
		
		plv.setPullRefreshEnabled(true);
		plv.setScrollLoadEnabled(true);
		plv.setPullLoadEnabled(false);
		plv.setHasMoreData(true);
		plv.getRefreshableView().setDivider(null);
		plv.getRefreshableView().setVerticalScrollBarEnabled(
				false);
		plv.getRefreshableView().setSelector(
				new ColorDrawable(Color.TRANSPARENT));
		plv.setLastUpdatedLabel(VegetableUtils.getCurrentTime());
		plv.getRefreshableView().setAdapter(new CommunityAdapter(this));
		plv.getRefreshableView().setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				gotoActivity(CommDetailActivity.class, false);
			}
		});
	}

}

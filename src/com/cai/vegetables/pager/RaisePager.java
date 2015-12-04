package com.cai.vegetables.pager;

import com.cai.vegetables.R;
import com.cai.vegetables.activity.raise.RaiseDetailActivity;
import com.cai.vegetables.adapter.RaiseAdapter;
import com.cai.vegetables.view.pulltorefresh.PullToRefreshListView;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

/**
 * 众筹Pager
 * @author wangbin
 *
 */
public class RaisePager extends BasePager{
    
	private PullToRefreshListView plv;
	public RaisePager(Context context) {
		super(context);
	}

	@Override
	public View initView() {
		View view=View.inflate(context, R.layout.pager_raise,null);
		plv=(PullToRefreshListView) view.findViewById(R.id.plv);
		plv.setPullRefreshEnabled(true);
		plv.setScrollLoadEnabled(true);
		plv.setPullLoadEnabled(false);
		plv.setHasMoreData(true);
		plv.getRefreshableView().setDivider(null);
		plv.getRefreshableView().setVerticalScrollBarEnabled(false);
		plv.getRefreshableView().setSelector(new ColorDrawable(Color.TRANSPARENT));
		plv.getRefreshableView().setAdapter(new RaiseAdapter(context));
		plv.getRefreshableView().setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Intent intent=new Intent(context,RaiseDetailActivity.class);
				context.startActivity(intent);
			}
		});
		return view;
	}

	@Override
	public void initData() {
		
	}

}

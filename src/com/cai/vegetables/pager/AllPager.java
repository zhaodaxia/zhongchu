package com.cai.vegetables.pager;

import com.cai.vegetables.R;
import com.cai.vegetables.activity.market.GoodsDetailActivity;
import com.cai.vegetables.adapter.GvMainAdapter;
import com.cai.vegetables.view.pulltorefresh.PullToRefreshGridView;
import com.lidroid.xutils.view.annotation.ViewInject;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

/** 
*
* @author dongsy  
* @version 创建时间：2015年10月27日 下午10:34:16 
*/
public class AllPager extends BasePager {
	 @ViewInject(R.id.pgv)
     private PullToRefreshGridView gv;
	public AllPager(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	@Override
	public View initView() {
		View view=View.inflate(context, R.layout.pager_all, null);
		gv=(PullToRefreshGridView) view.findViewById(R.id.pgv);
		gv.getRefreshableView().setNumColumns(2);
		gv.getRefreshableView().setAdapter(new GvMainAdapter(context));
		gv.getRefreshableView().setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Intent intent=new Intent(context,GoodsDetailActivity.class);
				context.startActivity(intent);
			}
		});
		return view;
	}

	@Override
	public void initData() {
		
	}

}

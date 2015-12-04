package com.cai.vegetables.pager;

import java.util.ArrayList;
import java.util.List;

import com.cai.vegetables.R;
import com.cai.vegetables.adapter.CollectAdapter;
import com.cai.vegetables.entity.CollectInfo;
import com.cai.vegetables.view.pulltorefresh.PullToRefreshGridView;

import android.content.Context;
import android.view.View;

/** 
* 我的收藏子页面
* @author dongsy  
* @version 创建时间：2015年11月2日 下午3:40:22 
*/
public class CollectPager extends BasePager {

	private PullToRefreshGridView collect_gv;
	private CollectAdapter adapter;
	private List<CollectInfo> datalist;
	public CollectPager(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	@Override
	public View initView() {
		// TODO Auto-generated method stub
		view=View.inflate(context, R.layout.collectlist_layout, null);
		collect_gv=(PullToRefreshGridView) view.findViewById(R.id.gv_collect);
		initData();
		return view;
	}

	@Override
	public void initData() {
		// TODO Auto-generated method stub
		datalist=new ArrayList<CollectInfo>();
		TestData();
		adapter=new CollectAdapter(datalist, context);
		collect_gv.getRefreshableView().setAdapter(adapter);
		collect_gv.getRefreshableView().setNumColumns(2);
	}

	private void TestData() {
		// TODO Auto-generated method stub
		datalist.add(new CollectInfo("老姜", "月销量：1500kg", 4.80));
		datalist.add(new CollectInfo("香葱", "月销量：875斤", 3.80));
		datalist.add(new CollectInfo("老姜", "月销量：1500kg", 4.80));
		datalist.add(new CollectInfo("香葱", "月销量：875斤", 3.80));
		datalist.add(new CollectInfo("老姜", "月销量：1500kg", 4.80));
		datalist.add(new CollectInfo("香葱", "月销量：875斤", 3.80));
		datalist.add(new CollectInfo("老姜", "月销量：1500kg", 4.80));
		datalist.add(new CollectInfo("香葱", "月销量：875斤", 3.80));
	}

}

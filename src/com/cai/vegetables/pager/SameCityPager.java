package com.cai.vegetables.pager;

import java.util.ArrayList;
import java.util.List;

import com.cai.vegetables.R;
import com.cai.vegetables.activity.myself.SameCityDetailActivity;
import com.cai.vegetables.adapter.SameCityAdapter;
import com.cai.vegetables.entity.SameCityInfo;
import com.cai.vegetables.view.pulltorefresh.PullToRefreshListView;
import com.cai.vegetables.wheelview.DensityUtil;
import com.lidroid.xutils.view.annotation.ViewInject;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

/** 
* 同城货的页面
* @author dongsy  
* @version 创建时间：2015年10月27日 下午10:34:16 
*/
public class SameCityPager extends BasePager {
	 @ViewInject(R.id.pgv)
     private PullToRefreshListView sc_list;
	 private SameCityAdapter adapter;
	 private List<SameCityInfo> datalist;
	public SameCityPager(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	@Override
	public View initView() {
		View view=View.inflate(context, R.layout.orderlist_layout, null);
		sc_list=(PullToRefreshListView) view.findViewById(R.id.lv_order);
		initData();
		return view;
	}

	@Override
	public void initData() {
		Testdata();
		adapter=new SameCityAdapter(datalist,context);
		sc_list.getRefreshableView().setAdapter(adapter);
		sc_list.getRefreshableView().setVerticalScrollBarEnabled(false);
		sc_list.getRefreshableView().setDivider(null);
		sc_list.getRefreshableView().setDividerHeight(DensityUtil.dip2px(context, 10));
		sc_list.getRefreshableView().setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
				// TODO Auto-generated method stub
				Intent detail=new Intent(context,SameCityDetailActivity.class);
				context.startActivity(detail);
			}
		});
	}

	//测试数据   为了适应MyBaseAdapter
	private void Testdata() {
		// TODO Auto-generated method stub
		datalist=new ArrayList<SameCityInfo>();
		datalist.add(new SameCityInfo());
		datalist.add(new SameCityInfo());
		datalist.add(new SameCityInfo());
		datalist.add(new SameCityInfo());
	}

}

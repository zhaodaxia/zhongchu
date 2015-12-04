package com.cai.vegetables.activity.myself;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.cai.vegetables.R;
import com.cai.vegetables.activity.BaseActivity;
import com.cai.vegetables.adapter.MyViewPagerAdpter;
import com.cai.vegetables.pager.BasePager;
import com.cai.vegetables.pager.OrderPager;
import com.cai.vegetables.pager.SameCityPager;
import com.cai.vegetables.widget.ViewPagerIndicator;
import com.lidroid.xutils.view.annotation.ViewInject;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.View.OnClickListener;

/** 
* 我的订单
* @author dongsy  
* @version 创建时间：2015年10月22日 下午3:24:27 
*/
public class MyOrderActivity extends BaseActivity {

	@ViewInject(R.id.in_order)
	private ViewPagerIndicator in_order;
	
	@ViewInject(R.id.vp_order)
	private ViewPager vp_order;
	private List<BasePager> mTabContents;
	private List<String> mDatas = Arrays.asList("全部","待付款", "待发货","待收货","待评论","同城货的");
	public static final String ALLORDER="ALL";
	public static final String NOPAY="NOPAY";
	public static final String NODE="NODE";
	public static final String NORE="NORE";
	public static final String NOCO="NOCO";
	
	private int postion=0;
	
	@Override
	public void setLayout() {
		// TODO Auto-generated method stub
		setContentView(R.layout.myorder_layout);
	}
	
	

	@Override
	public void init(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		postion=getIntent().getIntExtra("postion", 0);
		setTopTitle("我的订单");
		setRightTop2(R.drawable.search, new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
			}
		});
		mTabContents=new ArrayList<BasePager>();
		mTabContents.add(new OrderPager(this,ALLORDER));
		mTabContents.add(new OrderPager(this,NOPAY));
		mTabContents.add(new OrderPager(this,NODE));
		mTabContents.add(new OrderPager(this,NORE));
		mTabContents.add(new OrderPager(this,NOCO));
		mTabContents.add(new SameCityPager(this));
		in_order.setVisibleTabCount(6);
		in_order.setTabItemTitles(mDatas);
		vp_order.setAdapter(new MyViewPagerAdpter(mTabContents));
		in_order.setViewPager(vp_order, postion);
	}

}

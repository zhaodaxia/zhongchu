package com.cai.vegetables.activity.myself;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.cai.vegetables.R;
import com.cai.vegetables.activity.BaseActivity;
import com.cai.vegetables.adapter.MyViewPagerAdpter;
import com.cai.vegetables.pager.BasePager;
import com.cai.vegetables.pager.CollectPager;
import com.cai.vegetables.pager.OrderPager;
import com.cai.vegetables.widget.ViewPagerIndicator;
import com.lidroid.xutils.view.annotation.ViewInject;

import android.os.Bundle;
import android.support.v4.view.ViewPager;

/** 
* 我的收藏
* @author dongsy  
* @version 创建时间：2015年11月2日 下午3:34:06 
*/
public class CollectActivity extends BaseActivity {

	@ViewInject(R.id.in_collect)
	private ViewPagerIndicator in_collect;
	
	@ViewInject(R.id.vp_collect)
	private ViewPager vp_collect;
	
	private List<BasePager> mTabContents;
	private List<String> mDatas = Arrays.asList("商品","商铺", "众筹");
	@Override
	public void setLayout() {
		// TODO Auto-generated method stub
		setContentView(R.layout.mycollect_layout);
	}

	@Override
	public void init(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		setTopTitle("我的收藏");
		mTabContents=new ArrayList<BasePager>();
		mTabContents.add(new CollectPager(this));
		mTabContents.add(new CollectPager(this));
		mTabContents.add(new CollectPager(this));
		in_collect.setVisibleTabCount(3);
		in_collect.setTabItemTitles(mDatas);
		vp_collect.setAdapter(new MyViewPagerAdpter(mTabContents));
		in_collect.setViewPager(vp_collect, 0);
	}

}

package com.cai.vegetables.adapter;

import java.util.List;

import com.cai.vegetables.pager.BasePager;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

/** 
*
* @author dongsy  
* @version 创建时间：2015年10月30日 下午12:16:41 
*/
public class MyViewPagerAdpter extends PagerAdapter {
	
	private List<BasePager> mTabContents;

	public MyViewPagerAdpter(List<BasePager> pagerlist){
		this.mTabContents=pagerlist;
	}
	
	@Override
	public int getCount() {
		return mTabContents.size();
	}

	@Override
	public boolean isViewFromObject(View arg0, Object arg1) {
		return arg0 == arg1;
	}
	
	@Override
	public Object instantiateItem(ViewGroup container, int position) {
	
		container.addView(mTabContents.get(position).getRootView());
		
		return mTabContents.get(position).getRootView();
	}
	
	@Override
	public void destroyItem(ViewGroup container, int position, Object object) {
		
		container.removeView((View)object);
	}

}

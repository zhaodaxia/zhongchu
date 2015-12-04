package com.cai.vegetables.activity.myself;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.cai.vegetables.R;
import com.cai.vegetables.activity.BaseActivity;
import com.cai.vegetables.pager.AllPager;
import com.cai.vegetables.pager.BasePager;
import com.cai.vegetables.pager.CountPager;
import com.cai.vegetables.widget.ViewPagerIndicator;
import com.lidroid.xutils.view.annotation.ViewInject;

import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;

/** 
* 积分商城
* @author dongsy  
* @version 创建时间：2015年10月27日 下午10:17:07 
*/
public class IntegralActivity extends BaseActivity {

	@ViewInject(R.id.in_integral)
	private ViewPagerIndicator indicator;
	
	@ViewInject(R.id.vp_integral)
	private ViewPager vp_integral;
	private List<BasePager> mTabContents;
	private List<String> mDatas = Arrays.asList("全部","最新", "销量","价格");
	@Override
	public void setLayout() {
		// TODO Auto-generated method stub
		setContentView(R.layout.shopping_malllayout);
	}

	@Override
	public void init(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		setTopTitle("积分商城");
		mTabContents=new ArrayList<BasePager>();
		mTabContents.add(new CountPager(this));
		mTabContents.add(new CountPager(this));
		mTabContents.add(new CountPager(this));
		mTabContents.add(new CountPager(this));
		//设置Tab上的标题
		indicator.setTabItemTitles(mDatas);
		vp_integral.setAdapter(new ExPagerAdatper());
		//设置关联的ViewPager
		indicator.setViewPager(vp_integral,0);
	}
	
	class ExPagerAdatper extends PagerAdapter{

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

}

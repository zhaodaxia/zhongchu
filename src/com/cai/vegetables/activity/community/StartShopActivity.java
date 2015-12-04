package com.cai.vegetables.activity.community;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;

import com.cai.vegetables.R;
import com.cai.vegetables.activity.BaseActivity;
import com.cai.vegetables.activity.home.SearchActivity;
import com.cai.vegetables.pager.BasePager;
import com.cai.vegetables.pager.GoodsPager;
import com.cai.vegetables.widget.ViewPagerIndicator;
import com.lidroid.xutils.view.annotation.ViewInject;

/**
 * 开始购物/烘培原料
 * 
 * @author wangbin
 * 
 */
public class StartShopActivity extends BaseActivity {
	@ViewInject(R.id.in_integral)
	private ViewPagerIndicator indicator;

	@ViewInject(R.id.vp_integral)
	private ViewPager vp_integral;
	private List<BasePager> mTabContents;
	private List<String> mDatas = Arrays.asList("蔬菜素食", "鲜肉蛋禽", "水产海鲜", "粮油副食");

	@Override
	public void setLayout() {
		setContentView(R.layout.activity_start_shop);
	}

	@Override
	public void init(Bundle savedInstanceState) {
		setTopTitle("精品菜系");
		setRightTop2(R.drawable.search, new OnClickListener() {

			@Override
			public void onClick(View v) {
                gotoActivity(SearchActivity.class, false);
			}
		});
		mTabContents = new ArrayList<BasePager>();
		mTabContents.add(new GoodsPager(this));
		mTabContents.add(new GoodsPager(this));
		mTabContents.add(new GoodsPager(this));
		mTabContents.add(new GoodsPager(this));
		indicator.setTabItemTitles(mDatas);
		vp_integral.setAdapter(new ExPagerAdatper());
		// 设置关联的ViewPager
		indicator.setViewPager(vp_integral, 0);
	}

	class ExPagerAdatper extends PagerAdapter {

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

			container.removeView((View) object);
		}
	}
}

package com.cai.vegetables.activity.cookbook;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.cai.vegetables.R;
import com.cai.vegetables.activity.BaseActivity;
import com.cai.vegetables.activity.home.SearchActivity;
import com.cai.vegetables.pager.BasePager;
import com.cai.vegetables.pager.CookBookPager;
import com.cai.vegetables.widget.ViewPagerIndicator;
import com.lidroid.xutils.view.annotation.ViewInject;

import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;

/**
 * 众厨菜谱
 * 
 * @author yang
 *
 */
public class CookBookAct extends BaseActivity {
	@ViewInject(R.id.in_integral)
	private ViewPagerIndicator indicator;

	@ViewInject(R.id.vp_integral)
	private ViewPager vp_integral;
	private List<BasePager> mTabContents;
	private List<String> mDatas = Arrays.asList("冷菜", "热菜", "汤");

	@Override
	public void setLayout() {
		setContentView(R.layout.cookbook);
	}

	@Override
	public void init(Bundle savedInstanceState) {
		setTopTitle("众厨菜谱");
		setRightTop2(R.drawable.search_circle, new OnClickListener() {
			@Override
			public void onClick(View v) {
              gotoActivity(SearchActivity.class, false);
			}
		});
		setRightBtn3(R.drawable.login_btn, "发布", new OnClickListener() {
			@Override
			public void onClick(View v) {
				gotoActivity(ReleaseFirAct.class, false);
			}
		});
		mTabContents = new ArrayList<BasePager>();
		mTabContents.add(new CookBookPager(this, 1));
		mTabContents.add(new CookBookPager(this, 2));
		mTabContents.add(new CookBookPager(this, 3));
		// 设置Tab上的标题
		indicator.setVisibleTabCount(3);
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

package com.cai.vegetables.activity.cookbook;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;

import com.cai.vegetables.R;
import com.cai.vegetables.activity.BaseActivity;
import com.cai.vegetables.adapter.TvAdapter;
import com.cai.vegetables.utils.VegetableUtils;
import com.cai.vegetables.view.LoopViewPager;
import com.cai.vegetables.widget.ShareDialog;
import com.leaf.library.widget.MyListView;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;

/**
 * 菜谱详情
 * 
 * @author wangbin
 * 
 */
public class CookBookDetailAct extends BaseActivity {
	
	/**
	 * 轮播图
	 */
	private List<View> views = new ArrayList<View>();
	private LayoutParams paramsL = new LayoutParams(15, 15);
	private int IMAGES[] = { R.drawable.main_vp, R.drawable.main_vp,
			R.drawable.main_vp, R.drawable.main_vp };
	
	@ViewInject(R.id.vpMain)
	private LoopViewPager vpMain;
	@ViewInject(R.id.ll_point)
	private LinearLayout ll_point;
	
	@ViewInject(R.id.lvSc)
	private MyListView lvSc;//食材
	@Override
	public void setLayout() {
		setContentView(R.layout.activity_cook_book_detail);
	}

	@Override
	public void init(Bundle savedInstanceState) {
        vpMain.setAuto(true);
		vpMain.setCyclesTime(5000);
		vpMain.setAdapter(new MyPageAdapter());
		vpMain.setCurrentItem(0);
		initPoint();
		vpMain.setOnPageChangeListener(new OnPageChangeListener() {
			public void onPageSelected(int position) {

				if (views.size() != 0 && views.get(position) != null) {

					for (int i = 0; i < views.size(); i++) {
						if (i == position) {
							views.get(i).setBackgroundResource(
									R.drawable.point_focus);
						} else {
							views.get(i).setBackgroundResource(
									R.drawable.point_normal);
						}
					}

				}

			}

			public void onPageScrolled(int arg0, float arg1, int arg2) {

			}

			public void onPageScrollStateChanged(int arg0) {

			}
		});
		
		lvSc.setAdapter(new TvAdapter(this));
	}
	
	/**
	 * 点击事件
	 * @param v
	 */
	@OnClick({R.id.tvComment,R.id.tvComm,R.id.tvBuyFood,R.id.tvSeasoning,R.id.one_share})
	public void toClick(View v){
		switch(v.getId()){
		//购买主要食材
		case R.id.tvBuyFood:
			gotoActivity(BuyFoodActivity.class, false);
			break;
		//购买调料
		case R.id.tvSeasoning:
			gotoActivity(BuySeasoningActivity.class, false);
			break;
		//评论列表
		case R.id.tvComment:
			gotoActivity(CommentActivity.class, false);
			break;
		//评论
		case R.id.tvComm:
			gotoActivity(CommActivity.class, false);
			break;
		case R.id.one_share:
			ShareDialog dialog = new ShareDialog(this, R.style.ActionSheetDialogStyle);
			dialog.show();
			break;
		}
	}
	
	/**
	 * 初始化点
	 */
	private void initPoint() {

		for (int i = 0; i < IMAGES.length; i++) {
			View view = new View(this);
			paramsL.setMargins(VegetableUtils.dip2px(this, 5), 0, 0, 0);
			view.setLayoutParams(paramsL);
			if (i == 0) {
				view.setBackgroundResource(R.drawable.point_focus);
			} else {
				view.setBackgroundResource(R.drawable.point_normal);
			}

			views.add(view);
			ll_point.addView(view);
		}

	}

	/**
	 * 
	 * @author wangbin
	 * 
	 */
	private class MyPageAdapter extends PagerAdapter {

		@Override
		public int getCount() {
			return IMAGES.length;
		}

		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			((ViewPager) container).removeView((View) object);
		}

		@Override
		public Object instantiateItem(ViewGroup container, final int position) {

			View view = View
					.inflate(CookBookDetailAct.this, R.layout.home_vp_item, null);

			ImageView iv_iamge = (ImageView) view.findViewById(R.id.iv_image);

			iv_iamge.setBackgroundResource(IMAGES[position]);

			((ViewPager) container).addView(view);

			view.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					// MyToastUtils.showShortDebugToast(context, "你点击了新闻图片");

				}
			});

			return view;
		}

		@Override
		public boolean isViewFromObject(View view, Object obj) {
			return view == obj;
		}

	}

}

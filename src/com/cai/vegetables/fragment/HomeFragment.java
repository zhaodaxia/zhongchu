package com.cai.vegetables.fragment;

import java.util.ArrayList;
import java.util.List;

import com.cai.vegetables.GotoLighttEvent;
import com.cai.vegetables.R;
import com.cai.vegetables.activity.community.CommunityActivity;
import com.cai.vegetables.activity.community.StartShopActivity;
import com.cai.vegetables.activity.home.CityActivity;
import com.cai.vegetables.activity.cookbook.CookBookAct;
import com.cai.vegetables.activity.home.PreferentialActivity;
import com.cai.vegetables.activity.home.ProcurementActivity;
import com.cai.vegetables.activity.home.SearchActivity;
import com.cai.vegetables.activity.raise.RaiseActivity;
import com.cai.vegetables.adapter.GvMainAdapter;
import com.cai.vegetables.adapter.GvMidAdapter;
import com.cai.vegetables.utils.VegetableUtils;
import com.cai.vegetables.utils.scan.CaptureActivity;
import com.cai.vegetables.view.LoopViewPager;
import com.cai.vegetables.view.pulltorefresh.PullToRefreshListView;
import com.cai.vegetables.widget.ClearEditText;
import com.leaf.library.widget.MyGridView;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.tandong.sa.eventbus.EventBus;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;

/**
 * 主页
 * 
 * @author wangbin
 * 
 */
public class HomeFragment extends BaseFragment {

	@ViewInject(R.id.plvMain)
	private PullToRefreshListView plvMain;

	/** 首页轮播图 */
	private LoopViewPager vpMain;
	private LinearLayout ll_point;// 轮播点

	// 扫描
	@ViewInject(R.id.llScan)
	private LinearLayout llScan;
	private MyGridView gvMainM;
	
	@ViewInject(R.id.clEt)
	private ClearEditText clEt;

	private MyGridView gvMain;

	private List<View> views = new ArrayList<View>();

	private LayoutParams paramsL = new LayoutParams(15, 15);
	private int IMAGES[] = { R.drawable.main_vp, R.drawable.main_vp,
			R.drawable.main_vp, R.drawable.main_vp };

	private GvMidAdapter mAdapter;

	@Override
	public View initView(LayoutInflater inflater) {
		View view = inflater.inflate(R.layout.fragment_home, null);
		return view;
	}

	@Override
	public void initData(Bundle savedInstanceState) {
		clEt.setEnabled(false);
		plvMain.setPullRefreshEnabled(true);
		plvMain.setPullLoadEnabled(false);
		plvMain.setScrollLoadEnabled(true);
		plvMain.getRefreshableView().setAdapter(new LvMainAdapter());

	}

	@OnClick({R.id.llScan,R.id.tvLocation,R.id.llSearch})
	public void toClick(View v) {
		Intent intent = null;
		switch (v.getId()) {
		// 扫描
		case R.id.llScan:
			intent = new Intent(getActivity(), CaptureActivity.class);
			getActivity().startActivity(intent);
			break;
		//地址
		case R.id.tvLocation:
			intent = new Intent(getActivity(), CityActivity.class);
			getActivity().startActivity(intent);
			break;
		case R.id.llSearch:
			intent=new Intent(getActivity(),SearchActivity.class);
			getActivity().startActivity(intent);
			break;
		}
	}

	/**
	 * 初始化点
	 */
	private void initPoint() {

		for (int i = 0; i < IMAGES.length; i++) {
			View view = new View(getActivity());
			paramsL.setMargins(VegetableUtils.dip2px(getActivity(), 5), 0, 0, 0);
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
					.inflate(getActivity(), R.layout.home_vp_item, null);

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

	class LvMainAdapter extends BaseAdapter {

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return 1;
		}

		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return position;
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			convertView = View.inflate(getActivity(), R.layout.lv_main_item,
					null);
			vpMain = (LoopViewPager) convertView.findViewById(R.id.vpMain);
			ll_point = (LinearLayout) convertView.findViewById(R.id.ll_point);
			gvMainM = (MyGridView) convertView.findViewById(R.id.gvMainM);
			gvMain = (MyGridView) convertView.findViewById(R.id.gvMain);

			vpMain.setAuto(true);
			vpMain.setCyclesTime(5000);
			vpMain.setAdapter(new MyPageAdapter());
			vpMain.setCurrentItem(0);
			initPoint();
			mAdapter = new GvMidAdapter(getActivity(), getActivity()
					.getResources().getStringArray(R.array.main_titles));
			gvMainM.setAdapter(mAdapter);
			gvMainM.setSelector(new ColorDrawable(Color.TRANSPARENT));
			gvMain.setAdapter(new GvMainAdapter(getActivity()));

			gvMainM.setOnItemClickListener(new OnItemClickListener() {

				@Override
				public void onItemClick(AdapterView<?> parent, View view,
						int position, long id) {
					Intent intent = null;
					switch(position){
					//众厨菜谱
					case 0:
						intent=new Intent(getActivity(),CookBookAct.class);
						getActivity().startActivity(intent);
						
						break;
					//优惠促销
					case 1:
						intent=new Intent(getActivity(),PreferentialActivity.class);
						getActivity().startActivity(intent);
						
						break;
					//产地众筹
					case 2:
						intent=new Intent(getActivity(),RaiseActivity.class);
						getActivity().startActivity(intent);
						break;
					//批发市场
					case 3:
						intent=new Intent(getActivity(),ProcurementActivity.class);
						intent.putExtra(ProcurementActivity.TYPE, 1);
						getActivity().startActivity(intent);
						
						break;
					//西餐食材
					case 4:
						intent=new Intent(getActivity(),ProcurementActivity.class);
						intent.putExtra(ProcurementActivity.TYPE, 2);
						getActivity().startActivity(intent);
						break;
					//烘培原料
					case 5:
						intent=new Intent(getActivity(),StartShopActivity.class);
						getActivity().startActivity(intent);
						break;
					//城市货的
					case 6:
						EventBus.getDefault().post(new GotoLighttEvent());
						break;
					//社区菜场
					case 7:
						intent = new Intent(getActivity(),
								CommunityActivity.class);
						getActivity().startActivity(intent);
						break;
					}
				}
			});

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
			return convertView;
		}
	}

}

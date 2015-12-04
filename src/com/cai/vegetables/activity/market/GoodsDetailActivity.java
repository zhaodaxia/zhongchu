package com.cai.vegetables.activity.market;

import java.util.ArrayList;
import java.util.List;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.LinearLayout.LayoutParams;

import com.cai.vegetables.R;
import com.cai.vegetables.activity.BaseActivity;
import com.cai.vegetables.fragment.DeliveryFragment;
import com.cai.vegetables.fragment.GoodsComentFragment;
import com.cai.vegetables.fragment.GoodsDetailFragment;
import com.cai.vegetables.utils.VegetableUtils;
import com.cai.vegetables.view.LoopViewPager;
import com.cai.vegetables.widget.ShareDialog;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;

/**
 * 商品详情
 * @author wangbin
 *
 */
public class GoodsDetailActivity extends BaseActivity{

	private List<View> views = new ArrayList<View>();

	private LayoutParams paramsL = new LayoutParams(15, 15);
	private int IMAGES[] = { R.drawable.main_vp, R.drawable.main_vp,
			R.drawable.main_vp, R.drawable.main_vp };
	
	@ViewInject(R.id.vpMain)
	private LoopViewPager vpMain;
	@ViewInject(R.id.ll_point)
	private LinearLayout ll_point;
	
	@ViewInject(R.id.tvDetail)
	private TextView tvDetail;//商品详情
	@ViewInject(R.id.tvComment)
	private TextView tvComment;//评论
	@ViewInject(R.id.tvDis)
	private TextView tvDis;//配送售后
	
	@ViewInject(R.id.tvAdd)
	private TextView tvAdd;//加入购物车
	@ViewInject(R.id.rlShop)
	private RelativeLayout rlShop;//＋ －布局
	@ViewInject(R.id.tvNum)
	private TextView tvNum;//商品数量
	
	@ViewInject(R.id.sv)
	private ScrollView sv;
	
	
	Fragment gdFragment,commFragment,disFragment;
	FragmentManager manager;
	
	private int currentGoods;//当前商品数量
	@Override
	public void setLayout() {
		setContentView(R.layout.activity_goods_detail);
	}

	@Override
	public void init(Bundle savedInstanceState) {
		manager=getSupportFragmentManager();
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
		setTabSelection(0);
		sv.post(new Runnable() {   
		    public void run() {  
		    	sv.scrollTo(0, 0);  
		    }   
		});  
	}
	
	
	@OnClick({R.id.tvDetail,R.id.tvComment,R.id.tvDis,R.id.rlShare,R.id.tvAdd,R.id.ivIncrease,R.id.ivDecrease})
	public void toClick(View v){
		switch(v.getId()){
		case R.id.tvDetail:
			settvAll();
			tvDetail.setBackgroundResource(R.color.main_green);
			tvDetail.setTextColor(getResources().getColor(R.color.white));
			setTabSelection(0);
			break;
		case R.id.tvComment:
			settvAll();
			tvComment.setBackgroundResource(R.color.main_green);
			tvComment.setTextColor(getResources().getColor(R.color.white));
			setTabSelection(1);
			break;
		case R.id.tvDis:
			settvAll();
			tvDis.setBackgroundResource(R.color.main_green);
			tvDis.setTextColor(getResources().getColor(R.color.white));
			setTabSelection(2);
			break;
		//分享
		case R.id.rlShare:
			ShareDialog dialog = new ShareDialog(this, R.style.ActionSheetDialogStyle);
			dialog.show();
			break;
		//添加购物车
		case R.id.tvAdd:
			currentGoods=1;
			tvNum.setText(currentGoods+"");
			tvAdd.setVisibility(View.GONE);
			rlShop.setVisibility(View.VISIBLE);
			break;
		//添加
		case R.id.ivIncrease:
			currentGoods++;
			tvNum.setText(currentGoods+"");
			break;
		//减少
		case R.id.ivDecrease:
			currentGoods--;
			if(currentGoods==0){
				tvAdd.setVisibility(View.VISIBLE);
				rlShop.setVisibility(View.GONE);
			}
			tvNum.setText(currentGoods+"");
			break;
		}
	}
	
	private void settvAll(){
		tvDetail.setBackgroundResource(R.color.white);
		tvDetail.setTextColor(getResources().getColor(R.color.main_green));
		tvComment.setBackgroundResource(R.color.white);
		tvComment.setTextColor(getResources().getColor(R.color.main_green));
		tvDis.setBackgroundResource(R.color.white);
		tvDis.setTextColor(getResources().getColor(R.color.main_green));
	}
	
	private void setTabSelection(int position){
		FragmentTransaction transaction=manager.beginTransaction();
		hideFragment(transaction);
		switch(position){
		case 0:
			if(gdFragment==null){
				gdFragment=new GoodsDetailFragment();
				transaction.add(R.id.frame, gdFragment);
			}else{
				transaction.show(gdFragment);
			}
			break;
		case 1:
			if(commFragment==null){
				commFragment=new GoodsComentFragment();
				transaction.add(R.id.frame, commFragment);
			}else{
				transaction.show(commFragment);
			}
			break;
		case 2:
			if(disFragment==null){
				disFragment=new DeliveryFragment();
				transaction.add(R.id.frame, disFragment);
			}else{
				transaction.show(disFragment);
			}
			break;
		}
		transaction.commit();
	}
	
	private void hideFragment(FragmentTransaction transaction){
		if(gdFragment!=null){
			transaction.hide(gdFragment);
		}
		if(commFragment!=null)
			transaction.hide(commFragment);
		if(disFragment!=null)
			transaction.hide(disFragment);
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
					.inflate(GoodsDetailActivity.this, R.layout.home_vp_item, null);

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

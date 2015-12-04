package com.cai.vegetables.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.cai.vegetables.GotoLighttEvent;
import com.cai.vegetables.R;
import com.cai.vegetables.fragment.HomeFragment;
import com.cai.vegetables.fragment.LightFragment;
import com.cai.vegetables.fragment.MarketFragment;
import com.cai.vegetables.fragment.MeFragment;
import com.cai.vegetables.fragment.ShopFragment;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.tandong.sa.eventbus.EventBus;

/**
 * 主页
 * @author wangbin
 *
 */
public class MainActivity extends BaseActivity {
    @ViewInject(R.id.ivhome)
    private ImageView ivHome;
    @ViewInject(R.id.tvHome)
    private TextView tvHome;
    @ViewInject(R.id.ivLight)
    private static ImageView ivLight;
    @ViewInject(R.id.tvLight)
    private static TextView tvLight;
    @ViewInject(R.id.ivMarket)
    private ImageView ivMarket;
    @ViewInject(R.id.tvMarket)
    private TextView tvMarket;
    @ViewInject(R.id.ivShop)
    private ImageView ivShop;
    @ViewInject(R.id.tvShop)
    private TextView tvShop;
    @ViewInject(R.id.ivMe)
    private ImageView ivMe;
    @ViewInject(R.id.tvMe)
    private TextView tvMe;
	
    private FragmentManager fragmentManager;
    
    private HomeFragment homeFragment;
    private LightFragment lightFragment;
    private MarketFragment marketFragment;
    private ShopFragment shopFragment;
    private MeFragment meFragment;
    
    /**
	 * 按两次退出键时间小于2秒退出
	 */
	private final static long WAITTIME = 2000;
	private long touchTime = 0;
	private static FragmentTransaction transaction;

    
	@Override
	public void setLayout() {
		setContentView(R.layout.activity_main);
	}

	@Override
	public void init(Bundle savedInstanceState) {
		fragmentManager = getSupportFragmentManager();
		setTabSelection(0);
		setAllNoChecked();
		ivHome.setBackgroundResource(R.drawable.home_check_true);
		tvHome.setTextColor(Color.parseColor("#f8df01"));
		EventBus.getDefault().register(this);
	}
	
	@OnClick({R.id.rlHome,R.id.rlLight,R.id.rlMarket,R.id.rlShop,R.id.rlMe})
	public void toClick(View v){
		switch(v.getId()){
		//首页
		case R.id.rlHome:
			setAllNoChecked();
			ivHome.setBackgroundResource(R.drawable.home_check_true);
			tvHome.setTextColor(Color.parseColor("#f8df01"));
			setTabSelection(0);
			break;
		//闪电配
		case R.id.rlLight:
			setAllNoChecked();
			ivLight.setBackgroundResource(R.drawable.light_check_true);
			tvLight.setTextColor(Color.parseColor("#f8df01"));
			setTabSelection(1);
			break;
		//市场
		case R.id.rlMarket:
			setAllNoChecked();
			ivMarket.setBackgroundResource(R.drawable.marked_checked_true);
			tvMarket.setTextColor(Color.parseColor("#f8df01"));
			setTabSelection(2);
			break;
		//购物车
		case R.id.rlShop:
			setAllNoChecked();
			ivShop.setBackgroundResource(R.drawable.shop_check_true);
			tvShop.setTextColor(Color.parseColor("#f8df01"));
			setTabSelection(3);
			break;
		//我的
		case R.id.rlMe:
			setAllNoChecked();
			ivMe.setBackgroundResource(R.drawable.me_check_true);
			tvMe.setTextColor(Color.parseColor("#f8df01"));
			setTabSelection(4);
			break;
		}
	}
	
	/**
	 * 选中的页面
	 * 
	 * @param position
	 */
	private void setTabSelection(int position) {

		transaction = fragmentManager.beginTransaction();
		hideFragments(transaction);
		switch (position) {
		case 0:
			if (homeFragment == null) {
				homeFragment = new HomeFragment();
				transaction.add(R.id.main_fl, homeFragment);
			} else {
				transaction.show(homeFragment);
			}
			break;
		case 1:
			if (lightFragment == null) {
				lightFragment = new LightFragment();
				transaction.add(R.id.main_fl, lightFragment);
			} else {
				transaction.show(lightFragment);
			}
			break;
		case 2:
			if (marketFragment == null) {
				marketFragment = new MarketFragment();
				transaction.add(R.id.main_fl, marketFragment);
			} else {
				transaction.show(marketFragment);
			}
			break;
		case 3:
			if (shopFragment == null) {
				shopFragment = new ShopFragment();
				transaction.add(R.id.main_fl, shopFragment);
			} else {
				transaction.show(shopFragment);
			}
			break;
		case 4:
			if (meFragment == null) {
				meFragment = new MeFragment();
				transaction.add(R.id.main_fl, meFragment);
			} else {
				transaction.show(meFragment);
			}
			break;
		default:
			break;
		}
		// 提交
		transaction.commit();
	}

	/**
	 * 隐藏所有的页面
	 */
	private void hideFragments(FragmentTransaction transaction) {
		if (homeFragment != null) {
			transaction.hide(homeFragment);
		}
		if (lightFragment != null) {
			transaction.hide(lightFragment);
		}
		if (marketFragment != null) {
			transaction.hide(marketFragment);
		}
		if (shopFragment != null) {
			transaction.hide(shopFragment);
		}
		
		if (meFragment != null) {
			transaction.hide(meFragment);
		}

	}
	
	private void setAllNoChecked() {
		ivHome.setBackgroundResource(R.drawable.home_check_false);
		ivLight.setBackgroundResource(R.drawable.light_check_false);
		ivShop.setBackgroundResource(R.drawable.shop_check_false);
		ivMarket.setBackgroundResource(R.drawable.market_check_false);
		ivMe.setBackgroundResource(R.drawable.me_check_false);
		
		tvHome.setTextColor(Color.parseColor("#919191"));
		tvLight.setTextColor(Color.parseColor("#919191"));
		tvShop.setTextColor(Color.parseColor("#919191"));
		tvMarket.setTextColor(Color.parseColor("#919191"));
		tvMe.setTextColor(Color.parseColor("#919191"));

	}
	
	@Override
	public void onBackPressed() {
		long currentTime = System.currentTimeMillis();
		if ((currentTime - touchTime) >= WAITTIME) {
			Toast.makeText(this, "再按一次退出", Toast.LENGTH_SHORT).show();
			touchTime = currentTime;
		} else {
			finish();
		}
	}
	
	public void onEventMainThread(GotoLighttEvent event) {  
		ivHome.setBackgroundResource(R.drawable.me_check_false);
		tvHome.setTextColor(Color.parseColor("#919191"));
		ivLight.setBackgroundResource(R.drawable.light_check_true);
		tvLight.setTextColor(Color.parseColor("#f8df01"));
		setTabSelection(1);
	}  
	
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		EventBus.getDefault().unregister(this);  
	}
}

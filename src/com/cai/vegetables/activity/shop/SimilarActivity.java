package com.cai.vegetables.activity.shop;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.TextView;

import com.cai.vegetables.R;
import com.cai.vegetables.activity.BaseActivity;
import com.cai.vegetables.fragment.ShopSimiFragment;
import com.cai.vegetables.utils.scan.CaptureActivity;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;

/**
 * 找同类
 * 
 * @author wangbin
 * 
 */
public class SimilarActivity extends BaseActivity {
	@ViewInject(R.id.tvAll)
	private TextView tvAll;
	@ViewInject(R.id.tvNew)
	private TextView tvNew;
	@ViewInject(R.id.tvSales)
	private TextView tvSales;
	@ViewInject(R.id.tvPrice)
	private TextView tvPrice;

	Fragment fragmentAll, fragmentNew, fragmentSales, fragmentPrice;
	FragmentManager fragmentManager;
	public static final String KEY="key";

	@Override
	public void setLayout() {
		setContentView(R.layout.activity_similar);
	}

	@Override
	public void init(Bundle savedInstanceState) {
		tvAll.setBackgroundResource(R.drawable.grey_bg);
		tvAll.setTextColor(Color.parseColor("#ffffff"));
		fragmentManager = getSupportFragmentManager();
		setTabSelection(0);
		setTabSelection(0);
	}

	@OnClick({R.id.llScan, R.id.rlAll, R.id.rlNew, R.id.rlSales, R.id.rlPrice })
	public void toClick(View v) {
		switch (v.getId()) {
		case R.id.llScan:
			gotoActivity(CaptureActivity.class, false);
			break;
		case R.id.rlAll:
			setTabSelection(0);
			setAllTab();
			tvAll.setBackgroundResource(R.drawable.grey_bg);
			tvAll.setTextColor(Color.parseColor("#ffffff"));
			break;
		case R.id.rlNew:
			setTabSelection(1);
			setAllTab();
			tvNew.setBackgroundResource(R.drawable.grey_bg);
			tvNew.setTextColor(Color.parseColor("#ffffff"));
			break;
		case R.id.rlSales:
			setTabSelection(2);
			setAllTab();
			tvSales.setBackgroundResource(R.drawable.grey_bg);
			tvSales.setTextColor(Color.parseColor("#ffffff"));
			break;
		case R.id.rlPrice:
			setTabSelection(3);
			setAllTab();
			tvPrice.setBackgroundResource(R.drawable.grey_bg);
			tvPrice.setTextColor(Color.parseColor("#ffffff"));
			break;
		}
	}

	private void setTabSelection(int position) {
		FragmentTransaction transaction = fragmentManager.beginTransaction();
		hideFragments(transaction);
		switch (position) {
		case 0:
			if (fragmentAll == null) {
				fragmentAll = new ShopSimiFragment();
				Bundle bundle = new Bundle();
				bundle.putInt(KEY, 0);
				fragmentAll.setArguments(bundle);
				transaction.add(R.id.shop_frame, fragmentAll);
			} else {
				transaction.show(fragmentAll);
			}
			break;
		case 1:
			if (fragmentNew == null) {
				fragmentNew = new ShopSimiFragment();
				Bundle bundle = new Bundle();
				bundle.putInt(KEY, 1);
				fragmentNew.setArguments(bundle);
				transaction.add(R.id.shop_frame, fragmentNew);
			} else {
				transaction.show(fragmentNew);
			}

			break;
		case 2:
			if (fragmentSales == null) {
				fragmentSales = new ShopSimiFragment();
				Bundle bundle = new Bundle();
				bundle.putInt(KEY, 2);
				fragmentSales.setArguments(bundle);
				transaction.add(R.id.shop_frame, fragmentSales);
			} else {
				transaction.show(fragmentSales);
			}

			break;
		case 3:
			if (fragmentPrice == null) {
				fragmentPrice = new ShopSimiFragment();
				Bundle bundle = new Bundle();
				bundle.putInt(KEY, 3);
				fragmentPrice.setArguments(bundle);
				transaction.add(R.id.shop_frame, fragmentPrice);
			} else {
				transaction.show(fragmentPrice);
			}

			break;
		}
		transaction.commit();
	}

	/**
	 * 隐藏所有的页面
	 */
	private void hideFragments(FragmentTransaction transaction) {
		if (fragmentAll != null) {
			transaction.hide(fragmentAll);
		}
		if (fragmentNew != null) {
			transaction.hide(fragmentNew);
		}
		if (fragmentSales != null) {
			transaction.hide(fragmentSales);
		}
		
		if (fragmentPrice != null) {
			transaction.hide(fragmentPrice);
		}

	}

	@SuppressWarnings("deprecation")
	private void setAllTab() {
		tvAll.setBackgroundDrawable(null);
		tvNew.setBackgroundDrawable(null);
		tvSales.setBackgroundDrawable(null);
		tvPrice.setBackgroundDrawable(null);
		tvAll.setTextColor(Color.parseColor("#919191"));
		tvNew.setTextColor(Color.parseColor("#919191"));
		tvSales.setTextColor(Color.parseColor("#919191"));
		tvPrice.setTextColor(Color.parseColor("#919191"));
	}

}

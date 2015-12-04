package com.cai.vegetables.fragment;

import com.cai.vegetables.R;
import com.cai.vegetables.activity.shop.EditShopActivity;
import com.cai.vegetables.activity.shop.SureOrderActivity;
import com.cai.vegetables.adapter.ShopAdapter;
import com.cai.vegetables.utils.scan.CaptureActivity;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ListView;

/**
 * 购物车
 * @author wangbin
 *
 */
public class ShopFragment extends BaseFragment{
	
    @ViewInject(R.id.lvShop)
    private ListView lvShop;
    
    @ViewInject(R.id.cbAll)
    private CheckBox cbAll;
    
    private ShopAdapter adapter;
	
	@Override
	public View initView(LayoutInflater inflater) {
		View view=inflater.inflate(R.layout.fragment_shop,null);
		return view;
	}

	@Override
	public void initData(Bundle savedInstanceState) {
		adapter=new ShopAdapter(getActivity(), false);
		lvShop.setAdapter(adapter);
		
		cbAll.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				if(isChecked){
					adapter.notifyChange(true);
				}
			}
		});
	}
	
	@OnClick({R.id.llScan,R.id.rlRight,R.id.tvSett})
	public void toClick(View v){
		Intent intent=null;
		switch(v.getId()){
		//扫描
		case R.id.llScan:
			intent=new Intent(getActivity(),CaptureActivity.class);
			getActivity().startActivity(intent);
			break;
		//编辑
		case R.id.rlRight:
			intent=new Intent(getActivity(),EditShopActivity.class);
			getActivity().startActivity(intent);
			break;
		//结算
		case R.id.tvSett:
			intent=new Intent(getActivity(),SureOrderActivity.class);
			getActivity().startActivity(intent);
			break;
		}
	}


}

package com.cai.vegetables.fragment;

import com.cai.vegetables.R;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

/**
 * 商品详情
 * @author wangbin
 *
 */
public class GoodsDetailFragment extends BaseFragment{

	@Override
	public View initView(LayoutInflater inflater) {
		return View.inflate(getActivity(), R.layout.fragmetn_goods_detail, null);
	}

	@Override
	public void initData(Bundle savedInstanceState) {
		
	}

}

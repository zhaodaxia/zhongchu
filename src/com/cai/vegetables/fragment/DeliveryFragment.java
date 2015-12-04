package com.cai.vegetables.fragment;

import com.cai.vegetables.R;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

/**
 * 配送售后
 * @author wangbin
 *
 */
public class DeliveryFragment extends BaseFragment{

	@Override
	public View initView(LayoutInflater inflater) {
		return View.inflate(getActivity(), R.layout.fragment_delivery, null);
	}

	@Override
	public void initData(Bundle savedInstanceState) {
		
	}

}

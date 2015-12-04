package com.cai.vegetables.fragment;

import com.cai.vegetables.R;
import com.cai.vegetables.adapter.GvMainAdapter;
import com.cai.vegetables.view.pulltorefresh.PullToRefreshGridView;
import com.lidroid.xutils.view.annotation.ViewInject;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

/**
 * 找同类Fragment
 * @author wangbin
 *
 */
public class ShopSimiFragment extends BaseFragment{
     @ViewInject(R.id.pgv)
     private PullToRefreshGridView gv;
	@Override
	public View initView(LayoutInflater inflater) {
		View view=inflater.inflate(R.layout.fragment_shop_similar, null);
		return view;
	}

	@Override
	public void initData(Bundle savedInstanceState) {
		gv.getRefreshableView().setNumColumns(2);
		gv.getRefreshableView().setAdapter(new GvMainAdapter(getActivity()));
	}

}

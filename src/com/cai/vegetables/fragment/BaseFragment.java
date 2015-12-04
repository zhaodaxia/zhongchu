package com.cai.vegetables.fragment;


import com.lidroid.xutils.ViewUtils;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
/**
 * Fragment基类
 * @author wangbin
 *
 */
public abstract class BaseFragment extends Fragment{

	/**
	 * 返回的view对象
	 */
	protected View view;
	/**
	 * 获取上下文
	 */
	public Context context;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		context = getActivity();
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		
		view = initView(inflater);
		// 注入控件
		ViewUtils.inject(this, view);

		return view;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		initData(savedInstanceState);
	}

	/**
	 * 初始化View 对象
	 * 
	 * @param inflater
	 *            view填充器 需要布局文件
	 * @return 返回view 对象
	 */
	public abstract View initView(LayoutInflater inflater);

	/**
	 * 初始化数据
	 * 
	 * @param savedInstanceState
	 */
	public abstract void initData(Bundle savedInstanceState);



	


}

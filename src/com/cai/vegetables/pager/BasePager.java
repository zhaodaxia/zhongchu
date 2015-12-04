package com.cai.vegetables.pager;

import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;
import android.content.Context;
import android.view.View;

import android.widget.TextView;

public abstract class BasePager{
	public Context context;
	public View view;
	public String state;;

	//构建UI的方法
	public abstract View initView();
	//填充数据的方法
	public abstract void initData();
	
	public BasePager(Context context) {
		this.context = context;
		//当前的view其实就是页面的展示效果
		view = initView();
		
	}
	
	public BasePager(Context context,String state) {
		this.context = context;
		this.state=state;
		//当前的view其实就是页面的展示效果
		view = initView();
		
	}
	
	//返回当前页面样式的方法
	public View getRootView(){
		return view;
	}
	
	//将请求网络的操作抽取到父类中
	public void requestData(HttpMethod httpMethod,String url,
			RequestParams params,RequestCallBack<String> callBack) {
		HttpUtils httpUtils = new HttpUtils();
		httpUtils.send(httpMethod,url,params,callBack);
	}
	
}

package com.cai.vegetables.adapter;

import java.util.List;

import android.content.Context;
import android.view.View;
import android.widget.BaseAdapter;

public abstract class MyBaseAdapter<T> extends BaseAdapter {

	public List<T> lists;
	public View itemview;
	public Context context;
	
	public MyBaseAdapter(List<T> lists, Context context) {
		super();
		this.lists = lists;
		this.context = context;
	}

	public MyBaseAdapter(List<T> lists, View q, Context context) {
		super();
		this.lists = lists;
		this.itemview = q;
		this.context = context;
	}

	public MyBaseAdapter(Context context) {
		super();
		this.context=context;
	}

	@Override
	public int getCount() {
		return lists.size();
	}

	@Override
	public Object getItem(int position) {
		return lists.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

}

package com.cai.vegetables.adapter;

import com.cai.vegetables.R;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

/**
 * 众厨菜谱
 * @author wangbin
 *
 */
public class CookBookAdapter extends BaseAdapter{
	private Context context;
	public CookBookAdapter(Context context){
		this.context=context;
	}

	@Override
	public int getCount() {
		return 16;
	}

	@Override
	public Object getItem(int position) {
		return position;
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		convertView=View.inflate(context, R.layout.lv_cookbook_item,null);
		return convertView;
	}

}

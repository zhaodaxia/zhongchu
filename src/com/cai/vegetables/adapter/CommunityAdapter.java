package com.cai.vegetables.adapter;

import com.cai.vegetables.R;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

/**
 * 社区菜场适配器
 * @author wangbin
 *
 */
public class CommunityAdapter extends BaseAdapter{
	private Context context;
	public CommunityAdapter(Context context){
		this.context=context;
	}

	@Override
	public int getCount() {
		return 8;
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
		convertView=View.inflate(context, R.layout.lv_community_item, null);
		return convertView;
	}

}

package com.cai.vegetables.adapter;

import com.cai.vegetables.R;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
/**
 * 主页下面的适配器
 * @author wangbin
 *
 */
public class GvMainAdapter extends BaseAdapter{
	private Context context;
	public GvMainAdapter(Context context){
		this.context=context;
	}

	@Override
	public int getCount() {
		return 10;
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
		convertView=View.inflate(context, R.layout.gv_main_item,null);
		return convertView;
	}

}

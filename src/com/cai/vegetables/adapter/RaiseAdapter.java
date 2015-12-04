package com.cai.vegetables.adapter;

import com.cai.vegetables.R;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

/**
 * 众筹
 * @author wangbin
 *
 */
public class RaiseAdapter extends BaseAdapter{
   
	private Context context;
	public RaiseAdapter(Context context){
		this.context=context;
	}
	@Override
	public int getCount() {
		return 8;
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		convertView=View.inflate(context, R.layout.lv_raise_item, null);
		return convertView;
	}

}

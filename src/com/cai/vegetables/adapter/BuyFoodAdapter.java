package com.cai.vegetables.adapter;

import com.cai.vegetables.R;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

/**
 * 购买主要食材适配器
 * @author wangbin
 *
 */
public class BuyFoodAdapter extends BaseAdapter{
    private Context context;
	public BuyFoodAdapter(Context context){
		this.context=context;
	}
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
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
		convertView=View.inflate(context, R.layout.lv_buy_food_item,null);
		return convertView;
	}

}

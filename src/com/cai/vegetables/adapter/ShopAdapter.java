package com.cai.vegetables.adapter;

import java.util.HashMap;
import java.util.Map;

import com.cai.vegetables.R;
import com.cai.vegetables.activity.shop.SimilarActivity;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

/**
 * 购物车
 * 
 * @author wangbin
 * 
 */
public class ShopAdapter extends BaseAdapter {
	private Context context;
	
	private boolean isAllChecked;
	
	private Map<Integer,String> map=new HashMap<Integer, String>();

	public ShopAdapter(Context context,boolean isAllChecked) {
		this.context = context;
		this.isAllChecked=isAllChecked;
	}
	
	public void notifyChange(boolean isAllChecked){
		this.isAllChecked=isAllChecked;
		notifyDataSetChanged();
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
		ViewHolder holder;
		if (convertView == null) {
			convertView = View.inflate(context, R.layout.lv_shop_item, null);
			holder = new ViewHolder();
			holder.tvSimilar = (TextView) convertView
					.findViewById(R.id.tvSimilar);
			holder.cbShop=(CheckBox) convertView.findViewById(R.id.cbShop);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		if(!map.containsKey(position)){
		map.put(position, position+"");
		}
		
		 if(isAllChecked&&!map.containsKey(position)){
			 holder.cbShop.setChecked(true);
		 }
          holder.tvSimilar.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent=new Intent(context,SimilarActivity.class);
				context.startActivity(intent);
			}
		});
		    
		return convertView;
	}

	class ViewHolder {
		TextView tvSimilar;
		CheckBox cbShop;
	}

}

package com.cai.vegetables.adapter;

import java.util.List;

import com.cai.vegetables.R;
import com.cai.vegetables.entity.CollectInfo;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/** 
* 我的收藏子页面适配器
* @author dongsy  
* @version 创建时间：2015年11月2日 下午3:57:45 
*/
public class CollectAdapter extends MyBaseAdapter<CollectInfo> {

	private CollectHolder holder;
	public CollectAdapter(List<CollectInfo> lists, Context context) {
		super(lists, context);
		// TODO Auto-generated constructor stub
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		if(convertView==null){
		itemview=View.inflate(context, R.layout.gv_collect_item, null);
		holder=new CollectHolder();
		holder.tv_name=(TextView) itemview.findViewById(R.id.tvName);
		holder.tv_des=(TextView) itemview.findViewById(R.id.tv_collectdes);
		holder.tv_price=(TextView) itemview.findViewById(R.id.tv_collectprice);
		itemview.setTag(holder);
		}
		else{
			itemview=convertView;
			holder=(CollectHolder) itemview.getTag();
		}
		holder.tv_name.setText(lists.get(position).collectname);
		holder.tv_des.setText(lists.get(position).saledes);
		holder.tv_price.setText(lists.get(position).price+"");
		return itemview;
	}

	private class CollectHolder{
		private TextView tv_name;
		private TextView tv_des;
		private TextView tv_price;
	}
}

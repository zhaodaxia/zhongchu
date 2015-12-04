package com.cai.vegetables.adapter;

import java.util.List;

import com.cai.vegetables.R;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/** 
* 商品分类搜索gridviewitem适配器
* @author dongsy  
* @version 创建时间：2015年10月26日 下午9:22:33 
*/
public class FoldGridItemAdpter extends MyBaseAdapter<String> {
	
	private FoldHolder holder;
	public FoldGridItemAdpter(List<String> lists, Context context) {
		super(lists, context);
		// TODO Auto-generated constructor stub
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		if (convertView == null) {
			itemview=View.inflate(context, R.layout.folditem_layout, null);
			holder=new FoldHolder();
			holder.tv_name=(TextView) itemview.findViewById(R.id.tv_folditem);
			itemview.setTag(holder);
		}
		else{
			itemview=convertView;
			holder=(FoldHolder) itemview.getTag();
		}
		holder.tv_name.setText(lists.get(position));
		return itemview;
	}

	private class FoldHolder{
		TextView tv_name;
	}
	
}

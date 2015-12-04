package com.cai.vegetables.adapter;

import java.util.List;

import com.cai.vegetables.R;
import com.cai.vegetables.entity.SameCityInfo;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

/** 
* 同城货的适配器
* @author dongsy  
* @version 创建时间：2015年10月30日 下午6:06:29 
*/
public class SameCityAdapter extends MyBaseAdapter<SameCityInfo> {

	public SameCityAdapter(List<SameCityInfo> lists, Context context) {
		super(lists, context);
		// TODO Auto-generated constructor stub
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		if(convertView==null){
			itemview=View.inflate(context, R.layout.samecitylistitem_layout, null);
		}
		else{
			itemview=convertView;
		}
		return itemview;
	}

}

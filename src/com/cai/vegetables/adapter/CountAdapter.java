package com.cai.vegetables.adapter;

import com.cai.vegetables.R;
import com.cai.vegetables.activity.myself.ExchangeAct;
import com.cai.vegetables.widget.MyMsgDialog;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
/**
 * 主页下面的适配器
 * @author wangbin
 *
 */
public class CountAdapter extends BaseAdapter{
	private Context context;
	public CountAdapter(Context context){
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
		ViewHolder holder;
		if(convertView==null){
			holder = new ViewHolder();
			convertView=View.inflate(context, R.layout.gv_count_item,null);
			holder.duihuan = (TextView) convertView.findViewById(R.id.gv_count_item_toget);
			convertView.setTag(holder);
		}else{
			holder = (ViewHolder) convertView.getTag();
		}
		holder.duihuan.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				MyMsgDialog dialog = new MyMsgDialog(context).builder();
				dialog.setTitle("很抱歉！您的积分余额不足，不能兑换该商品！");
				dialog.setContent("您当前的积分为：325");
				dialog.setPositiveButton("朕知道了", new OnClickListener() {
					@Override
					public void onClick(View v) {
					}
				});
				dialog.show();
				Intent intent = new Intent(context, ExchangeAct.class);
				context.startActivity(intent);
			}
		});
		return convertView;
	}
	
	class ViewHolder{
		TextView duihuan;
	}

}

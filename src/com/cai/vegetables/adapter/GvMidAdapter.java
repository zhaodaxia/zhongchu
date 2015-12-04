package com.cai.vegetables.adapter;

import com.cai.vegetables.R;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
/**
 * 主页中间八个适配器
 * @author wangbin
 *
 */
public class GvMidAdapter extends BaseAdapter{
    private int IMAGES[]={R.drawable.gv2,R.drawable.gv5,R.drawable.gv3,R.drawable.gv1,
    		R.drawable.gv6,R.drawable.gv7,R.drawable.gv8,R.drawable.gv4};
    private String titles[];
    
    private Context context;
    public GvMidAdapter(Context context,String titles[]){
    	this.context=context;
    	this.titles=titles;	
    }
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return IMAGES.length;
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
		convertView=View.inflate(context, R.layout.main_gv_mid_item,null);
		ImageView iv=(ImageView) convertView.findViewById(R.id.iv);
		TextView tv=(TextView) convertView.findViewById(R.id.tv);
		iv.setBackgroundResource(IMAGES[position]);
		tv.setText(titles[position]);	
		return convertView;
	}

}

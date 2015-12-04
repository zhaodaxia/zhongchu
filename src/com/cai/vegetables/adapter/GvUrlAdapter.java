package com.cai.vegetables.adapter;

import java.util.List;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;

import com.cai.vegetables.R;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;

public class GvUrlAdapter extends BaseAdapter{

    private List<String> list;
    private Context context;
//    private ImageLoader imageLoader;
//    private DisplayImageOptions optionsImag;
    public GvUrlAdapter(Context context,List<String> list){
    	this.context=context;
    	this.list=list;
//    	imageLoader=ImageLoader.getInstance();
//    	optionsImag = new DisplayImageOptions.Builder()
//		.showImageForEmptyUri(R.drawable.login_bg)
//		// 设置图片Uri为空或是错误的时候显示的图片
//		.showImageOnFail(R.drawable.login_bg)
//		// 设置图片加载/解码过程中错误时候显示的图片
//		.cacheInMemory(true).cacheOnDisk(true).considerExifParams(true)
//		.imageScaleType(ImageScaleType.EXACTLY)
//		.bitmapConfig(Bitmap.Config.RGB_565).build();
    }
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return 4;
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
		ViewHolder viewHolder;
		if (convertView == null) {
			viewHolder = new ViewHolder();
			convertView = View.inflate(context, R.layout.photo_gv_item, null);
			viewHolder.ivPhoto = (ImageView) convertView
					.findViewById(R.id.ivPhoto);
			convertView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}
//		imageLoader.displayImage(ConstantValue.BASE_URL+list.get(position), viewHolder.ivPhoto, optionsImag);
		viewHolder.ivPhoto.setScaleType(ScaleType.FIT_XY);
		return convertView;
	}
	
	class ViewHolder {
		ImageView ivPhoto;
	}


}

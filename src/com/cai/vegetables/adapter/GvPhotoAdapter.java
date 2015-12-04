package com.cai.vegetables.adapter;

import java.io.Serializable;
import java.util.List;

import com.cai.vegetables.R;
import com.cai.vegetables.activity.photo.ImageBrowserActivity;
import com.cai.vegetables.activity.photo.PicSelectActivity;
import com.cai.vegetables.entity.ImageBean;
import com.cai.vegetables.utils.MyBitmapUtils;

import android.app.Activity;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

/**
 * 照片Adapter
 * 
 * @author wangbin
 * 
 */
public class GvPhotoAdapter extends BaseAdapter {
	private List<ImageBean> list;
	private Activity context;
	public static final int PHOTO_CODE0 = 0x456;
	public static final int ADD_PHOTO_CODE = 0x300;

	public GvPhotoAdapter(List<ImageBean> list, Activity context) {
		this.list = list;
		this.context = context;
	}

	public void notifyDataChange(List<ImageBean> list) {
		this.list = list;
		this.notifyDataSetChanged();
	}

	@Override
	public int getCount() {
		return list.size() == 4? list.size() : list.size() + 1;
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
	public View getView(final int position, View convertView, ViewGroup parent) {
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
		if (list.size() < 4) {
			if (position == list.size()) {
				
				viewHolder.ivPhoto.setImageBitmap(BitmapFactory.decodeResource(
						context.getResources(), R.drawable.photo_add));
				viewHolder.ivPhoto.setOnClickListener(new OnClickListener() {
					@Override
					public void onClick(View v) {
						Intent intent = new Intent(context,
								PicSelectActivity.class);
						intent.putExtra("select_count", list.size());
						context.startActivityForResult(intent, ADD_PHOTO_CODE);
					}
				});
			} else {
				viewHolder.ivPhoto.setImageBitmap(MyBitmapUtils.LoadBigImg(list
						.get(position).path, 70, 70));
				viewHolder.ivPhoto.setOnClickListener(new OnClickListener() {
					@Override
					public void onClick(View v) {
						Intent intent = new Intent(context,
								ImageBrowserActivity.class);
						intent.putExtra("images", (Serializable) list);
						intent.putExtra("position", position);
						intent.putExtra("isdel", true);
						context.startActivityForResult(intent, PHOTO_CODE0);
					}
				});
			}
		} else {
			viewHolder.ivPhoto.setImageBitmap(MyBitmapUtils.LoadBigImg(list
					.get(position).path, 70, 70));
			viewHolder.ivPhoto.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					Intent intent = new Intent(context,
							ImageBrowserActivity.class);
					intent.putExtra("images", (Serializable) list);
					intent.putExtra("position", position);
					intent.putExtra("isdel", true);
					context.startActivityForResult(intent, PHOTO_CODE0);
				}
			});
		}
		return convertView;
	}

	class ViewHolder {
		ImageView ivPhoto;
	}
}

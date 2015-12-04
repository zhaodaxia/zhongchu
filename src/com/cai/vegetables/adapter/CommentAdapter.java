package com.cai.vegetables.adapter;

import java.util.ArrayList;
import java.util.List;

import com.cai.vegetables.R;
import com.cai.vegetables.activity.photo.ImagePagerActivity;
import com.leaf.library.widget.MyGridView;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
/**
 * 评论列表适配器
 * @author wangbin
 *
 */
public class CommentAdapter extends BaseAdapter{
	private Context context;
	List<String> images;
	public CommentAdapter(Context context){
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
		convertView=View.inflate(context,R.layout.lv_comment_item, null);
		MyGridView gvPhoto=(MyGridView) convertView.findViewById(R.id.gvPhoto);
		 images=new ArrayList<String>();
		images.add("http://app.doowinedu.com/Uploads/talk/api5608b330184e57076.jpg");
		images.add("http://app.doowinedu.com/Uploads/talk/api5608b330184e57076.jpg");
		images.add("http://app.doowinedu.com/Uploads/talk/api5608b330184e57076.jpg");
		gvPhoto.setAdapter(new GvUrlAdapter(context, images));
		gvPhoto.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				
				imageBrower(1,(ArrayList<String>) images);
			}
		});
		return convertView;
	}
	
	/**
	 * 打开图片查看器
	 * 
	 * @param position
	 * @param urls2
	 */
	protected void imageBrower(int position, ArrayList<String> urls2) {
		Intent intent = new Intent(context, ImagePagerActivity.class);
		// 图片url,为了演示这里使用常量，一般从数据库中或网络中获取
		intent.putExtra(ImagePagerActivity.EXTRA_IMAGE_URLS, urls2);
		intent.putExtra(ImagePagerActivity.EXTRA_IMAGE_INDEX, position);
		context.startActivity(intent);
	}

}

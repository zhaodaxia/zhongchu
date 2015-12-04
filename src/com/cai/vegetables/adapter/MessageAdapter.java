package com.cai.vegetables.adapter;

import java.util.List;

import com.cai.vegetables.R;
import com.cai.vegetables.entity.Message;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/** 
* 消息中心listview适配器
* @author dongsy  
* @version 创建时间：2015年10月30日 上午9:50:09 
*/
public class MessageAdapter extends MyBaseAdapter<Message> {

	private MessageHold holder;

	public MessageAdapter(List<Message> lists, Context context) {
		super(lists, context);
		// TODO Auto-generated constructor stub
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		if(convertView==null){
			itemview=View.inflate(context, R.layout.message_item, null);
			holder=new MessageHold();
			holder.title=(TextView) itemview.findViewById(R.id.tv_message_title);
			holder.content=(TextView) itemview.findViewById(R.id.tv_message_content);
			holder.time=(TextView) itemview.findViewById(R.id.tv_message_time);
			itemview.setTag(holder);
		}
		else{
			itemview=convertView;
			holder=(MessageHold) itemview.getTag();
		}
		holder.title.setText(lists.get(position).title);
		holder.content.setText(lists.get(position).content);
		holder.time.setText(lists.get(position).time);
		return itemview;
	}

	private class MessageHold{
		TextView title;
		TextView content;
		TextView time;
	}
}

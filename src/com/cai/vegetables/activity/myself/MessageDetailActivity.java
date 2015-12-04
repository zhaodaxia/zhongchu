package com.cai.vegetables.activity.myself;

import com.cai.vegetables.R;
import com.cai.vegetables.activity.BaseActivity;
import com.cai.vegetables.entity.Message;
import com.lidroid.xutils.view.annotation.ViewInject;

import android.os.Bundle;
import android.widget.TextView;

/** 
*
* @author dongsy  
* @version 创建时间：2015年10月30日 上午11:18:28 
*/
public class MessageDetailActivity extends BaseActivity {

	@ViewInject(R.id.tv_detailtitle)
	private TextView tv_detailtitle;
	
	@ViewInject(R.id.tv_detailtime)
	private TextView tv_detailtime;
	
	@ViewInject(R.id.tv_deatilcontent)
	private TextView tv_deatilcontent;
	private Message message;
	@Override
	public void setLayout() {
		// TODO Auto-generated method stub
		setContentView(R.layout.messagedetail_layout);
		message=(Message) this.getIntent().getSerializableExtra("detail");
	}

	@Override
	public void init(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		setTopTitle("消息详情");
		tv_detailtitle.setText(message.title);
		tv_detailtime.setText(message.time);
		tv_deatilcontent.setText(message.content);
	}

}

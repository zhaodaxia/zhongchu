package com.cai.vegetables.activity.myself;

import java.util.ArrayList;
import java.util.List;

import com.cai.vegetables.R;
import com.cai.vegetables.activity.BaseActivity;
import com.cai.vegetables.adapter.MessageAdapter;
import com.cai.vegetables.entity.Message;
import com.cai.vegetables.view.pulltorefresh.PullToRefreshListView;
import com.cai.vegetables.wheelview.DensityUtil;
import com.lidroid.xutils.view.annotation.ViewInject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

/** 
*
* @author dongsy  
* @version 创建时间：2015年10月30日 上午9:43:35 
*/
public class Message_Activity extends BaseActivity {

	@ViewInject(R.id.lv_message)
	private PullToRefreshListView listview;
	private MessageAdapter adapter;
	private List<Message> messlist;
	@Override
	public void setLayout() {
		// TODO Auto-generated method stub
		setContentView(R.layout.message_layout);
	}

	@Override
	public void init(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		setTopTitle("消息中心");
		messlist=new ArrayList<Message>();
		Testdata();
		adapter=new MessageAdapter(messlist, this);
		listview.getRefreshableView().setAdapter(adapter);
		listview.getRefreshableView().setVerticalScrollBarEnabled(false);
		listview.getRefreshableView().setDivider(null);
		listview.getRefreshableView().setDividerHeight(DensityUtil.dip2px(this, 10));
		listview.getRefreshableView().setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
				// TODO Auto-generated method stub
				Intent detailin=new Intent(Message_Activity.this,MessageDetailActivity.class);
				Bundle bundle = new Bundle();
				bundle.putSerializable("detail", messlist.get(arg2));
				detailin.putExtras(bundle);
				startActivity(detailin);
			}
		});
	}

	//测试数据
	private void Testdata() {
		// TODO Auto-generated method stub
		messlist.add(new Message("您好！您购买的金针菇，卖家已经发货，请随时准备收货", 
				"打包员王师傅已经将您的商品打包完好，并交付于李司机为您配送，预计在30分钟内送达，请准备收货。", 
				"2015-10-30 10:23"));
		messlist.add(new Message("您有一张代金券到账，快去看看吧！",
				"亲，您有一张10元的代金券已经到账了，真金白银哦，快去看看吧！",
				"2015-10-30 10:23"));
		messlist.add(new Message("付款成功！",
				"您于2015-10-30 10-27购买的一箱伊利天然牧场纯牛奶已经付款成功！我们会尽快安排发货,请您耐心等待。",
				"2015-10-30 10:23"));
		messlist.add(new Message("您收藏的商品降价啦！",
				"双11降价- -",
				"2015-10-30 10:23"));
	}

}

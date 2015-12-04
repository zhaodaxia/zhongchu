package com.cai.vegetables.pager;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import com.cai.vegetables.R;
import com.cai.vegetables.activity.myself.MyOrderActivity;
import com.cai.vegetables.activity.myself.OrderDetailActivity;
import com.cai.vegetables.adapter.OrderAdapter;
import com.cai.vegetables.entity.OrderInfo;
import com.cai.vegetables.view.pulltorefresh.PullToRefreshListView;
import com.cai.vegetables.wheelview.DensityUtil;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

/** 
* 订单页面
* @author dongsy  
* @version 创建时间：2015年10月27日 下午10:36:12 
*/
public class OrderPager extends BasePager {
	private PullToRefreshListView lv_order;
	private List<OrderInfo> datalists;
	private OrderAdapter adpter;
	//显示的订单集合
	private List<OrderInfo> showlists;
	/*
	 * state 
	 * 待付款  待发货  待收货  待评论  
	 * NOPAY NODE NORE NOCO 
	 */

	private Intent gointent;
	
	@Override
	public View initView() {
		// TODO Auto-generated method stub
		view=View.inflate(context, R.layout.orderlist_layout, null);
		lv_order=(PullToRefreshListView) view.findViewById(R.id.lv_order);
		initData();
		return view;
	}

	public OrderPager(Context context, String state) {
		super(context, state);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void initData() {
		// TODO Auto-generated method stub
		datalists=new ArrayList<OrderInfo>();
		showlists=new ArrayList<OrderInfo>();
		Testdata();
		//区分订单类型
		if(state.equals(MyOrderActivity.ALLORDER)){
			adpter=new OrderAdapter(datalists,context);
		}
		//未付款
		else if(state.equals(MyOrderActivity.NOPAY)){
			for(OrderInfo info:datalists){
				if(info.state.equals(MyOrderActivity.NOPAY)){
					showlists.add(info);
				}
			}
			adpter=new OrderAdapter(showlists,context);
		}
		//未发货
		else if(state.equals(MyOrderActivity.NODE)){
			for(OrderInfo info:datalists){
				if(info.state.equals(MyOrderActivity.NODE)){
					showlists.add(info);
				}
			}
			adpter=new OrderAdapter(showlists,context);
		}
		//未收货
		else if(state.equals(MyOrderActivity.NORE)){
			for(OrderInfo info:datalists){
				if(info.state.equals(MyOrderActivity.NORE)){
					showlists.add(info);
				}
			}
			adpter=new OrderAdapter(showlists,context);
		}
		//未评论
		else if(state.equals(MyOrderActivity.NOCO)){
			for(OrderInfo info:datalists){
				if(info.state.equals(MyOrderActivity.NOCO)){
					showlists.add(info);
				}
			}
			adpter=new OrderAdapter(showlists,context);
		}
		lv_order.getRefreshableView().setAdapter(adpter);
		lv_order.getRefreshableView().setVerticalScrollBarEnabled(false);
		lv_order.getRefreshableView().setDivider(null);
		lv_order.getRefreshableView().setDividerHeight(DensityUtil.dip2px(context, 10));
		lv_order.getRefreshableView().setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
				// TODO Auto-generated method stub
				gointent=new Intent(context,OrderDetailActivity.class);
				context.startActivity(gointent);
			}
		});
	}

	//添加测试数据
	private void Testdata() {
		// TODO Auto-generated method stub
		datalists.add(new OrderInfo(MyOrderActivity.NOCO, "纯正青岛大虾", 188, 2, "2015-10-30 16:48", 
				5));
		datalists.add(new OrderInfo(MyOrderActivity.NODE, "纯正青岛大虾", 188, 2, "2015-10-30 16:48", 
				5));
		datalists.add(new OrderInfo(MyOrderActivity.NORE, "纯正青岛大虾", 188, 2, "2015-10-30 16:48", 
				5));
		datalists.add(new OrderInfo(MyOrderActivity.NOPAY, "纯正青岛大虾", 188, 2, "2015-10-30 16:48", 
				5));
		datalists.add(new OrderInfo(MyOrderActivity.NOPAY, "纯正青岛大虾", 188, 2, "2015-10-30 16:48", 
				5));
		datalists.add(new OrderInfo(MyOrderActivity.NODE, "纯正青岛大虾", 188, 2, "2015-10-30 16:48", 
				5));
		datalists.add(new OrderInfo(MyOrderActivity.NOCO, "纯正青岛大虾", 188, 2, "2015-10-30 16:48", 
				5));
	}

}

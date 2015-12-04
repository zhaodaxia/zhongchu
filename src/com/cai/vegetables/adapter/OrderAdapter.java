package com.cai.vegetables.adapter;

import java.util.List;

import com.cai.vegetables.R;
import com.cai.vegetables.activity.myself.MyOrderActivity;
import com.cai.vegetables.activity.myself.OrderComment;
import com.cai.vegetables.activity.shop.OrderPayActivity;
import com.cai.vegetables.entity.OrderInfo;
import com.cai.vegetables.pager.OrderPager;
import com.cai.vegetables.widget.MyMsgDialog;

import android.content.Context;
import android.content.Intent;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.TextAppearanceSpan;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;

/** 
* 我的订单listview适配器
* @author dongsy  
* @version 创建时间：2015年10月30日 下午3:55:13 
*/
public class OrderAdapter extends MyBaseAdapter<OrderInfo> {

	public OrderAdapter(List<OrderInfo> lists, Context context) {
		super(lists, context);
		dialog=new MyMsgDialog(context);
		// TODO Auto-generated constructor stub
	}

	private OrderHolder holder;
	private TextView tv_left;
	private TextView tv_right;
	private SpannableString styledText ;
	private Intent gointent=null;
	private MyMsgDialog dialog;
	@Override
	public View getView(int arg0, View arg1, ViewGroup arg2) {
		// TODO Auto-generated method stub
		if(arg1==null){
			itemview=View.inflate(context, R.layout.orderitem_layout, null);
			holder=new OrderHolder();
			holder.goodcount=(TextView) itemview.findViewById(R.id.tv_ordercount);
			holder.ordertime=(TextView) itemview.findViewById(R.id.tv_ordertime);
			holder.goodname=(TextView) itemview.findViewById(R.id.tv_ordername);
			holder.goodprice=(TextView) itemview.findViewById(R.id.tv_orderprice);
			holder.orderstate=(TextView) itemview.findViewById(R.id.tv_orderstate);
			holder.orderdes=(TextView) itemview.findViewById(R.id.tv_orderfreight);
			itemview.setTag(holder);
		}
		else{
			itemview=arg1;
			holder=(OrderHolder) itemview.getTag();
		}
		tv_left=(TextView) itemview.findViewById(R.id.order_leftbt);
		tv_right=(TextView) itemview.findViewById(R.id.order_rightbt);
		setBttext(lists.get(arg0).state);
		holder.goodcount.setText("购买数量:"+lists.get(arg0).goodscount+"斤");
		holder.ordertime.setText(lists.get(arg0).ordertime);
		holder.goodname.setText(lists.get(arg0).goodsname);
		//设置单价文字
		styledText=new SpannableString("单价："+lists.get(arg0).goodsprice+"元/斤");
		styledText.setSpan(new TextAppearanceSpan(context, R.style.tv_14_gray_color), 0, "单价：".length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);  
	    styledText.setSpan(new TextAppearanceSpan(context, R.style.tv_16_orange_color), "单价：".length(), ("单价："+lists.get(arg0).goodsprice).length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
	    styledText.setSpan(new TextAppearanceSpan(context, R.style.tv_14_gray_color), ("单价："+lists.get(arg0).goodsprice).length(), ("单价："+lists.get(arg0).goodsprice+"元/斤").length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);  
		holder.goodprice.setText(styledText,TextView.BufferType.SPANNABLE);
		holder.orderdes.setText("共计"+lists.get(arg0).goodscount+"件商品  合计："+lists.get(arg0).goodsprice*lists.get(arg0).goodscount+
				"元  运费："+lists.get(arg0).freight+"元");
		return itemview;
	}
	
	private void setBttext(String state) {
		// TODO Auto-generated method stub
		//待付款
		if(state.equals(MyOrderActivity.NOPAY)){
			tv_left.setText("取消订单");
			tv_right.setText("立即付款");
			tv_right.setVisibility(View.VISIBLE);
			holder.orderstate.setText("等待买家付款");
			tv_right.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					gointent=new Intent(context,OrderPayActivity.class);
					context.startActivity(gointent);
				}
			});
		}
		//待发货
		else if(state.equals(MyOrderActivity.NODE)){
			tv_left.setText("取消订单");
			tv_right.setVisibility(View.GONE);
			holder.orderstate.setText("等待卖家发货");
		}
		//待收货
		else if(state.equals(MyOrderActivity.NORE)){
			tv_left.setText("取消订单");
			tv_right.setText("确认收货");
			tv_right.setVisibility(View.VISIBLE);
			holder.orderstate.setText("卖家已发货");
			dialog.builder();
			tv_right.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					dialog.setTitle("您确认要收获吗");
					dialog.setNegativeButton("",null);
					dialog.setPositiveButton("确认", new OnClickListener() {
						
						@Override
						public void onClick(View v) {
							// TODO Auto-generated method stub
							
						}
					});
					dialog.setContent(" ");
					dialog.show();
				}
			});
		}
		//待评论
		else if(state.equals(MyOrderActivity.NOCO)){
			tv_left.setText("删除订单");
			tv_right.setText("立即评价");
			tv_right.setVisibility(View.VISIBLE);
			holder.orderstate.setText("交易完成");
			tv_right.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					gointent=new Intent(context,OrderComment.class);
					context.startActivity(gointent);
				}
			});
		}
	}

	private class OrderHolder{
		public TextView ordertime;
		public TextView goodname;
		public TextView goodprice;
		public TextView orderstate;
		public TextView goodcount;
		public TextView orderdes;
	}
}

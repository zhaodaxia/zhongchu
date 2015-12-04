package com.cai.vegetables.adapter;

import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.cai.vegetables.R;
import com.cai.vegetables.activity.market.GoodsItemActivity;
import com.cai.vegetables.entity.FoldInfo;

/** 
* 自定义listview适配器
* @author dongsy  
* @version 创建时间：2015年10月26日 下午9:47:39 
*/
public class FoldListviewAdapter extends MyBaseAdapter<FoldInfo> {
	
//	private Context context;

	public FoldListviewAdapter(List<FoldInfo> lists, Context context) {
		super(lists, context);
		// TODO Auto-generated constructor stub
		arrowdown.setFillAfter(true);
		arrowup.setFillAfter(true);
	}

	private FoldHolder holder;
	private FoldGridItemAdpter gridadpter;
	private Animation arrowdown=AnimationUtils.loadAnimation(context, R.anim.rotate_animdown);
	private Animation arrowup=AnimationUtils.loadAnimation(context, R.anim.rotate_animup);
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		if(convertView==null){
			itemview=View.inflate(context, R.layout.foldlistitem_layout, null);
			holder=new FoldHolder();
			holder.foldicon=(ImageView) itemview.findViewById(R.id.iv_foldicon);
			holder.foldname=(TextView) itemview.findViewById(R.id.tv_foldname);
			holder.rl_fold=(RelativeLayout) itemview.findViewById(R.id.rl_product_item);
			itemview.setTag(holder);
		}
		else{
			holder=(FoldHolder) itemview.getTag();
		}
		final GridView product_gridview=(GridView) itemview.findViewById(R.id.foldgrid);
		final ImageView foldarrow=(ImageView) itemview.findViewById(R.id.iv_foldarrow);
		final FoldInfo info=lists.get(position);
		if(info.IsVisvable){
			product_gridview.setVisibility(View.VISIBLE);
			foldarrow.startAnimation(arrowdown);	
		}else{
			product_gridview.setVisibility(View.GONE);
		}
		holder.foldname.setText(lists.get(position).SearchName);
		holder.rl_fold.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(info.IsVisvable){
				product_gridview.setVisibility(View.GONE);
				foldarrow.startAnimation(arrowup);
				info.IsVisvable=false;
				}
				else{
				product_gridview.setVisibility(View.VISIBLE);
				foldarrow.startAnimation(arrowdown);	
				info.IsVisvable=true;
				}
			}
		});
		gridadpter=new FoldGridItemAdpter(lists.get(position).SearchItemlist, context);
		product_gridview.setAdapter(gridadpter);
		product_gridview.setSelector(new ColorDrawable(Color.TRANSPARENT));//取消GridView中Item选中时默认的背景色  
		product_gridview.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Intent intent=new Intent(context,GoodsItemActivity.class);
				context.startActivity(intent);
			}
		});
		
		return itemview;
	}

	private class FoldHolder{
		ImageView foldicon;
		TextView foldname;
		RelativeLayout rl_fold;
	}
	
	//设置当前展开的listitem
	public void setcurrentitem(int postion){
		
	}
}

package com.cai.vegetables.pager;

import java.util.ArrayList;
import java.util.List;

import com.cai.vegetables.R;
import com.cai.vegetables.activity.cookbook.CookBookDetailAct;
import com.cai.vegetables.activity.raise.RaiseDetailActivity;
import com.cai.vegetables.adapter.CookBookAdapter;
import com.cai.vegetables.adapter.FoldGridItemAdpter;
import com.cai.vegetables.view.pulltorefresh.PullToRefreshListView;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.AdapterView.OnItemClickListener;

/**
 * 众厨菜谱pager
 * 
 * @author yang
 * 
 */
public class CookBookPager extends BasePager {
	private PullToRefreshListView plv;
	private GridView product_gridview;
	private FoldGridItemAdpter adapter;

	public CookBookPager(Context context) {
		super(context);
	}

	public CookBookPager(Context context, int flag) {
		super(context);
	}

	@Override
	public View initView() {
		View view = View.inflate(context, R.layout.cookbook_pager, null);
		plv = (PullToRefreshListView) view.findViewById(R.id.plv);
		product_gridview=(GridView) view.findViewById(R.id.foldgrid);
		plv.setPullRefreshEnabled(true);
		plv.setScrollLoadEnabled(true);
		plv.setPullLoadEnabled(false);
		plv.setHasMoreData(true);
		plv.getRefreshableView().setDivider(null);
		plv.getRefreshableView().setVerticalScrollBarEnabled(false);
		plv.getRefreshableView().setSelector(
				new ColorDrawable(Color.TRANSPARENT));
		plv.getRefreshableView().setAdapter(new CookBookAdapter(context));
		plv.getRefreshableView().setOnItemClickListener(
				new OnItemClickListener() {

					@Override
					public void onItemClick(AdapterView<?> parent, View view,
							int position, long id) {
						Intent intent = new Intent(context,
								CookBookDetailAct.class);
						context.startActivity(intent);
					}
				});
		
		List<String> lists=new ArrayList<String>();
		lists.add("全部");
		lists.add("热门");
		lists.add("时间");
 		adapter=new FoldGridItemAdpter(lists, context);
		product_gridview.setAdapter(adapter);
		product_gridview.setSelector(new ColorDrawable(Color.TRANSPARENT));
		return view;
	}

	@Override
	public void initData() {

	}

}

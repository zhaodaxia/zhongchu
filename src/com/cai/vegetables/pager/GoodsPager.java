package com.cai.vegetables.pager;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.AdapterView.OnItemClickListener;

import com.cai.vegetables.R;
import com.cai.vegetables.activity.market.GoodsDetailActivity;
import com.cai.vegetables.adapter.FoldGridItemAdpter;
import com.cai.vegetables.adapter.GvMainAdapter;
import com.cai.vegetables.view.pulltorefresh.PullToRefreshGridView;
import com.lidroid.xutils.view.annotation.ViewInject;

/**
 * 商品
 * @author wangbin
 *
 */
public class GoodsPager extends BasePager {

    private PullToRefreshGridView gv;
    private GridView product_gridview;
    private FoldGridItemAdpter adapter;
	public GoodsPager(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	@Override
	public View initView() {
		View view=View.inflate(context, R.layout.pager_all, null);
		gv=(PullToRefreshGridView) view.findViewById(R.id.pgv);
		product_gridview=(GridView) view.findViewById(R.id.foldgrid);
		gv.getRefreshableView().setNumColumns(2);
		gv.getRefreshableView().setAdapter(new GvMainAdapter(context));
		gv.getRefreshableView().setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Intent intent=new Intent(context,GoodsDetailActivity.class);
				context.startActivity(intent);
			}
		});
		
		List<String> lists=new ArrayList<String>();
		lists.add("全部菜品");
		lists.add("精品蔬菜");
		lists.add("全部菜品");
		lists.add("茄果类");
		lists.add("叶菜类");
		adapter=new FoldGridItemAdpter(lists, context);
		product_gridview.setAdapter(adapter);
		product_gridview.setSelector(new ColorDrawable(Color.TRANSPARENT));
		return view;
	}

	@Override
	public void initData() {
		
	}


}

package com.cai.vegetables.activity.cookbook;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

import com.cai.vegetables.R;
import com.cai.vegetables.activity.BaseActivity;
import com.cai.vegetables.adapter.CommentAdapter;
import com.cai.vegetables.view.pulltorefresh.PullToRefreshListView;
import com.lidroid.xutils.view.annotation.ViewInject;

/**
 * 评论列表
 * @author wangbin
 *
 */
public class CommentActivity extends BaseActivity{
    @ViewInject(R.id.plv)
	private PullToRefreshListView plv;
	@Override
	public void setLayout() {
		setContentView(R.layout.activity_comment);
	}

	@Override
	public void init(Bundle savedInstanceState) {
		setTopTitle("评论列表");
		setRightTop2(R.drawable.edit, new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				gotoActivity(CommActivity.class, false);
			}
		});
		plv.getRefreshableView().setAdapter(new CommentAdapter(this));
	}

}

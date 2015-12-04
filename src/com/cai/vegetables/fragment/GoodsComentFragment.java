package com.cai.vegetables.fragment;

import com.cai.vegetables.R;
import com.cai.vegetables.adapter.CommentAdapter;
import com.leaf.library.widget.MyListView;
import com.lidroid.xutils.view.annotation.ViewInject;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

/**
 * 产品评论
 * @author wangbin
 *
 */
public class GoodsComentFragment extends BaseFragment{
    @ViewInject(R.id.lvComm)
    private MyListView lvComm;
	@Override
	public View initView(LayoutInflater inflater) {
		return View.inflate(getActivity(), R.layout.fragment_comment, null);
	}

	@Override
	public void initData(Bundle savedInstanceState) {
		lvComm.setAdapter(new CommentAdapter(getActivity()));
	}

}

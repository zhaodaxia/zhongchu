package com.cai.vegetables.fragment;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListView;

import com.cai.vegetables.R;
import com.cai.vegetables.adapter.FoldListviewAdapter;
import com.cai.vegetables.entity.FoldInfo;
import com.cai.vegetables.utils.scan.CaptureActivity;
import com.cai.vegetables.widget.ClearEditText;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;

/**
 * 市场
 * 
 * @author wangbin
 * 
 */
public class MarketFragment extends BaseFragment {
	@ViewInject(R.id.clEt)
	private ClearEditText clearEt;
	@ViewInject(R.id.mark_list)
	private ListView lv;

//	private PinnedHeaderExpandableAdapter adapter;
//	private int expandFlag = -1;// 控制列表的展开
//	private String[][] childrenData = new String[10][10];
//	private String[] groupData = new String[10];
	private List<FoldInfo> foldlist;
	private FoldListviewAdapter adapter;

	@Override
	public View initView(LayoutInflater inflater) {
		View view = inflater.inflate(R.layout.fragment_market, null);
		return view;
	}

	@Override
	public void initData(Bundle savedInstanceState) {
		clearEt.setHint("请输入商品名");

		foldlist=new ArrayList<FoldInfo>();
		InitTestData();
		adapter=new FoldListviewAdapter(foldlist,getActivity());
		lv.setAdapter(adapter);
//		for(int i=0;i<10;i++){
//			groupData[i] = "分组"+i;
//		}
//		
//		for(int i=0;i<10;i++){
//			for(int j=0;j<10;j++){
//				childrenData[i][j] = "好友"+i+"-"+j;
//			}
//		}
//		// 设置悬浮头部VIEW
//		explistview.setHeaderView(getActivity().getLayoutInflater().inflate(
//				R.layout.group_head, explistview, false));
//		adapter = new PinnedHeaderExpandableAdapter(childrenData, groupData,
//				getActivity(), explistview);
//		explistview.setAdapter(adapter);
//
//		// 设置单个分组展开
//		// explistview.setOnGroupClickListener(new GroupClickListener());
	}

	@OnClick(R.id.llScan)
	public void toClick(View view) {
		Intent intent = null;
		switch (view.getId()) {
		case R.id.llScan:
			intent = new Intent(getActivity(), CaptureActivity.class);
			startActivity(intent);
			break;
		}
	}
	
	//模拟数据
		private void InitTestData() {
			// TODO Auto-generated method stub
			List<String> list1=new ArrayList<String>();
			list1.add("精品1");
			list1.add("精品2");
			list1.add("精品3");
			list1.add("精品4");
			list1.add("精品5");
			List<String> list2=new ArrayList<String>();
			list2.add("水果1");
			list2.add("水果2");
			list2.add("水果3");
			list2.add("水果4");
			list2.add("水果5");
			foldlist.add(new FoldInfo("精品菜",list1,false));
			foldlist.add(new FoldInfo("水果",list2,false));
		}

//	class GroupClickListener implements OnGroupClickListener {
//		@Override
//		public boolean onGroupClick(ExpandableListView parent, View v,
//				int groupPosition, long id) {
//			if (expandFlag == -1) {
//				// 展开被选的group
//				explistview.expandGroup(groupPosition);
//				// 设置被选中的group置于顶端
//				explistview.setSelectedGroup(groupPosition);
//				expandFlag = groupPosition;
//			} else if (expandFlag == groupPosition) {
//				explistview.collapseGroup(expandFlag);
//				expandFlag = -1;
//			} else {
//				explistview.collapseGroup(expandFlag);
//				// 展开被选的group
//				explistview.expandGroup(groupPosition);
//				// 设置被选中的group置于顶端
//				explistview.setSelectedGroup(groupPosition);
//				expandFlag = groupPosition;
//			}
//			return true;
//		}
//	}

}

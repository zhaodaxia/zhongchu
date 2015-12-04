package com.cai.vegetables.activity.myself;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.widget.ListView;

import com.cai.vegetables.R;
import com.cai.vegetables.activity.BaseActivity;
import com.cai.vegetables.adapter.FoldListviewAdapter;
import com.cai.vegetables.entity.FoldInfo;
import com.lidroid.xutils.view.annotation.ViewInject;

/** 
* 商品分类
* @author dongsy  
* @version 创建时间：2015年10月26日 下午6:32:52 
*/
public class ProductClassActivity extends BaseActivity {

	@ViewInject(R.id.product_list)
	ListView product_list;
	
	private List<FoldInfo> foldlist;
	private FoldListviewAdapter adapter;
	@Override
	public void setLayout() {
		// TODO Auto-generated method stub
		setContentView(R.layout.activity_productclass);
	}

	@Override
	public void init(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		setTopTitle("商品分类");
		foldlist=new ArrayList<FoldInfo>();
		InitTestData();
		adapter=new FoldListviewAdapter(foldlist,this);
		product_list.setAdapter(adapter);
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
		foldlist.add(new FoldInfo("精品菜",list1,true));
		foldlist.add(new FoldInfo("水果",list2,false));
	}

}

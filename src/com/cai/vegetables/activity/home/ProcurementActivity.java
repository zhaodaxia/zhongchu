package com.cai.vegetables.activity.home;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.cai.vegetables.R;
import com.cai.vegetables.activity.BaseActivity;
import com.cai.vegetables.adapter.FoldListviewAdapter;
import com.cai.vegetables.entity.FoldInfo;
import com.cai.vegetables.utils.scan.CaptureActivity;
import com.cai.vegetables.widget.ClearEditText;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;

/**
 * 企业采购/西餐食材
 * 
 * @author wangbin
 * 
 */
public class ProcurementActivity extends BaseActivity {
	@ViewInject(R.id.clEt)
	private ClearEditText clearEt;
	@ViewInject(R.id.mark_list)
	private ListView lv;
    
	@ViewInject(R.id.tv_title)
	private TextView tvTitle;
	private List<FoldInfo> foldlist;
	private FoldListviewAdapter adapter;

	public static final String TYPE = "type";

	private int POSITION;

	@Override
	public void setLayout() {
		setContentView(R.layout.activity_procurement);
	}

	@Override
	public void init(Bundle savedInstanceState) {
		
		POSITION=getIntent().getIntExtra(TYPE, 0);
		if(POSITION==1){
			tvTitle.setText("企业采购");
		}else if(POSITION==2){
			tvTitle.setText("西餐食材");
		}
		clearEt.setHint("请输入商品名");
   
		foldlist = new ArrayList<FoldInfo>();
		InitTestData();
		adapter = new FoldListviewAdapter(foldlist, this);
		lv.setAdapter(adapter);
	}

	@OnClick(R.id.llScan)
	public void toClick(View view) {
		Intent intent = null;
		switch (view.getId()) {
		case R.id.llScan:
			intent = new Intent(this, CaptureActivity.class);
			startActivity(intent);
			break;
		}
	}

	// 模拟数据
	private void InitTestData() {
		// TODO Auto-generated method stub
		List<String> list1 = new ArrayList<String>();
		list1.add("精品1");
		list1.add("精品2");
		list1.add("精品3");
		list1.add("精品4");
		list1.add("精品5");
		List<String> list2 = new ArrayList<String>();
		list2.add("水果1");
		list2.add("水果2");
		list2.add("水果3");
		list2.add("水果4");
		list2.add("水果5");
		foldlist.add(new FoldInfo("精品菜", list1, false));
		foldlist.add(new FoldInfo("水果", list2, false));
	}

}

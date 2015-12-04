package com.cai.vegetables.activity.cookbook;

import java.util.ArrayList;
import java.util.List;

import com.cai.vegetables.R;
import com.cai.vegetables.activity.BaseActivity;
import com.cai.vegetables.entity.CookBook;
import com.cai.vegetables.entity.CookStep;
import com.cai.vegetables.utils.SharedPreferencesUtils;
import com.cai.vegetables.widget.CookLayoutViewFive;
import com.leaf.library.widget.MyListView;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.tandong.sa.json.Gson;
import com.tandong.sa.json.reflect.TypeToken;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

/**
 * 发布菜谱第五步
 * 
 * @author yang
 *
 */
public class ReleaseFiveAct extends CookBase {
	@ViewInject(R.id.releasefive_lv)
	private LinearLayout releasefive_lv;

	@ViewInject(R.id.releasefive_btn)
	private Button releasefive_btn;
	private int width;
	private CookLayoutViewFive itemview;
	private int postion=0;
	private List<CookLayoutViewFive> viewlists;
	private List<CookStep> steplist;
	private CookStep stepinfo;
	@Override
	public void setCookLayout() {
		setContentView(R.layout.releasefive);
	}

	@SuppressLint("NewApi")
	@Override
	public void initCook(Bundle savedInstanceState) {
		setTopTitle("添加制作步骤");
		setRightBtn2("取消", new OnClickListener() {
			@Override
			public void onClick(View v) {

			}
		});
		// 从Android 3.2（API Level 13）开始，在Activity里使用下面的方法来获取屏幕分辨率（单位是像素）
		Point size = new Point();
		Display display = getWindowManager().getDefaultDisplay();
		display.getSize(size);
		width = size.x;
		viewlists=new ArrayList<CookLayoutViewFive>();
		steplist=new ArrayList<CookStep>();
		if(getIntent().getSerializableExtra(COOKINFO)!=null)
		cook=(CookBook) getIntent().getSerializableExtra(COOKINFO);
		//判断是否存在缓存数据
		String json=SharedPreferencesUtils.getString(this, "COOKSTEP", "");
		if(!TextUtils.isEmpty(json)){
			steplist=new Gson().fromJson(json, new TypeToken<List<CookStep>>(){}.getType());
			for(CookStep stepitem:steplist){
				postion++;
				itemview=new CookLayoutViewFive(this, width, postion,stepitem);
				releasefive_lv.addView(itemview);
				viewlists.add(itemview);
			}
		}
		if(getIntent().getBooleanExtra(ISUPDATE, false)){
			releasefive_btn.setText("保存");
		}
	}

	@OnClick({ R.id.releasefive_btn, R.id.releasefive_add })
	public void todo(View v) {
		switch (v.getId()) {
		case R.id.releasefive_btn:
			cook.steplist=steplist;
			if(getIntent().getBooleanExtra(ISUPDATE, false)){
				gonext=new Intent(this,ReleaseSixAct.class);
				gonext.putExtra("UPDATESTEP",cook );
				setResult(Activity.RESULT_OK, gonext);
				finish();
			}
			else{
			gonext=new Intent(this,ReleaseSixAct.class);
			Bundle mBundle = new Bundle(); 
			mBundle.putSerializable(COOKINFO,cook);  
			gonext.putExtras(mBundle);  
			startActivity(gonext);
			}
			break;
		case R.id.releasefive_add:
			postion++;
			itemview=new CookLayoutViewFive(this, width, postion);
			stepinfo=new CookStep();
			releasefive_lv.addView(itemview);
			viewlists.add(itemview);
			steplist.add(stepinfo);
			break;

		}
	}

	@Override
	protected void onActivityResult(int arg0, int arg1, Intent arg2) {
		// TODO Auto-generated method stub
		super.onActivityResult(arg0, arg1, arg2);
		if(arg1==Activity.RESULT_OK){
			Bundle bundle = arg2.getExtras();
			CookStep iteminfo=(CookStep) arg2.getSerializableExtra(COOKSTEPDES);
			steplist.set(arg0-1, iteminfo);
			viewlists.get(arg0-1).setiteminfo(iteminfo);
			SharedPreferencesUtils.saveString(this, "COOKSTEP", new Gson().toJson(steplist));
		}
	}
	
}

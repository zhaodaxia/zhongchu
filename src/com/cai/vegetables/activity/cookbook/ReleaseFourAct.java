package com.cai.vegetables.activity.cookbook;

import java.util.ArrayList;
import java.util.List;

import com.cai.vegetables.R;
import com.cai.vegetables.activity.BaseActivity;
import com.cai.vegetables.entity.CookBook;
import com.cai.vegetables.utils.SharedPreferencesUtils;
import com.cai.vegetables.widget.CookLayoutViewFour;
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
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;

/**
 * 发布菜谱第四步
 * 
 * @author yang
 *
 */
public class ReleaseFourAct extends CookBase {
	@ViewInject(R.id.releasefour_lv)
	private LinearLayout releasefour_lv;

	@ViewInject(R.id.releasefour_btn)
	private Button releasefour_btn;
	private int width;
	private CookLayoutViewFour cookitem;
	private List<CookLayoutViewFour> edlists;
	private List<String> kitchenlist;
	private List<String> historylist;
	@Override
	public void setCookLayout() {
		setContentView(R.layout.releasefour);
	}

	@SuppressLint("NewApi")
	@Override
	public void initCook(Bundle savedInstanceState) {
		setTopTitle("添加所需用具");
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
		if(getIntent().getSerializableExtra(COOKINFO)!=null)
		cook=(CookBook) getIntent().getSerializableExtra(COOKINFO);
		edlists=new ArrayList<CookLayoutViewFour>();
		kitchenlist=new ArrayList<String>();
		//获取历史缓存的记录
		String json=SharedPreferencesUtils.getString(this, SPCOOKUSE, "");
		if(!TextUtils.isEmpty(json)){
			historylist=new ArrayList<String>();
			historylist=new Gson().fromJson(json,new TypeToken<List<String>>() {
			}.getType());
			for(String historyitem:historylist){
				cookitem=new CookLayoutViewFour(this, width,historyitem);
				releasefour_lv.addView(cookitem);
				edlists.add(cookitem);
			}
		}
		if(getIntent().getBooleanExtra(ISUPDATE, false)){
			releasefour_btn.setText("保存");
		}
	}

	@OnClick({R.id.releasefour_btn,R.id.releasedour_add})
	public void todo(View v) {
		switch (v.getId()) {
		case R.id.releasefour_btn:
			for(CookLayoutViewFour editem:edlists){
				if(!TextUtils.isEmpty(editem.getIteminfo()))
				kitchenlist.add(editem.getIteminfo());
			}
			//缓存选择的厨具
			SharedPreferencesUtils.saveString(this, SPCOOKUSE, new Gson().toJson(kitchenlist));
			cook.kitchen=kitchenlist;
			if(getIntent().getBooleanExtra(ISUPDATE, false)){
				gonext=new Intent(this,ReleaseSixAct.class);
				gonext.putExtra("UPDATEUSE",cook );
				setResult(Activity.RESULT_OK, gonext);
				finish();
			}else{
			gonext=new Intent(this,ReleaseFiveAct.class);
			Bundle mBundle = new Bundle(); 
			mBundle.putSerializable(COOKINFO,cook);  
			gonext.putExtras(mBundle);  
			startActivity(gonext);
			}
			break;
		case R.id.releasedour_add:
			cookitem=new CookLayoutViewFour(this, width);
			releasefour_lv.addView(cookitem);
			edlists.add(cookitem);
			break;
		}
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		kitchenlist.clear();
	}

}

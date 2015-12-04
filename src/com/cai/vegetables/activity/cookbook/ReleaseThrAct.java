package com.cai.vegetables.activity.cookbook;

import java.util.ArrayList;
import java.util.List;

import com.cai.vegetables.R;
import com.cai.vegetables.activity.BaseActivity;
import com.cai.vegetables.entity.CookBook;
import com.cai.vegetables.entity.Food;
import com.cai.vegetables.utils.SharedPreferencesUtils;
import com.cai.vegetables.wheelview.DensityUtil;
import com.cai.vegetables.widget.CookLayoutView;
import com.cai.vegetables.widget.CookLayoutView.OndeleteClickListener;
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
import android.widget.EditText;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.Toast;

/**
 * 发布菜谱第三步
 * 
 * @author yang
 *
 */
public class ReleaseThrAct extends CookBase {

	@ViewInject(R.id.ll_thrcontain)
	private LinearLayout ll_thrcontain;
	
	@ViewInject(R.id.releasethr_btn)
	private Button releasethr_btn;
	private int width;
	private CookLayoutView itemview;
	private List<Food> fooddatalist;
	private List<CookLayoutView> viewlists;
	private List<Food> historylists;
	@Override
	public void setCookLayout() {
		setContentView(R.layout.releasethr);
	}

	@SuppressLint("NewApi")
	@Override
	public void initCook(Bundle savedInstanceState) {
		setTopTitle("添加所需食材");
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
		fooddatalist=new ArrayList<Food>();
		viewlists=new ArrayList<CookLayoutView>();
		if(getIntent().getSerializableExtra(COOKINFO)!=null)
		cook=(CookBook) getIntent().getSerializableExtra(COOKINFO);
		String json=SharedPreferencesUtils.getString(this, SPCOOKFOOD, "");
		if(!TextUtils.isEmpty(json)){
			historylists=new ArrayList<Food>();
			historylists=new Gson().fromJson(json,new TypeToken<List<Food>>() {
			}.getType());
			//根据历史缓存数据生成view
			for(Food historyitem:historylists){
				itemview=new CookLayoutView(this,width,historyitem);
				itemview.setOnDelListener(new OndeleteClickListener() {
					
					@Override
					public void DelOnclick() {
						// TODO Auto-generated method stub
						viewlists.remove(itemview);
					}
				});
				viewlists.add(itemview);
				ll_thrcontain.addView(itemview);
			}
		}
		if(getIntent().getBooleanExtra(ISUPDATE, false)){
			releasethr_btn.setText("保存");
		}
	}

	@OnClick({ R.id.releasethr_btn, R.id.releasethr_ll })
	public void todo(View v) {
		switch (v.getId()) {
		case R.id.releasethr_btn:
			for(CookLayoutView itemview:viewlists){
				if(itemview.getItemFood()!=null)
				fooddatalist.add(itemview.getItemFood());
			}
			//缓存用户选择的食材
			SharedPreferencesUtils.saveString(this, SPCOOKFOOD, new Gson().toJson(fooddatalist));
			cook.foodlist=fooddatalist;
			if(getIntent().getBooleanExtra(ISUPDATE, false)){
				gonext=new Intent(this,ReleaseSixAct.class);
				gonext.putExtra("UPDATEFOOD",cook );
				setResult(Activity.RESULT_OK, gonext);
				finish();
			}
			else{
			gonext=new Intent(this,ReleaseFourAct.class);
			Bundle mBundle = new Bundle(); 
			mBundle.putSerializable(COOKINFO,cook);  
			gonext.putExtras(mBundle);  
			startActivity(gonext);
			}
			break;
		case R.id.releasethr_ll:
			itemview=new CookLayoutView(this,width);
			itemview.setOnDelListener(new OndeleteClickListener() {
				
				@Override
				public void DelOnclick() {
					// TODO Auto-generated method stub
					viewlists.remove(itemview);
				}
			});
			viewlists.add(itemview);
			ll_thrcontain.addView(itemview);
			break;
		}
	}
	
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		fooddatalist.clear();
	}
}

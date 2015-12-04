package com.cai.vegetables.activity.cookbook;

import com.cai.vegetables.R;
import com.cai.vegetables.entity.CookBook.BaseInfo;
import com.cai.vegetables.utils.SharedPreferencesUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.TextView;

/**
 * 发布菜谱第一步
 * 
 * @author yang
 *
 */
public class ReleaseFirAct extends CookBase {
	@ViewInject(R.id.releasefir_btn)
	private Button releasefir_btn;

	@ViewInject(R.id.ed_cookname)
	private EditText ed_cookname;
	
	@ViewInject(R.id.ed_cookdes)
	private EditText ed_cookdes;
	
	@ViewInject(R.id.tv_level)
	private TextView tv_level;
	
	@ViewInject(R.id.tv_cookstye)
	private TextView tv_cookstye;
	
	private BaseInfo cook_baseinfo;
	
	private PopupWindow popupWindow;
	private int with;
	@Override
	public void setCookLayout() {
		setContentView(R.layout.releasefir);
	}

	@Override
	public void initCook(Bundle savedInstanceState) {
		setTopTitle("菜谱基本信息");
		setRightBtn2("取消", new OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
			}
		});
		cook_baseinfo=cook.new BaseInfo();
		with=getWindowManager().getDefaultDisplay().getWidth();
		if(getIntent().getBooleanExtra(ISUPDATE, false)){
			releasefir_btn.setText("保存");
		}
		ed_cookname.setText(SharedPreferencesUtils.getString(this, "COOKNAME", ""));
		ed_cookdes.setText(SharedPreferencesUtils.getString(this, "COOKDES", ""));
		tv_level.setText(SharedPreferencesUtils.getString(this, "COOKLEVEL", ""));
		tv_cookstye.setText(SharedPreferencesUtils.getString(this, "COOKSTYE", ""));
	}
	
	@OnClick({R.id.releasefir_btn,R.id.rl_chooselevel,R.id.rl_choosestye})
	public void todo(View v){
		switch (v.getId()) {
		case R.id.releasefir_btn:
			boolean isnull=true;
			//判断并缓存不为空的数据
			if(!TextUtils.isEmpty(ed_cookname.getText().toString().trim())){
				SharedPreferencesUtils.saveString(this, "COOKNAME", ed_cookname.getText().toString().trim());
				cook_baseinfo.cookname=ed_cookname.getText().toString().trim();
				isnull=false;
			}
			if(!TextUtils.isEmpty(ed_cookdes.getText().toString().trim())){
				SharedPreferencesUtils.saveString(this, "COOKDES", ed_cookdes.getText().toString().trim());
				cook_baseinfo.cookdes=ed_cookdes.getText().toString().trim();
				isnull=false;
			}
			if(!TextUtils.isEmpty(tv_level.getText().toString().trim())){
				SharedPreferencesUtils.saveString(this, "COOKLEVEL", tv_level.getText().toString().trim());
				cook_baseinfo.level=tv_level.getText().toString().trim();
				isnull=false;
			}
			if(!TextUtils.isEmpty(tv_cookstye.getText().toString().trim())){
				SharedPreferencesUtils.saveString(this, "COOKSTYE", tv_cookstye.getText().toString().trim());
				cook_baseinfo.cookstye=tv_cookstye.getText().toString().trim();
				isnull=false;
			}
			if(!isnull)
			cook.cookinfo=cook_baseinfo;
			//判断是否是从预览跳转
			if(getIntent().getBooleanExtra(ISUPDATE, false)){
				gonext=new Intent(this,ReleaseSixAct.class);
				Bundle mBundle = new Bundle(); 
				mBundle.putSerializable(COOKINFO,cook);  
				gonext.putExtras(mBundle);  
				setResult(Activity.RESULT_OK, gonext);
				finish();
			}
			else{
			gonext=new Intent(this,ReleaseSecAct.class);
			Bundle mBundle = new Bundle(); 
			mBundle.putSerializable(COOKINFO,cook);  
			gonext.putExtras(mBundle);  
			startActivity(gonext);
			}
			break;

		case R.id.rl_chooselevel:
			showPopupWindow();
			break;
		case R.id.rl_choosestye:
			showChooseStype();
			break;
		}
	}

	private void showChooseStype() {
		// TODO Auto-generated method stub

		if(popupWindow!=null){
			popupWindow.dismiss();
		}
		View view=View.inflate(this,R.layout.style_popup,null);
		TextView tvVegetable=(TextView) view.findViewById(R.id.tvVegetable);
		TextView tvStore=(TextView) view.findViewById(R.id.tvStore);
		TextView tv_sweet=(TextView) view.findViewById(R.id.tv_sweet);
		tvVegetable.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				tv_cookstye.setText("西餐");
				popupWindow.dismiss();
			}
		});
		tvStore.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				tv_cookstye.setText("中餐");
				popupWindow.dismiss();
			}
		});
		tv_sweet.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				tv_cookstye.setText("甜品");
				popupWindow.dismiss();
			}
		});
		popupWindow=new PopupWindow(view, LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT, false);
		popupWindow.setOutsideTouchable(true);
		  popupWindow.setBackgroundDrawable(new BitmapDrawable());  
		popupWindow.showAsDropDown((View) tv_cookstye.getParent(), with, 0);
	
	}

	private void showPopupWindow(){
		if(popupWindow!=null){
			popupWindow.dismiss();
		}
		View view=View.inflate(this,R.layout.level_popup,null);
		TextView tvVegetable=(TextView) view.findViewById(R.id.tvVegetable);
		TextView tvStore=(TextView) view.findViewById(R.id.tvStore);
		tvVegetable.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				tv_level.setText("简单");
				popupWindow.dismiss();
			}
		});
		tvStore.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				tv_level.setText("困难");
				popupWindow.dismiss();
			}
		});
		popupWindow=new PopupWindow(view, LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT, false);
		popupWindow.setOutsideTouchable(true);
		  popupWindow.setBackgroundDrawable(new BitmapDrawable());  
		popupWindow.showAsDropDown((View) tv_level.getParent(), with, 0);
	}
	
}

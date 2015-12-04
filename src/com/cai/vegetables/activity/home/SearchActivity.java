package com.cai.vegetables.activity.home;

import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.cai.vegetables.R;
import com.cai.vegetables.activity.BaseActivity;
import com.cai.vegetables.utils.scan.CaptureActivity;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;

/**
 * 搜索
 * @author wangbin
 *
 */
public class SearchActivity extends BaseActivity{
    
	@ViewInject(R.id.tvVe)
	private TextView tvVe;//
	
	private PopupWindow popupWindow;
	@Override
	public void setLayout() {
		setContentView(R.layout.activity_search);
	}

	@Override
	public void init(Bundle savedInstanceState) {
		
	}
	
	@OnClick({R.id.llScan,R.id.tvVe})
	public void toClick(View v){
		switch(v.getId()){
		//扫描
		case R.id.llScan:
			gotoActivity(CaptureActivity.class, false);
			break;
		//弹PopupWindow
		case R.id.tvVe:
			showPopupWindow();
			break;
		}
	}
	
	/**
	 * 展示popupWindow
	 */
	private void showPopupWindow(){
		if(popupWindow!=null){
			popupWindow.dismiss();
		}
		View view=View.inflate(this,R.layout.search_popup,null);
		TextView tvVegetable=(TextView) view.findViewById(R.id.tvVegetable);//蔬菜
		TextView tvStore=(TextView) view.findViewById(R.id.tvStore);//店铺
		tvVegetable.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				tvVe.setText("蔬菜");
				popupWindow.dismiss();
			}
		});
		tvStore.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				tvVe.setText("店铺");
				popupWindow.dismiss();
			}
		});
		popupWindow=new PopupWindow(view, LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT, false);
		popupWindow.setOutsideTouchable(true);
		  popupWindow.setBackgroundDrawable(new BitmapDrawable());  
		popupWindow.showAsDropDown((View) tvVe.getParent(), 0, 0);
	}

}

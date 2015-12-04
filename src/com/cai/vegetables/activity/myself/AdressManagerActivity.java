package com.cai.vegetables.activity.myself;

import com.cai.vegetables.R;
import com.cai.vegetables.activity.BaseActivity;
import com.cai.vegetables.activity.shop.AddAdressActivity;
import com.cai.vegetables.utils.VegetableUtils;
import com.cai.vegetables.view.LoadingView;
import com.cai.vegetables.view.pulltorefresh.PullToRefreshListView;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

/** 
* 我的-收获地址
* @author dongsy  
* @version 创建时间：2015年10月30日 下午9:04:48 
*/
public class AdressManagerActivity extends BaseActivity {

	 @ViewInject(R.id.loadView)
	    private LoadingView loadView;
		@ViewInject(R.id.plv)
		private PullToRefreshListView plv;
		@Override
		public void setLayout() {
			setContentView(R.layout.activity_select_address);
		}

		@Override
		public void init(Bundle savedInstanceState) {
			// TODO Auto-generated method stub
			setTopTitle("收获地址");
			loadView.noContent();
			//管理地址
			setRightTop(R.drawable.shop_manager, new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					
				}
			});
			
			plv.setPullRefreshEnabled(true);
			plv.setScrollLoadEnabled(true);
			plv.setPullLoadEnabled(false);
			plv.setHasMoreData(true);
			plv.getRefreshableView().setDivider(null);
			plv.getRefreshableView().setVerticalScrollBarEnabled(
					false);
			plv.getRefreshableView().setSelector(
					new ColorDrawable(Color.TRANSPARENT));
			plv.setLastUpdatedLabel(VegetableUtils.getCurrentTime());
		}
		
		
		@OnClick(R.id.tvAddress)
		public void toClick(View v){
			switch(v.getId()){
			case R.id.tvAddress:
				gotoActivity(AddAdressActivity.class, false);
				break;
			}
		}

}

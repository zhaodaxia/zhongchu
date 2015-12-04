package com.cai.vegetables.activity.myself;

import com.cai.vegetables.R;
import com.cai.vegetables.activity.BaseActivity;
import com.cai.vegetables.activity.cookbook.ReleaseSixAct;
import com.cai.vegetables.widget.ShareDialog;
import com.cai.vegetables.widget.ShareDialog.ShareDialogOnclickListener;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.RatingBar.OnRatingBarChangeListener;
import android.widget.RelativeLayout;

/** 
* 同城货的订单详情
* @author dongsy  
* @version 创建时间：2015年10月30日 下午6:34:25 
*/
public class SameCityDetailActivity extends BaseActivity {
	
	@ViewInject(R.id.driver_rating)
	private RatingBar driver_rating;
	
	@ViewInject(R.id.rl_right2)
	private RelativeLayout rl_right2;
	
	@ViewInject(R.id.disvisvible_bottom)
	private LinearLayout disvisvible_bottom;
	private float rating_count=0.0f;
	private ShareDialog dialog;
	@Override
	public void setLayout() {
		// TODO Auto-generated method stub
		setContentView(R.layout.samecityorder_detail);
	}

	@Override
	public void init(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		setTopTitle("订单详情");
		driver_rating.setOnRatingBarChangeListener(new OnRatingBarChangeListener() {
			
			@Override
			public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
				// TODO Auto-generated method stub
				rating_count=driver_rating.getRating();
			}
		});
	}
	
	@OnClick(R.id.bt_common)
	public void onclick(View v){
		switch (v.getId()) {
		case R.id.bt_common:
			driver_rating.setRating(rating_count);
			//设置评分不可选
			driver_rating.setIsIndicator(true);
			disvisvible_bottom.setVisibility(View.GONE);
			setRightTop2(R.drawable.zccp_03, new OnClickListener() {
				@Override
				public void onClick(View v) {
					share();
				}
			});
			break;

		default:
			break;
		}
	}
	

	private void share() {
		dialog=new ShareDialog(this, R.style.ActionSheetDialogStyle);
		dialog.show();
		dialog.setTop("既然评价了，就告诉小伙伴吧！");
		dialog.setShareOnclickListener(new ShareDialogOnclickListener() {

			@Override
			public void momentsOnclick() {
				// TODO Auto-generated method stub
				GoShare();
			}
			
			@Override
			public void WechatOnclick() {
				// TODO Auto-generated method stub
				GoShare();
			}
			
			@Override
			public void TencentOnclick() {
				// TODO Auto-generated method stub
				GoShare();
			}
			
			@Override
			public void SinaOnclick() {
				// TODO Auto-generated method stub
				GoShare();
			}
			
			@Override
			public void QZoneOnclick() {
				// TODO Auto-generated method stub
				GoShare();
			}
			
			@Override
			public void QQOnclick() {
				// TODO Auto-generated method stub
				GoShare();
			}
		});
	}
	
	private void GoShare(){
		Intent share=new Intent(this,ShareFriendActivity.class);
		startActivity(share);
		dialog.dismiss();
	}

}

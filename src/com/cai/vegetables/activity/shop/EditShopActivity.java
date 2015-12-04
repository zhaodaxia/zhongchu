package com.cai.vegetables.activity.shop;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ListView;

import com.cai.vegetables.R;
import com.cai.vegetables.activity.BaseActivity;
import com.cai.vegetables.adapter.ShopEtAdapter;
import com.cai.vegetables.widget.MyShopDialog;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;

/**
 * 编辑购物车
 * @author wangbin
 *
 */
public class EditShopActivity extends BaseActivity{
    @ViewInject(R.id.lvShop)
    private ListView lvShop;
	
	@Override
	public void setLayout() {
		setContentView(R.layout.activity_edit_shop);
	}

	@Override
	public void init(Bundle savedInstanceState) {
		setTopTitle("购物车");
		setRightBtn("完成",new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				finish();
			}
		});
		lvShop.setAdapter(new ShopEtAdapter(this));
	}
	
	@OnClick({R.id.tvColl,R.id.tvDelete})
	public void toClcik(View v){
		switch(v.getId()){
		case R.id.tvColl:
			MyShopDialog dialog=new MyShopDialog(EditShopActivity.this);
			dialog.builder().setIv(R.drawable.shop_coll).setMsg("您的商品已成功移至个人中心－收藏内！").setPositiveButton("我知道了", new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					finish();
				}
			},0).show();
			break;
		case R.id.tvDelete:
			MyShopDialog dialog1=new MyShopDialog(EditShopActivity.this);
			dialog1.builder().setIv(R.drawable.iv_delete).setMsg("您确定要删除所选择的商品吗？").setPositiveButton("我知道了", new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					finish();
				}
			},1).setNegativeButton("取消", null).show();
			break;
		}
	}

}

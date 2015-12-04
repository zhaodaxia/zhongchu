package com.cai.vegetables.activity.community;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ScrollView;

import com.cai.vegetables.R;
import com.cai.vegetables.activity.BaseActivity;
import com.cai.vegetables.activity.photo.ImagePagerActivity;
import com.cai.vegetables.adapter.CommentAdapter;
import com.cai.vegetables.widget.ShareDialog;
import com.leaf.library.widget.MyListView;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;

/**
 * 菜场详情
 * @author wangbin
 *
 */
public class CommDetailActivity extends BaseActivity{
	
	@ViewInject(R.id.lvComm)
	private MyListView lvComm;
	
	@ViewInject(R.id.sv)
	private ScrollView sv;
	private List<String> images=new ArrayList<String>();

	@Override
	public void setLayout() {
		setContentView(R.layout.activity_comm_detail);
	}

	@Override
	public void init(Bundle savedInstanceState) {
		
		lvComm.setAdapter(new CommentAdapter(this));
		sv.post(new Runnable() {   
		    public void run() {  
		    	sv.scrollTo(0, 0);  
		    }   
		});  
	}
	
	@OnClick({R.id.one_share,R.id.llPhoto,R.id.rlIntro,R.id.tvComment,R.id.tvStartShop})
	public void toClick(View v){
		Intent intent=null;
		switch (v.getId()) {
		//分享
		case R.id.one_share:
			ShareDialog dialog = new ShareDialog(this, R.style.ActionSheetDialogStyle);
			dialog.show();
			break;
		//查看照片
		case R.id.llPhoto:
			intent=new Intent(this,ImagePagerActivity.class);
			intent.putExtra(ImagePagerActivity.EXTRA_IMAGE_INDEX, 1);
			images.add("http://app.doowinedu.com/Uploads/talk/api5608b330184e57076.jpg");
			images.add("http://app.doowinedu.com/Uploads/talk/api5608b330184e57076.jpg");
			images.add("http://app.doowinedu.com/Uploads/talk/api5608b330184e57076.jpg");
			intent.putExtra(ImagePagerActivity.EXTRA_IMAGE_INDEX, 1);
			intent.putStringArrayListExtra(ImagePagerActivity.EXTRA_IMAGE_URLS, (ArrayList<String>) images);
			startActivity(intent);
			break;
		//菜场介绍
		case R.id.rlIntro:
			gotoActivity(CommIntroActivity.class, false);
			break;
		//写点评
		case R.id.tvComment:
			gotoActivity(ReviewActivity.class, false);
			break;
		//开始购物
		case R.id.tvStartShop:
			gotoActivity(StartShopActivity.class, false);
			break;
		default:
			break;
		}
	
	}

}

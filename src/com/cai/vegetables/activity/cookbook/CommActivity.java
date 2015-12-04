package com.cai.vegetables.activity.cookbook;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

import com.cai.vegetables.R;
import com.cai.vegetables.activity.BaseActivity;
import com.cai.vegetables.activity.photo.PicSelectActivity;
import com.cai.vegetables.adapter.GvPhotoAdapter;
import com.cai.vegetables.entity.ImageBean;
import com.cai.vegetables.widget.MySelfSheetDialog;
import com.cai.vegetables.widget.MySelfSheetDialog.OnSheetItemClickListener;
import com.cai.vegetables.widget.MySelfSheetDialog.SheetItemColor;
import com.cai.vegetables.widget.ShareDialog;
import com.cai.vegetables.widget.ToastCommom;
import com.leaf.library.widget.MyGridView;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;

/**
 * 评论菜谱
 * 
 * @author wangbin
 * 
 */
public class CommActivity extends BaseActivity {
	/**照片List */
    private List<ImageBean> selecteds=new ArrayList<ImageBean>();
    private GvPhotoAdapter adapter;
    @ViewInject(R.id.gvph_ordercomment)
    private MyGridView gvPhoto;
	@Override
	public void setLayout() {
		setContentView(R.layout.activity_comm);
	}

	@Override
	public void init(Bundle savedInstanceState) {
		setRightTop2(R.drawable.zccp_03, new OnClickListener() {
			@Override
			public void onClick(View v) {
				ShareDialog dialog = new ShareDialog(CommActivity.this,
						R.style.ActionSheetDialogStyle);
				dialog.show();
			}
		});
		adapter=new GvPhotoAdapter(selecteds,this);
        gvPhoto.setAdapter(adapter);
	}

	@OnClick({ R.id.ivPhoto,R.id.tvSucess})
	public void toClick(View v) {
		switch (v.getId()) {
		case R.id.ivPhoto:
//			MySelfSheetDialog dialog = new MySelfSheetDialog(CommActivity.this);
//			dialog.builder().addSheetItem("拍照", SheetItemColor.Black,
//					new OnSheetItemClickListener() {
//
//						@Override
//						public void onClick(int which) {
//							// TODO Auto-generated method stub
//
//						}
//					}).addSheetItem("从相册选择", SheetItemColor.Black ,new OnSheetItemClickListener() {
//						
//						@Override
//						public void onClick(int which) {
//							// TODO Auto-generated method stub
//							
//						}
//					}).show();
			break;
		case R.id.tvSucess:
			ToastCommom.createToastConfig().ToastShow(getApplicationContext(), "评价成功");
			break;
		}
	}
	
	 @Override
	    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
	        super.onActivityResult(requestCode, resultCode, data);
	        /**第一次添加照片 */
	        if(requestCode==0x123&&resultCode==RESULT_OK){
	            if(data!=null){
	                gvPhoto.setVisibility(View.VISIBLE);
	                selecteds=(List<ImageBean>) data.getSerializableExtra(PicSelectActivity.IMAGES);
	                if(adapter==null){
	                    adapter=new GvPhotoAdapter(selecteds, CommActivity.this);
	                    gvPhoto.setAdapter(adapter);
	                }else{
	                    adapter.notifyDataSetChanged();
	                }
//	                tvSelect.setVisibility(View.GONE);
	            }
	        }

	        /**继续添加照片 */
	        if(requestCode==GvPhotoAdapter.ADD_PHOTO_CODE&&resultCode==RESULT_OK){
	            if(data!=null){
	                List<ImageBean> addSelect=(List<ImageBean>) data.getSerializableExtra(PicSelectActivity.IMAGES);
	                selecteds.addAll(addSelect);
	                adapter.notifyDataSetChanged();
	            }
	        }
	        /**预览删除照片*/
	        if(requestCode==GvPhotoAdapter.PHOTO_CODE0&&resultCode==RESULT_OK){
	            if(data!=null){
	                if(selecteds.size()>1){
	                    selecteds=(List<ImageBean>)data.getSerializableExtra("M_LIST");
	                    adapter.notifyDataChange(selecteds);
	                }
	            }
	        }

	    }

}

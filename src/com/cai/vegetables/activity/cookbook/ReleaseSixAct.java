package com.cai.vegetables.activity.cookbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;

import com.cai.vegetables.R;
import com.cai.vegetables.activity.BaseActivity;
import com.cai.vegetables.entity.CookBook;
import com.cai.vegetables.utils.MyBitmapUtils;
import com.cai.vegetables.utils.SharedPreferencesUtils;
import com.cai.vegetables.widget.MySelfSheetDialog;
import com.cai.vegetables.widget.MySelfSheetDialog.OnSheetItemClickListener;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.provider.MediaStore.MediaColumns;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * 发布菜谱第六步，预览
 * 
 * @author yang
 *
 */
public class ReleaseSixAct extends CookBase {

	@ViewInject(R.id.releasesix_tv)
	private TextView takephoto;// 添加照片
	@ViewInject(R.id.releasesix_iv)
	private ImageView displayPic;// 添加照片
	
	@ViewInject(R.id.iv_cook1)
	private ImageView iv_cook1;
	
	@ViewInject(R.id.iv_cook2)
	private ImageView iv_cook2;
	
	@ViewInject(R.id.iv_cook3)
	private ImageView iv_cook3;
	
	@ViewInject(R.id.iv_cook4)
	private ImageView iv_cook4;
	
	@ViewInject(R.id.iv_cook5)
	private ImageView iv_cook5;
	
	@ViewInject(R.id.tv_cook1)
	private TextView tv_cook1;
	
	@ViewInject(R.id.tv_cook2)
	private TextView tv_cook2;
	
	@ViewInject(R.id.tv_cook3)
	private TextView tv_cook3;
	
	@ViewInject(R.id.tv_cook4)
	private TextView tv_cook4;
	
	@ViewInject(R.id.tv_cook5)
	private TextView tv_cook5;
	
	public static final int PHOTOZOOM = 0;
	public static final int PHOTOTAKE = 1;
	public static final int IMAGE_COMPLETE = 2; // 结果
	public static final int CROPREQCODE = 3; // 截取
	private String photoSavePath;
	private String photoSaveName;
	private String path;// 图片全路径
	private String wrongdes="未填写";
	String appHome = Environment.getExternalStorageDirectory().getAbsolutePath() + "/park_tx";
	private Bitmap getimage;

	private Intent gointent;
	private Bundle mBundle;
	@Override
	public void setCookLayout() {
		setContentView(R.layout.releasesix);
	}

	@Override
	public void initCook(Bundle savedInstanceState) {
		setTopTitle("菜谱总览");
		setRightBtn2("预览", new OnClickListener() {
			@Override
			public void onClick(View arg0) {

			}
		});
		
		File file = new File(Environment.getExternalStorageDirectory(), "ClipHeadPhoto/cache");

		if (!file.exists())
			file.mkdirs();
		photoSavePath = Environment.getExternalStorageDirectory() + "/ClipHeadPhoto/cache/";
		cook=(CookBook) getIntent().getSerializableExtra(COOKINFO);
		mBundle = new Bundle(); 
		chechinfo();
	}

	//校验菜谱填写信息
	private void chechinfo() {
		// TODO Auto-generated method stub
		//菜谱基本信息
		if(cook.cookinfo==null){
			iv_cook1.setBackgroundResource(R.drawable.wc_03);
			tv_cook1.setText(wrongdes);
		}else{
			iv_cook1.setBackgroundResource(R.drawable.check_duihao);
			tv_cook1.setText(cook.cookinfo.toString().replaceAll("null", "未填写"));			
		}
		//菜谱封面
		if(TextUtils.isEmpty(SharedPreferencesUtils.getString(this, "COOKIVPATH", ""))){
			iv_cook2.setBackgroundResource(R.drawable.wc_03);
			tv_cook2.setText(wrongdes);
		}else{
			displayPic.setImageBitmap(BitmapFactory.decodeFile(cook.bguri));
			displayPic.setVisibility(View.VISIBLE);
			takephoto.setVisibility(View.GONE);
			iv_cook2.setBackgroundResource(R.drawable.check_duihao);
			tv_cook2.setText("已上传");			
		}
		//所需食材
		if(cook.foodlist.size()==0){
			iv_cook3.setBackgroundResource(R.drawable.wc_03);
			tv_cook3.setText(wrongdes);
		}else{
			iv_cook3.setBackgroundResource(R.drawable.check_duihao);
			tv_cook3.setText(cook.foodlist.toString());			
		}
		//所需用具
		if(cook.kitchen.size()==0){
			iv_cook4.setBackgroundResource(R.drawable.wc_03);
			tv_cook4.setText(wrongdes);
		}else{
			iv_cook4.setBackgroundResource(R.drawable.check_duihao);
			tv_cook4.setText(cook.kitchen.toString());			
		}
		//制作步骤
		if(cook.steplist.size()==0){
			iv_cook5.setBackgroundResource(R.drawable.wc_03);
			tv_cook5.setText(wrongdes);
		}else{
			iv_cook5.setBackgroundResource(R.drawable.check_duihao);
			tv_cook5.setText("共"+cook.steplist.size()+"步");			
		}
	}

	@OnClick({ R.id.releasesix_btn, R.id.releasesix_tv,R.id.rl_cook1,R.id.rl_cook2,R.id.rl_cook3,
		R.id.rl_cook4,R.id.rl_cook5})
	public void todo(View v) {
		switch (v.getId()) {
		case R.id.releasesix_btn:
			gotoActivity(PublishAct.class, false);
			break;
		case R.id.releasesix_tv:
			showdialog();
			break;
		case R.id.rl_cook1:
			gointent=new Intent(this,ReleaseFirAct.class);
			mBundle.putBoolean(ISUPDATE, true);  
			gointent.putExtras(mBundle);  
			startActivityForResult(gointent, 2);
			break;
		case R.id.rl_cook2:
			gointent=new Intent(this,ReleaseSecAct.class);
			mBundle.putBoolean(ISUPDATE, true);  
			gointent.putExtras(mBundle);  
			startActivityForResult(gointent, 3);
			break;
		case R.id.rl_cook3:
			gointent=new Intent(this,ReleaseThrAct.class);
			mBundle.putBoolean(ISUPDATE, true);  
			gointent.putExtras(mBundle);  
			startActivityForResult(gointent, 4);
			break;
		case R.id.rl_cook4:
			gointent=new Intent(this,ReleaseFourAct.class);
			mBundle.putBoolean(ISUPDATE, true);  
			gointent.putExtras(mBundle);    
			startActivityForResult(gointent, 5);
			break;
		case R.id.rl_cook5:
			gointent=new Intent(this,ReleaseFiveAct.class);
			mBundle.putBoolean(ISUPDATE, true);  
			gointent.putExtras(mBundle);  
			startActivityForResult(gointent, 6);
			break;
		}
	}
	
	private void showdialog() {
		MySelfSheetDialog dialog = new MySelfSheetDialog(this).builder();
		dialog.addSheetItem("拍照", null, new OnSheetItemClickListener() {
			@Override
			public void onClick(int which) {
				photoSaveName = String.valueOf(System.currentTimeMillis()) + ".png";
				Uri imageUri = null;
				Intent openCameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
				imageUri = Uri.fromFile(new File(photoSavePath, photoSaveName));
				openCameraIntent.putExtra(MediaStore.Images.Media.ORIENTATION, 0);
				openCameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
				startActivityForResult(openCameraIntent, PHOTOTAKE);
			}
		});
		dialog.addSheetItem("相册选取", null, new OnSheetItemClickListener() {
			@Override
			public void onClick(int which) {
				Intent openAlbumIntent = new Intent(Intent.ACTION_GET_CONTENT);
				openAlbumIntent.setType("image/*");
				startActivityForResult(openAlbumIntent, PHOTOZOOM);
			}
		});
		dialog.show();
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (resultCode != RESULT_OK) {
			return;
		}
		Uri uri = null;
		switch (requestCode) {
		case PHOTOZOOM:// 相册
			if (data == null) {
				return;
			}
			uri = data.getData();
			String[] proj = { MediaColumns.DATA };
			Cursor cursor = managedQuery(uri, proj, null, null, null);
			int column_index = cursor.getColumnIndexOrThrow(MediaColumns.DATA);
			cursor.moveToFirst();
			path = cursor.getString(column_index);// 图片在的路径
			File destDir = new File(appHome);
			if (!destDir.exists()) {
				destDir.mkdirs();
			}
			try {
				int bytesum = 0;
				int byteread = 0;
				File oldfile = new File(path);
				// toastShortShowMessage("path"+path);
				if (oldfile.exists()) { // 文件不存在时
					InputStream inStream = new FileInputStream(path); // 读入原文件
					FileOutputStream fs = new FileOutputStream(appHome + "/tx.png");
					byte[] buffer = new byte[1444];
					int length;
					while ((byteread = inStream.read(buffer)) != -1) {
						bytesum += byteread; // 字节数 文件大小
						fs.write(buffer, 0, byteread);
					}
					inStream.close();
				}
			} catch (Exception e) {
				e.printStackTrace();

			}
			getimage = MyBitmapUtils.getimage(ReleaseSixAct.this, appHome + "/tx.png");
			displayPic.setVisibility(View.VISIBLE);
			displayPic.setImageBitmap(getimage);
			takephoto.setVisibility(View.GONE);
			break;
		case PHOTOTAKE:// 拍照
			path = photoSavePath + photoSaveName;
			uri = Uri.fromFile(new File(path));
			getimage = MyBitmapUtils.getimage(ReleaseSixAct.this, uri.getPath());
			displayPic.setVisibility(View.VISIBLE);
			displayPic.setImageBitmap(getimage);
			takephoto.setVisibility(View.GONE);
			break;
		//修改菜谱基本信息
		case 2:
			cook=(CookBook) data.getSerializableExtra(COOKINFO);
			if(cook.cookinfo==null){
				iv_cook1.setBackgroundResource(R.drawable.wc_03);
				tv_cook1.setText(wrongdes);
			}else{
				iv_cook1.setBackgroundResource(R.drawable.check_duihao);
				tv_cook1.setText(cook.cookinfo.toString());			
			}
			break;
		//修改菜谱封面
		case 3:
			if(TextUtils.isEmpty(SharedPreferencesUtils.getString(this, "COOKIVPATH", ""))){
				iv_cook2.setBackgroundResource(R.drawable.wc_03);
				tv_cook2.setText(wrongdes);
			}else{
				displayPic.setImageBitmap(BitmapFactory.decodeFile(SharedPreferencesUtils.getString(this, "COOKIVPATH", "")));
				displayPic.setVisibility(View.VISIBLE);
				takephoto.setVisibility(View.GONE);
				iv_cook2.setBackgroundResource(R.drawable.check_duihao);
				tv_cook2.setText("已上传");			
			}
			break;
		//修改菜谱食材
		case 4:
			cook=(CookBook) data.getSerializableExtra("UPDATEFOOD");
			if(cook.foodlist.size()==0){
				iv_cook3.setBackgroundResource(R.drawable.wc_03);
				tv_cook3.setText(wrongdes);
			}else{
				iv_cook3.setBackgroundResource(R.drawable.check_duihao);
				tv_cook3.setText(cook.foodlist.toString());			
			}
			break;
		//修改菜谱厨具
		case 5:
			cook=(CookBook) data.getSerializableExtra("UPDATEUSE");
			if(cook.kitchen.size()==0){
				iv_cook4.setBackgroundResource(R.drawable.wc_03);
				tv_cook4.setText(wrongdes);
			}else{
				iv_cook4.setBackgroundResource(R.drawable.check_duihao);
				tv_cook4.setText(cook.kitchen.toString());			
			}
			break;
		//修改菜谱步骤
		case 6:
			cook=(CookBook) data.getSerializableExtra("UPDATESTEP");
			if(cook.steplist.size()==0){
				iv_cook5.setBackgroundResource(R.drawable.wc_03);
				tv_cook5.setText(wrongdes);
			}else{
				iv_cook5.setBackgroundResource(R.drawable.check_duihao);
				tv_cook5.setText("共"+cook.steplist.size()+"步");			
			}
			break;
		}
		super.onActivityResult(requestCode, resultCode, data);

	}

}

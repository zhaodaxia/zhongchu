package com.cai.vegetables.activity.myself;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;

import com.cai.vegetables.ConstantValue;
import com.cai.vegetables.R;
import com.cai.vegetables.activity.BaseActivity;
import com.cai.vegetables.utils.MyBitmapUtils;
import com.cai.vegetables.utils.SharedPreferencesUtils;
import com.cai.vegetables.view.crop.ClipActivity;
import com.cai.vegetables.widget.MySelfSheetDialog;
import com.cai.vegetables.widget.MySelfSheetDialog.OnSheetItemClickListener;
import com.cai.vegetables.widget.MySelfSheetDialog.SheetItemColor;
import com.cai.vegetables.widget.SheetDialogChooseSex;
import com.cai.vegetables.widget.SheetDialogChooseSex.SexClickListener;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;

import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.provider.MediaStore.MediaColumns;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

/** 
* 个人资料
* @author dongsy  
* @version 创建时间：2015年10月22日 下午8:15:18 
*/
public class MyDataActivity extends BaseActivity {

	@ViewInject(R.id.tv_title)
	protected TextView tv_title;
	
	@ViewInject(R.id.user_header)
	private ImageView user_header;
	
	@ViewInject(R.id.tv_usersex)
	private TextView tv_usersex;
	
	@ViewInject(R.id.tv_username)
	private TextView tv_username;
	public static final int PHOTOZOOM = 0;
	public static final int PHOTOTAKE = 1;
	public static final int IMAGE_COMPLETE = 2; // 结果
	public static final int CROPREQCODE = 3; // 截取
	private String photoSavePath;
	private String photoSaveName;
	private String path;// 图片全路径

	String appHome = Environment.getExternalStorageDirectory().getAbsolutePath() + "/park_tx";
	
	@Override
	public void setLayout() {
		// TODO Auto-generated method stub
		setContentView(R.layout.activity_mydata);
	}

	@Override
	public void init(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		tv_title.setText("个人资料");
		if(!TextUtils.isEmpty(SharedPreferencesUtils.getString(this, "HRAFERPATH", ""))){
			Bitmap bitmap=MyBitmapUtils.getimage(MyDataActivity.this,
					SharedPreferencesUtils.getString(this, "HRAFERPATH", ""));
			user_header.setImageBitmap(bitmap);
		}
		File file = new File(Environment.getExternalStorageDirectory(), "ClipHeadPhoto/cache");

		if (!file.exists())
			file.mkdirs();
		photoSavePath = Environment.getExternalStorageDirectory() + "/ClipHeadPhoto/cache/";

	}
	
	//点击事件
	@OnClick({ R.id.user_icon, R.id.rl_user_name, R.id.user_sex,R.id.user_adress,R.id.login_destory })
	public void toClick(View v) {
		switch (v.getId()) {
		// 我的头像
		case R.id.user_icon:
			new MySelfSheetDialog(this).builder().addSheetItem("拍照",  SheetItemColor.Black, new OnSheetItemClickListener() {
				
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
			}).addSheetItem("从相册选取", SheetItemColor.Black, new OnSheetItemClickListener() {
				
				@Override
				public void onClick(int which) {
					Intent openAlbumIntent = new Intent(Intent.ACTION_GET_CONTENT);
					openAlbumIntent.setType("image/*");
					startActivityForResult(openAlbumIntent, PHOTOZOOM);
				}
			}).show();
			break;
		// 昵称
		case R.id.rl_user_name:
			Intent iname=new Intent(this,EditNameActivity.class);
			startActivity(iname);
			break;
		// 性别
		case R.id.user_sex:
			SheetDialogChooseSex sexdialog=new SheetDialogChooseSex(this).builder();
			sexdialog.setOnSheetItemClickListener(new SexClickListener() {
				
				@Override
				public void onClick(int which) {
					// TODO Auto-generated method stub
					switch (which) {
					//男
					case 0:
						tv_usersex.setText("男");	
						break;
					//女
					case 1:
						tv_usersex.setText("女");
						break;
					}
				}
			});
			sexdialog.show();
			break;
		//我的收获地址
		case R.id.user_adress:
			
			break;
		//退出登录
		case R.id.login_destory:
			MySelfSheetDialog destory_dialog=new MySelfSheetDialog(this);
			destory_dialog.builder()
		    .setCancelable(false)
            .setCanceledOnTouchOutside(false);
			destory_dialog.addSheetItem("您确认要退出当前账号吗？", SheetItemColor.Gray, new OnSheetItemClickListener() {
				
				@Override
				public void onClick(int which) {
					// TODO Auto-generated method stub
					
				}
			});
			destory_dialog.addSheetItem("确认退出", SheetItemColor.Black, new OnSheetItemClickListener() {
				
				@Override
				public void onClick(int which) {
					// TODO Auto-generated method stub
					SharedPreferencesUtils.saveBoolean(MyDataActivity.this, ConstantValue.TOKEN, false);
					finish();
				}
			});
			destory_dialog.show();
			break;
		}
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
			Intent intent3 = new Intent(MyDataActivity.this, ClipActivity.class);
			intent3.putExtra("path", appHome + "/tx.png");
			startActivityForResult(intent3, IMAGE_COMPLETE);
			break;
		case PHOTOTAKE:// 拍照
			path = photoSavePath + photoSaveName;
			uri = Uri.fromFile(new File(path));
			Intent intent2 = new Intent(MyDataActivity.this, ClipActivity.class);
			intent2.putExtra("path", path);
			startActivityForResult(intent2, IMAGE_COMPLETE);
			break;
        //截取返回的圆形图片
		case IMAGE_COMPLETE:// 完成
			String temppath = data.getStringExtra("path");
			Bitmap bitmap=MyBitmapUtils.getimage(MyDataActivity.this,temppath);
			user_header.setImageBitmap(bitmap);
			SharedPreferencesUtils.saveString(this, "HRAFERPATH", temppath);
			break;
			
		}
		super.onActivityResult(requestCode, resultCode, data);
		
	}
	
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		tv_username.setText(SharedPreferencesUtils.getString(this, "USERNAME", "拎着背包去流浪"));
	}

}

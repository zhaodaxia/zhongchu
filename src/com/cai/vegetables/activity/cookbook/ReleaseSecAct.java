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

import android.app.Activity;
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
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

/**
 * 发布菜谱第二步
 * 
 * @author yang
 *
 */
public class ReleaseSecAct extends CookBase {
	@ViewInject(R.id.releasesec_rl)
	private RelativeLayout releasesec_rl;// 拍照
	@ViewInject(R.id.releasesec_iv)
	private ImageView releasesec_iv;// 拍照后的图
	@ViewInject(R.id.releasesec_btn)
	private Button releasesec_btn;// 下一步
	@ViewInject(R.id.releasesec_tv)
	private TextView releasesec_tv;// 文字信息

	public static final int PHOTOZOOM = 0;
	public static final int PHOTOTAKE = 1;
	public static final int IMAGE_COMPLETE = 2; // 结果
	public static final int CROPREQCODE = 3; // 截取
	private String photoSavePath;
	private String photoSaveName;
	private String path;// 图片全路径
	String appHome = Environment.getExternalStorageDirectory().getAbsolutePath() + "/park_tx";
	private Bitmap getimage;

	private boolean ISUP;
	@Override
	public void setCookLayout() {
		setContentView(R.layout.releasesec);
	}

	@Override
	public void initCook(Bundle savedInstanceState) {
		setTopTitle("上传封面图");
		cook=(CookBook) getIntent().getSerializableExtra(COOKINFO);
		if(!TextUtils.isEmpty(SharedPreferencesUtils.getString(this, "COOKIVPATH", ""))){
			releasesec_iv.setImageBitmap(BitmapFactory.decodeFile(SharedPreferencesUtils.getString(this, "COOKIVPATH", "")));
			releasesec_tv.setVisibility(View.GONE);
		}
		ISUP=getIntent().getBooleanExtra(ISUPDATE, false);
		if(ISUP){
			releasesec_btn.setText("保存");
		}
		setRightBtn2("取消", new OnClickListener() {
			@Override
			public void onClick(View v) {

			}
		});

		File file = new File(Environment.getExternalStorageDirectory(), "ClipHeadPhoto/cache");

		if (!file.exists())
			file.mkdirs();
		photoSavePath = Environment.getExternalStorageDirectory() + "/ClipHeadPhoto/cache/";
	}

	@OnClick({ R.id.releasesec_rl, R.id.releasesec_btn })
	public void todo(View v) {
		switch (v.getId()) {
		case R.id.releasesec_rl:
			showdialog();
			break;
		case R.id.releasesec_btn:
			if(!TextUtils.isEmpty(path)){
			SharedPreferencesUtils.saveString(this, "COOKIVPATH", path);
			}
			if(ISUP){
				gonext=new Intent(this,ReleaseSixAct.class);
				setResult(Activity.RESULT_OK, gonext);
				finish();
			}else{
			gonext=new Intent(this,ReleaseThrAct.class);
			Bundle mBundle = new Bundle(); 
			mBundle.putSerializable(COOKINFO,cook);  
			gonext.putExtras(mBundle);  
			startActivity(gonext);
			}
			break;

		default:
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
				openAlbumIntent.setDataAndType( MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                        "image/*");
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
			getimage = MyBitmapUtils.getimage(ReleaseSecAct.this, appHome + "/tx.png");
			releasesec_iv.setImageBitmap(getimage);
			releasesec_tv.setVisibility(View.GONE);
			break;
		case PHOTOTAKE:// 拍照
			path = photoSavePath + photoSaveName;
			uri = Uri.fromFile(new File(path));
			getimage = BitmapFactory.decodeFile(path);
			releasesec_iv.setImageBitmap(getimage);
			releasesec_tv.setVisibility(View.GONE);
			break;

		}
		super.onActivityResult(requestCode, resultCode, data);

	}

}

package com.cai.vegetables.utils.scan;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.Vector;

import com.cai.vegetables.R;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.Result;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.hardware.Camera;
import android.hardware.Camera.Parameters;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Vibrator;
import android.provider.MediaStore;
import android.view.SurfaceHolder;
import android.view.View;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

/**
 * 扫描条形码界面
 * 
 * @author lion king
 * 
 */
@SuppressLint("InlinedApi")
public class CaptureActivity extends Activity implements Callback {

	private CaptureActivityHandler handler;
	private ViewfinderView viewfinderView;
	private boolean hasSurface;
	private Vector<BarcodeFormat> decodeFormats;
	private String characterSet;
	private InactivityTimer inactivityTimer;
	private MediaPlayer mediaPlayer;
	private boolean playBeep;
	private static final float BEEP_VOLUME = 0.10f;
	private boolean vibrate;

	private RelativeLayout flashBtn;
	private RelativeLayout rlBack;
	private TextView tvFlash;
	private boolean isOpen = true;
	private Camera camera;
	private Parameters parameter;
	private TextView tvPhoto;
	private String photo_path;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.act_scan);
		// 初始化 CameraManager
		CameraManager.init(getApplication());

		viewfinderView = (ViewfinderView) findViewById(R.id.viewfinder_view);

		hasSurface = false;
		inactivityTimer = new InactivityTimer(this);

		flashBtn = (RelativeLayout) findViewById(R.id.rlFlash);
		rlBack = (RelativeLayout) findViewById(R.id.rlBack);
		tvFlash = (TextView) findViewById(R.id.tv_flash);
		tvPhoto = (TextView) findViewById(R.id.tvPhoto);
		flashBtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				camera = CameraManager.get().getCamera();
				if (camera == null) {
					Toast.makeText(CaptureActivity.this, "未获得相机权限，请打开相机权限", Toast.LENGTH_SHORT).show();
					return;
				}
				parameter = camera.getParameters();
				// TODO 自动生成的方法存根
				if (isOpen == true) {
					parameter.setFlashMode(Parameters.FLASH_MODE_TORCH);
					camera.setParameters(parameter);
					tvFlash.setText("关闭闪光灯");
					flashBtn.setBackgroundResource(R.drawable.flash_button_back_open);
					isOpen = false;
				} else {
					parameter.setFlashMode(Parameters.FLASH_MODE_OFF);
					camera.setParameters(parameter);
					tvFlash.setText("打开闪光灯");
					flashBtn.setBackgroundResource(R.drawable.flash_button_back_close);
					isOpen = true;
				}
			}
		});

		tvPhoto.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent();
				// if(Build.VERSION.SDK_INT < 19){
				intent.setAction(Intent.ACTION_GET_CONTENT);
				// }else{
				// intent.setAction(Intent.ACTION_OPEN_DOCUMENT);
				// }
				intent.setType("image/*");
				Intent wrapperIntent = Intent.createChooser(intent, "选择二维码图片");
				startActivityForResult(wrapperIntent, 1);
			}
		});

		rlBack.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				finish();
			}
		});

		// oriventationListener=new OrientationEventListener(this) {
		// @Override
		// public void onOrientationChanged(int oriventation) {
		// if(oriventation>325||oriventation<=45){
		// degrees=90;
		// }else if(oriventation>45&&oriventation<=135){
		// degrees=180;
		// }else if(oriventation>135&&oriventation<225){
		// degrees=270;
		// }else{
		// degrees=0;
		// }
		//
		// }
		// };
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (requestCode == 1) {
			if (data == null) {
				return;
			}
			String[] proj = { MediaStore.Images.Media.DATA };
			// 获取选中图片的路径
			Cursor cursor = getContentResolver().query(data.getData(), proj, null, null, null);
			if (cursor.moveToFirst()) {

				int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
				photo_path = cursor.getString(column_index);
				if (photo_path == null) {
					photo_path = ScanImageUtil.getPath(getApplicationContext(), data.getData());
				}
			}

			cursor.close();
			new Thread(new Runnable() {
				@Override
				public void run() {
					Result result = ScanImageUtil.scaning(photo_path);
					if (result == null) {
						Looper.prepare();
						Toast.makeText(getApplicationContext(), "图片格式有误", Toast.LENGTH_SHORT).show();
						Looper.loop();
					} else {
						// 数据返回
						String recode = recode(result.toString());
						Intent intent = new Intent();
						intent.putExtra("code", recode);
						setResult(0, intent);
						finish();
					}
				}
			}).start();

		}
	}

	/**
	 * 中文乱码
	 * 
	 * 暂时解决大部分的中文乱码 但是还有部分的乱码无法解决 .
	 * 
	 * @return
	 */
	private String recode(String str) {
		String formart = "";
		try {
			boolean ISO = Charset.forName("ISO-8859-1").newEncoder().canEncode(str);
			if (ISO) {
				formart = new String(str.getBytes("ISO-8859-1"), "GB2312");
			} else {
				formart = str;
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return formart;
	}

	@SuppressWarnings("deprecation")
	@Override
	protected void onResume() {
		super.onResume();
		SurfaceView surfaceView = (SurfaceView) findViewById(R.id.preview_view);
		SurfaceHolder surfaceHolder = surfaceView.getHolder();
		if (hasSurface) {
			initCamera(surfaceHolder);
		} else {
			surfaceHolder.addCallback(this);
			surfaceHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
		}
		decodeFormats = null;
		characterSet = null;

		playBeep = true;
		AudioManager audioService = (AudioManager) getSystemService(AUDIO_SERVICE);
		if (audioService.getRingerMode() != AudioManager.RINGER_MODE_NORMAL) {
			playBeep = false;
		}
		initBeepSound();
		vibrate = true;
	}

	@Override
	protected void onPause() {
		super.onPause();
		if (handler != null) {
			handler.quitSynchronously();
			handler = null;
		}
		CameraManager.get().closeDriver();
	}

	@Override
	protected void onDestroy() {
		inactivityTimer.shutdown();
		super.onDestroy();
	}

	private void initCamera(SurfaceHolder surfaceHolder) {
		try {
			CameraManager.get().openDriver(surfaceHolder);
		} catch (IOException ioe) {
			return;
		} catch (RuntimeException e) {
			return;
		}
		if (handler == null) {
			handler = new CaptureActivityHandler(this, decodeFormats, characterSet);
		}
	}

	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

	}

	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		if (!hasSurface) {
			hasSurface = true;
			initCamera(holder);
		}

	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		hasSurface = false;
		CameraManager.get().stopPreview();
	}

	public ViewfinderView getViewfinderView() {
		return viewfinderView;
	}

	public Handler getHandler() {
		return handler;
	}

	public void drawViewfinder() {
		viewfinderView.drawViewfinder();

	}

	/**
	 * 
	 txtResult.setText(obj.getBarcodeFormat().toString() + ":" + obj.getText());
	 * 
	 * obj.getText()这个就是二维码扫描出来之后的URL地址。
	 */
	public void handleDecode(Result obj, Bitmap barcode) {
		inactivityTimer.onActivity();
		viewfinderView.drawResultBitmap(barcode);
		playBeepSoundAndVibrate();
		Intent intent = new Intent();
		// intent.putExtra("code", obj.getBarcodeFormat().toString() + ":"
		// + obj.getText());
		intent.putExtra("code", obj.getText());
		setResult(0, intent);
		finish();

	}

	private void initBeepSound() {
		if (playBeep && mediaPlayer == null) {
			// The volume on STREAM_SYSTEM is not adjustable, and users found it
			// too loud,
			// so we now play on the music stream.
			setVolumeControlStream(AudioManager.STREAM_MUSIC);
			mediaPlayer = new MediaPlayer();
			mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
			mediaPlayer.setOnCompletionListener(beepListener);

			AssetFileDescriptor file = getResources().openRawResourceFd(R.raw.beep);
			try {
				mediaPlayer.setDataSource(file.getFileDescriptor(), file.getStartOffset(), file.getLength());
				file.close();
				mediaPlayer.setVolume(BEEP_VOLUME, BEEP_VOLUME);
				mediaPlayer.prepare();
			} catch (IOException e) {
				mediaPlayer = null;
			}
		}
	}

	private static final long VIBRATE_DURATION = 200L;

	private void playBeepSoundAndVibrate() {
		if (playBeep && mediaPlayer != null) {
			mediaPlayer.start();
		}
		if (vibrate) {
			Vibrator vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
			vibrator.vibrate(VIBRATE_DURATION);
		}
	}

	/**
	 * When the beep has finished playing, rewind to queue up another one.
	 */
	private final OnCompletionListener beepListener = new OnCompletionListener() {
		public void onCompletion(MediaPlayer mediaPlayer) {
			mediaPlayer.seekTo(0);
		}
	};

}
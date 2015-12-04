package com.cai.vegetables.activity.light;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.location.LocationManagerProxy;
import com.amap.api.location.LocationProviderProxy;
import com.amap.api.maps2d.AMap;
import com.amap.api.maps2d.AMap.OnMapClickListener;
import com.amap.api.maps2d.AMap.OnMarkerClickListener;
import com.amap.api.maps2d.CameraUpdateFactory;
import com.amap.api.maps2d.LocationSource;
import com.amap.api.maps2d.MapView;
import com.amap.api.maps2d.UiSettings;
import com.amap.api.maps2d.model.BitmapDescriptorFactory;
import com.amap.api.maps2d.model.LatLng;
import com.amap.api.maps2d.model.Marker;
import com.amap.api.maps2d.model.MarkerOptions;
import com.cai.vegetables.R;
import com.cai.vegetables.activity.BaseActivity;
import com.cai.vegetables.activity.service.ServiceAct;
import com.cai.vegetables.utils.DataCleanManager;
import com.cai.vegetables.utils.VegetableUtils;
import com.cai.vegetables.widget.MyMsgDialog;
import com.cai.vegetables.widget.ToastCommom;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.nostra13.universalimageloader.core.ImageLoader;

import android.app.Dialog;
import android.content.Context;
import android.graphics.BitmapFactory;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.location.Location;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

/**
 * 下单界面
 * 
 * @author yang
 *
 */
public class PdAct extends BaseActivity implements OnMarkerClickListener, OnMapClickListener, LocationSource,
		AMapLocationListener, SensorEventListener {
	private Dialog dialog;

	@ViewInject(R.id.map)
	private MapView mapView;

	private AMap aMap;
	private UiSettings mUiSettings;
	private Marker mGPSMarker;
	private OnLocationChangedListener mListener;
	private LocationManagerProxy mAMapLocationManager;
	private Double latitude;
	private Double longitude;
	private LatLng latLng;
	private SensorManager mSensorManager;
	private Sensor mSensor;
	private long lastTime = 0;
	private float mAngle;
	private final int TIME_SENSOR = 100;

	@Override
	public void setLayout() {
		setContentView(R.layout.pdact);
	}

	@SuppressWarnings("deprecation")
	@Override
	public void init(Bundle savedInstanceState) {
		setTopTitle("派单中");
		setRightBtn2("取消订单", new OnClickListener() {
			@Override
			public void onClick(View v) {
				showdialog();
			}
		});

		if (aMap == null) {
			aMap = mapView.getMap();
			setUpMap();
			mUiSettings = aMap.getUiSettings();
			setUiSettings();
		}
		mapView.onCreate(savedInstanceState);// 必须要写
		// 初始化传感器
		mSensorManager = (SensorManager) this.getSystemService(Context.SENSOR_SERVICE);
		mSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION);
	}

	/**
	 * 设置一些amap的属性
	 */
	private void setUpMap() {
		aMap.setOnMarkerClickListener(this);// 设置点击marker事件监听器
		mGPSMarker = aMap.addMarker(new MarkerOptions()
				.icon(BitmapDescriptorFactory
						.fromBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.location_marker)))
				.anchor((float) 0.5, (float) 0.5));
		mGPSMarker.setObject(null);
		aMap.setOnMapClickListener(this);
		aMap.setLocationSource(PdAct.this);// 设置定位监听
		aMap.setMyLocationEnabled(true);// 设置为true表示显示定位层并可触发定位，false表示隐藏定位层并不可触发定位，默认是false
		aMap.moveCamera(CameraUpdateFactory.zoomTo(11)); // 缩放级别
	}

	private void setUiSettings() {
		// mUiSettings.setLogoPosition(AMapOptions.LOGO_POSITION_BOTTOM_LEFT);//设置地图logo显示在左下方
		mUiSettings.setScaleControlsEnabled(true);// 设置比例尺显示----默认是false
		// mUiSettings.setZoomControlsEnabled(true);// 设置放大缩小控制器显示 ----默认是true
		// mUiSettings.setMyLocationButtonEnabled(true);//设置默认定位按钮是否显示----默认是true
		// mUiSettings.setCompassEnabled(true);// 设置地图默认的指南针是否显示 ----默认是false
		// mUiSettings.setScrollGesturesEnabled(true);// 设置地图是否可以手势滑动--- 默认是true
		// mUiSettings.setZoomGesturesEnabled(true);// 设置地图是否可以手势缩放大小----默认是true

	}

	// 展示dialog
	private void showdialog() {
		MyMsgDialog shopDialog = new MyMsgDialog(PdAct.this);
		shopDialog.builder();
		shopDialog.setTitle("确定要取消此次用车吗？");
			shopDialog.setContent("取消后不可恢复，需重新下单");
		shopDialog.setNegativeButton("取消", null);
		shopDialog.setPositiveButton("确定", new OnClickListener() {
			@Override
			public void onClick(View v) {
				ToastCommom.createToastConfig().ToastShow(getApplicationContext(), "已取消用车");
			}
		});
		shopDialog.show();
	}

	/**
	 * 定位成功后回调函数
	 */
	public void onLocationChanged(AMapLocation amapLocation) {
		if (mListener != null && amapLocation != null) {
			if (amapLocation.getAMapException().getErrorCode() == 0) {
				mListener.onLocationChanged(amapLocation);// 显示系统小蓝点
				latitude = amapLocation.getLatitude();
				longitude = amapLocation.getLongitude();
				latLng = new LatLng(latitude, longitude);
				mGPSMarker.setPosition(latLng);
			}
		}
	}

	/**
	 * 激活定位
	 */
	@SuppressWarnings("deprecation")
	@Override
	public void activate(OnLocationChangedListener listener) {
		mListener = listener;
		if (mAMapLocationManager == null) {
			mAMapLocationManager = LocationManagerProxy.getInstance(this);
			mAMapLocationManager.setGpsEnable(true);
			/*
			 * mAMapLocationManager.setGpsEnable(false);
			 * 1.0.2版本新增方法，设置true表示混合定位中包含gps定位，false表示纯网络定位，默认是true Location
			 * API定位采用GPS和网络混合定位方式
			 * ，第一个参数是定位provider，第二个参数时间最短是2000毫秒，第三个参数距离间隔单位是米，第四个参数是定位监听者
			 */
			mAMapLocationManager.requestLocationUpdates(LocationProviderProxy.AMapNetwork, 2000, 10, this);
		}
	}

	/**
	 * 停止定位
	 */
	@SuppressWarnings("deprecation")
	@Override
	public void deactivate() {
		mListener = null;
		if (mAMapLocationManager != null) {
			mAMapLocationManager.removeUpdates(this);
			mAMapLocationManager.destory();
		}
		mAMapLocationManager = null;
		unRegisterSensorListener();
	}

	public void registerSensorListener() {
		mSensorManager.registerListener(this, mSensor, SensorManager.SENSOR_DELAY_NORMAL);
	}

	public void unRegisterSensorListener() {
		mSensorManager.unregisterListener(this, mSensor);
	}

	/**
	 * 在地图上点击时处理的方法写在这里
	 */
	@Override
	public void onMapClick(LatLng arg0) {

	}

	/**
	 * 在地图上点击标注时处理的方法写在这里
	 */
	@Override
	public boolean onMarkerClick(Marker arg0) {
		return false;
	}

	/**
	 * 此方法已废除
	 */
	@Override
	public void onLocationChanged(Location location) {

	}

	/**
	 * 此方法已废除
	 */
	@Override
	public void onProviderDisabled(String provider) {

	}

	/**
	 * 此方法已废除
	 */
	@Override
	public void onProviderEnabled(String provider) {

	}

	/**
	 * 此方法已废除
	 */
	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {

	}

	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy) {

	}

	@SuppressWarnings("deprecation")
	@Override
	public void onSensorChanged(SensorEvent event) {
		if (System.currentTimeMillis() - lastTime < TIME_SENSOR) {
			return;
		}
		switch (event.sensor.getType()) {
		case Sensor.TYPE_ORIENTATION: {
			float x = event.values[0];
			System.out.println(x);
			x += VegetableUtils.getScreenRotationOnPhone(this);
			x %= 360.0F;
			if (x > 180.0F)
				x -= 360.0F;
			else if (x < -180.0F)
				x += 360.0F;
			if (Math.abs(mAngle - 90 + x) < 3.0f) {
				break;
			}
			mAngle = x;
			if (mGPSMarker != null) {
				mGPSMarker.setRotateAngle(-mAngle);
				aMap.invalidate();
			}
			lastTime = System.currentTimeMillis();
		}
		}
	}

	/**
	 * 方法必须重写
	 */
	@Override
	protected void onResume() {
		super.onResume();
		mapView.onResume();
		registerSensorListener();
	}

	/**
	 * 方法必须重写
	 */
	@Override
	protected void onPause() {
		super.onPause();
		mapView.onPause();
		deactivate();
	}

	/**
	 * 方法必须重写
	 */
	@Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		mapView.onSaveInstanceState(outState);
	}

	/**
	 * 方法必须重写
	 */
	@Override
	protected void onDestroy() {
		super.onDestroy();
		mapView.onDestroy();
	}

}

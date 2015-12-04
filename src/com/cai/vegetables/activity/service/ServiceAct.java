package com.cai.vegetables.activity.service;

import com.cai.vegetables.R;
import com.cai.vegetables.activity.BaseActivity;
import com.cai.vegetables.utils.DataCleanManager;
import com.cai.vegetables.widget.MyMsgDialog;
import com.cai.vegetables.widget.MySelfSheetDialog;
import com.cai.vegetables.widget.MySelfSheetDialog.OnSheetItemClickListener;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.nostra13.universalimageloader.core.ImageLoader;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

/**
 * 服务中心界面
 * 
 * @author yang
 *
 */
public class ServiceAct extends BaseActivity {
	@ViewInject(R.id.service_btn)
	private Button service_btn;// 拨打紧急电话按钮
	@ViewInject(R.id.service_tv1)
	private TextView service_tv1;// 常见问题
	@ViewInject(R.id.service_tv2)
	private TextView service_tv2;// 退货细则
	@ViewInject(R.id.service_tv3)
	private TextView service_tv3;// 反馈投诉
	@ViewInject(R.id.service_tv4)
	private TextView service_tv4;// 关于我们
	@ViewInject(R.id.service_tv5)
	private TextView service_tv5;// 联系客服
	@ViewInject(R.id.service_tv6)
	private TextView service_tv6;// 清空缓存
	@ViewInject(R.id.service_tv7)
	private TextView service_tv7;// 紧急电话号码
	private String cache;

	@Override
	public void setLayout() {
		setContentView(R.layout.serviceact);
	}

	@Override
	public void init(Bundle savedInstanceState) {
		setTopTitle("服务中心");
	}

	@OnClick({ R.id.service_tv1, R.id.service_tv2, R.id.service_tv3, R.id.service_tv4, R.id.service_tv5,
			R.id.service_tv6, R.id.service_btn })
	public void todo(View v) {
		switch (v.getId()) {
		case R.id.service_tv1:
			gotoActivity(ProblemAct.class, false);
			break;
		case R.id.service_tv2:
			gotoActivity(GoodsBackAct.class, false);
			break;
		case R.id.service_tv3:
			gotoActivity(FeedBackAct.class, false);
			break;
		case R.id.service_tv4:
			gotoActivity(AboutUsAct.class, false);
			break;
		case R.id.service_tv5:
			gotoActivity(LinkServiceAct.class, false);
			break;
		case R.id.service_tv6:
			clean();
			break;
		case R.id.service_btn:
			call();
			break;

		default:
			break;
		}
	}

	private void clean() {
		try {
			cache = DataCleanManager.getTotalCacheSize(ServiceAct.this);
			MyMsgDialog shopDialog = new MyMsgDialog(ServiceAct.this);
			shopDialog.builder();
			shopDialog.setTitle("您确定要清空缓存吗？");
			if(TextUtils.isEmpty(cache))
				shopDialog.setContent("没有缓存");
			else
				shopDialog.setContent("缓存大小"+cache);
			shopDialog.setNegativeButton("取消", null);
			shopDialog.setPositiveButton("确定", new OnClickListener() {
				@Override
				public void onClick(View v) {
					ImageLoader.getInstance().clearDiskCache();
					ImageLoader.getInstance().clearMemoryCache();
					DataCleanManager.clearAllCache(ServiceAct.this);
				}
			});
			shopDialog.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void call() {
		final String phone = service_tv7.getText().toString();
		MySelfSheetDialog dialog = new MySelfSheetDialog(ServiceAct.this);
		dialog.builder();
		dialog.addSheetItem("拨打", null, new OnSheetItemClickListener() {
			@Override
			public void onClick(int which) {
				intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + phone));
				startActivity(intent);
			}
		});
		dialog.show();
	}

}

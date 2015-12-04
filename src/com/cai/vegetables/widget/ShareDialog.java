package com.cai.vegetables.widget;

import com.cai.vegetables.R;

import android.app.Dialog;
import android.content.Context;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

/** 
* 分享底部对话框
* @author dongsy  
* @version 创建时间：2015年10月23日 上午10:46:00 
*/
public class ShareDialog extends Dialog implements android.view.View.OnClickListener{

	private Context context;
	private ShareDialogOnclickListener listener;
	private TextView share_dialogtitle;
	public ShareDialog(Context context, int theme) {
		super(context, theme);
		this.context=context;
		initview();
	}

	private void initview() {
		// 获取Dialog布局
		View view = LayoutInflater.from(context).inflate(
					R.layout.share_dialog, null);
		WindowManager windowManager = (WindowManager) context
				.getSystemService(Context.WINDOW_SERVICE);
		
		TextView share_moments=(TextView) view.findViewById(R.id.share_moments);
		
		TextView share_qq=(TextView) view.findViewById(R.id.share_qq);
		
		TextView share_sina=(TextView) view.findViewById(R.id.share_sina);
		
		TextView share_wechat=(TextView) view.findViewById(R.id.share_wechat);
		
		TextView share_tencent=(TextView) view.findViewById(R.id.share_tencent);
		
		TextView share_zone=(TextView) view.findViewById(R.id.share_zone);
		
		share_dialogtitle=(TextView) view.findViewById(R.id.share_dialogtitle);
		
		ImageView dialog_dismiss=(ImageView) view.findViewById(R.id.dialog_dismiss);
		
		share_moments.setOnClickListener(this);
		share_qq.setOnClickListener(this);
		share_sina.setOnClickListener(this);
		share_wechat.setOnClickListener(this);
		share_tencent.setOnClickListener(this);
		share_zone.setOnClickListener(this);
		dialog_dismiss.setOnClickListener(this);
		// 设置Dialog最小宽度为屏幕宽度
		view.setMinimumWidth(windowManager.getDefaultDisplay().getWidth());
		setContentView(view);
		Window dialogWindow = getWindow();
		dialogWindow.setGravity(Gravity.LEFT | Gravity.BOTTOM);
		WindowManager.LayoutParams lp = dialogWindow.getAttributes();
		lp.x = 0;
		lp.y = 0;
		dialogWindow.setAttributes(lp);
	}
	
	public void setShareOnclickListener(ShareDialogOnclickListener listener){
		this.listener=listener;
	}
	
	
	public void setTop(String text){
		if(!TextUtils.isEmpty(text)){
			share_dialogtitle.setText(text);
		}
	}
	
	public interface ShareDialogOnclickListener{
		//点击朋友圈
		public abstract void momentsOnclick();
		//点击QQ
		public abstract void QQOnclick();
		//点击新浪微博
		public abstract void SinaOnclick();
		//点击微信
		public abstract void WechatOnclick();
		//点击腾讯微博
		public abstract void TencentOnclick();
		//点击QQ空间
		public abstract void QZoneOnclick();
	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		switch (arg0.getId()) {
		case R.id.share_moments:
			listener.momentsOnclick();
			break;

		case R.id.share_qq:
			listener.QQOnclick();
			break;
		
		case R.id.share_tencent:
			listener.TencentOnclick();
			break;
			
		case R.id.share_sina:
			listener.SinaOnclick();
			break;
		
		case R.id.share_wechat:
			listener.WechatOnclick();
			break;
			
		case R.id.share_zone:
			listener.QZoneOnclick();
			break;
		case R.id.dialog_dismiss:
			dismiss();
			break;
		}
	}

}

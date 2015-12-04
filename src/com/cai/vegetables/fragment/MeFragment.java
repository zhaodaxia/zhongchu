package com.cai.vegetables.fragment;

import com.cai.vegetables.ConstantValue;
import com.cai.vegetables.GotoLighttEvent;
import com.cai.vegetables.R;
import com.cai.vegetables.activity.myself.AdressManagerActivity;
import com.cai.vegetables.activity.myself.CollectActivity;
import com.cai.vegetables.activity.myself.IntegralActivity;
import com.cai.vegetables.activity.myself.JoinActivity;
import com.cai.vegetables.activity.myself.Message_Activity;
import com.cai.vegetables.activity.myself.MyAccountAct;
import com.cai.vegetables.activity.myself.MyDataActivity;
import com.cai.vegetables.activity.myself.MyOrderActivity;
import com.cai.vegetables.activity.myself.ShareFriendActivity;
import com.cai.vegetables.activity.service.ServiceAct;
import com.cai.vegetables.activity.user.LoginActivity;
import com.cai.vegetables.utils.MyBitmapUtils;
import com.cai.vegetables.utils.SharedPreferencesUtils;
import com.cai.vegetables.widget.CircleImageView;
import com.cai.vegetables.widget.ShareDialog;
import com.cai.vegetables.widget.ShareDialog.ShareDialogOnclickListener;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.tandong.sa.eventbus.EventBus;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
/**
 * 我的
 * @author wangbin
 *
 */
public class MeFragment extends BaseFragment{

	//未登陆顶部区域
	@ViewInject(R.id.unlogin_header)
	private LinearLayout unlogin_header;
	
	//登陆后顶部区域
	@ViewInject(R.id.login_header)
	private RelativeLayout login_header;
	
	//登陆后用户头像
	@ViewInject(R.id.login_icon)
	private CircleImageView login_icon;
	
	//登陆后消息图片
	@ViewInject(R.id.login_messageicon)
	private ImageView login_messageicon;
	
	//登陆后收货地址图片
	@ViewInject(R.id.wd_13)
	private ImageView login_adressicon;
	
	//登陆后才显示的分享图标
	@ViewInject(R.id.fg_me_share)
	private ImageView fg_me_share;
	
	//登陆后消息提示图片
	@ViewInject(R.id.login_messagepoint)
	private ImageView login_messagepoint;
	
	@ViewInject(R.id.user_name)
	private TextView user_name;
	
	@ViewInject(R.id.rl_service)
	private RelativeLayout rl_service;//服务中心

	private ShareDialog dialog;
	
	@Override
	public View initView(LayoutInflater inflater) {
		View view=inflater.inflate(com.cai.vegetables.R.layout.fragment_me,null);
		return view;
	}

	@Override
	public void initData(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
	}
	
	@Override
	public void onResume() {
		// TODO Auto-generated method stub
		checkislogin();
		user_name.setText(SharedPreferencesUtils.getString(context, "USERNAME", "拎着背包去流浪"));
		super.onResume();
	}
	
	private void checkislogin() {
		// TODO Auto-generated method stub
		//判断是否登陆
				if(SharedPreferencesUtils.getBoolean(context, ConstantValue.TOKEN, false))
				{
					unlogin_header.setVisibility(View.GONE);
					login_header.setVisibility(View.VISIBLE);
					login_icon.setVisibility(View.VISIBLE);
					login_messageicon.setVisibility(View.VISIBLE);
					login_adressicon.setVisibility(View.VISIBLE);
					login_messagepoint.setVisibility(View.VISIBLE);
					fg_me_share.setVisibility(View.VISIBLE);
					if(!TextUtils.isEmpty(SharedPreferencesUtils.getString(context, "HRAFERPATH", ""))){
						Bitmap bitmap=MyBitmapUtils.getimage(context,
								SharedPreferencesUtils.getString(context, "HRAFERPATH", ""));
						login_icon.setImageBitmap(bitmap);
					}
				}
				else
				{
					fg_me_share.setVisibility(View.GONE);
					unlogin_header.setVisibility(View.VISIBLE);
					login_header.setVisibility(View.GONE);
					login_icon.setVisibility(View.GONE);
					login_messageicon.setVisibility(View.GONE);
					login_adressicon.setVisibility(View.GONE);
					login_messagepoint.setVisibility(View.GONE);
				}
	}

	@OnClick({R.id.tvLogin,R.id.login_header,R.id.me_sharefriend,R.id.me_light,R.id.fg_me_share,
		R.id.me_join,R.id.me_count,R.id.rl_service,R.id.login_messageicon,R.id.me_order,
		R.id.me_order_noco,R.id.me_order_nopay,R.id.me_order_nore,R.id.me_order_node,R.id.wd_13,R.id.me_collect})
	public void onClick(View v) {
		Intent intent=null;
		switch(v.getId()){
		//登陆
		case R.id.tvLogin:
			intent=new Intent(getActivity(),LoginActivity.class);
			startActivity(intent);
			break;
		//个人资料
		case R.id.login_header:
			intent=new Intent(getActivity(),MyDataActivity.class);
			startActivity(intent);
			break;
		//邀请好友
		case R.id.me_sharefriend:
			share();
			dialog.setTop("邀请");
			break;
		case R.id.fg_me_share:
			share();
			break;
		//飞喵快递
		case R.id.me_light:
			intent=new Intent(getActivity(),MyOrderActivity.class);
			intent.putExtra("postion", 5);
			startActivity(intent);
			break;
		case R.id.me_join:
			Intent join=new Intent(context,JoinActivity.class);
			startActivity(join);
			break;
		case R.id.me_count:
			Intent count=new Intent(context,MyAccountAct.class);
			startActivity(count);
			break;
		case R.id.rl_service:
			intent=new Intent(getActivity(),ServiceAct.class);
			startActivity(intent);
			break;
		case R.id.login_messageicon:
			intent=new Intent(getActivity(),Message_Activity.class);
			startActivity(intent);
			break;
		//我的订单
		case R.id.me_order:
			intent=new Intent(getActivity(),MyOrderActivity.class);
			startActivity(intent);
			break;
		//待付款
		case R.id.me_order_nopay:
			intent=new Intent(getActivity(),MyOrderActivity.class);
			intent.putExtra("postion", 1);
			startActivity(intent);
			break;
		//待发货
		case R.id.me_order_node:
			intent=new Intent(getActivity(),MyOrderActivity.class);
			intent.putExtra("postion", 2);
			startActivity(intent);
			break;
		//待收货
		case R.id.me_order_nore:
			intent=new Intent(getActivity(),MyOrderActivity.class);
			intent.putExtra("postion", 3);
			startActivity(intent);
			break;
		//待评价
		case R.id.me_order_noco:
			intent=new Intent(getActivity(),MyOrderActivity.class);
			intent.putExtra("postion", 4);
			startActivity(intent);
			break;
		//收获地址
		case R.id.wd_13:
			intent=new Intent(getActivity(),AdressManagerActivity.class);
			intent.putExtra("postion", 4);
			startActivity(intent);
			break;
		//我的收藏
		case R.id.me_collect:
			intent=new Intent(getActivity(),CollectActivity.class);
			startActivity(intent);
			break;			
		}
	}

	private void share() {
		dialog=new ShareDialog(context, R.style.ActionSheetDialogStyle);
		dialog.show();
		dialog.setShareOnclickListener(new ShareDialogOnclickListener() {

			@Override
			public void momentsOnclick() {
				// TODO Auto-generated method stub
				GoShare();
			}
			
			@Override
			public void WechatOnclick() {
				// TODO Auto-generated method stub
				GoShare();
			}
			
			@Override
			public void TencentOnclick() {
				// TODO Auto-generated method stub
				GoShare();
			}
			
			@Override
			public void SinaOnclick() {
				// TODO Auto-generated method stub
				GoShare();
			}
			
			@Override
			public void QZoneOnclick() {
				// TODO Auto-generated method stub
				GoShare();
			}
			
			@Override
			public void QQOnclick() {
				// TODO Auto-generated method stub
				GoShare();
			}
		});
	}
	
	private void GoShare(){
		Intent share=new Intent(context,ShareFriendActivity.class);
		startActivity(share);
		dialog.dismiss();
	}
	
}

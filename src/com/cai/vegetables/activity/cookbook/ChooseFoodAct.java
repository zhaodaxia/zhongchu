package com.cai.vegetables.activity.cookbook;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.cai.vegetables.R;
import com.cai.vegetables.activity.BaseActivity;
import com.cai.vegetables.entity.CookBook;
import com.cai.vegetables.entity.Food;
import com.cai.vegetables.utils.SharedPreferencesUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.tandong.sa.json.Gson;
import com.tandong.sa.json.reflect.TypeToken;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.BaseAdapter;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

/**
 * 选择食材
 * 
 * @author yang
 *
 */
public class ChooseFoodAct extends CookBase {
	@ViewInject(R.id.choose_foodlv)
	private ListView flv;
	private CookBookAda adapter;

	private int width;
	private List<Food> fooddatalists;
	@Override
	public void setCookLayout() {
		setContentView(R.layout.choose_food);
	}

	@SuppressLint("NewApi")
	@Override
	public void initCook(Bundle savedInstanceState) {
		setTopTitle("选择所需食材");
		// 从Android 3.2（API Level 13）开始，在Activity里使用下面的方法来获取屏幕分辨率（单位是像素）
		Point size = new Point();
		Display display = getWindowManager().getDefaultDisplay();
		display.getSize(size);
		width = size.x;
		String json=SharedPreferencesUtils.getString(this, SPCOOKFOOD, "");
		fooddatalists=new Gson().fromJson(json,new TypeToken<List<Food>>() {
		}.getType());
		adapter = new CookBookAda();
		flv.setAdapter(adapter);
	}
	
	@OnClick({ R.id.bt_savefood})
	public void todo(View v) {
		switch (v.getId()) {
		case R.id.bt_savefood:
			//返回并保存本步骤所选的用具
			Intent reintent=getIntent();
			reintent.putExtra(COOKFOOD,(Serializable)fooddatalists);
			setResult(Activity.RESULT_OK, reintent);
			finish();
			break;
		}
	}

	private class CookBookAda extends BaseAdapter {
		private HorizontalScrollView resetView;
		private ViewHolder vh;
		private int x1, y1, x2, y2;

		@Override
		public int getCount() {
			return fooddatalists.size();
		}

		@Override
		public Object getItem(int position) {
			return position;
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(final int position, View convertView, ViewGroup parent) {
			ViewHolder holder;
			if (convertView != null && convertView instanceof HorizontalScrollView) {
				holder = (ViewHolder) convertView.getTag();
			} else {
				holder = new ViewHolder();
				convertView = View.inflate(ChooseFoodAct.this, R.layout.choosefood_item, null);
				convertView.setTag(holder);
				holder.hSView = (HorizontalScrollView) convertView.findViewById(R.id.hsv);
				holder.cookbook_item_ll = (LinearLayout) convertView.findViewById(R.id.cookbook_item_ll);
				holder.action = convertView.findViewById(R.id.action);
				holder.tv_foodname=(TextView) convertView.findViewById(R.id.tv_foodname);
				holder.tv_foodcount=(TextView) convertView.findViewById(R.id.tv_foodcount);
				LayoutParams lp = holder.cookbook_item_ll.getLayoutParams();
				lp.width = width;
			}
			holder.tv_foodname.setText(fooddatalists.get(position).foodname);
			holder.tv_foodcount.setText(fooddatalists.get(position).foodcount);
			// 设置监听事件
			convertView.setOnTouchListener(new View.OnTouchListener() {
				@SuppressLint("ClickableViewAccessibility")
				@Override
				public boolean onTouch(View v, MotionEvent event) {
					switch (event.getAction()) {
					case MotionEvent.ACTION_DOWN:
						if (resetView != null) {
							resetView.smoothScrollTo(0, 0);
						}
						x1 = (int) event.getRawX();// 得到相对应屏幕左上角的坐标
						y1 = (int) event.getRawY();
						break;

					case MotionEvent.ACTION_UP:
						x2 = (int) event.getRawX();
						y2 = (int) event.getRawY();
						double distance = Math
								.sqrt(Math.abs(x1 - x2) * Math.abs(x1 - x2) + Math.abs(y1 - y2) * Math.abs(y1 - y2));
						if (distance < 15) { // 距离较小，当作click事件来处理

							return false;
						} else {
							// 获得ViewHolder
							vh = (ViewHolder) v.getTag();
							// 获得HorizontalScrollView滑动的水平方向值.
							int scrollX = vh.hSView.getScrollX();
							// 获得操作区域的长度
							int actionW = vh.action.getWidth();
							// 注意使用smoothScrollTo,这样效果看起来比较圆滑,不生硬
							// 如果水平方向的移动值<操作区域的长度的一半,就复原
							if (scrollX < actionW / 2) {
								vh.hSView.smoothScrollTo(0, 0);
							} else// 否则的话显示操作区域
							{
								vh.hSView.smoothScrollTo(actionW, 0);
								resetView = vh.hSView;
							}
							return true;
						}

					}
					return false;
				}
			});
			holder.action.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					// 删除该条目
					// deleteAddress(position, address.id);
					fooddatalists.remove(position);
					notifyDataSetChanged();
					vh.hSView.smoothScrollTo(0, 0);
				}
			});

			// 这里防止删除一条item后,ListView处于操作状态,直接还原
			if (holder.hSView.getScrollX() != 0) {
				holder.hSView.scrollTo(0, 0);
			}
			return convertView;
		}

		class ViewHolder {
			HorizontalScrollView hSView;
			LinearLayout cookbook_item_ll;
			View action;
			TextView tv_foodname;
			TextView tv_foodcount;
		}

	}
	
}

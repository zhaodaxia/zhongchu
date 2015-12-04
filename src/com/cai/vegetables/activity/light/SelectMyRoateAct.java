package com.cai.vegetables.activity.light;

import com.cai.vegetables.R;
import com.cai.vegetables.activity.BaseActivity;
import com.cai.vegetables.utils.VegetableUtils;
import com.cai.vegetables.view.LoadingView;
import com.cai.vegetables.view.pulltorefresh.PullToRefreshListView;
import com.lidroid.xutils.view.annotation.ViewInject;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.BaseAdapter;
import android.widget.HorizontalScrollView;
import android.widget.RelativeLayout;

/**
 * 选择常用路线
 * 
 * @author yang
 *
 */
public class SelectMyRoateAct extends BaseActivity {
	@ViewInject(R.id.selectroate_ptr)
	private PullToRefreshListView plv;
	@ViewInject(R.id.loadView)
	private LoadingView loadView;

	private MyAdapter adapter;

	private int width;

	@Override
	public void setLayout() {
		setContentView(R.layout.selectmyroate);
	}

	@SuppressLint("NewApi")
	@Override
	public void init(Bundle savedInstanceState) {
		setTopTitle("选择常用路线");
		setRightBtn2("添加", new OnClickListener() {
			@Override
			public void onClick(View v) {
				gotoActivity(AddRoateAct.class, false);
			}
		});

		loadView.loadComplete();
		adapter = new MyAdapter();
		plv.getRefreshableView().setAdapter(adapter);
		plv.setPullRefreshEnabled(true);
		plv.setScrollLoadEnabled(true);
		plv.setPullLoadEnabled(false);
		plv.setHasMoreData(true);
		plv.getRefreshableView().setDivider(null);
		plv.getRefreshableView().setVerticalScrollBarEnabled(false);
		plv.getRefreshableView().setSelector(new ColorDrawable(Color.TRANSPARENT));
		plv.setLastUpdatedLabel(VegetableUtils.getCurrentTime());

		// 从Android 3.2（API Level 13）开始，在Activity里使用下面的方法来获取屏幕分辨率（单位是像素）
		Point size = new Point();
		Display display = getWindowManager().getDefaultDisplay();
		display.getSize(size);
		width = size.x;
	}

	private class MyAdapter extends BaseAdapter {
		private HorizontalScrollView resetView;
		private Holder vh;
		private int x1, y1, x2, y2;

		@Override
		public int getCount() {

			return 4;

		}

		@Override
		public View getView(final int position, View convertView, ViewGroup parent) {

			Holder holder;
			if (convertView != null && convertView instanceof HorizontalScrollView) {
				holder = (Holder) convertView.getTag();
			} else {
				holder = new Holder();
				convertView = View.inflate(SelectMyRoateAct.this, R.layout.selectroate_item, null);
				convertView.setTag(holder);
				holder.hSView = (HorizontalScrollView) convertView.findViewById(R.id.hsv);
				holder.rl = (RelativeLayout) convertView.findViewById(R.id.rl);
				holder.action = convertView.findViewById(R.id.action);

				LayoutParams lp = holder.rl.getLayoutParams();
				lp.width = width;
			}
			// 填值
			// final Address address = addressList.get(position);
			// holder.tv_name_number.setText("一二三" + " " + "999");
			// holder.tv_address.setText("地址 ");

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
							vh = (Holder) v.getTag();
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
					// TODO 可以自定义删除地址的方法
					// deleteAddress(position, address.id);

					vh.hSView.smoothScrollTo(0, 0);
				}
			});

			// 这里防止删除一条item后,ListView处于操作状态,直接还原
			if (holder.hSView.getScrollX() != 0) {
				holder.hSView.scrollTo(0, 0);
			}

			return convertView;
		}

		@Override
		public Object getItem(int position) {
			return null;
		}

		@Override
		public long getItemId(int position) {
			return 0;
		}

	}

	class Holder {
		HorizontalScrollView hSView;
		RelativeLayout rl;
		View action;

	}

}

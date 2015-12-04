package com.cai.vegetables.widget;

import com.cai.vegetables.R;
import com.cai.vegetables.entity.Food;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Point;
import android.text.TextUtils;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.EditText;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;

/** 
* 菜谱添加view
* @author dongsy  
* @version 创建时间：2015年11月3日 上午9:32:43 
*/
public class CookLayoutViewFour extends LinearLayout {

	private Context context;
	private HorizontalScrollView hSView;
	private View action;
	private EditText chitken;
	private HorizontalScrollView resetView;
	private LinearLayout cookbook_item_ll;
	private int x1, y1, x2, y2;
	private View convertView;
	private int width;
	private String cookinfo;
	public CookLayoutViewFour(Context context,int width) {
		super(context);
		// TODO Auto-generated constructor stub
		this.context=context;
		this.width=width;
		indata();
	}

	public CookLayoutViewFour(Context context,int width,String cookinfo) {
		super(context);
		// TODO Auto-generated constructor stub
		this.context=context;
		this.width=width;
		this.cookinfo=cookinfo;
		indata();
	}
	
	private void indata() {
		// TODO Auto-generated method stub
		convertView=View.inflate(context, R.layout.releasefour_item, null);
		hSView = (HorizontalScrollView) convertView.findViewById(R.id.hsv);
		action = convertView.findViewById(R.id.action);
		cookbook_item_ll=(LinearLayout) convertView.findViewById(R.id.cookbook_item_ll);
		LayoutParams lp = (LayoutParams) cookbook_item_ll.getLayoutParams();
		lp.width = width;
		chitken= (EditText) convertView.findViewById(R.id.ed_cookkitchen);
		if(!TextUtils.isEmpty(cookinfo))
			chitken.setText(cookinfo);
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
						// 获得HorizontalScrollView滑动的水平方向值.
						int scrollX = hSView.getScrollX();
						// 获得操作区域的长度
						int actionW = action.getWidth();
						// 注意使用smoothScrollTo,这样效果看起来比较圆滑,不生硬
						// 如果水平方向的移动值<操作区域的长度的一半,就复原
						if (scrollX < actionW / 2) {
							hSView.smoothScrollTo(0, 0);
						} else// 否则的话显示操作区域
						{
							hSView.smoothScrollTo(actionW, 0);
							resetView = hSView;
						}
						return true;
					}
				}
				return false;
			}
		});

		action.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// 删除该条目
				hSView.smoothScrollTo(0, 0);
				removeView(convertView);
			}
		});
		
		addView(convertView);
	}
	
	public String getIteminfo(){
		cookinfo=chitken.getText().toString().trim();
		return cookinfo;
	}

}

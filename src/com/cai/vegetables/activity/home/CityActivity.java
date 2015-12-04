package com.cai.vegetables.activity.home;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.os.Bundle;
import android.view.LayoutInflater;

import com.cai.vegetables.R;
import com.cai.vegetables.activity.BaseActivity;
import com.cai.vegetables.adapter.CityListAdapter;
import com.cai.vegetables.entity.City;
import com.cai.vegetables.view.BladeView;
import com.cai.vegetables.view.PinnedHeaderListView;
import com.cai.vegetables.widget.ClearEditText;
import com.lidroid.xutils.view.annotation.ViewInject;

/**
 * 城市切换
 * @author wangbin
 *
 */
public class CityActivity extends BaseActivity{

	private static final String FORMAT = "^[a-z,A-Z].*$";
	@ViewInject(R.id.mLetterListView)
	private BladeView mLetter;
	
	@ViewInject(R.id.plv)
	private PinnedHeaderListView mListView;
	@ViewInject(R.id.clEt)
	private ClearEditText clEt;

	// 首字母集
	private List<String> mSections;
	// 根据首字母存放数据
	private Map<String, List<City>> mMap;
	// 首字母位置集
	private List<Integer> mPositions;
	// 首字母对应的位置
	private Map<String, Integer> mIndexer;
	
	/**
	 * 城市列表Adapter
	 */
	private CityListAdapter mAdapter;
	@Override
	public void setLayout() {
		setContentView(R.layout.activity_city);
	}

	@Override
	public void init(Bundle savedInstanceState) {
		setTopTitle("城市切换");
		clEt.setHint("请输入城市名");
		mLetter.setOnItemClickListener(new BladeView.OnItemClickListener() {

			@Override
			public void onItemClick(String s) {
				if (mIndexer.get(s) != null) {
					mListView.setSelection(mIndexer.get(s));
				}
			}
		});
		mSections = new ArrayList<String>();
		mMap = new HashMap<String, List<City>>();
		mPositions = new ArrayList<Integer>();
		mIndexer = new HashMap<String, Integer>();
		
		initData(getCity());
	}
	
	private List<City> getCity(){
		List<City> datas=new ArrayList<City>();
		for(int i=0;i<4;i++){
			City city=new City();
			city.setCity_name("上海");
			city.setFirst_letter("S");
			datas.add(city);
		}
		
		for(int i=0;i<5;i++){
			City city=new City();
			city.setCity_name("北京");
			city.setFirst_letter("B");
			datas.add(city);
		}
		
		for(int i=0;i<3;i++){
			City city=new City();
			city.setCity_name("南京");
			city.setFirst_letter("N");
			datas.add(city);
		}
		
		for(int i=0;i<6;i++){
			City city=new City();
			city.setCity_name("河南");
			city.setFirst_letter("H");
			datas.add(city);
		}
		
		return datas;
	}
	
	
	/**
	 * 初始化数据
	 * 
	 * @param datas
	 */
	private void initData(List<City> datas) {
		
		Collections.sort(datas, new MyComparator());
		
		for (int i = 0; i < datas.size(); i++) {
			String firstName = datas.get(i).getFirst_letter();
			if (firstName.matches(FORMAT)) {
				if (mSections.contains(firstName)) {
					mMap.get(firstName).add(datas.get(i));
				} else {
					mSections.add(firstName);
					List<City> list = new ArrayList<City>();
					list.add(datas.get(i));
					mMap.put(firstName, list);
				}
			}

		}

		Collections.sort(mSections);
		
		int position = 0;
		for (int i = 0; i < mSections.size(); i++) {
			mIndexer.put(mSections.get(i), position);// 存入map中，key为首字母字符串，value为首字母在listview中位置
			mPositions.add(position);// 首字母在listview中位置，存入list中
			position += mMap.get(mSections.get(i)).size();// 计算下一个首字母在listview的位置
		}

		mAdapter = new CityListAdapter(this, datas, mSections, mPositions);
		mListView.setAdapter(mAdapter);
		mListView.setOnScrollListener(mAdapter);
		mListView.setPinnedHeaderView(LayoutInflater.from(this).inflate(
				R.layout.listview_head, mListView, false));

	}
	
	public class MyComparator implements Comparator<City> {

		@Override
		public int compare(City c1, City c2) {

			return c1.getFirst_letter().compareTo(c2.getFirst_letter());
		}

	}

}

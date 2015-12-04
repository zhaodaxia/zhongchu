package com.cai.vegetables.activity.cookbook;

import com.cai.vegetables.activity.BaseActivity;
import com.cai.vegetables.entity.CookBook;

import android.content.Intent;
import android.os.Bundle;

/** 
*
* @author dongsy  
* @version 创建时间：2015年11月2日 下午7:32:25 
*/
public abstract class CookBase extends BaseActivity {
	public Intent gonext;
	public CookBook cook;
	//菜谱对象常量
	public final static String COOKINFO="COOKINFO";

	//步骤选择用具
	public final static String COOKUSE="COOKUSE";
	//步骤选择食材
	public final static String COOKFOOD="COOKFOOD";	
	//步骤说明
	public final static String COOKSTEPDES="COOKSTEPDES";		
	
	//食材缓存key
	public final static String SPCOOKFOOD="SPCOOKFOOD";		
	
	//厨具缓存key
	public final static String SPCOOKUSE="SPCOOKUSE";	
	
	//厨具缓存key
	public final static String ISUPDATE="ISUPDATE";	
	@Override
	public void setLayout() {
		// TODO Auto-generated method stub
		cook=new CookBook();
		setCookLayout();
	}

	@Override
	public void init(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		initCook(savedInstanceState);
	}

	public abstract void setCookLayout();

	public abstract void initCook(Bundle savedInstanceState);
	
	
}

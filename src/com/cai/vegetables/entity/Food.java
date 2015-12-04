package com.cai.vegetables.entity;


import java.io.Serializable;

import android.os.Parcel;
import android.os.Parcelable;

/** 
* 食材类
* @author dongsy  
* @version 创建时间：2015年11月2日 下午7:45:53 
*/
	public class Food implements Serializable{
		public String foodname;
		public String foodcount;
		@Override
		public String toString() {
			return  foodcount+foodname;
		}
		
	}


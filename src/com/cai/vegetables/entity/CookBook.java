package com.cai.vegetables.entity;

import java.io.Serializable;
import java.util.List;

/** 
* 菜谱bean
* @author dongsy  
* @version 创建时间：2015年11月2日 下午6:56:47 
*/
public class CookBook implements Serializable{
	//菜谱基本信息类
	public class BaseInfo implements Serializable{
		public String cookname;
		public String cookdes;
		public String level;
		public String cookstye;
		@Override
		public String toString() {
			return "菜谱名：" + cookname + "描述：" + cookdes + "困难程度：" + level + "菜系："
					+ cookstye;
		}

	}
	//菜谱基本信息
	public BaseInfo cookinfo;
	//菜谱封面
	public String bguri;
	//食材
	public List<Food> foodlist;
	//厨具
	public List<String> kitchen;
	//步骤类
	
	//步骤
	public List<CookStep> steplist;
}

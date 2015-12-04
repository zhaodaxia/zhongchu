package com.cai.vegetables.entity;

import java.io.Serializable;
import java.util.List;

/** 
* 菜谱步骤类
* @author dongsy  
* @version 创建时间：2015年11月2日 下午7:46:59 
*/
public class CookStep implements Serializable{
	//步骤说明
	public String stepdes;
	//所选食材
	public List<Food> stepfoodlist;
	//所需用具
	public List<String> stepkitchenlist;
	//步骤图片
	public List<String> stepimagelist;

}

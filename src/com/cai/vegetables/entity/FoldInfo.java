package com.cai.vegetables.entity;

import java.util.List;

/** 
* 商品分类信息bean
* @author dongsy  
* @version 创建时间：2015年10月26日 下午9:49:58 
*/
public class FoldInfo {
	public String SearchName;
	public List<String> SearchItemlist;
	public FoldInfo(String searchName, List<String> searchItemlist,boolean isvisvable) {
		super();
		SearchName = searchName;
		SearchItemlist = searchItemlist;
		IsVisvable=isvisvable;
	}
	public boolean IsVisvable;
}	

package com.cai.vegetables.entity;
/** 
* 我的收藏信息bean
* @author dongsy  
* @version 创建时间：2015年11月2日 下午3:50:37 
*/
public class CollectInfo {
	/**
	 * 我的收藏类型
	 * 商品  商铺  众筹
	 * PRODUCT SHOP COLLRCT
	 */
	public String collettype;
	public String collectname;
	public String saledes;
	public Double price;
	public CollectInfo(String collectname, String saledes, Double price) {
		super();
		this.collectname = collectname;
		this.saledes = saledes;
		this.price = price;
	}
	
}

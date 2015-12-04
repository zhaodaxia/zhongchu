package com.cai.vegetables.entity;
/** 
* 订单信息bean
* @author dongsy  
* @version 创建时间：2015年10月30日 下午2:33:52 
*/
public class OrderInfo {
	/*
	 * state 
	 *  待付款  待发货  待收货  待评论  
	 *  NOPAY NODE NORE NOCO 
	 */
	public String state;
	public String goodsname;
	public int goodsprice;
	public int goodscount;
	public String ordertime;
	public int freight;
	public OrderInfo(String state, String goodsname, int goodsprice, int goodscount, String ordertime, int freight) {
		super();
		this.state = state;
		this.goodsname = goodsname;
		this.goodsprice = goodsprice;
		this.goodscount = goodscount;
		this.ordertime = ordertime;
		this.freight = freight;
	} 
	
	
}

package com.cai.vegetables.entity;

import java.io.Serializable;

/** 
* 我的信息bean
* @author dongsy  
* @version 创建时间：2015年10月30日 上午9:51:16 
*/
public class Message implements Serializable{
	public String title;
	public String content;
	public String time;
	public Message(String title,String content,String time){
		this.content=content;
		this.time=time;
		this.title=title;
	}
}

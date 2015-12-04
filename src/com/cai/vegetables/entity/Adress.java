package com.cai.vegetables.entity;
/** 
* 地址信息bean
* @author dongsy  
* @version 创建时间：2015年10月30日 下午9:14:37 
*/
public class Adress {
	public String username;
	public String useradress;
	public String usernumber;
	public boolean isdefault;
	public Adress(String username, String useradress, String usernumber, boolean isdefault) {
		super();
		this.username = username;
		this.useradress = useradress;
		this.usernumber = usernumber;
		this.isdefault = isdefault;
	}
	
}

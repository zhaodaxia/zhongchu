package com.cai.vegetables.entity;

import java.io.Serializable;

/**
 * @category 城市信息
 * @author 坡
 *
 */
public class City implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1099694442766166681L;
	private String city_id;
	private String province_id;
	private String city_name;
	private String status;
	private String is_hot;
	private String first_letter;
	//是否使用 0未使用，1已经使用过
	private int is_use;

	public String getCity_id() {
		return city_id;
	}

	public void setCity_id(String city_id) {
		this.city_id = city_id;
	}

	public String getProvince_id() {
		return province_id;
	}

	public void setProvince_id(String province_id) {
		this.province_id = province_id;
	}

	public String getCity_name() {
		return city_name;
	}

	public void setCity_name(String city_name) {
		this.city_name = city_name;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getIs_hot() {
		return is_hot;
	}

	public void setIs_hot(String is_hot) {
		this.is_hot = is_hot;
	}

	public String getFirst_letter() {
		return first_letter;
	}

	public void setFirst_letter(String first_letter) {
		this.first_letter = first_letter;
	}

	public int getIs_use() {
		return is_use;
	}

	public void setIs_use(int is_use) {
		this.is_use = is_use;
	}
	
	
}

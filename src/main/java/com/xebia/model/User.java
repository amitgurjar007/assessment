package com.xebia.model;

import java.util.List;

public class User {

	private String name;
	private String joinDate;
	private boolean isEmployee;
	private boolean isAffiliate;
	private boolean isGetDiscount = false;
	private List<Product> productList;
	
	public User() {
	}
	
	public User(String name, String joinDate, boolean isEmployee, boolean isAffiliate,
			List<Product> productList) {
		super();
		this.name = name;
		this.joinDate = joinDate;
		this.isEmployee = isEmployee;
		this.isAffiliate = isAffiliate;
		this.productList = productList;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getJoinDate() {
		return joinDate;
	}
	public void setJoinDate(String joinDate) {
		this.joinDate = joinDate;
	}
	public boolean isEmployee() {
		return isEmployee;
	}
	public void setEmployee(boolean isEmployee) {
		this.isEmployee = isEmployee;
	}
	public boolean isAffiliate() {
		return isAffiliate;
	}
	public void setAffiliate(boolean isAffiliate) {
		this.isAffiliate = isAffiliate;
	}
	
	public boolean isGetDiscount() {
		return isGetDiscount;
	}
	
	public void setGetDiscount(boolean isGetDiscount) {
		this.isGetDiscount = isGetDiscount;
	}
	
	public List<Product> getProductList() {
		return productList;
	}
	public void setProductList(List<Product> productList) {
		this.productList = productList;
	}
	
}

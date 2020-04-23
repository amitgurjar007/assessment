package com.xebia.model;

public class Product {

	private String name;
	private double price;
	private int quantity;
	private Category category;
	
	public Product() {
	}
	
	public Product(String name, double prize,int quantity, Category category) {
		super();
		this.name = name;
		this.price = prize;
		this.setQuantity(quantity);
		this.category = category;
	}
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getPrize() {
		return price;
	}
	public void setPrize(double prize) {
		this.price = prize;
	}
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	//This is total value of product;
	public double getTotalPrice() {
		return price*quantity;
	}

	/*
	 * public void setTotalPrize(double totalPrize) { this.totalPrize = totalPrize;
	 * }
	 */
	
}

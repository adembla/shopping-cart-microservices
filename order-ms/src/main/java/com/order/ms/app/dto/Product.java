package com.order.ms.app.dto;

public class Product {
	
	private long id;
	private String productName;
	private int quanity;
	private double unitPrice;
	private String productDescription;
	private Category category;
	@Override
	public String toString() {
		return "Product [id=" + id + ", productName=" + productName + ", quanity=" + quanity + ", unitPrice="
				+ unitPrice + ", productDescription=" + productDescription + ", category=" + category + "]";
	}
	public Product(long id, String productName, int quanity, double unitPrice, String productDescription,
			Category category) {
		super();
		this.id = id;
		this.productName = productName;
		this.quanity = quanity;
		this.unitPrice = unitPrice;
		this.productDescription = productDescription;
		this.category = category;
	}
	
	public Product() {
		// TODO Auto-generated constructor stub
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public int getQuanity() {
		return quanity;
	}
	public void setQuanity(int quanity) {
		this.quanity = quanity;
	}
	public double getUnitPrice() {
		return unitPrice;
	}
	public void setUnitPrice(double unitPrice) {
		this.unitPrice = unitPrice;
	}
	public String getProductDescription() {
		return productDescription;
	}
	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	
	

}

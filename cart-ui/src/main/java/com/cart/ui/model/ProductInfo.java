package com.cart.ui.model;

public class ProductInfo {
	
		private long id;
		private String productName;
		private long quanity;
		private double unitPrice;
		private String productDescription;
		private Category category;
		@Override
		public String toString() {
			return "Product [id=" + id + ", productName=" + productName + ", quanity=" + quanity + ", unitPrice="
					+ unitPrice + ", productDescription=" + productDescription + ", category=" + category + "]";
		}
		public ProductInfo(long id, String productName, String productDescription,double unitPrice,long quanity
				) {
			super();
			this.id = id;
			this.productName = productName;
			this.quanity = quanity;
			this.unitPrice = unitPrice;
			this.productDescription = productDescription;
		}
		
		public ProductInfo() {
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
		public long getQuanity() {
			return quanity;
		}
		public void setQuanity(long quanity) {
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

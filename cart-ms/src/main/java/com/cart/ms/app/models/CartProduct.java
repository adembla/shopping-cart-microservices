package com.cart.ms.app.models;

public class CartProduct {
	
	private long id;
	private String product_name;
	private long quanity;
	private String product_dscr;
	private long price;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getProduct_name() {
		return product_name;
	}
	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}
	public long getQuanity() {
		return quanity;
	}
	public void setQuanity(long quanity) {
		this.quanity = quanity;
	}
	public String getProduct_dscr() {
		return product_dscr;
	}
	public void setProduct_dscr(String product_dscr) {
		this.product_dscr = product_dscr;
	}
	public long getPrice() {
		return price;
	}
	public void setPrice(long price) {
		this.price = price;
	}
	public CartProduct(long id, String product_name, long quanity, String product_dscr, long price) {
		super();
		this.id = id;
		this.product_name = product_name;
		this.quanity = quanity;
		this.product_dscr = product_dscr;
		this.price = price;
	}
	
	public CartProduct() {
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "CartProduct [id=" + id + ", product_name=" + product_name + ", quanity=" + quanity + ", product_dscr="
				+ product_dscr + ", price=" + price + "]";
	}
	
}

package com.cart.ms.app.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name = "cart")
@Table(name = "cart")
public class Cart implements Serializable {

	private static final long serialVersionUID = 1785746404114078308L;

	@Id
	@Column(name="CART_ID")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;

	@Column(name="ID")
	private long productId;


	@Column(name="QUANTITY", nullable= true)
	private long quantity;


	@Override
	public String toString() {
		return "Cart [id=" + id + ", productId=" + productId + ", quantity=" + quantity + "]";
	}


	public Cart(Long id, long productId, long quantity) {
		super();
		this.id = id;
		this.productId = productId;
		this.quantity = quantity;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public long getProductId() {
		return productId;
	}


	public void setProductId(long productId) {
		this.productId = productId;
	}


	public long getQuantity() {
		return quantity;
	}


	public void setQuantity(long quantity) {
		this.quantity = quantity;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}


	public Cart(long productId, long quantity) {
		super();
		this.productId = productId;
		this.quantity = quantity;
	}
	
	public Cart() {
	}

}

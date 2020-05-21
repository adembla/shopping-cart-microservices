package com.order.ms.app.models;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name = "invoice")
@Table(name = "invoice")
public class Invoice implements Serializable {

	private static final long serialVersionUID = -3869826235658717494L;

	@Id
	@Column(name="INVOICE_ID")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;

	@Column(name="CUST_ID")
	private Long customerId;

	@Column(name="DATE_OF_PURCHASE")
	private Date dateOfPurchase;
	
	@Column(name = "MODE_PAY_ID")
	private String modeOfPay;
	
	@Column(name="TOTAL_AMT",nullable=true)
	private Double totalTaxAmount;

	/*
	 * @Column(name="TRANSACTION_ID",nullable=true) private String transactionId;
	 */
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public Invoice() {
		// TODO Auto-generated constructor stub
	}
	
	

	@Override
	public String toString() {
		return "Invoice [id=" + id + ", customerId=" + customerId + ", dateOfPurchase=" + dateOfPurchase
				+ ", modeOfPay=" + modeOfPay + ", totalTaxAmount=" + totalTaxAmount + "]";
	}

	public Date getDateOfPurchase() {
		return dateOfPurchase;
	}

	public void setDateOfPurchase(Date dateOfPurchase) {
		this.dateOfPurchase = dateOfPurchase;
	}


	public String getModeOfPay() {
		return modeOfPay;
	}

	public void setModeOfPay(String modeOfPay) {
		this.modeOfPay = modeOfPay;
	}

	public Invoice(Long customerId, Date dateOfPurchase, String modeOfPay, Double totalTaxAmount) {
		super();
		this.customerId = customerId;
		this.dateOfPurchase = dateOfPurchase;
		this.modeOfPay = modeOfPay;
		this.totalTaxAmount = totalTaxAmount;
	}

	public Invoice(Long id, Long customerId, Date dateOfPurchase, String modeOfPay, Double totalTaxAmount) {
		super();
		this.id = id;
		this.customerId = customerId;
		this.dateOfPurchase = dateOfPurchase;
		this.modeOfPay = modeOfPay;
		this.totalTaxAmount = totalTaxAmount;
	}

	public Double getTotalTaxAmount() {
		return totalTaxAmount;
	}

	public void setTotalTaxAmount(Double totalTaxAmount) {
		this.totalTaxAmount = totalTaxAmount;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((customerId == null) ? 0 : customerId.hashCode());
		result = prime * result + ((dateOfPurchase == null) ? 0 : dateOfPurchase.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((modeOfPay == null) ? 0 : modeOfPay.hashCode());
		result = prime * result + ((totalTaxAmount == null) ? 0 : totalTaxAmount.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Invoice other = (Invoice) obj;
		if (customerId == null) {
			if (other.customerId != null)
				return false;
		} else if (!customerId.equals(other.customerId))
			return false;
		if (dateOfPurchase == null) {
			if (other.dateOfPurchase != null)
				return false;
		} else if (!dateOfPurchase.equals(other.dateOfPurchase))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (modeOfPay == null) {
			if (other.modeOfPay != null)
				return false;
		} else if (!modeOfPay.equals(other.modeOfPay))
			return false;
		if (totalTaxAmount == null) {
			if (other.totalTaxAmount != null)
				return false;
		} else if (!totalTaxAmount.equals(other.totalTaxAmount))
			return false;
		return true;
	}

}

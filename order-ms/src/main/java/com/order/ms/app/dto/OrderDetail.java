package com.order.ms.app.dto;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
"invoiceId",
"customerId",
"dateofPurChase",
"modePayId",
"transactionId",
"items",
"totalCost"
})
public class OrderDetail {

@JsonProperty("customerId")
private Long customerId;

@JsonProperty("invoiceId")
private Long invoiceId;

public Long getInvoiceId() {
	return invoiceId;
}

public void setInvoiceId(Long invoiceId) {
	this.invoiceId = invoiceId;
}

@JsonProperty("dateofPurChase")
private Date dateofPurChase;
@JsonProperty("modePayId")
private String modePayId;
@JsonProperty("transactionId")
private String transactionId;
@JsonProperty("items")
private List<Item> items = null;

@JsonProperty(value= "totalCost")
private double totalCost;

@JsonProperty("totalCost")
public double getTotalCost() {
return totalCost;
}

@JsonProperty("totalCost")
public void setTotalCost(double totalCost) {
	this.totalCost = totalCost;
}

@Override
public String toString() {
	return "OrderDetail [customerId=" + customerId + ", invoiceId=" + invoiceId + ", dateofPurChase=" + dateofPurChase
			+ ", modePayId=" + modePayId + ", transactionId=" + transactionId + ", items=" + items + ", totalCost="
			+ totalCost + ", additionalProperties=" + additionalProperties + "]";
}

@JsonIgnore
private Map<String, Object> additionalProperties = new HashMap<String, Object>();

@JsonProperty("customerId")
public Long getCustomerId() {
return customerId;
}

@JsonProperty("customerId")
public void setCustomerId(Long customerId) {
this.customerId = customerId;
}

@JsonProperty("dateofPurChase")
public Date getDateofPurChase() {
return dateofPurChase;
}

@JsonProperty("dateofPurChase")
public void setDateofPurChase(Date dateofPurChase) {
this.dateofPurChase = dateofPurChase;
}

@JsonProperty("modePayId")
public String getModePayId() {
return modePayId;
}

@JsonProperty("modePayId")
public void setModePayId(String modePayId) {
this.modePayId = modePayId;
}

@JsonProperty("transactionId")
public String getTransactionId() {
return transactionId;
}

@JsonProperty("cashierName")
public void setTransactionId(String transactionId) {
this.transactionId = transactionId;
}

@JsonProperty("items")
public List<Item> getItems() {
return items;
}

@JsonProperty("items")
public void setItems(List<Item> items) {
this.items = items;
}

@JsonAnyGetter
public Map<String, Object> getAdditionalProperties() {
return this.additionalProperties;
}

@JsonAnySetter
public void setAdditionalProperty(String name, Object value) {
this.additionalProperties.put(name, value);
}
public OrderDetail() {
}

public OrderDetail(Long customerId, Date dateofPurChase, String modePayId, String transactionId, List<Item> items,
		double totalCost, Map<String, Object> additionalProperties) {
	super();
	this.customerId = customerId;
	this.dateofPurChase = dateofPurChase;
	this.modePayId = modePayId;
	this.transactionId = transactionId;
	this.items = items;
	this.totalCost = totalCost;
	this.additionalProperties = additionalProperties;
}
}
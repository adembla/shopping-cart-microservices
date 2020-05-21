/**
 * 
 */
package com.order.ms.app.dto;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
"productId",
"quantity",
"productName",
"unitCost"
})
public class Item {

@JsonProperty("productId")
private Long productId;
@JsonProperty("quantity")
private Integer quantity;

@JsonProperty("productName")
private String productName;

public String getProductName() {
	return productName;
}

public void setProductName(String productName) {
	this.productName = productName;
}

@JsonProperty(value= "unitCost")
private double unitCost;
@JsonIgnore
private Map<String, Object> additionalProperties = new HashMap<String, Object>();

@JsonProperty("productId")
public Long getProductId() {
return productId;
}

@JsonProperty("productId")
public void setProductId(Long productId) {
this.productId = productId;
}

@JsonProperty("quantity")
public Integer getQuantity() {
return quantity;
}

@JsonProperty("quantity")
public void setQuantity(Integer quantity) {
this.quantity = quantity;
}

@JsonProperty(value= "unitCost")
public double getUnitCost() {
	return unitCost;
}
@JsonProperty(value= "unitCost")
public void setUnitCost(double unitCost) {
	this.unitCost = unitCost;
}

@JsonAnyGetter
public Map<String, Object> getAdditionalProperties() {
return this.additionalProperties;
}

@JsonAnySetter
public void setAdditionalProperty(String name, Object value) {
this.additionalProperties.put(name, value);
}

}

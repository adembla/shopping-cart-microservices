/**
 * 
 */
package com.cart.ui.model;

import java.math.BigInteger;
/**
 * @author hitjoshi
 *
 */
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

@Override
	public String toString() {
		return "Item [productId=" + productId + ", quantity=" + quantity + ", productName=" + productName
				+ ", totalCost=" + unitCost + ", additionalProperties=" + additionalProperties + "]";
	}

@JsonProperty("productId")
private Long productId;
@JsonProperty("quantity")
private Long quantity;

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
public Long getQuantity() {
return quantity;
}

@JsonProperty("quantity")
public void setQuantity(Long quantity) {
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

/**
 * 
 */
package com.customer.ms.app.dto;

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
"productName",
"quantity",
"taxAmount",
"unitCost"
})
public class Product {

@JsonProperty("productId")
private Integer productId;
@JsonProperty("productName")
private String productName;
@JsonProperty("quantity")
private Integer quantity;
@JsonProperty("taxAmount")
private Double taxAmount;
@JsonProperty("unitCost")
private Integer unitCost;
@JsonIgnore
private Map<String, Object> additionalProperties = new HashMap<String, Object>();

@JsonProperty("productId")
public Integer getproductId() {
return productId;
}

@JsonProperty("productId")
public void setproductId(Integer productId) {
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

@JsonProperty("taxAmount")
public Double getTaxAmount() {
return taxAmount;
}

@JsonProperty("taxAmount")
public void setTaxAmount(Double taxAmount) {
this.taxAmount = taxAmount;
}

@JsonProperty("unitCost")
public Integer getUnitCost() {
return unitCost;
}

@JsonProperty("unitCost")
public void setUnitCost(Integer unitCost) {
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

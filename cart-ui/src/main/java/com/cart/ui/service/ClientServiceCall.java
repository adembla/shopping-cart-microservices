package com.cart.ui.service;

import java.util.List;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.cart.ui.model.OrderDetail;
import com.cart.ui.model.OuthResponse;
import com.cart.ui.model.Product;
import com.cart.ui.model.ProductInfo;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class ClientServiceCall {

	private RestTemplate restTemplate = new RestTemplate();

	public List<Product> getAllProducts(OuthResponse response) {

		MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
		headers.add("auth_token", response.getAccess_token());
		headers.add("Authorization", "Bearer " + response.getAccess_token());
		System.out.println("Auth Token is --- " + response.getAccess_token());
//		List<Product> products = restTemplate.getForObject(AppConstants.API_GATEWAY_BASE_URL+"/inventory-service/products", List.class);
		ResponseEntity<List> products = restTemplate.exchange(
				AppConstants.API_GATEWAY_BASE_URL + "/product-service/products", HttpMethod.GET,
				new HttpEntity<>(headers), List.class);

		return products.getBody();
	}

	public void addProductToCart(long productId, OuthResponse response) {
		MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
		headers.add("auth_token", response.getAccess_token());
		headers.add("Authorization", "Bearer " + response.getAccess_token());
		String finalURL = AppConstants.API_GATEWAY_CART + "cart/add?productId=" + productId;
		System.out.println("Final URL is ---- " + finalURL);
		restTemplate.exchange(finalURL, HttpMethod.POST, new HttpEntity<>(headers), Void.class);

	}

	public List<ProductInfo> getAllProductsInCart(OuthResponse response) {
		MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
		headers.add("auth_token", response.getAccess_token());
		headers.add("Authorization", "Bearer " + response.getAccess_token());
		ResponseEntity<String> products = restTemplate.exchange(AppConstants.API_GATEWAY_CART + "cart/", HttpMethod.GET,
				new HttpEntity<>(headers), String.class);
		List<ProductInfo> readValue = null;
		ObjectMapper mapper = new ObjectMapper();
		try {
			readValue = mapper.readValue(products.getBody(),new TypeReference<List<ProductInfo>>(){});
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return readValue;
	}

	public void removeFromCart(long productId, OuthResponse response) {
		MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
		headers.add("auth_token", response.getAccess_token());
		headers.add("Authorization", "Bearer " + response.getAccess_token());
		restTemplate.exchange(AppConstants.API_GATEWAY_CART + "cart/remove?productId=" + productId, HttpMethod.DELETE,
				new HttpEntity<>(headers), Void.class);

	}

	public OrderDetail checkoutAndBuy(OrderDetail orderDetail, OuthResponse response) {
		MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
		ResponseEntity<OrderDetail> orderDetailFetched = null;
		headers.add("auth_token", response.getAccess_token());
		headers.add("Content-Type", "application/json");
		headers.add("Authorization", "Bearer " + response.getAccess_token());
		String finalURL = AppConstants.API_GATEWAY_ORDER + "/orders/new";
		System.out.println("Final URL is ---- " + finalURL);
		orderDetailFetched = restTemplate.exchange(finalURL, HttpMethod.POST, new HttpEntity<>(orderDetail, headers),
				OrderDetail.class);
		return orderDetailFetched.getBody();
	}
}

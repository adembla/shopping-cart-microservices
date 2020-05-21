package com.cart.ms.app.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cart.ms.app.models.ProductInfo;
import com.cart.ms.app.service.CartService;
import com.cart.ms.app.verify.VerifyTokenUtil;

@RestController
@RequestMapping("/cart")
public class CartController {

	@Autowired
	CartService cartService;
	
	VerifyTokenUtil verifyTokenUtil = new VerifyTokenUtil();
	
	@GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<ProductInfo>> getAllProductsInCart(HttpServletRequest request)
	{
		ResponseEntity<List<ProductInfo>> allProductsInCart = null;
		if (verifyTokenUtil.verifyToken(request)) {
			List<ProductInfo> allcartProducts = cartService.getAllcartProducts();
			allProductsInCart = ResponseEntity.ok(allcartProducts);
		}
		else
		{
			allProductsInCart = new ResponseEntity<List<ProductInfo>>(HttpStatus.UNAUTHORIZED);
		}
		
		return allProductsInCart; 
	}
	
	
	@PostMapping(value = "/add")
	public ResponseEntity<Void> addProductToCart(@RequestParam("productId") long productId,HttpServletRequest request)
	{
		ResponseEntity<Void> allProductsInCart = null;
		System.out.println("Product Adding to cart ---- ");
		if (verifyTokenUtil.verifyToken(request)) {
		cartService.addProductsToCart(productId);
		allProductsInCart = ResponseEntity.ok(null);
		}
		else
		{
			allProductsInCart = new ResponseEntity<Void>(HttpStatus.UNAUTHORIZED);
		}
		return allProductsInCart;
	}
	
	@DeleteMapping(value = "/remove")
	public ResponseEntity<Void> removeProductFromCart(@RequestParam("productId") long productId,HttpServletRequest request)
	{
		ResponseEntity<Void> allProductsInCart = null;
		System.out.println("Product Adding to cart ---- ");
		if (verifyTokenUtil.verifyToken(request)) {
		cartService.removeProductsFromCart(productId);
		allProductsInCart = ResponseEntity.ok(null);
		}
		else
		{
			allProductsInCart = new ResponseEntity<Void>(HttpStatus.UNAUTHORIZED);
		}
		return allProductsInCart;
	}
	
	
}

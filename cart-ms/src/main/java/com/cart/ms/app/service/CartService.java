/**
 * 
 */
package com.cart.ms.app.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cart.ms.app.models.Cart;
import com.cart.ms.app.models.ProductInfo;
import com.cart.ms.app.repository.CartRepository;

@Service
public class CartService {
	
	List<Cart> cartProducts = new ArrayList<Cart>();
	List<Long> productsInCart = new ArrayList<Long>();
	
	@Autowired
	CartRepository categoryRepository;
	
	@Autowired
	CartRepository cartRepository;
	
	public List<ProductInfo> getAllcartProducts(){
		List<ProductInfo> collect = cartRepository.findProductDetails();
		System.out.println(collect);
		
		return collect;
	}
	
	public void addProductsToCart(long id)
	{
		Cart findOne = cartRepository.findOne(id);
		Cart cart = null;
		if(findOne==null)
		{
			cart = new Cart(id, 1);
		cartRepository.save(cart);
		}
		else
		{
		findOne.setQuantity(findOne.getQuantity()+1);
		cartRepository.save(findOne);
		}
	}
	
	
	public void removeProductsFromCart(long id)
	{
		Cart findOne = cartRepository.findOne(id);
		Cart cart = null;
		if(findOne.getQuantity()==1)
		{
			cartRepository.delete(cart);
		}
		else
		{
		findOne.setQuantity(findOne.getQuantity()-1);
		cartRepository.save(findOne);
		}
	}
	
}

package com.cart.ui.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import com.cart.ui.model.Item;
import com.cart.ui.model.LoginDTO;
import com.cart.ui.model.OrderDetail;
import com.cart.ui.model.OuthResponse;
import com.cart.ui.model.Product;
import com.cart.ui.model.ProductInfo;
import com.cart.ui.service.AppConstants;
import com.cart.ui.service.ClientServiceCall;

@Controller
@ControllerAdvice
public class MainController {

	@Autowired
	ClientServiceCall serviceCall;

	@Value("${client-auth}")
	private String authenticaion;
	
	private OuthResponse accessToken = null;
	
	@ModelAttribute
    public void addAttributes(Model model) {
        model.addAttribute("msg", "Welcome to the Netherlands!");
        model.addAttribute("auth", accessToken);
    }
	
	
	@GetMapping("/")
	public String index() {
		return "login";
	}

	@PostMapping("/hello")
	public String sayHello(@RequestParam("name") String name, Model model) {
		model.addAttribute("name", name);
		return "hello";
	}

	@PostMapping(value = "/login")
	public String doLogin(@ModelAttribute("login") LoginDTO login) {
		System.out.println(login.getUsername());
		ResponseEntity<OuthResponse> response = null;
		RestTemplate restTemplate = new RestTemplate();
		
		  HttpHeaders headers = new HttpHeaders();
		  headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		  headers.add("Authorization", "Basic "+authenticaion);
		  
		  HttpEntity<String> request = new HttpEntity<String>(headers);
		  
		  String access_token_url = AppConstants.API_GATEWAY_BASE_URL+"oauth/token";
		  access_token_url += "?grant_type=password"; access_token_url +=
		  "&username="+login.getUsername(); access_token_url +=
		  "&password="+login.getPassword();
		  
		  System.out.println("final URL is ---- "+access_token_url);
		  
		  response = restTemplate.exchange(access_token_url, HttpMethod.POST, request,
		  OuthResponse.class);
		  
		  accessToken = response.getBody();
		  
		return "redirect:/home";
	}

	@RequestMapping("/403")
	public String accessDenied(Model model) {
		return "/403";
	}

	@RequestMapping("/home")
	public String homeHandler(Model model) {

		System.out.println("Called home");

		List<Product> products = serviceCall.getAllProducts(accessToken);
		model.addAttribute("products", products);
		System.out.println(products);
		return "/productList";
	}

	@RequestMapping("/addToCart")
	public String addToCart(@RequestParam("productId") long productId) {

		System.out.println("Product " + productId + " added to cart");
		serviceCall.addProductToCart(productId,accessToken);

		return "redirect:/home";
	}

	@RequestMapping("/addToCart/cart")
	public String addToCartFromCartPage(@RequestParam("productId") long productId) {

		System.out.println("Product " + productId + " added to cart");
		serviceCall.addProductToCart(productId,accessToken);

		return "redirect:/cart";
	}

	@RequestMapping("/removeFromCart/cart")
	public String removeFromCartFromCartPage(@RequestParam("productId") long productId) {

		System.out.println("Product " + productId + " removed from cart");
		serviceCall.removeFromCart(productId,accessToken);

		return "redirect:/cart";
	}

	// Product List page.
	@RequestMapping({ "/productList" })
	public String listProductHandler(Model model, //
			@RequestParam(value = "name", defaultValue = "") String likeName,
			@RequestParam(value = "page", defaultValue = "1") int page) {
		/*
		 * final int maxResult = 5; final int maxNavigationPage = 10;
		 * 
		 * PaginationResult<ProductInfo> result = productDAO.queryProducts(page, //
		 * maxResult, maxNavigationPage, likeName);
		 * 
		 * model.addAttribute("paginationProducts", result);
		 */ return "productList";
	}

	@RequestMapping({ "/checkout" })
	public String checkoutAndBuy(HttpServletRequest request, Model model) {

		List<ProductInfo> products = serviceCall.getAllProductsInCart(accessToken);
		
		List<Item> items = new ArrayList<Item>();
		double totalCost = 0;
		for(int i=0;i<products.size();i++)
		{
			Item item = new Item();
			item.setProductId(products.get(i).getId());
			item.setProductName(products.get(i).getProductName());
			item.setQuantity(products.get(i).getQuanity());
			item.setUnitCost(products.get(i).getUnitPrice());
			totalCost = totalCost + (products.get(i).getQuanity()*products.get(i).getUnitPrice());
			items.add(item);
		}
		
		OrderDetail detail = new OrderDetail();
		
		detail.setDateofPurChase(new Date());
		detail.setModePayId("Debit Card");
		detail.setItems(items);
		detail.setCustomerId(3L);
		detail.setInvoiceId((long) (Math.random()*1000000));
		detail.setTransactionId("asdwdewwcwwefwef");
		detail.setTotalCost(totalCost);
		System.out.println("Order Detail Created is --- "+detail);
		OrderDetail orderDetail = serviceCall.checkoutAndBuy(detail,accessToken);
		
		model.addAttribute("orderDetails", orderDetail);
		return "shoppingCartFinalize";
	}

	@RequestMapping({ "/cart" })
	public String showProductsInCart(Model model) {
		System.out.println("Cart is called ---- ");
		List<ProductInfo> products = serviceCall.getAllProductsInCart(accessToken);
		System.out.println(products);
		model.addAttribute("products", products);
		return "shoppingCart";
	}


	// GET: Show Cart
	@RequestMapping(value = { "/shoppingCart" }, method = RequestMethod.GET)
	public String shoppingCartHandler(HttpServletRequest request, Model model) {
		/*
		 * CartInfo myCart = Utils.getCartInSession(request);
		 * 
		 * model.addAttribute("cartForm", myCart);
		 */
		return "shoppingCart";
	}

	// GET: Enter customer information.
	@RequestMapping(value = { "/shoppingCartFinalize" }, method = RequestMethod.GET)
	public String shoppingCartCustomerForm(HttpServletRequest request, Model model) {

		/*
		 * CartInfo cartInfo = Utils.getCartInSession(request);
		 * 
		 * // Cart is empty. if (cartInfo.isEmpty()) {
		 * 
		 * // Redirect to shoppingCart page. return "redirect:/shoppingCart"; }
		 * 
		 * CustomerInfo customerInfo = cartInfo.getCustomerInfo(); if (customerInfo ==
		 * null) { customerInfo = new CustomerInfo(); }
		 * 
		 * model.addAttribute("customerForm", customerInfo);
		 * 
		 */ return "shoppingCartFinalize";
	}

	// POST: Save customer information.
	/*
	 * @RequestMapping(value = { "/shoppingCartCustomer" }, method =
	 * RequestMethod.POST) public String shoppingCartCustomerSave(HttpServletRequest
	 * request, // Model model, //
	 * 
	 * @ModelAttribute("customerForm") @Validated CustomerInfo customerForm, //
	 * BindingResult result, // final RedirectAttributes redirectAttributes) {
	 * 
	 * // If has Errors. if (result.hasErrors()) { customerForm.setValid(false); //
	 * Forward to reenter customer info. return "shoppingCartCustomer"; }
	 * 
	 * customerForm.setValid(true); CartInfo cartInfo =
	 * Utils.getCartInSession(request);
	 * 
	 * cartInfo.setCustomerInfo(customerForm);
	 * 
	 * // Redirect to Confirmation page. return
	 * "redirect:/shoppingCartConfirmation"; }
	 */
	// GET: Review Cart to confirm.
	@RequestMapping(value = { "/shoppingCartConfirmation" }, method = RequestMethod.GET)
	public String shoppingCartConfirmationReview(HttpServletRequest request, Model model) {
		/*
		 * CartInfo cartInfo = Utils.getCartInSession(request);
		 * 
		 * // Cart have no products. if (cartInfo.isEmpty()) { // Redirect to
		 * shoppingCart page. return "redirect:/shoppingCart"; } else if
		 * (!cartInfo.isValidCustomer()) { // Enter customer info. return
		 * "redirect:/shoppingCartCustomer"; }
		 */
		return "shoppingCartConfirmation";
	}

	// POST: Send Cart (Save).
	@RequestMapping(value = { "/shoppingCartConfirmation" }, method = RequestMethod.POST)
	// Avoid UnexpectedRollbackException (See more explanations)
	public String shoppingCartConfirmationSave(HttpServletRequest request, Model model) {
		/*
		 * CartInfo cartInfo = Utils.getCartInSession(request);
		 * 
		 * // Cart have no products. if (cartInfo.isEmpty()) { // Redirect to
		 * shoppingCart page. return "redirect:/shoppingCart"; } else if
		 * (!cartInfo.isValidCustomer()) { // Enter customer info. return
		 * "redirect:/shoppingCartCustomer"; } try { orderDAO.saveOrder(cartInfo); }
		 * catch (Exception e) { // Need: Propagation.NEVER? return
		 * "shoppingCartConfirmation"; } // Remove Cart In Session.
		 * Utils.removeCartInSession(request);
		 * 
		 * // Store Last ordered cart to Session.
		 * Utils.storeLastOrderedCartInSession(request, cartInfo);
		 */
		// Redirect to successful page.
		return "redirect:/shoppingCartFinalize";
	}

	@RequestMapping(value = { "/orderPage" }, method = RequestMethod.GET)
	public String shoppingCartFinalize(HttpServletRequest request, Model model) {

		/*
		 * CartInfo lastOrderedCart = Utils.getLastOrderedCartInSession(request);
		 * 
		 * if (lastOrderedCart == null) { return "redirect:/shoppingCart"; }
		 */
		return "order";
	}
}
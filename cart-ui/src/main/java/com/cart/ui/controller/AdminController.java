package com.cart.ui.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
 
@Controller
public class AdminController {
 
 
    // Configurated In ApplicationContextConfig.
 
    // GET: Show Login Page
    @RequestMapping(value = { "/login" }, method = RequestMethod.GET)
    public String login(Model model) {
 
        return "login";
    }
 
    @RequestMapping(value = { "/accountInfo" }, method = RequestMethod.GET)
    public String accountInfo(Model model) {
		/*
		 * UserDetails userDetails = (UserDetails)
		 * SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		 * System.out.println(userDetails.getPassword());
		 * System.out.println(userDetails.getUsername());
		 * System.out.println(userDetails.isEnabled());
		 * 
		 * model.addAttribute("userDetails", userDetails);
		 */        return "accountInfo";
    }
 
    @RequestMapping(value = { "/orderList" }, method = RequestMethod.GET)
    public String orderList(Model model, //
            @RequestParam(value = "page", defaultValue = "1") String pageStr) {
        int page = 1;
        try {
            page = Integer.parseInt(pageStr);
        } catch (Exception e) {
        }
        final int MAX_RESULT = 5;
        final int MAX_NAVIGATION_PAGE = 10;
 
		/*
		 * PaginationResult<OrderInfo> paginationResult // =
		 * orderDAO.listOrderInfo(page, MAX_RESULT, MAX_NAVIGATION_PAGE);
		 * 
		 * model.addAttribute("paginationResult", paginationResult);
		 */
        return "orderList";
    }
 
    // GET: Show product.
    @RequestMapping(value = { "/product" }, method = RequestMethod.GET)
    public String product(Model model, @RequestParam(value = "code", defaultValue = "") String code) {
		/*
		 * ProductInfo productInfo = null;
		 * 
		 * if (code != null && code.length() > 0) { productInfo =
		 * productDAO.findProductInfo(code); } if (productInfo == null) { productInfo =
		 * new ProductInfo(); productInfo.setNewProduct(true); }
		 * model.addAttribute("productForm", productInfo);
		 */        return "product";
    }
 
	/*
	 * // POST: Save product
	 * 
	 * @RequestMapping(value = { "/product" }, method = RequestMethod.POST) // Avoid
	 * UnexpectedRollbackException (See more explanations) public String
	 * productSave(Model model, //
	 * 
	 * @ModelAttribute("productForm") @Validated ProductInfo productInfo, //
	 * BindingResult result, // final RedirectAttributes redirectAttributes) {
	 * 
	 * if (result.hasErrors()) { return "product"; } try {
	 * productDAO.save(productInfo); } catch (Exception e) { // Need:
	 * Propagation.NEVER? String message = e.getMessage();
	 * model.addAttribute("message", message); // Show product form. return
	 * "product";
	 * 
	 * } return "redirect:/productList"; }
	 */ 
    @RequestMapping(value = { "/order" }, method = RequestMethod.GET)
    public String orderView(Model model, @RequestParam("orderId") String orderId) {
		/*
		 * OrderInfo orderInfo = null; if (orderId != null) { orderInfo =
		 * this.orderDAO.getOrderInfo(orderId); } if (orderInfo == null) { return
		 * "redirect:/orderList"; } List<OrderDetailInfo> details =
		 * this.orderDAO.listOrderDetailInfos(orderId); orderInfo.setDetails(details);
		 * 
		 * model.addAttribute("orderInfo", orderInfo);
		 */
 
        return "order";
    }
    
}
package com.order.ms.app.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.order.ms.app.dto.Item;
import com.order.ms.app.dto.OrderDetail;
import com.order.ms.app.dto.Product;
import com.order.ms.app.models.Invoice;
import com.order.ms.app.repository.InvoiceRepository;
import com.order.ms.app.service.InvoiceService;
import com.order.ms.app.verify.VerifyTokenUtil;

@RestController
@RequestMapping("/orders")
public class OrderController {
	
    protected Logger logger = Logger.getLogger(OrderController.class
            .getName());
	
	@Autowired
	InvoiceRepository invoiceRepository;

	@Autowired
	InvoiceService invoiceService;

	VerifyTokenUtil verifyTokenUtil = new VerifyTokenUtil();

	@RequestMapping(value="/", method= RequestMethod.GET, produces = "application/json")
	public ResponseEntity<List<Invoice>> getAllInvoice( HttpServletRequest request){   
		ResponseEntity<List<Invoice>> productsResponseEntity = null;
		 if (verifyTokenUtil.verifyToken(request)) { 
			List<Invoice> allProducts = new ArrayList<>();
			Iterable<Invoice> findAll = invoiceRepository.findAll();
			findAll.forEach(product -> {
				allProducts.add(product);
			});
			System.out.println("---- "+allProducts);
			productsResponseEntity = ResponseEntity.ok(allProducts);
		
		  } else { productsResponseEntity = new
		  ResponseEntity<List<Invoice>>(HttpStatus.UNAUTHORIZED); }
		 
		return productsResponseEntity;
	}
	/**
	 * Get invoice details by ID
	 * @param id
	 * @return
	 */
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<OrderDetail> getInvoiceById(@PathVariable("id") String id,HttpServletRequest request) {
		ResponseEntity<OrderDetail> productsResponseEntity = null;
		 if (verifyTokenUtil.verifyToken(request)) { 
		productsResponseEntity = ResponseEntity.ok(invoiceService.findOrderDetailByInvoiceId(id));
		
		  } else { productsResponseEntity = new
		  ResponseEntity<OrderDetail>(HttpStatus.UNAUTHORIZED); }
		 
		return productsResponseEntity;
	}
	 
	/**
	 * Get invoice details by Customer ID
	 * @param id
	 * @return
	 */

	@RequestMapping(value = "/custid/{custid}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<List<Invoice>> getInvoiceByCustomer(@PathVariable("custid") String customerId,HttpServletRequest request) {
		logger.info("Findin invoices for Id " + customerId);
		System.out.println("Findin invoices for Id " + customerId);
		System.out.println("request is --- "+request.getHeaderNames());
		ResponseEntity<List<Invoice>> allInvoicesByCustId = null;
		 if (verifyTokenUtil.verifyToken(request)) { 
		allInvoicesByCustId = ResponseEntity.ok(invoiceRepository.findByCustomerId(Long.parseLong(customerId)));
		
		  } else { allInvoicesByCustId = new
		  ResponseEntity<List<Invoice>>(HttpStatus.UNAUTHORIZED); }
		 
		return allInvoicesByCustId;
	} 

	/*
	 * http://localhost:8300/orders/new
		POST JSON - 
    {
        "customerId": 2,
        "dateofPurChase": 1507660200000,
        "modePayId": "Online",
        "transactionId": "4532122323",
        "items": [
            {
                "itemId": 3,
                "quantity": 9,
                "productName": "Head and Shoulders shampoo",
                "totalCost": 100
            },
            {
                "itemId": 4,
                "quantity": 90,
                "productName": "Carrom Board Champion Edition",
                "totalCost": 1100
            }
        ]
    }
	 */
	// check if the Inventory has the right quantity
	
	@RequestMapping(value = "/new", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public ResponseEntity<OrderDetail> createOrder(@RequestBody OrderDetail orderDetail, HttpServletRequest request) {
		System.out.println("Order Detail Request is ----"+orderDetail);
		ResponseEntity<OrderDetail> orderDetails = null;
		if (verifyTokenUtil.verifyToken(request)) {
			if (invoiceService.createInvoiceAndOrder(orderDetail)) {
				orderDetails = ResponseEntity.ok(orderDetail);
			}

		} else {
			orderDetails = new ResponseEntity<OrderDetail>(HttpStatus.UNAUTHORIZED);
		}

		return orderDetails;
	}
}

package com.customer.ms.app.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.customer.ms.app.client.OrderServiceClient;
import com.customer.ms.app.dto.CustomerOrders;
import com.customer.ms.app.dto.InventoryCheckResultDTO;
import com.customer.ms.app.dto.Invoice;
import com.customer.ms.app.dto.OrderDetail;
import com.customer.ms.app.models.Customer;
import com.customer.ms.app.repository.CustomerRepository;
import com.customer.ms.app.services.InventoryService;
import com.customer.ms.app.services.InvoiceService;
import com.customer.ms.app.verify.VerifyTokenUtil;


@RestController
public class CustomerController {
    protected Logger logger = Logger.getLogger(CustomerController.class
            .getName());
	
	
	@Autowired
	CustomerRepository customerRepository;

	@Autowired
	InvoiceService invoiceService;

	@Autowired
	OrderServiceClient invoiceServiceClient;
	
	@Autowired
	InventoryService inventoryService;
	
	VerifyTokenUtil verifyTokenUtil = new VerifyTokenUtil();

	@RequestMapping(value="/customers", method= RequestMethod.GET, produces = "application/json")
	public ResponseEntity<List<Customer>> getAllCustomers(HttpServletRequest request){        
		
		ResponseEntity<List<Customer>> customersResponseEntity = null;
		if (verifyTokenUtil.verifyToken(request)) {
			List<Customer> allCustomers = new ArrayList<>();
			Iterable<Customer> findAll = customerRepository.findAll();
			findAll.forEach(customer -> {
				allCustomers.add(customer);
			});
			customersResponseEntity = ResponseEntity.ok(allCustomers);
		} else {
			customersResponseEntity = new ResponseEntity<List<Customer>>(HttpStatus.UNAUTHORIZED);
		}
		return customersResponseEntity;
		
	}

	@RequestMapping(value="/customers/{id}", method= RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Customer> getCustomerById(@PathVariable("id") String id, HttpServletRequest request){        
		ResponseEntity<Customer> customerResponseEntity = null;
		if (verifyTokenUtil.verifyToken(request)) {
			Customer customer = customerRepository.findOne(Long.parseLong(id));
			customerResponseEntity = new ResponseEntity<Customer>(customer, HttpStatus.OK);
		} else {
			customerResponseEntity = new ResponseEntity<Customer>(HttpStatus.UNAUTHORIZED);
		}
		return customerResponseEntity;
	}

	@RequestMapping(value="/customers/name/{name}", method= RequestMethod.GET, produces = "application/json")
	public List<Customer> getCustomerByName(@PathVariable("name") String customerName){        
		return customerRepository.findByCustomerName(customerName);
	}

	@RequestMapping(value="/customers/new/", method= RequestMethod.POST, produces = "application/json",consumes = "application/json")
	public Customer addCustomer(@RequestBody Customer customer){
		return customerRepository.save(customer);
	}

	@RequestMapping(value="/customers/{id}/orders", method= RequestMethod.GET, produces = "application/json")
	public ResponseEntity<CustomerOrders> getCustomerOrders(@PathVariable("id") String id,HttpServletRequest request){
		//    System.out.println("Calling a load balanced Rest template");	
		//        return invoiceService.getInvoices(id);
		ResponseEntity<CustomerOrders> customerOrdersEntity = null;
		if (verifyTokenUtil.verifyToken(request)) {
		CustomerOrders customerOrders= new CustomerOrders();
		Customer customer = customerRepository.findOne(Long.parseLong(id));
		customerOrders.setCustomer(customer);
		logger.info("Calling feign client to get invoices"+id);
		List<Invoice> invoiceList =  invoiceServiceClient.getInvoices(id,request).getBody();
		customerOrders.setInvoiceList(invoiceList);
		customerOrdersEntity = new ResponseEntity<CustomerOrders>(customerOrders, HttpStatus.OK);
		} else {
			customerOrdersEntity = new ResponseEntity<CustomerOrders>(HttpStatus.UNAUTHORIZED);
		}
		return customerOrdersEntity;
	}
	



/**
 * http://localhost:2222/customers/order
 * {
"customerId":1,
"modePayId":2,
"cashierName":"hanuman",
"items":[
	{
		"itemId":2,
		"quantity":5,
		"unitCost":10

	}
	]
}
 * @param orderDetail
 * @return
 */
	//TODO - design for failure
	@RequestMapping(value="/customers/order", method= RequestMethod.POST, produces = "application/json", consumes = "application/json" )
	public OrderDetail placeOrder(@RequestBody OrderDetail orderDetail) {
	
		List<InventoryCheckResultDTO> inventoryCheckResultDTOs =	inventoryService.checkAvailability(orderDetail);
		for(InventoryCheckResultDTO in: inventoryCheckResultDTOs) {
			logger.info(in.toString());
		}
		// TODO - do something if the requested inventory is not there
		OrderDetail orderPlaced = invoiceService.createInvoice(orderDetail);
		inventoryService.updateInventory(orderPlaced);
		return orderPlaced;
	}	
}

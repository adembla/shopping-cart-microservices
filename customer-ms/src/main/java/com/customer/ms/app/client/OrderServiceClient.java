/**
 * 
 */
package com.customer.ms.app.client;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.customer.ms.app.controller.CustomerController;
import com.customer.ms.app.dto.Invoice;

import feign.Headers;

// for feign client we do not need @HystrixCommand
@FeignClient(name = "invoice-service",
fallback = OrderServiceClient.InvoiceClientFallback.class)
public interface OrderServiceClient {
	
     Logger logger = Logger.getLogger(CustomerController.class
            .getName());
	

	@RequestMapping(method = RequestMethod.GET, value = "/orders/custid/{custid}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
//	@Headers(value = {"Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJhdWQiOlsiY2FySW52ZW50b3J5Il0sInVzZXJfbmFtZSI6ImpvaG4iLCJzY29wZSI6WyJyZWFkIiwid3JpdGUiXSwiZXhwIjoxNTkxMDQ2ODk5LCJhdXRob3JpdGllcyI6WyJJTlZFTlRPUllfQUREIiwiSU5WRU5UT1JZX1ZJRVciXSwianRpIjoiMDk2YTk0MmItNDQ0Ny00ZTRjLTlkNzAtMDEwMjI5ZTdhNWY3IiwiY2xpZW50X2lkIjoiYXBwY2xpZW50In0.Yd6UiQMS8JV6m3qYonHT1e9do8XpdOAIjuXGjTRc4hA","auth_token: eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJhdWQiOlsiY2FySW52ZW50b3J5Il0sInVzZXJfbmFtZSI6ImpvaG4iLCJzY29wZSI6WyJyZWFkIiwid3JpdGUiXSwiZXhwIjoxNTkxMDQ2ODk5LCJhdXRob3JpdGllcyI6WyJJTlZFTlRPUllfQUREIiwiSU5WRU5UT1JZX1ZJRVciXSwianRpIjoiMDk2YTk0MmItNDQ0Ny00ZTRjLTlkNzAtMDEwMjI5ZTdhNWY3IiwiY2xpZW50X2lkIjoiYXBwY2xpZW50In0.Yd6UiQMS8JV6m3qYonHT1e9do8XpdOAIjuXGjTRc4hA"})
	ResponseEntity<List<Invoice>> getInvoices(@PathVariable("custid") String custid,HttpServletRequest request);

	@Component
	static class InvoiceClientFallback implements OrderServiceClient{
		@Override	
		public ResponseEntity<List<Invoice>> getInvoices(String custId,HttpServletRequest request) {
			List<Invoice> invoiceList = new ArrayList<Invoice>();
			logger.info("Service is down, please try later");
			return ResponseEntity.ok(invoiceList);
		}
	}
}




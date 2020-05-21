package com.products.ms.app.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.products.ms.app.dto.InventoryDetailDTO;
import com.products.ms.app.models.Product;
import com.products.ms.app.repository.ProductRepository;
import com.products.ms.app.service.ProductService;
import com.products.ms.app.verify.VerifyTokenUtil;

@RestController
@RequestMapping("/products")
public class ProductController {

	@Autowired
	ProductRepository inventoryRepository;

	@Autowired
	ProductService inventoryService;

	VerifyTokenUtil verifyTokenUtil = new VerifyTokenUtil();

	@GetMapping(produces = "application/json")
	public ResponseEntity<List<Product>> getAllinventory(HttpServletRequest request) {
		ResponseEntity<List<Product>> productsResponseEntity = null;
		if (verifyTokenUtil.verifyToken(request)) {
			List<Product> allProducts = new ArrayList<>();
			Iterable<Product> findAll = inventoryRepository.findAll();
			findAll.forEach(product -> {
				allProducts.add(product);
			});
			productsResponseEntity = ResponseEntity.ok(allProducts);
		} else {
			productsResponseEntity = new ResponseEntity<List<Product>>(HttpStatus.UNAUTHORIZED);
		}
		return productsResponseEntity;
	}

	@GetMapping(value = "/{id}", produces = "application/json")
	public ResponseEntity<Product> getInventoryById(@PathVariable("id") String id, HttpServletRequest request) {
		ResponseEntity<Product> productResponseEntity = null;
		if (verifyTokenUtil.verifyToken(request)) {
			Product product = inventoryRepository.findOne(Long.parseLong(id));
			productResponseEntity = new ResponseEntity<Product>(product, HttpStatus.OK);
		} else {
			productResponseEntity = new ResponseEntity<Product>(HttpStatus.UNAUTHORIZED);
		}
		return productResponseEntity;
	}

	// @RequestMapping(value="/inventory/name/{name}", method= RequestMethod.GET,
	// produces = "application/json")
	// public List<Inventory> getInventoryByName(@PathVariable("name") String
	// InventoryName){
	// return inventoryRepository.findByInventoryName(InventoryName);
	// }

	@RequestMapping(value = "/inventory/new/", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public Product addInventory(@RequestBody Product inventory) {
		return inventoryRepository.save(inventory);
	}

	/**
	 * localhost:3333/inventory/check [{ "inventoryId":1, "quantity":9 }, {
	 * "inventoryId":2, "quantity":200 }]
	 * 
	 * @param inventoryDetailDTOList
	 * @return
	 */
	@RequestMapping(value = "/inventory/check", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public List<InventoryDetailDTO> checkInventory(@RequestBody List<InventoryDetailDTO> inventoryDetailDTOList) {
		return inventoryService.checkForInventory(inventoryDetailDTOList);
	}

	/**
	 * localhost:3333/inventory/update [{ "inventoryId":1, "quantity":10 }, {
	 * "inventoryId":2, "quantity":10 }, { "inventoryId":3, "quantity":10 } ]
	 * 
	 * @param inventoryDetailDTOList
	 * @return
	 */

	@RequestMapping(value = "/inventory/update", method = RequestMethod.PUT, produces = "application/json", consumes = "application/json")
	public List<InventoryDetailDTO> updateInventory(@RequestBody List<InventoryDetailDTO> inventoryDetailDTOList) {
		return inventoryService.updateInventory(inventoryDetailDTOList);
	}

}

/**
 * 
 */
package com.products.ms.app.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.products.ms.app.dto.InventoryDetailDTO;
import com.products.ms.app.models.Product;
import com.products.ms.app.repository.ProductRepository;

/**
 * @author hitjoshi
 *
 */
@Service
public class ProductService {
	@Autowired
	ProductRepository inventoryRepository;

	public List<InventoryDetailDTO> checkForInventory(List<InventoryDetailDTO> inventoryDetailDTOList) {
		List<Long> idList = new ArrayList<>();
		Map<Long, InventoryDetailDTO> map = new HashMap<>();
		
		for(InventoryDetailDTO inventoryDetailDTO: inventoryDetailDTOList) {
			idList.add(inventoryDetailDTO.getInventoryId());
			map.put(inventoryDetailDTO.getInventoryId(), inventoryDetailDTO);
		}
		Iterable<Product> inventoryList = inventoryRepository.findAll(idList);
		List<InventoryDetailDTO> inventoryDetailDTOResultList = new ArrayList<>();
		for(Product inventory: inventoryList) {
			InventoryDetailDTO  inventoryDetailDTO = map.get(inventory.getId());
			inventoryDetailDTO.setUnitPrice(inventory.getUnitPrice());
			inventoryDetailDTOResultList.add(inventoryDetailDTO);
		}
		return inventoryDetailDTOResultList;
	}

	public List<InventoryDetailDTO> updateInventory(List<InventoryDetailDTO> inventoryDetailDTOList) {
		List<Long> idList = new ArrayList<>();
		Map<Long, InventoryDetailDTO> map = new HashMap<>();
		for(InventoryDetailDTO inventoryDetailDTO: inventoryDetailDTOList) {
			idList.add(inventoryDetailDTO.getInventoryId());
			map.put(inventoryDetailDTO.getInventoryId(), inventoryDetailDTO);
		}
		Iterable<Product> inventoryList = inventoryRepository.findAll(idList);
		for(Product inventory: inventoryList) {
			InventoryDetailDTO inventoryDetailDTO = map.get(inventory.getId());
			}
		Iterable<Product> savedInventory= inventoryRepository.save(inventoryList);
       if(null !=savedInventory) return inventoryDetailDTOList;
       else return null;
	}

}

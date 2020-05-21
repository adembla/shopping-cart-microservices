package com.order.ms.app.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.order.ms.app.dto.Item;
import com.order.ms.app.dto.OrderDetail;
import com.order.ms.app.models.Items;
import com.order.ms.app.models.Invoice;
import com.order.ms.app.repository.InvoiceRepository;
import com.order.ms.app.repository.ItemsRepository;

@Service
public class InvoiceService {
	
    protected Logger logger = Logger.getLogger(InvoiceService.class
            .getName());
	
	@Autowired
	InvoiceRepository invoiceRepository;
	
	@Autowired
	ItemsRepository itemsRepository;
	
	@Autowired
	RestTemplate restTemplate;

	public Boolean createInvoiceAndOrder(OrderDetail orderDetail) {

		// get mode of pay first
//		ModeOfPay modeOfPay = modeOfPayRepository.findOne(orderDetail.getModePayId());
		Invoice invoice = new Invoice();
		invoice.setId(orderDetail.getInvoiceId());
		invoice.setCustomerId(orderDetail.getCustomerId());
		invoice.setModeOfPay(orderDetail.getModePayId());
		invoice.setId(orderDetail.getInvoiceId());
		invoice.setDateOfPurchase(orderDetail.getDateofPurChase()!=null?orderDetail.getDateofPurChase(): Calendar.getInstance().getTime());
		invoice.setTotalTaxAmount(orderDetail.getTotalCost());
		
		Invoice invoiceSaved = invoiceRepository.save(invoice);
		System.out.println("Saved invoice is --- "+invoiceSaved);
		if(null != invoiceSaved) {
			List<Item> itemList = orderDetail.getItems();
			List<Items> itemsEntityList = populateItemEntity(itemList,invoiceSaved);
			Iterable<Items> itemsList =  itemsRepository.save(itemsEntityList);
			if(null != itemsList) {
			return true;	
			}
		}
		return false;
	}
	
	
	public OrderDetail findOrderDetailByInvoiceId(String invoiceId) {
		Invoice invoice = invoiceRepository.findOne(Long.parseLong(invoiceId));
		List<Items> itemsList = itemsRepository.findByInvoiceId(invoice);
		System.out.println("Invoice is --- "+invoice);
		System.out.println("Items for that invoice are --- "+itemsList);
		OrderDetail orderDetail = populateOrderEntry(invoice, itemsList);
		return orderDetail;
	}
	 
	
	private List<Items> populateItemEntity(List<Item> itemList, Invoice invoice ){
		List<Items> itemEntityList = new ArrayList<>();
		for(Item item : itemList) {
			Items itemEntity = new Items();
			itemEntity.setInvoiceId(invoice);
			itemEntity.setProdId(item.getProductId());
			itemEntity.setQuantity(item.getQuantity());
			itemEntity.setUnitCost(item.getUnitCost());
			itemEntity.setTransactionId("ABCDE");
			itemEntityList.add(itemEntity);
		}
		return itemEntityList;
	}
	
	private OrderDetail populateOrderEntry(Invoice invoice, List<Items> itemsList) {
		OrderDetail  orderDetail = new OrderDetail();
		orderDetail.setCustomerId(invoice.getCustomerId());
		orderDetail.setDateofPurChase(invoice.getDateOfPurchase());
		orderDetail.setModePayId(invoice.getModeOfPay());
		orderDetail.setTotalCost(invoice.getTotalTaxAmount());
		List<Item> itemList = populateItemDTO(itemsList, invoice);
		orderDetail.setItems(itemList);
		return orderDetail;
	}
	
	private List<Item> populateItemDTO(List<Items> itemEntityList, Invoice invoice){
		List<Item> itemList = new ArrayList<>();
		for(Items itemEntity : itemEntityList) {
			Item item = new Item();
			item.setProductId(itemEntity.getProdId());
			item.setQuantity(itemEntity.getQuantity());
			item.setUnitCost(itemEntity.getUnitCost());
			itemList.add(item);
		}
		return itemList;
	}
}

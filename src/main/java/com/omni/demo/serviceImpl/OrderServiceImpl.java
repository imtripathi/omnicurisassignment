package com.omni.demo.serviceImpl;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.omni.demo.entity.Item;
import com.omni.demo.entity.OrderDetails;
import com.omni.demo.exception.OrderException;
import com.omni.demo.model.OrderRequest;
import com.omni.demo.model.ResponseMessage;
import com.omni.demo.repository.ItemRepository;
import com.omni.demo.repository.OrderRepository;
import com.omni.demo.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService{
@Autowired
private ItemRepository itemRepo;
@Autowired
private OrderRepository orderRepo;
@Transactional
	@Override
	public ResponseMessage createOrder(List<OrderRequest> orderRequest) throws Exception {
		ResponseMessage res= new ResponseMessage();
		if(orderRequest == null || orderRequest.isEmpty()) {
			throw new OrderException("At least one order item is required");
		}

		Map<Long, Item> itemsById = new LinkedHashMap<>();
		Map<Long, Integer> requestedQuantityByItemId = new LinkedHashMap<>();

		for(OrderRequest orderReq:orderRequest) {
			if(orderReq.getItemId() == null) {
				throw new OrderException("Item id is required to place an order");
			}
			if(orderReq.getQuantity() <= 0) {
				throw new OrderException("Order quantity should be greater than zero");
			}
			Item item = itemsById.get(orderReq.getItemId());
			if(item == null) {
				item = itemRepo.findById(orderReq.getItemId())
						.orElseThrow(() -> new OrderException("Item not found for id " + orderReq.getItemId()));
				itemsById.put(orderReq.getItemId(), item);
			}
			int requestedQuantity = requestedQuantityByItemId.getOrDefault(orderReq.getItemId(), 0) + orderReq.getQuantity();
			requestedQuantityByItemId.put(orderReq.getItemId(), requestedQuantity);
			if(item.getStock() < requestedQuantity) {
				throw new OrderException("Sufficient Stock not available for the Item order is being placed .please change the order");
			}
		}

		for(OrderRequest orderReq:orderRequest) {
			Item item=itemsById.get(orderReq.getItemId());
			int availableStock=item.getStock()-orderReq.getQuantity();
			item.setStock(availableStock);
			OrderDetails order =new OrderDetails();
			order.setEmailid(orderReq.getEmailId());
			order.setItemid(orderReq.getItemId());
			order.setQuantity(orderReq.getQuantity());
			order.setTotalamount(orderReq.getQuantity()*item.getPrice());
			orderRepo.save(order);
		}

		for(Item item:itemsById.values()) {
			itemRepo.save(item);
		}

		res.setSatus("Success");
		res.setMessage("Order has been placed Successfully");
		return res;
	}
	@Override
	public List<OrderDetails> getAllorder() {
		List<OrderDetails> orderDetails=new ArrayList<>();
		orderDetails=(List<OrderDetails>) orderRepo.findAll();
		return orderDetails;
	}
	

	

}

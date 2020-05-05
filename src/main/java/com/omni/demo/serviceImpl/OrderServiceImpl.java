package com.omni.demo.serviceImpl;

import java.util.ArrayList;
import java.util.List;

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
		try {
		for(OrderRequest orderReq:orderRequest) {
			Item item=new Item();
			item=itemRepo.findById(orderReq.getItemId()).get();
			if(item.getStock()<orderReq.getQuantity()) {
				throw new OrderException("Sufficient Stock not available for the Item order is being placed .please change the order");
			}
			int availableStock=item.getStock()-orderReq.getQuantity();
			item.setStock(availableStock);
			itemRepo.save(item);
			OrderDetails order =new OrderDetails();
			order.setEmailid(orderReq.getEmailId());
			order.setItemid(orderReq.getItemId());
			order.setQuantity(orderReq.getQuantity());
			order.setTotalamount(orderReq.getQuantity()*item.getPrice());
			orderRepo.save(order);
			
			
			
			
		}
		res.setSatus("Success");
		res.setMessage("Order has been placed Successfully");
		}
		catch(Exception e) {
			throw e;
		}
		return res;
	}
	@Override
	public List<OrderDetails> getAllorder() {
		List<OrderDetails> orderDetails=new ArrayList<>();
		orderDetails=(List<OrderDetails>) orderRepo.findAll();
		return orderDetails;
	}
	

	

}

package com.omni.demo.service;

import java.util.List;
import com.omni.demo.entity.OrderDetails;
import com.omni.demo.model.OrderRequest;
import com.omni.demo.model.ResponseMessage;

public interface OrderService {

	ResponseMessage createOrder(List<OrderRequest> orderRequest) throws Exception;

	List<OrderDetails> getAllorder();

	

	

}

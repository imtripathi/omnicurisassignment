package com.omni.demo.controller;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.omni.demo.entity.Item;
import com.omni.demo.entity.OrderDetails;
import com.omni.demo.model.OrderRequest;
import com.omni.demo.model.ResponseMessage;
import com.omni.demo.service.OrderService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(value = "orders")
@RestController
@RequestMapping("/orders")
public class OrderController {

	@Autowired
	private OrderService orderService;
	
	@ApiOperation(value = "creates multiple or single orders ", response = ResponseMessage.class)
	@ApiResponses(value = { 
	            @ApiResponse(code = 200, message = "Success|OK"),
	            @ApiResponse(code = 404, message = "not found!!!") })
	 
	@PutMapping(value = "/createorder",produces = "application/json")
	@Transactional
	public ResponseMessage createItem(@RequestBody List<OrderRequest> orderRequest) throws Exception {
		return orderService.createOrder(orderRequest);
	}
	
	
	@ApiOperation(value = "Gets All the order details", response = OrderDetails.class)
	@ApiResponses(value = { 
	            @ApiResponse(code = 200, message = "Success|OK"),
	            @ApiResponse(code = 404, message = "not found!!!") })
	 
	@GetMapping(value = "/getallorders",produces = "application/json")
	public List<OrderDetails> getAllItem() {
		return orderService.getAllorder();
	}
}

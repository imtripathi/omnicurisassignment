package com.omni.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.omni.demo.entity.Item;
import com.omni.demo.model.ItemRequest;
import com.omni.demo.model.ResponseMessage;
import com.omni.demo.service.ItemService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
@Api(value = "Item Controller")
@RestController
@RequestMapping("/items")
public class ItemController {
  
	@Autowired
	private ItemService itemService;
	
	@ApiOperation(value = "creates the Item ", response = Item.class)
	@ApiResponses(value = { 
	            @ApiResponse(code = 200, message = "Success|OK"),
	            @ApiResponse(code = 404, message = "not found!!!") })
	 
	@PutMapping(value = "/createitem",produces = "application/json")
	public Item createItem(@RequestBody ItemRequest itemRequest) {
		return itemService.createItem(itemRequest);
	}
	
	@ApiOperation(value = "Get the Item details on the basis of Id", response = Item.class)
	@ApiResponses(value = { 
	            @ApiResponse(code = 200, message = "Success|OK"),
	            @ApiResponse(code = 404, message = "not found!!!") })
	 
	@GetMapping(value = "/getitem/{id}",produces = "application/json")
	public Item getItem(@PathVariable(value = "id") Long id) {
		return itemService.getItem(id);
	}
	@ApiOperation(value = "update the Item details on the basis of Id", response = Item.class)
	@ApiResponses(value = { 
	            @ApiResponse(code = 200, message = "Success|OK"),
	            @ApiResponse(code = 404, message = "not found!!!") })
	@PostMapping(value = "/updateitem",produces = "application/json")
	public Item updateItem(@RequestBody Item item) {
		return itemService.updateItem(item);
	}
	@ApiOperation(value = "deletes the Item details on the basis of Id", response = ResponseMessage.class)
	@ApiResponses(value = { 
	            @ApiResponse(code = 200, message = "Success|OK"),
	            @ApiResponse(code = 404, message = "not found!!!") })
	@DeleteMapping(value = "/deleteitem/{id}",produces = "application/json")
	public  ResponseMessage deleteItem(@PathVariable(value = "id") Long id) {
		return itemService.deleteItem(id);
	}
	
	@ApiOperation(value = "Gets all the Item", response = Item.class)
	@ApiResponses(value = { 
	            @ApiResponse(code = 200, message = "Success|OK"),
	            @ApiResponse(code = 404, message = "not found!!!") })
	 
	@GetMapping(value = "/getallitem",produces = "application/json")
	public List<Item> getAllItem() {
		return itemService.getAllItem();
	}

}



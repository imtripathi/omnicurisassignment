package com.omni.demo.service;

import java.util.List;

import com.omni.demo.entity.Item;
import com.omni.demo.model.ItemRequest;
import com.omni.demo.model.ResponseMessage;

public interface ItemService {

	public Item createItem(ItemRequest itemRequest);

	public Item getItem(Long id);

	public Item updateItem(Item item);

	public ResponseMessage deleteItem(Long id);

	public List<Item> getAllItem();
}

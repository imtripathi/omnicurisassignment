package com.omni.demo.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.omni.demo.entity.Item;
import com.omni.demo.model.ItemRequest;
import com.omni.demo.model.ResponseMessage;
import com.omni.demo.repository.ItemRepository;
import com.omni.demo.service.ItemService;

@Service
public class ItemserviceImpl  implements ItemService{
	@Autowired
	private ItemRepository itemRepository;

	@Override
	public Item createItem(ItemRequest itemRequest) {
		Item item=new Item();
		item.setBrand(itemRequest.getBrand());
		item.setItemname(itemRequest.getItemname());
		item.setPrice(itemRequest.getPrice());
		item.setStock(itemRequest.getStock());
		item=itemRepository.save(item);
		return item;
	}

	@Override
	public Item getItem(Long id) {
		Item item= new Item();
		item=itemRepository.findById(id).get();
		return item;
	}

	@Override
	public Item updateItem(Item item) {
		item =itemRepository.save(item);
		return item;
	}

	@Override
	public ResponseMessage deleteItem(Long id) {
		itemRepository.deleteById(id);
		ResponseMessage res= new ResponseMessage();
		res.setMessage("anans");
		return res;
	}

	@Override
	public List<Item> getAllItem() {
		List<Item> listItem = new ArrayList<>();
		listItem=(List<Item>) itemRepository.findAll();
		return listItem;
	}

}

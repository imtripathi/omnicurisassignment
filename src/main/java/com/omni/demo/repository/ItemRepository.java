package com.omni.demo.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.omni.demo.entity.Item;
@Repository
public interface ItemRepository extends CrudRepository<Item,Long>{

	 
}

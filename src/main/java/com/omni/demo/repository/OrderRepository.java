package com.omni.demo.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.omni.demo.entity.OrderDetails;
@Repository
public interface OrderRepository extends CrudRepository<OrderDetails,Long>{

}

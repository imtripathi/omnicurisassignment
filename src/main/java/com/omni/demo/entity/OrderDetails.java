package com.omni.demo.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class OrderDetails {
	@Id
    @GeneratedValue
    private long orderid;
	private long itemid;
	private int quantity;
	private String emailid;
	private long totalamount;
	public long getOrderid() {
		return orderid;
	}
	public void setOrderid(long orderid) {
		this.orderid = orderid;
	}
	public Long getItemid() {
		return itemid;
	}
	public void setItemid(Long itemid) {
		this.itemid = itemid;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public String getEmailid() {
		return emailid;
	}
	public void setEmailid(String emailid) {
		this.emailid = emailid;
	}
	public long getTotalamount() {
		return totalamount;
	}
	public void setTotalamount(long totalamount) {
		this.totalamount = totalamount;
	}
	
}

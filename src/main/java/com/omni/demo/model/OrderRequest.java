package com.omni.demo.model;

public class OrderRequest {
private Long itemId;
private int quantity;
private String emailId;
public Long getItemId() {
	return itemId;
}
public void setItemId(Long itemId) {
	this.itemId = itemId;
}
public int getQuantity() {
	return quantity;
}
public void setQuantity(int quantity) {
	this.quantity = quantity;
}
public String getEmailId() {
	return emailId;
}
public void setEmailId(String emailId) {
	this.emailId = emailId;
}

}

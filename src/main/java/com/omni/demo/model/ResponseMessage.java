package com.omni.demo.model;

import java.io.Serializable;

public class ResponseMessage implements Serializable {
	
private String Satus;
private String message;
public String getSatus() {
	return Satus;
}
public void setSatus(String satus) {
	Satus = satus;
}
public String getMessage() {
	return message;
}
public void setMessage(String message) {
	this.message = message;
}
@Override
public String toString() {
	return "ResponseMessage [Satus=" + Satus + ", message=" + message + "]";
}


}

package com.zee.zee5app.exceptions;

public class IdNotFoundException extends Exception {

	//tostring method
	//super call
	public IdNotFoundException(String msg) {
		super(msg);
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.getMessage(); 
	}
}

package com.capgemini.onlinefoodordersys.exception;

public class IdNotFoundException extends RuntimeException {
	public IdNotFoundException(String errorMsg){
		super(errorMsg);
	}
}
package com.capgemini.onlinefoodordersys.exception;

public class OrderException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9143182537087549338L;
	
	public OrderException(String msg) {
		super(msg);
	}

}

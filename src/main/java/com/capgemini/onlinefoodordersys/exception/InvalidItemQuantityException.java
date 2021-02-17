package com.capgemini.onlinefoodordersys.exception;

public class InvalidItemQuantityException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public InvalidItemQuantityException(String msg) {
		super(msg);
	}
}

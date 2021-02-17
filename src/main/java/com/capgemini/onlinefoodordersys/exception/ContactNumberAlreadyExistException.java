package com.capgemini.onlinefoodordersys.exception;

public class ContactNumberAlreadyExistException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1062613650837156455L;
	public ContactNumberAlreadyExistException(String msg) {
		super(msg);
	}

}

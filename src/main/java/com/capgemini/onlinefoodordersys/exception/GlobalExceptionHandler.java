package com.capgemini.onlinefoodordersys.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@ControllerAdvice
public class GlobalExceptionHandler {

	@ResponseStatus(code = HttpStatus.ALREADY_REPORTED,reason = "Email already exist")
	@ExceptionHandler(EmailAlreadyExistsException.class)
	public void handleEmailAlreadyExistException()
	{
	//TO handle Email already exist
	}
	
	@ResponseStatus(code = HttpStatus.ALREADY_REPORTED,reason = "Contact number already exist")
	@ExceptionHandler(ContactNumberAlreadyExistException.class)
	public void handleContactNumberAlreadyExistException()
	{
	//TO handle Contact number already exist
	}
	
	@ResponseStatus(code = HttpStatus.UNAUTHORIZED,reason = "Unauthorized Access")
	@ExceptionHandler(UnauthorizedException.class)
	public void UnauthorizedException()
	{
	//TO handle unauthorized exception
	}
	
	
//	@ResponseStatus(code = HttpStatus.ALREADY_REPORTED,reason = "Insufficient Wallet Balance!!")
//	@ExceptionHandler(InsufficientBalanceexception.class)
//	public void InsufficientBalanceexception()
//	{
//	//TO handle unauthorized exception
//	}
	
	
}

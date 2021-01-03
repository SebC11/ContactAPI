package com.example.ContactAPI;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

public class ContactNotFoundDescription {
	
	@ResponseBody
	@ExceptionHandler(ContactNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	String employeeNotFoundHandler(ContactNotFoundException ex) {
	  return ex.getMessage();
	}
}
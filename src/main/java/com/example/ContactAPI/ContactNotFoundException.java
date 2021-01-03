package com.example.ContactAPI;

@SuppressWarnings("serial")
public class ContactNotFoundException extends RuntimeException{
	
	ContactNotFoundException(Long id){
		super("Could not find employee '" + id + "'");
	}
}

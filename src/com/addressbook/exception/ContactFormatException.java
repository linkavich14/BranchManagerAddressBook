package com.addressbook.exception;

public class ContactFormatException extends Exception{

	private static final long serialVersionUID = 1L;
	
	public ContactFormatException(String message) {
		super(message);
	}

}

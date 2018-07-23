package com.addressbook.exception;

public class AddressBookFormatException extends Exception{

	private static final long serialVersionUID = 1L;
	
	public AddressBookFormatException(String message) {
		super(message);
	}
}

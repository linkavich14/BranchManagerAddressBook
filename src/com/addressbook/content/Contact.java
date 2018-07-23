package com.addressbook.content;

import java.util.HashSet;
import java.util.Set;

import com.addressbook.exception.ContactFormatException;

/**
 * <p>Contact class has a name and phone numbers associated to it.
 * Each contact can have many phone numbers associated to it, but cannot be duplicated</p>
 * @author Juan Hernandez
 *
 */
public class Contact {
	
	private final Set<String> contactPhoneNumbers = new HashSet<>();
	private String contactName;

	public Contact(String contactName) throws ContactFormatException {		
		if (isValidContactName(contactName)) {
			this.contactName = contactName;
		}		
	}
	
	public String getContactName() {
		return contactName;
	}
	
	public boolean setContactName(String name) throws ContactFormatException {
		if (isValidContactName(name)) {
			this.contactName = name;
			return true;
		}
		return false;
	}	
	
	public Set<String> getContactPhoneNumbers(){
		return contactPhoneNumbers;
	}

	public void addContactNumber(String number) throws Exception {
		if (number == null) {
			throw new ContactFormatException("Null phone number");
		}
		
		if (number.isEmpty()) {
			throw new NumberFormatException("Invalid amount of numbers");
		}
		
		if (isValidContactNumber(number)) {
			contactPhoneNumbers.add(number);
		}else {
			throw new NumberFormatException("Invalid characters in the contact number");
		}		
	}
	
	/**
	 * Check if every digit is a valid number
	 * @param number given number
	 * @return 
	 */
	public boolean isValidContactNumber(String number) {		
		for (int i = 0; i < number.length(); i++) {
			if (!Character.isDigit(number.charAt(i))) {
				return false;
			}
		}		
		return true;
	}
	
	private boolean isValidContactName(String contactName) throws ContactFormatException{
		if (contactName == null) {
			throw new ContactFormatException("Null contact name");
		}
		
		if (contactName.isEmpty()) {
			throw new ContactFormatException("Empty contact name");
		}
		
		return true;
	}
}

package com.addressbook.content;

import java.util.HashSet;
import java.util.Set;
import com.addressbook.exception.AddressBookFormatException;
import com.addressbook.exception.ContactFormatException;

/**
 * <p>Address book class has a name and contacts associated to it.
 * Cannot have an empty name, but it can have no contacts
 * </p>
 * @author Juan Hernandez
 *
 */
public class AddressBook {
	
	private final Set<Contact> contactsBook = new HashSet<>();
	private String addressBookName;
	
	public AddressBook(String name) throws AddressBookFormatException {		
		if (isAddressBookNameValid(name)) {
			this.addressBookName = name;
		}				
	}
	
	/**
	 * Add a contact to the address book
	 * @param contact
	 * @return true if the given contact does not exists in the address book and has a valid number associated to it.
	 * @throws Exception
	 */
	public boolean addNewContact(Contact contact) throws Exception {
		if (contactExists(contact)) {			
			return false;
		}else if(contact.getContactPhoneNumbers().isEmpty()) {
			throw new ContactFormatException("Empty contact phone number");
		}else {
			contactsBook.add(contact);
			return true;
		}		
	}
	
	public Set<Contact> getContactsBook(){
		return contactsBook;
	}	
	
	/**
	 * Remove a contact from the address book
	 * @param contact
	 * @return true if the given contact exists in the set and was removed successfully
	 */
	public boolean removeContact(Contact contact) {
		return contactsBook.removeIf(p -> p.getContactName().equals(contact.getContactName()));
	}
	
	/**
	 * Prints all contacts with its respective number, it will print the contact name with each phone number
	 * @return true if the contact book contains at least one contact
	 */
	public boolean printAllContacts() {
		if (contactsBook.size() > 0) {						
			contactsBook.forEach(p -> p.getContactPhoneNumbers().stream().sorted().forEach(q -> System.out.println(p.getContactName() + " " + q)));
			return true;
		}				
		return false;		
	}
	
	public boolean contactExists(Contact contact) {				
		return contactsBook.stream().anyMatch(p -> p.getContactName().equals(contact.getContactName()));						
	}

	public String getAddressBookName() {		
		return addressBookName;
	}
	
	private boolean isAddressBookNameValid(String name) throws AddressBookFormatException{
		if (name == null) {
			throw new AddressBookFormatException("Null name Address Book");
		}
		
		if (name.isEmpty()) {
			throw new AddressBookFormatException("Empty address book name");
		}
		
		return true;
	}
}

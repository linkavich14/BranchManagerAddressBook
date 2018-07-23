package com.addressbook.content;

import java.util.HashSet;
import java.util.Set;

/**
 * <p>ContentManager class contains all the address books and unique address book.</p>
 * @author Juan Hernandez
 *
 */
public class ContentManager {
	
	private final Set<AddressBook> addressBooks = new HashSet<>();
	private AddressBook uniqueAddressesBook;
	
	public boolean addNewAddressBook(AddressBook contactAddressBook) {	
		if (addressBookExists(contactAddressBook)) {
			return false;
		} else {
			addressBooks.add(contactAddressBook);
			return true;
		}		
	}
	
	public boolean printUniqueSetAddressesBook() throws Exception {
		uniqueAddressesBook = new AddressBook("temp");
		if (!addressBooks.isEmpty()) {
			addressBooks.forEach(p -> addAddressBook(p, uniqueAddressesBook));
			uniqueAddressesBook.getContactsBook().stream().forEach(q ->  System.out.println(q.getContactName()));;
			return true;
		}
		return false;
	}
	
	private AddressBook addAddressBook(AddressBook addressBook, AddressBook temp) {
		addressBook.getContactsBook().stream().forEach(p -> {
			try {
				temp.addNewContact(p);
			} catch (Exception e) {
				throw new IllegalArgumentException();
			}
		});		
		return temp;		
	}
	
	private boolean addressBookExists(AddressBook newBook) {
		return addressBooks.stream().anyMatch(p -> p.getAddressBookName().equals(newBook.getAddressBookName()));
	}

	public boolean removeAddressBook(AddressBook contactAddressBook) {
		return addressBooks.removeIf(p -> p.getAddressBookName().equals(contactAddressBook.getAddressBookName()));		
	}

	public Set<AddressBook> getAdressesBooks() {		
		return addressBooks;
	}
	
	public AddressBook getUniqueAddressBookSet() {
		return uniqueAddressesBook;
	}

}

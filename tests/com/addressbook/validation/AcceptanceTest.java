package com.addressbook.validation;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import com.addressbook.content.AddressBook;
import com.addressbook.content.Contact;
import com.addressbook.content.ContentManager;
import com.addressbook.exception.AddressBookFormatException;
import com.addressbook.exception.ContactFormatException;

/**
 * <p>Test cases for the address book manager application.
 * For the project assumptions, please go to the README file
 * </p>
 * @author Juan Hernandez
 *
 */
public class AcceptanceTest {
	
	private AddressBook contactAddressBook;
	private ContentManager contentManager;
	private Contact firstContact;
	
	/**
	 * Adds a first contact and address book to the content manager
	 * @throws Exception
	 */
	@Before
	public void setUpContact() throws Exception {
		firstContact = new Contact("FirstContact");
		firstContact.addContactNumber("1");
		contactAddressBook = new AddressBook("FirstTestBook");
		contactAddressBook.addNewContact(firstContact);
		contentManager = new ContentManager();
		contentManager.addNewAddressBook(contactAddressBook);
	}
	
	/**
	 * Checks that the new contact is added successfully
	 * @throws Exception
	 */
	@Test
	public void checkAddNewContact() throws Exception {
		Contact contact = new Contact("testName");
		contact.addContactNumber("0");
		contact.addContactNumber("0");
		contact.addContactNumber("0");
		AddressBook addressBook = new AddressBook("TestBook");
		boolean result = addressBook.addNewContact(contact);
		assertTrue(result);
		assertEquals(addressBook.getContactsBook().size(), 1);
		assertTrue(addressBook.getContactsBook().contains(contact));
		assertEquals(contact.getContactPhoneNumbers().size(), 1);
	}		
	
	/**
	 * Checks if the new contact to be added already exists, 
	 * please refer to the READNE file for duplicated contacts definition
	 * @throws Exception
	 */
	@Test
	public void checkDuplicatedContact() throws Exception{	
		Contact contact = new Contact("FirstContact");
		contact.addContactNumber("1");
		boolean result = contactAddressBook.addNewContact(contact);
		assertFalse(result);
		assertEquals(contactAddressBook.getContactsBook().size(), 1);
		assertFalse(contactAddressBook.getContactsBook().contains(contact));
	}
	
	/**
	 * Checks for invalid data in the contact number
	 * @throws Exception
	 */
	@Test(expected = NumberFormatException.class)
	public void checkAddInvalidNewContactNumber() throws Exception {
		Contact contact = new Contact("testName");
		contact.addContactNumber("wrong");
	}
	
	/**
	 * Checks for null data in the contact number
	 * @throws Exception
	 */
	@Test(expected = ContactFormatException.class)
	public void checkAddNullNewContactNumber() throws Exception {
		Contact contact = new Contact("testName");
		contact.addContactNumber(null);
	}
	
	/**
	 * Checks for invalid data in the contact name
	 * @throws Exception
	 */
	@Test(expected = ContactFormatException.class)
	public void checkAddInvalidNewContactName() throws Exception {
		new Contact("");		
	}
	
	/**
	 * Checks for null data in the contact name
	 * @throws Exception
	 */
	@Test(expected = ContactFormatException.class)
	public void checkAddNullNewContactName() throws Exception {
		new Contact(null);
	}
	
	/**
	 * Removes a valid contact
	 * @throws Exception
	 */
	@Test
	public void removeContact() throws Exception {
		Contact contact = new Contact("FirstContact");
		contact.addContactNumber("1");
		boolean result = contactAddressBook.removeContact(contact);
		assertTrue(result);			
		assertEquals(contactAddressBook.getContactsBook().size(), 0);
		assertFalse(contactAddressBook.getContactsBook().contains(contact));
	}
	
	/**
	 * Checks removing an invalid contact
	 * @throws Exception
	 */
	@Test
	public void removeInvalidContact() throws Exception {
		Contact contact = new Contact("testName");
		contact.addContactNumber("0");
		boolean result = contactAddressBook.removeContact(contact);
		assertFalse(result);
		assertEquals(contactAddressBook.getContactsBook().size(), 1);
		assertFalse(contactAddressBook.getContactsBook().contains(contact));
	}
	
	@Test
	public void printContactAddressBook() throws Exception {
		Contact contact = new Contact("testName");
		contact.addContactNumber("0");
		contactAddressBook.addNewContact(contact);
		boolean result = contactAddressBook.printAllContacts();
		assertTrue(result);
		assertEquals(contactAddressBook.getContactsBook().size(), 2);
		assertTrue(contactAddressBook.getContactsBook().contains(contact));
	}
	
	@Test
	public void checkPrintEmptyContactAddressBook() throws Exception {
		AddressBook addressBook = new AddressBook("TestBook");
		boolean result = addressBook.printAllContacts();
		assertFalse(result);		
		assertEquals(addressBook.getContactsBook().size(), 0);
	}
	
	@Test
	public void addAddressBook() throws Exception {
		AddressBook addressBook = new AddressBook("TestBook");
		ContentManager contentManager = new ContentManager();
		boolean result = contentManager.addNewAddressBook(addressBook);
		assertTrue(result);
		assertEquals(contentManager.getAdressesBooks().size(), 1);		
	}
	
	/**
	 * Checks for invalid data in the address book name
	 * @throws Exception
	 */
	@Test(expected = AddressBookFormatException.class)
	public void addInvalidAddressBook() throws Exception {
		new AddressBook("");
	}
	
	/**
	 * Checks for null data in the address book name
	 * @throws Exception
	 */
	@Test(expected = AddressBookFormatException.class)
	public void addNullAddressBook() throws Exception {
		new AddressBook(null);
	}
	
	@Test
	public void checkDuplicateddAddressBook() throws Exception {
		AddressBook addressBook = new AddressBook("FirstTestBook");
		boolean result = contentManager.addNewAddressBook(addressBook);
		assertFalse(result);
		assertEquals(contentManager.getAdressesBooks().size(), 1);	
		assertFalse(contentManager.getAdressesBooks().contains(addressBook));
	}
	
	@Test
	public void removeAddressBook() throws Exception {
		boolean result = contentManager.removeAddressBook(contactAddressBook);
		assertTrue(result);
		assertEquals(contentManager.getAdressesBooks().size(), 0);	
	}
	
	@Test
	public void printDuplicatedContactMultipleAddressBooks() throws Exception {
		//First Address Book
		Contact contact = new Contact("SecondContact");
		contact.addContactNumber("2");
		contactAddressBook.addNewContact(contact);		
		Contact contact2 = new Contact("DuplicatedContact");
		contact2.addContactNumber("4");
		contactAddressBook.addNewContact(contact2);
		
		//Second Address Book
		AddressBook secondAddressBook = new AddressBook("SecondTestBook");
		Contact firstContact = new Contact("NewBookFirstContact");
		firstContact.addContactNumber("3");
		firstContact.addContactNumber("5");
		firstContact.addContactNumber("6");
		secondAddressBook.addNewContact(firstContact);		
		Contact secondContact = new Contact("DuplicatedContact");
		secondContact.addContactNumber("4");
		secondAddressBook.addNewContact(secondContact);		
		contentManager.addNewAddressBook(secondAddressBook);
		
		//Third Address Book
		AddressBook thirdAddressBook = new AddressBook("ThirdTestBook");
		Contact contactThird = new Contact("ThirdContact");
		contactThird.addContactNumber("5");
		thirdAddressBook.addNewContact(contactThird);
		Contact contactThird2 = new Contact("DuplicatedContact");
		contactThird2.addContactNumber("4");
		thirdAddressBook.addNewContact(contactThird2);
		
		contentManager.addNewAddressBook(thirdAddressBook);
				
		boolean result = contentManager.printUniqueSetAddressesBook();
		assertTrue(result);
		assertEquals(contentManager.getAdressesBooks().size(), 3);
		assertEquals(contactAddressBook.getContactsBook().size(), 3);
		assertEquals(secondAddressBook.getContactsBook().size(), 2);
		assertEquals(thirdAddressBook.getContactsBook().size(), 2);
		assertEquals(contentManager.getUniqueAddressBookSet().getContactsBook().size(), 5);
	}

}

# BranchManagerAddressBook

As a Reece Branch Manager I would like an address book application on my PC So that I can keep track of my customer contacts

Acceptance Criteria
 - Address book will hold name and phone numbers of contact entries

 - Users should be able to add new contact entries

 - Users should be able to remove existing contact entries

 - Users should be able to print all contacts in an address book

 - Users should be able to maintain multiple address books

 - Users should be able to print a unique set of all contacts across multiple address book
 
# How to run it

The test cases are found in the tests folder, run the AcceptanceTest class as JUnit test

# Architecture reason

There is no need for a more complex architecture for the given problem, if the user wants to increase the functionality, for instance different addresses, 
different email accounts, etc. They would be add as another container of objects with the same rules 

# Assumptions 

 - Contact name cannot be empty but can contain numbers and/or letters.
 - Contact number cannot be empty and cannot contain any letters and/or special characters, however it can contain only one digit.
 - Contact cannot have duplicated contact numbers associated to it.
 - An address book can be empty and still be added, as long as it has a name.
 - Maintain is defined as adding and removing entries.
 - A duplicated contact and a duplicated Address Book will be considered duplicated only when they have the same name.

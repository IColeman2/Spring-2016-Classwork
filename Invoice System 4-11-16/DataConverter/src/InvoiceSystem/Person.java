/**
*I Coleman, Sarah Kenny, Nisha Rao
*icoleman@huskers.unl.edu / sarah.kenny@huskers.unl.edu / nisha.bookworm@gmail.com
*2/12/16
*Assignment 2
*/
package InvoiceSystem;

import java.util.ArrayList;

public class Person {
    
    private String personCode;
    private String firstName;
    private String lastName;
    private Address address;
    private ArrayList<String> emailAddress;
    
    public Person(String code, String first, String last, Address a, ArrayList<String> email){
        this.personCode = code;
        this.firstName = first;
        this.lastName = last;
        this.address = a;
        this.emailAddress = email;
    }
    
    public static Person stringArrayToPerson(String[] tempArray){
    	//there are 8 things that will appear before emails.  the number of emails can vary
    	int numEmails = tempArray.length - 3;
    	//either change email to an array list or change nisha's code to a plain string array
    	ArrayList<String> email = new ArrayList<String>();
		String personCode = tempArray[0];
                String [] name = tempArray[1].split(",");
		String lastName = name[0];
		String firstName = name[1].trim();
		//create array of all emails.  May have to put an if statement for 0 emails bc it would be null
		for(int i = 0; i < numEmails; i++){
			email.add(tempArray[3 + i]);
		}
		String[] address = tempArray[2].split(",");
		Address a = new Address(address);
		Person result = new Person(personCode, firstName, lastName, a, email);
		return result;
    }
    
	//Getters and setters
    public String getPersonCode() {
		return personCode;
	}

	public void setPersonCode(String personCode) {
		this.personCode = personCode;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public ArrayList<String> getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(ArrayList<String> emailAddress) {
		this.emailAddress = emailAddress;
	}

	public void removeEmailAddress(String email){
        emailAddress.remove(email);
    }
	
	//Used to get a single string with a readable, nicely formatted name for the salesperson
	public String getFormattedPersonName(){
		String result = lastName+", "+firstName;
		return result;
	}
}

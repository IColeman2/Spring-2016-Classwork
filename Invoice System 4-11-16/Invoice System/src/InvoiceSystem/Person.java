/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package InvoiceSystem;

import java.util.ArrayList;

/**
 *
 * @author NishPC
 */
public class Person {
    
    private String personCode;
    private String firstName;
    private String lastName;
    private Address address;
    private ArrayList<String> emailAddress;
    
    public Person(String code, String first, String last, Address a, ArrayList<String> email){
        personCode = code;
        firstName = first;
        lastName = last;
        address = a;
        emailAddress = email;
    }
    
    public Person(String[] tempArray){
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
		Address a = new Address(tempArray[2]);
		
		Person personInstance = new Person(personCode, firstName, lastName, a, email);
    }
    
    public String getPersonCode(){
        return personCode;
    }
    
    public String getFirstName(){
        return firstName;
    }
    public String getLastName(){
        return lastName;
    }
    
    public Address getAddress(){
        return address;
    }
    
    public ArrayList<String> getEmail(){
        return emailAddress;
    }
    
    public void setEmailAddress(String email){
        emailAddress.add(email);
    }
    
    public void removeEmailAddress(String email){
        emailAddress.remove(email);
    }
}

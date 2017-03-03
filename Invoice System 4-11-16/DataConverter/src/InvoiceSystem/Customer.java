/**
*I Coleman, Sarah Kenny, Nisha Rao
*icoleman@huskers.unl.edu / sarah.kenny@huskers.unl.edu / nisha.bookworm@gmail.com
*2/12/16
*Assignment 2
*/
package InvoiceSystem;

public abstract class Customer {
    
    private String customerCode;
    private String customerName;
    private Person primaryContact;
    private Address customerAddress;
    private String customerType;
    
    public Customer(String code, String name, Person contact, Address add, String type){
        customerCode = code;
        customerName = name;
        primaryContact = contact;
        customerAddress = add;
        customerType = type;
    }

    //Getters and setters
	public String getCustomerCode() {
		return customerCode;
	}

	public void setCustomerCode(String customerCode) {
		this.customerCode = customerCode;
	}
	
	public String getCustomerType() {
		return customerType;
	}

	public void setCustomerType(String customerType) {
		this.customerType = customerType;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public Person getPrimaryContact() {
		return primaryContact;
	}

	public void setPrimaryContact(Person primaryContact) {
		this.primaryContact = primaryContact;
	}

	public Address getCustomerAddress() {
		return customerAddress;
	}

	public void setCustomerAddress(Address customerAddress) {
		this.customerAddress = customerAddress;
	}
}

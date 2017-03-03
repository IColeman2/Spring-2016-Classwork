package InvoiceSystem;

public class Corporate extends Customer {
	
	public Corporate(String code, String name, Person Contact, Address add){
		super(code, name, Contact, add, "Corporate");
	}
}

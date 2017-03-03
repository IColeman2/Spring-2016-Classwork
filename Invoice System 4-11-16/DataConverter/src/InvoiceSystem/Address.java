/**
*I Coleman, Sarah Kenny, Nisha Rao
*icoleman@huskers.unl.edu / sarah.kenny@huskers.unl.edu / nisha.bookworm@gmail.com
*2/12/16
*Assignment 2
*/
package InvoiceSystem;

public class Address {
    
    private String street;
    private String city;
    private String state;
    private String zipCode;
    private String country;
    
    public Address(String s, String c, String st, String zip, String co){
        street = s;
    	city = c;
    	state = st;
        zipCode = zip;
        country = co;
    }
    
    //Makes an address from a string array
    public Address(String[] tempArray){
    	this(tempArray[0], tempArray[1], tempArray[2], tempArray[3], tempArray[4]);
    }
    
    //Getters and setters
    public String getStreet(){
        return street;
    }
    
    public String getCity(){
        return city;
    }
    
    public String getState(){
        return state;
    }
    
    public String getZipCode(){
        return zipCode;
    }
    
    public String getCountry(){
        return country;
    }
    
    public void setStreet(String str){
        street = str;
    }
    
    public void setCity(String c){
        city = c;
    }
    
    public void setState(String s){
        state = s;
    }
    
    public void setZipCode(String zip){
        zipCode = zip;
    }
    
    public void setCountry(String c){
        country = c;
    }
}

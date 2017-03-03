/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package InvoiceSystem;

/**
 *
 * @author NishPC
 */
public class Address {
    
    private String street;
    private String city;
    private String state;
    private String zipCode;
    private String country;
    
    public Address(String add){
        String[] addressArray = add.split(",");
        street = addressArray[0];
        city = addressArray[1];
        state = addressArray[2];
        zipCode = addressArray[3];
        country = addressArray[4];
    }
    
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

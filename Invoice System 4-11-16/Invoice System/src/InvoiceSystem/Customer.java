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
public class Customer {
    
    private String customerCode;
    private String customerName;
    private Person primaryContact;
    private boolean government;
    private Address customerAddress;
    
    public Customer(String[] tempArray, Person contact, Address add){
        customerCode = tempArray[0];
        customerName = tempArray[3];
        primaryContact = contact;
        customerAddress = add;
        if(tempArray[1].equals("G")){
            government = true;
        }
        else{
            government = false;
        }
    }
    
    public String getCustomerCode(){
        return customerCode;
    }
    
    public String getCustomerName(){
        return customerName;
    }
    
    public Person getPrimaryContact(){
        return primaryContact;
    }
    
    public char getType(){
        if(government){
            return 'G';
        }
        else{
            return 'C';
        }
    }
    
    public Address getAddress(){
        return customerAddress;
    }
            
    public double totalCost(Equipment product, double cost){
        if(government){
            return 125 + cost;
        }
        else{
            return cost * 1.07;
        }
    }
    
    public double totalCost(Consultations product, double cost){
        if(government){
            return 125 + cost;
        }
        else{
                return (cost - 150) * 1.0425 + 150;
        }
    }
     
    public double totalCost(Licenses product, double cost){
        if(government){
            return 125 + cost;
        }
        else{
            return (cost - product.getFlatServiceFee()) * 1.0425 + product.getFlatServiceFee();
        }
    }
}

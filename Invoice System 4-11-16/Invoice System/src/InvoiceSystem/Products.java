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
public class Products {
    
    private String productCode;
    private String productName;
    private String productType;
    
    public Products(String code, String name, String type){
        productCode = code;
        productName = name;
        productType = type;
    }
    
    public String getProductCode(){
        return productCode;
    }
    
    public String getProductName(){
        return productName;
    }
    
    public String getProductType(){
        return productType;
    }
}
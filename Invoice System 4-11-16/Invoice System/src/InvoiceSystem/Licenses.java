/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package InvoiceSystem;

import java.util.Date;
/**
 *
 * @author NishPC
 */
public class Licenses extends Products{
    
    private double annualServiceFee;
    private double flatServiceFee;
    
    public Licenses(String[] tempArray){
        this(tempArray[0], tempArray[1], Double.parseDouble(tempArray[3]), Double.parseDouble(tempArray[2]));
        annualServiceFee = Double.parseDouble(tempArray[3]);
        flatServiceFee = Double.parseDouble(tempArray[2]);
    }
	
	public Liscenses(String code, String name, double annual, double flat){
		super(tempArray[0], tempArray[1], "Licenses");
		annualServiceFee = annual;
        flatServiceFee = flat;
	}
    
    public double getAnnualServiceFee(){
        return annualServiceFee;
    }
    
    public double getFlatServiceFee(){
        return flatServiceFee;
    }
    
    public double getTotalCost (Date beginning, Date ending){
        int days = (beginning.getTime()- ending.getTime()) * 86400000;
        return days * annualServiceFee + flatServiceFee;
    }
}
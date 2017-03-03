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
public class Consultations extends Products{
    
    private double perHourCost;
    
    public Consultations(String[] tempArray){
        this(tempArray[0], tempArray[1], Double.parseDouble(tempArray[2]));
    }
	
	public Consultions(String code, String name, double hourCost){
		super(code, name, "Consultations");
        perHourCost = hourCost;
	}
    
    public double getPerHourCost(){
        return perHourCost;
    }
    
    public double getTotalCost(int totalHours){
        return 150 + totalHours*perHourCost;
    }
    
}

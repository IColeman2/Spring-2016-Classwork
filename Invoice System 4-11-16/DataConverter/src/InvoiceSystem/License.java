/**
*I Coleman, Sarah Kenny, Nisha Rao
*icoleman@huskers.unl.edu / sarah.kenny@huskers.unl.edu / nisha.bookworm@gmail.com
*2/12/16
*Assignment 2
*/
package InvoiceSystem;

public class License extends Product{
    
    private double annualServiceFee;
    private double flatServiceFee;
    private final double TAX = 1.0425;
    
    public License(String code, String name, double flat, double annual){
        super(code, name, "License");
        annualServiceFee = annual;
        flatServiceFee = flat;
    }
    
    //Getters and setters
    public double getAnnualServiceFee() {
		return annualServiceFee;
	}

	public double getTax() {
		return TAX;
	}
	
	public double getFee(){
		return flatServiceFee;
	}

	public void setAnnualServiceFee(double annualServiceFee) {
		this.annualServiceFee = annualServiceFee;
	}

	public void setFlatServiceFee(double flatServiceFee) {
		this.flatServiceFee = flatServiceFee;
	}

	public double getTotalCost (int units){
        //int days = (int) ((endDate.getTime()- beginningDate.getTime()) / 86400000);
		int days = units;
        return days/365.0 * annualServiceFee;
    }
}
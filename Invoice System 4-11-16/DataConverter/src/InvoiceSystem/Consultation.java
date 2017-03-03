/**
*I Coleman, Sarah Kenny, Nisha Rao
*icoleman@huskers.unl.edu / sarah.kenny@huskers.unl.edu / nisha.bookworm@gmail.com
*2/12/16
*Assignment 2
*/
package InvoiceSystem;

public class Consultation extends Product{
    
    private double perHourCost;
    private Person consultant;
    private final double TAX = 1.045;
    private final double FEE = 150.0;

	public Consultation(String code, String name, double hourly, Person c){
        super(code, name, "Consultation");
        perHourCost = hourly;
        consultant = c;
	}

	//Getters and setters
    public double getPerHourCost() {
		return perHourCost;
	}


	public void setPerHourCost(double perHourCost) {
		this.perHourCost = perHourCost;
	}
	
    public double getTax() {
		return TAX;
	}

	public double getFee() {
		return FEE;
	}

	public Person getConsultant() {
		return consultant;
	}


	public void setConsultant(Person consultant) {
		this.consultant = consultant;
	}


	public double getTotalCost(int units){
        return units*perHourCost;
    }
    
}

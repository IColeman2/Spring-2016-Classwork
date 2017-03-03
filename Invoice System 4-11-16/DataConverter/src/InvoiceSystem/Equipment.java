/**
*I Coleman, Sarah Kenny, Nisha Rao
*icoleman@huskers.unl.edu / sarah.kenny@huskers.unl.edu / nisha.bookworm@gmail.com
*2/12/16
*Assignment 2
*/

package InvoiceSystem;

public class Equipment extends Product{
    
    private double unitCost;
    private final double TAX = 1.07;
    
    public Equipment(String code, String name, double cost){
       super(code, name, "Equipment");
       unitCost = cost;
    }
    
    //Getter and setter
    public double getUnitCost() {
		return unitCost;
	}

	public void setUnitCost(double unitCost) {
		this.unitCost = unitCost;
	}
	
	public double getFee(){
		return 0;
	}

	public double getTax() {
		return TAX;
	}

	//Used to get the total cost of the equipment
	public double getTotalCost(int units){
        return unitCost * units;
    }
}

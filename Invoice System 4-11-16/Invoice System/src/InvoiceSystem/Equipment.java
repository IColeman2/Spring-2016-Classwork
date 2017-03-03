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
public class Equipment extends Products{
    
    private double unitCost;
    
    public Equipment(String [] tempArray){
       super(tempArray[0], tempArray[1], "Equipment");
       unitCost = Double.parseDouble(tempArray[2]);
    }
    
    public double getUnitCost(){
        return unitCost;
    }
    
    public double getTotalCost(int totalEquipment){
        return unitCost * totalEquipment;
    }
}

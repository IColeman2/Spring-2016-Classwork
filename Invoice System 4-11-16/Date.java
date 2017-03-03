/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package InvoiceSystem;
import java.math.*;

/**
 *
 * @author NishPC
 */
public class Date {
    
    private int year;
    private int day;
    private int month;
    
    public Date(int d, int m, int y){
        year = y;
        day = d;
        month = m;
    }
    
    public int getYear(){
        return year;
    }
    
    public int getDay(){
        return day;
    }
    
    public int getMonth(){
        return month;
    }
    
    public int daysBetween(Date other){
        int daysBetween = 0;
        if(other.getMonth() < this.month){
            daysBetween += other.getYear()*365 - this.year*365 - 365;
            daysBetween += 365 - other.getMonth() * 30 + this.month * 30;
        }
        else{
            daysBetween += other.getYear()*365 - this.year*365;
            daysBetween += other.getMonth()*30 - this.month*30;
        }
        daysBetween += Math.abs(other.getMonth() - this.month);
        return daysBetween;
    }
}
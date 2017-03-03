/*
 * Sarah Kenny, Nisha Rao, I coleman
 * This class will convert all data from other things to xml and json
 * rename to dataconverter.jar later
 */
package InvoiceSystem;
import java.util.Scanner;
import java.util.HashMap;

public class DataToClass {
	
	public static void dataCollect(){
		PersonStore people = new PersonStore();
		Scanner dataFile = new Scanner("data/Persons.dat");
		int numOfAccounts = Integer.parseInt(dataFile.nextLine());
		for(int i = 0; i < numOfAccounts; i++){
			//splits along both types of punctuation
			String[] tempArray = dataFile.nextLine().split(";");
			//use person function to turn each line into a person and save to personStore
			people.add(new Person(tempArray));
		}
		
		HashMap<String, Products> productsList = new HashMap();
		Scanner productsFile = new Scanner("data/Products.dat");
		numOfAccounts = Integer.parseInt(productsFile.nextLine());
		for(int i = 0; i < numOfAccounts; i++){
			//splits along both types of punctuation
			String[] tempArray = productsFile.nextLine().split("[;,]");
			//use person function to turn each line into a person and save to personStore
			if(tempArray[1].equals("C")){
				productsList.put(tempArray[0], new Consultations(tempArray));
			}
			else if(tempArray[1].equals("L")){
				productsList.put(tempArray[0], new Licenses(tempArray));
			}
			else if(tempArray[1].equals("E")){
				productsList.put(tempArray[0], new Equipment(tempArray));
			}
		}
                
                HashMap<String, Customer> customerList = new HashMap();
                Scanner customerFile = new Scanner("data/Customers.dat");
                numOfAccounts = Integer.parseInt(customerFile.nextLine());
		for(int i = 0; i < numOfAccounts; i++){
			//splits along both types of punctuation
			String[] tempArray = customerFile.nextLine().split("[;,]");
			//use person function to turn each line into a person and save to personStore
                        Person contact = people.get(tempArray[2]);
                        Address address = new Address(tempArray[4]);
			customerList.put(tempArray[0], new Customer(tempArray, contact, address));
		}
	}
        
        public static void toXML(){
            
        }
	
}

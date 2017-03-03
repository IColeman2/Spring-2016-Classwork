/**
*I Coleman, Sarah Kenny, Nisha Rao
*icoleman@huskers.unl.edu / sarah.kenny@huskers.unl.edu / nisha.bookworm@gmail.com
*2/12/16
*Assignment 2
* This class will convert all data from other things to xml and json
*/

package InvoiceSystem;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.json.JsonHierarchicalStreamDriver;
import com.thoughtworks.xstream.io.xml.DomDriver;

public class Main {
	
	public static void main(String [] args){
		//Imports the .dat files
		try(
			Scanner personFile = new Scanner(new File("data/Persons.dat"));
			Scanner customerFile = new Scanner(new File("data/Customers.dat"));
			Scanner productsFile = new Scanner(new File("data/Products.dat"));
		){
			HashMap<String, Person> peopleList = new HashMap<String, Person>();
			int numOfAccounts = Integer.parseInt(personFile.nextLine());
			for(int i = 0; i < numOfAccounts; i++){
				//splits along ;
				String[] tempArray = personFile.nextLine().split(";");
				//use person function to turn each line into a person and save to personStore
				peopleList.put(tempArray[0], new Person(tempArray));
			}
			
			//Create a hash map for products
			HashMap<String, Product> productsList = new HashMap<String, Product>();
			numOfAccounts = Integer.parseInt(productsFile.nextLine());
			for(int i = 0; i < numOfAccounts; i++){
				//splits along both types of punctuation
				String[] tempArray = productsFile.nextLine().split("[;,]");
				//NISHA OR SARAH WHAT DOES THIS MEAN: use person function to turn each line into a person and save to personStore
				if(tempArray[1].equals("C")){
					productsList.put(tempArray[0], new Consultation(tempArray, peopleList.get(tempArray[3])));
				}
				else if(tempArray[1].equals("L")){
					productsList.put(tempArray[0], new License(tempArray));
				}
				else if(tempArray[1].equals("E")){
					productsList.put(tempArray[0], new Equipment(tempArray));
				}
			}
            
			//Create a hash map for customers
            HashMap<String, Customer> customerList = new HashMap<String, Customer>();
            numOfAccounts = Integer.parseInt(customerFile.nextLine());
			for(int i = 0; i < numOfAccounts; i++){
				//splits along both types of punctuation
				String[] tempArray = customerFile.nextLine().split(";");
				//use person function to turn each line into a person and save to personStore
				Person contact = peopleList.get(tempArray[2]);
	            Address address = new Address(tempArray[4]);
	            if(tempArray[1].equals("G")){
	            	customerList.put(tempArray[0], new Government(tempArray, contact, address));
	            }
	            else if(tempArray[1].equals("C")){
	            	customerList.put(tempArray[0], new Corporate(tempArray, contact, address));
	            }
			}
			
			//Creates XML and JSON output files
			try(	
			FileWriter peopleWriter = new FileWriter("data/Persons.xml");
			FileWriter customerWriter = new FileWriter("data/Customers.xml");
			FileWriter productWriter = new FileWriter("data/Products.xml");
				
			FileWriter peopleJSONwriter = new FileWriter("data/Persons.json");
			FileWriter customerJSONwriter = new FileWriter("data/Customers.json");
			FileWriter productJSONwriter = new FileWriter("data/Products.json");
			){
			
			//We wanted to store the input from the .dat files in hash maps, which makes it easy to find customers, etc. based on keys
			//But for writing to the file, it's easier to read if the customer codes/product codes only appear once
			Object[] customerArray = customerList.values().toArray();
			Object[] peopleArray = peopleList.values().toArray();
			Object[] productArray = productsList.values().toArray();
			
			//We're using the Xstream library to easily convert to XML and JSON
			XStream XMLConverter = new XStream(new DomDriver());
			XStream JSONConverter = new XStream(new JsonHierarchicalStreamDriver());
			
			XMLConverter.toXML(peopleArray, peopleWriter);
			XMLConverter.toXML(customerArray, customerWriter);
			XMLConverter.toXML(productArray, productWriter);

			JSONConverter.toXML(peopleArray, peopleJSONwriter);
			JSONConverter.toXML(customerArray, customerJSONwriter);
			JSONConverter.toXML(productArray, productJSONwriter);
			}
		catch (IOException e) {
			e.printStackTrace();
		}
		}catch (IOException e) {
			e.printStackTrace();
		}
	}
}
/**
*I Coleman, Sarah Kenny, Nisha Rao
*icoleman@huskers.unl.edu / sarah.kenny@huskers.unl.edu / nisha.bookworm@gmail.com
*4/11/16
*Assignment 6
*
* This is a collection of utility methods that define a general API for
* interacting with the database supporting this application.
*
*/

package InvoiceSystem;
import java.sql.*;
import java.util.ArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

public class InvoiceData {
	private static Connection conn;
	private final String url = "jdbc:mysql://cse.unl.edu/icoleman";
	private final String username = "icoleman";
	private final String password = "4Kt:7H";
	private static Logger log = LogManager.getLogger("invoiceSystem");
	private static MysqlDataSource dataSource;
	
	public InvoiceData(){
		dataSource = new MysqlDataSource();
		dataSource.setUser(username);
		dataSource.setURL(url);
		dataSource.setPassword(password);
		try {
			conn = dataSource.getConnection();
		} catch (SQLException e) {
			log.fatal("Incorrect Password", e);
		}
	}
	
	private static PreparedStatement getPreparedStatement(String query){
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement(query);
			
		} catch (SQLException e) {
			log.fatal("Incorrect Query", e);
		}
		return ps;
	}
	
	private static ResultSet getResultSet(String query){
		PreparedStatement ps = getPreparedStatement(query);
		ResultSet rs = null;
		try {
			rs = ps.executeQuery();
			
		} catch (SQLException e) {
			log.fatal("Incorrect Query", e);
		}
		return rs;
	}
	
	/**
	 * Method that removes every person record from the database
	 */
	public static void removeAllPersons() {
		String query = "SELECT personCode FROM Persons";		
		ResultSet rs = getResultSet(query);
		try {
			while(rs.next()){
				removePerson(rs.getString("personCode"));
			}
		} catch (SQLException e) {
			log.fatal("Incorrect Query", e);
		}
	}

	/**
	 * Removes the person record from the database corresponding to the
	 * provided <code>personCode</code>
	 * @param personCode
	 */
	public static void removePerson(String personCode) {
		int personId = getPersonId(personCode);
		ArrayList<String> productCode = getProductCodeWithPersonId(personId);
		for(String product: productCode){
			removeProduct(product);
		}
		ArrayList<String> customerCode = getCustomerCodeWithPersonId(personId);
		for(String customer: customerCode){
			removeCustomer(customer);
		}
		ArrayList<String> invoiceCode = getInvoiceCodeWithPersonId(personId);
		for(String invoice: invoiceCode){
			removeInvoice(invoice);
		}
		removeEmailWithPersonId(personId);
		String query = "DELETE FROM Persons WHERE personCode = " + personCode;
		PreparedStatement ps = getPreparedStatement(query);
		try {
			ps.executeUpdate();
		} catch (SQLException e) {
			log.fatal("Incorrect Query", e);
		}
	}
	
	/**
	 * Method to add a person record to the database with the provided data. 
	 * @param personCode
	 * @param firstName
	 * @param lastName
	 * @param street
	 * @param city
	 * @param state
	 * @param zip
	 * @param country
	 */
	public static void addPerson(String personCode, String firstName, String lastName, 
			String street, String city, String state, String zip, String country) {
		String query = "INSERT INTO Persons(personCode, firstName, lastName, addressId) "
				+ "VALUES(" + personCode + ", " + firstName + ", " + lastName + ", " 
				+ getAddressId(street, city, state, zip, country) + ")";
		PreparedStatement ps = getPreparedStatement(query);
		try {
			ps.executeUpdate();
		} catch (SQLException e) {
			log.fatal("Incorrect Query", e);
		}
	}
	
	/**
	 * Adds an email record corresponding person record corresponding to the
	 * provided <code>personCode</code>
	 * @param personCode
	 * @param email
	 */
	public static void addEmail(String personCode, String email) {
		String query = "INSERT INTO Emails(emailAddress, personId) "
				+ "VALUES (" + email + ", " + getPersonId(personCode) + ")";
		PreparedStatement ps = getPreparedStatement(query);
		try {
			ps.executeUpdate();
		} catch (SQLException e) {
			log.fatal("Incorrect Query", e);
		}
	}
	
	/**
	 * Method that removes every customer record from the database
	 */
	public static void removeAllCustomers() {
		String query = "SELECT customerCode FROM Customers";
		ResultSet rs = getResultSet(query);
		try {
			while(rs.next()){
				removeCustomer(rs.getString("customerCode"));
			}
		} catch (SQLException e) {
			log.fatal("IncorrectQuery", e);
		}
	}
	
	private static void removeCustomer(String code){
		int id = getCustomerId(code);
		ArrayList<String> invoiceCode = getInvoiceCodeWithCustomerId(id);
		for(String invoice: invoiceCode){
			removeInvoice(invoice);
		}
		String query = "DELETE FROM Customers WHERE customerCode = " + code;
		PreparedStatement ps = getPreparedStatement(query);
		try {
			ps.executeUpdate();
		} catch (SQLException e) {
			log.fatal("Incorrect Query", e);
		}
	}

	public static void addCustomer(String customerCode, String type, String primaryContactPersonCode, String name, 
			String street, String city, String state, String zip, String country) {
		String query = "INSERT INTO Customers(customerCode, customerName, customerType, addressId, personId) "
				+ "VALUES(" + customerCode + ", " + name + ", " +  type + ", " 
				+ getAddressId(street, city, state, zip, country) + ", " 
				+ getPersonId(primaryContactPersonCode) + ")";
		PreparedStatement ps = getPreparedStatement(query);
		try {
			ps.executeUpdate();
		} catch (SQLException e) {
			log.fatal("Incorrect Query", e);
		}
	}

	/**
	 * Removes all product records from the database
	 */
	public static void removeAllProducts() {
		String query = "SELECT productCode FROM Products";
		ResultSet rs = getResultSet(query);
		try {
			while(rs.next()){
				removeProduct(rs.getString("productCode"));
			}
		} catch (SQLException e) {
			log.fatal("IncorrectQuery", e);
		}
	}

	/**
	 * Removes a particular product record from the database corresponding to the
	 * provided <code>productCode</code>
	 * @param assetCode
	 */
	public static void removeProduct(String productCode) {
		removeProductsInInvoicesWithProductCode(productCode);
		String query = "DELETE FROM Products WHERE productCode = " + productCode;
		PreparedStatement ps = getPreparedStatement(query);
		try {
			ps.executeUpdate();
		} catch (SQLException e) {
			log.fatal("Incorrect Query", e);
		}
	}

	/**
	 * Adds an equipment record to the database with the
	 * provided data.  
	 */
	public static void addEquipment(String productCode, String name, Double pricePerUnit) {
		String query = "INSERT INTO Products (productCode, productName, productType, unitCost) "
				+ "VALUES (" + productCode + ", " + name + ", equimpent, " + pricePerUnit + ")";
		PreparedStatement ps = getPreparedStatement(query);
		try {
			ps.executeUpdate();
		} catch (SQLException e) {
			log.fatal("Invalid Query", e);
		}
	}
	
	/**
	 * Adds an license record to the database with the
	 * provided data.  
	 */
	public static void addLicense(String productCode, String name, double serviceFee, double annualFee) {
		String query = "INSERT INTO Products "
				+ "(productCode, productName, productType, annualServiceFee, flatServiceFee) "
				+ "VALUES (" + productCode + ", " + name + ", license, " + annualFee + ", " + serviceFee + ")";
		PreparedStatement ps = getPreparedStatement(query);
		try {
			ps.executeUpdate();
		} catch (SQLException e) {
			log.fatal("Invalid Query", e);
		}
	}

	/**
	 * Adds an consultation record to the database with the
	 * provided data.  
	 */
	public static void addConsultation(String productCode, String name, String consultantPersonCode, Double hourlyFee) {
		String query = "INSERT INTO Products (productCode, productName, productType, perHourCost, personId) "
				+ "VALUES (" + productCode + ", " + name + ", equimpent, " + hourlyFee + "," 
				+ getPersonId(consultantPersonCode) + ")";
		PreparedStatement ps = getPreparedStatement(query);
		try {
			ps.executeUpdate();
		} catch (SQLException e) {
			log.fatal("Invalid Query", e);
		}
	}
	
	/**
	 * Removes all invoice records from the database
	 */
	public static void removeAllInvoices() {
		String query = "SELECT invoiceCode FROM Invoices";
		ResultSet rs = getResultSet(query);
		try {
			while(rs.next()){
				removeInvoice(rs.getString("invoiceCode"));
			}
		} catch (SQLException e) {
			log.fatal("IncorrectQuery", e);
		}
	}
	
	/**
	 * Removes the invoice record from the database corresponding to the
	 * provided <code>invoiceCode</code>
	 * @param invoiceCode
	 */
	public static void removeInvoice(String invoiceCode) {
		removeProductsInInvoicesWithInvoiceCode(invoiceCode);
		String query = "DELETE FROM Invoices WHERE invoiceCode = " + invoiceCode;
		PreparedStatement ps = getPreparedStatement(query);
		try {
			ps.executeUpdate();
		} catch (SQLException e) {
			log.fatal("Incorrect Query", e);
		}
	}
	
	/**
	 * Adds an invoice record to the database with the given data.  
	 */
	public static void addInvoice(String invoiceCode, String customerCode, String salesPersonCode) {
		String query = "INSERT INTO Invoices(invoiceCode, invoiceCustomer, invoiceSalesperson) "
				+ "VALUES(" + invoiceCode + ", " + getCustomerId(customerCode) + ", " + getPersonId(salesPersonCode) + ")";
		PreparedStatement ps = getPreparedStatement(query);
		try {
			ps.executeUpdate();
		} catch (SQLException e) {
			log.fatal("Incorrect Query", e);
		}
	}
	
	/**
	 * Adds a particular equipment (corresponding to <code>productCode</code> to an 
	 * invoice corresponding to the provided <code>invoiceCode</code> with the given
	 * number of units
	 */
	public static void addEquipmentToInvoice(String invoiceCode, String productCode, int numUnits) {
		String query = "INSERT INTO ProductsInInvoices(invoiceId, productId, numberOfUnits) "
				+ "VALUES(" + getInvoiceId(invoiceCode) + ", " + getProductId(productCode) + ", " + numUnits + ")";
		PreparedStatement ps = getPreparedStatement(query);
		try {
			ps.executeUpdate();
		} catch (SQLException e) {
			log.fatal("Incorrect Query", e);
		}
	}
	
	/**
	 * Adds a particular equipment (corresponding to <code>productCode</code> to an 
	 * invoice corresponding to the provided <code>invoiceCode</code> with the given
	 * begin/end dates
	 */
	public static void addLicenseToInvoice(String invoiceCode, String productCode, String startDate, String endDate) {
		String query = "INSERT INTO ProductsInInvoices(invoiceId, productId, beginningDate, endDate) "
				+ "VALUES(" + getInvoiceId(invoiceCode) + ", " + getProductId(productCode) + ", " 
				+ startDate + ", " + endDate + ")";
		PreparedStatement ps = getPreparedStatement(query);
		try {
			ps.executeUpdate();
		} catch (SQLException e) {
			log.fatal("Incorrect Query", e);
		}
	}

	/**
	 * Adds a particular equipment (corresponding to <code>productCode</code> to an 
	 * invoice corresponding to the provided <code>invoiceCode</code> with the given
	 * number of billable hours.
	 */
	public static void addConsultationToInvoice(String invoiceCode, String productCode, double numHours) {
		String query = "INSERT INTO ProductsInInvoices(invoiceId, productId, consultationHours) "
				+ "VALUES(" + getInvoiceId(invoiceCode) + ", " + getProductId(productCode) + ", " + numHours + ")";
		PreparedStatement ps = getPreparedStatement(query);
		try {
			ps.executeUpdate();
		} catch (SQLException e) {
			log.fatal("Incorrect Query", e);
		}
	}
	
	private static int getPersonId(String code){
		String query = "SELECT personId FROM Persons WHERE personCode = " + code;
		ResultSet rs = getResultSet(query);
		int id = 0;
		try {
			while(rs.next()){
				id = rs.getInt("personId");
			}
		} catch (SQLException e) {
			log.fatal("Incorrect Query", e);
		}
		return id;
	}
	
	private static int getCountryId(String code){
		String query = "SELECT countryId FROM Countries WHERE countryName = " + code;
		ResultSet rs = getResultSet(query);
		int id = 0;
		try {
			while(rs.next()){
				id = rs.getInt("countryId");
			}
		} catch (SQLException e) {
			log.fatal("Incorrect Query", e);
		}
		return id;
	}
	
	private static int getStateId(String code){
		String query = "SELECT stateId FROM States WHERE stateName = " + code;
		ResultSet rs = getResultSet(query);
		int id = 0;
		try {
			while(rs.next()){
				id = rs.getInt("stateId");
			}
		} catch (SQLException e) {
			log.fatal("Incorrect Query", e);
		}
		return id;
	}
	
	private static int getAddressId(String street, String city, String state, String zip, 
			String country){
		String query = "SELECT addressId FROM Addresses WHERE street = " + street + 
				", city = " + city + ", state = " + getStateId(state) + 
				", zip = " + zip + ", country = " + getCountryId(country) + ")";
		ResultSet rs = getResultSet(query);
		int id = 0;
		try {
			while(rs.next()){
				id = rs.getInt("addressId");
			}
		} catch (SQLException e) {
			log.fatal("Incorrect Query", e);
		}
		return id;
	}
	
	private static int getCustomerId(String code){
		String query = "SELECT customerId FROM Customers WHERE customerName = " + code;
		ResultSet rs = getResultSet(query);
		int id = 0;
		try {
			while(rs.next()){
				id = rs.getInt("customerId");
			}
		} catch (SQLException e) {
			log.fatal("Incorrect Query", e);
		}
		return id;
	}
	
	private static int getInvoiceId(String code){
		String query = "SELECT invoiceId FROM Invoices WHERE invoiceCode = " + code;
		ResultSet rs = getResultSet(query);
		int id = 0;
		try {
			while(rs.next()){
				id = rs.getInt("invoiceId");
			}
		} catch (SQLException e) {
			log.fatal("Incorrect Query", e);
		}
		return id;
	}
	
	private static int getProductId(String code){
		String query = "SELECT productId FROM Products WHERE productName = " + code;
		ResultSet rs = getResultSet(query);
		int id = 0;
		try {
			while(rs.next()){
				id = rs.getInt("productId");
			}
		} catch (SQLException e) {
			log.fatal("Incorrect Query", e);
		}
		return id;
	}
	
	private static void removeProductsInInvoicesWithInvoiceCode(String code){
		String query = "DELETE FROM ProductsInInvoices WHERE invoiceId = " + getInvoiceId(code);
		PreparedStatement ps = getPreparedStatement(query);
		try {
			ps.executeUpdate();
		} catch (SQLException e) {
			log.fatal("Incorrect Query", e);
		}
	}
	
	private static void removeProductsInInvoicesWithProductCode(String code){
		String query = "DELETE FROM ProductsInInvoices WHERE productId = " + getProductId(code);
		PreparedStatement ps = getPreparedStatement(query);
		try {
			ps.executeUpdate();
		} catch (SQLException e) {
			log.fatal("Incorrect Query", e);
		}
	}
	
	private static ArrayList<String> getInvoiceCodeWithCustomerId(int code){
		String query = "SELECT invoiceCode FROM Invoices WHERE customerId = " + code;
		ResultSet rs = getResultSet(query);
		ArrayList<String> invoiceCode = new ArrayList<String>();
		try {
			while(rs.next()){
				invoiceCode.add(rs.getString("invoiceCode"));
			}
		} catch (SQLException e) {
			log.fatal("Incorrect Query", e);
		}
		return invoiceCode;
	}
	
	private static ArrayList<String> getProductCodeWithPersonId(int code){
		String query = "SELECT productCode FROM Products WHERE personId = " + code;
		ResultSet rs = getResultSet(query);
		ArrayList<String> productCode = new ArrayList<String>();
		try {
			while(rs.next()){
				productCode.add(rs.getString("productCode"));
			}
		} catch (SQLException e) {
			log.fatal("Incorrect Query", e);
		}
		return productCode;
	}
	
	private static ArrayList<String> getCustomerCodeWithPersonId(int code){
		String query = "SELECT customerCode FROM Customers WHERE personId = " + code;
		ResultSet rs = getResultSet(query);
		ArrayList<String> customerCode = new ArrayList<String>();
		try {
			while(rs.next()){
				customerCode.add(rs.getString("customerCode"));
			}
		} catch (SQLException e) {
			log.fatal("Incorrect Query", e);
		}
		return customerCode;
	}
	
	private static ArrayList<String> getInvoiceCodeWithPersonId(int code){
		String query = "SELECT invoiceCode FROM Invoices WHERE invoiceSalesperson = " + code;
		ResultSet rs = getResultSet(query);
		ArrayList<String> invoiceCode = new ArrayList<String>();
		try {
			while(rs.next()){
				invoiceCode.add(rs.getString("invoiceCode"));
			}
		} catch (SQLException e) {
			log.fatal("Incorrect Query", e);
		}
		return invoiceCode;
	}
	
	private static void removeEmailWithPersonId(int code){
		String query = "DELETE FROM Emails WHERE personId = " + code;
		PreparedStatement ps = getPreparedStatement(query);
		try {
			ps.executeUpdate();
		} catch (SQLException e) {
			log.fatal("Incorrect Query", e);
		}
	}
}
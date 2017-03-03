#TODO: Comment out before handing in
USE icoleman;

#1. query to retrieve the major fields for every person
SELECT * FROM Persons;

#2. A query to retrieve the email(s) of a given person
SELECT * FROM Emails WHERE personId = (SELECT personId FROM Persons WHERE personCode = 'sc4v');

#3. A query to add an email to a specific person
INSERT INTO Emails (emailAddress, personId) VALUES ('poesbae@gmail.com', (SELECT personId FROM Persons WHERE personCode = 'tr8r'));

#4. A query to change the email address of a given email record
UPDATE Emails SET emailAddress = 'poeshusband@gmail.com' WHERE emailAddress = 'poesbae@gmail.com'; 

#5. A query (or series of queries) to remove a given person record
DELETE FROM ProductsInInvoices WHERE invoiceId IN (SELECT i.invoiceId FROM Invoices i WHERE i.invoiceCustomer IN (SELECT c.customerId FROM Customers c WHERE c.personId IN (SELECT p.personId FROM Persons p WHERE p.firstName = 'Matt' AND p.lastName = 'RadarTechnician')));
DELETE FROM ProductsInInvoices WHERE invoiceId IN (SELECT i.invoiceId FROM Invoices i WHERE i.invoiceSalesPerson IN (SELECT p.personId FROM Persons p WHERE p.firstName = 'Matt' AND p.lastName = 'RadarTechnician'));
DELETE FROM Invoices WHERE invoiceCustomer IN (SELECT c.customerId FROM Customers c WHERE c.personId IN (SELECT p.personId FROM Persons p WHERE p.firstName = 'Matt' AND p.lastName = 'RadarTechnician'));
DELETE FROM Invoices WHERE invoiceSalesperson IN (SELECT p.personId FROM Persons p WHERE p.firstName = 'Matt' AND p.lastName = 'RadarTechnician');
DELETE FROM Customers WHERE personId IN (SELECT p.personId FROM Persons p WHERE p.firstName = 'Matt' AND p.lastName = 'RadarTechnician');
DELETE FROM Emails WHERE personId IN (SELECT p.personId FROM Persons p WHERE p.firstName = 'Matt' AND p.lastName = 'RadarTechnician');
DELETE FROM Persons WHERE firstName = 'Matt' AND lastName = 'RadarTechnician';

#6. A query to create a person record
INSERT INTO Persons (personCode, firstname, lastname, addressId) VALUES ('4nd3ad', 'Andeddu', 'Darth', (SELECT addressId FROM Addresses WHERE street = '49 Portland Place'));

#7. A query to get all the products in a particular invoice
SELECT pi.productId FROM ProductsInInvoices pi WHERE (SELECT i.invoiceId FROM Invoices i WHERE i.invoiceCode = 'INV001');
    
#8. A query to get all the invoices of a particular customer
SELECT i.invoiceId FROM Invoices i WHERE (SELECT c.customerId FROM Customers c WHERE c.customerCode = 'C001');

#9. A query to create a new product record
INSERT INTO Emails (emailAddress, personId) VALUES ('daddyissues@solo.net', (SELECT personId FROM Persons WHERE personCode = 'b3ns010'));

#10. A query to create a new invoice record
INSERT INTO Invoices (invoiceCode, invoiceCustomer, invoiceSalesperson, totalCost, totalTaxes, fee, totalFee, complianceFee, total) VALUES (
	'INV006',
    (SELECT customerId FROM Customers WHERE customerCode = 'C005'),
	(SELECT personId FROM Persons WHERE personCode = 'w2w2'),
    '3414.00',
	'0.00',
	'150.00',
	'275.00',
	'125.00',
	'3689.00'
);

#11. A query to associate a particular product with a particular invoice
INSERT INTO ProductsInInvoices (invoiceId, productId, tax, unitCost, numberOfUnits) VALUES(
	(SELECT invoiceId FROM Invoices WHERE invoiceCode = 'INV006'),
    (SELECT productId FROM Products WHERE productCode = 'cole'),
	'0.00',
	'1138.00',
	'3'
);

#12. A query to find the total number of invoices for each (and every) customer record
SELECT c.customerName AS customerName, COUNT(i.invoiceId) 
    FROM Customers c 
    LEFT JOIN Invoices i ON c.customerId = i.invoiceCustomer 
    GROUP BY c.customerId
    ORDER BY customerName;
    
#13. A query to find the total number of invoices for each salesperson
SELECT p.lastName AS personLastName, p.firstName AS personFirstName, COUNT(i.invoiceId) 
    FROM Persons p
    LEFT JOIN Invoices i ON p.personId = i.invoiceSalesPerson
    GROUP BY p.personId
    ORDER BY personLastName, personFirstName;
    
#14. A query to find the total number of invoices that include a particular product
SELECT p.productId, COUNT(pi.invoiceId) FROM Products AS p  
	LEFT JOIN ProductsInInvoices AS pi ON pi.productId = p.productId
    WHERE p.productCode = '4r40';
    
/*15. A query to find the total cost (excluding fees and taxes) of all equipment in each invoice 
	 (hint:  you can take an aggregate of a mathematical expression)*/
SELECT SUM(unitCost * numberOfUnits) AS totalCost FROM ProductsInInvoices;
	
/*16. A query to detect invalid data in invoices as follows.  In a single invoice, a particular
equipment product should only appear once (since any number of units can be con-
solidated to a single record).  Write a query to find any invoice that includes multiple
instances of the same equipment product.*/
SELECT pi.productId FROM ProductsInInvoices pi WHERE 'equipment' IN (SELECT p.productType FROM Products p WHERE pi.productId = p.productId) GROUP BY pi.invoiceId, pi.productId HAVING COUNT(*) > 1;

/*17.
  Write  a  query  to  detect  a  possible  conflict  of  interest  as  follows.   No  distinction  is
made in this system between a person who is the primary contact of a client and a
person who is also a sales person.  Write a query to find any and all invoices where the
salesperson is the same as the primary contact of the invoice's customer.*/
SELECT i.invoiceId FROM Invoices AS i WHERE (SELECT personId FROM Customers c WHERE i.invoiceCustomer = c.customerId) = i.invoiceSalesPerson;
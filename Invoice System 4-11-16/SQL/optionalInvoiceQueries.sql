#Optional queries I wants to test

#A query that deletes all people
DELETE FROM ProductsInInvoices WHERE invoiceId IN (SELECT i.invoiceId FROM Invoices i WHERE i.invoiceCustomer IN (SELECT c.customerId FROM Customers c WHERE c.personId IN (SELECT p.personId FROM Persons p WHERE p.firstName = 'Matt' AND p.lastName = 'RadarTechnician')));
DELETE FROM ProductsInInvoices WHERE invoiceId IN (SELECT i.invoiceId FROM Invoices i WHERE i.invoiceSalesPerson IN (SELECT p.personId FROM Persons p WHERE p.firstName = 'Matt' AND p.lastName = 'RadarTechnician'));
DELETE FROM Invoices WHERE invoiceCustomer IN (SELECT c.customerId FROM Customers c WHERE c.personId IN (SELECT p.personId FROM Persons p WHERE p.firstName = 'Matt' AND p.lastName = 'RadarTechnician'));
DELETE FROM Invoices WHERE invoiceSalesperson IN (SELECT p.personId FROM Persons p WHERE p.firstName = 'Matt' AND p.lastName = 'RadarTechnician');
DELETE FROM Customers WHERE personId IN (SELECT p.personId FROM Persons p WHERE p.firstName = 'Matt' AND p.lastName = 'RadarTechnician');
DELETE FROM Emails WHERE personId IN (SELECT p.personId FROM Persons p WHERE p.firstName = 'Matt' AND p.lastName = 'RadarTechnician');
DELETE FROM Persons WHERE firstName = 'Matt' AND lastName = 'RadarTechnician';
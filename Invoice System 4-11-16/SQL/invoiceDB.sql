/**
*I Coleman, Sarah Kenny, Nisha Rao
*icoleman@huskers.unl.edu / sarah.kenny@huskers.unl.edu / nisha.bookworm@gmail.com
*3/2/16
*Assignment 4 (Phase III): The creation of a database to hold invoices for CCC
*/

#TODO: Comment out before handing in
#USE skenny;

#drops existing tables
DROP TABLE IF EXISTS Emails;
DROP TABLE IF EXISTS Equipments;
DROP TABLE IF EXISTS Licenses;
DROP TABLE IF EXISTS Consultations;
DROP TABLE IF EXISTS ProductsInInvoices;
DROP TABLE IF EXISTS Invoices;
DROP TABLE IF EXISTS Products;
DROP TABLE IF EXISTS Customers;
DROP TABLE IF EXISTS Persons;
DROP TABLE IF EXISTS Addresses;
DROP TABLE IF EXISTS States;
DROP TABLE IF EXISTS Countries;

CREATE TABLE States (
	stateId INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    stateName VARCHAR(255),
    CONSTRAINT uniqueName UNIQUE INDEX (stateName)
) ENGINE=INNODB , COLLATE = LATIN1_GENERAL_CS;

CREATE TABLE Countries (
	countryId INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    countryName VARCHAR(255),
    CONSTRAINT uniqueName UNIQUE INDEX (countryName)
) ENGINE=INNODB , COLLATE = LATIN1_GENERAL_CS;

CREATE TABLE Addresses (
    addressId INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    street VARCHAR(255) NOT NULL,
    city VARCHAR(255) NOT NULL,
    state INT,
    zipCode VARCHAR(255) NOT NULL,
    country INT,
    FOREIGN KEY (state)
        REFERENCES States (stateId),
	FOREIGN KEY (country)
        REFERENCES Countries (countryId)
)  ENGINE=INNODB , COLLATE = LATIN1_GENERAL_CS;

CREATE TABLE Products (
    productId INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    productCode VARCHAR(255) NOT NULL,
    productName VARCHAR(255) NOT NULL,
    productType VARCHAR(255) NOT NULL,
    tax DOUBLE,
    #Fields used only for Equipment
    unitCost DOUBLE,
    #Fields used only for Licenses
    annualServiceFee DOUBLE,
    flatServiceFee DOUBLE,
    #Fields used only for Consultations
    perHourCost DOUBLE,
    personId INT,
    FOREIGN KEY(personId) 
		REFERENCES Persons(personId),
    CONSTRAINT uniqueProduct UNIQUE INDEX (productId)
)  ENGINE=INNODB , COLLATE = LATIN1_GENERAL_CS;

CREATE TABLE Persons (
    personId INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    personCode VARCHAR(255) NOT NULL,
    firstName VARCHAR(255) NOT NULL,
    lastName VARCHAR(255) NOT NULL,
    addressId INT NOT NULL,
    FOREIGN KEY (addressId)
        REFERENCES Addresses (addressId)
)  ENGINE=INNODB , COLLATE = LATIN1_GENERAL_CS;

CREATE TABLE Emails (
    emailId INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    emailAddress VARCHAR(255) NOT NULL,
    personId INT NOT NULL,
    FOREIGN KEY (personId)
        REFERENCES Persons (personId)
)  ENGINE=INNODB , COLLATE = LATIN1_GENERAL_CS;

CREATE TABLE Customers (
    customerId INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    customerCode VARCHAR(255) NOT NULL,
    customerName VARCHAR(255) NOT NULL,
    customerType VARCHAR(255) NOT NULL,
    addressId INT NOT NULL,
    personId INT NOT NULL,
    FOREIGN KEY (personId)
        REFERENCES Persons (personId),
    FOREIGN KEY (addressId)
        REFERENCES Addresses (addressId)
)  ENGINE=INNODB , COLLATE = LATIN1_GENERAL_CS;

CREATE TABLE Invoices (
    invoiceId INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    invoiceCode VARCHAR(255) NOT NULL,
    invoiceCustomer INT NOT NULL,
    invoiceSalesperson INT NOT NULL,
    FOREIGN KEY (invoiceCustomer)
        REFERENCES Customers (customerId),
    FOREIGN KEY (invoiceSalesperson)
        REFERENCES Persons (personId),
    totalCost DOUBLE NOT NULL,
    totalTaxes DOUBLE NOT NULL,
    fee DOUBLE NOT NULL,
    totalFee DOUBLE NOT NULL,
    complianceFee DOUBLE NOT NULL,
    total DOUBLE NOT NULL,
    CONSTRAINT uniqueName UNIQUE INDEX (invoiceCode)
)  ENGINE=INNODB , COLLATE = LATIN1_GENERAL_CS;

CREATE TABLE ProductsInInvoices (
    productConnectionID INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    invoiceId INT NOT NULL,
    productId INT NOT NULL,
    tax DOUBLE,
    #Fields used only for Equipment
    numberOfUnits INT,
    #Fields used only for Licenses
    beginningDate VARCHAR(255),
    endDate VARCHAR(255),
    #Fields used only for Consultations
    consultationHours INT,
    fee DOUBLE,
    FOREIGN KEY (invoiceId)
        REFERENCES Invoices (invoiceId),
    FOREIGN KEY (productId)
        REFERENCES Products (productId),
	CONSTRAINT uniqueCombination UNIQUE (invoiceId, productId)
)  ENGINE=INNODB , COLLATE = LATIN1_GENERAL_CS;

#inserts data into tables
INSERT INTO States (stateName) VALUES ('IL');
INSERT INTO States (stateName) VALUES ('NE');
INSERT INTO States (stateName) VALUES ('KS');
INSERT INTO States (stateName) VALUES ('CA');
INSERT INTO States (stateName) VALUES ('MI');
INSERT INTO States (stateName) VALUES ('NJ');
INSERT INTO States (stateName) VALUES ('SC');
INSERT INTO States (stateName) VALUES ('NY');
INSERT INTO States (stateName) VALUES ('NC');
INSERT INTO States (stateName) VALUES ('WI');
INSERT INTO States (stateName) VALUES ('FL');
INSERT INTO States (stateName) VALUES ('Trinidad');
INSERT INTO States (stateName) VALUES ('Pyongyang');
INSERT INTO States (stateName) VALUES ('Yunusobod District');

INSERT INTO Countries (countryName) VALUES ('USA');
INSERT INTO Countries (countryName) VALUES ('Uzbekistan');
INSERT INTO Countries (countryName) VALUES ('Tobago');
INSERT INTO Countries (countryName) VALUES ('South Africa');
INSERT INTO Countries (countryName) VALUES ('North Korea');
INSERT INTO Countries (countryName) VALUES ('Uganda');
INSERT INTO Countries (countryName) VALUES ('China');

INSERT INTO Addresses (street, city, state, zipCode, country) VALUES(
	'4444 Yavin Street',
    'Chicago',
    (SELECT stateId FROM States WHERE stateName = 'IL'),
    '60613',
    (SELECT countryId FROM Countries WHERE countryName = 'USA')
);
INSERT INTO Addresses (street, city, state, zipCode, country) VALUES(
	'4567 Jedi Temple Way',
    'Lincoln',
    (SELECT stateId FROM States WHERE stateName = 'NE'),
    '68508',
    (SELECT countryId FROM Countries WHERE countryName = 'USA')
);
INSERT INTO Addresses (street, city, state, zipCode, country) VALUES(
	'16016 W 83rd Terrace',
    'Lenexa',
    (SELECT stateId FROM States WHERE stateName = 'KS'),
    '66219',
    (SELECT countryId FROM Countries WHERE countryName = 'USA')
);
INSERT INTO Addresses (street, city, country) VALUES(
    'Plot 1577 Ggaba Rd.',
    'Campala',
    (SELECT countryId FROM Countries WHERE countryName = 'Uganda')
);
INSERT INTO Addresses (street, city, state, zipCode, country) VALUES(
	'17603 T St',
    'Omaha',
    (SELECT stateId FROM States WHERE stateName = 'NE'),
    '68135',
    (SELECT countryId FROM Countries WHERE countryName = 'USA')
);
INSERT INTO Addresses (street, city, state, zipCode, country) VALUES(
	'7 Abeto',
    'Irvine',
    (SELECT stateId FROM States WHERE stateName = 'CA'),
    '9260',
    (SELECT countryId FROM Countries WHERE countryName = 'USA')
);
INSERT INTO Addresses (street, city, state, zipCode, country) VALUES(
	'630 N 14th St',
    'Lincoln',
    (SELECT stateId FROM States WHERE stateName = 'NE'),
    '68508',
    (SELECT countryId FROM Countries WHERE countryName = 'USA')
);
INSERT INTO Addresses (street, city, zipCode, country) VALUES(
	'11 Guang Hua Lu Jian Guo Men Wai',
    'Beijing',
    '100600',
    (SELECT countryId FROM Countries WHERE countryName = 'China')
);
INSERT INTO Addresses (street, city, state, zipCode, country) VALUES(
	'4682 Huron Hill Drive',
    'Lansing',
    (SELECT stateId FROM States WHERE stateName = 'MI'),
    '48864',
    (SELECT countryId FROM Countries WHERE countryName = 'USA')
);
INSERT INTO Addresses (street, city, state, zipCode, country) VALUES(
	'6642 Redwood Drive',
    'Passaic',
    (SELECT stateId FROM States WHERE stateName = 'NJ'),
    '07055',
    (SELECT countryId FROM Countries WHERE countryName = 'USA')
);
INSERT INTO Addresses (street, city, state, zipCode, country) VALUES(
	'8878 Hickory Lane',
    'Georgetown',
    (SELECT stateId FROM States WHERE stateName = 'SC'),
    '29440',
    (SELECT countryId FROM Countries WHERE countryName = 'USA')
);
INSERT INTO Addresses (street, city, state, country) VALUES(
	'15 Queen\'s Park West',
    'Port of Spain',
    (SELECT stateId FROM States WHERE stateName = 'Trinidad'),
    (SELECT countryId FROM Countries WHERE countryName = 'Tobago')
);
INSERT INTO Addresses (street, city, state, country) VALUES(
	'Jung Song-Dong',
    'Central District',
    (SELECT stateId FROM States WHERE stateName = 'Pyongyang'),
	(SELECT countryId FROM Countries WHERE countryName = 'North Korea')
);
INSERT INTO Addresses (street, city, state, zipCode, country) VALUES(
	'6839 School Street',
    'Far Rockaway',
    (SELECT stateId FROM States WHERE stateName = 'NY'),
    '11691',
    (SELECT countryId FROM Countries WHERE countryName = 'USA')
);
INSERT INTO Addresses (street, city, zipCode, country) VALUES(
	'40 Plein St',
    'Capetown',
    '8001',
    (SELECT countryId FROM Countries WHERE countryName = 'South Africa')
);
INSERT INTO Addresses (street, city, state, zipCode, country) VALUES(
	'3135 Lincoln Ave',
    'Hickory',
    (SELECT stateId FROM States WHERE stateName = 'NC'),
    '28601',
    (SELECT countryId FROM Countries WHERE countryName = 'USA')
);
INSERT INTO Addresses (street, city, state, zipCode, country) VALUES(
	'8162 Halsey St',
    'Overland Park',
	(SELECT stateId FROM States WHERE stateName = 'KS'),
    '66216',
    (SELECT countryId FROM Countries WHERE countryName = 'USA')
);
INSERT INTO Addresses (street, city, zipCode, country) VALUES(
	'49 Portland Place',
    'London',
    'W1B1JL',
    (SELECT countryId FROM Countries WHERE countryName = 'England')
);
INSERT INTO Addresses (street, city, state, zipCode, country) VALUES(
	'1060 West Addison Street',
    'Chicago',
    (SELECT stateId FROM States WHERE stateName = 'IL'),
    '60613',
    (SELECT countryId FROM Countries WHERE countryName = 'USA')
);
INSERT INTO Addresses (street, city, state, zipCode, country) VALUES(
	'6702 CedarLane',
    'Fond Du Lac',
    (SELECT stateId FROM States WHERE stateName = 'WI'),
    '54935',
    (SELECT countryId FROM Countries WHERE countryName = 'USA')
);
INSERT INTO Addresses (street, city, state, zipCode, country) VALUES(
	'773 Garfield Ave',
    'Meadville',
    (SELECT stateId FROM States WHERE stateName = 'PA'),
    '16335',
    (SELECT countryId FROM Countries WHERE countryName = 'USA')
);
INSERT INTO Addresses (street, city, state, country) VALUES(
	'3 Moyqo\'rq\'on St',
    'Tashkent',
    (SELECT stateId FROM States WHERE stateName = 'Yunusobod District'),
    (SELECT countryId FROM Countries WHERE countryName = 'Uzbekistan')
);
INSERT INTO Addresses (street, city, state, zipCode, country) VALUES(
	'457 Poplar St',
    'Palm Harbor',
    (SELECT stateId FROM States WHERE stateName = 'FL'),
    '34683',
    (SELECT countryId FROM Countries WHERE countryName = 'USA')
);

INSERT INTO Products (productCode, productName, productType) VALUES(
	'311i',
	'Starkiller Base',
	'equipment'
);
INSERT INTO Products (productCode, productName, productType) VALUES(
	'3040',
	'Death Star Plans',
	'consultation'
);
INSERT INTO Products (productCode, productName, productType) VALUES(
	'j4c4',
	'Force Training',
	'consultation'
);
INSERT INTO Products (productCode, productName, productType) VALUES(
	'0n4s',
	'Pilot Lessons',
	'consultation'
);
INSERT INTO Products (productCode, productName, productType) VALUES(
	'amig',
	'Cloud City SQL Hosting',
	'license'
);
INSERT INTO Products (productCode, productName, productType) VALUES(
	'cole',
	'Photon Torpedo',
	'equipment'
);
INSERT INTO Products (productCode, productName, productType) VALUES(
	'yn1s',
	'Cinco Intergalactic Service',
	'license'
);
INSERT INTO Products (productCode, productName, productType) VALUES(
	'm4ns',
	'Tie Fighter',
	'equipment'
);
INSERT INTO Products (productCode, productName, productType) VALUES(
	'k3nn',
	'X-Wing Fighter',
	'equipment'
);
INSERT INTO Products (productCode, productName, productType) VALUES(
	'4r40',
	'Bounty Hunter Hire',
	'license'
);
INSERT INTO Products (productCode, productName, productType) VALUES(
	'j0hm',
	'Astromech Droid',
	'equipment'
);
INSERT INTO Products (productCode, productName, productType) VALUES(
	'ejkj',
	'Protocol Droid',
	'equipment'
);
	
INSERT INTO Persons(personCode, firstName, lastName, addressID) VALUES(
	'g4y',
    'Poe',
    'Dameron',
    (SELECT addressID FROM Addresses WHERE street = '4444 Yavin Street')
);
INSERT INTO Persons(personCode, firstName, lastName, addressID) VALUES(
	'jed1',
    'Luke',
    'Skywalker',
    (SELECT addressId FROM Addresses WHERE street = '4567 Jedi Temple Way')
);
INSERT INTO Persons(personCode, firstName, lastName, addressID) VALUES(
	'b3ns010',
    'Kylo',
    'Ren',
    (SELECT addressId FROM Addresses WHERE street = '16016 W 83rd Terrace')
);
INSERT INTO Persons(personCode, firstName, lastName, addressID) VALUES(
	'010sn3b',
    'Matt',
    'RadarTechnician',
    (SELECT addressId FROM Addresses WHERE street = 'Plot 1577 Ggaba Rd.')
);
INSERT INTO Persons(personCode, firstName, lastName, addressID) VALUES(
	'1r1ncss',
    'Leia',
    'Organa',
    (SELECT addressId FROM Addresses WHERE street = '17603 T St')
);
INSERT INTO Persons(personCode, firstName, lastName, addressID) VALUES(
	'12prscs',
    'Han',
    'Solo',
    (SELECT addressId FROM Addresses WHERE street = '7 Abeto')
);
INSERT INTO Persons(personCode, firstName, lastName, addressID) VALUES(
	'fn2187',
    'Finn',
    'Finn',
    (SELECT addressId FROM Addresses WHERE street = '630 N 14th St')
);
INSERT INTO Persons(personCode, firstName, lastName, addressID) VALUES(
	'sc4v',
    'Rey',
    'Skywalker',
    (SELECT addressId FROM Addresses WHERE street = '11 Guang Hua Lu Jian Guo Men Wai')
);
INSERT INTO Persons(personCode, firstName, lastName, addressID) VALUES(
	'tr8r',
	'Captain',
	'Phasma',
    (SELECT addressId FROM Addresses WHERE street = '4682 Huron Hill Drive')
);
INSERT INTO Persons(personCode, firstName, lastName, addressID) VALUES(
	'1128h',
    'Jessika',
    'Pava',
    (SELECT addressId FROM Addresses WHERE street = '6642 Redwood Drive')
);
INSERT INTO Persons(personCode, firstName, lastName, addressID) VALUES(
	'1138t',
    'Maz',
    'Kanata',
    (SELECT addressId FROM Addresses WHERE street = '8878 Hickory Lane')
);
INSERT INTO Persons(personCode, firstName, lastName, addressID) VALUES(
	'4ngr3g4y',
    'General',
    'Hux',
    (SELECT addressId FROM Addresses WHERE street = '15 Queen\'s Park West')
);
INSERT INTO Persons(personCode, firstName, lastName, addressID) VALUES(
	'b4d',
    'Leader',
    'Snoke',
    (SELECT addressId FROM Addresses WHERE street = 'Jung Song-Dong')
);
INSERT INTO Persons(personCode, firstName, lastName, addressID) VALUES(
	'l4nd0',
	'Lando',
	'Calrissan',
	(SELECT addressId FROM Addresses WHERE street = '6839 School Street')
);
INSERT INTO Persons(personCode, firstName, lastName, addressID) VALUES(
	'123de',
	'Jarjar',
	'Binks',
	(SELECT addressId FROM Addresses WHERE street = '40 Plein St')
);
INSERT INTO Persons(personCode, firstName, lastName, addressID) VALUES(
	'345n6',
	'Sheev',
	'Palpatine',
	(SELECT addressId FROM Addresses WHERE street = '3135 Lincoln Ave')
	);
INSERT INTO Persons(personCode, firstName, lastName, addressID) VALUES(
	'qu33n',
	'Padme',
	'Amidala',
	(SELECT addressId FROM Addresses WHERE street = '8162 Halsey St')
);
INSERT INTO Persons(personCode, firstName, lastName, addressID) VALUES(
	'v4d3r',
	'Anakin',
	'Skywalker',
	(SELECT addressId FROM Addresses WHERE street = '49 Portland Place')
);
INSERT INTO Persons(personCode, firstName, lastName, addressID) VALUES(
	'h3rm1t',
	'ObiWan',
	'Kenobi',
	(SELECT addressId FROM Addresses WHERE street = '1060 West Addison Street')
);
INSERT INTO Persons(personCode, firstName, lastName, addressID) VALUES(
	'smljxn',
	'Mace',
	'Windu',
	(SELECT addressId FROM Addresses WHERE street = '6702 CedarLane')
);
INSERT INTO Persons(personCode, firstName, lastName, addressID) VALUES(
	'p1lt',
	'Wedge',
	'Antilles',
	(SELECT addressId FROM Addresses WHERE street = '773 Garfield Ave')
);
INSERT INTO Persons(personCode, firstName, lastName, addressID) VALUES(
	'401k',
	'QuiGon',
	'Jinn',
	(SELECT addressId FROM Addresses WHERE street = '3 Moyqo\'rq\'on St')
);
INSERT INTO Persons(personCode, firstName, lastName, addressID) VALUES(
	'w2w2',
	'Darth',
	'Maul',
	(SELECT addressId FROM Addresses WHERE street = '457 Poplar St')
);

INSERT INTO Emails (emailAddress, personId) VALUES(
	'mostdaring_fighter_pilot@resistance.gov',
	(SELECT personId FROM Persons WHERE personCode = 'g4y')
);
INSERT INTO Emails (emailAddress, personId) VALUES(
	'whiny_farmboy@aol.com',
	(SELECT personId FROM Persons WHERE personCode = 'jed1')
);
INSERT INTO Emails (emailAddress, personId) VALUES(
	'lskywalker@huskers.unl.edu',
	(SELECT personId FROM Persons WHERE personCode = 'jed1')
);
INSERT INTO Emails (emailAddress, personId) VALUES(
	'its_not_a_phase_dad@hotmail.com',
	(SELECT personId FROM Persons WHERE personCode = 'b3ns010')
);
INSERT INTO Emails (emailAddress, personId) VALUES(
	'commander@firstorder.gov',
	(SELECT personId FROM Persons WHERE personCode = 'b3ns010')
);
INSERT INTO Emails (emailAddress, personId) VALUES(
	'notacommittee@resistance.gov',
	(SELECT personId FROM Persons WHERE personCode = '1r1ncss')
);
INSERT INTO Emails (emailAddress, personId) VALUES(
	'totally_with_the_resistance@firstorder.gov',
	(SELECT personId FROM Persons WHERE personCode = 'fn2187')
);
INSERT INTO Emails (emailAddress, personId) VALUES(
	'poesbae@gmail.com',
	(SELECT personId FROM Persons WHERE personCode = 'fn2187')
);
INSERT INTO Emails (emailAddress, personId) VALUES(
	'reysourbae@gmail.com',
	(SELECT personId FROM Persons WHERE personCode = 'sc4v')
);
INSERT INTO Emails (emailAddress, personId) VALUES(
	'bak2jakku@resistance.gov',
	(SELECT personId FROM Persons WHERE personCode = 'sc4v')
);
INSERT INTO Emails (emailAddress, personId) VALUES(
	'totallynotkyloren@kylo.ren',
	(SELECT personId FROM Persons WHERE personCode = '010sn3b')
);
INSERT INTO Emails (emailAddress, personId) VALUES(
	'imtheboss@firstorder.gov',
	(SELECT personId FROM Persons WHERE personCode = 'tr8r')
);
INSERT INTO Emails (emailAddress, personId) VALUES(
	'2nd_daring_pilot@resistance.gov',
	(SELECT personId FROM Persons WHERE personCode = '1128h')
);
INSERT INTO Emails (emailAddress, personId) VALUES(
	'blueleader@yahoo.com',
	(SELECT personId FROM Persons WHERE personCode = '1128h')
);
INSERT INTO Emails (emailAddress, personId) VALUES(
	'snokes_fav@firstorder.gov',
	(SELECT personId FROM Persons WHERE personCode = '4ngr3g4y')
);
INSERT INTO Emails (emailAddress, personId) VALUES(
	'bigbad@firstorder.gov',
	(SELECT personId FROM Persons WHERE personCode = 'b4d')
);
INSERT INTO Emails (emailAddress, personId) VALUES(
	'pinkponyluvr@yahoo.com',
	(SELECT personId FROM Persons WHERE personCode = 'b4d')
);
INSERT INTO Emails (emailAddress, personId) VALUES(
	'meesaclumsy@firstorder.gov',
	(SELECT personId FROM Persons WHERE personCode = '123de')
);
INSERT INTO Emails (emailAddress, personId) VALUES(
	'somehowasenator@oldrepublic.org',
	(SELECT personId FROM Persons WHERE personCode = '123de')
);
INSERT INTO Emails (emailAddress, personId) VALUES(
	'notleadingtheconfederacy@oldrepublic.org',
	(SELECT personId FROM Persons WHERE personCode = '345n6')
);
INSERT INTO Emails (emailAddress, personId) VALUES(
	'not_leading_the_republic@CIS.com',
	(SELECT personId FROM Persons WHERE personCode = '345n6')
);
INSERT INTO Emails (emailAddress, personId) VALUES(
	'queen14@naboo.gov',
	(SELECT personId FROM Persons WHERE personCode = 'qu33n')
);
INSERT INTO Emails (emailAddress, personId) VALUES(
	'whiny_bad_actor@seriouslytheworst.com',
	(SELECT personId FROM Persons WHERE personCode = 'v4d3r')
);
INSERT INTO Emails (emailAddress, personId) VALUES(
	'oldben@gmail.com',
	(SELECT personId FROM Persons WHERE personCode = 'h3rm1t')
);
INSERT INTO Emails (emailAddress, personId) VALUES(
	'purplsabr@oldrepublic.org',
	(SELECT personId FROM Persons WHERE personCode = 'smljxn')
);
INSERT INTO Emails (emailAddress, personId) VALUES(
	'hothhero@rebellion.net',
	(SELECT personId FROM Persons WHERE personCode = 'p1lt')
);
INSERT INTO Emails (emailAddress, personId) VALUES(
	'diesquick@oldrepublic.org',
	(SELECT personId FROM Persons WHERE personCode = '401k')
);
INSERT INTO Emails (emailAddress, personId) VALUES(
	'diesquicker@sith.net',
	(SELECT personId FROM Persons WHERE personCode = 'w2w2')
);
INSERT INTO Customers (customerCode, customerName, customerType, personId, addressId) VALUES(
	'C001',
	'The New Republic',
	'government',
	(SELECT personId FROM Persons WHERE personCode = 'g4y'),
	(SELECT addressId FROM Addresses WHERE street = '4444 Yavin Street')
);
INSERT INTO Customers (customerCode, customerName, customerType, personId, addressId) VALUES(
	'C002',
	'The First Order',
	'government',
	(SELECT personId FROM Persons WHERE personCode = 'jed1'),
	(SELECT addressId FROM Addresses WHERE street = '4567 Jedi Temple Way')
);
INSERT INTO Customers (customerCode, customerName, customerType, personId, addressId) VALUES(
	'C003',
	'The Rebellion',
	'company',
	(SELECT personId FROM Persons WHERE personCode = '1r1ncss'),
	(SELECT addressId FROM Addresses WHERE street = '17603 T St')
);
INSERT INTO Customers (customerCode, customerName, customerType, personId, addressId) VALUES(
	'C004',
	'Jedi Order',
	'government',
	(SELECT personId FROM Persons WHERE personCode = 'fn2187'),
	(SELECT addressId FROM Addresses WHERE street = '630 N 14th St')
);
INSERT INTO Customers (customerCode, customerName, customerType, personId, addressId) VALUES(
	'C005',
	'The Old Republic',
	'company',
	(SELECT personId FROM Persons WHERE personCode = 'tr8r'),
	(SELECT addressId FROM Addresses WHERE street = '4682 Huron Hill Drive')
);
INSERT INTO Customers (customerCode, customerName, customerType, personId, addressId) VALUES(
	'C006',
	'The Aloha Oe',
	'company',
	(SELECT personId FROM Persons WHERE personCode = '1128h'),
	(SELECT addressId FROM Addresses WHERE street = '6642 Redwood Drive')
);
INSERT INTO Customers (customerCode, customerName, customerType, personId, addressId) VALUES(
	'C007',
	'The Gogol Empire',
	'company',
	(SELECT personId FROM Persons WHERE personCode = 'g4y'),
	(SELECT addressId FROM Addresses WHERE street = '6839 School Street')
);
INSERT INTO Customers (customerCode, customerName, customerType, personId, addressId) VALUES(
	'C008',
	'Space Alien Registration',
	'government',
	(SELECT personId FROM Persons WHERE personCode = '123de'),
	(SELECT addressId FROM Addresses WHERE street = '40 Plein St')	
);
INSERT INTO Customers (customerCode, customerName, customerType, personId, addressId) VALUES(
	'C009',
	'State Alchemists',
	'government',
	(SELECT personId FROM Persons WHERE personCode = 'qu33n'),
	(SELECT addressId FROM Addresses WHERE street = '8162 Halsey St')
);
INSERT INTO Customers (customerCode, customerName, customerType, personId, addressId) VALUES(
	'C010',
	'Ishvalian Rebellion',
	'company',
	(SELECT personId FROM Persons WHERE personCode = 'smljxn'),
	(SELECT addressId FROM Addresses WHERE street = '6702 CedarLane')
);

INSERT INTO Invoices (invoiceCode, invoiceCustomer, invoiceSalesperson, totalCost, totalTaxes, fee, totalFee, complianceFee, total) VALUES(
	'INV001',
	(SELECT customerId FROM Customers WHERE customerCode = 'C001'),
	(SELECT personId FROM Persons WHERE personCode = 'g4y'),
	'18380.00',
	'0.00',
	'150.00',
	'275.00',
	'125.00',
	'18530.00'
);
INSERT INTO Invoices (invoiceCode, invoiceCustomer, invoiceSalesperson, totalCost, totalTaxes, fee, totalFee, complianceFee, total) VALUES(
	'INV002',
	(SELECT customerId FROM Customers WHERE customerCode = 'C002'),
	(SELECT personId FROM Persons WHERE personCode = '123de'),
	'208400.00',
	'0.00',
	'150.00',
	'275.00',
	'125.00',
	'208550'
);
INSERT INTO Invoices (invoiceCode, invoiceCustomer, invoiceSalesperson, totalCost, totalTaxes, fee, totalFee, complianceFee, total) VALUES(
	'INV003',
	(SELECT customerId FROM Customers WHERE customerCode = 'C010'),
	(SELECT personId FROM Persons WHERE personCode = 'b3ns010'),
	'36314.91',
	'1849.04',
	'2350.00',
	'2350.00',
	'0.00',
	'40513.08'
);
INSERT INTO Invoices (invoiceCode, invoiceCustomer, invoiceSalesperson, totalCost, totalTaxes, fee, totalFee, complianceFee, total) VALUES(
	'INV004',
	(SELECT customerId FROM Customers WHERE customerCode = 'C001'),
	(SELECT personId FROM Persons WHERE personCode = '12prscs'),
	'2400.00',
	'0.00',
	'350.00',
	'475.00',
	'125.00',
	'2525.00'
);
INSERT INTO Invoices (invoiceCode, invoiceCustomer, invoiceSalesperson, totalCost, totalTaxes, fee, totalFee, complianceFee, total) VALUES(
	'INV005',
	(SELECT customerId FROM Customers WHERE customerCode = 'C003'),
	(SELECT personId FROM Persons WHERE personCode = '1128h'),
	'5066.00',
	'266.62',
	'150.00',
	'150.00',
	'0.00',
	'5332.62'
);

INSERT INTO ProductsInInvoices (invoiceId, productId, tax, unitCost, numberOfUnits) VALUES(
	(SELECT invoiceId FROM Invoices WHERE invoiceCode = 'INV001'),
    (SELECT productId FROM Products WHERE productCode = 'ejkj'),
	'0.00',
	'6390.00',
	'2'
);
INSERT INTO ProductsInInvoices (invoiceId, productId, tax, perHourCost, consultationHours, fee) VALUES(
	(SELECT invoiceId FROM Invoices WHERE invoiceCode = 'INV001'),
	(SELECT productId FROM Products WHERE productCode = '3040'),
	'0.00',
	'70.0',
	'80',
	'150.00'
);
INSERT INTO ProductsInInvoices (invoiceId, productId, tax, unitCost, numberOfUnits) VALUES(
	(SELECT invoiceId FROM Invoices WHERE invoiceCode = 'INV002'),
	(SELECT productId FROM Products WHERE productCode = 'm4ns'),
	'0.00',
	'100000.00',
	'2'
);
INSERT INTO ProductsInInvoices (invoiceId, productId, tax, perHourCost, consultationHours, fee) VALUES(
	(SELECT invoiceId FROM Invoices WHERE invoiceCode = 'INV002'),
	(SELECT productId FROM Products WHERE productCode = '3040'),
	'0.00',
	'70.00',
	'120',
	'150.00'
);
INSERT INTO ProductsInInvoices (invoiceId, productId, tax, unitCost, numberOfUnits) VALUES(
	(SELECT invoiceId FROM Invoices WHERE invoiceCode = 'INV003'),
	(SELECT productId FROM Products WHERE productCode = 'k3nn'),
	'778.04',
	'1234.99',
	'9'
);
INSERT INTO ProductsInInvoices (invoiceId, productId, tax, annualServiceFee, flatServiceFee, beginningDate, endDate) VALUES(
	(SELECT invoiceId FROM Invoices WHERE invoiceCode = 'INV003'),
	(SELECT productId FROM Products WHERE productCode = 'yn1s'),
	'1020',
	'12000.00',
	'2000.00',
	'1997-03-04',
	'1998-03-04'
);
INSERT INTO ProductsInInvoices (invoiceId, productId, tax, annualServiceFee, flatServiceFee, beginningDate, endDate) VALUES(
	(SELECT invoiceId FROM Invoices WHERE invoiceCode = 'INV003'),
	(SELECT productId FROM Products WHERE productCode = '4r40'),
	'51.00',
	'1200.00',
	'350.00',
	'2003-01-01',
	'2004-01-01'
);
INSERT INTO ProductsInInvoices (invoiceId, productId, tax, annualServiceFee, flatServiceFee, beginningDate, endDate) VALUES(
	(SELECT invoiceId FROM Invoices WHERE invoiceCode = 'INV004'),
	(SELECT productId FROM Products WHERE productCode = '4r40'),
	'0.00',
	'1200.00',
	'350.00',
	'2007-01-01',
	'2009-01-01'
);
INSERT INTO ProductsInInvoices (invoiceId, productId, tax, perHourCost, consultationHours, fee) VALUES(
	(SELECT invoiceId FROM Invoices WHERE invoiceCode = 'INV005'),
	(SELECT productId FROM Products WHERE productCode = '0n4s'),
	'136.00',
	'8.00',
	'400',
	'350.00'
);
INSERT INTO ProductsInInvoices (invoiceId, productId, tax, unitCost, numberOfUnits) VALUES(
	(SELECT invoiceId FROM Invoices WHERE invoiceCode = 'INV002'),
	(SELECT productId FROM Products WHERE productCode = 'j0hm'),
	'130.62',
	'6202.00',
	'3'
);
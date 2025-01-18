CREATE DATABASE demo;
CREATE TABLE Product(
	code char(3) PRIMARY KEY,
	barcode_num bigint,
	p_name varchar(50),
	price integer,
	p_cost integer,
	alert_num integer
);

CREATE TABLE Inventory(
	p_code char(3) PRIMARY KEY REFERENCES Product (code),
	stock integer
);

CREATE TABLE Vehicle (
	licensePlate varchar(7) PRIMARY KEY,
	mileage double precision,
	owner_name varchar(10),
	owner_phone char(10),
	build_date timestamp,
	modified_date timestamp
);

CREATE TABLE Maintenance (
	order_num char(12) PRIMARY KEY,
	licensePlate varchar(7) REFERENCES Vehicle (licensePlate),
	maitanence_time timestamp,
	build_date timestamp
);

CREATE TABLE MaintenanceDetails (
	order_num SERIAL PRIMARY KEY REFERENCES Maintenance (order_num),
	p_code char(3) REFERENCES Product (code),
	quantity integer
);

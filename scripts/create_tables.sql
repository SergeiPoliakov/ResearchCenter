
DROP TABLE Params CASCADE CONSTRAINTS PURGE;

DROP TABLE Pj_Attributes CASCADE CONSTRAINTS PURGE;

DROP TABLE Transactions CASCADE CONSTRAINTS PURGE;

DROP TABLE Fin_objects CASCADE CONSTRAINTS PURGE;

DROP TABLE Pj_Users CASCADE CONSTRAINTS PURGE;
DROP TABLE Fin_Object_Types CASCADE CONSTRAINTS PURGE;

CREATE TABLE Fin_Object_Types
(
	Fin_Object_type_ID   INTEGER NOT NULL ,
	Fin_Object_type_name VARCHAR2(50) NOT NULL 
);

ALTER TABLE Fin_Object_Types
	ADD CONSTRAINT  XPKFin_Object_types PRIMARY KEY (Fin_Object_type_ID);

CREATE TABLE Fin_objects
(
	Fin_object_id        INTEGER NOT NULL ,
	Parent_id            INTEGER NULL ,
	Object_name          VARCHAR2(50) NOT NULL ,
	Fin_Object_type_ID   INTEGER NOT NULL ,
	User_ID              INTEGER NOT NULL 
);

ALTER TABLE Fin_objects
	ADD CONSTRAINT  XPKFin_objects PRIMARY KEY (Fin_object_id);

CREATE TABLE Pj_Attributes
(
	Attribute_Name       VARCHAR2(50) NOT NULL ,
	Fin_Object_type_ID   INTEGER NOT NULL ,
	Attribute_ID         INTEGER NOT NULL 
);

ALTER TABLE Pj_Attributes
	ADD CONSTRAINT  XPKAttributes PRIMARY KEY (Attribute_ID);

CREATE TABLE Params
(
	value               VARCHAR2(4000) NULL ,
	value_date           DATE NULL ,
	Fin_object_id        INTEGER NOT NULL ,
	Attribute_ID         INTEGER NOT NULL 
);

CREATE TABLE Transactions
(
	Transaction_ID       INTEGER NOT NULL ,
	Transaction_date     DATE NOT NULL ,
	Fin_object_id        INTEGER NOT NULL ,
	Cost                 DECIMAL(6,2) NOT NULL ,
	User_ID              INTEGER NOT NULL 
);

ALTER TABLE Transactions
	ADD CONSTRAINT  XPKTransactions PRIMARY KEY (Transaction_ID);

CREATE TABLE Pj_Users
(
	User_ID              INTEGER NOT NULL ,
	Login                VARCHAR2(20) NOT NULL ,
	Hash_sum             INTEGER NOT NULL ,
	Name                 VARCHAR2(50) NULL ,
	Account_Type         VARCHAR2(50) NOT NULL ,
	Salt                 INTEGER NOT NULL 
);

ALTER TABLE Pj_Users
	ADD CONSTRAINT  XPKUsers PRIMARY KEY (User_ID);

ALTER TABLE Fin_objects
	ADD (CONSTRAINT R_20 FOREIGN KEY (Fin_object_id) REFERENCES Fin_objects (Fin_object_id) ON DELETE SET NULL);

ALTER TABLE Fin_objects
	ADD (CONSTRAINT R_23 FOREIGN KEY (User_ID) REFERENCES Pj_Users (User_ID));

ALTER TABLE Fin_objects
	ADD (CONSTRAINT R_18 FOREIGN KEY (Fin_Object_type_ID) REFERENCES Fin_Object_Types (Fin_Object_type_ID));

ALTER TABLE Pj_Attributes
	ADD (CONSTRAINT R_25 FOREIGN KEY (Fin_Object_type_ID) REFERENCES Fin_Object_Types (Fin_Object_type_ID));

ALTER TABLE Params
	ADD (CONSTRAINT R_8 FOREIGN KEY (Fin_object_id) REFERENCES Fin_objects (Fin_object_id));

ALTER TABLE Params
	ADD (CONSTRAINT R_26 FOREIGN KEY (Attribute_ID) REFERENCES Pj_Attributes (Attribute_ID));

ALTER TABLE Transactions
	ADD (CONSTRAINT R_16 FOREIGN KEY (Fin_object_id) REFERENCES Fin_objects (Fin_object_id));
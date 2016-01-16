
CREATE TABLE Pj_Attributes
(
	Attribute_ID         INTEGER NOT NULL ,
	Attribute_Name       VARCHAR2(50) NOT NULL 
);

ALTER TABLE Pj_Attributes
	ADD CONSTRAINT  XPKAttributes PRIMARY KEY (Attribute_ID);

CREATE TABLE Fin_Object_Atr_types
(
	Fin_Object_type_ID   INTEGER NOT NULL ,
	Fin_Object_type_name VARCHAR2(50) NOT NULL ,
	Attribute_ID         INTEGER NOT NULL 
);

ALTER TABLE Fin_Object_Atr_types
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

CREATE TABLE Params
(
	value1               VARCHAR2(4000) NULL ,
	value_date           DATE NULL ,
	Attribute_ID         INTEGER NOT NULL ,
	Fin_object_id        INTEGER NOT NULL 
);

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

CREATE TABLE User_resources
(
	User_ID              INTEGER NOT NULL ,
	Fin_object_id        INTEGER NOT NULL 
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

ALTER TABLE Fin_Object_Atr_types
	ADD (CONSTRAINT R_22 FOREIGN KEY (Attribute_ID) REFERENCES Pj_Attributes (Attribute_ID));

ALTER TABLE Fin_objects
	ADD (CONSTRAINT R_18 FOREIGN KEY (Fin_Object_type_ID) REFERENCES Fin_Object_Atr_types (Fin_Object_type_ID));

ALTER TABLE Fin_objects
	ADD (CONSTRAINT R_20 FOREIGN KEY (Fin_object_id) REFERENCES Fin_objects (Fin_object_id) ON DELETE SET NULL);

ALTER TABLE Fin_objects
	ADD (CONSTRAINT R_23 FOREIGN KEY (User_ID) REFERENCES Pj_Users (User_ID));

ALTER TABLE Params
	ADD (CONSTRAINT R_7 FOREIGN KEY (Attribute_ID) REFERENCES Pj_Attributes (Attribute_ID));

ALTER TABLE Params
	ADD (CONSTRAINT R_8 FOREIGN KEY (Fin_object_id) REFERENCES Fin_objects (Fin_object_id));

ALTER TABLE User_resources
	ADD (CONSTRAINT R_9 FOREIGN KEY (User_ID) REFERENCES Pj_Users (User_ID));

ALTER TABLE User_resources
	ADD (CONSTRAINT R_10 FOREIGN KEY (Fin_object_id) REFERENCES Fin_objects (Fin_object_id));

ALTER TABLE Transactions
	ADD (CONSTRAINT R_16 FOREIGN KEY (Fin_object_id) REFERENCES Fin_objects (Fin_object_id));

ALTER TABLE Transactions
	ADD (CONSTRAINT R_21 FOREIGN KEY (User_ID) REFERENCES Pj_Users (User_ID));

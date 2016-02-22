/* delete old tables*/
DROP TABLE Params CASCADE CONSTRAINTS PURGE;
DROP TABLE Pj_Attributes CASCADE CONSTRAINTS PURGE;
DROP TABLE Transactions CASCADE CONSTRAINTS PURGE;
DROP TABLE Fin_objects CASCADE CONSTRAINTS PURGE;
DROP TABLE Pj_Users CASCADE CONSTRAINTS PURGE;
DROP TABLE Fin_Object_Types CASCADE CONSTRAINTS PURGE;

/*delete tables*/

DROP TABLE sp_params CASCADE CONSTRAINTS PURGE;
DROP TABLE sp_attributes CASCADE CONSTRAINTS PURGE;
DROP TABLE sp_transactions CASCADE CONSTRAINTS PURGE;
DROP TABLE sp_fin_objects CASCADE CONSTRAINTS PURGE;
DROP TABLE sp_users CASCADE CONSTRAINTS PURGE;
DROP TABLE sp_fin_object_types CASCADE CONSTRAINTS PURGE;


CREATE TABLE sp_fin_object_types
(
	fin_object_type_ID   INTEGER NOT NULL ,
	Fin_Object_type_name VARCHAR2(50) NOT NULL 
);

ALTER TABLE sp_fin_object_types
	ADD CONSTRAINT  XPKsp_Fin_Object_types PRIMARY KEY (Fin_Object_type_ID);

CREATE TABLE sp_fin_objects
(
	Fin_object_id        INTEGER NOT NULL ,
	Parent_id            INTEGER NULL ,
	Object_name          VARCHAR2(50) NOT NULL ,
	Fin_Object_type_ID   INTEGER NOT NULL ,
	User_ID              INTEGER NOT NULL 
);

ALTER TABLE sp_fin_objects
	ADD CONSTRAINT  XPKsp_Fin_objects PRIMARY KEY (Fin_object_id);

CREATE TABLE sp_attributes
(
	Attribute_Name       VARCHAR2(50) NOT NULL ,
	Fin_Object_type_ID   INTEGER NOT NULL ,
	Attribute_ID         INTEGER NOT NULL 
);

ALTER TABLE sp_attributes
	ADD CONSTRAINT  XPKsp_Attributes PRIMARY KEY (Attribute_ID);

CREATE TABLE sp_params
(
	value               VARCHAR2(4000) NULL ,
	value_date           DATE NULL ,
	Fin_object_id        INTEGER NOT NULL ,
	Attribute_ID         INTEGER NOT NULL 
);

CREATE TABLE sp_transactions
(
	Transaction_ID       INTEGER NOT NULL ,
	Transaction_date     DATE NOT NULL ,
	Fin_object_id        INTEGER NOT NULL ,
	Cost                 DECIMAL(6,2) NOT NULL ,
	User_ID              INTEGER NOT NULL 
);

ALTER TABLE sp_transactions
	ADD CONSTRAINT  XPKsp_Transactions PRIMARY KEY (Transaction_ID);

CREATE TABLE sp_users
(
	User_ID              INTEGER NOT NULL ,
	Login                VARCHAR2(20) NOT NULL ,
	Hash_sum             INTEGER NOT NULL ,
	Name                 VARCHAR2(50) NULL ,
	Account_Type         VARCHAR2(50) NOT NULL ,
	Salt                 INTEGER NOT NULL 
);

ALTER TABLE sp_users
	ADD CONSTRAINT  XPKsp_Users PRIMARY KEY (User_ID);

ALTER TABLE sp_fin_objects
	ADD (CONSTRAINT R_20 FOREIGN KEY (Fin_object_id) REFERENCES sp_Fin_objects (Fin_object_id) ON DELETE SET NULL);

ALTER TABLE sp_fin_objects
	ADD (CONSTRAINT R_23 FOREIGN KEY (User_ID) REFERENCES sp_Users (User_ID));

ALTER TABLE sp_fin_objects
	ADD (CONSTRAINT R_18 FOREIGN KEY (Fin_Object_type_ID) REFERENCES sp_Fin_Object_Types (Fin_Object_type_ID));

ALTER TABLE sp_attributes
	ADD (CONSTRAINT R_25 FOREIGN KEY (Fin_Object_type_ID) REFERENCES sp_Fin_Object_Types (Fin_Object_type_ID));

ALTER TABLE sp_params
	ADD (CONSTRAINT R_8 FOREIGN KEY (Fin_object_id) REFERENCES sp_Fin_objects (Fin_object_id));

ALTER TABLE sp_params
	ADD (CONSTRAINT R_26 FOREIGN KEY (Attribute_ID) REFERENCES sp_Attributes (Attribute_ID));

ALTER TABLE sp_transactions
	ADD (CONSTRAINT R_16 FOREIGN KEY (Fin_object_id) REFERENCES sp_Fin_objects (Fin_object_id));
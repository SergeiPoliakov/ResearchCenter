DROP TABLE sp_params CASCADE CONSTRAINTS PURGE;
DROP TABLE sp_attributes CASCADE CONSTRAINTS PURGE;
DROP TABLE sp_transactions CASCADE CONSTRAINTS PURGE;
DROP TABLE sp_fin_objects CASCADE CONSTRAINTS PURGE;
DROP TABLE sp_users CASCADE CONSTRAINTS PURGE;
DROP TABLE sp_fin_object_types CASCADE CONSTRAINTS PURGE;

DROP SEQUENCE SP_USER_ID_SEQ;
DROP SEQUENCE SP_FIN_OBJECT_TYPE_ID_SEQ;
DROP SEQUENCE SP_FIN_OBJECT_ID_SEQ;
DROP SEQUENCE SP_ATTRIBUTE_ID_SEQ;
DROP SEQUENCE SP_Transaction_ID_SEQ;

CREATE SEQUENCE SP_USER_ID_SEQ INCREMENT BY 1 START WITH 1;
CREATE SEQUENCE SP_FIN_OBJECT_TYPE_ID_SEQ INCREMENT BY 1 START WITH 1;
CREATE SEQUENCE SP_FIN_OBJECT_ID_SEQ INCREMENT BY 1 START WITH 1;
CREATE SEQUENCE SP_ATTRIBUTE_ID_SEQ INCREMENT BY 1 START WITH 1;
CREATE SEQUENCE SP_Transaction_ID_SEQ INCREMENT BY 1 START WITH 1;

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
	Salt                 INTEGER NOT NULL ,
	Email                VARCHAR2(50) NOT NULL 
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

INSERT INTO SP_FIN_OBJECT_TYPES --1
 (FIN_OBJECT_TYPE_ID, FIN_OBJECT_TYPE_NAME)
   VALUES(SP_FIN_OBJECT_TYPE_ID_seq.nextval, '���������');
INSERT INTO SP_FIN_OBJECT_TYPES --2
 (FIN_OBJECT_TYPE_ID, FIN_OBJECT_TYPE_NAME) 
   VALUES(SP_FIN_OBJECT_TYPE_ID_seq.nextval, '�����');
INSERT INTO SP_FIN_OBJECT_TYPES --3
 (FIN_OBJECT_TYPE_ID, FIN_OBJECT_TYPE_NAME) 
   VALUES(SP_FIN_OBJECT_TYPE_ID_seq.nextval, '������');
INSERT INTO SP_FIN_OBJECT_TYPES --4
 (FIN_OBJECT_TYPE_ID, FIN_OBJECT_TYPE_NAME) 
   VALUES(SP_FIN_OBJECT_TYPE_ID_seq.nextval, '������');
INSERT INTO SP_FIN_OBJECT_TYPES --5
 (FIN_OBJECT_TYPE_ID, FIN_OBJECT_TYPE_NAME)
   VALUES(SP_FIN_OBJECT_TYPE_ID_seq.nextval, '����');
   
INSERT INTO SP_ATTRIBUTES(ATTRIBUTE_ID, ATTRIBUTE_NAME, FIN_OBJECT_TYPE_ID) --1
 VALUES(SP_ATTRIBUTE_ID_SEQ.nextval, '����������� ����������', 1);
INSERT INTO SP_ATTRIBUTES(ATTRIBUTE_ID, ATTRIBUTE_NAME, FIN_OBJECT_TYPE_ID) --2
 VALUES(SP_ATTRIBUTE_ID_SEQ.nextval, '����������� % �� �������', 1);
INSERT INTO SP_ATTRIBUTES(ATTRIBUTE_ID, ATTRIBUTE_NAME, FIN_OBJECT_TYPE_ID) --3
 VALUES(SP_ATTRIBUTE_ID_SEQ.nextval, '������������ % �� �������', 1);
INSERT INTO SP_ATTRIBUTES(ATTRIBUTE_ID, ATTRIBUTE_NAME, FIN_OBJECT_TYPE_ID) --4
 VALUES(SP_ATTRIBUTE_ID_SEQ.nextval, '���� ������', 2);
INSERT INTO SP_ATTRIBUTES(ATTRIBUTE_ID, ATTRIBUTE_NAME, FIN_OBJECT_TYPE_ID) --5
 VALUES(SP_ATTRIBUTE_ID_SEQ.nextval, '����� ������', 2);
INSERT INTO SP_ATTRIBUTES(ATTRIBUTE_ID, ATTRIBUTE_NAME, FIN_OBJECT_TYPE_ID) --6
 VALUES(SP_ATTRIBUTE_ID_SEQ.nextval, '����������� �����', 2);
INSERT INTO SP_ATTRIBUTES(ATTRIBUTE_ID, ATTRIBUTE_NAME, FIN_OBJECT_TYPE_ID) --7
 VALUES(SP_ATTRIBUTE_ID_SEQ.nextval, '���� �������', 3);
INSERT INTO SP_ATTRIBUTES(ATTRIBUTE_ID, ATTRIBUTE_NAME, FIN_OBJECT_TYPE_ID) --8
 VALUES(SP_ATTRIBUTE_ID_SEQ.nextval, '����� �������', 3);
INSERT INTO SP_ATTRIBUTES(ATTRIBUTE_ID, ATTRIBUTE_NAME, FIN_OBJECT_TYPE_ID) --9
 VALUES(SP_ATTRIBUTE_ID_SEQ.nextval, '����������� ������', 3);
INSERT INTO SP_ATTRIBUTES(ATTRIBUTE_ID, ATTRIBUTE_NAME, FIN_OBJECT_TYPE_ID) --10
 VALUES(SP_ATTRIBUTE_ID_SEQ.nextval, '���� ��������', 4);
INSERT INTO SP_ATTRIBUTES(ATTRIBUTE_ID, ATTRIBUTE_NAME, FIN_OBJECT_TYPE_ID) --11
 VALUES(SP_ATTRIBUTE_ID_SEQ.nextval, '���� ����������', 4);
INSERT INTO SP_ATTRIBUTES(ATTRIBUTE_ID, ATTRIBUTE_NAME, FIN_OBJECT_TYPE_ID) --12
 VALUES(SP_ATTRIBUTE_ID_SEQ.nextval, '���������', 4);
INSERT INTO SP_ATTRIBUTES(ATTRIBUTE_ID, ATTRIBUTE_NAME, FIN_OBJECT_TYPE_ID) --13
 VALUES(SP_ATTRIBUTE_ID_SEQ.nextval, '���������', 4);
INSERT INTO SP_ATTRIBUTES(ATTRIBUTE_ID, ATTRIBUTE_NAME, FIN_OBJECT_TYPE_ID) --14
 VALUES(SP_ATTRIBUTE_ID_SEQ.nextval, '������', 5);
INSERT INTO SP_ATTRIBUTES(ATTRIBUTE_ID, ATTRIBUTE_NAME, FIN_OBJECT_TYPE_ID) --15
 VALUES(SP_ATTRIBUTE_ID_SEQ.nextval, '���������', 5);
INSERT INTO SP_ATTRIBUTES(ATTRIBUTE_ID, ATTRIBUTE_NAME, FIN_OBJECT_TYPE_ID) --16
 VALUES(SP_ATTRIBUTE_ID_SEQ.nextval, '���������� ������', 5);


CREATE OR REPLACE FUNCTION CHECK_UNIQUE_CATEGORY_NAME
(
  CATEGORY_NAME IN VARCHAR2,
  CAT_USER_ID IN NUMBER
)
RETURN BOOLEAN
AS
  COUNT_SELECT NUMBER := 0;
BEGIN
  SELECT COUNT(UP_OBJECT_NAME)
  INTO COUNT_SELECT
  FROM(
    SELECT UPPER(OBJECT_NAME) AS UP_OBJECT_NAME
    FROM SP_FIN_OBJECTS
    WHERE FIN_OBJECT_TYPE_ID = 1 
    AND USER_ID = CAT_USER_ID) T1
  WHERE UP_OBJECT_NAME = UPPER(CATEGORY_NAME);
  
  IF(COUNT_SELECT = 0)
  THEN
    RETURN(TRUE);
  ELSE
    RETURN(FALSE);
  END IF;
END CHECK_UNIQUE_CATEGORY_NAME;
/

CREATE OR REPLACE FUNCTION ADD_CATEGORY 
(
  USER_ID IN NUMBER 
, CATEGORY_NAME IN VARCHAR2 
, COEFFICIENT IN VARCHAR2 
, MIN_PERCENT IN VARCHAR2 
, MAX_PERCENT IN VARCHAR2 
)
RETURN NUMBER
AS 
BEGIN
  IF(CHECK_UNIQUE_CATEGORY_NAME(CATEGORY_NAME, USER_ID) = TRUE)
  THEN
    INSERT INTO SP_FIN_OBJECTS(FIN_OBJECT_ID, OBJECT_NAME, PARENT_ID, FIN_OBJECT_TYPE_ID, USER_ID)
      VALUES(SP_FIN_OBJECT_ID_SEQ.NEXTVAL, CATEGORY_NAME, NULL, 1, USER_ID);
    INSERT INTO SP_PARAMS(FIN_OBJECT_ID, ATTRIBUTE_ID, VALUE_DATE, VALUE)
      VALUES(SP_FIN_OBJECT_ID_SEQ.CURRVAL, 1, NULL, COEFFICIENT);
    INSERT INTO SP_PARAMS(FIN_OBJECT_ID, ATTRIBUTE_ID, VALUE_DATE, VALUE)
      VALUES(SP_FIN_OBJECT_ID_SEQ.CURRVAL, 2, NULL, MIN_PERCENT);
    INSERT INTO SP_PARAMS(FIN_OBJECT_ID, ATTRIBUTE_ID, VALUE_DATE, VALUE)
      VALUES(SP_FIN_OBJECT_ID_SEQ.CURRVAL, 3, NULL, MAX_PERCENT);
    RETURN(SP_FIN_OBJECT_ID_SEQ.CURRVAL); 
  ELSE
    RETURN(0);
  END IF;
END ADD_CATEGORY;
/

CREATE OR REPLACE PROCEDURE UPDATE_CATEGORY 
(
  OBJECT_ID IN NUMBER,
  CATEGORY_NAME IN VARCHAR2,
  COEFFICIENT IN VARCHAR2,
  MIN_PERCENT IN VARCHAR2,
  MAX_PERCENT IN VARCHAR2
) AS 
BEGIN
  UPDATE SP_FIN_OBJECTS 
    SET OBJECT_NAME = CATEGORY_NAME
      WHERE FIN_OBJECT_ID = OBJECT_ID;
  UPDATE SP_PARAMS
    SET VALUE = COEFFICIENT
      WHERE FIN_OBJECT_ID = OBJECT_ID AND ATTRIBUTE_ID = 1;
  UPDATE SP_PARAMS
    SET VALUE = MIN_PERCENT
      WHERE FIN_OBJECT_ID = OBJECT_ID AND ATTRIBUTE_ID = 2;
  UPDATE SP_PARAMS
    SET VALUE = MAX_PERCENT
      WHERE FIN_OBJECT_ID = OBJECT_ID AND ATTRIBUTE_ID = 3;
END UPDATE_CATEGORY;
/




CREATE OR REPLACE FUNCTION SEARCH_CATEGORY_OTHER(CATEGORY_OBJECT_ID IN NUMBER)
  RETURN NUMBER
  IS 
    COUNT_ROW number:=0;
    CATEGORY_OTHER_ID NUMBER := 0;
  BEGIN
    SELECT COUNT(CATEGORY_OBJECT_ID)
    INTO COUNT_ROW
    FROM SP_FIN_OBJECTS
    WHERE UPPER(OBJECT_NAME) = '������' AND USER_ID = (
      SELECT USER_ID 
      FROM SP_FIN_OBJECTS 
      WHERE FIN_OBJECT_ID = CATEGORY_OBJECT_ID);
    
    IF(COUNT_ROW = 0)
    THEN 
      RETURN(0);
    ELSE
      SELECT FIN_OBJECT_ID
      INTO CATEGORY_OTHER_ID
      FROM SP_FIN_OBJECTS
      WHERE UPPER(OBJECT_NAME) = '������' AND USER_ID = (
        SELECT USER_ID 
        FROM SP_FIN_OBJECTS 
        WHERE FIN_OBJECT_ID = CATEGORY_OBJECT_ID);
    
      RETURN(CATEGORY_OTHER_ID);
    END IF;
  END;
/

CREATE OR REPLACE FUNCTION CHECK_CATEGORY_EMPTY(CATEGORY_OBJECT_ID IN NUMBER)
  RETURN BOOLEAN
  IS
    COUNT_CHILD NUMBER:=0;
  BEGIN
    SELECT COUNT(FIN_OBJECT_ID)
    INTO COUNT_CHILD
    FROM SP_FIN_OBJECTS
    WHERE PARENT_ID = CATEGORY_OBJECT_ID;
    
    IF(COUNT_CHILD = 0)
      THEN
        RETURN(TRUE);
      ELSE
        RETURN(FALSE);
    END IF;
  END;
/


CREATE OR REPLACE PROCEDURE DELETE_SP_FIN_OBJECT(OBJECT_ID IN NUMBER)
  AS
  BEGIN
    DELETE FROM SP_PARAMS 
      WHERE FIN_OBJECT_ID = OBJECT_ID;
    DELETE FROM SP_FIN_OBJECTS
      WHERE FIN_OBJECT_ID = OBJECT_ID;
  END;
/


CREATE OR REPLACE PROCEDURE DELETE_CATEGORY 
(
  OBJECT_ID IN NUMBER
) AS
  CATEGORY_NAME VARCHAR2(50);
  CATEGORY_USER_ID NUMBER;
  CATEGORY_EMPTY BOOLEAN := TRUE;
  CATEGORY_OTHER_ID NUMBER:=0;
BEGIN
  IF(CHECK_CATEGORY_EMPTY(OBJECT_ID) = TRUE)
    THEN
      DELETE_SP_FIN_OBJECT(OBJECT_ID);
    ELSE
      SELECT OBJECT_NAME
      INTO CATEGORY_NAME
      FROM SP_FIN_OBJECTS
      WHERE FIN_OBJECT_ID = OBJECT_ID;
      
      IF(UPPER(CATEGORY_NAME) != '������')
      THEN
        IF(SEARCH_CATEGORY_OTHER(OBJECT_ID) = 0)
        THEN
          SELECT USER_ID
          INTO CATEGORY_USER_ID
          FROM SP_FIN_OBJECTS
          WHERE FIN_OBJECT_ID = OBJECT_ID;
          
          CATEGORY_OTHER_ID := ADD_CATEGORY(CATEGORY_USER_ID, '������', '0.2', '10', '50');
        ELSE
          CATEGORY_OTHER_ID := SEARCH_CATEGORY_OTHER(OBJECT_ID);
      END IF;
      
        UPDATE SP_FIN_OBJECTS
        SET PARENT_ID = CATEGORY_OTHER_ID
        WHERE PARENT_ID = OBJECT_ID;
        
        DELETE_SP_FIN_OBJECT(OBJECT_ID);
    END IF;  
  END IF;
END DELETE_CATEGORY;
/
 ---------------------------------------------------------------
 --        Script Oracle.  
 ---------------------------------------------------------------

CREATE TABLE Utilisateur(
	ut_id       NUMBER NOT NULL ,
	ut_nom      VARCHAR2 (250)  ,
	ut_prenom   VARCHAR2 (250)  ,
	ut_cp       VARCHAR2 (250)  ,
	ut_pass     VARCHAR2 (250)  ,
	ut_hash     VARCHAR2 (250)  ,
	ut_isAdmin  NUMBER (1) ,
	CONSTRAINT Utilisateur_Pk PRIMARY KEY (ut_id) ,
	CONSTRAINT CHK_BOOLEAN_ut_isAdmin CHECK (ut_isAdmin IN (0,1))
);
CREATE TABLE Article(
	ar_id     NUMBER NOT NULL ,
	ar_ref    VARCHAR2 (250)  ,
	ar_label  VARCHAR2 (250)  ,
	ar_prix   FLOAT   ,
	ar_image  CLOB   ,
	CONSTRAINT Article_Pk PRIMARY KEY (ar_id)
);
CREATE TABLE Date(
	da_date  DATE  NOT NULL  ,
	CONSTRAINT Date_Pk PRIMARY KEY (da_date)
);
CREATE TABLE consulter(
	ut_id    NUMBER(10,0)  NOT NULL  ,
	ar_id    NUMBER(10,0)  NOT NULL  ,
	da_date  DATE  NOT NULL  ,
	CONSTRAINT consulter_Pk PRIMARY KEY (ut_id,ar_id,da_date)
);




ALTER TABLE consulter ADD FOREIGN KEY (ut_id) REFERENCES Utilisateur(ut_id);
ALTER TABLE consulter ADD FOREIGN KEY (ar_id) REFERENCES Article(ar_id);
ALTER TABLE consulter ADD FOREIGN KEY (da_date) REFERENCES Date(da_date);

CREATE SEQUENCE Seq_Utilisateur_ut_id START WITH 1 INCREMENT BY 1 NOCYCLE;
CREATE SEQUENCE Seq_Article_ar_id START WITH 1 INCREMENT BY 1 NOCYCLE;


CREATE OR REPLACE TRIGGER Utilisateur_ut_id
	BEFORE INSERT ON Utilisateur 
  FOR EACH ROW 
	WHEN (NEW.ut_id IS NULL) 
	BEGIN
		 select Seq_Utilisateur_ut_id.NEXTVAL INTO :NEW.ut_id from DUAL; 
	END;
CREATE OR REPLACE TRIGGER Article_ar_id
	BEFORE INSERT ON Article 
  FOR EACH ROW 
	WHEN (NEW.ar_id IS NULL) 
	BEGIN
		 select Seq_Article_ar_id.NEXTVAL INTO :NEW.ar_id from DUAL; 
	END;


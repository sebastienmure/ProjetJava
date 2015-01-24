CREATE PROCEDURE createBase
AS
   BEGIN
       	---------------------------------------------------------------
		--        Script Oracle.  
		---------------------------------------------------------------

		/* SUPRRESION DES TABLES */
		DROP TABLE consulter IF EXISTS
		DROP TABLE Article IF EXISTS
		DROP TABLE Utilisateur IF EXISTS
		DROP TABLE Calendrier IF EXISTS

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
			ar_image  VARCHAR2 (250)  ,
			CONSTRAINT Article_Pk PRIMARY KEY (ar_id)
		);
		CREATE TABLE Calendrier(
			ca_date  DATE  NOT NULL  ,
			CONSTRAINT Date_Pk PRIMARY KEY (ca_date)
		);
		CREATE TABLE consulter(
			ut_id    NUMBER(10,0)  NOT NULL  ,
			ar_id    NUMBER(10,0)  NOT NULL  ,
			ca_date  DATE  NOT NULL  ,
			CONSTRAINT consulter_Pk PRIMARY KEY (ut_id,ar_id,ca_date)
		);

		ALTER TABLE consulter ADD FOREIGN KEY (ut_id) REFERENCES Utilisateur(ut_id);
		ALTER TABLE consulter ADD FOREIGN KEY (ar_id) REFERENCES Article(ar_id);
		ALTER TABLE consulter ADD FOREIGN KEY (ca_date) REFERENCES Calendrier(ca_date);

		CREATE SEQUENCE Seq_Utilisateur_ut_id START WITH 1 INCREMENT BY 1 NOCYCLE;
		CREATE SEQUENCE Seq_Article_ar_id START WITH 1 INCREMENT BY 1 NOCYCLE;


		CREATE OR REPLACE TRIGGER Utilisateur_ut_id
			BEFORE INSERT ON Utilisateur 
		  FOR EACH ROW 
			WHEN (NEW.UT_ID IS NULL) 
			BEGIN
				 select Seq_Utilisateur_ut_id.NEXTVAL INTO :NEW.UT_ID from DUAL; 
			END;

		CREATE OR REPLACE TRIGGER Article_ar_id
			BEFORE INSERT ON Article 
		  FOR EACH ROW 
		WHEN (NEW.ar_id IS NULL) 
		BEGIN
			 select Seq_Article_ar_id.NEXTVAL INTO :NEW.ar_id from DUAL; 
		END;


		/*	INSERTIONS	*/
		-- utilisateurs
		INSERT INTO Utilisateur (UT_ID, UT_NOM, UT_PRENOM, UT_CP, UT_PASS, UT_HASH, UT_ISADMIN) values
			(NULL, 'Nom0', 'Prenom0', '69000', '123Soleil', 'lol', 1);

		INSERT INTO Utilisateur (UT_ID, UT_NOM, UT_PRENOM, UT_CP, UT_PASS, UT_HASH, UT_ISADMIN) values
			(NULL, 'Nom1', 'Prenom1', '69100', '123Soleil', 'lol', 0);

		INSERT INTO Utilisateur (UT_ID, UT_NOM, UT_PRENOM, UT_CP, UT_PASS, UT_HASH, UT_ISADMIN) values
			(NULL, 'Nom2', 'Prenom2', '63000', '123Soleil', 'lol', 0);

		INSERT INTO Utilisateur (UT_ID, UT_NOM, UT_PRENOM, UT_CP, UT_PASS, UT_HASH, UT_ISADMIN) values
			(NULL, 'Nom3', 'Prenom3', '43000', '123Soleil', 'lol', 0);

		INSERT INTO Utilisateur (UT_ID, UT_NOM, UT_PRENOM, UT_CP, UT_PASS, UT_HASH, UT_ISADMIN) values
			(NULL, 'Nom4', 'Prenom4', '43700', '123Soleil', 'lol', 0);

		-- Articles
		INSERT INTO Article (AR_ID, AR_REF, AR_LABEL, AR_PRIX, AR_IMAGE) values
			(NULL, 'ref0', 'Label0', 20.5, '/img/img0.jpg');

		INSERT INTO Article (AR_ID, AR_REF, AR_LABEL, AR_PRIX, AR_IMAGE) values
			(NULL, 'ref1', 'Label1', 20.5, '/img/img1.jpg');

		INSERT INTO Article (AR_ID, AR_REF, AR_LABEL, AR_PRIX, AR_IMAGE) values
			(NULL, 'ref2', 'Label2', 20.5, '/img/img2.jpg');

		INSERT INTO Article (AR_ID, AR_REF, AR_LABEL, AR_PRIX, AR_IMAGE) values
			(NULL, 'ref3', 'Label3', 20.5, '/img/img3.jpg');
		/****************/
   END;
package de.rho.server.patient.control;

import java.text.SimpleDateFormat;
import java.util.Date;
import de.rho.server.patient.entity.Patient;


/**
 * @author Heiko, Roger
 * @version 1.5
 * 
 * konkrete Klasse zum Aufbereiten und Definieren der SQL-Statements
 *
 */


public class PatientToDB {


/*****************/
/**** General ****/
/*****************/
	
	// ------------------------------
	// ---- create Date of Today ----
	// ------------------------------
	Date today = new Date();
	
	
	// ****************************
	// **** format Date for DB ****
	// ****************************
	private String formatDateForDB(Date date) {
		SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
		String datefordb = DATE_FORMAT.format(date);
		return datefordb;
	}
		

/*******************************/
/**** SQL Queries - ADDRESS ****/
/*******************************/
	
	// ******************************
	// **** MAX(id) from Address ****
	// ******************************
	public String selectMaxIdFromAddressSqlStatement() {
		System.out.println("PatientToDB.selectMaxIdFromAddressSqlStatement()"); //debug
		
		// --------------------------------------------------------
		// -- create *CREATE MAX(id) FROM ADDRESS* sql statement --
		// --------------------------------------------------------
		String sqlstatement = "SELECT MAX(id)+1 AS id FROM ADDRESS;";
		
		System.out.println("\t" + sqlstatement); //debug
		return sqlstatement;
	}
	
	
	// ************************
	// **** create Address ****
	// ************************
	public String createAddressSqlStatement(Patient patient, int id) {
		System.out.println("PatientToDB.createAddressSqlStatement()"); //debug

		// -------------------------------------------
		// -- create *CREATE ADDRESS* sql statement --
		// -------------------------------------------	
		String sqlstatement = "INSERT INTO ADDRESS (id, street, housenumber, postalcode, city) VALUES (";
		sqlstatement = sqlstatement + id + ", \'";
		sqlstatement = sqlstatement + patient.getStreet() + "\', \'";
		sqlstatement = sqlstatement + patient.getHousenumber() + "\', \'";
		sqlstatement = sqlstatement + patient.getPostalcode() + "\', \'";
		sqlstatement = sqlstatement + patient.getCity() + "\');";
		
		System.out.println("\t" + sqlstatement); //debug
		return sqlstatement;
	}

	
	// ************************	
	// **** update Address ****
	// ************************
	public String updateAddressSqlStatement(Patient patient) {
		System.out.println("PatientToDB.updateAddressSqlStatement()"); //debug
		
		// -------------------------------------------
		// -- create *UPDATE ADDRESS* sql statement --
		// -------------------------------------------
		String sqlstatement = "UPDATE address SET ";
		sqlstatement = sqlstatement + "street=\'" + patient.getStreet() + "\', ";
		sqlstatement = sqlstatement + "housenumber=\'" + patient.getHousenumber() + "\', ";
		sqlstatement = sqlstatement + "postalcode=\'" + patient.getPostalcode() + "\', ";
		sqlstatement = sqlstatement + "city=\'" + patient.getCity() + "\' ";
		sqlstatement = sqlstatement + "WHERE id=" + patient.getAddressid() + ";";
		
		System.out.println("\t" + sqlstatement); //debug
		return sqlstatement;	
	}
	
	
/**************************************/
/**** SQL Queries - PATIENT - CRUD ****/
/**************************************/
	
	// ************************
	// **** create Patient ****
	// ************************
	public String createPatientSqlStatement(Patient patient, int id) {
		System.out.println("PatientToDB.createPatientSqlStatement()"); //debug

		// -------------------------------------------
		// -- create *CREATE PATIENT* sql statement --
		// -------------------------------------------
		String sqlstatement = "INSERT INTO PATIENT (firstname, lastname, gender, dayofbirth, lastvisit, addressid) VALUES (\'";
		sqlstatement = sqlstatement + patient.getFirstname() + "\', \'";
		sqlstatement = sqlstatement + patient.getLastname() + "\', \'";
		sqlstatement = sqlstatement + patient.getGender() + "\', \'";
		sqlstatement = sqlstatement + this.formatDateForDB(patient.getDayofbirth()) + "\', \'";		// call formatDateForDB
		sqlstatement = sqlstatement + this.formatDateForDB(today) + "\', ";							// call formatDateForDB
		sqlstatement = sqlstatement + id + "); ";
	
		System.out.println("\t" + sqlstatement); //debug
		return sqlstatement;
	}
	
	
	// **********************
	// **** read Patient ****
	// **********************
	public String readPatientSqlStatement(int id) {
		System.out.println("PatientToDB.readPatientSqlStatement()"); //debug

		// -----------------------------------------		
		// -- create *Read Patient* sql statement --
		// -----------------------------------------
		String sqlstatement = "SELECT p.*, a.street, a.housenumber, a.postalcode, a.city FROM patient AS p ";
		sqlstatement = sqlstatement + "INNER JOIN address AS a ON p.addressid=a.id WHERE p.id=" + id + ";";
		
		System.out.println("\t" + sqlstatement); //debug
		return sqlstatement;
	}
	
	
	// ************************	
	// **** update Patient ****
	// ************************
	public String updatePatientSqlStatement(Patient patient) {
		System.out.println("PatientToDB.updatePatientSqlStatement()"); //debug
		
		// -------------------------------------------
		// -- create *UPDATE PATIENT* sql statement --
		// -------------------------------------------
		String sqlstatement = "UPDATE patient SET ";
		sqlstatement = sqlstatement + "firstname=\'" + patient.getFirstname() + "\', ";
		sqlstatement = sqlstatement + "lastname=\'" + patient.getLastname() + "\', ";
		sqlstatement = sqlstatement + "gender=\'" + patient.getGender() + "\', ";
		sqlstatement = sqlstatement + "dayofbirth=\'" + this.formatDateForDB(patient.getDayofbirth()) + "\', ";
		sqlstatement = sqlstatement + "lastvisit=\'" + this.formatDateForDB(today) + "\' ";
		sqlstatement = sqlstatement + "WHERE id=" + patient.getId();
		
		System.out.println("\t" + sqlstatement); //debug
		return sqlstatement;	
	}

	
	// **************************************	
	// **** delete Patient (and Address) ****
	// **************************************
	public String deletePatientSqlStatement(int id, int addressid) {
		System.out.println("PatientToDB.deletePatientSqlStatement()"); //debug
		
		// -------------------------------------------
		// -- create *DELETE PATIENT* sql statement --
		// -------------------------------------------
		String sqlstatement = "DELETE FROM patient WHERE id=" + id + ";\n";
		sqlstatement = sqlstatement + "DELETE FROM address WHERE id=" + addressid + ";";
		
		System.out.println("\t" + sqlstatement); //debug
		return sqlstatement;		
	}

	
/***********************************************/
/**** SQL Queries - PATIENT - List & Search ****/
/***********************************************/
	
	// **************************	
	// **** get Patient List ****
	// **************************
	public String getPatientListSqlStatement() {
		System.out.println("PatientToDB.getPatientListSqlStatement()"); //debug
		
		// ---------------------------------------------
		// -- create *GET PATIENT LIST* sql statement --
		// ---------------------------------------------
		String sqlstatement = "SELECT p.*, a.STREET, a.HOUSENUMBER, a.POSTALCODE, a.CITY FROM PATIENT p ";
		sqlstatement = sqlstatement + "INNER JOIN ADDRESS a ON p.addressid=a.id";
	
		System.out.println("\t" + sqlstatement); //debug
		return sqlstatement;
	}
	
	
	// ********************************	
	// **** search Patient by Name ****
	// ********************************
	public String searchPatientByNameSqlStatement(String lastname) {
		System.out.println("PatientToDB.searchPatientByNameSqlStatement()"); //debug
		
		// ---------------------------------------------
		// -- create *SEARCH PATIENT* sql statement --
		// ---------------------------------------------
		String sqlstatement = "SELECT p.*, a.STREET, a.HOUSENUMBER, a.POSTALCODE, a.CITY FROM PATIENT p ";
		sqlstatement = sqlstatement + "INNER JOIN ADDRESS a ON p.addressid=a.id WHERE p.lastname LIKE \'%" + lastname + "%\'";
		
		System.out.println("\t" + sqlstatement); //debug
		return sqlstatement;
	}

	
/**************************************/	
	// *****************************	
	// **** create Tables in DB **** - Nur für Praesentation
	// *****************************
	public String createTablesInDBSqlStatement() {
		System.out.println("PatientToDB.createTablesInDBSqlStatement()"); //debug

		String sqlstatement = "DROP TABLE IF EXISTS PATIENT; DROP TABLE IF EXISTS MEDSTAFF; DROP TABLE IF EXISTS ADDRESS; DROP Table IF EXISTS ROLES; DROP Table IF EXISTS MT; ";
		sqlstatement = sqlstatement + "CREATE TABLE Patient(ID int auto_increment,FirstName varchar(20),LastName varchar(20),Gender char,DayOfBirth date,LastVisit date,AddressID int,PRIMARY KEY (ID)); ";
		sqlstatement = sqlstatement + "CREATE TABLE MedStaff(ID int auto_increment,FirstName varchar(20),LastName varchar(20),RoleId int,Gender char,DayOfBirth date,AddressID int,PRIMARY KEY (ID)); ";
		sqlstatement = sqlstatement + "CREATE TABLE Address(ID int not null,Street varchar(20),HouseNumber varchar(20),PostalCode varchar(5),City varchar(20),PRIMARY KEY (ID)); ";
		sqlstatement = sqlstatement + "CREATE TABLE Roles(ID int auto_increment,Role varchar(20),PRIMARY KEY (ID)); ";
		sqlstatement = sqlstatement + "CREATE TABLE MT(ID int auto_increment,PatientID int,MedStaffID int,Date date,Treatment varchar,PRIMARY KEY (ID)); ";
		sqlstatement = sqlstatement + "ALTER TABLE Patient ADD FOREIGN KEY (AddressID) REFERENCES Address(ID); ";
		sqlstatement = sqlstatement + "ALTER TABLE MedStaff ADD FOREIGN KEY (AddressID) REFERENCES Address(ID); ";
		sqlstatement = sqlstatement + "ALTER TABLE MedStaff ADD FOREIGN KEY (RoleID) REFERENCES Roles(ID); ";
		sqlstatement = sqlstatement + "INSERT INTO Roles (ID, Role) VALUES (1, 'Chefarzt'); ";
		sqlstatement = sqlstatement + "INSERT INTO Roles (ID, Role) VALUES (2, 'Arzt'); ";
		sqlstatement = sqlstatement + "INSERT INTO Roles (ID, Role) VALUES (3, 'Arzthelfer(in)'); ";
		sqlstatement = sqlstatement + "INSERT INTO Address (ID, Street, HouseNumber, Postalcode, City) VALUES (1, 'Heisenbergstrasse', '5', '50170', 'Kerpen'); ";
		sqlstatement = sqlstatement + "INSERT INTO Address (ID, Street, HouseNumber, Postalcode, City) VALUES (2, 'Strombergallee', '44', '50129', 'Bergheim'); ";
		sqlstatement = sqlstatement + "INSERT INTO Address (ID, Street, HouseNumber, Postalcode, City) VALUES (3, 'Spielstrasse', '12', '50129', 'Bergheim'); ";
		sqlstatement = sqlstatement + "INSERT INTO Address (ID, Street, HouseNumber, Postalcode, City) VALUES (4, 'Horst-Schlemmer-Weg', '99', '50667', 'Köln'); ";
		sqlstatement = sqlstatement + "INSERT INTO Address (ID, Street, HouseNumber, Postalcode, City) VALUES (5, 'AppDev-Weg', '101', '50667', 'Köln'); ";
		sqlstatement = sqlstatement + "INSERT INTO Address (ID, Street, HouseNumber, Postalcode, City) VALUES (6, 'Hauptstrasse', '34', '50996', 'Köln'); ";
		sqlstatement = sqlstatement + "INSERT INTO Address (ID, Street, HouseNumber, Postalcode, City) VALUES (7, 'Hubertusallee', '48', '14193', 'Berlin'); ";
		sqlstatement = sqlstatement + "INSERT INTO Address (ID, Street, HouseNumber, Postalcode, City) VALUES (8, 'Frankstrasse', '18', '50996', 'Köln'); ";
		sqlstatement = sqlstatement + "INSERT INTO Patient (FirstName, LastName, Gender, Dayofbirth, LastVisit, AddressID) VALUES ('Bernd', 'Stromberg', 'm', '1960-04-25', '1960-07-26', '1'); ";
		sqlstatement = sqlstatement + "INSERT INTO Patient (FirstName, LastName, Gender, Dayofbirth, LastVisit, AddressID) VALUES ('Walter', 'White', 'm', '1954-05-15', '1973-06-16', '2'); ";
		sqlstatement = sqlstatement + "INSERT INTO Patient (FirstName, LastName, Gender, Dayofbirth, LastVisit, AddressID) VALUES ('Harald', 'Schmidt', 'm', '1952-11-01', '1959-02-21', '3'); ";
		sqlstatement = sqlstatement + "INSERT INTO Patient (FirstName, LastName, Gender, Dayofbirth, LastVisit, AddressID) VALUES ('Biene', 'Maja', 'w', '1989-10-30', '1990-12-01', '4'); ";
		sqlstatement = sqlstatement + "INSERT INTO Patient (FirstName, LastName, Gender, Dayofbirth, LastVisit, AddressID) VALUES ('Berthold', 'Heisterkamp', 'm', '1975-10-30', '1979-12-06', '5'); ";
		sqlstatement = sqlstatement + "INSERT INTO MedStaff (FirstName, LastName, Gender, RoleID, Dayofbirth, AddressID) VALUES ('Klaus', 'Brinkmann', 'm', '1', '1962-08-12', '6'); ";
		sqlstatement = sqlstatement + "INSERT INTO MedStaff (FirstName, LastName, Gender, RoleID, Dayofbirth, AddressID) VALUES ('Günter', 'Pfitzmann', 'm', '2', '1962-08-12', '7'); ";
		sqlstatement = sqlstatement + "INSERT INTO MedStaff (FirstName, LastName, Gender, RoleID, Dayofbirth, AddressID) VALUES ('Lisa', 'Müller', 'w', '3', '1976-07-04', '8'); ";
		sqlstatement = sqlstatement + "INSERT INTO MT (PatientID, MedStaffID, Date, Treatment) VALUES ('1', '2', '2015-01-04', 'Großes Blutbild. Verdacht auf Virusgrippe. Patient hat hohes Fieber (40,2 Grad)');";

		System.out.println("\t" + sqlstatement); //debug
		return sqlstatement;

	}
		
}
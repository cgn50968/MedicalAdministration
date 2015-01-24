package de.rho.server.patient.control;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import de.rho.server.patient.entity.Patient;


/**
 * @author Heiko, Roger
 * @version 1.1
 * 
 * konkrete Klasse zum Definieren der SQL-Statements
 *
 */


public class PatientToDB {


/*****************/
/**** General ****/
/*****************/
	
	// ******************************
	// **** create Date of Today ****
	// ******************************
	Date today = new Date();
	
	
	// ****************************
	// **** format Date for DB ****
	// ****************************
	public String formatDateForDB(Date date) {
		SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
		String datefordb = DATE_FORMAT.format(date);
		return datefordb;
	}
		

/*********************/
/**** SQL Queries ****/
/*********************/
	
	// ******************************
	// **** MAX(id) from Patient ****
	// ******************************
	public String selectMaxIdFromAddressSqlStatement() {
		System.out.println("PatientToDB.selectMaxIdFromAddressSqlStatement()"); //debug
		String sqlstatement = "SELECT MAX(id)+1 AS id FROM ADDRESS;";
		
		System.out.println(sqlstatement); //debug
		return sqlstatement;
	}
	
	
	// ************************
	// **** create Address ****
	// ************************
	public String createAddressSqlStatement(Patient patient, int id) {
		System.out.println("PatientToDB.createAddressSqlStatement()"); //debug
		String sqlstatement = "INSERT INTO ADDRESS (id, street, housenumber, postalcode, city) VALUES (";
		sqlstatement = sqlstatement + id + ", \'";
		sqlstatement = sqlstatement + patient.getStreet() + "\', \'";
		sqlstatement = sqlstatement + patient.getHousenumber() + "\', \'";
		sqlstatement = sqlstatement + patient.getPostalcode() + "\', \'";
		sqlstatement = sqlstatement + patient.getCity() + "\');";
		
		System.out.println(sqlstatement); //debug
		return sqlstatement;
	}
	
	
	// ************************
	// **** create Patient ****
	// ************************
	public String createPatientSqlStatement(Patient patient, int id) {
		System.out.println("PatientToDB.createPatientSqlStatement()"); //debug

		// ***********************************************
		// **** create *Create Patient* sql statement ****
		// ***********************************************
		String sqlstatement = "INSERT INTO PATIENT (firstname, lastname, gender, dayofbirth, lastvisit, addressid) VALUES (\'";
		sqlstatement = sqlstatement + patient.getFirstname() + "\', \'";
		sqlstatement = sqlstatement + patient.getLastname() + "\', \'";
		sqlstatement = sqlstatement + patient.getGender() + "\', \'";
		sqlstatement = sqlstatement + this.formatDateForDB(patient.getDayofbirth()) + "\', \'";		// call formatDateForDB
		sqlstatement = sqlstatement + this.formatDateForDB(today) + "\', ";							// call formatDateForDB
		sqlstatement = sqlstatement + id + "); ";
	
		
		System.out.println(sqlstatement); //debug
		return sqlstatement;
	}
	
	
	// **********************
	// **** read Patient ****
	// **********************
	public String readPatientSqlStatement(int id) {
		System.out.println("PatientToDB.readPatientSqlStatement"); //debug
		
		// **** create *Read Patient* sql statement ****
		String sqlstatement = "SELECT p.*, a.street, a.housenumber, a.postalcode, a.city FROM patient AS p ";
		sqlstatement = sqlstatement + "INNER JOIN address AS a ON p.addressid=a.id WHERE p.id=" + id + ";";
		return sqlstatement;
	}
	
	
	// ************************	
	// **** update Patient ****
	// ************************
	public String updatePatientSqlStatement(Patient patient) {
		
		// **** create Date ****
		Date today = new Date();
		SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
		String date = DATE_FORMAT.format(today);
		
		// **** create *Update Patient* sql statement ****
		String sqlstatement = "UPDATE patient SET ";
		sqlstatement = sqlstatement + "firstname=\'" + patient.getFirstname() + "\', ";
		sqlstatement = sqlstatement + "lastname=\'" + patient.getLastname() + "\', ";
		sqlstatement = sqlstatement + "gender=\'" + patient.getGender() + "\', ";
		sqlstatement = sqlstatement + "dayofbirth=\'" + patient.getDayofbirth() + "\', ";
		//sqlstatement = sqlstatement + "addressid=" + patient.getAddressid() + ", ";
		sqlstatement = sqlstatement + "lastvisit=\'" + date + "\' ";
		sqlstatement = sqlstatement + "WHERE id=" + patient.getId();
		return sqlstatement;	
	}

	
	// **************************************	
	// **** delete Patient (and Address) ****
	// **************************************
	public String deletePatientSqlStatement(int id, int addressid) {
		System.out.println("PatientToDB.deletePatientSqlStatement"); //debug
		
		// **** create *delete Patient* sql statement ****
		String sqlstatement = "DELETE FROM patient WHERE id=" + id + ";\n";
		sqlstatement = sqlstatement + "DELETE FROM address WHERE id=" + addressid + ";";
		
		System.out.println(sqlstatement); //debug
		return sqlstatement;		
	}
	
	
	// **************************	
	// **** get Patient List ****
	// **************************
	public String getPatientListSqlStatement() {
		System.out.println("PatientToDB.getPatientListSqlStatement"); //debug
		
		// **** create *Get Patient List* sql statement ****
		//String sqlstatement = "SELECT * FROM PATIENT";
		String sqlstatement = "SELECT p.*, a.STREET, a.HOUSENUMBER, a.POSTALCODE, a.CITY FROM PATIENT p ";
		sqlstatement = sqlstatement + "INNER JOIN ADDRESS a ON p.addressid=a.id";
	
		System.out.println(sqlstatement); //debug		
		return sqlstatement;
	}
	
	
	// ****************************	
	// **** write Patient List ****
	// ****************************
	/*
	public String writePatientListSqlStatement(ArrayList<Patient> patientList) {
		System.out.println("PatientToDB.wirtePatientListSqlStatement"); //debug
		
		System.out.println("Achtung: Tabellen werden ueberschrieben!");
		System.out.println("----------------------------------------\n");
		System.out.println("Fortfahren? - Eingabe:j/n");
		
		ArrayList<Patient> toDBlist = patientList;
		
		
		//TODO: select für vorherigen check, ob tabelle vorhanden
		
		String sqlstatement = "";
		sqlstatement = sqlstatement + "DROP TABLE PATIENT;";
		sqlstatement = sqlstatement + "DROP TABLE ADDRESS;";
				
		sqlstatement = sqlstatement + "CREATE TABLE Patient (ID int auto_increment, FirstName varchar(20), LastName varchar(20), Gender char, DayOfBirth varchar(10), LastVisit varchar(10), AddressID int,	PRIMARY KEY (ID));";		
		sqlstatement = sqlstatement + "CREATE TABLE Address (ID int not null, Street varchar(20), HouseNumber varchar(20), PostalCode varchar(5), City varchar(20), PRIMARY KEY (ID));";
		sqlstatement = sqlstatement + "ALTER TABLE Patient ADD FOREIGN KEY (AddressID) REFERENCES Address(ID);";
		
		sqlstatement = sqlstatement + "SELECT p.*, a.STREET, a.HOUSENUMBER, a.POSTALCODE, a.CITY FROM PATIENT AS p INNER JOIN ADDRESS AS a ON p.addressid=a.id;";
		
		for(Patient patient : toDBlist){					
		
			sqlstatement = sqlstatement + "INSERT INTO PATIENT (p.id, p.firstname, p.lastname, p.gender, p.dayofbirth, p.lastvisit, p.addressid)";
			sqlstatement = sqlstatement + "VALUES (\'";
			sqlstatement = sqlstatement + patient.getId() + "\', \'";
			sqlstatement = sqlstatement + patient.getFirstname() + "\', \'";
			sqlstatement = sqlstatement + patient.getLastname() + "\', \'";
			sqlstatement = sqlstatement + patient.getGender() + "\', \'";
			sqlstatement = sqlstatement + patient.getDayofbirth() + "\', \'";
			sqlstatement = sqlstatement + patient.getLastvisit() + "\', \'";
			sqlstatement = sqlstatement + patient.getAddressid() + "\'";
			sqlstatement = sqlstatement + ");";
		}
		//sqlstatement = sqlstatement.substring(0, sqlstatement.length()-2);
		
		/*
		//sqlstatement = sqlstatement + "DROP TABLE ADDRESS;";
		
		for(Patient patient : toDBlist){					
			
			sqlstatement = sqlstatement + "INSERT INTO ADDRESS (id, street, housenumber, postalcode, city)";
			sqlstatement = sqlstatement + "VALUES (\'";
			sqlstatement = sqlstatement + patient.getAddressid() + "\', \'";
			sqlstatement = sqlstatement + patient.getStreet() + "\', \'";
			sqlstatement = sqlstatement + patient.getHousenumber() + "\', \'";
			sqlstatement = sqlstatement + patient.getPostalcode() + "\', \'";
			sqlstatement = sqlstatement + patient.getCity() + "\'";
			sqlstatement = sqlstatement + ");";
		}
		
		
		//sqlstatement = sqlstatement.substring(0, sqlstatement.length()-3);
				
		System.out.println(sqlstatement); //debug
		
		return sqlstatement;
	}
	*/
	
	
	// ********************************	
	// **** search Patient by Name ****
	// ********************************
	public String searchPatientByNameSqlStatement(String lastname) {
		System.out.println("PatientToDB.searchPatientByNameSqlStatement"); //debug
		
		String sqlstatement = "SELECT p.*, a.STREET, a.HOUSENUMBER, a.POSTALCODE, a.CITY FROM PATIENT p ";
		sqlstatement = sqlstatement + "INNER JOIN ADDRESS a ON p.addressid=a.id WHERE p.lastname LIKE \'%" + lastname + "%\'";
		
		System.out.println(sqlstatement); //debug
		return sqlstatement;
	}
	
	
	
/* - Auskommentiert - Roger - 20.01.2015
 * 
	private DaoToDB h2db;									//Deklaration fuer DaoToDB
	private DaoToFile mysqldb; 								//Deklaration fuer DaoToFile
	
	
	public void createPatientDB(Patient patient) {
		System.out.println("ServicetoDB: Methode createPatientDB");
		
		String sql = "SELECT alles von allem";
		this.h2db = new DaoToDB();								//Instanziierung DaoToDB (Default-Konstruktor)
		
		System.out.println("ServicetoDB: Trying to locate DaoToDB");
		
		h2db.connect();
		h2db.executeQuery(sql);
	}
	
	public Patient readPatientDB(int id) {
		System.out.println("ServicetoDB: Methode readPatientDB");
		
		String sql = "SELECT alles von allem";
		this.h2db = new DaoToDB();								//Instanziierung DaoToDB (Default-Konstruktor)
		
		System.out.println("ServicetoDB: Trying to locate DaoToDB");
		
		h2db.connect();
		h2db.executeQuery(sql);
		
		return null;
	}
		
	public void updatePatientDB(Patient patient) {
		System.out.println("ServicetoDB: Methode updatePatientDB: Processing SQL: Done.");
	}

	public void deletePatientDB(int id) {
		System.out.println("ServicetoDB: Methode deletePatientDB: Processing SQL: Done.");
	}
	
	public ArrayList<Patient> readPatientListDB(String searchString) {
		System.out.println("ServicetoDB: Methode readPatientListDB: Processing SQL: Erstelle Liste.");
		return null;
	}
	
	public ArrayList<Patient> searchPatientListDB(String searchString) {
		System.out.println("ServicetoDB: Methode searchPatientList: Processing SQL: Suche nach Kriterien und erstelle Liste.");
		return null;
	}
*/
	
}
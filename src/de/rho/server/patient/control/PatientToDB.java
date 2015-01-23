package de.rho.server.patient.control;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


import de.rho.server.dao.persistence.DaoToDB;
import de.rho.server.patient.entity.Patient;


/**
 * @author Heiko, Roger
 * @version 1.1
 * 
 * konkrete Klasse zum Definieren der SQL-Statements
 *
 */


public class PatientToDB {

	// ************************
	// **** create Patient ****
	// ************************
	public String createPatientSqlStatement(Patient patient) {
		System.out.println("PatientToDB.createPatientSqlStatement"); //debug
		
		// **** create Date ****
		Date today = new Date();
		SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
		String date = DATE_FORMAT.format(today);
		
		// **** create *Create Patient* sql statement ****
		String sqlstatement = "INSERT INTO PATIENT (id, firstname, lastname, gender, dayofbirth, addressid, lastvisit) VALUES (";
		sqlstatement = sqlstatement + "(SELECT MAX(id)+1 FROM PATIENT), \'"; 
		sqlstatement = sqlstatement + patient.getFirstname() + "\', \'";
		sqlstatement = sqlstatement + patient.getLastname() + "\', \'";
		sqlstatement = sqlstatement + patient.getGender() + "\', \'";
		sqlstatement = sqlstatement + patient.getDayofbirth() + "\', ";
		sqlstatement = sqlstatement + patient.getAddressid() + ", \'";
		sqlstatement = sqlstatement + date + "\')";		
		return sqlstatement;
	}
	
	
	// **********************
	// **** read Patient ****
	// **********************
	public String readPatientSqlStatement(int id) {
		System.out.println("PatientToDB.readPatientSqlStatement"); //debug
		
		// **** create *Read Patient* sql statement ****
		String sqlstatement = "SELECT p.*, a.STREET, a.HOUSENUMBER, a.POSTALCODE, a.CITY FROM PATIENT p ";
		sqlstatement = sqlstatement + "INNER JOIN ADDRESS a ON p.addressid=a.id WHERE p.id=" + id;
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

	
	// ************************	
	// **** delete Patient ****
	// ************************
	public String deletePatientSqlStatement(int id) {

		// **** create *Read Patient* sql statement ****
		String sqlstatement = "DELETE FROM patient WHERE id=" + id;
		return sqlstatement;		
	}
	
	
	// **************************	
	// **** get Patient List ****
	// **************************
	public String getPatientListSqlStatement() {
		System.out.println("PatientToDB.getPatientListSqlStatement"); //debug
		
		// **** create *Get Patient List* sql statement ****
		String sqlstatement = "SELECT * FROM PATIENT";
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
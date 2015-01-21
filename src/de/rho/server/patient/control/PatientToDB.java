package de.rho.server.patient.control;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import de.rho.server.dao.persistence.DaoToFile;
import de.rho.server.dao.persistence.DaoToH2DB;
import de.rho.server.patient.entity.Patient;


/**
 * @author Heiko, Roger
 * @version 1.1
 * 
 * konkrete Klasse zum Regeln der Datenbankzugriffe
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
		String sqlstatement = "INSERT INTO PATIENT (id, firstname, lastname, gender, addressid, lastvisit) VALUES (";
		sqlstatement = sqlstatement + "(SELECT MAX(id)+1 FROM PATIENT), \'"; 
		sqlstatement = sqlstatement + patient.getFirstname() + "\', \'";
		sqlstatement = sqlstatement + patient.getLastname() + "\', \'";
		sqlstatement = sqlstatement + patient.getGender() + "\', ";
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
		String sqlstatement = "SELECT * FROM PATIENT WHERE id=" + id;
		return sqlstatement;
	}
	
	
	// ************************	
	// **** update Patient ****
	// ************************
	public void updatePatientDB(Patient patient) {
		
	}
	
	/**** delete Patient ****/
	public void deletePatientDB(int id) {
		
	}
	
/* - Auskommentiert - Roger - 20.01.2015
 * 
	private DaoToH2DB h2db;									//Deklaration fuer DaoToH2DB
	private DaoToFile mysqldb; 								//Deklaration fuer DaoToFile
	
	
	public void createPatientDB(Patient patient) {
		System.out.println("ServicetoDB: Methode createPatientDB");
		
		String sql = "SELECT alles von allem";
		this.h2db = new DaoToH2DB();								//Instanziierung DaoToH2DB (Default-Konstruktor)
		
		System.out.println("ServicetoDB: Trying to locate DaoToH2DB");
		
		h2db.connect();
		h2db.executeQuery(sql);
	}
	
	public Patient readPatientDB(int id) {
		System.out.println("ServicetoDB: Methode readPatientDB");
		
		String sql = "SELECT alles von allem";
		this.h2db = new DaoToH2DB();								//Instanziierung DaoToH2DB (Default-Konstruktor)
		
		System.out.println("ServicetoDB: Trying to locate DaoToH2DB");
		
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
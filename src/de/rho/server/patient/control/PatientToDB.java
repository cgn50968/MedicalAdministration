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
		
		// --------------------------------------------------------
		// -- create *CREATE MAX(id) FROM ADDRESS* sql statement --
		// --------------------------------------------------------
		String sqlstatement = "SELECT MAX(id)+1 AS id FROM ADDRESS;";
		
		System.out.println(sqlstatement); //debug
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
		
		System.out.println(sqlstatement); //debug
		return sqlstatement;
	}
	
	
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
	
		System.out.println(sqlstatement); //debug
		return sqlstatement;
	}
	
	
	// **********************
	// **** read Patient ****
	// **********************
	public String readPatientSqlStatement(int id) {
		System.out.println("PatientToDB.readPatientSqlStatement"); //debug

		// -----------------------------------------		
		// -- create *Read Patient* sql statement --
		// -----------------------------------------
		String sqlstatement = "SELECT p.*, a.street, a.housenumber, a.postalcode, a.city FROM patient AS p ";
		sqlstatement = sqlstatement + "INNER JOIN address AS a ON p.addressid=a.id WHERE p.id=" + id + ";";
		
		System.out.println(sqlstatement); //debug
		return sqlstatement;
	}
	
	
	// ************************	
	// **** update Patient ****
	// ************************
	public String updatePatientSqlStatement(Patient patient) {
		System.out.println("PatientToDB.updatePatientSqlStatement"); //debug
		
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
		
		System.out.println(sqlstatement); //debug
		return sqlstatement;	
	}

	
	// **************************************	
	// **** delete Patient (and Address) ****
	// **************************************
	public String deletePatientSqlStatement(int id, int addressid) {
		System.out.println("PatientToDB.deletePatientSqlStatement"); //debug
		
		// -------------------------------------------
		// -- create *DELETE PATIENT* sql statement --
		// -------------------------------------------
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
		
		// ---------------------------------------------
		// -- create *GET PATIENT LIST* sql statement --
		// ---------------------------------------------
		String sqlstatement = "SELECT p.*, a.STREET, a.HOUSENUMBER, a.POSTALCODE, a.CITY FROM PATIENT p ";
		sqlstatement = sqlstatement + "INNER JOIN ADDRESS a ON p.addressid=a.id";
	
		System.out.println(sqlstatement); //debug		
		return sqlstatement;
	}
	
	
	// ********************************	
	// **** search Patient by Name ****
	// ********************************
	public String searchPatientByNameSqlStatement(String lastname) {
		System.out.println("PatientToDB.searchPatientByNameSqlStatement"); //debug
		
		// ---------------------------------------------
		// -- create *SEARCH PATIENT* sql statement --
		// ---------------------------------------------
		String sqlstatement = "SELECT p.*, a.STREET, a.HOUSENUMBER, a.POSTALCODE, a.CITY FROM PATIENT p ";
		sqlstatement = sqlstatement + "INNER JOIN ADDRESS a ON p.addressid=a.id WHERE p.lastname LIKE \'%" + lastname + "%\'";
		
		System.out.println(sqlstatement); //debug
		return sqlstatement;
	}
		
}
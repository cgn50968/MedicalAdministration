package de.rho.server.medstaff.control;

import java.text.SimpleDateFormat;
import java.util.Date;

import de.rho.server.medstaff.entity.MedStaff;
import de.rho.server.patient.entity.Patient;

public class MedStaffToDB {


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
		System.out.println("MedStaffToDB.selectMaxIdFromAddressSqlStatement()"); //debug
		
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
	public String createAddressSqlStatement(MedStaff medstaff, int id) {
		System.out.println("MedStaffToDB.createAddressSqlStatement()"); //debug

		// -------------------------------------------
		// -- create *CREATE ADDRESS* sql statement --
		// -------------------------------------------	
		String sqlstatement = "INSERT INTO ADDRESS (id, street, housenumber, postalcode, city) VALUES (";
		sqlstatement = sqlstatement + id + ", \'";
		sqlstatement = sqlstatement + medstaff.getStreet() + "\', \'";
		sqlstatement = sqlstatement + medstaff.getHousenumber() + "\', \'";
		sqlstatement = sqlstatement + medstaff.getPostalcode() + "\', \'";
		sqlstatement = sqlstatement + medstaff.getCity() + "\');";
		
		System.out.println(sqlstatement); //debug
		return sqlstatement;
	}
	
	
	// ************************	
	// **** update Address ****
	// ************************
	public String updateAddressSqlStatement(MedStaff medstaff) {
		System.out.println("MedStaffToDB.updateAddressSqlStatement()"); //debug
		
		// -------------------------------------------
		// -- create *UPDATE ADDRESS* sql statement --
		// -------------------------------------------
		String sqlstatement = "UPDATE address SET ";
		sqlstatement = sqlstatement + "street=\'" + medstaff.getStreet() + "\', ";
		sqlstatement = sqlstatement + "housenumber=\'" + medstaff.getHousenumber() + "\', ";
		sqlstatement = sqlstatement + "postalcode=\'" + medstaff.getPostalcode() + "\', ";
		sqlstatement = sqlstatement + "city=\'" + medstaff.getCity() + "\' ";
		sqlstatement = sqlstatement + "WHERE id=" + medstaff.getAddressid() + ";";
		
		System.out.println(sqlstatement); //debug
		return sqlstatement;	
	}
	
	
/********************************/
/**** SQL Queries - MEDSTAFF ****/
/********************************/

	// ************************
	// **** create MedStaff ****
	// ************************
	public String createMedStaffSqlStatement(MedStaff medstaff, int id) {
		System.out.println("MedStaffToDB.createMedStaffSqlStatement()"); //debug

		// -------------------------------------------
		// -- create *CREATE MEDSTAFF* sql statement --
		// -------------------------------------------
		String sqlstatement = "INSERT INTO MEDSTAFF (firstname, lastname, roleid, gender, dayofbirth, addressid) VALUES (\'";
		sqlstatement = sqlstatement + medstaff.getFirstname() + "\', \'";
		sqlstatement = sqlstatement + medstaff.getLastname() + "\', ";
		sqlstatement = sqlstatement + medstaff.getRoleid() + ", \'";
		sqlstatement = sqlstatement + medstaff.getGender() + "\', \'";
		sqlstatement = sqlstatement + this.formatDateForDB(medstaff.getDayofbirth()) + "\', ";		// call formatDateForDB
		sqlstatement = sqlstatement + id + "); ";
	
		System.out.println(sqlstatement); //debug
		return sqlstatement;
	}
	
	
	// ***********************
	// **** read MedStaff ****
	// ***********************
	public String readMedStaffSqlStatement(int id) {
		System.out.println("MedStaffToDB.readMedStaffSqlStatement()"); //debug

		// -----------------------------------------		
		// -- create *Read MedStaff* sql statement --
		// -----------------------------------------
		String sqlstatement = "SELECT m.*, r.role, a.street, a.housenumber, a.postalcode, a.city FROM medstaff AS m ";
		sqlstatement = sqlstatement + "INNER JOIN address AS a ON m.addressid=a.id ";
		sqlstatement = sqlstatement + "INNER JOIN roles AS r ON m.roleid=r.id WHERE m.id=" + id + ";";
		
		System.out.println(sqlstatement); //debug
		return sqlstatement;
	}
	
	
	// *************************	
	// **** update MedStaff ****
	// *************************
	public String updateMedStaffSqlStatement(MedStaff medstaff) {
		System.out.println("MedStaffToDB.updateMedStaffSqlStatement()"); //debug
		
		// -------------------------------------------
		// -- create *UPDATE MEDSTAFF* sql statement --
		// -------------------------------------------
		String sqlstatement = "UPDATE medstaff SET ";
		sqlstatement = sqlstatement + "firstname=\'" + medstaff.getFirstname() + "\', ";
		sqlstatement = sqlstatement + "lastname=\'" + medstaff.getLastname() + "\', ";
		sqlstatement = sqlstatement + "roleid=" + medstaff.getRoleid() + ", ";
		sqlstatement = sqlstatement + "gender=\'" + medstaff.getGender() + "\', ";
		sqlstatement = sqlstatement + "dayofbirth=\'" + this.formatDateForDB(medstaff.getDayofbirth()) + "\' ";
		sqlstatement = sqlstatement + "WHERE id=" + medstaff.getId();
		
		System.out.println(sqlstatement); //debug
		return sqlstatement;	
	}
	
}

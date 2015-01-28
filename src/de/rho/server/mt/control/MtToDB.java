package de.rho.server.mt.control;

import java.text.SimpleDateFormat;
import java.util.Date;


import de.rho.server.mt.entity.MedTreatment;

public class MtToDB {
	



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
	
		
/*********************************/
/**** SQL Queries - MT - CRUx ****/
/*********************************/

	// *******************
	// **** create MT ****
	// *******************
	public String createMtSqlStatement(MedTreatment mt) {
		System.out.println("MtToDB.createMtSqlStatement()"); //debug

		// --------------------------------------
		// -- create *CREATE MT* sql statement --
		// --------------------------------------
		String sqlstatement = "INSERT INTO mt (patientid, medstaffid, date, treatment) VALUES (";
		sqlstatement = sqlstatement + mt.getPatientid() + ", ";
		sqlstatement = sqlstatement + mt.getMedstaffid() + ", \'";
		sqlstatement = sqlstatement + this.formatDateForDB(mt.getDate()) + "\', \'";
		sqlstatement = sqlstatement + mt.getTreatment() + "\');";
			
		
		System.out.println(sqlstatement); //debug
		return sqlstatement;
	}
		
		
	// *****************
	// **** read MT ****
	// *****************
	public String readMtSqlStatement(int id) {
		System.out.println("MtToDB.readMtSqlStatement()"); //debug

		// ------------------------------------		
		// -- create *Read MT* sql statement --
		// ------------------------------------
		String sqlstatement = "SELECT t.id, t.patientid, p.firstname AS pfirstname, p.lastname AS plastname, p.dayofbirth, ";
		sqlstatement = sqlstatement + "t.treatment, t.date, ";  
		sqlstatement = sqlstatement + "t.medstaffid, r.role, m.firstname AS mfirstname, m.lastname AS mlastname ";
		sqlstatement = sqlstatement + "FROM mt AS t ";
		sqlstatement = sqlstatement + "INNER JOIN medstaff AS m ON m.id = t.medstaffid ";
		sqlstatement = sqlstatement + "INNER JOIN roles AS r ON r.id = m.roleid ";
		sqlstatement = sqlstatement + "INNER JOIN patient AS p ON p.id = t.patientid ";
		sqlstatement = sqlstatement + "WHERE t.id=" + id + ";";
		
		System.out.println(sqlstatement); //debug
		return sqlstatement;
	}
		
		
	// *******************	
	// **** update MT ****
	// *******************
	public String updateMtSqlStatement(MedTreatment mt) {
		System.out.println("MtToDB.updateMtSqlStatement()"); //debug
			
		// --------------------------------------
		// -- create *UPDATE MT* sql statement --
		// --------------------------------------
			String sqlstatement = "UPDATE medstaff SET ";
			sqlstatement = sqlstatement + "patientid=\'" + mt.getPatientid() + "\', ";
			sqlstatement = sqlstatement + "medstaffid=\'" + mt.getMedstaffid() + "\', ";
			sqlstatement = sqlstatement + "date=\'" + this.formatDateForDB(mt.getDate()) + "\' ";
			sqlstatement = sqlstatement + "treatment=\'" + mt.getTreatment() + "\' ";
			
			System.out.println(sqlstatement); //debug
			return sqlstatement;	
		}
	
	
	
/*********************************/
/**** SQL Queries - MT - List ****/
/*********************************/

		// **************************	
		// **** get Patient List ****
		// **************************
		public String getMtListSqlStatement(int patientid) {
			System.out.println("MtToDB.getMtListSqlStatement()"); //debug
				
			
			// ----------------------------------------
			// -- create *GET MT LIST* sql statement --
			// ----------------------------------------
			String sqlstatement = "SELECT t.id, t.patientid, p.firstname AS pfirstname, p.lastname AS plastname, p.dayofbirth, ";
			sqlstatement = sqlstatement + "t.treatment, t.date, ";  
			sqlstatement = sqlstatement + "t.medstaffid, r.role, m.firstname AS mfirstname, m.lastname AS mlastname ";
			sqlstatement = sqlstatement + "FROM mt AS t ";
			sqlstatement = sqlstatement + "INNER JOIN medstaff AS m ON m.id = t.medstaffid ";
			sqlstatement = sqlstatement + "INNER JOIN roles AS r ON r.id = m.roleid ";
			sqlstatement = sqlstatement + "INNER JOIN patient AS p ON p.id = t.patientid ";
			sqlstatement = sqlstatement + "WHERE t.patientid=" + patientid + ";";
			
			System.out.println(sqlstatement); //debug		
			return sqlstatement;
		}
		
	
	
	
}

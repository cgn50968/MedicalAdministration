package de.rho.server.medstaff.control;

public class MedStaffToDB {

	
	
	
// **********************
// **** read Patient ****
// **********************
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
	
}

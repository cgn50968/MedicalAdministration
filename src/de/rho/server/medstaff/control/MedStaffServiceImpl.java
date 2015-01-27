package de.rho.server.medstaff.control;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import de.rho.server.dao.boundary.InDaoToDB;
import de.rho.server.dao.control.FaDaoService;
import de.rho.server.medstaff.boundary.InMedStaffService;
import de.rho.server.medstaff.entity.MedStaff;

public class MedStaffServiceImpl extends UnicastRemoteObject implements InMedStaffService {

	private static final long serialVersionUID = 123456789;

	// ***************************
	// **** declare Variables ****
	// ***************************
	
	/** Methoden-Services **/
	private MedStaffToDB medstaff2db;		// -- Deklaration fuer DB-Methoden
	
	/** Connection-Services over Interface **/
	private InDaoToDB db_service; 			// -- FaDaoService.getDaoToDBService()
	
	/** Database **/
	private ResultSet resultSet;
	private String sql_statement;
	private int max_id;
	
	
	// *********************
	// **** Constructor ****
	// *********************
	protected MedStaffServiceImpl() throws RemoteException {
		super();
		this.medstaff2db = new MedStaffToDB();   					// -- Initialisierung/Instanziierung der "Objektverbindung" DB (Defaultkonstruktor)
		this.db_service = FaDaoService.getDaoToDBService(); 		// -- Initialisierung/Instanziierung des DB Service
	}
	
	
/**************/
/**** CRUD ****/
/**************/
		
		
// *************************
// **** create MedStaff ****
// *************************	
	@Override	
		public void createMedStaffInDB(MedStaff medstaff) throws RemoteException {
			System.out.println("\nMedStaffService.createMedStaffDB");			
		}
		
		
// ***********************
// **** read MedStaff ****
// ***********************
	@Override	
	public MedStaff readMedStaffInDB(int id) throws RemoteException {
		System.out.println("\nMedStaffService.readMedStaffDB");
			
		MedStaff medstaff = new MedStaff();
		
		// ------------------------------------------
		// -- create SQL Statement - READ MEDSTAFF --
		// ------------------------------------------
		sql_statement = this.medstaff2db.readMedStaffSqlStatement(id);

		
		// ------------------------------------
		// -- open Connection to H2 Database --  
		// ------------------------------------
		Connection con = null;					
		try {
			con = this.db_service.connect();
		} 
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		
		
		// ----------------------------------------------
		// -- execute SQL Query - MAX(id) FROM ADDRESS --
		// ----------------------------------------------
		resultSet = this.db_service.executeQuery(con, sql_statement, true);
		
		
		// ---------------------------------------
		// -- write ResultSet to Patient object --
		// ---------------------------------------
		try {
			while(resultSet.next()) {
				medstaff.setId(Integer.parseInt(resultSet.getString("ID")));
				medstaff.setFirstname(resultSet.getString("FIRSTNAME"));
				medstaff.setLastname(resultSet.getString("LASTNAME"));	
				medstaff.setRoleid(Integer.parseInt(resultSet.getString("ROLEID")));
				medstaff.setRole(resultSet.getString("ROLE"));
				medstaff.setGender(resultSet.getString("GENDER"));
				medstaff.setDayofbirth(resultSet.getDate("DAYOFBIRTH"));
				medstaff.setAddressid(Integer.parseInt(resultSet.getString("ADDRESSID")));
				medstaff.setStreet(resultSet.getString("STREET"));
				medstaff.setHousenumber(resultSet.getString("HOUSENUMBER"));
				medstaff.setPostalcode(resultSet.getString("POSTALCODE"));
				medstaff.setCity(resultSet.getString("CITY"));
			}
		} 
		catch (NumberFormatException e) {
			e.printStackTrace();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		// ---------------------------------------
		// -- close DB Connection and ResultSet --
		// ---------------------------------------
		try {
			this.db_service.disconnect(con, resultSet);		//con und resultSet schlie�en
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		// ---------------------------
		// -- return MedStaff object --
		// ---------------------------
		return medstaff;

	}
		
		
// *************************
// **** update MedStaff ****
// *************************
	@Override	
		public void updateMedStaffInDB(MedStaff medstaff) throws RemoteException {
			System.out.println("\nMedStaffService.updateMedStaffDB");			
		}
		
		
// *************************
// **** delete MedStaff ****
// *************************
	@Override	
		public void deleteMedStaffInDB(int id, int addressid) throws RemoteException {
			System.out.println("\nMedStaffService.deleteMedStaffDB");		
		}
		
		
/**************/
/**** List ****/
/**************/

// ***************************
// **** get MedStaff List ****
// ***************************
	@Override	
		public ArrayList<MedStaff> getMedStaffListFromDB() throws RemoteException {
			System.out.println("\nMedStaffServiceImpl.getMedStaffListFromDB");
			return null;
		}
	
	
}
package de.rho.server.mt.control;

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
import de.rho.server.mt.boundary.InMtService;
import de.rho.server.mt.entity.MedTreatment;


public class MtServiceImpl extends UnicastRemoteObject implements InMtService {

	private static final long serialVersionUID = 123456789;

	// ***************************
	// **** declare Variables ****
	// ***************************
		
	/** Methoden-Services **/
	private MtToDB mt2db;		// -- Deklaration fuer DB-Methoden
		
	/** Connection-Services over Interface **/
	private InDaoToDB db_service; 			// -- FaDaoService.getDaoToDBService()
		
	/** Database **/
	private ResultSet resultSet;
	private String sql_statement;
		
		
	// *********************
	// **** Constructor ****
	// *********************
	protected MtServiceImpl() throws RemoteException {
		super();
		this.mt2db = new MtToDB();   								// -- Initialisierung/Instanziierung der "Objektverbindung" DB (Defaultkonstruktor)
		this.db_service = FaDaoService.getDaoToDBService(); 		// -- Initialisierung/Instanziierung des DB Service
	}
		
		
/**************/
/**** CRUx ****/
/**************/
			
// *******************
// **** create MT ****
// *******************	
	@Override	
	public void createMtInDB(MedTreatment mt) throws RemoteException {
		System.out.println("\nMtService.createMtDB");	
			
		// --------------------------------------
		// -- create SQL Statement - CREATE MT --
		// --------------------------------------
		sql_statement = this.mt2db.createMtSqlStatement(mt);

		// ------------------------------------
		// -- open Connection to H2 Database --  
		// ------------------------------------
		Connection con = null;
		try {
			con = this.db_service.connect();
		} 
		catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

				
		// -----------------------------------
		// -- execute SQL Query - CREATE MT --
		// -----------------------------------
		this.db_service.executeQuery(con, sql_statement, false);		// false = kein Return Wert

				
		// ---------------------------------------
		// -- close DB Connection and ResultSet --
		// ---------------------------------------
		try {
			this.db_service.disconnect(con, resultSet);		//'con' = connection, 'resultSet' oder 'null' (wenn kein resultSet geschlossen werden muss)
		} 
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
			

// *****************
// **** read MT ****
// *****************
	@Override	
	public MedTreatment readMtInDB(int id) throws RemoteException {
		System.out.println("\nMtService.readMtDB");
				
		MedTreatment mt = new MedTreatment();
			
		// ------------------------------------
		// -- create SQL Statement - READ MT --
		// ------------------------------------
		sql_statement = this.mt2db.readMtSqlStatement(id);

			
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
			
			
		// ---------------------------------
		// -- execute SQL Query - READ MT --
		// ---------------------------------
		resultSet = this.db_service.executeQuery(con, sql_statement, true);
			
			
		// ----------------------------------
		// -- write ResultSet to MT object --
		// ----------------------------------
		try {
			while(resultSet.next()) {
				mt.setId(Integer.parseInt(resultSet.getString("ID")));
				mt.setPatientid(Integer.parseInt(resultSet.getString("PATIENTID")));
				mt.setMedstaffid(Integer.parseInt(resultSet.getString("MEDSTAFFID")));
				mt.setDate(resultSet.getDate("DATE"));
				mt.setTreatment(resultSet.getString("TREATMENT"));
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
			this.db_service.disconnect(con, resultSet);		//con und resultSet schlieﬂen
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		
			
		// ----------------------
		// -- return MT object --
		// ----------------------
		return mt;
	}
			
			
// *************************
// **** update MedStaff ****
// *************************
	@Override	
	public void updateMtInDB(MedTreatment mt) throws RemoteException {
		System.out.println("\nMtService.updateMtDB");	
			
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
				
				
		// --------------------------
		// -- create SQL Statement --
		// --------------------------
		sql_statement = this.mt2db.updateMtSqlStatement(mt);
				
				
		// -----------------------------------
		// -- execute SQL Query - UPDATE MT --
		// -----------------------------------
		this.db_service.executeQuery(con, sql_statement, false);
				

		// ----------------------------------------
		// -- close DB Connection / no ResultSet --
		// ----------------------------------------
		try {
			this.db_service.disconnect(con, null);		
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}

						
/**************/
/**** List ****/
/**************/

// ****************************
// **** get MtList From DB ****
// ****************************
	@Override	
	public ArrayList<MedTreatment> getMtListFromDB(int patientid) throws RemoteException {
		System.out.println("\nMtServiceImpl.getMtListFromDB");
			
		// --------------------------
		// -- create SQL Statement --
		// --------------------------
		sql_statement = this.mt2db.getMtListSqlStatement(patientid);
					
				
		// ---------------------------------
		// -- open Connection to Database --  
		// --------------------------------- 
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
					
				
		// --------------------------------------------
		// -- execute SQL Query - resultSet expected --
		// --------------------------------------------
		resultSet = this.db_service.executeQuery(con, sql_statement, true);
					
		// ---------------------------
		// -- create Treatment List --
		// ---------------------------
		ArrayList<MedTreatment> mtList = new ArrayList<MedTreatment>();
			
		try {
			while(resultSet.next()) {				// Pro Datensatz
				
				MedTreatment mt = new MedTreatment();
				mt.setId(Integer.parseInt(resultSet.getString("ID")));
				mt.setPatientid(Integer.parseInt(resultSet.getString("PATIENTID")));
				mt.setMedstaffid(Integer.parseInt(resultSet.getString("MEDSTAFFID")));
				mt.setDate(resultSet.getDate("DATE"));
				mt.setTreatment(resultSet.getString("TREATMENT"));
					
						
				// ----------------------------------------
				// -- Save MedTreatment object in MtList --
				// ----------------------------------------
				mtList.add(mt);
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
			this.db_service.disconnect(con, resultSet);		//con und resultSet schlieﬂen
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
					
							
		// -------------------
		// -- return MtList --
		// -------------------	
							
		System.out.println("MtServiceImpl.getMtListFromDB()");			//debug
		System.out.println("-----Inhalt der ArrayListe aus der DB:");	//debug
							  	
		return mtList;
	}	
}

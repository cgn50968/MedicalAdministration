package de.rho.server.patient.control;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import de.rho.server.dao.boundary.InDaoToDB;
import de.rho.server.dao.boundary.InDaoToFile;
import de.rho.server.dao.control.FaDaoService;
import de.rho.server.patient.boundary.InPatientService;
import de.rho.server.patient.entity.Patient;

/**
 * @author Heiko, Roger
 * @version 1.5
 * 
 * Implementierung des Services Patient
 * Uebergabestation der Servicemethoden an konkrete Klassen 
 *
 */

public class PatientServiceImpl extends UnicastRemoteObject implements InPatientService {
	
	
	private static final long serialVersionUID = 123456789;
	

	// ***************************
	// **** declare Variables ****
	// ***************************
	
	/** Methoden-Services **/
	private PatientToCSV patient2csv; 	// -- Deklaration fuer CSV-Methoden
	private PatientToDB patient2db;		// -- Deklaration fuer DB-Methoden
	
	/** Connection-Services over Interface **/
	private InDaoToDB db_service; 		// -- FaDaoService.getDaoToDBService()
	private InDaoToFile file_service;	// -- FaDaoService.getDaoToFileService()
	
	/** Database **/
	private ResultSet resultSet;
	private String sql_statement;
	private int max_id;
	
	// ******************************
	// **** create Date of today ****
	// ******************************
	Date today = new Date();
	

	// *********************
	// **** Constructor ****
	// *********************
		
	protected PatientServiceImpl() throws RemoteException {
		super();
		this.patient2csv = new PatientToCSV(); 						// -- Initialisierung/Instanziierung der "Objektverbindung" CSV (Defaultkonstruktor)
		this.patient2db = new PatientToDB();   						// -- Initialisierung/Instanziierung der "Objektverbindung" DB (Defaultkonstruktor)
		this.db_service = FaDaoService.getDaoToDBService(); 		// -- Initialisierung/Instanziierung des DB Service
		this.file_service = FaDaoService.getDaoToFileService();		// -- Initialisierung/Instanziierung des File Service
	}

	
/**************/
/**** CRUD ****/
/**************/
	
// ************************
// **** create Patient ****
// ************************
	@Override
	public void createPatientInDB(Patient patient) throws RemoteException {
		System.out.println("\nPatientServiceImpl.createPatientInDB()");

		// -----------------------------------
		// -- reset variable for Address ID --
		// -----------------------------------
		max_id = 1;
			
		// -------------------------------------------------
		// -- create SQL Statement - MAX(id) FROM ADDRESS --
		// -------------------------------------------------
		sql_statement = this.patient2db.selectMaxIdFromAddressSqlStatement();
			
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

		
		// ----------------------------------------------
		// -- execute SQL Query - MAX(id) FROM ADDRESS --
		// ----------------------------------------------
		resultSet = this.db_service.executeQuery(con, sql_statement, true);
			
		try {
			while(resultSet.next()) {
				max_id = Integer.parseInt(resultSet.getString("ID"));
				System.out.println(max_id);
			}
		} catch (NumberFormatException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

			
		// -------------------------------------------
		// -- create SQL Statement - CREATE ADDRESS --
		// -------------------------------------------
		sql_statement = this.patient2db.createAddressSqlStatement(patient, max_id);

			
		// ----------------------------------------
		// -- execute SQL Query - CREATE ADDRESS --
		// ----------------------------------------
		this.db_service.executeQuery(con, sql_statement, false);		// false = kein Return Wert

		
		// -------------------------------------------
		// -- create SQL Statement - CREATE PATIENT --
		// -------------------------------------------
		sql_statement = this.patient2db.createPatientSqlStatement(patient, max_id);

		
		// ----------------------------------------
		// -- execute SQL Query - CREATE PATIENT --
		// ----------------------------------------
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

	
// **********************
// **** Read Patient ****
// **********************
	@Override
	public Patient readPatientInDB(int id) throws RemoteException {
		System.out.println("\nPatientServiceImpl.readPatientInDB()");
		
		Patient patient = new Patient();
		
		// -----------------------------------------
		// -- create SQL Statement - READ PATIENT --
		// -----------------------------------------
		sql_statement = this.patient2db.readPatientSqlStatement(id);

		
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
				patient.setId(Integer.parseInt(resultSet.getString("ID")));
				patient.setFirstname(resultSet.getString("FIRSTNAME"));
				patient.setLastname(resultSet.getString("LASTNAME"));	
				patient.setGender(resultSet.getString("GENDER"));
				patient.setDayofbirth(resultSet.getDate("DAYOFBIRTH"));
				patient.setLastvisit(resultSet.getDate("LASTVISIT"));
				patient.setAddressid(Integer.parseInt(resultSet.getString("ADDRESSID")));
				patient.setStreet(resultSet.getString("STREET"));
				patient.setHousenumber(resultSet.getString("HOUSENUMBER"));
				patient.setPostalcode(resultSet.getString("POSTALCODE"));
				patient.setCity(resultSet.getString("CITY"));
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
		
		
		// ---------------------------
		// -- return Patient object --
		// ---------------------------
		return patient;
	}


// ************************
// **** Update Patient ****
// ************************	
	@Override
	public void updatePatientInDB(Patient patient) throws RemoteException {
		System.out.println("\nPatientServiceImpl.updatePatientInDB()");

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
		sql_statement = this.patient2db.updatePatientSqlStatement(patient);
		
		
		// ----------------------------------------
		// -- execute SQL Query - UPDATE PATIENT --
		// ----------------------------------------
		this.db_service.executeQuery(con, sql_statement, false);
		

		// --------------------------
		// -- create SQL Statement --
		// --------------------------
		sql_statement = this.patient2db.updateAddressSqlStatement(patient);
		
		
		// ----------------------------------------
		// -- execute SQL Query - UPDATE ADDRESS --
		// ----------------------------------------
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

	
// ************************
// **** Delete Patient ****
// ************************
	@Override
	public void deletePatientInDB(int id, int addressid) throws RemoteException {
		System.out.println("\nPatientServiceImpl.deletePatientInDB()");
		
		
		// --------------------------
		// -- create SQL Statement --
		// --------------------------
		sql_statement = this.patient2db.deletePatientSqlStatement(id, addressid);
		
		
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
		
		
		// ----------------------------------------
		// -- execute SQL Query - DELETE PATIENT --
		// ----------------------------------------
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
	
// *****************************
// **** Get Patient List DB ****
// *****************************
	public ArrayList<Patient> getPatientListFromDB() throws RemoteException {
		System.out.println("\nPatientServiceImpl.getPatientListFromDB()");
	

		// --------------------------
		// -- create SQL Statement --
		// --------------------------
		sql_statement = this.patient2db.getPatientListSqlStatement();
		
		
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
		
		// -------------------------
		// -- create Patient List --
		// -------------------------
		ArrayList<Patient> patientList = new ArrayList<Patient>();
		
		try {
			while(resultSet.next()) {				// Pro Datensatz
				
				Patient patient = new Patient();
				patient.setId(Integer.parseInt(resultSet.getString("ID")));
				patient.setFirstname(resultSet.getString("FIRSTNAME"));
				patient.setLastname(resultSet.getString("LASTNAME"));	
				patient.setGender(resultSet.getString("GENDER"));
				patient.setDayofbirth(resultSet.getDate("DAYOFBIRTH"));
				patient.setLastvisit(resultSet.getDate("LASTVISIT"));
				patient.setAddressid(Integer.parseInt(resultSet.getString("ADDRESSID")));
				patient.setStreet(resultSet.getString("STREET"));	
				patient.setHousenumber(resultSet.getString("HOUSENUMBER"));	
				patient.setPostalcode(resultSet.getString("POSTALCODE"));	
				patient.setCity(resultSet.getString("CITY"));	
				
				// -----------------------------------------
				// -- Save Patient object in PatientListe --
				// -----------------------------------------
				patientList.add(patient);
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
		
				
		// ------------------------
		// -- return PatientList --
		// ------------------------	
				
		System.out.println("PatientServiceImpl.getPatientListFromDB()");		//debug
		System.out.println("-----Inhalt der ArrayListe aus der DB:");			//debug
				  	
		return patientList;
	}

// *******************************
// **** Write Patient List DB ****
// *******************************
	@Override
	public void writePatientListToDB(ArrayList<Patient> patientList) throws RemoteException {
		System.out.println("\nPatientServiceImpl.writePatientListToDB()"); // debug
	
		
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
		
		
		// ------------------------------------------		
		// -- Import Patients step by step into DB --
		// ------------------------------------------
        for (Patient patient : patientList) {
        	
    		// -----------------------------------
    		// -- reset variable for Address ID --
    		// -----------------------------------
    		max_id = 0;
    		
    		
    		// -------------------------------------------------
    		// -- create SQL Statement - MAX(id) FROM ADDRESS --
    		// -------------------------------------------------
    		sql_statement = this.patient2db.selectMaxIdFromAddressSqlStatement();
    		
    		
    		// ----------------------------------------------
    		// -- execute SQL Query - MAX(id) FROM ADDRESS --
    		// ----------------------------------------------
    		resultSet = this.db_service.executeQuery(con, sql_statement, true);
    		
    		try {
    			while(resultSet.next()) {
    				max_id = Integer.parseInt(resultSet.getString("ID"));
    				System.out.println(max_id);
    			}
    		}
    		catch (NumberFormatException e1) {
    			e1.printStackTrace();
    		}
    		catch (SQLException e1) {
    			e1.printStackTrace();
    		}

    		
    		// -------------------------------------------
    		// -- create SQL Statement - CREATE ADDRESS --
    		// -------------------------------------------
    		sql_statement = this.patient2db.createAddressSqlStatement(patient, max_id);

    		
    		// ----------------------------------------
    		// -- execute SQL Query - CREATE ADDRESS --
    		// ----------------------------------------
    		this.db_service.executeQuery(con, sql_statement, false);		// false = kein Return Wert

    	
    		// -------------------------------------------
    		// -- create SQL Statement - CREATE PATIENT --
    		// -------------------------------------------
    		sql_statement = this.patient2db.createPatientSqlStatement(patient, max_id);
    		

    		// ----------------------------------------
    		// -- execute SQL Query - CREATE PATIENT --
    		// ----------------------------------------
    		this.db_service.executeQuery(con, sql_statement, false);		// false = kein Return Wert
        }
		
  
		// ---------------------------------------
		// -- close DB Connection and ResultSet --
		// ---------------------------------------
		try {
			this.db_service.disconnect(con, resultSet);		//'con' = connection, 'resultSet' oder 'null' (wenn kein resultSet geschlossen werden muss)
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
/****************/		
/**** Search ****/
/****************/
	
// *******************************
// **** Search Patient by Name ***
// *******************************	
	
	@Override
	public ArrayList<Patient> searchPatientByNameInDB(String lastname) throws RemoteException {
		System.out.println("\nPatientServiceImpl.searchPatientByNameInDB()");
		
		
		// -------------------------------------------
		// -- create SQL Statement - SEARCH PATIENT --
		// -------------------------------------------
		sql_statement = this.patient2db.searchPatientByNameSqlStatement(lastname);
		
		
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
		
		// ----------------------------------------
		// -- execute SQL Query - SEARCH PATIENT --
		// ----------------------------------------
		resultSet = this.db_service.executeQuery(con, sql_statement, true);
		
				
		// ------------------------
		// -- create PatientList --
		// ------------------------	
		ArrayList<Patient> patientList = new ArrayList<Patient>();
		
		try {
			while(resultSet.next()) {				// Pro Datensatz
				
				Patient patient = new Patient();
				patient.setId(Integer.parseInt(resultSet.getString("ID")));
				patient.setFirstname(resultSet.getString("FIRSTNAME"));
				patient.setLastname(resultSet.getString("LASTNAME"));	
				patient.setGender(resultSet.getString("GENDER"));
				patient.setDayofbirth(resultSet.getDate("DAYOFBIRTH"));
				patient.setLastvisit(resultSet.getDate("LASTVISIT"));
				patient.setAddressid(Integer.parseInt(resultSet.getString("ADDRESSID")));
				patient.setStreet(resultSet.getString("STREET"));	
				patient.setHousenumber(resultSet.getString("HOUSENUMBER"));	
				patient.setPostalcode(resultSet.getString("POSTALCODE"));	
				patient.setCity(resultSet.getString("CITY"));	
				
				// -----------------------------------------
				// -- Save Patient object in PatientListe --
				// -----------------------------------------
				patientList.add(patient);
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
			this.db_service.disconnect(con, resultSet);		
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		// ------------------------
		// -- return PatientList --
		// ------------------------	
		return patientList;
	}

	
/**************/	
/**** File ****/
/**************/
 
	
// ***********************************
// **** Read Patient List from CSV ***
// ***********************************	
	@Override
	public ArrayList<Patient> readPatientListFromCSV() throws RemoteException, ParseException {
		System.out.println("\nPatientServiceImpl.readPatientListFromCSV()");
		
		String filelocation = this.file_service.locateFile();					//hat Rueckgabetyp pathtofile
		
		// ----------------------------------
		// -- call & return list from CSV  --
		// ----------------------------------	
		return this.patient2csv.readCSVFile(filelocation);
	}

	
// ***********************************
// **** Write Patient List to CSV ****
// ***********************************
	@Override
	public void writePatientListToCSV(ArrayList<Patient> patientList) throws RemoteException {
		System.out.println("\nPatientServiceImpl.writePatientListToCSV()");	//debug
		
		String filelocation = this.file_service.locateFile();					//Datei ermitteln
		
		boolean permit = this.file_service.permitFileGeneration();				//Datei vorhanden?
								
		if ( permit == true){												//Wenn Erlaubnis(=Datei nicht vorhanden)
			
			System.out.println("-Calling File Generation-Method....");
			
			this.patient2csv.generateCsvFile(patientList, filelocation);	//an dieser Stelle Weiterleitung, Verarbeitung eine Ebene tiefer
		}
		else {
			System.out.println("*** ACHTUNG ***");
			System.out.println("Abbruch, da Datei bereits vorhanden - bitte Dateinamen aendern.");
		}
		
	}
	
	
/****************/
/**** Status ****/
/****************/

// **********************************
// **** Check Date of last visit ****
// **********************************
	@Override
	public int checkDateOfLastVisit(Date lastvisit) throws RemoteException {
		System.out.println("\nPatientServiceImpl.checkDateOfLastVisit");
		
		// ------------------------------------
		// -- register Patient Card (yes/no) --
		// ------------------------------------
		int registerPatientCard = 0; // 0 = no registration required
		
		// -------------------------
		// -- Declare Date Format --
		// -------------------------
		SimpleDateFormat year = new SimpleDateFormat("yyyy");
		SimpleDateFormat month = new SimpleDateFormat("MM");

		// ----------------------------------------
		// -- Get 'year' of last visit and today --
		// ----------------------------------------
		int yearOfLastVisit = Integer.parseInt(year.format(lastvisit));
		System.out.println(yearOfLastVisit);
		int yearOfToday = Integer.parseInt(year.format(today));
		System.out.println(yearOfToday);
				
		// -----------------------------------------
		// -- Get 'month' of last visit and today --
		// -----------------------------------------
		int monthOfLastVisit = Integer.parseInt(month.format(lastvisit));
		System.out.println(monthOfLastVisit);
		int monthOfToday = Integer.parseInt(month.format(today));
		System.out.println(monthOfToday);
		
		
		// ------------------------------------------------
		// -- if year of last visit unequal to this year --
		// ------------------------------------------------
		if (yearOfLastVisit != yearOfToday) {
			registerPatientCard = 1; // -- registration required 
			System.out.println("year is different");
		}
		// -----------------------------------------------------------
		// -- 1st Quarter: Result of (3 - Current Month) = (0 to 2) --
		// -----------------------------------------------------------
		else if ((3 - monthOfToday) >= 0 && (3 - monthOfToday) <= 2 ) {
			
			if (monthOfLastVisit >= 1 && monthOfLastVisit <= 3) {
				// ------------------------------
				// -- no registration required --
				// ------------------------------
				registerPatientCard = 0;
				System.out.println("1st Quarter - no Card required");
			}	
			else {
				// ---------------------------
				// -- registration required --
				// ---------------------------
				registerPatientCard = 1;
				System.out.println("1st Quarter - Card required");
			}
		}
		
		// -----------------------------------------------------------
		// -- 2nd Quarter: Result of (6 - Current Month) = (0 to 2) --
		// -----------------------------------------------------------
		else if ((6 - monthOfToday) >= 0 && (6 - monthOfToday) <= 2 ) {
			
			if (monthOfLastVisit >= 4 && monthOfLastVisit <= 6) {
				// ------------------------------
				// -- no registration required --
				// ------------------------------
				registerPatientCard = 0;			
				System.out.println("2nd Quarter - no Card required");
			}	
			else {
				// ---------------------------
				// -- registration required --
				// ---------------------------
				registerPatientCard = 1;
				System.out.println("2nd Quarter - Card required");
			}
		}

		// -----------------------------------------------------------
		// -- 3rd Quarter: Result of (9 - Current Month) = (0 to 2) --
		// -----------------------------------------------------------
		else if ((9 - monthOfToday) >= 0 && (9 - monthOfToday) <= 2 ) {
			if (monthOfLastVisit >= 7 && monthOfLastVisit <= 9) {
				// ------------------------------
				// -- no registration required --
				// ------------------------------
				registerPatientCard = 0;
				System.out.println("3rd Quarter - no Card required");
			}	
			else {
				// ---------------------------
				// -- registration required --
				// ---------------------------
				registerPatientCard = 1;
				System.out.println("3rd Quarter - Card required");
			}
		}

		// ------------------------------------------------------------
		// -- 4th Quarter: Result of (12 - Current Month) = (0 to 2) --
		// ------------------------------------------------------------
		else if ((12 - monthOfToday) >= 0 && (12 - monthOfToday) <= 2 ) {

			if (monthOfLastVisit >= 10 && monthOfLastVisit <= 12) {
				// ------------------------------
				// -- no registration required --
				// ------------------------------
				registerPatientCard = 0; 
				System.out.println("4th Quarter - no Card required");
			}	
			else {
				// ---------------------------
				// -- registration required --
				// ---------------------------
				registerPatientCard = 1;
				System.out.println("4th Quarter - Card required");
			}
		}
		
		// ---------------------------------------------
		// -- return if Card registration is required --
		// ---------------------------------------------
		System.out.println(registerPatientCard);
		return registerPatientCard;
		
		/*

		Hintergedanke: Liegt der aktuelle Monat in einem Quartal, dann ergibt 
		die Aufgabe (Grˆﬂter Monat in einem Quartal minus dem aktuellen Monat)
		immer einen Wert zwischen 0 und 2. 
		
		3-1 =  2	6-3 =  3	9- 6 =  3	12- 8 = 4 
		3-2 =  1	6-4 =  2	9- 7 =  2	12- 9 = 3
		3-3 =  0	6-5 =  1	9- 8 =  1	12-10 = 2
		3-4 = -1	6-6 =  0	9- 9 =  0	12-11 = 1
		3-5 = -2	6-7 = -1	9-10 = -1   12-12 = 0

		*/
	}

}

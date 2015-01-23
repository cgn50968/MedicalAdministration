package de.rho.server.patient.control;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;

import de.rho.server.dao.boundary.InDaoToDB;
import de.rho.server.dao.boundary.InDaoToFile;
import de.rho.server.dao.control.FaDaoService;
import de.rho.server.patient.boundary.InPatientService;
import de.rho.server.patient.entity.Patient;

/*
import java.util.Date;
  
public class DateDemo {
   public static void main(String args[]) {
       // Instantiate a Date object
       Date date = new Date();
        
       // display time and date using toString()
       System.out.println(date.toString());
   }
}
 */


/**
 * @author Heiko, Roger
 * @version 1.1
 * 
 * Implementierung des Services Patient
 * Uebergabestation der Servicemethoden an konkrete Klassen 
 *
 */

public class PatientServiceImpl extends UnicastRemoteObject implements InPatientService {
	
	
	//Alternative:
	//interface variable = factory.methode...
	
	//Trennung von Methoden-Service (File, DB) und Connection-Service (File, DB)
	

	/** Methoden-Services **/
	private PatientToCSV patient2csv; 	 				//Deklaration fuer CSV
	private PatientToDB patient2db;				 		//Deklaration fuer DB
	
	
	/** Connection-Services **/
	private InDaoToDB db_service = FaDaoService.getDaoToDBService();
	private InDaoToFile file_service = FaDaoService.getDaoToFileService(); //to do: benutze file_service-connection
	private ResultSet resultSet;
	private String sql_statement;
	private Integer max_id;
	
	protected PatientServiceImpl() throws RemoteException {
		super();
		this.patient2csv = new PatientToCSV(); //Initialisierung/Instanziierung der "Objektverbindung" CSV (Defaultkonstruktor)
		this.patient2db = new PatientToDB();   //Initialisierung/Instanziierung der "Objektverbindung" DB (Defaultkonstruktor)

	}

	
	/**** TEST ****/
	
	public String createPatient(String test) throws RemoteException {
		return test;
	}
	
/**************/
/**** CRUD ****/
/**************/
	
// ************************
// **** create Patient ****
// ************************
	@Override
	public void createPatientInDB(Patient patient) throws RemoteException {
		System.out.println("PatientServiceImpl.createPatientInDB()");

		// ************************
		// **** reset variable ****
		// ************************
		max_id = 0;
		
		// *****************************************************
		// **** create SQL Statement - MAX(id) FROM ADDRESS ****
		// *****************************************************
		sql_statement = this.patient2db.selectMaxIdFromAddressSqlStatement();
		
		
		// ****************************************
		// **** open Connection to H2 Database ****  
		// ****************************************
		Connection con = null;
		try {
			con = db_service.connect();
		} 
		catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		// **************************************************
		// **** execute SQL Query - MAX(id) FROM ADDRESS ****
		// **************************************************
		resultSet = db_service.executeQuery(con, sql_statement, true);
		
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

		
		// ***********************************************
		// **** create SQL Statement - CREATE ADDRESS ****
		// ***********************************************
		sql_statement = this.patient2db.createAddressSqlStatement(patient, max_id);

		
		// ********************************************
		// **** execute SQL Query - CREATE ADDRESS ****
		// ********************************************
		db_service.executeQuery(con, sql_statement, false);		// false = kein Return Wert

	
		// ***********************************************
		// **** create SQL Statement - CREATE PATIENT ****
		// ***********************************************
		sql_statement = this.patient2db.createPatientSqlStatement(patient, max_id);
		

		// ********************************************
		// **** execute SQL Query - CREATE PATIENT ****
		// ********************************************
		db_service.executeQuery(con, sql_statement, false);		// false = kein Return Wert
				
		
		// ***************************************
		// **** close DB Connection/ResultSet ****
		// ***************************************
		try {
			db_service.disconnect(con, resultSet);		//'con' = connection, 'resultSet' oder 'null' (wenn kein resultSet geschlossen werden muss)
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
		System.out.println("PatientServiceImpl.readPatientInDB()");
		
		Patient patient = new Patient();
		
		// **** SQL Statement erstellen ****
		sql_statement = this.patient2db.readPatientSqlStatement(id);
				
		// **** Connection zur Datenbank oeffnen ****  
		Connection con = null;					
		try {
			con = db_service.connect();
		} 
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		
		
		// **** SQL Query ausfuehren, ResultSet erwartet ****
		resultSet = db_service.executeQuery(con, sql_statement, true);
		
		
		//**** ResultSet in Patient Objekt speichern ****
		try {
			while(resultSet.next()) {
				patient.setId(Integer.parseInt(resultSet.getString("ID")));
				patient.setFirstname(resultSet.getString("FIRSTNAME"));
				patient.setLastname(resultSet.getString("LASTNAME"));	
				patient.setGender(resultSet.getString("GENDER"));
				patient.setGender(resultSet.getString("DAYOFBIRTH"));
				patient.setAddressid(Integer.parseInt(resultSet.getString("ADDRESSID")));
				patient.setLastvisit(resultSet.getString("LASTVISIT"));
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
		
		
		// **** Connection zur H2 Datenbank schliessen ****
		try {
			db_service.disconnect(con, resultSet);		//con und resultSet schließen
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		// **** Rückgabe des Patientenobjekts **** 
		return patient;
	}


// ************************
// **** Update Patient ****
// ************************	
	@Override
	public void updatePatientInDB(Patient patient) throws RemoteException {
		System.out.println("PatientServiceImpl.updatePatientInDB()");

		// **** SQL Statement erstellen ****
		sql_statement = this.patient2db.updatePatientSqlStatement(patient);

		System.out.println(sql_statement);
		
		// **** Connection zur H2 Datenbank oeffnen ****  
		Connection con = null;
		try {
			con = db_service.connect();
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		
		
		// **** SQL Query ausfuehren ****
		db_service.executeQuery(con, sql_statement, false);
		
		
		// **** Connection zur H2 Datenbank schliessen ****
		try {
			db_service.disconnect(con, null);		//null als Parameter, damit der zweite Parameter bestehen bleiben kann.
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}

	
// ************************
// **** Delete Patient ****
// ************************
	@Override
	public void deletePatientInDB(int id) throws RemoteException {
		System.out.println("PatientServiceImpl.deletePatientInDB()");
		
		// **** SQL Statement erstellen ****
		sql_statement = this.patient2db.deletePatientSqlStatement(id);
		
		
		// **** Connection zur Datenbank oeffnen ****  
		Connection con = null;
		try {
			con = db_service.connect();
		} 
		catch (FileNotFoundException e) {
			e.printStackTrace();
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
		
		
		// **** SQL Query ausfuehren ****
		db_service.executeQuery(con, sql_statement, false);
		
		
		// **** Connection zur Datenbank schliessen ****
		try {
			db_service.disconnect(con, null);		
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
/**************/
/**** List ****/
/**************/
	
// **************************
// **** Get Patient List ****
// **************************
	public ArrayList<Patient> getPatientListFromDB() throws RemoteException {
		System.out.println("PatientServiceImpl.getPatientListFromDB()");
	

		// **** SQL Statement erstellen ****
		sql_statement = this.patient2db.getPatientListSqlStatement();
		
		
		// **** Connection zur Datenbank oeffnen ****  
		Connection con = null;					
		try {
			con = db_service.connect();
		} 
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		
		
		// **** SQL Query ausfuehren, ResultSet erwartet ****
		resultSet = db_service.executeQuery(con, sql_statement, true);
		
				
		// **** Patient List erstellen ****
		ArrayList<Patient> patientList = new ArrayList<Patient>();
		
		try {
			while(resultSet.next()) {				// Pro Datensatz
				
				Patient patient = new Patient();
				patient.setId(Integer.parseInt(resultSet.getString("ID")));
				patient.setFirstname(resultSet.getString("FIRSTNAME"));
				patient.setLastname(resultSet.getString("LASTNAME"));	
				patient.setGender(resultSet.getString("GENDER"));
				patient.setDayofbirth(resultSet.getString("DAYOFBIRTH"));
				patient.setLastvisit(resultSet.getString("LASTVISIT"));	
				patient.setAddressid(Integer.parseInt(resultSet.getString("ADDRESSID")));
				patient.setStreet(resultSet.getString("STREET"));	
				patient.setHousenumber(resultSet.getString("HOUSENUMBER"));	
				patient.setPostalcode(resultSet.getString("POSTALCODE"));	
				patient.setCity(resultSet.getString("CITY"));	
				
				// **** Uebergabe des Patienten Objekts an die PatientenListe
				patientList.add(patient);
			}
		}
		catch (NumberFormatException e) {
			e.printStackTrace();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		// **** Connection zur Datenbank schliessen ****
		try {
			db_service.disconnect(con, resultSet);		
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		// **** Rückgabe der ArrayList (Patientenliste) 
		return patientList;
	}
		
/****************/		
/**** Search ****/
/****************/
	
	
	@Override
	public Patient searchPatientByIdInDB(int id) throws RemoteException {
		System.out.println("Impl: leite 'searchByIdInDB' an 2DB weiter");
		return this.searchPatientByIdInDB(id);
	}
	
	@Override
	public ArrayList<Patient> searchPatientByNameInDB(String searchString) throws RemoteException {
		System.out.println("Impl: leite 'searchByNameInDB' an 2DB weiter");
		return this.searchPatientByNameInDB(searchString);
	}

/**************/	
/**** File ****/
/**************/
	
	@Override
	public ArrayList<Patient> readPatientListFromCSV(String list) throws RemoteException {
		System.out.println("Impl: leite 'readList' an 2CSV weiter");
		//return this.patient2csv.readPatientListFromCSV(ArrayList(list));
		return this.patient2csv.readPatientFromCSV(list);
	}

// ***********************************
// **** Write Patient List to CSV ****
// ***********************************
	@Override
	public void writePatientListToCSV(ArrayList<Patient> patientList) throws RemoteException {
		System.out.println("PatientServiceImpl.writePatientListToCSV()");
		this.patient2csv.generateCsvFile(patientList);					//an dieser Stelle nur Weiterleitung, normale Uebergabe, da Verarbeitung eine Ebene tiefer
	}
	
/****************/
/**** Status ****/
/****************/
	
	@Override
	public void checkDate(String searchdate) throws RemoteException {
		// TODO wo soll die MEthode implementiert werden, eigentlich im "search"....
		System.out.println("Impl: noch keine Weiterleitung implementiert");
		
	}

}

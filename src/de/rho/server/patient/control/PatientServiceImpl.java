package de.rho.server.patient.control;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import de.rho.server.patient.boundary.InPatientService;
import de.rho.server.patient.entity.Patient;
import java.util.Date;

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

public class PatientServiceImpl extends UnicastRemoteObject implements InPatientService{
	
	/**** ??? Benoetigen wir das ??? ****/
	// Hier muss die Funktion grundsätzlich umgeschrieben werden.
	// Die Erklärung folgt in der Klassenbeschreibung
	
	// interface variable = factory.methode...
	
	private PatientToCSV patient2csv; 	 				//Deklaration fuer CSV
	private PatientToDB patient2db;				 		//Deklaration fuer DB
	
	
	protected PatientServiceImpl() throws RemoteException {
		super();
		this.patient2csv = new PatientToCSV(); //Initialisierung/Instanziierung der "Objektverbindung" CSV
		this.patient2db = new PatientToDB();   //Initialisierung/Instanziierung der "Objektverbindung" DB		
	}

	
	/**** TEST ****/
	
	public String createPatient(String test) throws RemoteException {
		return test;
	}
	
	
	/**** CRUD ****/
	
	@Override
	public void createPatientInDB(Patient patient) throws RemoteException {
		System.out.println("Impl: leite 'create' an 2DB weiter");
		
		// call createPatientDB
		this.patient2db.createPatientDB(patient);
	}

	
	@Override
	public Patient readPatientInDB(int id) throws RemoteException {
		System.out.println("Impl: leite 'read' an 2DB weiter");
		//return this.patient2db.readPatientDB(id);
		
		Date date = new Date();
		
		//Konstruktor Patient
		Patient patient = new Patient(id);
		patient.setFirstname("Hans");
		patient.setLastname("Albers");
		patient.setLastvisit(date);
		return patient;
	}

	
	
	@Override
	public void updatePatientInDB(Patient patient) throws RemoteException {
		System.out.println("Impl: leite 'update' an 2DB weiter");
		this.patient2db.updatePatientDB(patient);
	}

	@Override
	public void deletePatientInDB(int id) throws RemoteException {
		System.out.println("Impl: leite 'delete' an 2DB weiter");
		this.patient2db.deletePatientDB(id);
	}

	
	/**** Search ****/
	
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

	
	/**** File ****/

	@Override
	public ArrayList<Patient> readPatientListFromCSV(String list) throws RemoteException {
		System.out.println("Impl: leite 'readList' an 2CSV weiter");
		//return this.patient2csv.readPatientListFromCSV(ArrayList(list));
		return this.patient2csv.readPatientFromCSV(list);
	}

	@Override
	public void writePatientListToCSV(ArrayList<Patient> list) throws RemoteException {
		System.out.println("Impl: leite 'writeList' an 2CSV weiter");
		this.patient2csv.writePatientToCSV(list);
	}
	

	/**** Status ****/

	@Override
	public void checkDate(String searchdate) throws RemoteException {
		// TODO wo soll die MEthode implementiert werden, eigentlich im "search"....
		System.out.println("Impl: noch keine Weiterleitung implementiert");
		
	}

}

package de.rho.server.PatientService.control;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import de.rho.server.PatientService.boundary.InPatientService;
import de.rho.server.PatientService.entity.Patient;



/**
 * @author Heiko
 * @version 1.0
 * 
 * Implementierung des Services Patient
 * Uebergabestation der Servicemethoden an konkrete Klassen 
 *
 */

public class PatientServiceImpl extends UnicastRemoteObject implements InPatientService{
	
	private PatientServicetoCSV patient2csv; 	 				//Deklaration für CSV
	private PatientServicetoDB patient2db;				 		//Deklaration für DB
	
	
	protected PatientServiceImpl() throws RemoteException {
		super();
		this.patient2csv = new PatientServicetoCSV(); //Initialisierung/Instanziierung der "Objektverbindung" CSV
		this.patient2db = new PatientServicetoDB();   //Initialisierung/Instanziierung der "Objektverbindung" DB		
	}

	@Override
	public void createPatientDB(Patient patient) throws RemoteException {
		System.out.println("Impl: leite 'create' an 2DB weiter");
		this.patient2db.createPatientDB(patient);
	}

	@Override
	public Patient readPatientDB(int id) throws RemoteException {
		System.out.println("Impl: leite 'read' an 2DB weiter");
		return this.patient2db.readPatientDB(id);
	}

	@Override
	public void updatePatientDB(Patient patient) throws RemoteException {
		System.out.println("Impl: leite 'update' an 2DB weiter");
		this.patient2db.updatePatientDB(patient);
	}

	@Override
	public void deletePatientDB(int id) throws RemoteException {
		System.out.println("Impl: leite 'delete' an 2DB weiter");
		this.patient2db.deletePatientDB(id);
	}

	@Override
	public ArrayList<Patient> readPatientListfromFile(String list) throws RemoteException {
		System.out.println("Impl: leite 'readList' an 2CSV weiter");
		return this.patient2csv.readPatientListfromFile(list);
	}

	@Override
	public void writePatientListtoFile(ArrayList<Patient> list) throws RemoteException {
		System.out.println("Impl: leite 'writeList' an 2CSV weiter");
		this.patient2csv.writePatientListtoFile(list);
	}

	@Override
	public ArrayList<Patient> readPatientListDB(String list) throws RemoteException {
		System.out.println("Impl: leite 'readListDB' an 2DB weiter");
		return this.patient2db.readPatientListDB(list);
	}

	@Override
	public ArrayList<Patient> searchPatientListDB(String searchString) throws RemoteException {
		System.out.println("Impl: leite 'searchListDB' an 2DB weiter");
		return this.searchPatientListDB(searchString);
	}

	@Override
	public void checkDate(String searchdate) throws RemoteException {
		// TODO wo soll die MEthode implementiert werden, eigentlich im "search"....
		System.out.println("Impl: noch keine Weiterleitung implementiert");
		
	}

}

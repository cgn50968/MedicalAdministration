package de.rho.server.patient.boundary;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;

import de.rho.server.patient.entity.Patient;


/**
 * @author Andreas Röwert, Heiko Herder, Roger Ordon
 * @version 1.4
 * 
 * Interface als externe Schnittstelle fuer (RMI)
 * Methodenvorlage fuer PatientService
 *
 */

public interface InPatientService extends Remote {
	
/**************/
/**** CRUD ****/
/**************/
	
	// -- For PatientService.createPatientDB
	public void createPatientInDB(Patient patient) throws RemoteException;
	
	// -- For PatientService.readPatientDB
	public Patient readPatientInDB(int id) throws RemoteException;
	
	// -- For PatientService.updatePatientDB
	public void updatePatientInDB(Patient patient) throws RemoteException;
	
	// -- For PatientService.deletePatientDB
	public void deletePatientInDB(int id, int addressid) throws RemoteException;
	
	
/**************/
/**** List ****/
/**************/
	
	// -- For PatientServiceImpl.getPatientListFromDB
	public ArrayList<Patient> getPatientListFromDB() throws RemoteException;
	
	// -- For PatientServiceImpl.writePatientListToDB
	public void writePatientListToDB(ArrayList<Patient> patientList) throws RemoteException;

	
/****************/
/**** Search ****/
/****************/

	// -- For PatientServiceImpl.searchPatientByNameInDB
	public ArrayList<Patient> searchPatientByNameInDB(String searchString) throws RemoteException;
	
	
/**************/
/**** File ****/
/**************/
	
	// -- For PatientServiceImpl.readPatientListFromCSV
	public ArrayList<Patient> readPatientListFromCSV() throws RemoteException, ParseException;

	// -- For PatientServiceImpl.writePatientListToCSV
	public void writePatientListToCSV(ArrayList<Patient> patientList) throws RemoteException;

	
/****************/
/**** Status ****/
/****************/
	
	// -- For PatientServiceImpl.checkDate
	public int checkDateOfLastVisit(Date lastvisit) throws RemoteException;

	
/************/
/**** DB ****/
/************/
	
	// -- For PatientServiceImpl.createTablesInDB
	public void createTablesInDB() throws RemoteException; 
	
	

}

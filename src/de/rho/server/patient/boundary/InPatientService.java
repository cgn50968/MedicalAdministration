package de.rho.server.patient.boundary;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import de.rho.server.patient.entity.Patient;


/**
 * @author Andreas Röwert, Heiko Herder, Roger Ordon
 * @version 1.1
 * 
 * Interface als externe Schnittstelle fuer (RMI)
 * Methodenvorlage fuer PatientService
 *
 */

public interface InPatientService extends Remote {
	
	
	/**** TEST ****/
	
	String createPatient(String patient) throws RemoteException;
	
	/**** CRUD ****/
	
	//For PatientService.createPatientDB
	public void createPatientInDB(Patient patient) throws RemoteException;
	
	//For PatientService.readPatientDB
	public Patient readPatientInDB(int id) throws RemoteException;
	
	//For PatientService.updatePatientDB
	public void updatePatientInDB(Patient patient) throws RemoteException;
	
	//For PatientService.deletePatientDB
	public void deletePatientInDB(int id) throws RemoteException;
	
		
	/**** Search ****/
	
	//For PatientServiceImpl.searchPatientByIdInDB	
	public Patient searchPatientByIdInDB(int id) throws RemoteException;

	//For PatientServiceImpl.searchPatientByNameInDB
	public ArrayList<Patient> searchPatientByNameInDB(String searchString) throws RemoteException;
	
	
	/**** File ****/

	//For PatientServiceImpl.searchPatientListFromCSV
	public ArrayList<Patient> readPatientListFromCSV(String list) throws RemoteException;

	//For PatientServiceImpl.writePatientListToCSV
	public void writePatientListToCSV(ArrayList<Patient> list) throws RemoteException;

	
	/**** Status ****/

	//For PatientServiceImpl.checkDate
	public void checkDate(String searchdate) throws RemoteException;

		
	
	
	

}

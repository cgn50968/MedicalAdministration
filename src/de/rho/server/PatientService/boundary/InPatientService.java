package de.rho.server.PatientService.boundary;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import de.rho.server.PatientService.entity.Patient;


/**
 * @author Heiko
 * @version 1.0
 * 
 * Interface als externe Schnittstelle (RMI)
 * Methodenvorlage für PatientService
 *
 */

public interface InPatientService extends Remote {
	
	//DB (aktuell: H2 und MySQL)
	public void createPatientDB(Patient patient) throws RemoteException;
	public Patient readPatientDB(int id) throws RemoteException;
	public void updatePatientDB(Patient patient) throws RemoteException;
	public void deletePatientDB(int id) throws RemoteException;
	public ArrayList<Patient> readPatientListDB(String searchString) throws RemoteException;
	public ArrayList<Patient> searchPatientListDB(String searchString) throws RemoteException;
	
	//File (aktuell: CSV)
	public ArrayList<Patient> readPatientListfromFile(String list) throws RemoteException;
	public void writePatientListtoFile(ArrayList<Patient> list) throws RemoteException;
		
	//Status
	public void checkDate(String searchdate) throws RemoteException;
	
	
	
	

}

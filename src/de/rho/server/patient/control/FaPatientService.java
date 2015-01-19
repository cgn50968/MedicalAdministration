package de.rho.server.patient.control;

import java.rmi.RemoteException;

import de.rho.server.patient.boundary.InPatientService;
import de.rho.server.patient.boundary.InPatientToCSV;
import de.rho.server.patient.boundary.InPatientToDB;

/**
 * @author Heiko Herder, Roger Ordon, Andreas Röwert
 * @version 1.1
 * 
 * Factory zum Erstellen von PatientService-Objekten
 * 
 */

public class FaPatientService {

	//Interface Zuweisung - PatientService
	public static InPatientService getPatientService() throws RemoteException {
		return new PatientServiceImpl();
	}
	
	//Interface Zuweisung - CSV
	public static InPatientToCSV getPatientToCSV() throws RemoteException {
		return new PatientToCSV();
	}
	
	//Interface Zuweisung - DB
	public static InPatientToDB getPatientToDB() throws RemoteException {
		return new PatientToDB();
	}
	
}

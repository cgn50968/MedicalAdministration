package de.rho.server.PatientService.control;

import java.rmi.RemoteException;

import de.rho.server.PatientService.boundary.InPatientService;

/**
 * @author Heiko
 * @version 1.0
 * 
 * Factory zum Erstellen von PatientService-Objekten
 * 
 */

public class FaPatientService {

	public static InPatientService createPatientService() throws RemoteException {
		return new PatientServiceImpl();
	}
	
}

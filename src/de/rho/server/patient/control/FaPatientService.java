package de.rho.server.patient.control;

import java.rmi.RemoteException;

import de.rho.server.patient.boundary.InPatientService;

/**
 * @author Heiko, Roger
 * @version 1.2
 * 
 * Factory zum Erstellen von PatientService-Objekten
 * 
 */

public class FaPatientService {

	// ******************************************************************
	// **** Interface - return PatientServiceImpl for PatientService ****
	// ******************************************************************
	public static InPatientService getPatientService() throws RemoteException {
		
		// --------------------------------------
		// -- return PatientServiceImpl object --
		// --------------------------------------
		return new PatientServiceImpl();
	}
}

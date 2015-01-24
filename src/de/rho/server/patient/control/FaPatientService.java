package de.rho.server.patient.control;

import java.rmi.RemoteException;

import de.rho.server.patient.boundary.InPatientService;

/**
 * @author Heiko Herder, Roger Ordon, Andreas Röwert
 * @version 1.1
 * 
 * Factory zum Erstellen von PatientService-Objekten
 * 
 */

public class FaPatientService {

	// ******************************************************************
	// **** Interface - return PatientServiceImpl for PatientService ****
	// ******************************************************************
	public static InPatientService getPatientService() throws RemoteException {
		return new PatientServiceImpl();
	}
}

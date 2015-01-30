package de.rho.server.medstaff.control;

import java.rmi.RemoteException;

import de.rho.server.medstaff.boundary.InMedStaffService;

/**
 * @author Heiko, Roger
 * @version 1.1
 * 
 * Factory fuer MedStaffImpl
 *
 */



public class FaMedStaffService {

	// ********************************************************************
	// **** Interface - return MedStaffServiceImpl for MedStaffService ****
	// ********************************************************************
	public static InMedStaffService getMedStaffService() throws RemoteException {
		
		// --------------------------------------
		// -- return MedStaffServiceImpl object --
		// --------------------------------------
		return new MedStaffServiceImpl();
	}
}

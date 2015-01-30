package de.rho.server.mt.control;

import java.rmi.RemoteException;

import de.rho.server.mt.boundary.InMtService;

/**
 * @author Heiko, Roger
 * @version 1.0
 * 
 * Factory fuer MtServicesImpl
 *
 */


public class FaMtService {

	// ********************************************************************
	// **** Interface - return MedStaffServiceImpl for MedStaffService ****
	// ********************************************************************
	public static InMtService getMtService() throws RemoteException {
		
		// --------------------------------------
		// -- return MedStaffServiceImpl object --
		// --------------------------------------
		return new MtServiceImpl();
	}
}

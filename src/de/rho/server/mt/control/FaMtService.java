package de.rho.server.mt.control;

import java.rmi.RemoteException;

import de.rho.server.mt.boundary.InMtService;



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

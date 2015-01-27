package de.rho.server.medstaff.boundary;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import de.rho.server.medstaff.entity.MedStaff;

/**
 * @author Andreas Röwert, Heiko Herder, Roger Ordon
 * @version 1.0
 * 
 * Interface als externe Schnittstelle fuer (RMI)
 * Methodenvorlage fuer MedStaffService
 *
 */

public interface InMedStaffService extends Remote {

	
	/**************/
	/**** CRUD ****/
	/**************/
		
		// -- For MedStaffService.createMedStaffDB
		public void createMedStaffInDB(MedStaff medstaff) throws RemoteException;
		
		// -- For MedStaffService.readMedStaffDB
		public MedStaff readMedStaffInDB(int id) throws RemoteException;
		
		// -- For MedStaffService.updateMedStaffDB
		public void updateMedStaffInDB(MedStaff medstaff) throws RemoteException;
		
		// -- For MedStaffService.deleteMedStaffDB
		public void deleteMedStaffInDB(int id, int addressid) throws RemoteException;
		
		
	/**************/
	/**** List ****/
	/**************/
		
		// -- For MedStaffServiceImpl.getMedStaffListFromDB
		public ArrayList<MedStaff> getMedStaffListFromDB() throws RemoteException;
		
}

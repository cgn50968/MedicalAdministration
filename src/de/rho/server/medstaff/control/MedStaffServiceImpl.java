package de.rho.server.medstaff.control;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import de.rho.server.medstaff.boundary.InMedStaffService;
import de.rho.server.medstaff.entity.MedStaff;
import de.rho.server.patient.control.PatientToCSV;
import de.rho.server.patient.control.PatientToDB;

public class MedStaffServiceImpl extends UnicastRemoteObject implements InMedStaffService {

	private static final long serialVersionUID = 123456789;

	
	// *********************
	// **** Constructor ****
	// *********************
	protected MedStaffServiceImpl() throws RemoteException {
		super();
	}
	
	
/**************/
/**** CRUD ****/
/**************/
		
		
// *************************
// **** create MedStaff ****
// *************************	
	@Override	
		public void createMedStaffInDB(MedStaff medstaff) throws RemoteException {
			System.out.println("\nMedStaffService.createMedStaffDB");			
		}
		
		
// ***********************
// **** read MedStaff ****
// ***********************
	@Override	
		public MedStaff readMedStaffInDB(int id) throws RemoteException {
			System.out.println("\nMedStaffService.readMedStaffDB");
			return null;
		}
		
		
// *************************
// **** update MedStaff ****
// *************************
	@Override	
		public void updateMedStaffInDB(MedStaff medstaff) throws RemoteException {
			System.out.println("\nMedStaffService.updateMedStaffDB");			
		}
		
		
// *************************
// **** delete MedStaff ****
// *************************
	@Override	
		public void deleteMedStaffInDB(int id, int addressid) throws RemoteException {
			System.out.println("\nMedStaffService.deleteMedStaffDB");		
		}
		
		
/**************/
/**** List ****/
/**************/

// ***************************
// **** get MedStaff List ****
// ***************************
	@Override	
		public ArrayList<MedStaff> getMedStaffListFromDB() throws RemoteException {
			System.out.println("\nMedStaffServiceImpl.getMedStaffListFromDB");
			return null;
		}
	
	
}

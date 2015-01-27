package de.rho.server;

import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

import de.rho.server.medstaff.boundary.InMedStaffService;
import de.rho.server.medstaff.control.FaMedStaffService;
import de.rho.server.patient.boundary.InPatientService;
import de.rho.server.patient.control.FaPatientService;


/**
 * @author Andreas R�wert, Heiko Herder, Roger Ordon
 * @version 1.1
 * 
 * Server zum Bereitstellen der Services einer Praxisverwaltung
 *
 */

public class MaServer {

	public static void main(String[] args) {
		
		try {
            LocateRegistry.createRegistry(1099);
            InPatientService PatientService = FaPatientService.getPatientService();			// -- create PatientService
            InMedStaffService MedStaffService = FaMedStaffService.getMedStaffService();		// -- create MedStaffService
            //InMTService MTService = FaMTService.getMTService();
                        
            Naming.bind("rmi://localhost:1099/PatientService", PatientService);				// -- Bind Name of PatientService
            Naming.bind("rmi://localhost:1099/MedStaffService", MedStaffService);			// -- Bind Name of MedStaffService
            //Naming.bind("rmi://localhost:1099/MTService", MTService);
 
            System.out.println("------------------------");
            System.out.println("Server is up and running");
            System.out.println("------------------------");
            
        } catch (RemoteException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (MalformedURLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (AlreadyBoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
		
	}

}

package de.rho.server;

import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

import de.rho.server.PatientService.boundary.InPatientService;
import de.rho.server.PatientService.control.FaPatientService;


/**
 * @author Heiko
 * @version 1.0
 * 
 * Server zum Bereitstellen der Services einer Praxisverwaltung
 *
 */

public class PraxisServer {

	
	public static void main(String[] args) {
		
		try {
            LocateRegistry.createRegistry(1099);
            InPatientService PatientService = FaPatientService.createPatientService();
            //InPhysicianService PhysicianService = FaPhysicianService.createPhysicianService();
            //InMTService MTService = FaMTService.createMTService();
            
            
            Naming.bind("rmi://localhost:1099/PatientService", PatientService);
            //Naming.bind("rmi://localhost:1099/PhysicianService", PhysicianService);
            //Naming.bind("rmi://localhost:1099/MTService", MTService);
 
            System.out.println("########################");
            System.out.println("Server is up and running");
            System.out.println("########################");
            
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

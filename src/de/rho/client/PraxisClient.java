package de.rho.client;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import de.rho.server.PatientService.boundary.InPatientService;
import de.rho.server.PatientService.entity.Patient;


/**
 * @author Heiko
 * @version 1.0
 * 
 * Client zum Aufrufen der entfernten Methoden einer Praxisverwaltung
 *
 */

public class PraxisClient {

	
	public static void main(String[] args) {
		
		try {
			InPatientService PatientService = (InPatientService) Naming.lookup("rmi://localhost:1099/PatientService");
			//InPhysicianService PhysicianService = (InPhysicianService) Naming.lookup("rmi://localhost:1099/PhysicianService");
			//InMTService MTService = (InMTService) Naming.lookup("rmi://localhost:1099/MTService");
			
			System.out.println(" ");
			System.out.println("-----------------");
			System.out.println("Client is running");
			System.out.println("-----------------");
			
			//------------------------------------------------------
			//Testaufruf einer Methode eines Services auf dem Server
			//------------------------------------------------------
			
			
			//Use-Case 1: Patient anzeigen (über id)
			PatientService.readPatientDB(12345);
			
			
			//Use-Case 2: Patient erstellen
			//dazu muss ein Objekt Patient erstellt werden und mit der Methode
			//Patient patient = new Patient(52435230);
			//PatientService.createPatientDB(patient);
			
			//der Compiler zeigt keinen Fehler, nur bei der AUführung gibt es einige Exceptions . . . .  
			
			
						
			//Use-Case 3
			//Suche: (nach Nachnamen)
			
			
			
			//usw.
					
		}
		
		
		catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		catch (NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
					
	}
			
	
}

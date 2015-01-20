package de.rho.client;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Date;

import de.rho.server.patient.boundary.InPatientService;
import de.rho.server.patient.entity.Patient;


/**
 * @author Andreas Röwert, Heiko Herder, Roger Ordon
 * @version 1.1
 * 
 * Client zum Aufrufen der entfernten Methoden einer Praxisverwaltung
 *
 */

public class MaClient {

	
	public static void main(String[] args) {
		
		try {
			InPatientService PatientService = (InPatientService) Naming.lookup("rmi://localhost:1099/PatientService");
			//InPhysicianService PhysicianService = (InPhysicianService) Naming.lookup("rmi://localhost:1099/PhysicianService");
			//InMTService MTService = (InMTService) Naming.lookup("rmi://localhost:1099/MTService");
			
			System.out.println("---------------------");
			System.out.println("Client up and running");
			System.out.println("---------------------");
			
			//------------------------------------------------------
			//Testaufruf einer Methode eines Services auf dem Server
			//------------------------------------------------------
			
		
			//Patient patient = new Patient(1234567);
			System.out.println(PatientService.createPatient("TEST Patient"));
			
			
			/**** createPatient ****/
			
			Date date = new Date();
			
			//Konstruktor Patient
			Patient patient = new Patient(1);
			patient.setId(1);
			patient.setFirstname("Hans");
			patient.setLastname("Albers");
			patient.setGender("m");
			patient.setLastvisit(date);
			
			PatientService.createPatientInDB(patient);
		
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

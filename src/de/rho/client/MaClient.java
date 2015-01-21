package de.rho.client;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.text.SimpleDateFormat;
import java.util.Date;

import de.rho.server.patient.boundary.InPatientService;
import de.rho.server.patient.entity.Patient;


/**
 * @author Heiko, Roger
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
			System.out.println("#01 - Start MaClient");
			System.out.println("-----------------------");
			System.out.println("MaClient up and running");
			System.out.println("-----------------------\n");
			
			//------------------------------------------------------
			//Testaufruf einer Methode eines Services auf dem Server
			//------------------------------------------------------
			
		
			//Patient patient = new Patient(1234567);
			System.out.println(PatientService.createPatient("TEST Patient"));
			
			
			/**** create Patient ****/
			System.out.println("#02 - Create new Patient");
				// Aktuelles Datum
				Date today = new Date();
				SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
				String date = DATE_FORMAT.format(today);
				
				// Objekt Patient - nicht Konstruktor; der ist nur der Teil rechts von "new", guck mal in die Entity, dort verlangt er einen int-Wert. da war ich mir nicht sicher, brauchen wir den Eingabeparameter ueberhaupt im Konstruktor?
				Patient patient = new Patient();
				patient.setId(1); //hier wird der eigentliche Attributwert fuer ID gesetzt
				patient.setFirstname("Theo");
				patient.setLastname("Lingen");
				patient.setGender("m");
				patient.setAddressid(0);
				patient.setLastvisit(date);
				
				// Funktionsaufruf: Benutzer erstellen
				PatientService.createPatientInDB(patient);
			
			/**** read Patient ****/
		
				// Funktionsaufruf: Benutzer mit id=5 ausgeben 
				// PatientService.readPatientInDB(5);
				
				
				
				
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

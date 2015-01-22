package de.rho.client;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
			System.out.println("MaClient up and running\n");
			
			
			//------------------------------------------------------
			//Testaufruf einer Methode eines Services auf dem Server
			//------------------------------------------------------
			
		
			//Patient patient = new Patient(1234567);
			//System.out.println(PatientService.createPatient("TEST Patient"));
			
			// ************************			
			// **** create Patient ****
			// ************************
			/*
			System.out.println("#02 - Create new Patient");

				// Objekt Patient
				Patient patient = new Patient();
				patient.setId(1);
				patient.setFirstname("Theo");
				patient.setLastname("Lingen");
				patient.setGender("m");
				patient.setDayofbirth("1920-05-29");
				patient.setAddressid(0);
				
				// Funktionsaufruf: create Patient
				PatientService.createPatientInDB(patient);
				patient = null; //reset
			*/
			
			// **********************
			// **** read Patient ****
			// **********************
			/*System.out.println("#02 - Get Patient with id=5");
				
				// **** Funktionsaufruf: Get Patient with id=5 
				patient = PatientService.readPatientInDB(5);
				System.out.println(patient.getFirstname() + " " + patient.getLastname());
				patient = null;
				*/
				
			// ************************
			// **** delete Patient ****
			// ************************				
			/*System.out.println("#03 - Delete Patient with id=24");		// Hier muss die (Max ID + 1) eingetragen werden. Zuerst wird ein User erstellt. Dann wieder gelöscht :-)
			
				// **** Funktionsaufruf: delete Patient ****
				PatientService.deletePatientInDB(24);					// Hier muss die (Max ID + 1) eingetragen werden. Zuerst wird ein User erstellt. Dann wieder gelöscht :-)
				*/
				
			// ************************
			// * write Patient to CSV *
			// ************************				
			System.out.println("#0x - Write CSV with sample data");		// Hier muss die (Max ID + 1) eingetragen werden. Zuerst wird ein User erstellt. Dann wieder gelöscht :-)
				
			// Patientenliste (kann auch nur ein Patient enthalten sein)
			
			
			ArrayList<Patient> patientList = new ArrayList<Patient>();
			patientList.add(new Patient(1, "Theo", "Lingen", "m", 0));
			patientList.add(new Patient(2, "Leo", "Bingen", "m", 0));
			patientList.add(new Patient(3, "Klaus", "Mussraus", "m", 0));
			patientList.add(new Patient(4, "Hans", "Willrein", "m", 0));
			PatientService.writePatientListToCSV(patientList);
			patientList = null;
			
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

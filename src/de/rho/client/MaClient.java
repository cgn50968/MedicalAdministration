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
			
			// **** Variablen und Objekte ****
			Patient patient = new Patient();
			
			
			//------------------------------------------------------
			//Testaufruf einer Methode eines Services auf dem Server
			//------------------------------------------------------
			
		
			//Patient patient = new Patient(1234567);
			//System.out.println(PatientService.createPatient("TEST Patient"));
			
			// ************************			
			// **** create Patient ****
			// ************************
			
			System.out.println("#02 - Create new Patient");
			
				// Patienten Objekt 
				patient.setFirstname("Theo");
				patient.setLastname("Lingen");
				patient.setGender("m");
				patient.setDayofbirth("1920-05-29");
				patient.setStreet("Landstrasse");
				patient.setHousenumber("2");
				patient.setPostalcode("50999");
				patient.setCity("Köln");
				
				// Funktionsaufruf: create Patient
				PatientService.createPatientInDB(patient);
				//patient = null; //reset
				
			
			
			// **********************
			// **** read Patient ****
			// **********************
			System.out.println("#03 - Get Patient with id=1");
				
				// **** Funktionsaufruf: Get Patient with id=1 
				patient = PatientService.readPatientInDB(1);
				System.out.println(patient.getId() + ";" + patient.getFirstname() + ";" + patient.getLastname() + ";" + patient.getCity());
				
				

			// ************************
			// **** update Patient ****
			// ************************		
			System.out.println("#04 - Update Patient with id=1");
			
				//Patienten Objekt
				patient.setId(1);
				patient.setFirstname("Maria");
				patient.setLastname("Schmitz");
				patient.setGender("w");
				patient.setDayofbirth("1967-10-03");
				patient.setAddressid(1);
				
				// **** Funktionsaufruf: update Patient ****
				PatientService.updatePatientInDB(patient);
								
			
			// ************************
			// **** delete Patient ****
			// ************************				
			/*System.out.println("#05 - Delete Patient with id=24");		// Hier muss die (Max ID + 1) eingetragen werden. Zuerst wird ein User erstellt. Dann wieder gelöscht :-)
			
				// **** Funktionsaufruf: delete Patient ****
				PatientService.deletePatientInDB(24);					// Hier muss die (Max ID + 1) eingetragen werden. Zuerst wird ein User erstellt. Dann wieder gelöscht :-)
				*/
				
			// ******************************
			// **** write Patient to CSV ****
			// ******************************				
			System.out.println("#0x - Write CSV with sample data");		
				
			
			// **** Patientenliste aus DB auslesen ****
			ArrayList<Patient> patientList = PatientService.getPatientListFromDB();
			
			
			// **** Patientenliste in CSV Datei schreiben ****
			PatientService.writePatientListToCSV(patientList);
			
			
			// **** Reset patientList ****
			patientList = null;
	

			
/*****************************/
/**** ENDE des TRY Blocks ****/
/*****************************/
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

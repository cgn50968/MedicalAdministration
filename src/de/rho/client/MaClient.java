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

			System.out.println("#01 - Start MaClient\n");
            
			System.out.println("-----------------------");
			System.out.println("MaClient up and running");
            System.out.println("-----------------------");			
			
			// *******************************
			// **** Variables und Objects ****
			// *******************************
			Patient patient = new Patient();
			
			
			/**********************************/
			/**** TEST CALLS ON APP SERVER ****/
			/**********************************/
						
						
				// ************************			
				// **** create Patient ****
				// ************************
				/*		System.out.println("#02 - create new Patient");
						
							// ****************************
							// **** set Patient object ****
							// **************************** 
							patient.setFirstname("Theo");
							patient.setLastname("Lingen");
							patient.setGender("m");
							patient.setDayofbirth("1920-05-29");
							patient.setStreet("Landstrasse");
							patient.setHousenumber("2");
							patient.setPostalcode("50999");
							patient.setCity("Köln");
							
							// ******************************
							// **** call: create Patient ****
							// ******************************
							PatientService.createPatientInDB(patient);
						
				*/		
				// **********************
				// **** read Patient ****
				// **********************
				/*		System.out.println("#03 - get Patient with id=1");
							
							// *************************************
							// **** call: get Patient with id=1 ****
							// *************************************
							patient = PatientService.readPatientInDB(1);
							System.out.println(patient.getId() + ";" + patient.getFirstname() + ";" + patient.getLastname() + ";" + patient.getCity());
							
				*/
				// ************************
				// **** update Patient ****
				// ************************		
				/*		System.out.println("#04 - update Patient with id=1");
						
							// ****************************
							// **** set Patient object ****
							// ****************************
							patient.setId(1);
							patient.setFirstname("Maria");
							patient.setLastname("Schmitz");
							patient.setGender("w");
							patient.setDayofbirth("1967-10-03");
							patient.setAddressid(1);
							
							// ******************************
							// **** call: update Patient ****
							// ******************************
							PatientService.updatePatientInDB(patient);
											
				*/		
				// ************************
				// **** delete Patient ****
				// ************************				
						System.out.println("#05 - Delete Patient with id=7");		// Hier muss die (Max ID + 1) eingetragen werden. Zuerst wird ein User erstellt. Dann wieder gelöscht :-)
						
							// ******************************
							// **** call: delete Patient ****
							// ******************************
							PatientService.deletePatientInDB(6, 6);					// Hier muss die (Max ID + 1) eingetragen werden. Zuerst wird ein User erstellt. Dann wieder gelöscht :-)
							
							
				// ******************************
				// **** read Patient from CSV ***
				// ******************************				
						System.out.println("#0x - read CSV with sample data");		
							
						// ******************************************
						// **** read Patient List from CSV File *****
						// ******************************************
						//PatientService.readPatientListFromCSV();
						
						// **********************************
						// **** write Patient List to DB ****
						// **********************************
						PatientService.writePatientListToDB(PatientService.readPatientListFromCSV());
						
						
						
						// ***************************
						// **** reset patientList ****
						// ***************************
						//patientList = null;
			
			
				// ******************************
				// **** write Patient to CSV ****
				// ******************************				
						System.out.println("#0x - write CSV with sample data");		
							
						// **********************************			
						// **** get Patient List from DB ****
						// **********************************
						ArrayList<Patient> patientList = PatientService.getPatientListFromDB();
						
						// ******************************************
						// **** write Patient List into CSV File ****
						// ******************************************
						PatientService.writePatientListToCSV(patientList);
						
						// ***************************
						// **** reset patientList ****
						// ***************************
						patientList = null;
				
			
		/**************************/
		/**** END OF TRY BLOCK ****/
		/**************************/
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

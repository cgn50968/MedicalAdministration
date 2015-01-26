package de.rho.client;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import de.rho.server.patient.boundary.InPatientService;
import de.rho.server.patient.entity.Patient;


/**
 * @author Heiko, Roger
 * @version 1.5
 * 
 * Client zum Aufrufen der entfernten Methoden einer Praxisverwaltung
 *
 */

public class MaClient {

	
	public static void main(String[] args) throws ParseException {
		
		try {

			System.out.println("#01 - Start MaClient\n");
			
			InPatientService PatientService = (InPatientService) Naming.lookup("rmi://localhost:1099/PatientService");
			//InPhysicianService PhysicianService = (InPhysicianService) Naming.lookup("rmi://localhost:1099/PhysicianService");
			//InMTService MTService = (InMTService) Naming.lookup("rmi://localhost:1099/MTService");
           
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

							SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
						
													
							patient.setFirstname("Theo");
							patient.setLastname("Lingen");
							patient.setGender("m");
							patient.setDayofbirth(format.parse("29.05.1920"));
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
						System.out.println("#03 - get Patient with id=1");
							
							// *************************************
							// **** call: get Patient with id=1 ****
							// *************************************
							patient = PatientService.readPatientInDB(40);
							System.out.println(patient.getId() + ";" + patient.getFirstname() + ";" + patient.getLastname() + ";" + patient.getCity());
							
				
				// **********************************
				// **** Check Date of last visit ****
				// **********************************
						System.out.println("#0x - check date of last visit");		
													
							int registerPatientCard = PatientService.checkDateOfLastVisit(patient);
							if (registerPatientCard == 1) {
								System.out.println(registerPatientCard);
								System.out.println("Neues Quartal: Versicherungskarte muss eingelesen werden.");
							}
							else {
								System.out.println(registerPatientCard);
								System.out.println("Versicherungskarte wurde in diesem Quartal bereits registriert.");
							}
								
							//TODO: wirft noch NPE, da spezifiziert werden muss, welcher Patient (id als Eingabeparamter der Methode)
																
							
				// ************************
				// **** update Patient ****
				// ************************		
				/*		System.out.println("#04 - update Patient with id=1");
						
							// ------------------------
							// -- set Patient object --
							// ------------------------
							patient.setId(1);
							patient.setFirstname("Maria");
							patient.setLastname("Schmitz");
							patient.setGender("w");
							patient.setDayofbirth("1967-10-03");
							patient.setAddressid(1);
							
							// --------------------------
							// -- call: update Patient --
							// --------------------------
							PatientService.updatePatientInDB(patient);
											
				*/		
				// ************************
				// **** delete Patient ****
				// ************************				
				/*		System.out.println("#05 - Delete Patient with id=7");		// Hier muss die (Max ID + 1) eingetragen werden. Zuerst wird ein User erstellt. Dann wieder gelöscht :-)
						
							// --------------------------
							// -- call: delete Patient --
							// --------------------------
							PatientService.deletePatientInDB(6, 6);					// Hier muss die (Max ID + 1) eingetragen werden. Zuerst wird ein User erstellt. Dann wieder gelöscht :-)
							
				*/			
				// *****************************************
				// **** import Patient from CSV into DB ****
				// *****************************************	
				/*			
						System.out.println("#0x - import PatientList from CSV");		
						
						//Bitte Aufrufverbund fuers testen nicht aendern
						PatientService.writePatientListToDB(PatientService.readPatientListFromCSV());
						
						//TODO Leerzeichenpruefung
						
						//viel wichtiger: was ist mit der RMI- Verbindung?
						//patientList.clear();
		
				*/
				// **********************************
				// **** write PatientList to CSV ****
				// **********************************				
						
						System.out.println("#0x - write PatientList to CSV");		
															
						//Bitte Aufrufverbund fuers testen nicht aendern
						
						PatientService.writePatientListToCSV(PatientService.getPatientListFromDB());
						
						//TODO Leerzeichenpruefung
												
						//viel wichtiger: was ist mit der RMI- Verbindung?
						//patientList.clear();
			
				
				// *************************************
				// **** search Patient by Last Name ****
				// *************************************
				/*		System.out.println("#0x - search Patient by Last-Name");		
								
						// ----------------------------------------------
						// -- search Patient with Last Name = 'Lingen' --
						// ----------------------------------------------
						String lastname = "Lingen";
								
						// --------------------
						// -- search Patient --
						// --------------------
						ArrayList<Patient> patientList = PatientService.searchPatientByNameInDB(lastname);
								
						// ------------------------
						// -- show search result --
						// ------------------------
						for (Patient patient01 : patientList) {
							String output = patient01.getId() + " ";
							output = output + patient01.getFirstname() + " ";
							output = output + patient01.getLastname() + " ";
							output = output + patient01.getGender() + " ";
							output = output + patient01.getDayofbirth() + " ";
							output = output + patient01.getLastvisit() + " ";
							output = output + patient01.getStreet() + " ";
							output = output + patient01.getHousenumber() + " ";
							output = output + patient01.getPostalcode() + " ";
							output = output + patient01.getCity();
							System.out.println(output);
						}

					*/
						
		/**************************/
		/**** END OF TRY BLOCK ****/
		/**************************/
		}
					
		catch (MalformedURLException e) {
			e.printStackTrace();
		}
		
		catch (RemoteException e) {
			e.printStackTrace();
		}
		
		catch (NotBoundException e) {
			e.printStackTrace();
		}
				
	}
	
}

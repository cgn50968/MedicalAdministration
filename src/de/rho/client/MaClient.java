package de.rho.client;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import de.rho.server.medstaff.boundary.InMedStaffService;
import de.rho.server.medstaff.entity.MedStaff;
import de.rho.server.mt.boundary.InMtService;
import de.rho.server.mt.entity.MedTreatment;
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

	
/*****************/
/**** General ****/
/*****************/
							
	// ------------------------------
	// ---- create Date of Today ----
	// ------------------------------
	private static Date today = new Date();

	
	public static void main(String[] args) throws ParseException {
		
		try {

			System.out.println("-------------------");
			System.out.println("1. - Start MaClient");
			System.out.println("-------------------\n");
			
			InPatientService PatientService = (InPatientService) Naming.lookup("rmi://localhost:1099/PatientService");
			InMedStaffService MedStaffService = (InMedStaffService) Naming.lookup("rmi://localhost:1099/MedStaffService");
			InMtService MtService = (InMtService) Naming.lookup("rmi://localhost:1099/MtService");
           
			System.out.println("\t-----------------------");
			System.out.println("\tMaClient up and running");
            System.out.println("\t-----------------------");			
			
			// *******************************
			// **** Variables und Objects ****
			// *******************************
			Patient patient = new Patient();
			MedStaff medstaff = new MedStaff();
			MedTreatment mt = new MedTreatment();
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
			

				
				
/**********************************/
/**** TEST CALLS ON APP SERVER ****/
/**********************************/
	System.out.println("\n--------------------------------------");
	System.out.println("2. - Testdurchlauf des PatientService");
	System.out.println("--------------------------------------");

/***************************/
/** TEST - PatientService **/
/***************************/
				
	// ************************			
	// **** create Patient ****
	// ************************
			System.out.println("\n2.1 - MaClient.client.PatientService.createPatientInDB()");
						
				// ------------------------
				// -- set Patient object --
				// ------------------------ 
				patient.setFirstname("Theo");
				patient.setLastname("Lingen");
				patient.setGender("m");
				patient.setDayofbirth(dateFormat.parse("29.05.1920"));
				patient.setStreet("Landstrasse");
				patient.setHousenumber("2");
				patient.setPostalcode("50999");
				patient.setCity("Köln");
				
				// --------------------------
				// -- call: create Patient --
				// --------------------------
				PatientService.createPatientInDB(patient);
						
		
	// **********************
	// **** read Patient ****
	// **********************
			System.out.println("\n2.2 - MaClient.client.PatientService.readPatientInDB()\n");
							
				// ---------------------------------
				// -- call: get Patient with id=1 --
				// ---------------------------------
				patient = PatientService.readPatientInDB(1);
				System.out.println("\tRückgabe: " + patient.getId() + ";" + patient.getFirstname() + ";" + patient.getLastname() + ";" + patient.getStreet() + ";" + patient.getHousenumber() + ";" + patient.getPostalcode() + ";" + patient.getCity());
										
				
	// **********************************
	// **** Check Date of last visit ****
	// **********************************
			System.out.println("\n2.3 - MaClient.client.PatientService.checkDateOfLastVisit()\n");		
											
				int registerPatientCard = PatientService.checkDateOfLastVisit(patient.getLastvisit());
				if (registerPatientCard == 1) {
					System.out.println("\tRückgabe: " + registerPatientCard);
					System.out.println("\tNeues Quartal: Versicherungskarte muss eingelesen werden.");
				}
				else {
					System.out.println("\tRückgabe: " + registerPatientCard);
					System.out.println("\tVersicherungskarte wurde in diesem Quartal bereits registriert.");
				}
								
				//TODO: wirft noch NPE, da spezifiziert werden muss, welcher Patient (id als Eingabeparamter der Methode)
																
							
	// ************************
	// **** update Patient ****
	// ************************		
			System.out.println("\n2.4 - MaClient.client.PatientService.updatePatientInDB()");
						
				// ------------------------
				// -- set Patient object --
				// ------------------------
				patient.setId(1);
				patient.setFirstname("Maria");
				patient.setLastname("Schmitz");
				patient.setGender("w");
				patient.setDayofbirth(dateFormat.parse("03.10.1967"));
				patient.setAddressid(1);
				patient.setStreet("Hauptstrasse");
				patient.setHousenumber("6");
				patient.setPostalcode("53902");
				patient.setCity("Euskirchen");
					
				// --------------------------
				// -- call: update Patient --
				// --------------------------
				PatientService.updatePatientInDB(patient);
										
						
	// ************************
	// **** delete Patient ****
	// ************************				
	/*	System.out.println("\n2.5 - MaClient.client.PatientService.deletePatientInDB()");		// Hier muss die (Max ID + 1) eingetragen werden. Zuerst wird ein User erstellt. Dann wieder gelöscht :-)
						
			// --------------------------
			// -- call: delete Patient --
			// --------------------------
			PatientService.deletePatientInDB(6, 6);					// Hier muss die (Max ID + 1) eingetragen werden. Zuerst wird ein User erstellt. Dann wieder gelöscht :-)
							
	*/			
	// *****************************************
	// **** import Patient from CSV into DB ****
	// *****************************************	
				
		System.out.println("\n2.6 - MaClient.client.PatientService.readPatientListFromCSV()");		
			
		
		PatientService.writePatientListToDB(PatientService.readPatientListFromCSV());
									
		
		//patientList.clear();
		
	
	// **********************************
	// **** write PatientList to CSV ****
	// **********************************				
						
		System.out.println("\n2.7 - MaClient.client.PatientService.writePatientListToCSV(PatientService.getPatientListFromDB())");		
															
				
		// -- Get PatientList object > Write PatientList object to file --
		PatientService.writePatientListToCSV(PatientService.getPatientListFromDB());
													
		
		//patientList.clear();
		
				
	// *************************************
	// **** search Patient by Last Name ****
	// *************************************
		System.out.println("\n2.8 - MaClient.client.PatientService.searchPatientByNameInDB()\n");		
								
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
			System.out.println("\t" + output);
		}


/****************************/
/** TEST - MedStaffService **/
/****************************/
		System.out.println("\n--------------------------------------");
		System.out.println("3. - Testdurchlauf des MedStaffService");
		System.out.println("--------------------------------------");

		
	// *************************			
	// **** create MedStaff ****
	// *************************			
		System.out.println("\n3.1 - MaClient.client.MedStaffService.createMedStaffInDB()");
		
								
		// -------------------------
		// -- set MedStaff object --
		// ------------------------- 
		medstaff.setFirstname("Doktor");
		medstaff.setLastname("Dolittle");
		medstaff.setRoleid(2);
		medstaff.setGender("m");
		medstaff.setDayofbirth(dateFormat.parse("01.04.1960"));
		medstaff.setStreet("Weißer Hauptstrasse");
		medstaff.setHousenumber("8");
		medstaff.setPostalcode("50996");
		medstaff.setCity("Köln");
						
		// ---------------------------
		// -- call: create MedStaff --
		// ---------------------------
		MedStaffService.createMedStaffInDB(medstaff);
		
		
	// ***********************
	// **** read MedStaff ****
	// ***********************
		System.out.println("\n3.2 - MaClient.client.MedStaffService.readMedStaffInDB\n");
												
		// ---------------------------------
		// -- call: get MedStaff with id=1 --
		// ---------------------------------
		medstaff = MedStaffService.readMedStaffInDB(1);
		System.out.println("\tRückgabe: " + medstaff.getId() + " " + medstaff.getFirstname() + " " + medstaff.getLastname() + " " + medstaff.getRole() + " " + medstaff.getStreet() + " " + medstaff.getHousenumber() + " " + medstaff.getPostalcode() + " " + medstaff.getCity());

		
	// *************************
	// **** update MedStaff ****
	// *************************
		System.out.println("\n3.3 - MaClient.client.MedStaffService.updateMedStaffInDB");
		
		// -------------------------
		// -- set MedStaff object --
		// ------------------------- 
		medstaff.setId(1);
		medstaff.setFirstname("Brad");
		medstaff.setLastname("Pitt");
		medstaff.setRoleid(1);
		medstaff.setGender("m");
		medstaff.setDayofbirth(dateFormat.parse("01.04.1968"));
		medstaff.setStreet("Musfeldstraße");
		medstaff.setHousenumber("1");
		medstaff.setPostalcode("47051");
		medstaff.setCity("Duisburg");
						
		// ---------------------------
		// -- call: update MedStaff --
		// ---------------------------
		MedStaffService.updateMedStaffInDB(medstaff);
		
	// ************************
	// **** delete Patient ****
	// ************************				
/*		System.out.println("\n3.4 - MaClient.client.MedStaffService.deleteMedStaffInDB\n");	
							
		// --------------------------
		// -- call: delete Patient --
		// --------------------------
		MedStaffService.deleteMedStaffInDB(2, 7);		// -- (2 = id, 7 = addressid)	
*/	

	// **********************************
	// **** get MedStaffList From DB ****
	// **********************************
		System.out.println("\n3.5 - MaClient.client.MedStaffService.getMedStaffListFromDB\n");
													
		// ------------------------------------
		// -- call: get MedStaffList From DB --
		// ------------------------------------
		ArrayList<MedStaff> medstaffList = MedStaffService.getMedStaffListFromDB();
		
    	// ---------------------------------------
    	// -- For each medstaff in medstaffList -- 
    	// ---------------------------------------
        
		for (MedStaff m : medstaffList) {
			System.out.println("\t" + m.getId() + " " + m.getFirstname() + " " + m.getLastname() + " " + m.getRole() + " " + m.getStreet() + " " + m.getHousenumber() + " " + m.getPostalcode() + " " + m.getCity());
        }

	
/**********************/
/** TEST - MtService **/
/**********************/
		System.out.println("\n--------------------------------");
		System.out.println("4. - Testdurchlauf des MTService");
		System.out.println("--------------------------------");
		
	// *******************			
	// **** create MT ****
	// *******************
		System.out.println("\n4.1 - MaClient.client.MtService.createMtInDB");
									
		// -------------------------
		// -- set MT object --
		// ------------------------- 
		mt.setPatientid(3);
		mt.setMedstaffid(2);
		mt.setDate(today);
		mt.setTreatment("Patient ist erkältet. Ruhe verordnet");
	
							
		// ---------------------------
		// -- call: create MedStaff --
		// ---------------------------
		MtService.createMtInDB(mt);
		
		
	// *****************
	// **** read MT ****
	// *****************
		System.out.println("\n4.2 - MaClient.client.MtService.readMtInDB\n");
													
			// ---------------------------------
			// -- call: get MedTreatment with id=1 --
			// ---------------------------------
			mt = MtService.readMtInDB(1);
			System.out.println("\tRückgabe: " + mt.getId() + " " + mt.getPatientid() + " " + mt.getPfirstname() + " " + mt.getPlastname() + " " + mt.getDayofbirth() + " " + mt.getMedstaffid() + " " + mt.getRole() + " " + mt.getMfirstname() + " " + mt.getMlastname() + " " + mt.getDate() + " " + mt.getTreatment());

			
	// *******************
	// **** update MT ****
	// *******************
			System.out.println("\n4.3 - MaClient.client.MtService.updateMtInDB");
				
		// -------------------
		// -- set MT object --
		// ------------------- 		
		mt.setId(2);
		mt.setMedstaffid(1);
		mt.setPatientid(3);
		mt.setDate(today);
		mt.setTreatment("Patient hat Bandscheibenvorfall. Überweisung an Orthopäden.");
		
		// ---------------------
		// -- call: update MT --
		// ---------------------
		MtService.updateMtInDB(mt);
		
		
	// ****************************
	// **** get MtList From DB ****
	// ****************************
		System.out.println("\n4.4 - MaClient.client.MtService.createMtInDB");
		
		
		// ------------------------------------------------
		// -- medstaff = get all treatments of Patient 1 --
		// ------------------------------------------------
		
		ArrayList<MedTreatment> mtList01 = MtService.getMtListFromDB(1);
			
	   	// ---------------------------
	   	// -- For each mt in mtList -- 
	   	// ---------------------------
		System.out.println("\n\tAll Medical Treatments of Patient: 1");
		for (MedTreatment mt01 : mtList01) {
			System.out.println("\t" + mt01.getId() + " " + mt01.getPatientid() + " " + mt01.getPfirstname() + " " + mt01.getPlastname() + " " + mt01.getDayofbirth() + " " + mt01.getMedstaffid() + " " + mt01.getRole() + " " + mt01.getMfirstname() + " " + mt01.getMlastname() + " " + mt01.getDate() + " " + mt01.getTreatment());
	    }
		
		// ------------------------------------------------
		// -- medstaff = get all treatments of Patient 3 --
		// ------------------------------------------------
		ArrayList<MedTreatment> mtList02 = MtService.getMtListFromDB(3);
		
	   	// ---------------------------
	   	// -- For each mt in mtList -- 
	   	// ---------------------------
		System.out.println("\n\tAll medical Treatments of Patient: 3");
		for (MedTreatment mt02 : mtList02) {
			System.out.println("\t" + mt02.getId() + " " + mt02.getPatientid() + " " + mt02.getPfirstname() + " " + mt02.getPlastname() + " " + mt02.getDayofbirth() + " " + mt02.getMedstaffid() + " " + mt02.getRole() + " " + mt02.getMfirstname() + " " + mt02.getMlastname() + " " + mt02.getDate() + " " + mt02.getTreatment());
	    }
		
		
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

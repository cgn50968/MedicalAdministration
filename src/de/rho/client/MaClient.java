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
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
			
			
/********/
/** DB **/
/********/

	// *****************************
	// **** create Tables in DB ****
	// *****************************
	System.out.println("\n--------------------------");
	System.out.println("1.1 - Create Tables in DB.");
	System.out.println("----------------------------");
	PatientService.createTablesInDB();
				
				
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
				Patient patient1 = new Patient();
				patient1.setFirstname("Theo");
				patient1.setLastname("Lingen");
				patient1.setGender("m");
				patient1.setDayofbirth(dateFormat.parse("29.05.1920"));
				patient1.setStreet("Landstrasse");
				patient1.setHousenumber("2");
				patient1.setPostalcode("50999");
				patient1.setCity("Köln");
				
				// --------------------------
				// -- call: create Patient --
				// --------------------------
				PatientService.createPatientInDB(patient1);
						
	
	// **********************
	// **** read Patient ****
	// **********************
	
			System.out.println("\n2.2 - MaClient.client.PatientService.readPatientInDB()\n");
							
				// ---------------------------------
				// -- call: get Patient with id=1 --
				// ---------------------------------
				Patient patient2 = PatientService.readPatientInDB(1);
				System.out.println("\tRückgabe: " + patient2.getId() + ";" + patient2.getFirstname() + ";" + patient2.getLastname() + ";" + patient2.getStreet() + ";" + patient2.getHousenumber() + ";" + patient2.getPostalcode() + ";" + patient2.getCity());
										
				
	// **********************************************
	// **** Check Date of last visit of patient2 **** 
	// **********************************************
	
			System.out.println("\n2.3 - MaClient.client.PatientService.checkDateOfLastVisit()\n");		
											
				int registerPatientCard = PatientService.checkDateOfLastVisit(patient2.getLastvisit());
				if (registerPatientCard == 1) {
					System.out.println("\tRückgabe: " + registerPatientCard);
					System.out.println("\tNeues Quartal: Versicherungskarte muss eingelesen werden.");
				}
				else {
					System.out.println("\tRückgabe: " + registerPatientCard);
					System.out.println("\tVersicherungskarte wurde in diesem Quartal bereits registriert.");
				}
								

																
							
	// ************************
	// **** update Patient ****
	// ************************		
	
			System.out.println("\n2.4 - MaClient.client.PatientService.updatePatientInDB()");
						
				// ------------------------
				// -- set Patient object --
				// ------------------------
				Patient patient3 = new Patient();
				patient3.setId(1);
				patient3.setFirstname("Maria");
				patient3.setLastname("Schmitz");
				patient3.setGender("w");
				patient3.setDayofbirth(dateFormat.parse("03.10.1967"));
				patient3.setAddressid(1);
				patient3.setStreet("Hauptstrasse");
				patient3.setHousenumber("6");
				patient3.setPostalcode("53902");
				patient3.setCity("Euskirchen");
					
				// --------------------------
				// -- call: update Patient --
				// --------------------------
				PatientService.updatePatientInDB(patient3);
										
				
	// ************************
	// **** delete Patient ****
	// ************************				
		System.out.println("\n2.5 - MaClient.client.PatientService.deletePatientInDB()");		// Hier muss die (Max ID + 1) eingetragen werden. Zuerst wird ein User erstellt. Dann wieder gelöscht :-)
						
			// --------------------------
			// -- call: delete Patient --
			// --------------------------
			PatientService.deletePatientInDB(4, 4);			// Biene Maja...	
							
				
	// *****************************************
	// **** import Patient from CSV into DB ****
	// *****************************************	
		System.out.println("\n2.6 - MaClient.client.PatientService.readPatientListFromCSV()");		
			
			// --------------------------------------------------------
			// -- get PatientList from CSV > write PatientList to DB --
			// --------------------------------------------------------
			PatientService.writePatientListToDB(PatientService.readPatientListFromCSV());
									
	
	// **********************************
	// **** write PatientList to CSV ****
	// **********************************				
	//TODO					
		System.out.println("\n2.7 - MaClient.client.PatientService.writePatientListToCSV(PatientService.getPatientListFromDB())");		
															
			// ---------------------------------------------------------------	
			// -- Get PatientList object > Write PatientList object to file --
			// ---------------------------------------------------------------
			PatientService.writePatientListToCSV(PatientService.getPatientListFromDB());
													
					
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
		for (Patient patient4 : patientList) {
			String output = patient4.getId() + " ";
			output = output + patient4.getFirstname() + " ";
			output = output + patient4.getLastname() + " ";
			output = output + patient4.getGender() + " ";
			output = output + patient4.getDayofbirth() + " ";
			output = output + patient4.getLastvisit() + " ";
			output = output + patient4.getStreet() + " ";
			output = output + patient4.getHousenumber() + " ";
			output = output + patient4.getPostalcode() + " ";
			output = output + patient4.getCity();
			System.out.println("\t" + output);
		}

		
/****************************/
/** TEST - MedStaffService **/
/****************************/
//TODO

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
		MedStaff medstaff1 = new MedStaff();
		medstaff1.setFirstname("Doktor");
		medstaff1.setLastname("Dolittle");
		medstaff1.setRoleid(2);
		medstaff1.setGender("m");
		medstaff1.setDayofbirth(dateFormat.parse("01.04.1960"));
		medstaff1.setStreet("Weißer Hauptstrasse");
		medstaff1.setHousenumber("8");
		medstaff1.setPostalcode("50996");
		medstaff1.setCity("Köln");
						
		// ---------------------------
		// -- call: create MedStaff --
		// ---------------------------
		MedStaffService.createMedStaffInDB(medstaff1);
		
		
	// ***********************
	// **** read MedStaff ****
	// ***********************
		System.out.println("\n3.2 - MaClient.client.MedStaffService.readMedStaffInDB\n");
												
		// ----------------------------------
		// -- call: get MedStaff with id=1 --
		// ----------------------------------
		MedStaff medstaff2 = MedStaffService.readMedStaffInDB(1);
		System.out.println("\tRückgabe: " + medstaff2.getId() + " " + medstaff2.getFirstname() + " " + medstaff2.getLastname() + " " + medstaff2.getRole() + " " + medstaff2.getStreet() + " " + medstaff2.getHousenumber() + " " + medstaff2.getPostalcode() + " " + medstaff2.getCity());

		
	// *************************
	// **** update MedStaff ****
	// *************************
		System.out.println("\n3.3 - MaClient.client.MedStaffService.updateMedStaffInDB");
		
		// -------------------------
		// -- set MedStaff object --
		// ------------------------- 
		MedStaff medstaff3 = new MedStaff();
		medstaff3.setId(1);
		medstaff3.setFirstname("Brad");
		medstaff3.setLastname("Pitt");
		medstaff3.setRoleid(1);
		medstaff3.setGender("m");
		medstaff3.setDayofbirth(dateFormat.parse("01.04.1968"));
		medstaff3.setStreet("Musfeldstraße");
		medstaff3.setHousenumber("1");
		medstaff3.setPostalcode("47051");
		medstaff3.setCity("Duisburg");
						
		// ---------------------------
		// -- call: update MedStaff --
		// ---------------------------
		MedStaffService.updateMedStaffInDB(medstaff3);
		
	// ************************
	// **** delete Patient ****
	// ************************				
		System.out.println("\n3.4 - MaClient.client.MedStaffService.deleteMedStaffInDB\n");	
							
		// --------------------------
		// -- call: delete Patient --
		// --------------------------
		MedStaffService.deleteMedStaffInDB(3, 8);		// -- (3 = id, 8 = addressid)	
	

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
		for (MedStaff medstaff4 : medstaffList) {
			System.out.println("\t" + medstaff4.getId() + " " + medstaff4.getFirstname() + " " + medstaff4.getLastname() + " " + medstaff4.getRole() + " " + medstaff4.getStreet() + " " + medstaff4.getHousenumber() + " " + medstaff4.getPostalcode() + " " + medstaff4.getCity());
        }

	
/**********************/
/** TEST - MtService **/
/**********************/
//TODO

		System.out.println("\n--------------------------------");
		System.out.println("4. - Testdurchlauf des MTService");
		System.out.println("--------------------------------");
		
	// *******************			
	// **** create MT ****
	// *******************
		System.out.println("\n4.1 - MaClient.client.MtService.createMtInDB");
									
		// -------------------
		// -- set MT object --
		// -------------------
		MedTreatment mt1 = new MedTreatment();
		mt1.setPatientid(3);
		mt1.setMedstaffid(2);
		mt1.setDate(today);
		mt1.setTreatment("Patient ist erkältet. Ruhe verordnet");
	
							
		// ---------------------------
		// -- call: create MedStaff --
		// ---------------------------
		MtService.createMtInDB(mt1);
		
		
	// *****************
	// **** read MT ****
	// *****************
		System.out.println("\n4.2 - MaClient.client.MtService.readMtInDB\n");
													
			// --------------------------------------
			// -- call: get MedTreatment with id=1 --
			// --------------------------------------
			MedTreatment mt2 = MtService.readMtInDB(1);
			System.out.println("\tRückgabe: " + mt2.getId() + " " + mt2.getPatientid() + " " + mt2.getPfirstname() + " " + mt2.getPlastname() + " " + mt2.getDayofbirth() + " " + mt2.getMedstaffid() + " " + mt2.getRole() + " " + mt2.getMfirstname() + " " + mt2.getMlastname() + " " + mt2.getDate() + " " + mt2.getTreatment());

			
	// *******************
	// **** update MT ****
	// *******************
			System.out.println("\n4.3 - MaClient.client.MtService.updateMtInDB");
				
		// -------------------
		// -- set MT object --
		// ------------------- 	
		MedTreatment mt3 = new MedTreatment();
		mt3.setId(2);
		mt3.setMedstaffid(1);
		mt3.setPatientid(3);
		mt3.setDate(today);
		mt3.setTreatment("Patient hat Bandscheibenvorfall. Überweisung an Orthopäden.");
		
		// ---------------------
		// -- call: update MT --
		// ---------------------
		MtService.updateMtInDB(mt3);
		
		
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
		for (MedTreatment mt4 : mtList01) {
			System.out.println("\t" + mt4.getId() + " " + mt4.getPatientid() + " " + mt4.getPfirstname() + " " + mt4.getPlastname() + " " + mt4.getDayofbirth() + " " + mt4.getMedstaffid() + " " + mt4.getRole() + " " + mt4.getMfirstname() + " " + mt4.getMlastname() + " " + mt4.getDate() + " " + mt4.getTreatment());
	    }
		
		// ------------------------------------------------
		// -- medstaff = get all treatments of Patient 3 --
		// ------------------------------------------------
		ArrayList<MedTreatment> mtList02 = MtService.getMtListFromDB(3);
		
	   	// ---------------------------
	   	// -- For each mt in mtList -- 
	   	// ---------------------------
		System.out.println("\n\tAll medical Treatments of Patient: 3");
		for (MedTreatment mt5 : mtList02) {
			System.out.println("\t" + mt5.getId() + " " + mt5.getPatientid() + " " + mt5.getPfirstname() + " " + mt5.getPlastname() + " " + mt5.getDayofbirth() + " " + mt5.getMedstaffid() + " " + mt5.getRole() + " " + mt5.getMfirstname() + " " + mt5.getMlastname() + " " + mt5.getDate() + " " + mt5.getTreatment());
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

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
							
	// ******************************
	// **** create Date of Today ****
	// ******************************
	private static Date today = new Date();

	
	public static void main(String[] args) throws ParseException {
		
		try {

			System.out.println("#01 - Start MaClient\n");
			
			InPatientService PatientService = (InPatientService) Naming.lookup("rmi://localhost:1099/PatientService");
			InMedStaffService MedStaffService = (InMedStaffService) Naming.lookup("rmi://localhost:1099/MedStaffService");
			InMtService MtService = (InMtService) Naming.lookup("rmi://localhost:1099/MtService");
           
			System.out.println("-----------------------");
			System.out.println("MaClient up and running");
            System.out.println("-----------------------");			
			
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
	System.out.println("\n#02 - Testdurchlauf des PatientService");
	System.out.println("--------------------------------------");

/***************************/
/** TEST - PatientService **/
/***************************/
				
	// ************************			
	// **** create Patient ****
	// ************************
			
	/*		System.out.println("1. Situation: Ein neuer Patient betritt die Praxis:");
			System.out.println("#a - create new Patient");
						
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
						
	*/	
	// **********************
	// **** read Patient ****
	// **********************
			System.out.println("\n#03 - get Patient with id=1\n");
							
				// ---------------------------------
				// -- call: get Patient with id=1 --
				// ---------------------------------
				patient = PatientService.readPatientInDB(1);
				System.out.println(patient.getId() + ";" + patient.getFirstname() + ";" + patient.getLastname() + ";" + patient.getCity());
										
				
	// **********************************
	// **** Check Date of last visit ****
	// **********************************
			System.out.println("#0x - check date of last visit");		
											
				int registerPatientCard = PatientService.checkDateOfLastVisit(patient.getLastvisit());
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
			System.out.println("#04 - update Patient with id=1");
						
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
				patient.setCity("Bad Münstereifel");
					
				// --------------------------
				// -- call: update Patient --
				// --------------------------
				PatientService.updatePatientInDB(patient);
										
						
	// ************************
	// **** delete Patient ****
	// ************************				
	/*	System.out.println("#05 - Delete Patient with id=7");		// Hier muss die (Max ID + 1) eingetragen werden. Zuerst wird ein User erstellt. Dann wieder gelöscht :-)
						
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
		
		// -- Get PatientList object > Write PatientList object to file --
		PatientService.writePatientListToCSV(PatientService.getPatientListFromDB());
						
		//TODO Leerzeichenpruefung
												
		//viel wichtiger: was ist mit der RMI- Verbindung?
		//patientList.clear();
		
				
	// *************************************
	// **** search Patient by Last Name ****
	// *************************************
	/*	System.out.println("#0x - search Patient by Last-Name");		
								
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

/****************************/
/** TEST - MedStaffService **/
/****************************/

	// ************************			
	// **** create Patient ****
	// ************************
					
		System.out.println("\n1. Situation: 1. Arbeitstag eines neuen Arztes.");
		System.out.println("#A: - create new MedStaff\n");
								
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
	System.out.println("\n#B: - get MedStaff with id=1\n");
												
		// ---------------------------------
		// -- call: get MedStaff with id=1 --
		// ---------------------------------
		medstaff = MedStaffService.readMedStaffInDB(1);
		System.out.println(medstaff.getId() + ";" + medstaff.getFirstname() + ";" + medstaff.getLastname() + ";" + medstaff.getRole() + ";" + medstaff.getCity());

		
	// *************************
	// **** update MedStaff ****
	// *************************

		System.out.println("\n#C: - update MedStaff\n");
		
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
/*		System.out.println("\n#D - delete MedStaff with id=1");	
							
		// --------------------------
		// -- call: delete Patient --
		// --------------------------
		MedStaffService.deleteMedStaffInDB(2, 7);		// -- (2 = id, 7 = addressid)	
*/	

	// **********************************
	// **** get MedStaffList From DB ****
	// **********************************
		System.out.println("\n#E: - get MedStaff List");
													
		// ------------------------------------
		// -- call: get MedStaffList From DB --
		// ------------------------------------
		ArrayList<MedStaff> medstaffList = MedStaffService.getMedStaffListFromDB();
		
    	// ---------------------------------------
    	// -- For each medstaff in medstaffList -- 
    	// ---------------------------------------
        
		for (MedStaff m : medstaffList) {
			System.out.println(m.getId() + ";" + m.getFirstname() + ";" + m.getLastname() + ";" + m.getRole() + ";" + m.getStreet() + " " + m.getHousenumber() + ";" + m.getPostalcode() + " " + m.getCity());
        }

	
/**********************/
/** TEST - MtService **/
/**********************/

	// ************************			
	// **** create Patient ****
	// ************************
		System.out.println("#x: - create new MT\n");
									
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
		
	// ****************************
	// **** get MtList From DB ****
	// ****************************
		System.out.println("\n#x: - get MT List");
		
		
		// ------------------------------------------------
		// -- medstaff = get all treatments of Patient 1 --
		// ------------------------------------------------
		
		ArrayList<MedTreatment> mtList01 = MtService.getMtListFromDB(1);
			
	   	// ---------------------------
	   	// -- For each mt in mtList -- 
	   	// ---------------------------
		System.out.println("\n1. Patient");
		for (MedTreatment mt01 : mtList01) {
			System.out.println(mt01.getId() + ";" + mt01.getPatientid() + ";" + mt01.getMedstaffid() + ";" + mt01.getDate() + ";" + mt01.getTreatment());
	    }
		
		// ------------------------------------------------
		// -- medstaff = get all treatments of Patient 3 --
		// ------------------------------------------------
		ArrayList<MedTreatment> mtList02 = MtService.getMtListFromDB(3);
		
	   	// ---------------------------
	   	// -- For each mt in mtList -- 
	   	// ---------------------------
		System.out.println("\n3. Patient");
		for (MedTreatment mt02 : mtList02) {
			System.out.println(mt02.getId() + ";" + mt02.getPatientid() + ";" + mt02.getMedstaffid() + ";" + mt02.getDate() + ";" + mt02.getTreatment());
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

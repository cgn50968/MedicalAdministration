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
 * @author Heiko Herder, Roger Ordon, Andreas Roewert
 * @version 1.5
 * 
 * Presentation Client - 2015-01-31
 *
 */

public class MaClientForPresentation {

	
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
			MedStaff medstaff = new MedStaff();
			MedTreatment mt = new MedTreatment();
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
			

/********/
/** DB **/
/********/

	// *****************************
	// **** create Tables in DB ****
	// *****************************
		System.out.println("\n-------------------------------------------------------");
		System.out.println("1.1 - Create Tables in DB.");
		System.out.println("-------------------------------------------------------\n");
		PatientService.createTablesInDB();
				
/*************************************************/
/**** TEST CALLS ON APP SERVER - PRESENTATION ****/
/*************************************************/
		System.out.println("\n---------------------------------------------------------------------------");
		System.out.println("2. - Testdurchlauf - Ausgewählte Methoden für die Präsentation - 31.01.2015");
		System.out.println("---------------------------------------------------------------------------");

		
/***************************/
/** TEST - PatientService **/
/***************************/

	
	// **********************
	// **** read Patient ****
	// **********************
		System.out.println("\n-------------------------------------------------------");
		System.out.println("2.1 - Arzthelferin ruft den Patienten mit der ID=3 auf.");
		System.out.println("-------------------------------------------------------\n");
		System.out.println("\tcall: MaClient.client.PatientService.readPatientInDB(3)\n");
							
		// ---------------------------------
		// -- call: get Patient with id=3 --
		// ---------------------------------
		Patient patient1 = PatientService.readPatientInDB(3);
		System.out.println("\tAusgabe: " + patient1.getId() + ": " + patient1.getFirstname() + " " + patient1.getLastname() + " - " + patient1.getStreet() + " " + patient1.getHousenumber() + " - " + patient1.getPostalcode() + " " + patient1.getCity());
										
	
	// **********************************
	// **** Check Date of last visit ****
	// **********************************	
		System.out.println("\n-------------------------------------------------------------------------------------------------");
		System.out.println("2.2 - Programm prüft, ob letzter Arztbesuch des Patienten innerhalb des aktuellen Quartals liegt.");
		System.out.println("-------------------------------------------------------------------------------------------------\n");
		System.out.println("\tMaClient.client.PatientService.checkDateOfLastVisit()\n");		
														
		int registerPatientCard = PatientService.checkDateOfLastVisit(patient1.getLastvisit());
		if (registerPatientCard == 1) {
			System.out.println("\tRückgabe: " + registerPatientCard);
			System.out.println("\tVersicherungskarte muss eingelesen werden.");
		}
		else {
			System.out.println("\tRückgabe: " + registerPatientCard);
			System.out.println("\tVersicherungskarte wurde in diesem Quartal bereits registriert.");
		}
											

	// ************************			
	// **** create Patient ****
	// ************************
		System.out.println("\n---------------------------------------------------------");
		System.out.println("2.3 - Arzthelferin legt neuen Patienten an. (Theo Lingen)");
		System.out.println("---------------------------------------------------------\n");
		System.out.println("\tcall: MaClient.client.PatientService.createPatientInDB()");
						
		// ------------------------
		// -- set Patient object --
		// ------------------------ 
		Patient patient2 = new Patient();
		patient2.setFirstname("Theo");
		patient2.setLastname("Lingen");
		patient2.setGender("m");
		patient2.setDayofbirth(dateFormat.parse("29.05.1920"));
		patient2.setStreet("Landstrasse");
		patient2.setHousenumber("2");
		patient2.setPostalcode("50999");
		patient2.setCity("Köln");
				
		// --------------------------
		// -- call: create Patient --
		// --------------------------
		PatientService.createPatientInDB(patient2);

		
	// *************************************
	// **** search Patient by Last Name ****
	// *************************************
		System.out.println("\n--------------------------------------------------------------------------------------------------");
		System.out.println("2.4 - Arzthelferin sucht zur Kontrolle den neu angelegten Patienten anhand des Nachnamen (Lingen).");
		System.out.println("--------------------------------------------------------------------------------------------------\n");
		System.out.println("\tcall: MaClient.client.PatientService.searchPatientByNameInDB()\n");		
											
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
		for (Patient patient : patientList) {
			String output = patient.getId() + ": ";
			output = output + patient.getFirstname() + " ";
			output = output + patient.getLastname() + " - Geschlecht: ";
			output = output + patient.getGender() + " - Geburtstag: ";
			output = output + patient.getDayofbirth() + " - Letzter Arztbesuch: ";
			output = output + patient.getLastvisit() + " - ";
			output = output + patient.getStreet() + " ";
			output = output + patient.getHousenumber() + " - ";
			output = output + patient.getPostalcode() + " ";
			output = output + patient.getCity();
			System.out.println("\t" + output);
		}
				
					
	// ************************
	// **** update Patient ****
	// ************************
		System.out.println("\n------------------------------------------------------------------------");
		System.out.println("2.5 - Der Strassenname wurde falsch geschrieben und muss geändert werden");
		System.out.println("------------------------------------------------------------------------\n");
		System.out.println("\tcall: MaClient.client.PatientService.updatePatientInDB()");
						
		// ------------------------
		// -- set Patient object --
		// ------------------------
		patient2.setId(6);
		//patient2.setFirstname("Maria");
		//patient2.setLastname("Schmitz");
		//patient2.setGender("w");
		//patient2.setDayofbirth(dateFormat.parse("03.10.1967"));
		patient2.setAddressid(9);
		patient2.setStreet("Hauptstrasse");
		//patient2.setHousenumber("6");
		//patient2.setPostalcode("53902");
		//patient2.setCity("Bad Münstereifel");
				
		// --------------------------
		// -- call: update Patient --
		// --------------------------
		PatientService.updatePatientInDB(patient2);
										
						
	// **********************************
	// **** write PatientList to CSV ****
	// **********************************
		System.out.println("\n-----------------------------------------------");
		System.out.println("2.6 - Arzt sichert Patientenliste in CSV Datei.");
		System.out.println("-----------------------------------------------\n");
		System.out.println("\tcall: MaClient.client.PatientService.writePatientListToCSV(PatientService.getPatientListFromDB())");		
															
		// ---------------------------------------------------------------		
		// -- Get PatientList object > Write PatientList object to file --
		// ---------------------------------------------------------------
		PatientService.writePatientListToCSV(PatientService.getPatientListFromDB());
													
		//patientList.clear();
		
		
	// ************************
	// **** delete Patient ****
	// ************************				
	/*	System.out.println("\n-----------------------------------------------------------------------------------------------------------------");
		System.out.println("2.7 - Theo Lingen wird wieder gelöscht, damit der Client wiederholt mit gleichem Ergebnis ausgeführt werden kann.");
		System.out.println("-----------------------------------------------------------------------------------------------------------------\n");
		System.out.println("\tcall: MaClient.client.PatientService.deletePatientInDB()");									

		// --------------------------
		// -- call: delete Patient --
		// --------------------------
		PatientService.deletePatientInDB(6, 9); // (6=patientid, 6=addressid)
								
								
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

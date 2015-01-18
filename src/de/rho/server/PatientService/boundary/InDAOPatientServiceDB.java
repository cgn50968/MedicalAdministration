package de.rho.server.PatientService.boundary;

import java.util.ArrayList;

import de.rho.server.PatientService.entity.Patient;


/**
 * @author Heiko
 * @version 1.0
 * 
 * Interface als interne Schnittstelle (2DB)
 * Methodenvorlage für Datenbankzugriffe
 *
 */

public interface InDAOPatientServiceDB {
	
	public void createPatientDB(Patient patient);
	public Patient readPatientDB(int id);
	public void updatePatientDB(Patient patient);
	public void deletePatientDB(int id);
	
	public ArrayList<Patient> readPatientListDB(String searchString);
	public ArrayList<Patient> searchPatientListDB(String searchString); 
	

}




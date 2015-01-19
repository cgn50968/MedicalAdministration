package de.rho.server.patient.boundary;

import java.util.ArrayList;

import de.rho.server.patient.entity.Patient;


/**
 * @author Heiko
 * @version 1.0
 * 
 * Interface als interne Schnittstelle (2DB)
 * Methodenvorlage fuer Datenbankzugriffe
 *
 */

public interface InPatientToDB {
	
	public void createPatientDB(Patient patient);
	public Patient readPatientDB(int id);
	public void updatePatientDB(Patient patient);
	public void deletePatientDB(int id);
	
	public ArrayList<Patient> readPatientListDB(String searchString);
	public ArrayList<Patient> searchPatientListDB(String searchString); 
	

}




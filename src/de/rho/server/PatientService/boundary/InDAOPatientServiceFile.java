package de.rho.server.PatientService.boundary;

import java.util.ArrayList;

import de.rho.server.PatientService.entity.Patient;


/**
 * @author Heiko
 * @version 1.0
 * 
 * Interface als interne Schnittstelle (2CSV)
 * Methodenvorlage für Dateizugriffe
 *
 */

public interface InDAOPatientServiceFile {
	
	public ArrayList<Patient> readPatientListfromFile(String list);
	
	public void writePatientListtoFile(ArrayList<Patient> list);
		

}

package de.rho.server.PatientService.control;

import java.util.ArrayList;

import de.rho.server.PatientService.boundary.InDAOPatientServiceFile;
import de.rho.server.PatientService.entity.Patient;


/**
 * @author Heiko
 * @version 1.0
 * 
 * konkrete Klasse zum Lesen und Schreiben von CSV-Dateien
 * 
 *
 */

public class PatientServicetoCSV implements InDAOPatientServiceFile{

	@Override
	public ArrayList<Patient> readPatientListfromFile(String list) {
		System.out.println("reading from CSV..."); //CSV als Dateiformat angedacht
		return null;
	}

	@Override
	public void writePatientListtoFile(ArrayList<Patient> list) {
		System.out.println("writing to CSV..."); //CSV als Dateiformat angedacht
		
	}

	
}

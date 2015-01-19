package de.rho.server.patient.control;

import java.util.ArrayList;

import de.rho.server.patient.boundary.InPatientToCSV;
import de.rho.server.patient.entity.Patient;


/**
 * @author Heiko Herder, Roger Ordon, Andreas Röwert
 * @version 1.1
 * 
 * konkrete Klasse zum Lesen und Schreiben von CSV-Dateien
 * 
 *
 */

public class PatientToCSV implements InPatientToCSV{

	@Override
	public ArrayList<Patient> readPatientFromCSV(String list) {
		System.out.println("reading from CSV..."); //CSV als Dateiformat angedacht
		return null;
	}

	@Override
	public void writePatientToCSV(ArrayList<Patient> list) {
		System.out.println("writing to CSV..."); //CSV als Dateiformat angedacht
		
	}

	
}

package de.rho.server.PatientService.control;

import java.util.ArrayList;

import de.rho.server.PatientService.boundary.InDAOPatientServiceDB;
import de.rho.server.PatientService.entity.Patient;
import de.rho.server.PatientService.persistence.H2DB;
import de.rho.server.PatientService.persistence.MySQLDB;


/**
 * @author Heiko
 * @version 1.0
 * 
 * konkrete Klasse zum Regeln der Datenbankzugriffe
 *
 */


public class PatientServicetoDB implements InDAOPatientServiceDB{

	private H2DB h2db;										//Deklaration für H2DB
	private MySQLDB mysqldb; 								//Deklaration für MySQLDB
	
	
	@Override
	public void createPatientDB(Patient patient) {
		System.out.println("ServicetoDB: Methode createPatientDB");
		
		String sql = "SELECT alles von allem";
		this.h2db = new H2DB();								//Instanziierung H2DB (Default-Konstruktor)
		
		System.out.println("ServicetoDB: Trying to locate H2DB");
		
		h2db.connect();
		h2db.executeQuery(sql);
	}
	
	
	@Override
	public Patient readPatientDB(int id) {
		System.out.println("ServicetoDB: Methode readPatientDB");
		
		String sql = "SELECT alles von allem";
		this.h2db = new H2DB();								//Instanziierung H2DB (Default-Konstruktor)
		
		System.out.println("ServicetoDB: Trying to locate H2DB");
		
		h2db.connect();
		h2db.executeQuery(sql);
		
		return null;
	}

	@Override
	public void updatePatientDB(Patient patient) {
		System.out.println("ServicetoDB: Methode updatePatientDB: Processing SQL: Done.");
	}

	@Override
	public void deletePatientDB(int id) {
		System.out.println("ServicetoDB: Methode deletePatientDB: Processing SQL: Done.");
	}
	
	@Override
	public ArrayList<Patient> readPatientListDB(String searchString) {
		System.out.println("ServicetoDB: Methode readPatientListDB: Processing SQL: Erstelle Liste.");
		return null;
	}
	
	@Override
	public ArrayList<Patient> searchPatientListDB(String searchString) {
		System.out.println("ServicetoDB: Methode searchPatientList: Processing SQL: Suche nach Kriterien und erstelle Liste.");
		return null;
		
	}

		 
	
	
	
}
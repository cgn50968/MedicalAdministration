package de.rho.server.patient.control;

import java.util.ArrayList;

import de.rho.server.dao.persistence.DaoToFile;
import de.rho.server.dao.persistence.DaoToH2DB;
import de.rho.server.patient.boundary.InPatientToDB;
import de.rho.server.patient.entity.Patient;


/**
 * @author Heiko Herder, Roger Ordon, Andreas Röwert
 * @version 1.1
 * 
 * konkrete Klasse zum Regeln der Datenbankzugriffe
 *
 */


public class PatientToDB implements InPatientToDB{

	private DaoToH2DB h2db;										//Deklaration fuer DaoToH2DB
	private DaoToFile mysqldb; 								//Deklaration fuer DaoToFile
	
	
	@Override
	public void createPatientDB(Patient patient) {
		System.out.println("ServicetoDB: Methode createPatientDB");
		
		String sql = "SELECT alles von allem";
		this.h2db = new DaoToH2DB();								//Instanziierung DaoToH2DB (Default-Konstruktor)
		
		System.out.println("ServicetoDB: Trying to locate DaoToH2DB");
		
		h2db.connect();
		h2db.executeQuery(sql);
	}
	
	
	@Override
	public Patient readPatientDB(int id) {
		System.out.println("ServicetoDB: Methode readPatientDB");
		
		String sql = "SELECT alles von allem";
		this.h2db = new DaoToH2DB();								//Instanziierung DaoToH2DB (Default-Konstruktor)
		
		System.out.println("ServicetoDB: Trying to locate DaoToH2DB");
		
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
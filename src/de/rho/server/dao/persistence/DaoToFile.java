package de.rho.server.dao.persistence;

import java.io.FileWriter;
import java.io.IOException;

import de.rho.server.dao.boundary.InDaoToFile;
import de.rho.server.patient.entity.Patient;

public class DaoToFile implements InDaoToFile{

	@Override
	public String locateFile() {
		
		String filetype = (".csv");
		String filename = ("testw"+filetype);
		String pathtofile = ("c:/Users/heiko/Documents/GitHub/MedicalAdministration/"+filename);
		 
		return pathtofile;
	}

	@Override
	public void generateFile() {
		
		//if File schon da print "Datei schon vorhanden, bitte erst loeschen"
		//else writeFile()
		
	}

	

}

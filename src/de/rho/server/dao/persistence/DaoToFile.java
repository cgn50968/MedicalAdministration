package de.rho.server.dao.persistence;

import java.io.File;

import de.rho.server.dao.boundary.InDaoToFile;


public class DaoToFile implements InDaoToFile{

	@Override
	public String locateFile() {			//koennte man auch ueber Client mitgeben
		
		String filetype = (".csv");
		String filename = ("testw"+filetype);
		String pathtoffile = (""+filename); // hier koennte auch ein vollstaendiger Pfad stehen c:/Users/heiko/Documents/GitHub/MedicalAdministration/
		 
		return pathtoffile;
	}

	@Override
	public boolean permitFileGeneration() {
		
		File file = new File (locateFile()); //nutzt Rueckgabewert (pathtofile) von locateFile()
		
		if (file.exists()) {
			System.out.println("Datei vorhanden. Erstellen-Status: 'false'. (Nein)");
			return false;
		}
		
		else {
			System.out.println("Datei nicht vorhanden. Erstellen-Status: 'true'. (Ja) Datei kann erstellt werden.");
			return true;			 
		}
	}
}

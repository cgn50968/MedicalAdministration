package de.rho.server.dao.boundary;

import de.rho.server.patient.entity.Patient;


public interface InDaoToFile {
	
	/**** path to file ****/
	public String locateFile();
		
	/**** handle File ****/
	public void generateFile();
	    
	

}

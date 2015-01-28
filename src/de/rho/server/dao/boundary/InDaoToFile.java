package de.rho.server.dao.boundary;

/**
 * @author Heiko, Roger
 * @version 1.2
 * 
 * Interface als interne Schnittstelle (File-Verbindung)
 * Methodenvorlage fuer Dateienhandling
 *
 */

public interface InDaoToFile {
	
	// -- For Path to file
	public String locateFile();
		
	// For Permission to generate file
	public boolean permitFileGeneration();
	    
	

}

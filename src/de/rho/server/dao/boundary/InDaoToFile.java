package de.rho.server.dao.boundary;


public interface InDaoToFile {
	
	/**** open connection to file ****/
	public void openFile();
		
	/**** read/write File ****/
	public void handleFile();
	    
	/**** close connection to file ****/
	public void disconnect();

}

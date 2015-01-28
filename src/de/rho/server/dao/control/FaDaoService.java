package de.rho.server.dao.control;

import java.rmi.RemoteException;

import de.rho.server.dao.boundary.InDaoToDB;
import de.rho.server.dao.boundary.InDaoToFile;
import de.rho.server.dao.persistence.DaoToFile;
import de.rho.server.dao.persistence.DaoToDB;

/**
 * @author Heiko, Roger
 * @version 1.4
 * 
 * Factory zum Erstellen von Verbindungsobjekten (Connection-Services)
 * 
 */

public class FaDaoService {
	
	public static InDaoToDB getDaoToDBService() throws RemoteException {
		return new DaoToDB();
	}
	
	public static InDaoToFile getDaoToFileService() throws RemoteException {
		return new DaoToFile();
	}
	
}

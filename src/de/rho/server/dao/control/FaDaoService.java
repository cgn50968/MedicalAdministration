package de.rho.server.dao.control;

import java.rmi.RemoteException;

import de.rho.server.dao.boundary.InDaoToDB;
import de.rho.server.dao.boundary.InDaoToFile;
import de.rho.server.dao.persistence.DaoToFile;
import de.rho.server.dao.persistence.DaoToDB;

/**
 * @author Heiko
 * @version 1.2
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

/*
    // Die Fabrik-Methode mit if
    public InDaoToDB createDatabase()
    {
        if( getDatabaseType().equalsIgnoreCase("H2") )
        	databaseDAO = new DaoToDB();
        if( getDatabaseType().equalsIgnoreCase("mysql") )
            databaseDAO = new DaoToFile();

        return databaseDAO;
    }

        
    // Die Datenbank-Fabrik entscheidet hier intern, welches Datenbank-Objekt sie erstellt:
    private String getDatabaseType()
    {
        return "H2";
    }
*/
	
}

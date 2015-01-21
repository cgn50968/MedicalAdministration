package de.rho.server.dao.control;

import java.rmi.RemoteException;

import de.rho.server.dao.boundary.InDaoToDB;
import de.rho.server.dao.persistence.DaoToFile;
import de.rho.server.dao.persistence.DaoToDB;
import de.rho.server.patient.boundary.InPatientService;
import de.rho.server.patient.control.PatientServiceImpl;

/**
 * @author Heiko, Roger
 * @version 1.1
 * 
 * Factory zum Erstellen von DB-Verbindungsobjekten
 * 
 */

public class FaDaoService {
	
	public static InDaoToDB getDaoToDBService() throws RemoteException {
		return new DaoToDB();
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

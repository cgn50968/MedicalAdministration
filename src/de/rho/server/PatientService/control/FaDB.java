package de.rho.server.PatientService.control;

import de.rho.server.PatientService.boundary.InDBConnect;
import de.rho.server.PatientService.persistence.H2DB;
import de.rho.server.PatientService.persistence.MySQLDB;

/**
 * @author Heiko
 * @version 1.0
 * 
 * Factory zum Erstellen von DB-Verbindungsobjekten
 * 
 */

public class FaDB {
	
	private InDBConnect databaseDAO;

    // Die Fabrik-Methode mit if
    public InDBConnect createDatabase()
    {
        if( getDatabaseType().equalsIgnoreCase("H2") )
        	databaseDAO = new H2DB();
        if( getDatabaseType().equalsIgnoreCase("mysql") )
            databaseDAO = new MySQLDB();

        return databaseDAO;
    }

        
    // Die Datenbank-Fabrik entscheidet hier intern, welches Datenbank-Objekt sie erstellt:
    private String getDatabaseType()
    {
        return "H2";
    }

}

package de.rho.server.dao.control;

import de.rho.server.dao.boundary.InDaoToDB;
import de.rho.server.dao.persistence.DaoToFile;
import de.rho.server.dao.persistence.DaoToH2DB;

/**
 * @author Heiko
 * @version 1.0
 * 
 * Factory zum Erstellen von DB-Verbindungsobjekten
 * 
 */

public class FaDaoService {
	
	private InDaoToDB databaseDAO;

    // Die Fabrik-Methode mit if
    public InDaoToDB createDatabase()
    {
        if( getDatabaseType().equalsIgnoreCase("H2") )
        	databaseDAO = new DaoToH2DB();
        if( getDatabaseType().equalsIgnoreCase("mysql") )
            databaseDAO = new DaoToFile();

        return databaseDAO;
    }

        
    // Die Datenbank-Fabrik entscheidet hier intern, welches Datenbank-Objekt sie erstellt:
    private String getDatabaseType()
    {
        return "H2";
    }

}

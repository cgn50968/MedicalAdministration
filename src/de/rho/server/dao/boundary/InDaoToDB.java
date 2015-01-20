package de.rho.server.dao.boundary;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;



/**
 * @author Heiko, Roger
 * @version 1.1
 * 
 * Interface als interne Schnittstelle (DB-Verbindung)
 * Methodenvorlage fuer Datenbankverbindung
 *
 */

public interface InDaoToDB {
	
	public Connection connect() throws FileNotFoundException, IOException;
	
    public void executeQuery(Connection con, String sql);

}

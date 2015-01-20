package de.rho.server.dao.boundary;

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
	
	public Connection connect();
	
    public void executeQuery(Connection con, String sql);

}

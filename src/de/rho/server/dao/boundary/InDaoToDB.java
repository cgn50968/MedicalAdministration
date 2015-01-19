package de.rho.server.dao.boundary;

/**
 * @author Heiko
 * @version 1.0
 * 
 * Interface als interne Schnittstelle (DB-Verbindung)
 * Methodenvorlage für Datenbankverbindung
 *
 */

public interface InDaoToDB {
	
	public void connect();
    public void executeQuery(String sql);

}

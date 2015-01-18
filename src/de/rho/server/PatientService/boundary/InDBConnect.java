package de.rho.server.PatientService.boundary;

/**
 * @author Heiko
 * @version 1.0
 * 
 * Interface als interne Schnittstelle (DB-Verbindung)
 * Methodenvorlage für Datenbankverbindung
 *
 */

public interface InDBConnect {
	
	public void connect();
    public void executeQuery(String sql);

}

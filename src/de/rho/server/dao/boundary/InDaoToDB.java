package de.rho.server.dao.boundary;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Heiko, Roger
 * @version 1.1
 * 
 * Interface als interne Schnittstelle (DB-Verbindung)
 * Methodenvorlage fuer Datenbankverbindung
 *
 */

public interface InDaoToDB {
	
	// -- For open Connection to DB
	public Connection connect() throws FileNotFoundException, IOException;
	
	// -- For execute SQL Query
    public ResultSet executeQuery(Connection con, String sql_statement, Boolean returnResultSet);
    
    // -- For close Connection 
    public void disconnect(Connection con, ResultSet resultSet) throws SQLException;
    
}

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
	
	/**** open Connection to DB ****/
	public Connection connect() throws FileNotFoundException, IOException;
	
	/**** execute SQL Query ****/
    public void executeQuery(Connection con, String sql);
    
    /**** execute SQL Query with ResultSet expected ****/
    public ResultSet executeQueryResultSet(Connection con, String sql);
    
    /**** close Connection ****/
    public void disconnect(Connection con, ResultSet res) throws SQLException;
    
    /**** TEST ****/
    public ResultSet executeQueryTEST(Connection con, String sql, Boolean ret);

}

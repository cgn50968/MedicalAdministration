package de.rho.server.dao.persistence;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import de.rho.server.dao.boundary.InDaoToDB;

public class DaoToDB implements InDaoToDB {
	
			
	/**
	 * @author Heiko, Roger
	 * @version 1.4
	 * @return java.sql.Connection
	 *   
	 */

	// *****************
	// **** Objects ****
	// *****************
	private ResultSet resultSet;
	private PreparedStatement preStat;
		
	
/*******************************/	
/**** handle DB Connections ****/
/*******************************/
	
	
	// *******************************
	// **** open Connection to DB ****
	// *******************************
	public Connection connect() throws IOException, FileNotFoundException  {
		System.out.println("DaoToDB.connect"); //debug
        
		// ---------------------------------------
		// -- Properties object for Config file --
		// ---------------------------------------
		Properties properties = new Properties();
        
		// -------------------
		// -- reset Objects --
		// -------------------
		FileInputStream fis = null;
        Connection con = null;
        
        try {
            fis = new FileInputStream("database.properties");
            properties.load(fis);
 
            // -------------------
            // -- set DB driver --
            // -------------------
            Class.forName(properties.getProperty("DB_DRIVER"));
 
            // ---------------------------------------
            // -- set credentials for DB Connection --
            // ---------------------------------------
            con = DriverManager.getConnection(properties.getProperty("DB_URL"),
            								  properties.getProperty("DB_USER"),
            								  properties.getProperty("DB_PASSWORD"));  
        } 
        catch (IOException | ClassNotFoundException | SQLException e) {
                e.printStackTrace();
        }
        
        // ------------------------------
        // -- return Connection object --
        // ------------------------------
        return con;
    }
		
	
	// ***************************
	// **** execute SQL Query ****
	// ***************************
	public ResultSet executeQuery(Connection con, String sql_statement, Boolean returnResultSet) {
		System.out.println("DaoToDB.executeQuery"); //debug
			
		// -------------------
		// -- reset Objects --
		// -------------------
		resultSet = null;
		preStat = null;
		
		try {
			
			// -----------------------------------------
			// -- Connection to DB with SQL-Statement --
			// -----------------------------------------
			preStat = con.prepareStatement(sql_statement);
			
			
			// -------------------------
			// -- return NO ResultSet --
			// -------------------------
			if (returnResultSet == false) {
				preStat.execute();
			}
			
			
			// ----------------------
			// -- return ResultSet --
			// ----------------------
			else if (returnResultSet == true) {
				resultSet = preStat.executeQuery();
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}	
		
		
		// -------------------------------------
		// -- return ResultSet (empty or not) --
		// -------------------------------------
		return resultSet;
	}

	
	// **************************
	// **** close Connection ****
    // **************************
	public void disconnect(Connection con, ResultSet resultSet) throws SQLException {
		System.out.println("DaoToDB.disconnect"); //debug
		
        try {
			if (resultSet != null) {
				System.out.println("DaoToDB.disconnect.resultSet.close"); //debug
				
				// ---------------------
				// -- close ResultSet --
				// ---------------------
				resultSet.close();
			}

			if (con != null) {
				System.out.println("DaoToDB.disconnect.con.close"); //debug
				
				// -------------------------
				// -- close DB Connection --
				// -------------------------
				con.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
	    
package de.rho.server.dao.persistence;

//import java.awt.List;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.List;

import de.rho.server.dao.boundary.InDaoToDB;

public class DaoToDB implements InDaoToDB {
	
			
	/**
	 * @author Heiko, Roger
	 * @version 1.21
	 * @return java.sql.Connection
	 *   
	 */

	/** Objects **/
	private ResultSet resultSet;
	private PreparedStatement preStat;
		
	
	/**** open Connection to DB ****/
	public Connection connect() throws IOException, FileNotFoundException  {
		System.out.println("open DB Connection..."); //debug
        
		/** reset **/
		Properties properties = new Properties();
        FileInputStream fis = null;
        Connection con = null;
        
        try {
            fis = new FileInputStream("database.properties");
            properties.load(fis);
 
            // DB Treiber zuweisen
            Class.forName(properties.getProperty("DB_DRIVER"));
 
            // Connection aus Dateiquelle erstellen
            con = DriverManager.getConnection(properties.getProperty("DB_URL"),
            								  properties.getProperty("DB_USER"),
            								  properties.getProperty("DB_PASSWORD"));  
        } 
        catch (IOException | ClassNotFoundException | SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return con;
    }
		

	/**** execute SQL Query ****/
	public ResultSet executeQuery(Connection con, String sql, Boolean ret) {
		System.out.println("execute SQL-Query..."); //debug
			
		/** reset **/
		resultSet = null;
		preStat = null;
		
		try {
			preStat = con.prepareStatement(sql);
			if (ret == false) {
				preStat.execute();
			}
			else if (ret == true) {
				resultSet = preStat.executeQuery();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		return resultSet;
	}

	
	/**** close Connection ****/
	public void disconnect(Connection con, ResultSet res) throws SQLException {
		System.out.println("close DB Connection..."); //debug
        try {
			if (res != null) {
			    res.close();
			}

			if (con != null) {
			    con.close();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
	    
	    
	    
	
/*
	
	public Connection connect() {			// Datenbankverbindung
			
		System.out.println("DaoToDB.connect");
			
		Connection con = null;				//Connection deklarieren
			
			/*
			String driver = "org.h2.Driver";
			String db_url = "jdbc:h2:tcp://localhost/~/test";
			String user = "sa";
			String password = "";
			*/
			/*
		try {
			Class.forName("org.h2.Driver");
			con = DriverManager.getConnection("jdbc:h2:tcp://localhost/~/test", "sa", "");
		}
		
		catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
		}

		return con;
	}

    public void executeQuery(String sql) {
        System.out.println("H2 Executing Query: ");
        System.out.println(sql);
        System.out.println("on H2 database");
    }
*/


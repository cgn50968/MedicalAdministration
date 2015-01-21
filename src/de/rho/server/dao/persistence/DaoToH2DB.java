package de.rho.server.dao.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;


import de.rho.server.dao.boundary.InDaoToDB;

public class DaoToH2DB implements InDaoToDB {
	
			
	/**
	 * @author Heiko, Roger
	 * @version 1.21
	 * @return java.sql.Connection
	 *   
	 */
		
	public Connection connect() throws IOException, FileNotFoundException  {
        
		Properties properties = new Properties();
        FileInputStream fis = null;
        Connection con = null;
        
        try {
            fis = new FileInputStream("database.properties");
            properties.load(fis);
 
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
		
		
	public void executeQuery(Connection con, String sql) {
	
		System.out.println("Fuehre SQL-Query aus . . .");
			
		try {
			PreparedStatement pStat = con.prepareStatement(sql);
			pStat.execute();
		}
		
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
	    
	    
	    
	
/*
	
	public Connection connect() {			// Datenbankverbindung
			
		System.out.println("DaoToH2DB.connect");
			
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

